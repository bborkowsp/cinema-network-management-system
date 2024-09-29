import {Component, Input, signal} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-login-form-fields',
  templateUrl: './login-form-fields.component.html',
  styleUrls: ['./login-form-fields.component.scss']
})
export class LoginFormFieldsComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() emailControl!: FormControl;
  @Input() passwordControl!: FormControl;
  hidePassword = signal(true);

  hidePasswordEvent(event: MouseEvent) {
    this.hidePassword.set(!this.hidePassword());
    event.stopPropagation();
  }
}
