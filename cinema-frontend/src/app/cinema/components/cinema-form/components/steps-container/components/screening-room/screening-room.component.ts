import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ScreeningRoomResponse} from "../../../../../../../repertory/dtos/screening-room.response";
import {SeatResponse} from "../../../../../../dtos/response/seat.response";
import {map, Observable} from "rxjs";
import {
  ProjectionTechnologyService
} from "../../../../../../../projection-technology/services/projection-technology.service";
import {
  ProjectionTechnologyNameResponse
} from "../../../../../../../projection-technology/dtos/response/projection-technology-name.response";

@Component({
  selector: 'app-screening-room',
  templateUrl: './screening-room.component.html',
  styleUrls: ['./screening-room.component.scss']
})
export class ScreeningRoomComponent implements OnInit, OnChanges {
  @Input({required: true}) form!: FormGroupDirective | NgForm;
  @Input({required: true}) formArray!: FormArray;
  rowsNumberControl = new FormControl(1, Validators.required);
  columnsNumberControl = new FormControl(1, Validators.required);
  supportedTechnolgiesControl = new FormControl([], Validators.required);
  protected allScreeningRooms: ScreeningRoomResponse[] = [];
  projectionTechnologies!: Observable<string[]>;

  createScreeningRoomFormGroup!: FormGroup;
  currentScreeningRoom: SeatResponse[][] = [];
  currentSupportedTechnologies: ProjectionTechnologyNameResponse[] = [];
  showCurrentScreeningRoom = false;
  currentEditedContactDetailIndex: number = -1;

  constructor(
    private readonly projectionTechnologyService: ProjectionTechnologyService,
  ) {
  }

  ngOnInit() {
    this.projectionTechnologies = this.getOnlyTechnologyNames(this.projectionTechnologyService.getAllProjectionTechnologies());
    this.updateScreeningRooms();
    this.createScreeningRoomFormGroup = this.createFormGroup();
  }

