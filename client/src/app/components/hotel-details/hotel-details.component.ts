import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Hotel} from "../../model/hotel.model";
import {HotelService} from "../create-post-place/create-hotel/hotel.service";
import {Position} from "../../model/position.model";
import {City} from "../../model/city.model";
import {Location} from "@angular/common";
import {CustomAuthService} from "../authentication/custom-auth.service";
import {FeedbackPlaceService} from "../feedback/feedback-place.service";
import {PageFeedbackPlaceModel} from "../../model/page-feedback-place.model";

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private hotelService: HotelService,
              private location: Location, private authService: CustomAuthService) { }
  id: number;
  countryId: number;
  cityId: number;
  hotel: Hotel = new Hotel(0, '', '', [], '',
    '', '', '', '',new Position(0, 0),null,
    new City(0, '', new Position(0, 0), 0, ''),0,0);
  zoom: number = ZoomLevel.Place;
  daysMap = new Map();
  imageURL: string = '';
  role: string;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.hotelService.findById(this.id).subscribe(value => {
      this.hotel = value;
      this.hotel.workingDays.sort((d1, d2) => this.daysMap.get(d1) - this.daysMap.get(d2));
      this.imageURL = 'http://localhost:8080/places/' + this.hotel.id + '/image';
    });

    this.authService.getCurrentUser().subscribe(data => this.role = data.role);

    this.daysMap.set("MONDAY", 1);
    this.daysMap.set("TUESDAY", 2);
    this.daysMap.set("WEDNESDAY", 3);
    this.daysMap.set("THURSDAY", 4);
    this.daysMap.set("FRIDAY", 5);
    this.daysMap.set("SATURDAY", 6);
    this.daysMap.set("SUNDAY", 7);
  }

  deletePlace() {
    let confirmation = confirm("Delete " + this.hotel.name + "?");
    if (confirmation) {
      this.hotelService.deleteById(this.hotel.id);
    }
    this.location.back();
  }
}
