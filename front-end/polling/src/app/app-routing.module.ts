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
import { SecurityGuardian } from './features/auth/security.guard';
import { NavBarComponent } from './componenets/nav-bar/nav-bar.component';
import { ForgetPasswordComponent } from './password/forget-password/forget-password.component';
import { RecoverPasswordComponent } from './password/recover-password/recover-password.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'user-profile',
    component: UserProfileComponent,
    canActivate: [SecurityGuardian],
  },
  {
    path: 'poll',
    component: PollJoinComponent,
    canActivate: [SecurityGuardian],
  }, //poll join?
  {
    path: 'create-poll',
    component: CreatePollComponent,
    canActivate: [SecurityGuardian],
  },
  { path: 'register', component: RegisterComponent },
  {
    path: 'poll-history',
    component: PollHistoryComponent,
    canActivate: [SecurityGuardian],
  },
  {
    path: 'poll-view',
    component: PollViewComponent,
    canActivate: [SecurityGuardian],
  },
  { path: '**', component: NotFoundPageComponent },
  {
    path: 'password', children: [
      {
        path: 'forget-password', component: ForgetPasswordComponent,
      },
      {
        path: 'recover-password', component: RecoverPasswordComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
