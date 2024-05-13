import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, Observable, throwError} from "rxjs";
import {ErrorNotificationService} from "../services/error-notification.service";
import {Router} from "@angular/router";

@Injectable()
export class ErrorHandlerInterceptor implements HttpInterceptor {
  private static readonly BAD_REQUEST_STATUS = 400;
  private static readonly UNAUTHORIZED_STATUS = 401;
  private static readonly FORBIDDEN_STATUS = 403;
  private static readonly SERVER_ERROR_MESSAGE = 'Unrecognized error';
  private static readonly UNAUTHORIZED_MESSAGE = 'Authentication error';
  private static readonly FORBIDDEN_MESSAGE = 'Forbidden access error';


  constructor(
    private readonly errorNotificationService: ErrorNotificationService,
    private readonly router: Router,
  ) {
  }

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler,
  ): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        this.showErrorNotification(error);
        return throwError(() => error);
      }),
    );
  }

  private showErrorNotification(httpErrorResponse: HttpErrorResponse) {
    switch (httpErrorResponse.status) {
      case ErrorHandlerInterceptor.BAD_REQUEST_STATUS:
        return this.showBadRequestNotification(httpErrorResponse);
      case ErrorHandlerInterceptor.UNAUTHORIZED_STATUS:
        this.router.navigate(['/login']);
        return this.showUnauthorizedNotification();
      case ErrorHandlerInterceptor.FORBIDDEN_STATUS:
        this.router.navigate(['/login']);
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

  private showUnauthorizedNotification() {
    this.errorNotificationService.showDialog(ErrorHandlerInterceptor.UNAUTHORIZED_MESSAGE);
  }

  private showForbiddenNotification() {
    this.errorNotificationService.showDialog(ErrorHandlerInterceptor.FORBIDDEN_MESSAGE);
  }

}
