import { Room } from './room.model';

export class Booking {
    constructor(public startTime: Date, public endTime: Date, public date: Date, public reservationDate: Date, public reason: string, public room: Room) { }
}