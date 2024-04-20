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
      label: 'Home',
      path: '/',
      icon: 'home'
    },
    {
      label: 'Movies',
      path: '/movies',
      icon: 'movie'
    },
    {
      label: 'Cinemas',
      path: '/cinemas',
      icon: 'business'
    },
    {
      label: 'Projection technologies',
      path: '/projection-technologies',
      icon: 'settings'
    },
  ];


  constructor(
    private readonly router: Router,
  ) {
  }

  protected get isUserOnLoginPage() {
    return this.router.url === '/login';
  }

  protected get isUserOnRegisterPage() {
    return this.router.url === '/register';
  }

  protected handleToggleDrawerButtonClick() {
    this.isDrawerOpened = !this.isDrawerOpened;
  }
}
