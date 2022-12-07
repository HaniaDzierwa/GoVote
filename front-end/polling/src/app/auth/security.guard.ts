import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  UrlTree,
} from '@angular/router';

import { AuthorizeService } from './AuthorizeService.service';

@Injectable({
  providedIn: 'root',
})
export class SecurityGuardian implements CanActivate {
  constructor(
    private readonly _authService: AuthorizeService,
    private readonly _router: Router
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): UrlTree | boolean {
    if (this._authService.isLogged()) {
      return true;
    } else {
      return this._router.createUrlTree(['login']);
    }
  }
}
