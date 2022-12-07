import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SideMenuServiceService } from '../side-menu-service.service';


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss'],
})
export class SidenavComponent {

  @ViewChild('sideNav') public sideNav! : any;

  constructor(
    private readonly _router: Router,
    private sideMenuService: SideMenuServiceService
  ) {
      this.sideMenuService.sideMenuShowedStatus$.subscribe(value => this.handleSideManu(value));
  }

  handleSideManu(state: boolean): void {
    state 
    ? this.sideNav.open() 
    : this.sideNav.close();
  }
}
