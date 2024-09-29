import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Role} from "../enums/role";
import {UserRoleService} from "./user-role.service";


export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionService).canActivate(next, state);
};

@Injectable({
  providedIn: 'root'
})
class PermissionService {
  private static readonly CAN_ACTIVATE_DENIED_REDIRECT_URL = '/login';

  constructor(
    private router: Router,
    private authService: AuthService,
    private userRoleService: UserRoleService
  ) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUserRole = this.userRoleService.getUserRoleAsEnum();
    console.log(currentUserRole);
    const expectedRoles: Role[] = next.data['roles'];
    if (
      currentUserRole == null ||
      !this.authService.isLoggedIn() ||
      !this.hasExpectedRole(expectedRoles, currentUserRole)
    ) {
      this.router.navigate([PermissionService.CAN_ACTIVATE_DENIED_REDIRECT_URL]);
      return false;
    }
    return true;
  }

  private hasExpectedRole(expectedRoles: Role[], currentUserRole: Role): boolean {
    return expectedRoles.some((role) => currentUserRole === role);
  }
}
