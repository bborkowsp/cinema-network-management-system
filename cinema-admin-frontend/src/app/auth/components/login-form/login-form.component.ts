import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {LoginUserRequest} from "../../../user/dtos/request/login-user.request";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  protected readonly loginFormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private readonly authService: AuthService,
  ) {
  }

  protected onSubmit(): void {
    const loginUserRequest = this.createLoginUserRequest();
    this.authService.login(loginUserRequest);
  }

  private createLoginUserRequest() {
    return new LoginUserRequest(
      this.loginFormGroup.value.email as string,
      this.loginFormGroup.value.password as string
    );
  }
}
