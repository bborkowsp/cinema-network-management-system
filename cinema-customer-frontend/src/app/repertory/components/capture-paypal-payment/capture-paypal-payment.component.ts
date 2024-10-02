import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PayPalService} from "../../services/pay-pal.service";
import {FinalizePaymentRequest} from "../../dtos/request/finalize-payment.request";
import {PaymentMethod} from "../buy-ticket/enums/payment-method";

@Component({
  selector: 'app-capture-paypal-payment',
  templateUrl: './capture-paypal-payment.component.html',
  styleUrl: './capture-paypal-payment.component.scss'
})
export class CapturePaypalPaymentComponent implements OnInit {

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly ticketingService: PayPalService
  ) {
  }

  ngOnInit() {
    const token = this.activatedRoute.snapshot.queryParams['token'];
    const finalizePaymentRequest = this.createFinalizePaymentRequest(token);
    this.ticketingService.sendCompletePayPalPaymentRequest(finalizePaymentRequest).subscribe({
      next: () => {
      }, error: () => {
      }
    });
  }

  private createFinalizePaymentRequest(token: any) {
    return new FinalizePaymentRequest(token, PaymentMethod.PAYPAL);
  }
}
