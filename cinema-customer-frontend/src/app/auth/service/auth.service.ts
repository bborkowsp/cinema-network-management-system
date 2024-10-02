import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {environment} from "../../../assets/environment";
import {LoginUserRequest} from "../dto/login-user.request";
import {RegisterUserRequest} from "../dto/register-user.request";
import {ResetPasswordRequest} from "../dto/reset-password.request";
import {OpenSnackBar} from "../../_shared/components/snackbar/open-snack-bar";
import {SnackBarType} from "../../_shared/components/snackbar/snackbar-type.enum";
import {JwtService} from "./jwt.service";

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private static readonly AUTH_ENDPOINT_URL = `${environment.API_BASE_URL}/auth`;
  private static readonly SUCCESS_LOGIN_REDIRECT_URL = '/account';
  private static readonly LOGIN_PAGE_REDIRECT_URL = '/login';
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLoggedIn());

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private openSnackBar: OpenSnackBar,
    private jwtService: JwtService
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/login`;
    this.jwtService.removeJwtFromLocalStorage();
    this.createLoginHttpRequest(url, loginUserRequest);
  }

  register(registerUserRequest: RegisterUserRequest) {
    this.jwtService.removeJwtFromLocalStorage();
    const url = `${AuthService.AUTH_ENDPOINT_URL}/register`;
    return this.httpClient.post<any>(url, registerUserRequest);
  }

  logout() {
    this.jwtService.removeJwtFromLocalStorage();
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }

  isLoggedIn(): boolean {
    console.log(!this.jwtService.isJwtExpired());
    return !this.jwtService.isJwtExpired();
  }

  requestForPasswordReset(email: string) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/reset-password-request?email=${encodeURIComponent(email)}`;
    return this.httpClient.post<void>(url, {});
  }

  resetPassword(resetPasswordRequest: ResetPasswordRequest) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/reset-password`;
    return this.httpClient.post<void>(url, resetPasswordRequest);
  }

  private createLoginHttpRequest(url: string, loginUserRequest: LoginUserRequest) {
    return this.httpClient.post<any>(url, loginUserRequest).subscribe({
      next: (response) => {
        this.handleSuccessfulLogin(loginUserRequest, response);
      },
      error: (error) => {
        this.handleFailedLogin(error);
      }
    });
  }

  private handleSuccessfulLogin(loginUserRequest: LoginUserRequest, response: any) {
    this.jwtService.setJwt(response.token);
    this.loggedIn.next(true);
    this.loggedInUserSubject.next(loginUserRequest.email);
    this.router.navigate([AuthService.SUCCESS_LOGIN_REDIRECT_URL]);
  }

  private handleFailedLogin(error: any) {
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
    this.openSnackBar.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
  }
}
