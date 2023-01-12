import {Injectable} from '@angular/core';
import {DialogComponent} from "../componenets/dialog/dialog.component";
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {DialogMessage} from "../componenets/dialog/dialog-message.module";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  public dialogReference: MatDialogRef<DialogComponent> | undefined;

  constructor(public dialog: MatDialog) {
  }

  public openDialog(message: DialogMessage, yesAction: () => void = () => {
  }, noAction: () => void = () => {
  }): void {
    this.dialogReference = this.dialog.open(DialogComponent, {
      width: '400px',
      data: message
    });

    this.dialogReference.afterClosed().subscribe(result => {
      if (result == true) {
        yesAction();
      } else {
        noAction();
      }
    });
  }

  public openLoader(message: DialogMessage): void {
    message.loader = true;
    this.dialogReference = this.dialog.open(DialogComponent, {
      width: '400px',
      data: message
    });

    this.dialogReference.disableClose = true;
  }
}
