import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {NavigationLink} from "../drawer/drawer.component";

@Component({
  selector: 'app-nav-list',
  templateUrl: './navigation-list.component.html',
  styleUrls: ['./navigation-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavigationListComponent {
  @Input({required: true}) navigationLinks!: NavigationLink[];
}
