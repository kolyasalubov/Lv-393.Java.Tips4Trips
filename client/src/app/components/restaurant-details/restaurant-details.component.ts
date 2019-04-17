import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../create-post-place/create-restaurant/restaurant.service";
import {Restaurant} from "../../model/restaurant.model";

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private restaurantService: RestaurantService) { }
  id: number;
  countryId: number;
  cityId: number;
  restaurant: Restaurant = new Restaurant(null, null, null, [], null,
    null, null, null, null,null,null,null,
    null,null,false);

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      // this.countryId = +params.get('countryId');
      // this.cityId = +params.get('cityId');
      this.id = +params.get('id');
    });
    this.restaurantService.findById(this.id).subscribe(value => this.restaurant = value);
  }

}
