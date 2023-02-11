import { Component, Inject, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PollWithQuestions } from 'src/app/model/PollWithQuestions';
import { Question } from '../../model/Question';
import { Answer } from '../../model/Answer';
import { DemoNumberPipe } from 'src/app/utils/DemoNumber.pipe';
import { AnswerToSend } from '../../model/Answer-to-send';
import { HttpErrorResponse } from '@angular/common/http';
import { Subject, catchError, of, filter, takeUntil } from 'rxjs';
import { PollService } from 'src/app/services/poll.service';
import { CompleteBallot } from '../../model/completeBallot';

@Component({
  selector: 'app-questionDialog',
  templateUrl: './questionDialog.component.html',
  styleUrls: ['./questionDialog.component.scss'],
})
export class QuestionDialogComponent {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  questionForm: FormGroup;
  answersToSend?: AnswerToSend;
  completeballot?: CompleteBallot;

  constructor(
    public dialogRef: MatDialogRef<QuestionDialogComponent>,
    private readonly _pollService: PollService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public pollWithQuestions: PollWithQuestions
  ) {
    this.questionForm = this.fb.group({ questions: this.fb.array([]) });

    pollWithQuestions.questions.forEach((question) => {
      this.questions.push(new FormControl(question));
    });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onAccept(): void {
    console.log(this.pollWithQuestions.ballotId!);
    const arrayAnswer: AnswerToSend[] = [];

    for (let control of this.questions.controls) {
      if (control instanceof FormControl) {
        (control.value.answers as Answer[]).forEach((answer) => {
          if (answer.userAnswer && control.value['type'] == 'ABCSingle') {
            const answerToSend: AnswerToSend = {
              questionId: answer.questionId,
              answer: answer.answerText.toString(),
            };
            arrayAnswer.push(answerToSend);
          } else if (
            control.value['type'] == 'OPEN' &&
            control.value['answers'].length > 0
          ) {
            const answerString = control.value['answers'][0];
            const answerToSend: AnswerToSend = {
              questionId: control.value['questionId'],
              answer: answerString,
            };
            arrayAnswer.push(answerToSend);
          } else if (answer.userAnswer && control.value['type'] == 'SCALE') {
            const answerToSend: AnswerToSend = {
              questionId: answer.questionId,
              answer: answer.userAnswer.toString(),
            };
            arrayAnswer.push(answerToSend);
          } else if (
            answer.userAnswer &&
            control.value['type'] == 'ABCMultiple'
          ) {
            const answerToSend: AnswerToSend = {
              questionId: answer.questionId,
              answer: answer.answerText.toString(),
            };
            arrayAnswer.push(answerToSend);
          }
        });
      }
    }
    const CompleteBallot: CompleteBallot = {
      ballotId: this.pollWithQuestions.ballotId!,
      questionWithAnswer: arrayAnswer,
    };

    this.completeballot = CompleteBallot;

    this._pollService
      .addNewUserAnswerToBallot(this.completeballot!)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return of(undefined);
        }),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )
      .subscribe((result) => {
        console.log(result);
      });
  }

  onCheckboxChangeABCSingle(question: AbstractControl, answer: Answer): void {
    (question.value['answers'] as Answer[]).forEach((res) => {
      if (res.answerId != answer.answerId) {
        res.userAnswer = false;
      }
    });
  }

  get questions() {
    return this.questionForm.controls['questions'] as FormArray;
  }
}
