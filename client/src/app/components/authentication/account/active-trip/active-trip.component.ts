import {Component, Input, OnInit} from '@angular/core';
import {NotificationTripModel} from "../../../../model/notification-trip.model";

@Component({
  selector: 'app-active-trip',
  templateUrl: './active-trip.component.html',
  styleUrls: ['./active-trip.component.css']
})
export class ActiveTripComponent implements OnInit {

  @Input() trip: NotificationTripModel;

  constructor() {
  }

  ngOnInit() {
  }

}
