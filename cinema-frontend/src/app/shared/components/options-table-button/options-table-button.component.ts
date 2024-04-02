import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-options-table-button',
  templateUrl: './options-table-button.component.html',
  styleUrls: ['./options-table-button.component.scss']
})
export class OptionsTableButtonComponent {
  @Input() row!: any;

  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();
  @Output() showDetails = new EventEmitter<any>();

  handleEdit(): void {
    this.edit.emit(this.row);
  }

  handleDelete(): void {
    this.delete.emit(this.row);
  }

  handleShowDetails(): void {
    this.showDetails.emit(this.row);
  }
}
