import {Component, Input} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() streetAndBuildingNumberControl!: FormControl;
  @Input() cityControl!: FormControl;
  @Input() postalCodeControl!: FormControl;
  @Input() countryControl!: FormControl;
}
