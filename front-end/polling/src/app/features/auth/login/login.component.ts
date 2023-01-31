import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthorizeService } from '../../../services/AuthorizeService.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm = this._formBuilder.group({
    login: [null, Validators.required],
    password: [null, Validators.required],
  });

  constructor(
    private readonly _formBuilder: FormBuilder,
    private readonly _router: Router,
    private readonly _authService: AuthorizeService,
    private readonly _matSnackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    if (this._authService.isLogged()) {
      this._router.navigateByUrl('poll-history');
    }
  }

  loginAttempt(): void {
    return this._authService.logUser(
      this.loginForm.value.login!,
      this.loginForm.value.password!
    );
  }

  onSubmit(): void {
    this.loginAttempt();
    this.loginForm.controls['password'].reset();
  }

  showLoginForm(): boolean {
    return this._authService.isLogged();
  }

  openRegisterWindow(): void {
    this._router.navigateByUrl('register');
  }
}
