import { Component, OnInit } from '@angular/core';
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {City} from "../../../model/city.model";
import {Restaurant} from "../../../model/restaurant.model";
import {RestaurantService} from "./restaurant.service";
import {Position} from "../../../model/position.model";
import {Country} from "../../../model/country.model";
import {CountryService} from "../../../country.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

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
    '', '', '', '', this.position,"photo_path",
    this.cityDTO,0,false);

  setXY: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }

  formGroup: FormGroup = new FormGroup({
    name: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(35)
    ]),
    description: new FormControl(null,[
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(255)
    ]),
    workingDays: new FormControl(null, [
      Validators.required
    ]),
    webSite: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(35)
    ]),
    telephone: new FormControl(null,[
      Validators.required,
      Validators.minLength(8),
      Validators.maxLength(15)
    ]),
    openingTime: new FormControl(null,[
      Validators.required
    ]),
    closingTime: new FormControl(null,[
      Validators.required
    ]),
    address: new FormControl(null,[
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(60)
    ]),
    averageBill: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(5)
    ]),
    positionX: new FormControl(null,[
      Validators.required
    ]),
    positionY: new FormControl(null,[
      Validators.required
    ]),
    country: new FormControl(null,[
      Validators.required
    ]),
    city: new FormControl(null,[
      Validators.required
    ]),
    hasVeganFood: new FormControl(),
  });

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
