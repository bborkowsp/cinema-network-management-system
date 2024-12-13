import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, switchMap, throwError} from "rxjs";
import {ErrorNotificationService} from "../services/error-notification.service";
import {Router} from "@angular/router";
import {JwtService} from "../../auth/services/jwt.service";

@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {
  private static readonly BAD_REQUEST_STATUS = 400;
  private static readonly UNAUTHORIZED_STATUS = 401;
  private static readonly FORBIDDEN_STATUS = 403;
  private static readonly NOT_FOUND_STATUS = 404;
  private static readonly SERVER_ERROR_MESSAGE = 'Unrecognized error';
  private static readonly FORBIDDEN_MESSAGE = 'Forbidden access error';
  private static readonly ERROR_REDIRECT_URL = '/login';

  constructor(
    private readonly errorNotificationService: ErrorNotificationService,
    private readonly router: Router,
    private readonly jwtService: JwtService
  ) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler,): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === ErrorHandlerInterceptor.UNAUTHORIZED_STATUS) {
          return this.handleUnauthorizedError(request, next)
        }
        this.handleError(error);
        return throwError(() => error);
      }),
    );
  }

  private handleError(httpErrorResponse: HttpErrorResponse) {
    switch (httpErrorResponse.status) {
      case ErrorHandlerInterceptor.BAD_REQUEST_STATUS:
        return this.showBadRequestNotification(httpErrorResponse);

      case ErrorHandlerInterceptor.NOT_FOUND_STATUS:
        this.router.navigate([ErrorHandlerInterceptor.ERROR_REDIRECT_URL]);
        return this.showBadRequestNotification(httpErrorResponse);

      case ErrorHandlerInterceptor.FORBIDDEN_STATUS:
        this.router.navigate([ErrorHandlerInterceptor.ERROR_REDIRECT_URL]);
        this.showForbiddenNotification();
        return

      default:
        return this.showServerErrorNotification();
    }
  }

  private showServerErrorNotification() {
    this.showErrorDialog(ErrorHandlerInterceptor.SERVER_ERROR_MESSAGE);
  }

  private showBadRequestNotification(errorResponse: HttpErrorResponse) {
    const errorMessage = errorResponse.error.errors[0];
    this.showErrorDialog(errorMessage);
  }

  private showErrorDialog(errorMessage: any) {
    this.errorNotificationService.showDialog(errorMessage);
  }

  private showForbiddenNotification() {
    this.errorNotificationService.showDialog(ErrorHandlerInterceptor.FORBIDDEN_MESSAGE);
  }

  private handleUnauthorizedError(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.jwtService.requestForJwtRefreshToken().pipe(
      switchMap((newToken) => {
        const clonedRequest = request.clone({
          setHeaders: {
            'X-Authorization': `Bearer ${newToken}`
          }
        });
        return next.handle(clonedRequest);
      }),
      catchError((err) => {
        this.router.navigate([ErrorHandlerInterceptor.ERROR_REDIRECT_URL]);
        return throwError(() => err);
      })
    );
  }
}
