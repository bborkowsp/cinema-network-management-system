import {Injectable} from "@angular/core";
import {Role} from "../enums/role";
import {JwtService} from "./jwt.service";

@Injectable({
  providedIn: 'root'
})
export class UserRoleService {
  constructor(
    private jwtService: JwtService
  ) {
  }

  getCurrentUserRole(): string {
    const token = this.jwtService.getJwt();
    if (token) {
      const decodedJwt = this.jwtService.decodeJwt(token);
      const authorities = decodedJwt.authorities;
      if (authorities && authorities.length > 0) {
        return authorities[0].authority;
      }
    }
    return '';
  }

  getUserRoleAsEnum(): Role | null {
    const role = this.getCurrentUserRole();
    if (role === Role.ROLE_ADMIN) {
      return Role.ROLE_ADMIN;
    }
    if (role === Role.ROLE_CINEMA_NETWORK_MANAGER) {
      return Role.ROLE_CINEMA_NETWORK_MANAGER;
    }
    if (role === Role.ROLE_CINEMA_MANAGER) {
      return Role.ROLE_CINEMA_MANAGER;
    }
    return null;
  }

  isCinemaManager(): boolean {
    return this.getCurrentUserRole() === Role.ROLE_CINEMA_MANAGER;
  }
}
