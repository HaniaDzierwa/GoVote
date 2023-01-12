import {Component, Inject, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PollType} from '../../model/poll-type';
import {SingleChoiceQuestionComponent} from "./single-choice-question/single-choice-question.component";
import {MultipleChoiceQuestionComponent} from "./multiple-choice-question/multiple-choice-question.component";
import {OpenQuestionComponent} from "./open-question/open-question.component";
import {ScaleQuestionComponent} from "./scale-question/scale-question.component";
import {PollService} from "../../services/poll.service";
import {CreateQuestionWithAnswersRequest} from "../../model/create-question-with-answers-request";

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss']
})
export class AddQuestionComponent {
  pollType!: PollType;
  isAddNextToggle: boolean = false;
  numberOfAnswers: number = 2;
  question: string = ""
  orderNumber!: number;
  _createQuestionWithAnswersRequest!: CreateQuestionWithAnswersRequest;

  @ViewChild(SingleChoiceQuestionComponent) childSingle?: SingleChoiceQuestionComponent;
  @ViewChild(MultipleChoiceQuestionComponent) childMultiple?: MultipleChoiceQuestionComponent;
  @ViewChild(OpenQuestionComponent) childOpen?: OpenQuestionComponent;
  @ViewChild(ScaleQuestionComponent) childScale?: ScaleQuestionComponent;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { pollType: PollType, orderNumber: number },
    private _dialogRef: MatDialogRef<AddQuestionComponent>,
    private _pollService: PollService
  ) {
    this.pollType = data.pollType;
    this.orderNumber = data.orderNumber;
  }

  setQuestion(question: string) {
    this.question = question;
  }

  addQuestion() {

    let answers: string[] = new Array<string>();
    if (this.pollType === PollType.SCALE) {
      answers[0] = this.childScale ? this.childScale.numberOfAnswers.toString() : "";
    } else if (this.pollType === PollType.OPEN) {
      answers = this.childOpen ? this.childOpen.answers : [];
    } else if (this.pollType === PollType.ABCSingle) {
      answers = this.childSingle ? this.childSingle.answers : [];
    } else if (this.pollType === PollType.ABCMultiple) {
      answers = this.childMultiple ? this.childMultiple.answers : [];
    }

    this._createQuestionWithAnswersRequest = {
      ballotId: this._pollService._createdPoll!.ballotId,
      questionType: this.pollType,
      orderNumber: this.orderNumber,
      questionContent: this.question,
      answers: answers,
    }

    this.orderNumber++
    this._pollService
      .createQuestionWithAnswers(this._createQuestionWithAnswersRequest).subscribe(respondBody => {
      if (respondBody) {
        if (!this.isAddNextToggle) {
          this._dialogRef.close({data: this.orderNumber});
        }
      }
    });
  }

  createNextToggle() {
    this.isAddNextToggle = !this.isAddNextToggle;
  }

  addOption() {
    if (this.numberOfAnswers < 10) {
      this.numberOfAnswers++;
    }
  }

  deleteOption() {
    if (this.numberOfAnswers > 2) {
      this.numberOfAnswers--;
    }
  }
}
