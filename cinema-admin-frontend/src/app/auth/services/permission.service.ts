import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Role} from "./roles";


export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionService).canActivate(next, state);
};

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
    const userActualRole = this.authService.getUserRoleAsEnum();
    const expectedRoles: Role[] = next.data['roles'];
    if (
      userActualRole == null ||
      !this.authService.isLoggedIn ||
      !this.checkIfUserHasExpectedRole(expectedRoles, userActualRole)
    ) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }

  private checkIfUserHasExpectedRole(expectedRoles: Role[], userActualRole: Role): boolean {
    return expectedRoles.some((role) => userActualRole === role);
  }
}
