import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { map, take } from 'rxjs/operators';

import { FilterDetail } from '../models/filter-detail.model';

@Injectable({ providedIn: 'root' })
export class FilterService {
    private baseUrl = 'http://localhost:8080/';

    filtersChanged = new Subject<void>();

    private activeFilters: Map<string, string> = new Map();

    constructor(private http: HttpClient) { }

    getActiveFilters(): Map<string, string> {
        return new Map(this.activeFilters);
    }

    addFilter(filter: string): void {
        this.activeFilters.set(filter, 'All');
    }

    updateFilter(filter: string, value: string): void {
        this.activeFilters.set(filter, value);
        this.filtersChanged.next();
    }

    getValues(filterDetail: FilterDetail): Observable<string[]> {
        return this.fetchValues(filterDetail);
    }

    private fetchValues(filterDetail: FilterDetail): Observable<string[]> {
        return this.http
            .get<string[]>(this.baseUrl + filterDetail.selector + "/all")
            .pipe(
                take(1),
                map(responseData => {
                    let value;
                    const arr: string[] = [];

                    responseData.forEach(function (element) {
                        value = element[filterDetail.field];
                        if (-1 === arr.indexOf(value)) {
                            arr.push(value);
                        }
                    });

                    arr.sort((a, b) => {
                        let ret = 0;

                        if (NaN !== Number(a)) {
                            if (NaN !== Number(b)) {
                                ret = Number(a) - Number(b);
                            } else {
                                ret = -1;
                            }
                        } else {
                            if (NaN !== Number(b)) {
                                ret = 1;
                            } else {
                                ret = a.localeCompare(b);
                            }
                        }

                        return ret;
                    });

                    arr.unshift("All");

                    return arr;
                }));
    }

}
