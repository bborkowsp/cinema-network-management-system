import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../services/cinema.service";
import {CinemaListResponse} from "../../dtos/response/cinema-list.response";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDeletionCinemaDialog} from "../confirm-deletion-cinema-dialog/confirm-deletion-cinema-dialog.component";
import {TableColumn} from "../../../_shared/components/generic-table/generic-table.component";

@Component({
  selector: 'app-cinema-table',
  templateUrl: './cinema-table.component.html',
  styleUrls: ['./cinema-table.component.scss']
})
export class CinemaTableComponent implements OnInit {
  displayedColumns: TableColumn[] = [
    {columnDefinition: 'options', header: 'Options', isOptionsColumn: true},
    {columnDefinition: 'name', header: 'Name'},
    {columnDefinition: 'cinemaManager', header: 'Cinema Manager'},
    {columnDefinition: 'numberOfScreeningRooms', header: 'Number of Screening Rooms'},
    {columnDefinition: 'numberOfAvailableSeats', header: 'Number of Available Seats'},
    {columnDefinition: 'numberOfUnavailableSeats', header: 'Number of Unavailable Seats'},
  ]
  cinemas: CinemaListResponse[] = [];
  isLoading = true;

  constructor(
    private cinemaService: CinemaService,
    private readonly router: Router,
    private readonly dialog: MatDialog,
  ) {
  }

  ngOnInit() {
    this.getData().subscribe(cinemas => {
      this.cinemas = cinemas;
      this.isLoading = false;
    });
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

