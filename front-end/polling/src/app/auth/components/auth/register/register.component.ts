import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthorizeService } from 'src/app/auth/modules/auth/services/AuthorizeService.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  registerForm = this._formBuilder.group({
    name: ["",Validators.required],
    surname: [null, Validators.required],
    login: [null, Validators.required],
    password: [null, Validators.required],
    email: [null,Validators.required],
    PESEL: [null, Validators.required]
  });

  constructor(
    private readonly _formBuilder: FormBuilder,
    private readonly _router: Router,
    private readonly _authService: AuthorizeService,
    private readonly _matSnackBar: MatSnackBar
  ) {}

  ngOnInit() {}

  onSubmit(): void {}

  isFormValid(): boolean {
    console.log(this.registerForm.valid);
    return this.registerForm.valid;
  }

  openLoginForm(): void {
    this._router.navigateByUrl('login');
  }
}
