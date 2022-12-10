import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../mat-design/material-design.module";
import {NotFoundPageComponent} from "./not-found-page.component";

@NgModule({
  imports: [CommonModule, MaterialDesignModule],
  declarations: [NotFoundPageComponent],
  exports: [NotFoundPageComponent],
})

export class NotFoundPageModule {}
