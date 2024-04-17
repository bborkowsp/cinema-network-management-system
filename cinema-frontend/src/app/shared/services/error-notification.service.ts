import {Injectable} from "@angular/core";
import {MatDialog} from "@angular/material/dialog";
import {ErrorDialogComponent} from "../components/error-dialog/error-dialog.component";

@Injectable({
  providedIn: 'root',
})

export class ErrorNotificationService {

  constructor(private readonly dialog: MatDialog) {
  }

  showDialog(errorMessage: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: {
        message: errorMessage
      },
    });
  }
}
