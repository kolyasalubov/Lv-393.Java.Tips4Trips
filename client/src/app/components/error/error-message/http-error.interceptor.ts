import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Injectable} from "@angular/core";
import { Router } from "@angular/router";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  constructor (private roter: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request)
      .pipe(
        retry(1),
        catchError((error: HttpErrorResponse) => {
          let errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            // client-side error
            errorMessage = `Error: ${error.error.message}`;
          } else {
            // server-side error
              errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
          }
          if (parseInt(error.status.toString()) < 500) {
            this.roter.navigate(['/error/', errorMessage]);
          }
          return throwError(errorMessage);
        })
      )
  }
}
