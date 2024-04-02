import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {CinemaService} from "../../services/cinema.service";
import {CinemaTableResponse} from "../../dtos/response/cinema-table.response";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.scss']
})
export class CinemaListComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['options', 'name', 'cinemaManager', 'numberOfScreeningRooms', 'numberOfAvailableSeats', 'numberOfUnavailableSeats'];
  cinemas$: MatTableDataSource<CinemaTableResponse>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  protected isLoading = true;

  constructor(
    private cinemaService: CinemaService,
    private readonly router: Router
  ) {
    this.cinemas$ = new MatTableDataSource<CinemaTableResponse>([]);
  }

  ngOnInit() {
    this.getData().subscribe(cinemas => {
      this.cinemas$.data = cinemas;
    });
    this.isLoading = false;
  }

  ngAfterViewInit() {
    this.cinemas$.paginator = this.paginator;
    this.cinemas$.sort = this.sort;
  }

  handleDelete(cinema: CinemaTableResponse) {
    this.cinemaService.deleteCinema(cinema.name).subscribe({
      next: () => this.ngOnInit(),
    });
  }

  handleEdit(cinema: CinemaTableResponse) {
    const url = `cinemas/edit/${cinema.name}`;
    this.router.navigateByUrl(url);
  }

  handleShowDetails(cinema: CinemaTableResponse) {
    const url = `cinemas/details/${cinema.name}`;
    this.router.navigateByUrl(url);
  }

  private getData(): Observable<CinemaTableResponse[]> {
    return this.cinemaService.getCinemas();
  }
}

