import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent {
  @Output() logoutButtonClick = new EventEmitter<void>();

  protected handleLogoutButtonClick() {
    this.logoutButtonClick.emit();
  }
}
