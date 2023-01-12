import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../../mat-design/material-design.module";
import {CreatePollComponent} from "./create-poll.component";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [CommonModule, MaterialDesignModule, RouterModule, FormsModule,ReactiveFormsModule ],
  declarations: [CreatePollComponent],
  exports: [CreatePollComponent],
})

export class CreatePollModule {

}
