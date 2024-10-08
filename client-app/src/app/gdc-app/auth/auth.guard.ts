import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { NbAuthService } from '@nebular/auth';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class GdcAuthGuard implements CanActivate {
  constructor(private authService: NbAuthService, private router: Router) {}

  canLoad(): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService.isAuthenticated().pipe(
      tap((authenticated) => {
        if (!authenticated) {
          this.router.navigate(['/auth/login']);
        }
      }),
    );
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ):
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    return this.authService.isAuthenticated().pipe(
      tap((authenticated) => {
        if (!authenticated) {
          this.router.navigate(['/auth/login']);
        }
      }),
    );
  }
}
