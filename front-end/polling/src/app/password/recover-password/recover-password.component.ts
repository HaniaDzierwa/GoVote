import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogMessage } from 'src/app/componenets/dialog/dialog-message.module';
import { DialogService } from 'src/app/services/dialog.service.';
import { PasswordHttpService } from 'src/app/test/services/password.http.service';

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.scss']
})
export class RecoverPasswordComponent implements OnInit {

  public activationCode: string = ""

  public changePasswordForm: FormGroup

  constructor(private router: Router, private route: ActivatedRoute, private passwordHttpService: PasswordHttpService,
    private dialogService: DialogService, private fb: FormBuilder) { 
    this.route.queryParams.subscribe(params => {
      this.activationCode = params['activationCode']
    });

    this.changePasswordForm = this.fb.group({
      "password": ['', Validators.compose([Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')])],
      "reenterPassword": ['', Validators.compose([Validators.required, this.comparePasswords])]
    })
  }

  ngOnInit(): void {
  }

  public recoverPassword(): void {

    if (!this.changePasswordForm.valid) {
      return this.dialogService.openDialog(new DialogMessage('Błąd', 'Sprawdź czy wszystkie pola zostały poprawnie wypełnione'))
    }

    this.dialogService.openLoader(new DialogMessage('Zmiana hasła', 'Trwa zmiana hasła, proszę czekać'))
    this.passwordHttpService.recoverPassword(this.activationCode, this.changePasswordForm.controls['password'].value).then(
      resp => {
        this.dialogService.dialogReference?.close()

        this.dialogService.openDialog(new DialogMessage('Sukces', 'Hasło zostało z sukcesem zmienione'),
          () => {
            this.router.navigate([''])
          })
      }
    ).catch(err => {
      this.dialogService.dialogReference?.close()
    })
  }


  private comparePasswords(form: FormGroup): ValidationErrors {
    let passwordsMatch: boolean = form.get('password')?.value == form.get('reenterPassword')?.value
    if (passwordsMatch) return {}

    return {
      passwordsDoNotMatch: true
    }
  }

}
