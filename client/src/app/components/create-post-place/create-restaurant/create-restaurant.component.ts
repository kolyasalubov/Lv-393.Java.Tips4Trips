import { Component, OnInit } from '@angular/core';
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {City} from "../../../model/city.model";
import {Restaurant} from "../../../model/restaurant.model";
import {RestaurantService} from "./restaurant.service";
import {Position} from "../../../model/position.model";
import {Country} from "../../../model/country.model";
import {CountryService} from "../../../country.service";

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css']
})
export class CreateRestaurantComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

  city: City[] = null;
  countries: Country[] = null;

  position: Position = new Position(0, 0);

  cityDTO: City = new City(null, '', this.position, null, null);

  restaurant: Restaurant = new Restaurant(null, '', '', [], '',
    '', "FOOD", '', '','', this.position,"photo_path",
    this.cityDTO,0,false);

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router, private restaurantService: RestaurantService) { }

  setChosenCountry(value) {
    this.cityService.getAllByCountryId(value).subscribe(val => this.city = val);
  }

  setChosenCity(value) {
    this.restaurantService.cityId = value;
    this.cityService.getById(value).subscribe(val => this.cityDTO = val);
  }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
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
