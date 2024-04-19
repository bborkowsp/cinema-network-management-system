import {Injectable} from '@angular/core';
import {environment} from '../../../assets/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginUserRequest} from "../dtos/request/LoginUserRequest";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  static readonly usersUrl = `${environment.apiBaseUrl}/users`;

  constructor(private readonly httpClient: HttpClient) {
  }


  loginUser(loginUserRequest: LoginUserRequest): Observable<void> {
    const url = `${UserService.usersUrl}/login`;
    return this.httpClient.post<void>(url, loginUserRequest);
  }


}
