import {Component, OnInit} from '@angular/core';
import {Route} from "../../../model/route.model";
import {RouteService} from "../../../route.service";
import {PlaceService} from "../../../place.service";
import {Router} from "@angular/router";
import {AuthService} from "../../authentication/auth.service";
import {AccountInfo} from "../../../model/account-info.model";
import {FindGroupDetailsDTO} from "../../../model/trip-details";
import {Account} from "../../../model/account.model";
import {TripService} from "../trip/trip.service";

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.css']
})
export class CreateTripComponent implements OnInit {
  trip: FindGroupDetailsDTO;
  routeName: string;

  constructor(
    private routeService: RouteService,
    private placeService: PlaceService,
    private router: Router,
    private authService: AuthService,
    private tripService: TripService
  ) {
    this.trip = new FindGroupDetailsDTO(
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null);
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      this.trip.creator= new Account(null,
        null,
        null,
        null,
        null,
        null,
        null,
        null)
      this.trip.creator.id = data.id;
    });
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
      console.log(this.trip);
      // this.tripService.createTrip(this.trip).subscribe(result => this.trip = result);
      // setTimeout(() => {
      //   this.router.navigate(['routes']);
      // }, 2000);
    }
  }

  validate(): boolean {
    return this.trip.route != null &&
      this.trip.name != null &&
      this.trip.description != null &&
      this.trip.startDate != null &&
      this.trip.creator.id != null;
  }

  //
  // removePlace(id: number): void {
  //   this.route.places = this.route.places.filter(p => p.id != id);
  // }

}
