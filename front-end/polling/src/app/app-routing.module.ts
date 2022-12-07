import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { CreatingPollComponent } from './components/ballot/creating-poll/creating-poll.component';
import { PollHistoryComponent } from './components/ballot/poll-history/poll-history.component';
import { PollComponent } from './components/ballot/poll/poll.component';
import { NotFoundPageComponent } from './components/general/not-found-page/not-found-page.component';
import { UserProfileComponent } from './components/profiles/user-profile/user-profile.component';


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
