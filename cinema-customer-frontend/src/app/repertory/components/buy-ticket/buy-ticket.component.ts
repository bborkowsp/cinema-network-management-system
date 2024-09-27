import {Component, OnInit} from '@angular/core';
import {BuyTicketFormBuilder} from "./buy-ticket-form-builder";
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentMethod} from "./enums/payment-method";
import {PayPalService} from "../../services/pay-pal.service";
import {AuthService} from "../../../auth/service/auth.service";
import {BuyTicketRequest} from "../../dtos/request/buy-ticket.request";
import {PaypalResponse} from "../../dtos/response/paypal.response";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CustomerService} from "../../../account/services/customer.service";
import {UserResponse} from "../../../account/dtos/response/user.response";


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
    private payPalService: PayPalService,
    private readonly authService: AuthService,
    private readonly customerService: CustomerService,
    private snackBar: MatSnackBar
  ) {
  }

  ngOnInit() {
    this.deleteReturnLinkFromLocalStorage();
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
    this.saveReturnLinkToCurrentPageInLocalStorage();
    this.payPalService.payWithPayPal(buyTicketForm).subscribe({
      next: (paypalResponse: PaypalResponse) => {
        console.log(paypalResponse);
        this.openSafeWindow(paypalResponse.redirectUrl);
      },
      error: (error) => {
        this.snackBar.open(error.error.errors[0], 'Close', {
          duration: 3000,
        });
      }
    });
  }

  private getCustomerData() {
    const customer$ = this.customerService.getCustomer();
    customer$.subscribe({
      next: (customer: UserResponse) => {
        this.buyTicketFormBuilder.fillFormWithCustomerData(customer);
      },
      error: () => {
      }
    });
  }

  private openSafeWindow(redirectUrl: string) {
    window.open(redirectUrl, '_blank', 'noopener,noreferrer');
  }

  private saveReturnLinkToCurrentPageInLocalStorage() {
    localStorage.setItem('returnLink', this.router.url);
  }

  private deleteReturnLinkFromLocalStorage() {
    localStorage.removeItem('returnLink');
  }
}
