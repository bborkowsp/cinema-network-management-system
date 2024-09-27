import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PayPalService} from "../../services/pay-pal.service";

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
    this.ticketingService.sendCompletePayPalPaymentRequest(token).subscribe({
      next: () => {
      }, error: () => {
      }
    });
  }
}
