import { Component } from '@angular/core';

@Component({
  selector: 'app-open-question',
  templateUrl: './open-question.component.html',
  styleUrls: ['./open-question.component.scss']
})
export class OpenQuestionComponent {
  answers: Array<string> = new Array<string>()
  constructor() { }

  inputChange(answer: string) {
    console.log(answer)
    this.answers.push(answer);
  }
}
