import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidenavComponent } from './sidenav.component';
import { SideNavMaterialsModule } from './material';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [CommonModule, SideNavMaterialsModule, RouterModule],
  declarations: [SidenavComponent],
  exports: [SidenavComponent],
})
export class SidenavModule {}
