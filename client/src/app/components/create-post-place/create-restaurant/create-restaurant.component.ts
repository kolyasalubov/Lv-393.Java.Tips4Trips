import { Component, OnInit } from '@angular/core';
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {City} from "../../../model/city.model";
import {Restaurant} from "../../../model/restaurant.model";
import {RestaurantService} from "./restaurant.service";
import {Position} from "../../../model/position.model";

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

  city: City[] = null;

  restaurant: Restaurant = new Restaurant(null, null, null, [], null,
    null, "FOOD", null, null,null,null,"photo_path",
    null,null,false);

  position: Position = new Position(0, 0);
  cityDTO: City = new City(null, null, null, null, null);

  constructor(private cityService: CityService, private router: Router, private restaurantService: RestaurantService) { }

  setCityId(value) {
    this.restaurantService.cityId = value;
    this.cityService.getById(value).subscribe(val => this.cityDTO = val);
  }

  ngOnInit() {
    this.cityService.getAll().subscribe(data => this.city = data);
    // this.restaurantService.updateRestaurant(this.restaurant);
  }

  create(){
    this.restaurant.cityDTO = this.cityDTO;
    this.restaurant.position = this.position;
    this.restaurantService.createRestaurant(this.restaurant).subscribe(data => this.restaurant = data);
    setTimeout(() => {window.location.href = '/restaurants/' + this.restaurant.id;}, 2000);
  }

  setWorkingDays(day: string) {
    if (this.restaurant.workingDays.includes(day)) {
      this.restaurant.workingDays.splice(this.restaurant.workingDays.indexOf(day), 1);
    } else {
      this.restaurant.workingDays.push(day);
    }
  }

}
