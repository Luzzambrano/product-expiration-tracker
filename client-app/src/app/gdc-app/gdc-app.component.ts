import { Component } from '@angular/core';

@Component({
  selector: 'ngx-gdc-app',
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class GdcAppComponent {
  menu = [
    {
      title: 'Dashboard',
      icon: 'home-outline',
      link: '/gdcapp/dashboard',
      home: true,
    },
    {
      title: 'Products',
      icon: 'cube-outline',
      link: '/gdcapp/products',
    },
    {
      title: 'Reports',
      icon: 'trending-up-outline',
      link: '/gdcapp/reports',
    },
    // {
    //   title: 'Generate Purchase Order',
    //   icon: 'layout-outline',
    //   link: '/gdcapp/products',
    // },
  ];
}
