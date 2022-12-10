import {Component, Input } from '@angular/core';
import {numSequence} from "../add-question.utils";
@Component({
  selector: 'app-multiple-choice-question',
  templateUrl: './multiple-choice-question.component.html',
  styleUrls: ['./multiple-choice-question.component.scss']
})
export class MultipleChoiceQuestionComponent {

  @Input() numberOfAnswers: number = 2;
  answers: Array<string> = new Array<string>()

  constructor() {
  }

  inputChange(answer: string, index: number) {
    this.answers[index] = answer
  }
  numSequence(numberOfQuestions: any) {
    return numSequence(numberOfQuestions);
  }
}
