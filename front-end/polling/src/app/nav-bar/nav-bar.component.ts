import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthorizeService } from '../auth/AuthorizeService.service';
import { SideMenuServiceService } from '../side-menu-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {

  constructor(
    private readonly _authService: AuthorizeService,
    private readonly _router: Router,
    private readonly _sideMenuService: SideMenuServiceService
  ) {}

  onLogoutToggle(): void {
    this._authService.logOut();
  }

  isLogged() :boolean {
    return this._authService.isLogged();
  }

  showSideMenu() {
  this._sideMenuService.showSideMenu();
  }
}
