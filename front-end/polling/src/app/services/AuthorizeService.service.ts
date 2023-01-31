import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { catchError, filter, Observable, of, Subject, takeUntil } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Login } from '../model/login';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root',
})
export class AuthorizeService implements OnDestroy {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private readonly _router: Router,
    private readonly _loginService: LoginService,
    private readonly _matSnackBar: MatSnackBar
  ) {}

  ngOnDestroy(): void {}

  /**
   * set status as logged in without providing a token
   */
  private setLocalStorageLoginInfo(tokken: string): void {
    localStorage.setItem(environment.loginKey, 'true');
    localStorage.setItem(environment.tokken, tokken);
  }

  logIn(tokken: any): void {
    this.setLocalStorageLoginInfo(tokken.accessToken);
  }

  logOut(): void {
    localStorage.removeItem('login'); // temporarily until when get new response about  logged user data from server
    localStorage.setItem(environment.loginKey, 'false');
    this._router.navigateByUrl('login');
  }

  isLogged(): boolean {
    return localStorage.getItem(environment.loginKey) === 'true';
  }

  logUser(login: string, password: string): void {
    const loginTry: Login = {
      userName: login,
      password: password,
    };
    this._loginService
      .loginAttempt(loginTry)
      .pipe(
        catchError((error: HttpErrorResponse) => this.handleErrorLogin()),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )
      .subscribe((res) => {
        this.logIn(res);
        this._router.navigateByUrl('poll-history');
        this._matSnackBar.open('logowanie się powiodło', 'Zamknij', {
          duration: 5000,
          verticalPosition: 'bottom',
        });
      });
  }

  handleErrorLogin(): Observable<undefined> {
    this.logOut();
    this._matSnackBar.open(
      'Login lub hasło niepoprawne. Proszę spróbować jeszcze raz',
      'Zamknij',
      {
        duration: 5000,
        verticalPosition: 'bottom',
      }
    );
    return of(undefined);
  }
}
