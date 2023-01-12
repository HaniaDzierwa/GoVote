import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from '@angular/forms';
import {QuestionViewComponent} from "./question-view.component";
import {MaterialDesignModule} from "../../mat-design/material-design.module";

@NgModule({
  imports: [CommonModule, RouterModule, FormsModule, ReactiveFormsModule, MaterialDesignModule],
  declarations: [QuestionViewComponent],
  exports: [QuestionViewComponent],
})

export class QuestionViewModule {

}
