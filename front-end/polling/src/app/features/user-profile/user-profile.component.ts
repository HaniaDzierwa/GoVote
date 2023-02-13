import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { catchError, of, filter, takeUntil, Subject } from 'rxjs';
import { UserService } from '../../services/user.service';
import { UserProfile } from '../../model/UserProfile';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent implements OnInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  public user?: UserProfile;
  public isReady: boolean = false;

  constructor(private readonly _userService: UserService) {}

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
        this.user = result;
        this.isReady = true;
      });
  }
}
