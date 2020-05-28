import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BookingStartComponent } from './components/booking-main/booking-start/booking-start.component';
import { BookingMainComponent } from './components/booking-main/booking-main.component';
import { RoomDetailComponent } from './components/booking-main/room-detail/room-detail.component';
import { RoomUnselectedComponent } from './components/booking-main/room-unselected/room-unselected.component';

const routes: Routes = [
  { path: '', redirectTo: '/booking-main', pathMatch: 'full' },
  { path: 'booking-start', component: BookingStartComponent },
  {
    path: 'booking-main', component: BookingMainComponent, children: [
      { path: '', component: RoomUnselectedComponent },
      { path: ':id', component: RoomDetailComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
