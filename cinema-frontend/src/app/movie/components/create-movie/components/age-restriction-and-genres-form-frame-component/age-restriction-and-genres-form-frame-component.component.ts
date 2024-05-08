import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {MovieService} from "../../../../services/movie.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-age-restriction-and-genres-form-frame-component',
  templateUrl: './age-restriction-and-genres-form-frame-component.component.html',
  styleUrls: ['./age-restriction-and-genres-form-frame-component.component.scss']
})
export class AgeRestrictionAndGenresFormFrameComponentComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;
  genres!: Observable<string[]>;
  ageRestrictions!: Observable<string[]>;

  constructor(
    private readonly movieService: MovieService,
  ) {
  }

  get ageRestrictionControl(): FormControl {
    return this.formGroup.get('ageRestriction') as FormControl;
  }

  get genresControl(): FormControl {
    return this.formGroup.get('genres') as FormControl;
  }

  ngOnInit(): void {
    this.genres = this.movieService.getGenres();
    this.ageRestrictions = this.movieService.getAgeRestrictions();
  }
}
