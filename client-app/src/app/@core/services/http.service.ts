import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}

  options = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  get<T>(endPoint: string): Observable<T> {
    return this.http.get<T>(endPoint, this.options).pipe(
      tap((response: any) => response),
      catchError((err) => throwError(() => err)),
    );
  }

  post<T>(endPoint: string, body: any): Observable<T> {
    return this.http.post<T>(endPoint, body, this.options).pipe(
      tap((response: any) => response),
      catchError((err) => throwError(() => err)),
    );
  }

  patch<T>(endPoint: string, body: any): Observable<T> {
    return this.http.patch<T>(endPoint, body, this.options).pipe(
      tap((response: any) => response),
      catchError((err) => throwError(() => err)),
    );
  }
}
