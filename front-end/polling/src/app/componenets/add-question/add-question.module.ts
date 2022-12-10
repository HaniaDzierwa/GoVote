import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../mat-design/material-design.module";
import {AddQuestionComponent} from "./add-question.component";
import {MultipleChoiceQuestionComponent} from "./multiple-choice-question/multiple-choice-question.component";
import {OpenQuestionComponent} from "./open-question/open-question.component";
import {ScaleQuestionComponent} from "./scale-question/scale-question.component";
import {SingleChoiceQuestionComponent} from "./single-choice-question/single-choice-question.component";



@NgModule({
  imports: [CommonModule,MaterialDesignModule
  ],
  declarations: [AddQuestionComponent,
    MultipleChoiceQuestionComponent,
    OpenQuestionComponent,
    ScaleQuestionComponent,
    SingleChoiceQuestionComponent
  ],
  exports: [AddQuestionComponent,
    MultipleChoiceQuestionComponent,
    OpenQuestionComponent,
    ScaleQuestionComponent,
    SingleChoiceQuestionComponent
  ],
})

export class AddQuestionModule {}
