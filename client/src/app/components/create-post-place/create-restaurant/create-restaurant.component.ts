import { Component, OnInit } from '@angular/core';
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {City} from "../../../model/city.model";
import {Restaurant} from "../../../model/restaurant.model";
import {RestaurantService} from "./restaurant.service";
import {Position} from "../../../model/position.model";
import {WeekDay} from "@angular/common";

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent implements OnInit {

  city: City[] = null;
  restaurant: Restaurant = new Restaurant(null, null, null, [WeekDay.Friday.toString()], null,
    null, "FOOD", new Date(),new Date(),null,null,"photo_path",
    null,null,false);
  position: Position = new Position(0, 0);
  cityDTO: City = new City(null, null, null, null, null);
  constructor(private citySerice: CityService, private router: Router, private restaurantService: RestaurantService) { }

  setCityId(value) {
    this.restaurantService.cityId = value;
    this.citySerice.getById(value).subscribe(val => this.cityDTO = val);
  }

  ngOnInit() {
    this.citySerice.getAll().subscribe(data => this.city = data);
    // this.restaurantService.updateRestaurant(this.restaurant);
  }

  create(){
    this.restaurant.city = this.cityDTO;
    this.restaurant.position = this.position;
    this.restaurantService.createRestaurant(this.restaurant).subscribe(data => this.restaurant = data);
  }
}
