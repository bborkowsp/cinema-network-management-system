import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NavLink} from "./components/drawer/drawer.component";
import {AuthService} from "../user/services/auth.service";

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
      path: '/home',
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
    {
      label: 'Cinema managers',
      path: '/cinema-managers',
      icon: 'people'
    },
  ];


  constructor(
    private readonly router: Router,
    private readonly authService: AuthService
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

  protected handleLogoutButtonClick() {
    this.authService.logout();
  }
}
