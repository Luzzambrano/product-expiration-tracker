import { ExtraOptions, RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  NbAuthComponent,
  NbLoginComponent,
  NbLogoutComponent,
  NbRegisterComponent,
} from '@nebular/auth';
import { GdcAuthGuard } from './gdc-app/auth/auth.guard';

export const routes: Routes = [
  {
    path: 'gdcapp',
    canActivate: [GdcAuthGuard],
    loadChildren: () =>
      import('./gdc-app/gdc-app.module').then((m) => m.GdcAppModule),
  },
  {
    path: 'auth',
    component: NbAuthComponent,
    children: [
      {
        path: '',
        component: NbLoginComponent,
      },
      {
        path: 'login',
        component: NbLoginComponent,
      },
      {
        path: 'register',
        component: NbRegisterComponent,
      },
      {
        path: 'logout',
        component: NbLogoutComponent,
      },
    ],
  },
  { path: '', redirectTo: 'gdcapp', pathMatch: 'full' },
  { path: '**', redirectTo: 'gdcapp' },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [RouterModule.forRoot(routes, config)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
