import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GdcTableComponent } from './gdc-table/gdc-table.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { NbCardModule, NbListModule, NbSelectModule, NbTabsetModule } from '@nebular/theme';
import {
  NbIconModule,
  NbInputModule,
  NbProgressBarModule,
} from '@nebular/theme';
import { ProfitCardComponent } from './profit-card/profit-card.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { ChartModule } from 'angular2-chartjs';
import { StatsCardFrontComponent } from './profit-card/front-side/stats-card-front.component';
import { StatsCardBackComponent } from './profit-card/back-side/stats-card-back.component';
import { StatsAreaChartComponent } from './profit-card/back-side/stats-area-chart.component';
import { StatsBarAnimationChartComponent } from './profit-card/front-side/stats-bar-animation-chart.component';
import { TrafficBackCardComponent } from './traffic-reveal-card/back-side/traffic-back-card.component';
import { TrafficFrontCardComponent } from './traffic-reveal-card/front-side/traffic-front-card.component';
import { TrafficCardsHeaderComponent } from './traffic-reveal-card/traffic-cards-header/traffic-cards-header.component';
import { TrafficBarChartComponent } from './traffic-reveal-card/back-side/traffic-bar-chart.component';
import { TrafficBarComponent } from './traffic-reveal-card/front-side/traffic-bar/traffic-bar.component';
import { TrafficRevealCardComponent } from './traffic-reveal-card/traffic-reveal-card.component';
import { ECommerceChartsPanelComponent } from './charts-panel/charts-panel.component';
import { OrdersChartComponent } from './charts-panel/charts/orders-chart.component';
import { ProfitChartComponent } from './charts-panel/charts/profit-chart.component';
import { ChartPanelHeaderComponent } from './charts-panel/chart-panel-header/chart-panel-header.component';
import { ChartPanelSummaryComponent } from './charts-panel/chart-panel-summary/chart-panel-summary.component';
import { ECommerceLegendChartComponent } from './legend-chart/legend-chart.component';

@NgModule({
  declarations: [
    ChartPanelHeaderComponent,
    ChartPanelSummaryComponent,
    ECommerceChartsPanelComponent,
    ECommerceLegendChartComponent,
    GdcTableComponent,
    OrdersChartComponent,
    ProfitChartComponent,
    ProfitCardComponent,
    StatsBarAnimationChartComponent,
    StatsCardBackComponent,
    StatsCardFrontComponent,
    StatsAreaChartComponent,
    TrafficRevealCardComponent,
    TrafficBackCardComponent,
    TrafficBackCardComponent,
    TrafficFrontCardComponent,
    TrafficCardsHeaderComponent,
    TrafficBarChartComponent,
    TrafficBarComponent,
  ],
  imports: [
    CommonModule,
    NbCardModule,
    NbIconModule,
    NbInputModule,
    NbListModule,
    NbTabsetModule,
    Ng2SmartTableModule,
    ChartModule,
    NbProgressBarModule,
    NgxEchartsModule,
    NgxChartsModule,
    NbSelectModule,
  ],
  exports: [
    ChartPanelHeaderComponent,
    ChartPanelSummaryComponent,
    ECommerceChartsPanelComponent,
    ECommerceLegendChartComponent,
    GdcTableComponent,
    OrdersChartComponent,
    ProfitChartComponent,
    ProfitCardComponent,
    StatsBarAnimationChartComponent,
    StatsCardBackComponent,
    StatsCardFrontComponent,
    StatsAreaChartComponent,
    TrafficRevealCardComponent,
    TrafficBackCardComponent,
    TrafficBackCardComponent,
    TrafficFrontCardComponent,
    TrafficCardsHeaderComponent,
    TrafficBarChartComponent,
    TrafficBarComponent,
  ],
})
export class GdcComponentsModule {}
