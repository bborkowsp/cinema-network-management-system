import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {NavLink} from "../drawer/drawer.component";

@Component({
  selector: 'app-nav-list',
  templateUrl: './nav-list.component.html',
  styleUrls: ['./nav-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavListComponent {
  @Input({required: true}) navLinks!: NavLink[];
}
