import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';

import { IntervalService } from 'src/app/services/interval.service';
import { RoomService } from 'src/app/services/room.service';
import { TimeInterval } from 'src/app/models/time-interval.model';
import { Room } from 'src/app/models/room.model';
import { BookingService } from 'src/app/services/booking.service';
import { DatePickerService } from 'src/app/services/date-picker.service';

const INTERVALS_COUNT = 24;
const INTERVALS_LENGTH = 30; /* lenght in minutes */

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.css']
})
export class RoomDetailComponent implements OnInit, OnDestroy {
  room: Room;
  id: number;
  intervals: TimeInterval[];
  intervalsSelected: boolean = false;
  subscriptions: Subscription[] = [];

  error: string = null;
  success: string = null;

  math = Math;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private intervalService: IntervalService,
    private bookingService: BookingService,
    private datePickerService: DatePickerService) { }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.id = +params['id'];

          if (undefined == (this.room = this.roomService.getRoom(this.id))) {

            this.subscriptions.push(this.roomService.roomsChanged
              .subscribe(
                (rooms: Room[]) => {
                  this.room = this.roomService.getRoom(this.id);

                  if (undefined != this.room) {
                    this.populateIntervals();
                  } else {
                    /* TODO: Go on empty details page in case of no rooms available by filter */
                  }

                }
              ));

          } else {
            this.populateIntervals();
          }
        }
      );

    this.subscriptions.push(this.intervalService.intervalsSelected
      .subscribe((selected: boolean) => {
        this.intervalsSelected = selected;
      }
      ));

    this.subscriptions.push(this.datePickerService.dateChanged
      .subscribe(() => {
        this.populateIntervals();
      }));

  }

  populateIntervals(): void {
    let date: Date = this.datePickerService.getDate();

    this.bookingService.getBookingsByRoomIdAndDate(date, this.room.id).subscribe(bookings => {
      this.intervals = this.intervalService.getIntervals(INTERVALS_COUNT, INTERVALS_LENGTH, bookings, date);
    });
  }

  onBook(): void {
    const timeout: number = 1500;

    if (this.intervalsSelected) {
      this.bookingService.addBooking(this.intervalService.getStartTime(), this.intervalService.getEndTime(), this.room.id)
        .subscribe(data => {
          this.success = "Successfully booked.";

          setTimeout(() => {
            this.success = null;
          }, timeout);

          this.intervalService.bookIntervals();
        }, error => {
          this.error = "An error occured!";

          setTimeout(() => {
            this.error = null;
          }, timeout);
        });
    }
  }

  getTitle(): string {
    let title: string = null;

    if (this.success) {
      title = this.success;
    } else if (this.error) {
      title = this.error;
    } else {
      title = "Select interval";
    }

    return title;
  }

  getTitleStyle(): string {
    let style: string = null;

    if (this.success) {
      style = "alert alert-success title";
    } else if (this.error) {
      style = "alert alert-danger title";
    } else {
      style = "alert alert-info title";
    }

    return style;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => {
      subscription.unsubscribe();
    });
  }

}
