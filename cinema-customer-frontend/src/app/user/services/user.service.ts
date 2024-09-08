import {HttpClient} from "@angular/common/http";
import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {UserResponse} from "../dtos/response/user.response";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  static readonly USERS_API_URL = `${environment.API_BASE_URL}/users`;

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getCustomer(email: string): Observable<UserResponse> {
    const url = `${UserService.USERS_API_URL}/customer/${email}`;
    return this.httpClient.get<UserResponse>(url);
  }
}
