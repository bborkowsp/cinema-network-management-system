import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {UserResponse} from "../../../user/dtos/response/user.response";
import {BuyTicketRequest} from "../../dtos/request/buy-ticket.request";

export class BuyTicketFormBuilder {
  form: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly activatedRoute: ActivatedRoute,
  ) {
    this.form = this.createForm();
  }

  get seatSelectionFormGroup() {
    return this.form.get('seatSelection') as FormGroup;
  }

  get customerDataFormGroup() {
    return this.form.get('customerData') as FormGroup;
  }

  getBuyTicketRequestFromForm() {
    const screeningId = this.activatedRoute.snapshot.params['id'];
    const paymentMethod = this.customerDataFormGroup.get('paymentMethod')!.value;
    return new BuyTicketRequest(
      screeningId,
      this.seatSelectionFormGroup.get('selectedSeats')!.value,
      this.customerDataFormGroup.get('firstName')!.value,
      this.customerDataFormGroup.get('lastName')!.value,
      this.customerDataFormGroup.get('email')!.value,
      paymentMethod[0]
    );
  }

  fillFormWithCustomerData(customer: UserResponse) {
    this.customerDataFormGroup.setValue({
      firstName: customer.firstName,
      lastName: customer.lastName,
      email: customer.email,
      paymentMethod: '',
    });
  }

  private createForm() {
    return this.formBuilder.group({
      seatSelection: this.formBuilder.group({
        selectedSeats: this.formBuilder.array([], Validators.required),
      }),
      customerData: this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        paymentMethod: ['', Validators.required],
      }),
    });
  }
}
