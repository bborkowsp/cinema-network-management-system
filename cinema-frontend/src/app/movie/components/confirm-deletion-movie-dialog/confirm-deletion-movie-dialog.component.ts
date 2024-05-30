import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ConfirmDialogData} from "../../../shared/components/confirm-deletion-dialog/confirm-deletion-dialog.component";

@Component({
  selector: 'app-confirm-deletion-movie-dialog',
  templateUrl: './confirm-deletion-movie-dialog.component.html',
  styleUrls: ['./confirm-deletion-movie-dialog.component.scss']
})
export class ConfirmDeletionMovieDialog {
  readonly data = inject<ConfirmDialogData>(MAT_DIALOG_DATA);
}
