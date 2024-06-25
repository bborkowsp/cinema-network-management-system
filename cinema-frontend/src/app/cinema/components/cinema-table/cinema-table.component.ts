import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {CinemaService} from "../../services/cinema.service";
import {CinemaListResponse} from "../../dtos/response/cinema-list.response";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeletionCinemaDialog} from "../confirm-deletion-cinema-dialog/confirm-deletion-cinema-dialog.component";

@Component({
  selector: 'app-cinema-table',
  templateUrl: './cinema-table.component.html',
  styleUrls: ['./cinema-table.component.scss']
})
export class CinemaTableComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  displayedColumns: string[] = ['options', 'name', 'cinemaManager', 'numberOfScreeningRooms', 'numberOfAvailableSeats', 'numberOfUnavailableSeats'];
  cinemas$: MatTableDataSource<CinemaListResponse>;
  isLoading = true;

  constructor(
    private cinemaService: CinemaService,
    private readonly router: Router,
    private readonly dialog: MatDialog,
  ) {
    this.cinemas$ = new MatTableDataSource<CinemaListResponse>([]);
  }

  ngOnInit() {
    this.getData().subscribe(cinemas => {
      this.cinemas$.data = cinemas;
      this.isLoading = false;
    });
  }

  ngAfterViewInit() {
    this.cinemas$.paginator = this.paginator;
    this.cinemas$.sort = this.sort;
  }

  handleDelete(cinema: CinemaListResponse) {
    const matDialog = this.dialog.open(ConfirmDeletionCinemaDialog, {
      data: cinema
    })

    matDialog.afterClosed().subscribe({
      next: (result) => {
        if (result) {
          this.cinemaService.deleteCinema(cinema.name).subscribe({
            next: () => this.ngOnInit(),
          });
        }
      },
    });
  }

  handleEdit(cinema: CinemaListResponse) {
    const url = `cinemas/edit/${cinema.name}`;
    this.router.navigateByUrl(url);
  }

  handleShowDetails(cinema: CinemaListResponse) {
    const url = `cinemas/details/${cinema.name}`;
    this.router.navigateByUrl(url);
  }

  private getData(): Observable<CinemaListResponse[]> {
    return this.cinemaService.getCinemas();
  }
}

