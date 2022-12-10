import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './features/auth/auth.module';
import { NavBarComponent } from './componenets/nav-bar/nav-bar.component';
import { SidenavModule } from './features/side-nav/side-nav.module';
import { FormsModule } from '@angular/forms';
import {MaterialDesignModule} from "./mat-design/material-design.module";
import {CommonModule} from "@angular/common";
import {PollJoinModule} from "./features/poll-join/poll-join.module";
import {PollHistoryModule} from "./features/poll-history/poll-history.module";
import {RouterModule} from "@angular/router";
import {UserProfileModule} from "./features/user-profile/user-profile.module";
import {PollViewModule} from "./features/poll-view/poll-view.module";
import {CreatePollModule} from "./features/create-poll/create-poll-module";
import {NotFoundPageModule} from "./features/not-found-page/not-found-page.module";
import { DialogComponent } from './componenets/dialog/dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    DialogComponent,


  ],
    imports: [
      MaterialDesignModule,
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      AuthModule,
      FormsModule,
      CommonModule,
      SidenavModule,
      PollJoinModule,
      PollHistoryModule,
      RouterModule,
      UserProfileModule,
      PollViewModule,
      CreatePollModule,
      NotFoundPageModule,
    ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
