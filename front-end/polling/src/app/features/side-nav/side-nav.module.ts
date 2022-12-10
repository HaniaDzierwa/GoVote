import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SidenavComponent } from './side-nav.component';
import { MaterialDesignModule } from "../../mat-design/material-design.module";

@NgModule({
  imports: [CommonModule,MaterialDesignModule, RouterModule],
  declarations: [SidenavComponent],
  exports: [SidenavComponent],
})

export class SidenavModule {}
