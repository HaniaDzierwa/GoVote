import {Component, Input} from '@angular/core';
import {numSequence} from "../../../utils/add-question.utils";

@Component({
  selector: 'app-scale-question',
  templateUrl: './scale-question.component.html',
  styleUrls: ['./scale-question.component.scss']
})
export class ScaleQuestionComponent {

  @Input() numberOfAnswers: number = 2;
  answers: Array<string> = new Array<string>()

  constructor() {
  }

  numSequence(numberOfQuestions: any) {
    return numSequence(numberOfQuestions);
  }
}
