import {HttpClient} from "@angular/common/http";
import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {UserResponse} from "../dtos/response/user.response";
import {Observable} from "rxjs";
import {UpdateCustomerProfileRequest} from "../dtos/request/update-customer-profile.request";
import {UpdatePasswordRequest} from "../dtos/request/update-password.request";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  static readonly USERS_API_URL = `${environment.API_BASE_URL}/users`;

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getCustomer(): Observable<UserResponse> {
    const url = `${UserService.USERS_API_URL}/customer`;
    return this.httpClient.get<UserResponse>(url);
  }

  updateCustomerProfile(updateCustomerProfile: UpdateCustomerProfileRequest) {
    const url = `${UserService.USERS_API_URL}/customer`;
    return this.httpClient.patch(url, updateCustomerProfile);
  }

  updatePassword(updatePassword: UpdatePasswordRequest) {
    const url = `${UserService.USERS_API_URL}/customer/update-password`;
    return this.httpClient.patch(url, updatePassword);
  }
}
