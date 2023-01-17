import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialDesignModule } from '../../../mat-design/material-design.module';
import { PollJoinComponent } from './poll-join.component';
import { FormsModule } from '@angular/forms';
import { FilterPipe } from 'src/app/utils/filter-pipe';

@NgModule({
  imports: [CommonModule, MaterialDesignModule, FormsModule],
  declarations: [PollJoinComponent, FilterPipe],
  exports: [PollJoinComponent],
})
export class PollJoinModule {}
