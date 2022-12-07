import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/components/auth/login/login.component';
import { RegisterComponent } from './auth/components/auth/register/register.component';
import { CreatingPollComponent } from './auth/components/ballot/creating-poll/creating-poll.component';
import { PollHistoryComponent } from './auth/components/ballot/poll-history/poll-history.component';
import { PollComponent } from './auth/components/ballot/poll/poll.component';
import { NotFoundPageComponent } from './auth/components/general/not-found-page/not-found-page.component';
import { UserProfileComponent } from './auth/components/profiles/user-profile/user-profile.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'user-profile',
    component: UserProfileComponent,
  },
  { path: 'poll', component: PollComponent },
  {
    path: 'create-poll',
    component: CreatingPollComponent,
  },
  { path: 'register', component: RegisterComponent },
  {
    path: 'poll-history',
    component: PollHistoryComponent,
  },
  { path: '**', component: NotFoundPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
