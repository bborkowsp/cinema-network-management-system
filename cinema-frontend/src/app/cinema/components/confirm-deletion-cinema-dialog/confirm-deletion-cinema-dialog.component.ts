import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ConfirmDialogData} from "../../../shared/components/confirm-deletion-dialog/confirm-deletion-dialog.component";

@Component({
  selector: 'app-confirm-deletion-cinema-dialog',
  templateUrl: './confirm-deletion-cinema-dialog.component.html',
  styleUrls: ['./confirm-deletion-cinema-dialog.component.scss']
})
export class ConfirmDeletionCinemaDialog {
  readonly data = inject<ConfirmDialogData>(MAT_DIALOG_DATA);
}
