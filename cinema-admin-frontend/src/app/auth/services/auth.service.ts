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
  static readonly usersUrl = `${environment.API_BASE_URL}/auth`;
  loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.usersUrl}/login`;
    this.removeTokenFromLocalStorage();
    return this.httpClient.post<any>(url, loginUserRequest).subscribe({
      next: (response) => {
        const token = response.token;
        this.setToken(token);
        this.loggedIn.next(true);
        this.loggedInUserSubject.next(loginUserRequest.email);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
      }
    });
  }

  logout() {
    this.loggedIn.next(false);
    this.removeTokenFromLocalStorage();
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    return expiration && moment().isBefore(expiration);
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
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = this.getDecodedAccessToken(token);
      return decodedToken.sub;
    }
    return '';
  }

  private getExpiration(): moment.Moment {
    const expiration = localStorage.getItem('expires_at');
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
    localStorage.removeItem('token');
    localStorage.removeItem('expires_at');
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
