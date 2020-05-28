export class TimeInterval {
    private id: number;

    private hourStart: number;
    private minuteStart: number;

    private hourEnd: number;
    private minuteEnd: number;

    private length: number;

    public available: boolean = true;
    public selectable: boolean = true;
    public selected: boolean = false;
    public intermediary: boolean = false;

    constructor(hour: number, minute: number, lengthMinutes: number, id?: number) {
        this.length = lengthMinutes;

        if (undefined == id) {
            this.id = 0;
        } else {
            this.id = id;
        }

        if (60 < lengthMinutes) {
            throw new Error("Invalid interval length!");
        }

        if (0 <= hour && 24 > hour) {
            this.hourStart = hour;

            if (0 <= minute && 60 > minute) {
                this.minuteStart = minute;

                if (60 <= minute + lengthMinutes) {
                    this.hourEnd = hour + 1;
                    this.minuteEnd = minute + lengthMinutes - 60;
                } else {
                    this.hourEnd = hour;
                    this.minuteEnd = minute + lengthMinutes;
                }

            } else {
                throw new Error("Invalid minute parameter")
            }

        } else {
            throw new Error("Invalid hour parameter");
        }

    }

    getId(): number {
        return this.id;
    }

    getStartHour(): number {
        return this.hourStart;
    }

    getStartMinute(): number {
        return this.minuteStart;
    }

    getEndHour(): number {
        return this.hourEnd;
    }

    getEndMinute(): number {
        return this.minuteEnd;
    }

    getTime(position: string): string {
        if ("from" === position) {
            return this.getStartTime();
        } else if ("to" === position) {
            return this.getEndTime();
        }

        return null;
    }

    getInterval(): string {
        let minuteStart: string = (0 == this.minuteStart) ? ("00") : ((10 > this.minuteStart) ? (this.minuteStart * 10 + "") : (this.minuteStart + ""));
        let minuteEnd: string = (0 == this.minuteEnd) ? ("00") : ((10 > this.minuteEnd) ? (this.minuteEnd * 10 + "") : (this.minuteEnd + ""));

        return this.hourStart + ":" + minuteStart + " - " + this.hourEnd + ":" + minuteEnd;
    }

    increment(): TimeInterval {
        return new TimeInterval(this.hourEnd, this.minuteEnd, this.length, this.id + 1);
    }

    private getStartTime(): string {
        let minuteStart: string = (0 == this.minuteStart) ? ("00") : ((10 > this.minuteStart) ? (this.minuteStart * 10 + "") : (this.minuteStart + ""));

        return this.hourStart + ":" + minuteStart;
    }

    private getEndTime(): string {
        let minuteEnd: string = (0 == this.minuteEnd) ? ("00") : ((10 > this.minuteEnd) ? (this.minuteEnd * 10 + "") : (this.minuteEnd + ""));

        return this.hourEnd + ":" + minuteEnd;
    }

}