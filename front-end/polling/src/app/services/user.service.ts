import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { UserProfile } from '../model/UserProfile';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private readonly _http: HttpClient) {}

  getUser(): Observable<UserProfile> {
    const urlGetUserProfile = 'api/v1/user/getUser';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };

    return this._http.get<UserProfile>(urlGetUserProfile, {
      headers: headers,
    });
  }
}
