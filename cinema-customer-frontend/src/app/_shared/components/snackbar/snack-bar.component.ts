import {Component, inject} from '@angular/core';
import {MAT_SNACK_BAR_DATA, MatSnackBarRef} from "@angular/material/snack-bar";
import {SnackBarData} from "./snackbar-data.interface";
import {SnackBarType} from "./snackbar-type.enum";

@Component({
  selector: 'app-snackbar',
  templateUrl: './snack-bar.component.html',
  styleUrl: './snack-bar.component.scss'
})
export class SnackBarComponent {
  data = inject<SnackBarData>(MAT_SNACK_BAR_DATA);
  snackBarRef = inject(MatSnackBarRef);
  protected readonly SnackBarType = SnackBarType;
}
