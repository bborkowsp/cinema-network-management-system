import {Component, OnInit} from '@angular/core';
import {map, Observable, switchMap, tap} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CinemaService} from "../../services/cinema.service";
import {CinemaResponse} from "../../dtos/response/cinema.response";

@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.scss']
})
export class CinemaDetailsComponent implements OnInit {

  cinemaDetails$!: Observable<CinemaResponse>;
  name: string = '';
  protected isLoading = true;


  constructor(
    private readonly cinemaService: CinemaService,
    private readonly activatedRoute: ActivatedRoute,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.getCinema();
    this.isLoading = false;
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
    this.cinemaDetails$ = this.activatedRoute.paramMap.pipe(
      map((paramMap) => paramMap.get('name')!),
      tap((name) => (this.name = name)),
      switchMap((name) => this.cinemaService.getCinema(name)),
    );
  }
}
