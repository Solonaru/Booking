import { Component, OnInit, Input } from '@angular/core';
import { FilterDetail } from 'src/app/models/filter-detail.model';
import { IntervalFilterService } from 'src/app/services/interval-filter.service';
import { TimeInterval } from 'src/app/models/time-interval.model';

@Component({
  selector: 'app-interval-filter-field',
  templateUrl: './interval-filter-field.component.html',
  styleUrls: ['./interval-filter-field.component.css']
})
export class IntervalFilterFieldComponent implements OnInit {
  @Input() filterField: FilterDetail;
  @Input() index: number;
  intervals: TimeInterval[] = [];

  constructor(private intervalFilterService: IntervalFilterService) { }

  ngOnInit() {
    this.intervals = this.intervalFilterService.getValues(this.filterField);
  }

  onSelect(option: string) {
    // this.filterService.updateFilter(this.filterField.identificator, option);
  }

}
