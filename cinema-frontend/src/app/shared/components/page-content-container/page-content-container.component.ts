import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-page-content-container',
  templateUrl: './page-content-container.component.html',
  styleUrls: ['./page-content-container.component.scss']
})
export class PageContentContainerComponent {
  @Input() pageTitle!: string;
  @Input() isLoading = false;
  @Input() routerLink = '';

  @Input() hasDetailsActionButtons = false;
  @Input() hasGoBackActionButton = false;
  @Input() hasCreateNewButton = false;

  @Input() buttonMatToolTip = '';
  @Input() buttonEditMatToolTip = '';
  @Input() buttonDeleteMatToolTip = '';

  @Input() floatingButtonDeleteAriaLabel = '';
  @Input() floatingButtonEditAriaLabel = '';

  @Output() floatingButtonDeleteAction: EventEmitter<void> = new EventEmitter();
  @Output() floatingButtonEditAction: EventEmitter<void> = new EventEmitter();
  @Output() goBackButtonAction: EventEmitter<void> = new EventEmitter();
}
