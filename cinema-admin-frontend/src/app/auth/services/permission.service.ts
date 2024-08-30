import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Role} from "./roles";

export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionService).canActivate(next, state);
}

@Injectable({
  providedIn: 'root'
})
class PermissionService {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const userRole = this.authService.getUserRoleAsEnum();
    if (!userRole || !this.isAuthorized(next.data['roles'], userRole)) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

  private isAuthorized(expectedRoles: Role[], userRole: Role): boolean {
    return this.authService.isLoggedIn() && expectedRoles.includes(userRole);
  }
}
