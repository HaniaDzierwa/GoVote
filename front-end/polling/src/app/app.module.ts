import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './features/auth/auth.module';
import { NavBarComponent } from './componenets/nav-bar/nav-bar.component';
import { SidenavModule } from './features/side-nav/side-nav.module';
import { FormsModule } from '@angular/forms';
import { MaterialDesignModule } from './mat-design/material-design.module';
import { CommonModule } from '@angular/common';
import { PollJoinModule } from './features/poll/poll-join/poll-join.module';
import { PollHistoryModule } from './features/poll/poll-history/poll-history.module';
import { RouterModule } from '@angular/router';
import { UserProfileModule } from './features/user-profile/user-profile.module';
import { CreatePollModule } from './features/poll/create-poll/create-poll-module';
import { NotFoundPageModule } from './features/not-found-page/not-found-page.module';
import { DialogComponent } from './componenets/dialog/dialog.component';
import { HttpClientModule } from '@angular/common/http';
import { PollViewModule } from './features/poll/poll-view/poll-view.module';
import { FilterPipe } from './utils/filter-pipe';
import { ForgetPasswordComponent } from './password/forget-password/forget-password.component';
import { RecoverPasswordComponent } from './password/recover-password/recover-password.component';

@NgModule({
  declarations: [AppComponent, NavBarComponent, DialogComponent, ForgetPasswordComponent, RecoverPasswordComponent],
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
    CreatePollModule,
    NotFoundPageModule,
    HttpClientModule,
    PollViewModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  exports: [],
})
export class AppModule {}
