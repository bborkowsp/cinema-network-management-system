import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../_shared/services/cinema.service";
import {map, Observable, startWith} from "rxjs";
import {FormControl} from "@angular/forms";
import {ScreeningService} from "../../_shared/services/screening.service";
import {ScreeningResponse} from "../dtos/response/screening.response";

@Component({
  selector: 'app-repertory',
  templateUrl: './repertory.component.html',
  styleUrls: ['./repertory.component.scss']
})
export class RepertoryComponent implements OnInit {
  cinemaNames: string[] = [];
  filteredCinemaNames!: Observable<string[]>;
  repertory !: ScreeningResponse[];
  myControl = new FormControl<string>('Jacobi-Bayer');

  constructor(
    private readonly cinemaService: CinemaService,
    private readonly screeningService: ScreeningService,
  ) {
  }

  ngOnInit(): void {
    this.getRepertory()
    this.subscribeToCinemaNameChanges();
    this.cinemaService.getAllCinemaNames().subscribe(
      cinemaNames => {
        this.cinemaNames = cinemaNames;
        this.setupFilteredCinemaNames();
      }
    );
  }

  displayCinemaNameFn(name: string): string {
    return name && name ? name : '';
  }

  private setupFilteredCinemaNames() {
    this.filteredCinemaNames = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value;
        return name ? this._filter(name as string) : this.cinemaNames.slice();
      }),
    );
  }

  private _filter(name: string): string[] {
    const filterValue = name.toLowerCase();
    return this.cinemaNames.filter(option => option.toLowerCase().includes(filterValue));
  }

  private subscribeToCinemaNameChanges() {
    this.myControl.valueChanges.subscribe(
      value => {
        this.screeningService.getRepertory(value).subscribe(
          repertory => {
            this.repertory = repertory;
          }
        )
      }
    );
  }

  private getRepertory() {
    this.screeningService.getRepertory(this.myControl.value).subscribe(
      repertory => {
        this.repertory = repertory;
      }
    )
  }
}
