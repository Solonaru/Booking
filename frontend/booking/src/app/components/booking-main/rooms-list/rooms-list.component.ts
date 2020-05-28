import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { FilterService } from 'src/app/services/filter.service';
import { RoomService } from 'src/app/services/room.service';
import { Room } from 'src/app/models/room.model';

@Component({
  selector: 'app-rooms-list',
  templateUrl: './rooms-list.component.html',
  styleUrls: ['./rooms-list.component.css']
})
export class RoomsListComponent implements OnInit, OnDestroy {
  rooms: Room[];
  subscriptions: Subscription[] = [];

  math = Math;

  constructor(
    private roomService: RoomService,
    private filterService: FilterService) { }

  ngOnInit() {
    this.filterService.addFilter('buildingName');
    this.filterService.addFilter('floorNr');

    this.subscriptions.push(this.roomService.roomsChanged
      .subscribe(
        (rooms: Room[]) => {
          this.rooms = rooms;
        }
      ));

    this.subscriptions.push(this.filterService.filtersChanged
      .subscribe(
        () => {
          this.roomService.filterRooms();
        }
      ));

    this.roomService.loadRooms();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }

}
