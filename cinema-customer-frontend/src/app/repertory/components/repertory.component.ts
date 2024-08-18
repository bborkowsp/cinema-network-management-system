import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../_shared/services/cinema.service";
import {FormControl} from "@angular/forms";
import {ScreeningService} from "../../_shared/services/screening.service";
import {ScreeningResponse} from "../dtos/response/screening.response";
import {Router} from "@angular/router";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-repertory',
  templateUrl: './repertory.component.html',
  styleUrls: ['./repertory.component.scss']
})
export class RepertoryComponent implements OnInit {
  isLoading: boolean = true;
  cinemaNames: string[] = [];
  repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  cinemaNameControl = new FormControl<string>('Greenholt LLC');
  dateControl: FormControl;

  constructor(
    private readonly cinemaService: CinemaService,
    private readonly router: Router,
    private readonly screeningService: ScreeningService,
    private datePipe: DatePipe
  ) {
    this.dateControl = new FormControl(new Date());
  }

  ngOnInit(): void {
    this.getRepertory()
    this.subscribeToCinemaNameChanges();
    this.subscribeToDateChanges();
    this.cinemaService.getAllCinemaNames().subscribe(
      cinemaNames => {
        this.cinemaNames = cinemaNames;
      }
    );
  }

  getMovieTitles(): string[] {
    return this.repertory ? Object.keys(this.repertory) : [];
  }

  handleBuyTicket(screening: ScreeningResponse) {
    this.router.navigateByUrl(`buy-ticket/${screening.id}`);
  }

  private subscribeToCinemaNameChanges() {
    this.cinemaNameControl.valueChanges.subscribe(
      value => {
        this.isLoading = true;
        this.screeningService.getRepertoryAtSpecificDate(value, this.dateControl.value).subscribe(
          repertory => {
            this.repertory = this.groupRepertoryByMovie(repertory);
            this.isLoading = false;
          }
        )
      }
    );
  }

  private getRepertory() {
    this.isLoading = true;
    this.screeningService.getRepertoryAtSpecificDate(this.cinemaNameControl.value, this.dateControl.value).subscribe(
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

  private subscribeToDateChanges() {
    this.dateControl.valueChanges.subscribe(
      value => {
        this.isLoading = true;
        this.screeningService.getRepertoryAtSpecificDate(this.cinemaNameControl.value, value).subscribe(
          repertory => {
            this.repertory = this.groupRepertoryByMovie(repertory);
            this.isLoading = false;
          }
        )
      }
    );
  }
}
