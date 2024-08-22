import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent {
  firstNameControl = new FormControl('', [Validators.required]);
  lastNameControl = new FormControl('', [Validators.required]);
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  emailConfirmationControl = new FormControl('', [Validators.required, Validators.email]);
  paymentMethods: string[] = ['Przelewy Online', 'Google Pay', 'BLIK'];

  form = new FormGroup({
    firstName: this.firstNameControl,
    lastName: this.lastNameControl,
    email: this.emailControl,
    emailConfirmation: this.emailConfirmationControl,
  });

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
