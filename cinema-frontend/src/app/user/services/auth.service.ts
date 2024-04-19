import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginUserRequest} from "../dtos/request/LoginUserRequest";
import {Observable, shareReplay, tap} from "rxjs";
import {environment} from "../../../assets/environment";
import * as moment from "moment";

interface AuthResult {
  expiresIn: number;
  idToken: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  static readonly usersUrl = `${environment.apiBaseUrl}/users`;

  constructor(private httpClient: HttpClient) {
  }

  login(loginUserRequest: LoginUserRequest): Observable<AuthResult> {
    const url = `${AuthService.usersUrl}/login`;
    return this.httpClient.post<AuthResult>(url, loginUserRequest)
      .pipe(
        tap(authResult => this.setSession(authResult)),
        shareReplay()
      );
  }


  logout() {
    localStorage.removeItem("id_token");
    localStorage.removeItem("expires_at");
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem("expires_at");
    const expiresAt = JSON.parse(expiration || "null"); // Handle null value
    return moment(expiresAt);
  }


  private setSession(authResult: AuthResult) {
    const expiresAt = moment().add(authResult.expiresIn, 'second');

    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  }
}