  private getOnlyTechnologyNames(allProjectionTechnologies: Observable<ProjectionTechnologyNameResponse[]>) {
    return allProjectionTechnologies.pipe(
      map((projectionTechnologies: ProjectionTechnologyNameResponse[]) => {
        return projectionTechnologies.map((projectionTechnology: ProjectionTechnologyNameResponse) => {
          return projectionTechnology.technology;
        });
      })
    );
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['formArray']) {
      this.updateScreeningRooms();
    }
  }

  private updateScreeningRooms() {
    this.allScreeningRooms = this.formArray.value as ScreeningRoomResponse[];
  }

  editScreeningRoom(i: number) {
    this.currentEditedContactDetailIndex = i;
    this.showCurrentScreeningRoom = true;
    this.currentScreeningRoom = (this.formArray.at(i).get('seats') as FormArray).value;
    this.currentSupportedTechnologies = (this.formArray.at(i).get('supportedTechnologies') as FormArray).value;
    this.updateRowsAndColumnsNumberControl();
  }

  deleteScreeningRoom(i: number) {
    this.formArray.removeAt(i);
    this.updateScreeningRooms();
    this.currentEditedContactDetailIndex = -1;
  }

  saveScreeningRoom() {
    const screeningRoom = this.createFilledScreeningRoomGroup();
    if (this.currentEditedContactDetailIndex === -1) {
      this.formArray.push(screeningRoom);
      this.createScreeningRoomFormGroup.reset();
    } else {
      this.formArray.at(this.currentEditedContactDetailIndex).patchValue(screeningRoom);
    }
    this.updateScreeningRooms();
    this.rowsNumberControl.setValue(1);
    this.columnsNumberControl.setValue(1);
    this.emptyGrid();
    this.currentEditedContactDetailIndex = -1;
  }

  private emptyGrid() {
    this.currentScreeningRoom = [];
  }

  private createFilledScreeningRoomGroup(): FormGroup {
    this.currentSupportedTechnologies = this.supportedTechnolgiesControl.value as ProjectionTechnologyNameResponse[];
    let supportedTechnologiesAsStrings = this.supportedTechnolgiesControl.value as string[];

    return new FormGroup({
        name: new FormControl(this.nameControl.value),
        seats: new FormArray(
          this.currentScreeningRoom.map(
            row => new FormArray(
              row.map(
                seat => new FormGroup({
                  seatRow: new FormControl(seat.seatRow),
                  seatColumn: new FormControl(seat.seatColumn),
                  seatZone: new FormControl(seat.seatZone),
                  seatType: new FormControl(seat.seatType),
                })
              )
            )
          )
        ),
        supportedTechnologies: new FormArray(
          supportedTechnologiesAsStrings.map((technology: string) => new FormGroup({
            technology: new FormControl(technology),
          })))
      }
    );
  }


  private createFormGroup(): FormGroup {
    return new FormGroup({
      name: new FormControl(''),
      seats: new FormArray([
        new FormArray([
          new FormGroup({
            seatRow: new FormControl(''),
            seatColumn: new FormControl(''),
            seatZone: new FormControl(''),
            seatType: new FormControl(''),
          })
        ])
      ]),
      supportedTechnologies: new FormArray([
        new FormGroup({
          technology: new FormControl('', Validators.required),
        })
      ])
    });
  }

  private updateRowsAndColumnsNumberControl() {
    const seatsControl = this.seatsControl;
    this.rowsNumberControl.setValue(seatsControl.length);
    this.columnsNumberControl.setValue(seatsControl.at(0).value.length);
    const projectionTechnologies = this.supportedTechnologiesControl;
    this.supportedTechnolgiesControl.setValue(projectionTechnologies.value.map((technology: any) => technology.technology));
  }

  get nameControl(): FormControl {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('name') as FormControl;
    }
    return this.createScreeningRoomFormGroup.get('name') as FormControl;
  }

  get supportedTechnologiesControl(): FormGroup {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('supportedTechnologies') as FormGroup;
    }
    return this.createScreeningRoomFormGroup.get('supportedTechnologies') as FormGroup;
  }

  get seatsControl(): FormArray {
    if (this.currentEditedContactDetailIndex >= 0) {
      return this.formArray.at(this.currentEditedContactDetailIndex).get('seats') as FormArray;
    }
    return this.createScreeningRoomFormGroup.get('seats') as FormArray;
  }

  createGridOfSeats() {
    if (this.currentEditedContactDetailIndex >= 0) {
      this.updateExistingScreeningRoom();
    } else {
      this.createInitialScreeningRoom();
    }
  }

  private updateExistingScreeningRoom() {
    const numberOfRowsInOldScreeningRoom = this.currentScreeningRoom.length;
    const numberOfColumnsInOldScreeningRoom = this.currentScreeningRoom[0].length;

    const newNumberOfRows = this.rowsNumberControl.value;
    const newNumberOfColumns = this.columnsNumberControl.value;

    if (newNumberOfRows != null && newNumberOfColumns != null) {
      if (newNumberOfRows < numberOfRowsInOldScreeningRoom) {
        this.removeExcessRows(newNumberOfRows);
      }

      if (newNumberOfColumns < numberOfColumnsInOldScreeningRoom) {
        this.removeExcessColumns(newNumberOfColumns);
      }

      if (newNumberOfRows > numberOfRowsInOldScreeningRoom) {
        this.addAdditionalRows(newNumberOfRows, newNumberOfColumns);
      }

      if (newNumberOfColumns > numberOfColumnsInOldScreeningRoom) {
        this.addAdditionalColumns(newNumberOfColumns);
      }
    }
  }

  private createInitialScreeningRoom() {
    this.showCurrentScreeningRoom = true;
    const newNumberOfRows = this.rowsNumberControl.value;
    const newNumberOfColumns = this.columnsNumberControl.value;

    this.currentScreeningRoom = [];
    if (newNumberOfRows != null && newNumberOfColumns != null) {
      for (let i = 0; i < newNumberOfRows; i++) {
        const newRow: SeatResponse[] = [];
        for (let j = 0; j < newNumberOfColumns; j++) {
          const newSeat: SeatResponse = {
            seatRow: i + 1,
            seatColumn: j + 1,
            seatZone: 'STANDARD',
            seatType: 'AVAILABLE'
          };
          newRow.push(newSeat);
        }
        this.currentScreeningRoom.push(newRow);
      }
    }
  }

  private removeExcessRows(newNumberOfRows: number) {
    this.currentScreeningRoom.splice(newNumberOfRows);
  }

  private removeExcessColumns(newNumberOfColumns: number) {
    this.currentScreeningRoom.forEach(row => {
      row.splice(newNumberOfColumns);
    });
  }

  private addAdditionalRows(newNumberOfRows: number, newNumberOfColumns: number) {
    const numberOfRowsInOldScreeningRoom = this.currentScreeningRoom.length;
    for (let i = numberOfRowsInOldScreeningRoom; i < newNumberOfRows; i++) {
      const newRow: SeatResponse[] = [];
      for (let j = 0; j < newNumberOfColumns; j++) {
        const newSeat: SeatResponse = {
          seatRow: i + 1,
          seatColumn: j + 1,
          seatZone: 'STANDARD',
          seatType: 'AVAILABLE'
        };
        newRow.push(newSeat);
      }
      this.currentScreeningRoom.push(newRow);
    }
  }

  private addAdditionalColumns(newNumberOfColumns: number) {
    const numberOfColumnsInOldScreeningRoom = this.currentScreeningRoom[0].length;
    this.currentScreeningRoom.forEach(row => {
      for (let j = numberOfColumnsInOldScreeningRoom; j < newNumberOfColumns; j++) {
        const newSeat: SeatResponse = {
          seatRow: row.length + 1,
          seatColumn: j + 1,
          seatZone: 'STANDARD',
          seatType: 'AVAILABLE'
        };
        row.push(newSeat);
      }
    });
  }

  handleStandardClicked(cell: any) {
    cell.seatZone = 'STANDARD';
  }

  handleVIPClicked(cell: any) {
    cell.seatZone = 'VIP';
  }

  handlePromoClicked(cell: any) {
    cell.seatZone = 'PROMO';
  }

  handleWheelchairClicked(cell: any) {
    cell.seatZone = 'WHEELCHAIR';
  }

  handleCorridorClicked(cell: any) {
    cell.seatZone = 'CORRIDOR';
  }

}
