import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { PollComponent } from './poll/poll.component';
import { CreatingPollComponent } from './creating-poll/creating-poll.component';
import { PollHistoryComponent } from './poll-history/poll-history.component';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';
import { RegisterComponent } from './auth/register/register.component';

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
