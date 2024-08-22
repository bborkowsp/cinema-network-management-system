import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent {
  firstNameControl = new FormControl;
  lastNameControl = new FormControl;
  emailControl = new FormControl;
  paymentMethods: string[] = ['Przelewy Online', 'Google Pay', 'BLIK'];

  getPaymentIcon(payment: string) {
    switch (payment) {
      case 'Przelewy Online':
        return 'assets/Przelewy24_logo.svg';
      case 'Google Pay':
        return 'assets/google-pay.svg';
      case 'BLIK':
        return 'assets/Blik.svg';
      default:
        return "";
    }
  }
}
