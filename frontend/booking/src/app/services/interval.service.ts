import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { TimeInterval } from '../models/time-interval.model';
import { Booking } from '../models/booking.model';

@Injectable({ providedIn: 'root' })
export class IntervalService {
    private intervals: TimeInterval[] = [];
    private firstSelectedInterval: TimeInterval = null;
    private lastSelectedInterval: TimeInterval = null;

    private date: Date;

    intervalsSelected = new Subject<boolean>();

    getStartTime(): Date {
        let date: Date = null;

        if (null !== this.firstSelectedInterval) {
            date = this.getTime(this.firstSelectedInterval, true);
        }

        return date;
    }

    getEndTime(): Date {
        let date: Date = null;

        if (null !== this.lastSelectedInterval) {
            date = this.getTime(this.lastSelectedInterval, false);
        } else if (null !== this.firstSelectedInterval) {
            date = this.getTime(this.firstSelectedInterval, false);
        }

        return date;
    }

    getIntervals(intervalsCount: number, intervalLength: number, bookings: Booking[], date: Date): TimeInterval[] {
        this.date = date;

        if (0 === this.intervals.length) {
            this.loadIntervals(intervalsCount, intervalLength, bookings);
        } else {
            this.intervals.forEach((interval: TimeInterval) => {
                this.checkInterval(bookings, interval);
            });
        }

        return this.intervals.slice();
    }

    updateIntervals(selectedInterval: TimeInterval, selected: boolean) {

        if (selected) {
            if (null === this.firstSelectedInterval) {
                /* This is the first interval to be selected ever:
                    - change the status to selected
                    - check all the intervals that cannot be selected as last interval */
                this.firstSelectedInterval = selectedInterval;
                this.firstSelectedInterval.selected = true;

                this.checkUnselectable(selectedInterval.getId(), true);
                this.checkUnselectable(selectedInterval.getId(), false);

                this.setIntervalsSelected(true);
            } else {
                if (null === this.lastSelectedInterval) {
                    /* The second interval is now selected:
                        - change the status to selected
                        - check first and last interval order
                        - mark all the intermediary intervals between the first and last as selected */
                    this.lastSelectedInterval = selectedInterval;
                    this.lastSelectedInterval.selected = true;
                    this.checkFirstAndLastOrder();
                    this.checkSelected(false);
                } else {
                    /* An interval is selected outside the first and last selection area:
                        - check first and last interval order   
                        - increment selection */
                    this.checkFirstAndLastOrder();
                    this.incrementSelected(selectedInterval);
                }
            }
        } else {
            if (selectedInterval === this.firstSelectedInterval) {
                /* The first interval is unselected:
                    - check whetever a last interval is selected */
                if (null === this.lastSelectedInterval) {
                    /* No last interval is available:
                        - unselect the current interval
                        - make all the interval selectable */
                    this.firstSelectedInterval.selected = false;
                    this.firstSelectedInterval = null;
                    this.checkSelectable();

                    this.setIntervalsSelected(false);
                } else {
                    /* There is a last interval selected:
                        - mark the last interval selected as the first interval
                        - remove the selection from the previously first interval
                        - remove the selection from all the intermediary intervals */
                    this.firstSelectedInterval.selected = false;
                    this.firstSelectedInterval = this.lastSelectedInterval;
                    this.lastSelectedInterval = null;
                    this.checkSelected(true);
                }
            } else if (selectedInterval === this.lastSelectedInterval) {
                /* The last interval is unselected:
                    - remove the selection from the last interval
                    - remove the selection from all the intermediary intervals */
                this.lastSelectedInterval = null;
                this.checkSelected(true);
            } else {
                /* An intermediary field is "re-selected":
                    - do nothing */
            }
        }

    }

    bookIntervals(): void {
        if (null !== this.firstSelectedInterval) {
            if (null !== this.lastSelectedInterval) {
                this.intervals.forEach((interval: TimeInterval) => {
                    if (interval.getId() >= this.firstSelectedInterval.getId() &&
                        interval.getId() <= this.lastSelectedInterval.getId()) {
                        interval.selected = false;
                        interval.available = false;
                        interval.intermediary = false;
                    } else {
                        interval.selectable = true;
                    }
                });

                this.firstSelectedInterval = null;
                this.lastSelectedInterval = null;
            } else {
                this.intervals.forEach((interval: TimeInterval) => {
                    if (interval.getId() === this.firstSelectedInterval.getId()) {
                        interval.selected = false;
                        interval.available = false;
                    } else {
                        interval.selectable = true;
                    }
                });

                this.firstSelectedInterval = null;
            }

            this.setIntervalsSelected(false);
        }
    }

