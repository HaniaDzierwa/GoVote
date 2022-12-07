import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UserProfileComponent } from './auth/components/profiles/user-profile/user-profile.component';
import { CreatingPollComponent } from './auth/components/ballot/creating-poll/creating-poll.component';
import { PollComponent } from './auth/components/ballot/poll/poll.component';
import { PollHistoryComponent } from './auth/components/ballot/poll-history/poll-history.component';
import { NotFoundPageComponent } from './auth/components/general/not-found-page/not-found-page.component';
import { SidenavModule } from './auth/components/general/sidenav/sidenav.module';
import { NavBarComponent } from './auth/components/general/nav-bar/nav-bar.component';
import { HomeComponent } from './auth/components/general/home/home.component';
import { SideNavMaterialsModule } from './auth/components/general/sidenav/material';
import { AuthModule } from './auth/modules/auth/auth.module';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    CreatingPollComponent,
    PollComponent,
    PollHistoryComponent,
    NotFoundPageComponent,
    NavBarComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AuthModule,
    SidenavModule,
    MatProgressBarModule,
    MatToolbarModule,
    SideNavMaterialsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
