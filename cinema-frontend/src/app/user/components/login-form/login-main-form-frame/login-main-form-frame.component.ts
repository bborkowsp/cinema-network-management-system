import {Component, Input} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm} from "@angular/forms";

@Component({
  selector: 'app-login-main-form-frame',
  templateUrl: './login-main-form-frame.component.html',
  styleUrls: ['./login-main-form-frame.component.scss']
})
export class LoginMainFormFrameComponent {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input() emailControl!: FormControl;
  @Input() passwordControl!: FormControl;
  @Input() roleControl!: FormControl;
}
