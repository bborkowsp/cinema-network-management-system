import {Component} from '@angular/core';
import {MatAnchor, MatButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {SharedModule} from "../../../_shared/shared.module";

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
export class CapturePaypalPaymentComponent {

}
