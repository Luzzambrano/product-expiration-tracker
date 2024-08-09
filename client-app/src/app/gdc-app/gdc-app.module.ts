import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';
import { GdcAuthModule } from './auth/auth.module';
import { GdcAppRoutingModule } from './gdc-app-routing.module';
import { GdcAppComponent } from './gdc-app.component';
import { ThemeModule } from '../@theme/theme.module';
import { GdcComponentsModule } from './components/components.module';
import { GdcDashboardComponent } from './dashboard/dashboard.component';
import { GdcProductsComponent } from './products/products.component';
import { GdcExpirationComponent } from './reports/expiration/expiration.component';

@NgModule({
  imports: [
    GdcAppRoutingModule,
    ThemeModule,
    NbMenuModule,
    GdcComponentsModule,
  ],
  exports: [GdcAuthModule, GdcComponentsModule],
  declarations: [
    GdcAppComponent,
    GdcDashboardComponent,
    GdcProductsComponent,
    GdcExpirationComponent,
  ],
})
export class GdcAppModule {}
