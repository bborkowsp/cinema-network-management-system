import {HttpClient} from "@angular/common/http";
import {environment} from "../../../assets/environment";
import {Injectable} from "@angular/core";
import {UserResponse} from "../dtos/response/user.response";
import {Observable} from "rxjs";
import {UpdateCustomerProfileRequest} from "../dtos/request/update-customer-profile.request";

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  static readonly CUSTOMERS_ENDPOINT_URL = `${environment.API_BASE_URL}/users/customers`;

  constructor(private httpClient: HttpClient) {
  }

  getCustomer(): Observable<UserResponse> {
    const url = `${CustomerService.CUSTOMERS_ENDPOINT_URL}`;
    return this.httpClient.get<UserResponse>(url);
  }

  updateCustomerProfile(updateCustomerProfile: UpdateCustomerProfileRequest) {
    const url = `${CustomerService.CUSTOMERS_ENDPOINT_URL}`;
    return this.httpClient.patch(url, updateCustomerProfile);
  }
}

