import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../create-post-place/create-restaurant/restaurant.service";
import {Restaurant} from "../../model/restaurant.model";
import {Position} from "../../model/position.model";
import {City} from "../../model/city.model";
import {Location} from "@angular/common";
import {CustomAuthService} from "../authentication/custom-auth.service";
import {PageFeedbackPlaceModel} from "../../model/page-feedback-place.model";
import {FeedbackPlaceService} from "../feedback/feedback-place.service";

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
              private restaurantService: RestaurantService, private location: Location,
              private authService: CustomAuthService,private feedbackService:FeedbackPlaceService) { }
  id: number;
  countryId: number;
  cityId: number;
  restaurant: Restaurant = new Restaurant(0, '', '', [], '',
    '', '', '', '', null, new Position(0,0),
    new City(0, '',new Position(0,0), 0,''),0,false);
  hasVeganFood: string;
  zoom: number = ZoomLevel.Place;
  daysMap = new Map();
  imageURL: string = '';
  role: string;
  pageFeedbackPlaceModel:PageFeedbackPlaceModel;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.restaurantService.findById(this.id).subscribe(value => {
      this.restaurant = value;
      this.restaurant.workingDays.sort((d1, d2) => this.daysMap.get(d1) - this.daysMap.get(d2));
      this.imageURL = 'http://localhost:8080/places/' + this.restaurant.id + '/image';
      this.hasVeganFood = this.restaurant.hasVeganFood ? 'YES' : 'NO';
    });

    this.authService.getCurrentUser().subscribe(data => this.role = data.role);

    this.daysMap.set("MONDAY", 1);
    this.daysMap.set("TUESDAY", 2);
    this.daysMap.set("WEDNESDAY", 3);
    this.daysMap.set("THURSDAY", 4);
    this.daysMap.set("FRIDAY", 5);
    this.daysMap.set("SATURDAY", 6);
    this.daysMap.set("SUNDAY", 7);
    this.feedbackService.getByPlaceIdAndPage(this.id,1).subscribe(data=>{
      this.pageFeedbackPlaceModel=data;
    });
  }

  deletePlace() {
    let confirmation = confirm("Delete " + this.restaurant.name + "?");
    if (confirmation) {
      this.restaurantService.deleteById(this.restaurant.id);
    }
    this.location.back();
  }

}
