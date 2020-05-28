import { Booking } from './booking.model';

export class Room {
    constructor(public id: number, public name: string, public floorNr: string, public buildingName: string, public bookings: Booking[]) { }
}