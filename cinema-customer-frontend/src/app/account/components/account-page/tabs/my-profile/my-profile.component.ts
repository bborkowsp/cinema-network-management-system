import {Component} from '@angular/core';
import {SnackBarType} from "../../../../../_shared/components/snackbar/snackbar-type.enum";
import {SnackBarComponent} from "../../../../../_shared/components/snackbar/snack-bar.component";
import {SnackBarData} from "../../../../../_shared/components/snackbar/snackbar-data.interface";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.scss']
})
export class MyProfileComponent {
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
