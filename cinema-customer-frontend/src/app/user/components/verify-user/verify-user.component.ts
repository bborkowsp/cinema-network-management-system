import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../assets/environment";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-verify-user',
  templateUrl: './verify-user.component.html',
  styleUrls: ['./verify-user.component.scss']
})
export class VerifyUserComponent implements OnInit {
  static readonly accountVerificationUrl = `${environment.API_BASE_URL}/verify-account`;


  constructor(
    private readonly route: ActivatedRoute,
    private readonly httpClient: HttpClient
  ) {
  }

  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
    const url = `${VerifyUserComponent.accountVerificationUrl}?token=${token}`;
    this.httpClient.get(url)
      .subscribe({
        next: () => {
          console.log('User registered')
        },
        error: () => {
          console.log('Error registering user')
        }
      })
  }
}
