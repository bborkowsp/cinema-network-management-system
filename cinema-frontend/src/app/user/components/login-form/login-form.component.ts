import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginUserRequest} from "../../dtos/request/LoginUserRequest";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  protected isLoading = false;
  protected readonly loginFormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required]),
  });

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
  ) {
  }

  protected onSubmit(): void {
    const {email, password, role} = this.loginFormGroup.value;
    const loginUserRequest = new LoginUserRequest(email ?? '', password ?? '', role ?? '');
    const login$ = this.authService.login(loginUserRequest);
    this.isLoading = true;
    login$.subscribe({
      next: () => this.goToHome(),
    });
  }

  private goToHome(): void {
    this.router.navigate(['/']);
  }
}
