import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ConfirmDialogData} from "../../../shared/components/confirm-deletion-dialog/confirm-deletion-dialog.component";

@Component({
  selector: 'app-confirm-deletion-projection-technology-dialog',
  templateUrl: './confirm-deletion-projection-technology-dialog.component.html',
  styleUrls: ['./confirm-deletion-projection-technology-dialog.component.scss']
})
export class ConfirmDeletionProjectionTechnologyDialogComponent {
  readonly data = inject<ConfirmDialogData>(MAT_DIALOG_DATA);
}
