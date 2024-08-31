import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../../../../assets/environment";

@Component({
  selector: 'app-verify-user',
  templateUrl: './verify-user.component.html',
  styleUrls: ['./verify-user.component.scss']
})
export class VerifyUserComponent implements OnInit {
  static readonly accountVerificationUrl = `${environment.API_BASE_URL}/verify-account`;
  status!: string;
  reason!: string;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly httpClient: HttpClient
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.status = params['status'];
      this.reason = params['reason'];
    });
    const token = this.route.snapshot.queryParamMap.get('token');
    const url = `${VerifyUserComponent.accountVerificationUrl}?token=${token}`;
    this.httpClient.get(url)
      .subscribe({
        next: (response: any) => {
          this.status = response.status;
          this.reason = response.reason;
        },
        error: (error: any) => {
          this.status = error.error.status;
          this.reason = error.error.reason;
        }
      });
  }
}
