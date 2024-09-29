import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarType} from "./snackbar-type.enum";
import {SnackBarComponent} from "./snack-bar.component";
import {SnackBarData} from "./snackbar-data.interface";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class OpenSnackBar {
  constructor(
    private snackBar: MatSnackBar,
  ) {
  }

  openSnackBar(message: string, snackBarType: SnackBarType) {
    this.snackBar.openFromComponent(SnackBarComponent, {
      duration: 5000,
      data: {
        message,
        snackBarType,
      } as SnackBarData,
    });
  }
}
