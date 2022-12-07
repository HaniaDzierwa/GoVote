import { Injectable } from '@angular/core';
import { Subject } from 'rxjs'


@Injectable({
  providedIn: 'root'
})
export class SideMenuServiceService {

  sideMenuShowedStatus$ = new Subject<boolean>();

  constructor() {}

  showSideMenu(): void {
    this.sideMenuShowedStatus$.next(true);
  }
}
