import {ChangeDetectionStrategy, Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToolbarComponent {
  @Output() toggleDrawerButtonClick = new EventEmitter<void>();
  @Output() logoutButtonClick = new EventEmitter<void>();

  protected handleToggleDrawerButtonClick() {
    this.toggleDrawerButtonClick.emit();
  }

  protected handleLogoutButtonClick() {
    this.logoutButtonClick.emit();
  }
}
