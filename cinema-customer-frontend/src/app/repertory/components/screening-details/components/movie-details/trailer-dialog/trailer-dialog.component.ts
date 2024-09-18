import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {SafePipe} from "./safe.pipe";

@Component({
  selector: 'app-trailer-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    SafePipe,
    MatDialogTitle
  ],
  templateUrl: './trailer-dialog.component.html',
  styleUrl: './trailer-dialog.component.scss'
})
export class TrailerDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { trailerUrl: string }) {
  }

}
