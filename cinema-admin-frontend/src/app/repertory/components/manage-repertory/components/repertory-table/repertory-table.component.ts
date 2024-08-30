import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ScreeningResponse} from "../../../../dtos/screening.response";

@Component({
  selector: 'app-repertory-table-component',
  templateUrl: './repertory-table.component.html',
  styleUrls: ['./repertory-table.component.scss'],
})
export class RepertoryTableComponent {
  @Input({required: true}) screenings!: ScreeningResponse[];
  @Output() handleDelete = new EventEmitter<ScreeningResponse>();
  @Output() handleEdit = new EventEmitter<ScreeningResponse>();
  @Output() handleAdd = new EventEmitter<string>();
  protected displayedColumns = ['options', 'movie', 'startTime', 'endTime'];

  handleDeleteScreening(screening: ScreeningResponse) {
    this.handleDelete.emit(screening);
  }

  handleEditScreening(screening: ScreeningResponse) {
    this.handleEdit.emit(screening);
  }
}
