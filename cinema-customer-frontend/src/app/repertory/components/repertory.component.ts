import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {ScreeningResponse} from "../dtos/response/screening.response";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {AuthService} from "../../auth/service/auth.service";
import {DialogBuyTicketComponent} from "./screening-list/dialog-buy-ticket/dialog-buy-ticket.component";
import {ScreeningService} from "../services/screening.service";
import {CinemaService} from "../services/cinema.service";

@Component({
  selector: 'app-repertory',
  templateUrl: './repertory.component.html',
  styleUrls: ['./repertory.component.scss']
})
export class RepertoryComponent implements OnInit {
  isLoading: boolean = false;
  isCinemaSelected: boolean = false
  cinemaNames: string[] = [];
  repertory: { [movieTitle: string]: ScreeningResponse[] } = {};
  cinemaNameControl = new FormControl<string>('');
  dateControl: FormControl;

  constructor(
    private readonly cinemaService: CinemaService,
    private readonly router: Router,
    private readonly screeningService: ScreeningService,
    private dialog: MatDialog,
    private readonly authService: AuthService
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
    console.log(screening)
    if (this.authService.isLoggedIn()) {
      this.saveScreeningToLocalStorage(screening);
      this.router.navigateByUrl(`buy-ticket/${screening.id}`, {state: {data: screening}});
    } else {
      this.saveScreeningToLocalStorage(screening);
      this.dialog.open(DialogBuyTicketComponent, {
        data: screening
      })
    }
  }

  private subscribeToCinemaNameChanges() {
    this.cinemaNameControl.valueChanges.subscribe(
      value => {
        this.isCinemaSelected = !!value;
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
    if (this.cinemaNameControl.value === '') return;
    this.isCinemaSelected = true;
    this.isLoading = true;
    this.screeningService.getRepertoryAtSpecificDate(this.cinemaNameControl.value, this.dateControl.value).subscribe(
      repertory => {
        this.repertory = this.groupRepertoryByMovie(repertory);
        this.isLoading = false;
      }
    );
  }

  private groupRepertoryByMovie(repertory: ScreeningResponse[]): { [movieTitle: string]: ScreeningResponse[] } {
    if (repertory.length === 0) return {};
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

  private saveScreeningToLocalStorage(screening: ScreeningResponse) {
    localStorage.setItem('screening', JSON.stringify(screening));
  }
}
