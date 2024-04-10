import {Injectable} from "@angular/core";
import {MatDialog} from "@angular/material/dialog";
import {DialogComponent} from "../components/dialog/dialog.component";

@Injectable({
  providedIn: 'root',
})

export class ErrorNotificationService {

  constructor(private readonly dialog: MatDialog) {
  }

  showDialog(errorMessage: string) {
    this.dialog.open(DialogComponent, {
      data: {
        message: errorMessage
      },
    });
  }
}
