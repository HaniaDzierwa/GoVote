import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../model/login';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private readonly _http: HttpClient) {}

  loginAttempt(login: Login) {
    const loginUrl = '/api/v1/public/login';
    return this._http.post<any>(loginUrl, login);
  }
}
