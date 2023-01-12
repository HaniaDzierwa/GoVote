import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {CreatePollRequest} from "../model/create-poll-request";
import {GeneralParamsService} from "./general-params.service";
import {map, Observable,} from "rxjs";
import {PollModel} from "../model/poll-model";
import {AddQuestionDto} from "../model/add-question-dto";
import {CreateQuestionWithAnswersRequest} from "../model/create-question-with-answers-request";
import {QuestionResponse} from "../model/question-response";

@Injectable({
  providedIn: 'root'
})
export class PollService {
  _createdPoll!: PollModel | null;
  _createdQuestion!: AddQuestionDto | null;
  _header: HttpHeaders;
  _createdQuestions!: Set<QuestionResponse> | null;
  _url: String;

  constructor(private _httpService: HttpClient, private generalParamsService: GeneralParamsService,) {

    this.generalParamsService = new GeneralParamsService();
    this._header = new HttpHeaders();
    this._header.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    this._header.append('Access-Control-Allow-Origin', '*');
    this._header.append('Access-Control-Allow-Headers', 'X-Requested-With,content-type')
    this._url = GeneralParamsService.apiAddress();
  }

  createPoll(createPollRequest: CreatePollRequest): Observable<any> {

    const urlCreatePoll = "/v1/ballot/create";
    return this._httpService.post<PollModel>(this._url + urlCreatePoll,
      createPollRequest, {headers: this._header, observe: "response"}
    )
      .pipe(
        map(response => {
          this._createdPoll = response.body;
          return response.body !== null;
        })
      )
  }

  createQuestionWithAnswers(createQuestionWithAnswersRequest: CreateQuestionWithAnswersRequest): Observable<boolean> {

    const urlCreateQuestion = "/v1/ballotQuestion/create";
    return this._httpService.post<AddQuestionDto>(this._url + urlCreateQuestion,
      createQuestionWithAnswersRequest, {headers: this._header, observe: "response"}
    )
      .pipe(
        map(response => {
          this._createdQuestion = response.body;
          return response.body !== null;
        })
      )
  }

  getAllQuestionWithAnswersForPoll(pollId: number): Observable<boolean> {
    const urlCreatedQuestions = "/v1/ballotQuestion/getQuestions";
    return this._httpService.get <Set<QuestionResponse>>(this._url + urlCreatedQuestions
      , {headers: this._header, observe: "response", params: new HttpParams().set('ballotId', pollId)}
    )
      .pipe(
        map(response => {
          this._createdQuestions = response.body;
          return response.body !== null;
        })
      )
  }

  deleteQuestion(questionId: number) {
    const urlCreatedQuestions = "/v1/ballotQuestion/delete";
    return this._httpService.delete(this._url + urlCreatedQuestions,
      {headers: this._header, params: new HttpParams().set('questionId', questionId)}
    )
  }
}
