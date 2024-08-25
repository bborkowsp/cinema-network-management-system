import {Injectable} from "@angular/core";
import {environment} from "../../assets/environment";
import {LoginUserRequest} from "./login-user.request";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import * as moment from "moment";
import {RegisterUserRequest} from "./register-user.request";

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
    console.log(loginUserRequest)
    return this.httpClient.post<any>(url, loginUserRequest).subscribe(
      (response) => {
        const token = response.token;
        console.log(token)
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

  register(registerUserRequest: RegisterUserRequest) {
    const url = `${AuthService.usersUrl}/register`;
    console.log(registerUserRequest);
    console.log(url);
    return this.httpClient.post<any>(url, registerUserRequest);
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

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    const expiresAt = JSON.parse(expiration ?? '');
    return moment(expiresAt);
  }
}
