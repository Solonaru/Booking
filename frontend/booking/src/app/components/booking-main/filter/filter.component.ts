import { Component, OnInit, Input } from '@angular/core';

import { FilterDetail } from 'src/app/models/filter-detail.model';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
  @Input() filterGroupTitle: string;
  @Input() filterFields: FilterDetail[];

  constructor() { }

  ngOnInit() {
  }

}
