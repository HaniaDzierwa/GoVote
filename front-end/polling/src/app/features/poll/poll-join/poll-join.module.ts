import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MaterialDesignModule,
  materials,
} from '../../../mat-design/material-design.module';
import { PollJoinComponent } from './poll-join.component';
import { FormsModule } from '@angular/forms';
import { FilterPipe } from 'src/app/utils/filter-pipe';
import { QuestionDialogComponent } from '../../questionDialog/questionDialog.component';
import { MatDialogModule } from '@angular/material/dialog';

const Materials = [MatDialogModule];

@NgModule({
  imports: [CommonModule, MaterialDesignModule, FormsModule, Materials],
  declarations: [PollJoinComponent, FilterPipe, QuestionDialogComponent],
  exports: [PollJoinComponent],
})
export class PollJoinModule {}
