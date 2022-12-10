import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../mat-design/material-design.module";
import {CreatePollComponent} from "./create-poll.component";
import {RouterModule} from "@angular/router";

@NgModule({
  imports: [CommonModule, MaterialDesignModule, RouterModule],
  declarations: [CreatePollComponent],
  exports: [CreatePollComponent],
})

export class CreatePollModule {}
