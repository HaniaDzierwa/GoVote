import {Component, OnInit, Input} from '@angular/core';
import {numSequence} from "../add-question.utils";


@Component({
  selector: 'app-single-choice-question',
  templateUrl: './single-choice-question.component.html',
  styleUrls: ['./single-choice-question.component.scss']
})
export class SingleChoiceQuestionComponent implements OnInit {

  @Input() numberOfAnswers: number = 2;
  answers: Array<string> = new Array<string>()

  constructor() {
  }

  ngOnInit(): void {
  }

  inputChange(answer: string, index: number) {
    this.answers[index] = answer
  }

  numSequence(numberOfQuestions: any) {
    return numSequence(numberOfQuestions);
  }
}
