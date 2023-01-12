import {Component, OnInit} from '@angular/core';
import {PollService} from "../../../services/poll.service";
import {CreatePollRequest} from "../../../model/create-poll-request";
import {Router} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-poll-join',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.scss'],
})


export class CreatePollComponent implements OnInit {
  pollForm = this._formBuilder.group({
    pollName: [null, Validators.required],
    pollDesc: [null, Validators.required],
  });
  _createPollRequest!: CreatePollRequest;

  constructor(private _pollService: PollService,
              private _router: Router,
              private readonly _formBuilder: FormBuilder,) {
  }

  onCreatePoll(poll: any) {
    this._createPollRequest = {
      ballotName: this.pollForm.get('pollName')!.value,
      ballotDescription: this.pollForm.get('pollDesc')!.value,
      // todo ownerId
      ownerId: 0
    }
    this._pollService
      .createPoll(this._createPollRequest).subscribe(respondBody => {
      if (respondBody) {
        this.onClickRedirect();
      }
    });
  }

  onClickRedirect() {
    this._router.navigate(["/poll-view"])
      .then(nav => {
        console.log(nav); // true if navigation is successful
      }, err => {
        console.log(err) // when there's an error
      })
  }

  ngOnInit() {
  }

}
