import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login.component';
import { UserProfileComponent } from './features/user-profile/user-profile.component';
import { PollJoinComponent } from './features/poll/poll-join/poll-join.component';
import { CreatePollComponent } from './features/poll/create-poll/create-poll.component';
import { PollHistoryComponent } from './features/poll/poll-history/poll-history.component';
import { NotFoundPageComponent } from './features/not-found-page/not-found-page.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { PollViewComponent } from './features/poll/poll-view/poll-view.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'poll', component: PollJoinComponent },//poll join?
  { path: 'create-poll', component: CreatePollComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'poll-history', component: PollHistoryComponent },
  { path: 'poll-view', component: PollViewComponent},
  { path: '**', component: NotFoundPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
