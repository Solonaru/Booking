import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularFontAwesomeModule } from 'angular-font-awesome';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { DropdownDirective } from './directives/dropdown.directive';

import { BookingStartComponent } from './components/booking-main/booking-start/booking-start.component';
import { BookingMainComponent } from './components/booking-main/booking-main.component';
import { RoomsListComponent } from './components/booking-main/rooms-list/rooms-list.component';
import { RoomItemComponent } from './components/booking-main/rooms-list/room-item/room-item.component';
import { RoomDetailComponent } from './components/booking-main/room-detail/room-detail.component';
import { RoomUnselectedComponent } from './components/booking-main/room-unselected/room-unselected.component';
import { IntervalRowComponent } from './components/booking-main/room-detail/interval-row/interval-row.component';
import { FilterComponent } from './components/booking-main/filter/filter.component';
import { FilterFieldComponent } from './components/booking-main/filter/filter-field/filter-field.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MomentDateModule, MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { MAT_DATE_FORMATS, DateAdapter, MAT_DATE_LOCALE } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { DatePickerComponent } from './components/booking-main/date-picker/date-picker.component';
import { LoadingSpinnerComponent } from './components/shared/loading-spinner/loading-spinner.component';
import { MatSelectModule } from '@angular/material/select';
import { IntervalFilterComponent } from './components/booking-main/interval-filter/interval-filter.component';
import { IntervalFilterFieldComponent } from './components/booking-main/interval-filter/interval-filter-field/interval-filter-field.component';

const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

const mat_modules = [
  MatFormFieldModule,
  MatDatepickerModule,
  MomentDateModule,
  MatInputModule,
  MatSelectModule
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DropdownDirective,

    BookingStartComponent,
    BookingMainComponent,
    RoomsListComponent,
    RoomItemComponent,
    RoomDetailComponent,
    RoomUnselectedComponent,
    IntervalRowComponent,
    FilterComponent,
    FilterFieldComponent,
    DatePickerComponent,
    LoadingSpinnerComponent,
    IntervalFilterComponent,
    IntervalFilterFieldComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    AngularFontAwesomeModule,
    mat_modules
  ],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
    { provide: MAT_MOMENT_DATE_ADAPTER_OPTIONS, useValue: { strict: true } }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
