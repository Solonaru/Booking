import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { take, tap } from 'rxjs/operators';

import { FilterService } from './filter.service';
import { Room } from '../models/room.model';

@Injectable({ providedIn: 'root' })
export class RoomService {
    roomsChanged = new Subject<Room[]>();

    private roomUrl = 'http://localhost:8080/room';

    private rooms: Room[] = [];
    private filteredRooms: Room[] = [];

    constructor(
        private http: HttpClient,
        private filterService: FilterService) { }

    getRooms() {
        return this.filteredRooms.slice();
    }

    getRoom(index: number) {
        return this.filteredRooms[index];
    }

    loadRooms(): void {
        this.fetchRooms().subscribe();
    }

    filterRooms() {
        let filters: Map<string, string> = this.filterService.getActiveFilters();
        let valid: boolean;

        this.filteredRooms = this.rooms.filter(room => {
            valid = true;

            filters.forEach((value: string, filter: string) => {
                if (value != 'All') {
                    valid = valid && (room[filter] == value);
                }
            });

            return valid;
        });

        this.setRooms(this.filteredRooms);
    }

    private setRooms(rooms: Room[]): void {
        this.roomsChanged.next(rooms.slice());
    }

    private fetchRooms(): Observable<Room[]> {
        return this.http
            .get<Room[]>(this.roomUrl + "/all")
            .pipe(
                take(1),
                tap(rooms => {
                    this.rooms = rooms;
                    this.filteredRooms = rooms;
                    this.setRooms(rooms);
                }));

    }

}
