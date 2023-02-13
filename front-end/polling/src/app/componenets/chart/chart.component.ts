import { HttpErrorResponse } from '@angular/common/http';
import {
  AfterViewChecked,
  AfterViewInit,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import Chart, { ChartItem } from 'chart.js/auto';
import { catchError, of, filter, takeUntil, Subject, forkJoin } from 'rxjs';
import { AnswerWithQuestion } from 'src/app/model/answer-with-question';
import { PollModel } from 'src/app/model/poll-model';
import { PollService } from 'src/app/services/poll.service';
import { OnDestroy } from '@angular/core';
import { PollWithQuestions } from 'src/app/model/PollWithQuestions';
import { OpenQuestionWithAnswers } from 'src/app/model/openQuestionWithAnswers';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss'],
})
export class ChartComponent implements OnInit, AfterViewInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  @Input() poll?: PollModel; // decorate the property with @Input()

  isReady: boolean = false;
  isOpenBallot: boolean = false;
  pollWithQuestions: PollWithQuestions | undefined;
  chart: any;
  unique: String[] = [];

  openQuestionWithAnswersConst?: OpenQuestionWithAnswers;
  openQuestionWithAnswers: OpenQuestionWithAnswers[] = [];

  private _answerWithQuestion: AnswerWithQuestion[] = [];
  private _answers: String[] = [];

  constructor(private _pollService: PollService) {}

  ngAfterViewInit(): void {}

  ngOnInit() {
    if (this.poll) {
      forkJoin({
        answersAndQuestions: this._pollService.getCompletedBallot(
          this.poll.ballotId
        ),
        typeOfQuestions: this._pollService.getPollQuestions(this.poll.ballotId),
      })
        .pipe(
          catchError((error: HttpErrorResponse) => {
            return of(undefined);
          }),
          filter((res) => Boolean(res)),
          takeUntil(this._destroy$)
        )
        .subscribe((result) => {
          if (result) {
            this._answerWithQuestion = result.answersAndQuestions;
            this._answerWithQuestion.forEach((answerAndQuestion) => {
              this.pollWithQuestions = result.typeOfQuestions;
              if (this.pollWithQuestions?.questions[0].type == 'SCALE') {
                this._answers.push(
                  answerAndQuestion.question + ' ' + answerAndQuestion.answer
                );
                this._answers.sort();
              } else {
                this._answers.push(answerAndQuestion.answer);
              }
            });

            if (
              result.typeOfQuestions.questions[0].type == 'SCALE' ||
              result.typeOfQuestions.questions[0].type == 'ABCSingle' ||
              result.typeOfQuestions.questions[0].type == 'ABCMultiple'
            ) {
              this.createChartSingleMulti();
            } else {
              this.pollWithQuestions = result.typeOfQuestions;

              this._answerWithQuestion = result.answersAndQuestions;

              this.unique = [
                ...new Set(this._answerWithQuestion.map((el) => el.question)),
              ];

              this.unique.map((question) => {
                this.openQuestionWithAnswersConst = {
                  questionText: question,
                  answersArray: [],
                };
                this.openQuestionWithAnswers.push(
                  this.openQuestionWithAnswersConst!
                );
              });

              this.openQuestionWithAnswers.forEach(
                (openQuestionWithAnswers) => {
                  this._answerWithQuestion.map((answerWithQuestion) => {
                    if (
                      openQuestionWithAnswers.questionText ==
                      answerWithQuestion.question
                    ) {
                      openQuestionWithAnswers.answersArray.push(
                        answerWithQuestion.answer
                      );
                    }
                  });
                }
              );

              this.isOpenBallot = true;
            }
          }
        });
    }
  }

  createChartSingleMulti() {
    const possiblebarColors = [
      'red',
      'green',
      'blue',
      'orange',
      'brown',
      'yellow',
    ];
    const barColors: String[] = [];

    let unique = [...new Set(this._answers)];

    let duplicates = unique.map((value) => [
      value,
      this._answers.filter((str) => str === value).length,
    ]);

    let iterator = 0;
    duplicates.forEach((element) => {
      if (iterator < possiblebarColors.length) {
        barColors.push(possiblebarColors[iterator]);
        iterator = iterator + 1;
      } else {
        iterator = 0;
      }
    });

    let chartStatus = Chart.getChart(this.poll!.ballotId.toString()); // <canvas> id
    if (chartStatus != undefined) {
      chartStatus.destroy();
    }

    const ctx = document.getElementById(
      this.poll!.ballotId.toString()
    )! as ChartItem;
    this.chart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: duplicates.map((el) => el[0]),
        datasets: [
          {
            label: this.poll?.ballotName,
            data: duplicates.map((el) => el[1]),
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }
}
