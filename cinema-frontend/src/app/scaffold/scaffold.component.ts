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
  protected navLinks: NavLink[] = [];

  protected isDrawerOpened = false;

  constructor(
    private readonly router: Router,
    private readonly authService: AuthService
  ) {
    this.setUpNavLinks();
  }

  protected get isUserOnLoginPage() {
    return this.router.url === '/login';
  }

  protected get isUserOnRegisterPage() {
    return this.router.url === '/register';
  }

  protected handleToggleDrawerButtonClick() {
    this.setUpNavLinks()
    this.isDrawerOpened = !this.isDrawerOpened;
  }

  protected handleLogoutButtonClick() {
    this.authService.logout();
  }


  private setUpNavLinks() {
    const userRole = this.authService.getUserRole();
    this.navLinks = [
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
    ];
    if (userRole === 'CINEMA_MANAGER') {
      this.navLinks.push(
        {
          label: 'Repertory',
          path: '/repertory',
          icon: 'event'
        }
      );
    }

    if (userRole === 'CINEMA_NETWORK_MANAGER') {
      this.navLinks.push(
        {
          label: 'Cinema managers',
          path: '/cinema-managers',
          icon: 'people'
        },
        {
          label: 'Projection technologies',
          path: '/projection-technologies',
          icon: 'settings'
        }
      );
    }
  }
}
