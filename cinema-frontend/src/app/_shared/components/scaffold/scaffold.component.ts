import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NavigationLink} from "./components/drawer/drawer.component";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-scaffold',
  templateUrl: './scaffold.component.html',
  styleUrls: ['./scaffold.component.scss']
})
export class ScaffoldComponent {
  navigationLinks: NavigationLink[] = [];
  isDrawerOpened = false;
 
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
    this.navigationLinks = this.getCommonLinks();
    switch (userRole) {
      case 'CINEMA_MANAGER':
        this.navigationLinks.push(...this.getCinemaManagerLinks());
        break;
      case 'CINEMA_NETWORK_MANAGER':
        this.navigationLinks.push(...this.getCinemaNetworkManagerLinks());
        break;
      case 'ADMIN':
        this.navigationLinks.push(...this.getAdminLinks());
        break;
      default:
        break;
    }
  }

  private getCommonLinks(): NavigationLink[] {
    return [
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
        label: 'Projection technologies',
        path: '/projection-technologies',
        icon: 'settings'
      }
    ];
  }

  private getCinemaManagerLinks(): NavigationLink[] {
    return [
      {
        label: 'Repertory',
        path: '/repertory',
        icon: 'event'
      },
    ];
  }

  private getCinemaNetworkManagerLinks(): NavigationLink[] {
    return [
      {
        label: 'Cinema managers',
        path: '/cinema-managers',
        icon: 'people'
      },
      {
        label: 'Cinemas',
        path: '/cinemas',
        icon: 'business'
      }
    ];
  }

  private getAdminLinks(): NavigationLink[] {
    return [
      {
        label: 'Cinemas',
        path: '/cinemas',
        icon: 'business'
      },
      {
        label: 'Users',
        path: '/users',
        icon: 'people'
      }
    ];
  }
}
