import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PollWithQuestions } from 'src/app/model/PollWithQuestions';

@Component({
  selector: 'app-questionDialog',
  templateUrl: './questionDialog.component.html',
  styleUrls: ['./questionDialog.component.scss'],
})
export class QuestionDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<QuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public pollWithQuestions: PollWithQuestions
  ) {
    console.log(pollWithQuestions);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
