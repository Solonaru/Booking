import { Injectable } from '@angular/core';
import { TimeInterval } from '../models/time-interval.model';
import { FilterDetail } from '../models/filter-detail.model';

const INTERVALS_COUNT = 24;
const INTERVALS_LENGTH = 30; /* lenght in minutes */
const FROM_SELECTOR = "from";
const TO_SELECTOR = "to";


@Injectable({ providedIn: 'root' })
export class IntervalFilterService {
    private fromIntervals: TimeInterval[] = [];
    private toIntervals: TimeInterval[] = [];

    // getValues(filterDetail: FilterDetail): string[] {
    //     let values: string[] = [];

    //     if (FROM_SELECTOR === filterDetail.selector) {
    //         this.getFromIntervals(INTERVALS_COUNT, INTERVALS_LENGTH).forEach(interval => {
    //             values.push(interval.getStartTime());
    //         });
    //     } else if (TO_SELECTOR === filterDetail.selector) {
    //         this.getToIntervals(INTERVALS_COUNT, INTERVALS_LENGTH).forEach(interval => {
    //             values.push(interval.getEndTime());
    //         });
    //     }

    //     return values;
    // }

    getValues(filterDetail: FilterDetail): TimeInterval[] {
        if (FROM_SELECTOR === filterDetail.selector) {
            return this.getFromIntervals(INTERVALS_COUNT, INTERVALS_LENGTH);
        } else if (TO_SELECTOR === filterDetail.selector) {
            return this.getToIntervals(INTERVALS_COUNT, INTERVALS_LENGTH);
        }

        return null;
    }

    private getFromIntervals(intervalsCount: number, intervalLength: number): TimeInterval[] {
        if ((undefined === this.fromIntervals) || (0 === this.fromIntervals.length)) {
            this.loadIntervals(intervalsCount, intervalLength);
        }

        return this.fromIntervals;
    }

    private getToIntervals(intervalsCount: number, intervalLength: number) {
        if ((undefined === this.toIntervals) || (0 === this.toIntervals.length)) {
            this.loadIntervals(intervalsCount, intervalLength);
        }

        return this.toIntervals;
    }

    private loadIntervals(intervalsCount: number, intervalLength: number): void {

        let interval: TimeInterval = new TimeInterval(8, 0, intervalLength);

        for (let i = 0; i < intervalsCount; i++) {
            this.fromIntervals.push(interval);
            this.toIntervals.push(interval);
            interval = interval.increment();
        }

    }
}