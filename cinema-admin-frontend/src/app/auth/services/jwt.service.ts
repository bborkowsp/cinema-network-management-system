import {Injectable} from "@angular/core";
import * as moment from "moment/moment";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  private static readonly TOKEN_LOCALSTORAGE_KEY = 'token';
  private static readonly EXPIRATION_LOCALSTORAGE_KEY = 'expires_at';

  getJwtExpiration(): moment.Moment {
    const expiration = localStorage.getItem(JwtService.EXPIRATION_LOCALSTORAGE_KEY);
    return expiration ? moment(JSON.parse(expiration)) : moment();
  }

  removeJwtFromLocalStorage() {
    localStorage.removeItem(JwtService.TOKEN_LOCALSTORAGE_KEY);
    localStorage.removeItem(JwtService.EXPIRATION_LOCALSTORAGE_KEY);
  }

  setJwt(token: string) {
    const decodedToken = this.decodeJwt(token);
    if (decodedToken) {
      const expiresAt = decodedToken.exp * 1000;
      localStorage.setItem(JwtService.TOKEN_LOCALSTORAGE_KEY, token);
      localStorage.setItem(JwtService.EXPIRATION_LOCALSTORAGE_KEY, JSON.stringify(expiresAt));
    }
  }

  getJwt(): string | null {
    return localStorage.getItem(JwtService.TOKEN_LOCALSTORAGE_KEY);
  }

  decodeJwt(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }

  isJwtExpired(): boolean {
    const expiration = this.getJwtExpiration();
    return expiration ? moment().isAfter(expiration) : true;
  }
}
