import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class DatePickerService {
    dateChanged = new Subject<void>();

    date: Date = new Date();

    getDate() : Date {
        return new Date(this.date);
    }

    setDate(date: Date) : void {
        this.date = date;
        this.dateChanged.next();
    }

}
