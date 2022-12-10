import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {MaterialDesignModule} from "../../mat-design/material-design.module";
import {UserProfileComponent} from "./user-profile.component";

@NgModule({
  imports: [CommonModule, MaterialDesignModule],
  declarations: [UserProfileComponent],
  exports: [UserProfileComponent],
})

export class UserProfileModule {}
