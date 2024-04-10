import {ChangeDetectionStrategy, Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToolbarComponent {
  @Output() toggleDrawerButtonClick = new EventEmitter<void>();

  handleToggleDrawerButtonClick() {
    this.toggleDrawerButtonClick.emit();
  }
}
