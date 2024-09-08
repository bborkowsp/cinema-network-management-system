import {Component, OnInit} from '@angular/core';
import {BuyTicketFormBuilder} from "./buy-ticket-form-builder";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentMethod} from "./enums/payment-method";
import {TicketingService} from "../../services/ticketing.service";
import {BuyTicketRequest} from "../../dtos/request/BuyTicketRequest";


@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.scss']
})
export class BuyTicketComponent implements OnInit {
  buyTicketFormBuilder !: BuyTicketFormBuilder;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private ticketingService: TicketingService,
  ) {
  }

  ngOnInit() {
    this.createBuyTicketForm();
  }

  onSubmit() {
  }

  onPayClicked() {
    const buyTicketForm = this.buyTicketFormBuilder.getBuyTicketRequestFromForm();
    if (buyTicketForm.paymentMethod === PaymentMethod.PAYPAL) {
      this.payWithPayPal(buyTicketForm);
    }
  }

  private createBuyTicketForm() {
    this.buyTicketFormBuilder = new BuyTicketFormBuilder(this.formBuilder, this.activatedRoute);
  }

  private payWithPayPal(buyTicketForm: BuyTicketRequest) {
    this.ticketingService.payWithPayPal(buyTicketForm).subscribe({
      next: () => {
        this.router.navigate(['/tickets']);
      }
    });
  }
}
