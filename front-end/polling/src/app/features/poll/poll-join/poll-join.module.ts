import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MaterialDesignModule,
  materials,
} from '../../../mat-design/material-design.module';
import { PollJoinComponent } from './poll-join.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FilterPipe } from 'src/app/utils/filter-pipe';
import { QuestionDialogComponent } from '../../questionDialog/questionDialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { DemoNumberPipe } from '../../../utils/DemoNumber.pipe';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

const Materials = [
  MatDialogModule,
  MatCheckboxModule,
  MatInputModule,
  MatFormFieldModule,
  FormsModule,
];

@NgModule({
  imports: [CommonModule, MaterialDesignModule, FormsModule, Materials],
  declarations: [
    PollJoinComponent,
    FilterPipe,
    QuestionDialogComponent,
    DemoNumberPipe,
  ],
  exports: [PollJoinComponent],
})
export class PollJoinModule {}
