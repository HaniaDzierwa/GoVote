import { Component, OnInit } from '@angular/core';
import { PollType } from '../../../model/poll-type';

@Component({
  selector: 'app-create-poll-join',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.scss'],
})
export class CreatePollComponent implements OnInit {

  pollType!: PollType;

  constructor() {
  }
  ngOnInit() {}

}
