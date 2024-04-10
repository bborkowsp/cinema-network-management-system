import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NavLink} from "./components/drawer/drawer.component";

@Component({
  selector: 'app-scaffold',
  templateUrl: './scaffold.component.html',
  styleUrls: ['./scaffold.component.scss']
})
export class ScaffoldComponent {

  protected isDrawerOpened = false;
  protected readonly navLinks: NavLink[] = [
    {
      label: 'Cinemas',
      path: '/cinemas',
    },
    {
      label: 'Projection technologies',
      path: '/projection-technologies',
    },
  ];


  constructor(
    private readonly router: Router,
  ) {
  }

  protected handleToggleDrawerButtonClick() {
    this.isDrawerOpened = !this.isDrawerOpened;
  }
}
