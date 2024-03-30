import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {CinemaService} from "../../services/cinema.service";
import {CinemaTableResponse} from "../../dtos/response/cinema-table.response";
import {Observable} from "rxjs";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.scss']
})
export class CinemaListComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['name', 'cinemaManager', 'numberOfScreeningRooms', 'numberOfAvailableSeats', 'numberOfUnavailableSeats'];
  dataSource: MatTableDataSource<CinemaTableResponse>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private cinemaService: CinemaService
  ) {
    this.dataSource = new MatTableDataSource<CinemaTableResponse>([]);
  }


  ngOnInit() {
    this.getData().subscribe(cinemas => {
      this.dataSource.data = cinemas;
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  private getData(): Observable<CinemaTableResponse[]> {
    return this.cinemaService.getCinemas();
  }

}

