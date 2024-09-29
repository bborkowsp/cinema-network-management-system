import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from "rxjs";
import {environment} from "../../../assets/environment";
import {Router} from "@angular/router";
import {LoginUserRequest} from "../../user/dtos/request/login-user.request";
import {JwtService} from "./jwt.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private static readonly AUTH_ENDPOINT_URL = `${environment.API_BASE_URL}/auth`;
  private static readonly LOGIN_PAGE_REDIRECT_URL = '/login';
  private static readonly SUCCESS_LOGIN_REDIRECT_URL = '/home';
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private jwtService: JwtService
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/login`;
    this.jwtService.removeJwtFromLocalStorage();
    this.createLoginHttpRequest(url, loginUserRequest);
  }

  logout() {
    this.loggedIn.next(false);
    this.jwtService.removeJwtFromLocalStorage();
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }

  isLoggedIn(): boolean {
    return !this.jwtService.isJwtExpired();
  }

  private createLoginHttpRequest(url: string, loginUserRequest: LoginUserRequest) {
    return this.httpClient.post<any>(url, loginUserRequest).subscribe({
      next: (response) => {
        this.handleSuccessfulLogin(loginUserRequest, response);
      },
      error: () => {
        this.handleFailedLogin();
      }
    });
  }

  private handleSuccessfulLogin(loginUserRequest: LoginUserRequest, response: any) {
    this.jwtService.setJwt(response.token);
    this.loggedIn.next(true);
    this.loggedInUserSubject.next(loginUserRequest.email);
    this.router.navigate([AuthService.SUCCESS_LOGIN_REDIRECT_URL]);
  }

  private handleFailedLogin() {
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }
}
