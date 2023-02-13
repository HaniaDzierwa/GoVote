import { Component, OnInit } from '@angular/core';
import { PollService } from '../../../services/poll.service';
import { catchError, filter, Observable, of, takeUntil, Subject } from 'rxjs';
import { PollModel } from '../../../model/poll-model';
import { HttpErrorResponse } from '@angular/common/http';
import { FilterPipe } from 'src/app/utils/filter-pipe';
import { MatDialog } from '@angular/material/dialog';
import { QuestionDialogComponent } from '../../questionDialog/questionDialog.component';
import { PollWithQuestions } from 'src/app/model/PollWithQuestions';

@Component({
  selector: 'app-poll-join',
  templateUrl: './poll-join.component.html',
  styleUrls: ['./poll-join.component.scss'],
})
export class PollJoinComponent implements OnInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();
  private _polls: PollModel[] = [];

  searchValue: string = '';

  public get polls(): PollModel[] {
    return this._polls;
  }
  public set polls(value: PollModel[]) {
    this._polls = value;
  }

  constructor(
    private readonly _pollService: PollService,
    public dialog: MatDialog
  ) {}

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
      });
  }

  onJoinPoll(poll: PollModel): void {
    this._pollService
      .getPollQuestions(poll.ballotId)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          return of(undefined);
        }),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )
      .subscribe((result) => {
        result!.ballotId = poll.ballotId;

        const dialogRef = this.dialog.open(QuestionDialogComponent, {
          data: result as PollWithQuestions,
        });

        dialogRef.afterClosed().subscribe((result) => {});
      });
  }
}

//todo show only published, not filled  poll with name in table -> option for each : join/fill
