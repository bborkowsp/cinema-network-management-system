import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  passwordControl = new FormControl('', [Validators.required]);

  hidePassword: boolean = true;

  form = new FormGroup({
    email: this.emailControl,
    password: this.passwordControl,
  });

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  submit() {

  }
}
