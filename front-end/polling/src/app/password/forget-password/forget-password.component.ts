import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogMessage } from 'src/app/componenets/dialog/dialog-message.module';
import { DialogService } from 'src/app/services/dialog.service.';
import { PasswordHttpService } from 'src/app/test/services/password.http.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {

  private recoverForm: FormGroup

  constructor(private route: ActivatedRoute, private passwordHttpService: PasswordHttpService,
    private dialogService: DialogService, private fb: FormBuilder, private router: Router) {

      this.recoverForm = this.fb.group({
        'email': Validators.compose([Validators.required, Validators.email])
      })

    }

  ngOnInit(): void {
    this.recoverForm.controls['email'].setValue('ola.lewandowska12@gmail.com')
    this.recoverPassword()
  }

  public recoverPassword(): void {

    if (!this.recoverForm.valid) {
      return this.dialogService.openDialog(new DialogMessage('Błąd', 'Sprawdź czy wszystkie pola zostały poprawnie wypełnione'))
    }

    this.dialogService.openLoader(new DialogMessage('Zmiana hasła', 'Trwa wysyłka maila z wiadomością do zmiany hasła'))
    this.passwordHttpService.requestPasswordRecovery(this.recoverForm.controls['email'].value).then(resp => {
      this.dialogService.dialogReference?.close()

      this.dialogService.openDialog(new DialogMessage('Sukces', 'Email został wysłany na adres email: ' + this.recoverForm.controls['email'].value),
        () => {
          this.router.navigate([''])
        })
    
    }).catch(err => {
      this.dialogService.dialogReference?.close()
    })
  }
}
