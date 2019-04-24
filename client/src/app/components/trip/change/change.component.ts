import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TripService} from "../trip/trip.service";
import {TripDetailsDTO} from "../../../model/trip-details";
import {AccountInfo} from "../../../model/account-info.model";
import {RouteService} from "../../../route.service";

@Component({
  selector: 'app-change',
  templateUrl: './change.component.html',
  styleUrls: ['./change.component.css']
})
export class ChangeComponent implements OnInit {
  id: number;
  trip: TripDetailsDTO;
  routeName: string;
  subscribers: AccountInfo[] = [];

  constructor(private routeService: RouteService,
              private tripService: TripService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
      console.log(this.id)
    });
    if (!isNaN(this.id) && this.id > 0) {
      this.tripService.getById(this.id).subscribe(data => {
        if (data!=null) {
          this.trip = data;
          console.log('null')
        }
        else {
          window.location.href = 'http://localhost:4200/trip';
        }
      });
    }
    else {
      window.location.href = 'http://localhost:4200/trip';
    }
  }

  addRoute(): void {
    if (this.routeName == null) {
      return;
    }
    this.routeService.findByName(this.routeName).subscribe(data => {
      if (data != null) {
        this.trip.route = data;
      }
    });
  }

  save(): void {
    if (this.validate()) {
      this.trip.creationDate = new Date();
      console.log(this.trip);
      this.tripService.update(this.trip).subscribe(result => this.trip = result);
      setTimeout(() => {
        window.location.href = 'http://localhost:4200/trip/' + this.id;
      }, 100);
    }
  }

  validate(): boolean {
    return this.trip.route != null &&
      this.trip.name != null &&
      this.trip.description != null &&
      this.trip.startDate != null &&
      this.trip.creator.id != null;
  }

  removePlace(): void {
    this.tripService.deleteTrip(this.trip.id).subscribe(result => {    });
    window.location.href = 'http://localhost:4200/trip';
  }
}

