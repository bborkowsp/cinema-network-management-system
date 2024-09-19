import {Component, EventEmitter, Input, Output} from '@angular/core';
import {BuyTicketFormBuilder} from "../../buy-ticket-form-builder";
import {FormGroup, FormGroupDirective} from "@angular/forms";

@Component({
  selector: 'app-steps-container',
  templateUrl: './steps-container.component.html',
  styleUrls: ['./steps-container.component.scss']
})
export class StepsContainerComponent {
  @Input({required: true}) buyTicketFormBuilder!: BuyTicketFormBuilder;
  @Input({required: true}) buyTicketForm!: FormGroupDirective;
  @Output() handlePay = new EventEmitter<any>();

  get seatSelectionFormGroup() {
    return this.buyTicketFormBuilder.form.get('seatSelection') as FormGroup;
  }

  get customerDataFormGroup() {
    return this.buyTicketFormBuilder.form.get('customerData') as FormGroup;
  }

  handlePayClicked() {
    this.handlePay.emit();
  }
}
