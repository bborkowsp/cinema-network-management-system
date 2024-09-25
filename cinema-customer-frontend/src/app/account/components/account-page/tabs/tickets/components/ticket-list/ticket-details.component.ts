import {Component, Input} from '@angular/core';
import {TicketResponse} from "../../../../../../dtos/response/ticket.response";

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrl: './ticket-details.component.scss'
})
export class TicketDetailsComponent {
  @Input({required: true}) ticket!: TicketResponse;
}
