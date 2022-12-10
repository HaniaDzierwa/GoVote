import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../mat-design/material-design.module";
import {PollHistoryComponent} from "./poll-history.component";

@NgModule({
  imports: [CommonModule, MaterialDesignModule],
  declarations: [PollHistoryComponent],
  exports: [PollHistoryComponent],
})

export class PollHistoryModule {}
