import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../../mat-design/material-design.module";
import {PollViewComponent} from "./poll-view.component";
import {AddQuestionModule} from "../../../componenets/add-question/add-question.module";
import {RouterModule} from "@angular/router";
import {QuestionViewModule} from "../../../componenets/question-view/question-view.module";


@NgModule({
  imports: [CommonModule, MaterialDesignModule, AddQuestionModule, RouterModule, QuestionViewModule],
  declarations: [PollViewComponent],
  exports: [PollViewComponent],
})

export class PollViewModule {}
