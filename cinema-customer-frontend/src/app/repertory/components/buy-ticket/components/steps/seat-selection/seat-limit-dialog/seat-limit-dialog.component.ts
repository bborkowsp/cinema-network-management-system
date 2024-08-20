import {Component} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-seat-limit-dialog',
  templateUrl: './seat-limit-dialog.component.html',
  styleUrls: ['./seat-limit-dialog.component.scss']
})
export class SeatLimitDialogComponent {
  constructor(public dialogRef: MatDialogRef<SeatLimitDialogComponent>) {
  }

}
