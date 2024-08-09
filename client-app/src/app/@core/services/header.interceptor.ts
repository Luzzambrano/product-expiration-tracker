import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { NbAuthJWTToken, NbAuthService } from '@nebular/auth';
import { take, switchMap } from 'rxjs/operators';

@Injectable()
export class HeaderInterceptor implements HttpInterceptor {
  constructor(private authService: NbAuthService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler,
  ): Observable<HttpEvent<unknown>> {
    return this.authService.getToken().pipe(
      take(1), // Take the latest token value once
      switchMap((token: NbAuthJWTToken) => {
        if (token.isValid()) {
          // Clone the request and add the authorization header
          const cloned = request.clone({
            setHeaders: {
              Authorization: `Bearer ${token.getValue()}`,
            },
          });
          return next.handle(cloned);
        }
        // Pass the request as is if no valid token
        return next.handle(request);
      }),
    );
  }
}
