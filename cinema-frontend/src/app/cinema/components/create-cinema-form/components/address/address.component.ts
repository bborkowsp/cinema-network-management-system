import {Component, Input} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;


  get streetAndBuildingNumberControl(): FormControl {
    return this.formGroup.get('address')?.get('streetAndBuildingNumber') as FormControl;
  }

  get cityControl(): FormControl {
    return this.formGroup.get('address')?.get('city') as FormControl;
  }

  get postalCodeControl(): FormControl {
    return this.formGroup.get('address')?.get('postalCode') as FormControl;
  }

  get countryControl(): FormControl {
    return this.formGroup.get('address')?.get('country') as FormControl;
  }


}
