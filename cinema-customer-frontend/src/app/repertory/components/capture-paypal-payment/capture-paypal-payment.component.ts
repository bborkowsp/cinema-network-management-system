import {Component, OnInit} from '@angular/core';
import {MatAnchor, MatButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {SharedModule} from "../../../_shared/shared.module";
import {ActivatedRoute} from "@angular/router";
import {TicketingService} from "../../services/ticketing.service";

@Component({
  selector: 'app-capture-paypal-payment',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    SharedModule,
    MatAnchor
  ],
  templateUrl: './capture-paypal-payment.component.html',
  styleUrl: './capture-paypal-payment.component.scss'
})
export class CapturePaypalPaymentComponent implements OnInit {

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly ticketingService: TicketingService
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
