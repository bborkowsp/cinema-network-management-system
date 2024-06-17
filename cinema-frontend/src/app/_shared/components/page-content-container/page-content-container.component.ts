import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-page-content-container',
  templateUrl: './page-content-container.component.html',
  styleUrls: ['./page-content-container.component.scss']
})
export class PageContentContainerComponent {
  @Input() pageTitle!: string;
  @Input() isLoading = false;
  @Input() isUserRoleCinemaManager = false;
  @Input() routerLink = '';

  @Input() hasDetailsActionButtons = false;
  @Input() hasGoBackButton = false;
  @Input() hasCreateNewButton = false;

  @Input() buttonMatToolTip = '';
  @Input() editButtonMatToolTip = '';
  @Input() deleteButtonMatToolTip = '';

  @Input() deleteButtonAriaLabel = '';
  @Input() editButtonAriaLabel = '';

  @Output() deleteButtonEventEmitter: EventEmitter<void> = new EventEmitter();
  @Output() editButtonEventEmitter: EventEmitter<void> = new EventEmitter();
  @Output() goBackButtonEventEmitter: EventEmitter<void> = new EventEmitter();
}
