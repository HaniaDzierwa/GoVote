import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../../mat-design/material-design.module";
import {PollJoinComponent} from "./poll-join.component";

@NgModule({
  imports: [CommonModule, MaterialDesignModule],
  declarations: [PollJoinComponent],
  exports: [PollJoinComponent],
})

export class PollJoinModule {}


