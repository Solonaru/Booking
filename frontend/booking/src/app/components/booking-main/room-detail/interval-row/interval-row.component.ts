import { Component, OnInit, Input } from '@angular/core';

import { IntervalService } from 'src/app/services/interval.service';
import { TimeInterval } from 'src/app/models/time-interval.model';

@Component({
  selector: 'tr[app-interval-row]',
  templateUrl: './interval-row.component.html',
  styleUrls: ['./interval-row.component.css']
})
export class IntervalRowComponent implements OnInit {
  @Input() interval: TimeInterval;

  constructor(private intervalService: IntervalService) { }

  ngOnInit() { }

  onClick() {
    if (this.interval.available && this.interval.selectable) {
      this.intervalService.updateIntervals(this.interval, !this.interval.selected);
    }
  }

}
