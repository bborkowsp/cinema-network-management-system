import {Component, EventEmitter, Input, Output} from '@angular/core';
import {images} from "../../../../assets/environment";

export interface TableColumn {
  columnDefinition: string;
  header: string;
  isOptionsColumn?: boolean;
  isPosterColumn?: boolean;
  nestedValue?: string;
}

@Component({
  selector: 'app-generic-table',
  templateUrl: './generic-table.component.html',
  styleUrl: './generic-table.component.scss'
})
export class GenericTableComponent<T> {
  readonly POSTERS_SERVER_DIRECTORY_URL = `${images.IMAGES_SERVER_DIRECTORY_URL}/posters/`;
  @Input() dataSource: T[] = [];
  @Input() columns: TableColumn[] = [];
  @Input() isUserRoleCinemaManager = false;
  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();
  @Output() showDetails = new EventEmitter<any>();

  get displayedColumns(): string[] {
    return this.columns.map(column => column.columnDefinition);
  }

  onEdit(row: any): void {
    this.edit.emit(row);
  }

  onDelete(row: any): void {
    this.delete.emit(row);
  }

  onShowDetails(row: any): void {
    this.showDetails.emit(row);
  }
}
