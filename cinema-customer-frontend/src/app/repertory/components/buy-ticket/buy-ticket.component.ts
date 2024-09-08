import {Component, OnInit} from '@angular/core';
import {BuyTicketFormBuilder} from "./buy-ticket-form-builder";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentMethod} from "./enums/payment-method";
import {TicketingService} from "../../services/ticketing.service";
import {BuyTicketRequest} from "../../dtos/request/BuyTicketRequest";
import {AuthService} from "../../../auth/service/auth.service";
import {UserService} from "../../../user/services/user.service";
import {UserResponse} from "../../../user/dtos/response/user.response";


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
    private readonly authService: AuthService,
    private readonly userService: UserService
  ) {
  }

  ngOnInit() {
    this.createBuyTicketForm();
  }

  onPayClicked() {
    const buyTicketForm = this.buyTicketFormBuilder.getBuyTicketRequestFromForm();
    if (buyTicketForm.paymentMethod === PaymentMethod.PAYPAL) {
      this.payWithPayPal(buyTicketForm);
    } else {
      console.log('Not implemented yet');
    }
  }

  private createBuyTicketForm() {
    this.buyTicketFormBuilder = new BuyTicketFormBuilder(this.formBuilder, this.activatedRoute);
    if (this.authService.isLoggedIn()) {
      this.getCustomerData();
    }
  }

  private payWithPayPal(buyTicketForm: BuyTicketRequest) {
    this.ticketingService.payWithPayPal(buyTicketForm).subscribe({
      next: () => {
        this.router.navigate(['/tickets']);
      }
    });
  }

  private getCustomerData() {
    const customer$ = this.userService.getCustomer(this.getLoggedInUserEmail());
    customer$.subscribe({
      next: (customer: UserResponse) => {
        this.buyTicketFormBuilder.fillFormWithCustomerData(customer);
      },
      error: () => {
      }
    });
  }

  private getLoggedInUserEmail(): string {
    return this.authService.getLoggedInUserEmail();
  }
}
