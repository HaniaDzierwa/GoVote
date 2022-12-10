import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddQuestionComponent } from '../../../componenets/add-question/add-question.component';
import { PollType } from '../../../model/poll-type';

@Component({
  selector: 'app-poll-join-view',
  templateUrl: './poll-view.component.html',
  styleUrls: ['./poll-view.component.scss']
})
export class PollViewComponent implements OnInit {

  pollType!: PollType;

  constructor( private matDialog: MatDialog) { }

  ngOnInit(): void {
    console.log("poll view")
  }


  onChange(value: any){
    this.pollType = value;
    console.log(value)
 }

 openPopUpAdd() {
 this.matDialog.open(AddQuestionComponent, {
  width: '1000px',
  maxHeight: '500px',
  autoFocus: false,
  data: {
    pollType: this.pollType

  }
 });

 }
}
