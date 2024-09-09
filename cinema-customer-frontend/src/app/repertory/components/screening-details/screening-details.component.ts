import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ScreeningService} from "../../services/screening.service";
import {ScreeningDetailsResponse} from "../../dtos/response/screening-details.response";

@Component({
  selector: 'app-screening-details',
  templateUrl: './screening-details.component.html',
  styleUrl: './screening-details.component.scss'
})
export class ScreeningDetailsComponent implements OnInit {
  screeningDetailsResponse!: ScreeningDetailsResponse;

  constructor(
    private readonly activatedRoute: ActivatedRoute,
    private readonly screeningService: ScreeningService
  ) {
  }

  ngOnInit() {
    this.loadScreeningDetails();
  }

  private loadScreeningDetails(): void {
    const title = this.activatedRoute.snapshot.params['title'] as string;
    const date = this.activatedRoute.snapshot.params['date'] as string;
    const screeningDetails$ = this.screeningService.getScreeningDetails(title, date);
    screeningDetails$.subscribe({
      next: (screeningDetailsResponse: ScreeningDetailsResponse) => {
        this.screeningDetailsResponse = screeningDetailsResponse
      },
      error: () => {
      }
    })
  }
}