    private getTime(interval: TimeInterval, start: boolean): Date {
        let date: Date = null;

        if (null !== this.date) {
            date = new Date(this.date);

            if (start) {
                date.setHours(interval.getStartHour());
                date.setMinutes(interval.getStartMinute());
            } else {
                date.setHours(interval.getEndHour());
                date.setMinutes(interval.getEndMinute());
            }

            date.setSeconds(0);
            date.setMilliseconds(0);
        }

        return date;
    }

    private setIntervalsSelected(selected: boolean): void {
        this.intervalsSelected.next(selected);
    }

    private checkFirstAndLastOrder() {
        if (this.firstSelectedInterval.getId() > this.lastSelectedInterval.getId()) {
            let temp: TimeInterval = this.firstSelectedInterval;
            this.firstSelectedInterval = this.lastSelectedInterval;
            this.lastSelectedInterval = temp;
        }
    }

    private incrementSelected(selectedInterval: TimeInterval) {
        if (selectedInterval.getId() < this.firstSelectedInterval.getId()) {
            for (let i = selectedInterval.getId() + 1; i <= this.firstSelectedInterval.getId(); i++) {
                this.intervals[i].selected = true;
                this.intervals[i].intermediary = true;
            }

            this.firstSelectedInterval = selectedInterval;
            this.firstSelectedInterval.selected = true;
        } else if (selectedInterval.getId() > this.lastSelectedInterval.getId()) {
            for (let i = this.lastSelectedInterval.getId(); i < selectedInterval.getId(); i++) {
                this.intervals[i].selected = true;
                this.intervals[i].intermediary = true;
            }

            this.lastSelectedInterval = selectedInterval;
            this.lastSelectedInterval.selected = true;
        }
    }

    private checkSelected(unselected: boolean): void {
        if (unselected) {
            this.intervals.forEach((interval: TimeInterval) => {
                if (interval !== this.firstSelectedInterval) {
                    interval.selected = false;
                }

                interval.intermediary = false;
            });
        } else {
            let startIntervalTime: number = this.firstSelectedInterval.getStartHour() + (this.firstSelectedInterval.getStartMinute() / 100);
            let endIntervalTime: number = this.lastSelectedInterval.getStartHour() + (this.lastSelectedInterval.getStartMinute() / 100);

            let intervalTime: number;

            this.intervals.forEach((interval: TimeInterval) => {
                intervalTime = interval.getStartHour() + (interval.getStartMinute() / 100);

                if ((startIntervalTime < intervalTime) && (endIntervalTime > intervalTime)) {
                    interval.selected = true;
                    interval.intermediary = true;
                }
            });
        }
    }

    private checkSelectable(): void {
        this.intervals.forEach((interval: TimeInterval) => {
            interval.selectable = true;
        });
    }

    private checkUnselectable(currentId: number, ascending: boolean): void {
        let unavailableIntervalFound: boolean = false;

        while (undefined !== this.intervals[currentId]) {
            if ((false === unavailableIntervalFound) && (false === this.intervals[currentId].available)) {
                unavailableIntervalFound = true;
            }

            if (unavailableIntervalFound && (true === this.intervals[currentId].available)) {
                this.intervals[currentId].selectable = false;
            }

            if (ascending) {
                currentId++;
            } else {
                currentId--;
            }
        }
    }

    private loadIntervals(intervalsCount: number, intervalLength: number, bookings: Booking[]): void {

        let interval: TimeInterval = new TimeInterval(8, 0, intervalLength);

        for (let i = 0; i < intervalsCount; i++) {

            if (undefined !== bookings) {
                this.checkInterval(bookings, interval);
            }

            this.intervals.push(interval);
            interval = interval.increment();
        }

    }

    private checkInterval(bookings: Booking[], interval: TimeInterval): void {
        this.firstSelectedInterval = null;
        this.lastSelectedInterval = null;
        interval.available = true;
        interval.selectable = true;
        interval.selected = false;
        interval.intermediary = false;

        this.setIntervalsSelected(false);

        bookings.forEach((booking: Booking) => {

            let intervalStartTime: number = interval.getStartHour() + (interval.getStartMinute() / 100);
            let bookingStartTime: number = new Date(booking.startTime).getHours() + (new Date(booking.startTime).getMinutes() / 100);
            let bookingEndTime: number = new Date(booking.endTime).getHours() + (new Date(booking.endTime).getMinutes() / 100);

            if ((intervalStartTime >= bookingStartTime) && (intervalStartTime < bookingEndTime)) {
                interval.available = false;
                return;
            }

        });

    }

}
