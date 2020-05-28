import { Component, OnInit, Input } from '@angular/core';
import { FilterDetail } from 'src/app/models/filter-detail.model';

@Component({
  selector: 'app-interval-filter',
  templateUrl: './interval-filter.component.html',
  styleUrls: ['./interval-filter.component.css']
})
export class IntervalFilterComponent implements OnInit {
  @Input() filterGroupTitle: string;
  @Input() filterFields: FilterDetail[];

  constructor() { }

  ngOnInit() {
  }

}

