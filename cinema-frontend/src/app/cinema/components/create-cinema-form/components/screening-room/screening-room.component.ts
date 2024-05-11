import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm} from "@angular/forms";
import {CreateScreeningRoomRequest} from "../../../../dtos/request/create-screening-room.request";
import {ProjectionTechnologyService} from "../../../../../projection-technology/services/projection-technology.service";
import {map, Observable} from "rxjs";
import {
  ProjectionTechnologyResponse
} from "../../../../../projection-technology/dtos/response/projection-technology.response";
import {CreateSeatRequest} from "../../../../dtos/request/create-seat.request";

@Component({
  selector: 'app-screening-room',
  templateUrl: './screening-room.component.html',
  styleUrls: ['./screening-room.component.scss']
})
export class ScreeningRoomComponent implements OnInit {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formGroup!: FormGroup;

  projectionTechnologies!: Observable<string[]>;
  rows: number = 0;
  columns: number = 0;
  name: string = '';
  currentScreeningRoom: CreateSeatRequest[][] = [];
  seatingPlan: CreateScreeningRoomRequest[] = [];
  screeningRooms: { index: number, rows: number, columns: number }[] = [];
  showCurrentScreeningRoom: boolean = true;
  selectedProjectionTechnologies: ProjectionTechnologyResponse[] = [];

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
  ) {
  }

  get screeningRoomsControl(): FormControl {
    return this.formGroup.get('screeningRooms') as FormControl;
  }

  ngOnInit() {
    this.projectionTechnologies = this.getOnlyTechnologyNames(this.projectionTechnologyService.getAllProjectionTechnologies());
  }

  createSeatingGrid() {
    this.emptyGrid()

    this.showCurrentScreeningRoom = true;
    for (let rowNumber = 0; rowNumber < this.rows; rowNumber++) {
      const row: CreateSeatRequest[] = [];
      for (let columnNumber = 0; columnNumber < this.columns; columnNumber++) {
        row.push(new CreateSeatRequest(rowNumber, columnNumber, 'STANDARD'));
      }
      this.currentScreeningRoom.push(row);
    }
  }

  saveScreeningRoom() {
    this.showCurrentScreeningRoom = false;
    console.log(this.selectedProjectionTechnologies)
    this.seatingPlan.push(new CreateScreeningRoomRequest(
      this.name,
      this.currentScreeningRoom,
      this.selectedProjectionTechnologies
    ));
    this.screeningRooms.push({index: this.screeningRooms.length + 1, rows: this.rows, columns: this.columns});

    this.rows = 0;
    this.columns = 0;
    this.currentScreeningRoom = [];
    this.selectedProjectionTechnologies = [];
    this.screeningRoomsControl.setValue(this.seatingPlan);
  }

  handleStandardClicked(cell: any) {
    cell.seatZone = 'Standard';
  }

  handleVIPClicked(cell: any) {
    cell.seatZone = 'VIP';
  }

  handlePromoClicked(cell: any) {
    cell.seatZone = 'Promo';
  }

  handleWheelchairClicked(cell: any) {
    cell.seatZone = 'Wheelchair';
  }

  handleCorridorClicked(cell: any) {
    cell.seatZone = 'Corridor';
  }

  deleteScreeningRoom(i: number) {
    this.seatingPlan.splice(i, 1);
    this.screeningRooms.splice(i, 1);
  }

  editScreeningRoom(room: number) {
    this.rows = 0;
    this.columns = 0;
    this.currentScreeningRoom = [];
    this.selectedProjectionTechnologies = [];

    const screeningRoomToEdit = this.seatingPlan[room];
    this.rows = screeningRoomToEdit.seats.length;
    this.columns = screeningRoomToEdit.seats[0].length;
    this.currentScreeningRoom = screeningRoomToEdit.seats;
    this.selectedProjectionTechnologies = screeningRoomToEdit.supportedTechnologies;
    this.showCurrentScreeningRoom = true;
  }

  private emptyGrid() {
    this.currentScreeningRoom = [];
  }

  private getOnlyTechnologyNames(allProjectionTechnologies: Observable<ProjectionTechnologyResponse[]>) {
    return allProjectionTechnologies.pipe(
      map((projectionTechnologies: ProjectionTechnologyResponse[]) => {
        return projectionTechnologies.map((projectionTechnology: ProjectionTechnologyResponse) => {
          return projectionTechnology.technology;
        });
      })
    );
  }
}
