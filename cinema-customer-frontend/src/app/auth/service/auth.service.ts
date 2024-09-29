import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import * as moment from "moment";
import {jwtDecode} from 'jwt-decode';
import {environment} from "../../../assets/environment";
import {LoginUserRequest} from "../dto/login-user.request";
import {RegisterUserRequest} from "../dto/register-user.request";
import {ResetPasswordRequest} from "../dto/reset-password.request";
import {OpenSnackBar} from "../../_shared/components/snackbar/open-snack-bar";
import {SnackBarType} from "../../_shared/components/snackbar/snackbar-type.enum";

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private static readonly AUTH_ENDPOINT_URL = `${environment.API_BASE_URL}/auth`;
  private static readonly SUCCESS_LOGIN_REDIRECT_URL = '/account';
  private static readonly LOGIN_PAGE_REDIRECT_URL = '/login';
  private static readonly TOKEN_LOCALSTORAGE_KEY = 'token';
  private static readonly EXPIRATION_LOCALSTORAGE_KEY = 'expires_at';
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLoggedIn());

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private readonly openSnackBar: OpenSnackBar
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/login`;
    this.removeTokenFromLocalStorage();
    this.createLoginHttpRequest(url, loginUserRequest);
  }

  register(registerUserRequest: RegisterUserRequest) {
    this.removeTokenFromLocalStorage();
    const url = `${AuthService.AUTH_ENDPOINT_URL}/register`;
    return this.httpClient.post<any>(url, registerUserRequest);
  }

  logout() {
    this.removeTokenFromLocalStorage();
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }

  isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    return expiration && moment().isBefore(expiration);
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
    this.setToken(response.token);
    this.loggedIn.next(true);
    this.loggedInUserSubject.next(loginUserRequest.email);
    this.router.navigate([AuthService.SUCCESS_LOGIN_REDIRECT_URL]);
  }

  private handleFailedLogin(error: any) {
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
    this.openSnackBar.openSnackBar(error.error.errors[0], SnackBarType.ERROR);
  }

  private getExpiration(): moment.Moment {
    const expiration = localStorage.getItem('expires_at');
    return expiration ? moment(JSON.parse(expiration)) : moment();
  }

  private removeTokenFromLocalStorage() {
    localStorage.removeItem(AuthService.TOKEN_LOCALSTORAGE_KEY);
    localStorage.removeItem(AuthService.EXPIRATION_LOCALSTORAGE_KEY);
  }

  private setToken(token: string) {
    const decodedToken = this.getDecodedAccessToken(token);
    if (decodedToken) {
      const expiresAt = decodedToken.exp * 1000;
      localStorage.setItem(AuthService.TOKEN_LOCALSTORAGE_KEY, token);
      localStorage.setItem(AuthService.EXPIRATION_LOCALSTORAGE_KEY, JSON.stringify(expiresAt));
    }
  }

  private getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }
}
