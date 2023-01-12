import {Injectable, OnDestroy} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthorizeService implements OnDestroy {
  username: String = 'user';
  password: String = '123';

  constructor(private readonly _router: Router) {
  }

  ngOnDestroy(): void {
  }

  /**
   * set status as logged in without providing a token
   */
  private setLocalStorageLoginInfo(): void {
    localStorage.setItem(environment.loginKey, 'true');
  }

  logIn(token: string): void {
    this.setLocalStorageLoginInfo();
  }

  logOut(): void {
    localStorage.removeItem('login'); // temporarily until when get new response about  logged user data from server
    localStorage.setItem(environment.loginKey, 'false');
  }

  isLogged(): boolean {
    return localStorage.getItem(environment.loginKey) === 'true';
  }

  logUser(login: string, password: string): boolean {
    if (login == this.username && password == this.password) {
      localStorage.setItem('logged', 'true');
      this._router.navigateByUrl('user-profile');
      return true;
    } else {
      return false;
    }
  }
}
