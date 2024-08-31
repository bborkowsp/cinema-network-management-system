import {Injectable} from "@angular/core";
import {environment} from "../../assets/environment";
import {LoginUserRequest} from "./login-user.request";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import * as moment from "moment";
import {RegisterUserRequest} from "./register-user.request";
import {jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  static readonly usersUrl = `${environment.API_BASE_URL}/auth`;
  public loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
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

  register(registerUserRequest: RegisterUserRequest) {
    const url = `${AuthService.usersUrl}/register`;
    return this.httpClient.post<any>(url, registerUserRequest);
  }

  logout() {
    this.removeTokenFromLocalStorage();
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    const expiration = this.getExpiration();
    return expiration && moment().isBefore(expiration);
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
