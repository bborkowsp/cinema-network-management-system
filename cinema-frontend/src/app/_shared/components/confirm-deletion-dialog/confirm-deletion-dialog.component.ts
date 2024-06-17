import {Component, Input} from '@angular/core';

export interface ConfirmDialogData {
  title: string;
  message: string;
}

@Component({
  selector: 'app-confirm-deletion-dialog',
  templateUrl: './confirm-deletion-dialog.component.html',
  styleUrls: ['./confirm-deletion-dialog.component.scss']
})
export class ConfirmDeletionDialogComponent {
  @Input({required: true}) title!: string;
  @Input({required: true}) message!: string;
}
