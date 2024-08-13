import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../_shared/services/cinema.service";
import {FormControl} from "@angular/forms";
import {ScreeningService} from "../../_shared/services/screening.service";
import {ScreeningResponse} from "../dtos/response/screening.response";
import {Router} from "@angular/router";

@Component({
  selector: 'app-repertory',
  templateUrl: './repertory.component.html',
  styleUrls: ['./repertory.component.scss']
})
export class RepertoryComponent implements OnInit {
  isLoading: boolean = true;
  cinemaNames: string[] = [];
  repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  myControl = new FormControl<string>('Stehr Group');

  constructor(
    private readonly cinemaService: CinemaService,
    private readonly router: Router,
    private readonly screeningService: ScreeningService,
  ) {
  }

  ngOnInit(): void {
    this.getRepertory()
    this.subscribeToCinemaNameChanges();
    this.cinemaService.getAllCinemaNames().subscribe(
      cinemaNames => {
        this.cinemaNames = cinemaNames;
      }
    );
  }

  displayCinemaNameFn(name: string): string {
    return name && name ? name : '';
  }

  getMovieTitles(): string[] {
    return this.repertory ? Object.keys(this.repertory) : [];
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.router.navigateByUrl(`buy-ticket/${screening.id}`);
  }

  private subscribeToCinemaNameChanges() {
    this.myControl.valueChanges.subscribe(
      value => {
        this.screeningService.getRepertory(value).subscribe(
          repertory => {
            this.repertory = this.groupRepertoryByMovie(repertory);
          }
        )
      }
    );
  }

  private getRepertory() {
    this.screeningService.getRepertory(this.myControl.value).subscribe(
      repertory => {
        this.repertory = this.groupRepertoryByMovie(repertory);
        this.isLoading = false;
      }
    );
  }

  private groupRepertoryByMovie(repertory: ScreeningResponse[]): { [movieTitle: string]: ScreeningResponse[] } {
    return repertory.reduce((groups, screening) => {
      const movieTitle = screening.movie.title;
      if (!groups[movieTitle]) {
        groups[movieTitle] = [];
      }
      groups[movieTitle].push(screening);
      return groups;
    }, {} as { [movieTitle: string]: ScreeningResponse[] });
  }
}
