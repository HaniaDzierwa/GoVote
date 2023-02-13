import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { catchError, of, filter, takeUntil, Subject } from 'rxjs';
import { PollModel } from 'src/app/model/poll-model';
import { PollService } from 'src/app/services/poll.service';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-poll-join-history',
  templateUrl: './poll-history.component.html',
  styleUrls: ['./poll-history.component.scss'],
})
export class PollHistoryComponent implements OnInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  private _polls: PollModel[] = [];
  public chart: any;
  public ready: boolean = false;

  public get polls(): PollModel[] {
    return this._polls;
  }
  public set polls(value: PollModel[]) {
    this._polls = value;
  }
  constructor(private readonly _pollService: PollService) {}

  ngOnInit() {
    this.getAllPolls();
  }

  private getAllPolls(): void {
    this._pollService
      .getAllPolls()
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return of(undefined);
        }),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )
      .subscribe((result) => {
        this._polls.push(...result!);
        this.ready = true;
      });
  }
}

//todo show pollHistory with name in table  -> option for each :  modify(only if not publish), publish, delete
//todo filled poll only to see
