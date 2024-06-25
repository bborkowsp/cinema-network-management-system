import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

export interface DialogData {
  message: string;
}

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ErrorDialogComponent {
  data = inject<DialogData>(MAT_DIALOG_DATA);
}
