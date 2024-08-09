import { Component, Input } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';

@Component({
  selector: 'ngx-gdc-table',
  templateUrl: './gdc-table.component.html',
  styleUrls: ['./gdc-table.component.scss'],
})
export class GdcTableComponent {

  @Input() title: string;
  @Input() data: unknown[];
  @Input() settings: object;

  constructor() {
  }

  onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }
}
