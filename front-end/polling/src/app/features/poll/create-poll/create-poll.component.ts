import { Component, OnInit } from '@angular/core';
import { PollService } from '../../../services/poll.service';
import { CreatePollRequest } from '../../../model/create-poll-request';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { catchError, of, filter, takeUntil, Subject } from 'rxjs';
import { UserProfile } from 'src/app/model/UserProfile';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-poll-join',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.scss'],
})
export class CreatePollComponent implements OnInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  public isReady: boolean = false;
  public user?: UserProfile;

  pollForm = this._formBuilder.group({
    pollName: [null, Validators.required],
    pollDesc: [null, Validators.required],
  });
  _createPollRequest!: CreatePollRequest;

  constructor(
    private _pollService: PollService,
    private _router: Router,
    private readonly _formBuilder: FormBuilder,
    private readonly _userService: UserService
  ) {}

  onCreatePoll(poll: any) {
    this._createPollRequest = {
      ballotName: this.pollForm.get('pollName')!.value,
      ballotDescription: this.pollForm.get('pollDesc')!.value,
      // todo ownerId
      ownerId: this.user!.id,
      published: true,
    };
    this._pollService
      .createPoll(this._createPollRequest)
      .subscribe((respondBody) => {
        if (respondBody) {
          console.log(respondBody);
          //this._pollService.publishPoll().subscribe((res) => {console.log(res)});
          this.onClickRedirect();
        }
      });
  }

  onClickRedirect() {
    this._router.navigate(['/poll-view']).then(
      (nav) => {
        console.log(nav); // true if navigation is successful
      },
      (err) => {
        console.log(err); // when there's an error
      }
    );
  }

  ngOnInit() {
    this._userService
      .getUser()
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return of(undefined);
        }),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )
      .subscribe((result) => {
        console.log(result);
        this.user = result;
        this.isReady = true;
      });
  }
}
