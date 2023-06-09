import {Injectable} from '@angular/core';
import {Subject} from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class SideNavService {

  sideMenuShowedStatus$ = new Subject<boolean>();

  constructor() {
  }

  showSideMenu(): void {
    this.sideMenuShowedStatus$.next(true);
  }
}
