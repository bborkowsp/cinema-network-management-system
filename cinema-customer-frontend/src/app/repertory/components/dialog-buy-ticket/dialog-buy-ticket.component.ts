import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Router} from "@angular/router";

export interface ConfirmDialogData {
  id: string;
}

@Component({
  selector: 'app-dialog-buy-ticket',
  templateUrl: './dialog-buy-ticket.component.html',
  styleUrls: ['./dialog-buy-ticket.component.scss']
})
export class DialogBuyTicketComponent {
  readonly data = inject<ConfirmDialogData>(MAT_DIALOG_DATA);

  constructor(
    private readonly router: Router
  ) {
  }

  handleBuyTicket() {
    console.log(this.data)
    console.log("handle buy")
    this.router.navigateByUrl(`buy-ticket/${this.data.id}`);
  }
}
