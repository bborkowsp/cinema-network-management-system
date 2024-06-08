import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ScreeningResponse} from "../../../../dtos/screening.response";

@Component({
  selector: 'app-repertory-list-component',
  templateUrl: './repertory-list-component.component.html',
  styleUrls: ['./repertory-list-component.component.scss'],
})
export class RepertoryListComponentComponent implements OnInit {
  @Input() repertory!: ScreeningResponse[];
  @Output() handleDelete = new EventEmitter<ScreeningResponse>();
  @Output() handleEdit = new EventEmitter<ScreeningResponse>();
  @Output() handleAdd = new EventEmitter<string>();

  displayedColumns = ['options', 'movie', 'startTime', 'endTime'];
  screeningsGroupedByScreeningRoom: { [key: string]: ScreeningResponse[] } = {};

  ngOnInit() {
    this.groupScreeningsByScreeningRoom();
  }

  private groupScreeningsByScreeningRoom() {
    this.screeningsGroupedByScreeningRoom = {};
    this.repertory.forEach((screening: ScreeningResponse) => {
      const roomName = screening.screeningRoom.name;
      if (!this.screeningsGroupedByScreeningRoom[roomName]) {
        this.screeningsGroupedByScreeningRoom[roomName] = [];
      }
      this.screeningsGroupedByScreeningRoom[roomName].push(screening);
    });
  }

  protected readonly Object = Object;

  protected handleDeleteScreening(screening: ScreeningResponse) {
    this.handleDelete.emit(screening);
  }

  handleEditScreening(screening: ScreeningResponse) {
    this.handleEdit.emit(screening);
  }

  handleAddScreening(roomName: string) {
    this.handleAdd.emit(roomName);
  }
}
