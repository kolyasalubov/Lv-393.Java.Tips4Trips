import {Component, OnInit} from '@angular/core';
import {Route} from "../../../model/route.model";
import {RouteService} from "../../../service/route.service";
import {PlaceService} from "../../../place.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomAuthService} from "../../authentication/custom-auth.service";
import {AccountInfo} from "../../../model/account-info.model";
import {TripDetailsDTO} from "../../../model/trip-details";
import {AccountDTO} from "../../../model/account.model";
import {TripService} from "../trip/trip.service";

@Component({
  selector: 'app-create-trip',
  templateUrl: './create-trip.component.html',
  styleUrls: ['./create-trip.component.css']
})
export class CreateTripComponent implements OnInit {
  trip: TripDetailsDTO;
  routeName: string;
  subscribers: AccountInfo[] = [];
  url: string = 'http://localhost:8080/trips/';
  uploadPhoto: boolean = false;

  constructor(
    private routeService: RouteService,
    private placeService: PlaceService,
    private router: Router,
    private authService: CustomAuthService,
    private tripService: TripService,
    private activatedRoute: ActivatedRoute
  ) {
    this.trip = new TripDetailsDTO(
      null,
      null,
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
      this.trip.creator = new AccountDTO(null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null, null, null, null, null);
      this.subscribers[0] = new AccountInfo();
      this.trip.creator = data;
      this.trip.subscribers = this.subscribers;
      this.trip.subscribers[0].id = data.id;
      this.trip.subscribers[0].lastName = data.lastName;
      this.trip.subscribers[0].firstName = data.firstName;
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
      this.trip.creationDate = new Date();
      this.tripService.createTrip(this.trip).subscribe(result => {
        this.trip = result;
        this.url = this.url + this.trip.id + '/image';
        this.uploadPhoto = true;
      });
    }
  }

  navigate() {
    console.log('tete');
    this.router.navigateByUrl('/trip/' + this.trip.id);
  }

  validate(): boolean {
    return this.trip.route != null &&
      this.trip.name != null &&
      this.trip.description != null &&
      this.trip.startDate != null &&
      this.trip.creator.id != null;
  }

}
