import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import {MovieService} from "../../../../../../../movie/services/movie.service";
import {CinemaService} from "../../../../../../../cinema/services/cinema.service";

@Component({
  selector: 'app-screening-form-frame',
  templateUrl: './screening-form-frame.component.html',
  styleUrls: ['./screening-form-frame.component.scss']
})
export class ScreeningFormFrameComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  movieTitles !: string[];
  screeningRoomsNames !: string[];

  filteredTitles!: Observable<string[]>;
  filteredScreeningRooms!: Observable<string[]>;

  constructor(
    private readonly movieService: MovieService,
    private readonly cinemaService: CinemaService
  ) {
  }

  get movieTitleControl() {
    return this.formGroup.get('movieTitle') as FormControl;
  }

  get startTimeControl(): FormControl {
    return this.formGroup.get('startTime') as FormControl;
  }

  get endTimeControl(): FormControl {
    return this.formGroup.get('endTime') as FormControl;
  }

  get screeningRoomControl(): FormControl {
    return this.formGroup.get('screeningRoom') as FormControl;
  }

  ngOnInit() {
    this.movieService.getAllMovieTitles().subscribe(
      titles => {
        this.movieTitles = titles;
        this.setupFilteredTitlesObservable();
      }
    );

    this.cinemaService.getAllScreeningRoomNames().subscribe(
      screeningRooms => {
        this.screeningRoomsNames = screeningRooms;
        this.setupFilteredScreeningRoomsObservable();
      }
    );
  }

  displayTitleFn(title: string): string {
    return title && title ? title : '';
  }

  displayScreeningRoomNameFn(screeningRooName: string): string {
    return screeningRooName && screeningRooName ? screeningRooName : '';
  }

  private setupFilteredTitlesObservable() {
    this.filteredTitles = this.movieTitleControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const title = typeof value === 'string' ? value : value?.title;
        return title ? this._filter_titles(title as string) : this.movieTitles.slice();
      }),
    );
  }

  private setupFilteredScreeningRoomsObservable() {
    this.filteredScreeningRooms = this.screeningRoomControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const room = typeof value === 'string' ? value : value?.room;
        return room ? this._filter_screeningRoomNames(room as string) : this.screeningRoomsNames.slice();
      }),
    );
  }

  private _filter_titles(name: string): string[] {
    const filterValue = name.toLowerCase();
    return this.movieTitles.filter(option => option.toLowerCase().includes(filterValue));
  }

  private _filter_screeningRoomNames(name: string): string[] {
    const filterValue = name.toLowerCase();
    return this.screeningRoomsNames.filter(option => option.toLowerCase().includes(filterValue));
  }
}
