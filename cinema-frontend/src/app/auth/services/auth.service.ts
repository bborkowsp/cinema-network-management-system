import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../../assets/environment";
import * as moment from "moment";
import {Router} from "@angular/router";
import {jwtDecode} from 'jwt-decode';
import {Role} from "./roles";
import {LoginUserRequest} from "../../user/dtos/request/login-user.request";
import {RegisterUserRequest} from "../../user/dtos/request/register-user.request";

interface AuthResult {
  expiresIn: number;
  idToken: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  static readonly usersUrl = `${environment.API_BASE_URL}/auth`;
  public loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.usersUrl}/login`;
    localStorage.removeItem("token");
    localStorage.removeItem("expires_at");
    return this.httpClient.post<any>(url, loginUserRequest).subscribe(
      (response) => {
        const token = response.token;
        this.loggedIn.next(true);
        this.loggedInUserSubject.next(loginUserRequest.email);
        localStorage.setItem('token', token);
        this.router.navigate(['/home']);
      },
      (error) => {
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
      }
    )
  }

  register(registerUserRequest: RegisterUserRequest): Observable<void> {
    this.logout();
    const url = `${AuthService.usersUrl}/register`;
    return this.httpClient.post<void>(url, registerUserRequest);
  }


  logout() {
    this.loggedIn.next(false);
    localStorage.removeItem("token");
    localStorage.removeItem("expires_at");
    this.router.navigate(['/login']);
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    const expiresAt = JSON.parse(expiration ?? '');
    return moment(expiresAt);
  }

  getUserRole(): string {
    const token = localStorage.getItem('token');
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
    if (role === 'ADMIN') {
      return Role.ADMIN;
    }
    if (role === 'CINEMA_NETWORK_MANAGER') {
      return Role.CINEMA_NETWORK_MANAGER;
    }
    if (role === 'CINEMA_MANAGER') {
      return Role.CINEMA_MANAGER;
    }
    return null;
  }

  checkIfLoggedInUserIsCinemaManager(): boolean {
    return this.getUserRole() === 'CINEMA_MANAGER';
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }

  getLoggedInUserEmail(): string {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = this.getDecodedAccessToken(token);
      return decodedToken.sub;
    }
    return '';
  }
}
