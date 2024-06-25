import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {Role} from "./roles";

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
    if (userRole === null) {
      this.router.navigate(['/login']);
      return false;
    }
    const expectedRoles: Role[] = next.data['roles'];
    const hasRole: boolean = expectedRoles.some((role) => userRole === role);
    if (!this.authService.isLoggedIn || !hasRole) {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}

export const AuthGuard: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
  return inject(PermissionService).canActivate(next, state);
}



