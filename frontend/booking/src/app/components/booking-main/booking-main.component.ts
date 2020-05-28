import { Component, OnInit } from '@angular/core';

import { FilterDetail } from 'src/app/models/filter-detail.model';

@Component({
  selector: 'app-booking-main',
  templateUrl: './booking-main.component.html',
  styleUrls: ['./booking-main.component.css']
})
export class BookingMainComponent implements OnInit {
  fields: FilterDetail[] = [
    { selector: 'building', identificator: 'buildingName', field: 'name' },
    { selector: 'floor', identificator: 'floorNr', field: 'nr' }
  ];

  intervalFields: FilterDetail[] = [
    { selector: 'from' },
    { selector: 'to' }
  ];

  constructor() { }

  ngOnInit() {

  }

}
