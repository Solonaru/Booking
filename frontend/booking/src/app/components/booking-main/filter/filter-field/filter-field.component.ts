import { Component, OnInit, Input } from '@angular/core';

import { FilterService } from 'src/app/services/filter.service';
import { FilterDetail } from 'src/app/models/filter-detail.model';

@Component({
  selector: 'app-filter-field',
  templateUrl: './filter-field.component.html',
  styleUrls: ['./filter-field.component.css']
})
export class FilterFieldComponent implements OnInit {
  @Input() filterField: FilterDetail;
  @Input() index: number;
  values: string[] = [];

  constructor(private filterService: FilterService) { }

  ngOnInit() {
    this.filterService.getValues(this.filterField).subscribe(data => {
      this.values = data;
    });
  }

  onSelect(option: string) {
    this.filterService.updateFilter(this.filterField.identificator, option);
  }

}
