import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {PaymentMethod} from "../../../enums/payment-method";


@Component({
  selector: 'app-order-form',
  templateUrl: './customer-data.component.html',
  styleUrls: ['./customer-data.component.scss']
})
export class CustomerDataComponent {
  @Input({required: true}) customerDataFormGroup!: FormGroupDirective | NgForm;
  @Input({required: true}) createBuyTicketForm!: FormGroup;
  protected paymentMethods: PaymentMethod[] = Object.values(PaymentMethod);
  private readonly paymentIconsDirectory = 'assets/payment-icons/';

  get firstNameControl(): FormControl {
    return this.createBuyTicketForm.get('firstName') as FormControl;
  }

  get lastNameControl(): FormControl {
    return this.createBuyTicketForm.get('lastName') as FormControl;
  }

  get emailControl(): FormControl {
    return this.createBuyTicketForm.get('email') as FormControl;
  }

  get paymentMethodControl(): FormControl {
    return this.createBuyTicketForm.get('paymentMethod') as FormControl;
  }

  getPaymentIcon(payment: string) {
    switch (payment) {
      case PaymentMethod.PAYPAL :
        return `${this.paymentIconsDirectory}PayPal.svg`;
      case PaymentMethod.PRZELEWY_ONLINE:
        return `${this.paymentIconsDirectory}Przelewy24.svg`;
      case PaymentMethod.GOOGLE_PAY:
        return `${this.paymentIconsDirectory}GooglePay.svg`;
      case PaymentMethod.BLIK:
        return `${this.paymentIconsDirectory}Blik.svg`;
      default:
        return "";
    }
  }
}
