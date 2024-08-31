import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../../auth/service/auth.service";
import {LoginUserRequest} from "../../../auth/dto/login-user.request";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  emailControl = new FormControl('', [Validators.required, Validators.email]);
  passwordControl = new FormControl('', [Validators.required]);

  hidePassword: boolean = true;

  form = new FormGroup({
    email: this.emailControl,
    password: this.passwordControl,
  });

  constructor(
    private readonly authService: AuthService,
  ) {
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  submit() {
    const {email, password} = this.form.value;
    const loginUserRequest = new LoginUserRequest(email ?? '', password ?? '');
    this.authService.login(loginUserRequest);
  }
}
