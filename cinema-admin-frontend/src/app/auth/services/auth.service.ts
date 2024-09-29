import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from "rxjs";
import {environment} from "../../../assets/environment";
import * as moment from "moment";
import {Router} from "@angular/router";
import {jwtDecode} from 'jwt-decode';
import {Role} from "./roles";
import {LoginUserRequest} from "../../user/dtos/request/login-user.request";

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private static readonly AUTH_ENDPOINT_URL = `${environment.API_BASE_URL}/auth`;
  private static readonly TOKEN_LOCALSTORAGE_KEY = 'token';
  private static readonly EXPIRATION_LOCALSTORAGE_KEY = 'expires_at';
  private static readonly LOGIN_PAGE_REDIRECT_URL = '/login';
  private static readonly SUCCESS_LOGIN_REDIRECT_URL = '/home';
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.AUTH_ENDPOINT_URL}/login`;
    this.removeTokenFromLocalStorage();
    this.createLoginHttpRequest(url, loginUserRequest);
  }

  logout() {
    this.loggedIn.next(false);
    this.removeTokenFromLocalStorage();
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }

  isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    return expiration && moment().isBefore(expiration);
  }

  getUserRole(): string {
    const token = localStorage.getItem(AuthService.TOKEN_LOCALSTORAGE_KEY);
    if (token) {
      const decodedToken = this.getDecodedAccessToken(token);
      const authorities = decodedToken.authorities;
      if (authorities && authorities.length > 0) {
        return authorities[0].authority;
      }
    }
    return '';
  }

  getUserRoleAsEnum(): Role | null {
    const role = this.getUserRole();
    if (role === 'ROLE_ADMIN') {
      return Role.ROLE_ADMIN;
    }
    if (role === 'ROLE_CINEMA_NETWORK_MANAGER') {
      return Role.ROLE_CINEMA_NETWORK_MANAGER;
    }
    if (role === 'ROLE_CINEMA_MANAGER') {
      return Role.ROLE_CINEMA_MANAGER;
    }
    return null;
  }

  checkIfLoggedInUserIsCinemaManager(): boolean {
    return this.getUserRole() === 'ROLE_CINEMA_MANAGER';
  }

  getLoggedInUserEmail(): string {
    const token = localStorage.getItem(AuthService.TOKEN_LOCALSTORAGE_KEY);
    if (token) {
      const decodedToken = this.getDecodedAccessToken(token);
      return decodedToken.sub;
    }
    return '';
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
    const token = response.token;
    this.setToken(token);
    this.loggedIn.next(true);
    this.loggedInUserSubject.next(loginUserRequest.email);
    this.router.navigate([AuthService.SUCCESS_LOGIN_REDIRECT_URL]);
  }

  private handleFailedLogin() {
    this.loggedIn.next(false);
    this.router.navigate([AuthService.LOGIN_PAGE_REDIRECT_URL]);
  }

  private getExpiration(): moment.Moment {
    const expiration = localStorage.getItem(AuthService.EXPIRATION_LOCALSTORAGE_KEY);
    return expiration ? moment(JSON.parse(expiration)) : moment();
  }

  private getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
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
}
