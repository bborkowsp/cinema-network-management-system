import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "../../../assets/environment";
import * as moment from "moment";
import {RegisterUserRequest} from "../dtos/request/register-user.request";
import {LoginUserRequest} from "../dtos/request/login-user.request";
import {Router} from "@angular/router";

interface AuthResult {
  expiresIn: number;
  idToken: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  static readonly usersUrl = `${environment.apiBaseUrl}/auth`;
  public loggedInUserSubject: BehaviorSubject<string> = new BehaviorSubject<string>('');
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  login(loginUserRequest: LoginUserRequest) {
    const url = `${AuthService.usersUrl}/login`;
    console.log(url);
    console.log("--------------------");
    return this.httpClient.post<any>(url, loginUserRequest).subscribe(
      (response) => {
        const token = response.token;
        this.loggedIn.next(true);
        this.loggedInUserSubject.next(loginUserRequest.email);
        localStorage.setItem('token', token);
        this.router.navigate(['/home']);
      },
      (error) => {
        console.log(error);
        this.loggedIn.next(false);
        this.router.navigate(['/login']);
      }
    )
  }

  register(registerUserRequest: RegisterUserRequest): Observable<void> {
    const url = `${AuthService.usersUrl}/register`;
    console.log(url);
    return this.httpClient.post<void>(url, registerUserRequest);
  }


  logout() {
    this.loggedIn.next(false);
    localStorage.removeItem("token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn() {
    console.log(this.getExpiration());
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


  private setSession(authResult: AuthResult) {
    const expiresAt = moment().add(authResult.expiresIn, 'second');

    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  }
}