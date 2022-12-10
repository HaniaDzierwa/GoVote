import {Component, Inject, ViewChild} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PollType } from '../../model/poll-type';
import {SingleChoiceQuestionComponent} from "./single-choice-question/single-choice-question.component";
import {AddQuestionDto} from "../../model/add-question-dto";
import {MultipleChoiceQuestionComponent} from "./multiple-choice-question/multiple-choice-question.component";
import {OpenQuestionComponent} from "./open-question/open-question.component";
import {ScaleQuestionComponent} from "./scale-question/scale-question.component";

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss']
})
export class AddQuestionComponent {
  pollType!: PollType;
  isAddNextToggle:boolean= false;
  numberOfAnswers: number = 2;
  scaleSelectedNumber: number = 0;
  question: string = ""

  @ViewChild(SingleChoiceQuestionComponent) childSingle?:SingleChoiceQuestionComponent;
  @ViewChild(MultipleChoiceQuestionComponent) childMultiple?:MultipleChoiceQuestionComponent;
  @ViewChild(OpenQuestionComponent) childOpen?:OpenQuestionComponent;
  @ViewChild(ScaleQuestionComponent) childScale?:ScaleQuestionComponent;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { pollType: PollType },
    private _dialogrRef: MatDialogRef<AddQuestionComponent>,
  ) {
    this.pollType = data.pollType;
  }
  setQuestion(question: string) {
    this.question = question;
  }
  addQuestion() {
    if(!this.isAddNextToggle)
    this._dialogrRef.close();

    var answers: string[] =[];
    if (this.pollType === PollType.SCALE) {
         answers = this.childScale? this.childScale.answers : [];
    } else if (this.pollType === PollType.OPEN) {
         answers = this.childOpen? this.childOpen.answers : [];
    } else if (this.pollType === PollType.ABCSingle) {
        answers = this.childSingle ? this.childSingle.answers : [];
    } else if (this.pollType === PollType.ABCMultiple) {
        answers = this.childMultiple ? this.childMultiple.answers : [];
    }

    const questionToAdd: AddQuestionDto = {
      pollId: 123,
      pollType: this.pollType,
      numberOfQuestion: this.numberOfAnswers,
      question: this.question,
      answers: answers,
    }
    console.log(questionToAdd);
  }
  createNextToggle() {
    this.isAddNextToggle=!this.isAddNextToggle;
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
