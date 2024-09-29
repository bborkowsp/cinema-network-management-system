import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {JwtService} from "../../auth/services/jwt.service";

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(
    private readonly jwtService: JwtService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.jwtService.getJwt();
    if (token) {
      const cloned = req.clone({
        setHeaders: {
          'X-Authorization': `Bearer ${token}`
        }
      });
      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}




