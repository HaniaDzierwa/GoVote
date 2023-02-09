import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { SideNavService } from '../../services/side-nav.service';
import { AuthorizeService } from '../../services/AuthorizeService.service';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.scss'],
})
export class SidenavComponent {
  @ViewChild('sideNav') public sideNav!: any;

  constructor(
    private readonly _router: Router,
    private sideNavSerivice: SideNavService,
    private readonly _authService: AuthorizeService
  ) {
    this.sideNavSerivice.sideMenuShowedStatus$.subscribe((value) =>
      this.handleSideManu(value)
    );
  }

  isLogged(): boolean {
    return this._authService.isLogged();
  }

  handleSideManu(state: boolean): void {
    state ? this.sideNav.open() : this.sideNav.close();
  }
}
