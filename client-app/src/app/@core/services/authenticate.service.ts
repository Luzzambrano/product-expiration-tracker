import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { NbAuthService } from '@nebular/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthenticateService {
  constructor(private readonly httpService: HttpService) {}
  // for login endpoint
  login(data: { userrname: string; password: string }): Observable<any> {
    return this.httpService
      .post<any>(`${environment.gdcApiUrl}/auth/authenticate`, data, {})
      .pipe(
        tap((userrname: any) => data),
        catchError((err) => throwError(() => err)),
      );
  }
  // for register endpoint
  register(data: { username: string; password: string }): Observable<any> {
    return this.httpService.post<any>(`${environment.gdcApiUrl}/auth/register`, data, {})
      .pipe(
        tap((userrname: any) => data),
        catchError((err) => throwError(() => err)),
      );
  }

  // for logout endpoint
  logout(): Observable<any> {
    return this.httpService.get<any>(`${environment.gdcApiUrl}/auth/logout`, {})
      .pipe(
        tap((userrname: any) => userrname),
        catchError((err) => throwError(() => err)),
      );
  }
}
