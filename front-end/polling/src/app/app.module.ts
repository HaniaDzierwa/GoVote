import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UserProfileComponent } from './components/profiles/user-profile/user-profile.component';
import { CreatingPollComponent } from './components/ballot/creating-poll/creating-poll.component';
import { PollComponent } from './components/ballot/poll/poll.component';
import { PollHistoryComponent } from './components/ballot/poll-history/poll-history.component';
import { NotFoundPageComponent } from './components/general/not-found-page/not-found-page.component';
import { SidenavModule } from './components/general/sidenav/sidenav.module';
import { NavBarComponent } from './components/general/nav-bar/nav-bar.component';
import { HomeComponent } from './components/general/home/home.component';
import { SideNavMaterialsModule } from './components/general/sidenav/material';
import { HttpClientModule } from '@angular/common/http';
import { AuthModule } from './modules/auth/auth.module';

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
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
