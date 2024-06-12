import {Component, Input} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-register-main-form-frame',
  templateUrl: './register-main-form-frame.component.html',
  styleUrls: ['./register-main-form-frame.component.scss']
})
export class RegisterMainFormFrameComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() emailControl!: FormControl;
  @Input() repeatEmailControl!: FormControl;
  @Input() passwordControl!: FormControl;
  @Input() roleControl!: FormControl;
  @Input() firstNameControl!: FormControl;
  @Input() lastNameControl!: FormControl;

  hidePassword: boolean = true;

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
