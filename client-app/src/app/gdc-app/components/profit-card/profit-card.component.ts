import { Component, Input } from '@angular/core';

@Component({
  selector: 'ngx-profit-card',
  styleUrls: ['./profit-card.component.scss'],
  templateUrl: './profit-card.component.html',
})
export class ProfitCardComponent {
  @Input() frontTitle: string = 'All Time';
  @Input() backTitle: string = 'Monthly trace';
  @Input() intiallyFlipped: boolean = true;

  flipped = this.intiallyFlipped;

  toggleView() {
    this.flipped = !this.flipped;
  }
}
