import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {AddQuestionComponent} from '../../../componenets/add-question/add-question.component';
import {PollType} from '../../../model/poll-type';
import {ActivatedRoute} from "@angular/router";
import {PollService} from "../../../services/poll.service";
import {QuestionResponse} from "../../../model/question-response";
import {QuestionViewComponent} from "../../../componenets/question-view/question-view.component";

@Component({
  selector: 'app-poll-join-view',
  templateUrl: './poll-view.component.html',
  styleUrls: ['./poll-view.component.scss']
})
export class PollViewComponent implements OnInit {

  _pollType!: PollType;
  _pollDesc: string | undefined;
  _pollName: string | undefined;
  orderNumber: number = 0;
  _createdQuestions!: Set<QuestionResponse> | null
  @ViewChild(QuestionViewComponent) questionView!: QuestionViewComponent;


  constructor(private _matDialog: MatDialog, private _route: ActivatedRoute, private _pollService: PollService) {
  }

  ngOnInit(): void {
    this._pollName = this._pollService._createdPoll?.ballotName;
    this._pollDesc = this._pollService._createdPoll?.ballotDescription;
  }

  onChange(value: any) {
    this._pollType = value;
    this.getAllQuestions();
  }

  openPopUpAdd() {
    this._matDialog.open(AddQuestionComponent, {
      width: '1000px',
      maxHeight: '500px',
      autoFocus: false,
      data: {
        pollType: this._pollType,
        orderNumber: this.orderNumber
      }
    }).afterClosed().subscribe(result => {
      if (result != undefined)
        this.orderNumber = result.data;
      this.getAllQuestions();
    });
  }

  getAllQuestions() {
    if (this._pollService._createdPoll === null) {
      return;
    } else {
      this._pollService
        .getAllQuestionWithAnswersForPoll(this._pollService._createdPoll.ballotId).subscribe(() => {
        this._createdQuestions = this._pollService._createdQuestions
      });
    }
  }


  deleteQuestion(questionId: number) {
    if (questionId === null) {
      return;
    }
    this._pollService.deleteQuestion(questionId).subscribe(() => this.getAllQuestions());

  }
}
