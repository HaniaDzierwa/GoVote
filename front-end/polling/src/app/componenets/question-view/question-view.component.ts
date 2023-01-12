import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {QuestionResponse} from "../../model/question-response";
import {PollService} from "../../services/poll.service";


@Component({
  selector: 'app-question-view',
  templateUrl: './question-view.component.html',
  styleUrls: ['./question-view.component.scss']
})
export class QuestionViewComponent implements OnInit {

  @Input() questions!: Set<QuestionResponse> | null;

  @Output() questionWasDeleted = new EventEmitter<number>();

  constructor(private _pollService: PollService) {
  }

  ngOnInit(): void {
  }

  deleteQuestion(questionId: number) {
    this.questionWasDeleted.emit(questionId);

  }
}

//todo button to modify question
