import {Component, OnInit} from '@angular/core';
import {TicketService} from "../../../../services/ticket.service";
import {TicketResponse} from "../../../../dtos/response/ticket.response";

@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrl: './tickets.component.scss'
})
export class TicketsComponent implements OnInit {
  tickets!: TicketResponse[];

  constructor(
    private readonly ticketService: TicketService,
  ) {
  }

  ngOnInit() {
    this.ticketService.getTickets().subscribe({
      next: (response) => {
        this.tickets = response;
      },
      error: (error) => {
        console.error(error);
      }
    })
  }
}
