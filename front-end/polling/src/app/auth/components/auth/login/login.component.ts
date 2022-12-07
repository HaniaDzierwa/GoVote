import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthorizeService } from 'src/app/auth/modules/auth/services/AuthorizeService.service';

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
      // this._router.navigateByUrl('home');
    }
  }

  loginAttempt(): boolean {
    return this._authService.logUser(
      this.loginForm.value.login!,
      this.loginForm.value.password!
    );
  }

  onSubmit(): void {
    const result = this.loginAttempt();
    if (result == false) {
      this.loginForm.controls['password'].reset();

      this._matSnackBar.open(
        'Login lub hasło niepoprawne. Proszę spróbować jeszcze raz',
        'Zamknij',
        {
          duration: 5000,
          verticalPosition: 'bottom',
        }
      );
    }
  }

  showLoginForm(): boolean {
    return this._authService.isLogged();
  }

  openRegisterWindow(): void {
    this._router.navigateByUrl('register');
  }
}
