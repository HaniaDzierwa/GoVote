import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ForgetPasswordComponent } from 'src/app/password/forget-password/forget-password.component';
import { RecoverPasswordComponent } from 'src/app/password/recover-password/recover-password.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatSnackBarModule,
  ],
  declarations: [RegisterComponent, LoginComponent, ForgetPasswordComponent, RecoverPasswordComponent],
  exports: [LoginComponent, RegisterComponent, ForgetPasswordComponent, RecoverPasswordComponent],
})
export class AuthModule {}
