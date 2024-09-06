import {Injectable} from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import * as moment from "moment";
import {jwtDecode} from 'jwt-decode';
import {environment} from "../../../assets/environment";
import {LoginUserRequest} from "../dto/login-user.request";
import {RegisterUserRequest} from "../dto/register-user.request";
import {ResetPasswordRequest} from "../../user/dtos/request/reset-password.request";

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  static readonly authUrl = `${environment.API_BASE_URL}/auth`;
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isLoggedIn());

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.authUrl}/login`;
    this.removeTokenFromLocalStorage();
    return this.httpClient.post<any>(url, loginUserRequest).subscribe({
      next: (response) => {
        const token = response.token;
        this.setToken(token);
        this.loggedIn.next(true);
        this.loggedInUserSubject.next(loginUserRequest.email);
        this.router.navigate(['/account']);
      },
      error: (error) => {
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
      }
    });
  }

  register(registerUserRequest: RegisterUserRequest) {
    const url = `${AuthService.authUrl}/register`;
    return this.httpClient.post<any>(url, registerUserRequest);
  }

  logout() {
    this.removeTokenFromLocalStorage();
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    const isLoggedIn = expiration && moment().isBefore(expiration);
    console.log('isLoggedIn fun', isLoggedIn);
    return isLoggedIn;
  }

  requestForPasswordReset(email: string) {
    const url = `${AuthService.authUrl}/reset-password-request?email=${encodeURIComponent(email)}`;
    return this.httpClient.post<void>(url, {});
  }

  resetPassword(resetPasswordRequest: ResetPasswordRequest) {
    const url = `${AuthService.authUrl}/reset-password`;
    return this.httpClient.post<void>(url, resetPasswordRequest);
  }

  private getExpiration(): moment.Moment {
    const expiration = localStorage.getItem('expires_at');
    return expiration ? moment(JSON.parse(expiration)) : moment();
  }

  private removeTokenFromLocalStorage() {
    localStorage.removeItem('token');
    localStorage.removeItem('expires_at');
  }

  private getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }

  private setToken(token: string) {
    const decodedToken = this.getDecodedAccessToken(token);
    if (decodedToken) {
      const expiresAt = decodedToken.exp * 1000;
      localStorage.setItem('token', token);
      localStorage.setItem('expires_at', JSON.stringify(expiresAt));
    }
  }
}
