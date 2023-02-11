import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { CreatePollRequest } from '../model/create-poll-request';
import { GeneralParamsService } from './general-params.service';
import { map, Observable } from 'rxjs';
import { PollModel } from '../model/poll-model';
import { AddQuestionDto } from '../model/add-question-dto';
import { CreateQuestionWithAnswersRequest } from '../model/create-question-with-answers-request';
import { QuestionResponse } from '../model/question-response';
import { environment } from '../../environments/environment';
import { PollWithQuestions } from '../model/PollWithQuestions';
import { AnswerToSend } from '../model/Answer-to-send';
import { CompleteBallot } from '../model/completeBallot';

@Injectable({
  providedIn: 'root',
})
export class PollService {
  _createdPoll!: PollModel | null;
  _createdQuestion!: AddQuestionDto | null;
  _header: HttpHeaders;
  _createdQuestions!: Set<QuestionResponse> | null;
  _url: String;

  constructor(
    private _httpService: HttpClient,
    private generalParamsService: GeneralParamsService
  ) {
    this.generalParamsService = new GeneralParamsService();
    this._header = new HttpHeaders();
    this._header.append(
      'Access-Control-Allow-Methods',
      'GET, POST, OPTIONS, PUT, PATCH, DELETE'
    );
    this._header.append('Access-Control-Allow-Origin', '*');

    this._url = GeneralParamsService.apiAddress();
  }

  createPoll(createPollRequest: CreatePollRequest): Observable<any> {
    const urlCreatePoll = 'api/v1/ballot/create';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };
    return this._httpService
      .post<PollModel>(urlCreatePoll, createPollRequest, {
        headers: headers,
        observe: 'response',
      })
      .pipe(
        map((response) => {
          this._createdPoll = response.body;
          return response.body !== null;
        })
      );
  }

  createQuestionWithAnswers(
    createQuestionWithAnswersRequest: CreateQuestionWithAnswersRequest
  ): Observable<boolean> {
    const urlCreateQuestion = 'api/v1/ballotQuestion/create';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };
    return this._httpService
      .post<AddQuestionDto>(
        urlCreateQuestion,
        createQuestionWithAnswersRequest,
        { headers: headers, observe: 'response' }
      )
      .pipe(
        map((response) => {
          this._createdQuestion = response.body;
          return response.body !== null;
        })
      );
  }

  getAllQuestionWithAnswersForPoll(pollId: number): Observable<boolean> {
    const urlCreatedQuestions = '/v1/ballotQuestion/getQuestions';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };
    return this._httpService
      .get<Set<QuestionResponse>>(urlCreatedQuestions, {
        headers: headers,
        observe: 'response',
        params: new HttpParams().set('ballotId', pollId),
      })
      .pipe(
        map((response) => {
          this._createdQuestions = response.body;
          return response.body !== null;
        })
      );
  }

  deleteQuestion(questionId: number) {
    const urlCreatedQuestions = 'api/v1/ballotQuestion/delete';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };
    return this._httpService.delete(urlCreatedQuestions, {
      headers: headers,
      params: new HttpParams().set('questionId', questionId),
    });
  }

  getAllPolls(): Observable<PollModel[]> {
    const urlGetAllPolls = 'api/v1/ballot/Ballots';
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };

    // console.log(this._header.get('Authorization'));

    return this._httpService.get<PollModel[]>(urlGetAllPolls, {
      headers: headers,
    });
  }

  getPollQuestions(pollId: number): Observable<PollWithQuestions> {
    const urlGetPollById = `api/v1/ballot/getBallotWithQuestions/?ballotId=${pollId}`;
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };

    return this._httpService.get<PollWithQuestions>(urlGetPollById, {
      headers: headers,
    });
  }

  publishPoll(pollId: number) {
    const urlGetPollById = `api/v1/ballot/publish/?ballotId=${pollId}`;
    const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };
    return this._httpService.post(urlGetPollById, {
      headers: headers,
    });
  }

  addNewUserAnswerToBallot(answersToSend: CompleteBallot) {
    const urlAddNewUserAnswerToBallot = `api/v1/ballot/completeBallot`;

    const header = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem(environment.tokken)}`,
    };

    console.log(localStorage.getItem(environment.tokken));

    return this._httpService.post<any>(
      urlAddNewUserAnswerToBallot,
      answersToSend,
      {
        headers: header,
        observe: 'response',
      }
    );
  }
}
