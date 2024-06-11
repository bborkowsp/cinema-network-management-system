import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {map, Observable, switchMap, tap} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CinemaService} from "../../services/cinema.service";
import {CinemaResponse} from "../../dtos/response/cinema.response";
import {ScreeningRoomResponse} from "../../../repertory/dtos/screening-room.response";

@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.scss']
})
export class CinemaDetailsComponent implements OnInit {
  cinema$!: Observable<CinemaResponse>;
  name: string = '';
  protected isLoading = true;

  constructor(
    private readonly cinemaService: CinemaService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly changeDetectorRef: ChangeDetectorRef,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.getCinema();
  }

  handleDeleteButtonCinema() {
    this.cinemaService.deleteCinema(this.name).subscribe(() => {
      this.router.navigateByUrl('/cinemas');
    });
  }

  handleEditButtonCinema() {
    this.router.navigateByUrl(`/cinemas/edit/${this.name}`);
  }

  handleGoBackButton() {
    this.router.navigateByUrl('/cinemas');
  }

  private getCinema() {
    this.cinema$ = this.activatedRoute.paramMap.pipe(
      map((paramMap) => paramMap.get('name')!),
      tap((name) => {
        this.name = name
        this.isLoading = false;
        this.changeDetectorRef.detectChanges();
      }),
      switchMap((name) => this.cinemaService.getCinema(name)),
    );
  }

  getSupportedTechnologiesAsString(screeningRoom: ScreeningRoomResponse) {
    return screeningRoom.supportedTechnologies
      .map(supportedTechnology => supportedTechnology.technology)
      .join(', ');
  }
}
