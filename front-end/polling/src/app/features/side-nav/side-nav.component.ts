import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SideNavService } from '../../services/side-nav.service';


@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss'],
})
export class SidenavComponent {

  @ViewChild('sideNav') public sideNav! : any;

  constructor(
    private readonly _router: Router,
    private sideNavSerivice: SideNavService
  ) {
      this.sideNavSerivice.sideMenuShowedStatus$.subscribe(value => this.handleSideManu(value));

  }

  handleSideManu(state: boolean): void {
    state
    ? this.sideNav.open()
    : this.sideNav.close();
  }
}
