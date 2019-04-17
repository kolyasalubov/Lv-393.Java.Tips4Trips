import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../create-post-place/create-restaurant/restaurant.service";
import {Restaurant} from "../../model/restaurant.model";
import {Position} from "../../model/position.model";
import {City} from "../../model/city.model";

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
  restaurant: Restaurant = new Restaurant(0, '', '', [], '',
    '', '', '', '','',new Position(0,0),'',
    new City(0, '',new Position(0,0), 0,''),0,false);

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.restaurantService.findById(this.id).subscribe(value => this.restaurant = value);
  }

}
