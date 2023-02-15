import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Register } from '../model/register';
import { GeneralParamsService } from './general-params.service';
import { QuestionResponse } from '../model/question-response';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  _header: HttpHeaders;
  _url: String;
  constructor(
    private readonly _http: HttpClient,
    private readonly _generalParamsService: GeneralParamsService
  ) {
    this._generalParamsService = new GeneralParamsService();
    this._header = new HttpHeaders();
    this._header.append(
      'Access-Control-Allow-Methods',
      'GET, POST, OPTIONS, PUT, PATCH, DELETE'
    );

    this._header.append(
      'Access-Control-Allow-Headers',
      'X-Requested-With,content-type'
    );
    this._url = GeneralParamsService.apiAddress();
  }

  registerUser(register: Register) {
    const registerUrl = '/api/v1/public/register';
    return this._http.post<Register>(registerUrl, register, {
      headers: this._header,
    }).toPromise();
  }
}
