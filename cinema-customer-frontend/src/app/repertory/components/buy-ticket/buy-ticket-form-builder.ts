import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BuyTicketRequest} from "../../dtos/request/BuyTicketRequest";
import {ActivatedRoute} from "@angular/router";

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
    const movieId = this.activatedRoute.snapshot.params['id'];
    return new BuyTicketRequest(
      movieId,
      this.seatSelectionFormGroup.get('selectedSeats')!.value,
      this.customerDataFormGroup.get('firstName')!.value,
      this.customerDataFormGroup.get('lastName')!.value,
      this.customerDataFormGroup.get('email')!.value,
      this.customerDataFormGroup.get('paymentMethod')!.value,
    );
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
