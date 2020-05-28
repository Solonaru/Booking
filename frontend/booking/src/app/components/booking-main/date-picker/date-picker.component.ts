import { Component, OnInit } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material';
import { DatePickerService } from 'src/app/services/date-picker.service';

@Component({
  selector: 'app-date-picker',
  templateUrl: './date-picker.component.html',
  styleUrls: ['./date-picker.component.css']
})
export class DatePickerComponent implements OnInit {
  minDate = new Date(Date.now());
  maxDate = new Date(Date.now() + 1.123e9); /* 1.123e9 milliseconds equivalent of 13 days */

  date: Date;

  constructor(private datePickerService: DatePickerService) { }

  ngOnInit() {
    this.date = new Date(Date.now());
  }

  weekendFilter = (d: Date): boolean => {
    const day = new Date(d).getDay();

    /* Prevent Saturday and Sunday from being selected */
    return day !== 0 && day !== 6;
  }

  onChange(event: MatDatepickerInputEvent<Date>) : void {
    this.datePickerService.setDate(new Date(event.value.toString()));
  }

}
