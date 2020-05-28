import { Injectable } from '@angular/core';
import { Booking } from '../models/booking.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpEvent } from '@angular/common/http';
import { take } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class BookingService {
    private bookingUrl = 'http://localhost:8080/booking';

    constructor(private http: HttpClient) { }

    getBookingsByRoomIdAndDate(date: Date, roomId: number): Observable<Booking[]> {
        return this.fetchBookingsByRoomIdAndDate(date, roomId);
    }

    addBooking(startDate: Date, endDate: Date, roomId: number): Observable<Booking> {
        return this.saveBooking(startDate, endDate, roomId);
    }

    private fetchBookings(): Observable<Booking[]> {
        return this.http
            .get<Booking[]>(this.bookingUrl + "/all")
            .pipe(
                take(1));
    }

    private fetchBookingsByRoomId(roomId: number): Observable<Booking[]> {
        return this.http
            .get<Booking[]>(this.bookingUrl + "/all/room/" + roomId)
            .pipe(
                take(1));
    }

    private fetchBookingsByRoomIdAndDate(date: Date, roomId: number): Observable<Booking[]> {
        this.adjustDate(date);

        let bookingData: BookingFetchData = new BookingFetchData(date, roomId);

        return this.http
            .put<Booking[]>(this.bookingUrl + "/all/room/data", JSON.stringify(bookingData), httpOptions)
            .pipe(
                take(1));
    }

    private saveBooking(startDate: Date, endDate: Date, roomId: number): Observable<Booking> {
        this.adjustDate(startDate);
        this.adjustDate(endDate);

        let bookingData: BookingSaveData = new BookingSaveData(startDate, endDate, roomId);

        return this.http.post<Booking>(this.bookingUrl + '/add', JSON.stringify(bookingData), httpOptions);
    }

    /* check if a better solution is available to add timezone */
    private adjustDate(date: Date): void {
        if (null != date) {
            const hoursDiff = date.getHours() - date.getTimezoneOffset() / 60;
            const minutesDiff = (date.getMinutes() - date.getTimezoneOffset()) % 60;
            date.setHours(hoursDiff);
            date.setMinutes(minutesDiff);
        }
    }

}

class BookingFetchData {
    constructor(public date: Date, public roomId: number) { }
}

class BookingSaveData {
    constructor(public startTime: Date, public endTime: Date, public roomId: number) { }
}