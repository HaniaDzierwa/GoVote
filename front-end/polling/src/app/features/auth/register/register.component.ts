import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthorizeService } from '../../../services/AuthorizeService.service';
import { RegisterService } from '../../../services/register.service';
import { Register } from '../../../model/register';
import { HttpErrorResponse } from '@angular/common/http';
import {
  Subject,
  catchError,
  of,
  filter,
  takeUntil,
  Observable,
  tap,
} from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  private readonly _destroy$: Subject<boolean> = new Subject<boolean>();

  registerForm = this._formBuilder.group({
    name: ['', Validators.required],
    surname: [null, Validators.required],
    login: [null, Validators.required],
    password: [null, Validators.required],
    email: [null, Validators.required],
    PESEL: [null, Validators.required],
  });

  constructor(
    private readonly _formBuilder: FormBuilder,
    private readonly _router: Router,
    private readonly _authService: AuthorizeService,
    private readonly _matSnackBar: MatSnackBar,
    private readonly _registerService: RegisterService
  ) {}

  ngOnInit() {}

  onSubmit(): void {
    const resgisterBody: Register = {
      name: this.registerForm.get('name')!.value!,
      surname: this.registerForm.get('surname')!.value!,
      login: this.registerForm.get('login')!.value!,
      password: this.registerForm.get('password')!.value!,
      email: this.registerForm.get('email')!.value!,
      PESEL: this.registerForm.get('PESEL')!.value!,
    };
    this._registerService
      .registerUser(resgisterBody)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this._matSnackBar.open('Napotkano problem przy rejestracji', 'OK');
          return of(undefined);
        }),
        filter((res) => Boolean(res)),
        takeUntil(this._destroy$)
      )

      .subscribe((res) => {
        this._matSnackBar.open('Rejestracja się powiodła', 'OK');
        this.registerForm.reset();
      });
  }

  isFormValid(): boolean {
    return this.registerForm.valid;
  }

  openLoginForm(): void {
    this._router.navigateByUrl('login');
  }

  errorHandlerResponse(): Observable<undefined> {
    this._matSnackBar.open('Napotkano problem przy rejestracji', 'OK');
    return of(undefined);
  }
}
