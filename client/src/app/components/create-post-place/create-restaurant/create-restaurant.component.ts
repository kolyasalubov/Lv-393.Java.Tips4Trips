import {Component, OnInit} from '@angular/core';
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

  position: Position = new Position(null, null);

  cityDTO: City = new City(null, '', this.position, null, null);

  restaurant: Restaurant = new Restaurant(null, '', '', [], '',
    '', '', '', '', null, this.position,
    this.cityDTO, 0, false);

  uploadPhoto: boolean = false;
  photoUrl: string = '';

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }
  setAddress: Function = (address: string) => {
    this.restaurant.address = address;
  };

  formGroup: FormGroup = new FormGroup({
    name: new FormControl(null, [
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(35)
    ]),
    description: new FormControl(null, [
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(255)
    ]),
    workingDays: new FormControl(null, [
      Validators.required
    ]),
    webSite: new FormControl(null, [
      Validators.required,
      Validators.pattern('^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$'),
      Validators.maxLength(35)
    ]),
    telephone: new FormControl(null, [
      Validators.required,
      Validators.pattern('\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n' +
        '2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n' +
        '4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$')
    ]),
    openingTime: new FormControl(null, [
      Validators.required
    ]),
    closingTime: new FormControl(null, [
      Validators.required
    ]),
    address: new FormControl(null, [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(60)
    ]),
    averageBill: new FormControl(null, [
      Validators.required,
      Validators.min(10),
      Validators.max(999999)
    ]),
    positionX: new FormControl(null, [
      Validators.required
    ]),
    positionY: new FormControl(null, [
      Validators.required
    ]),
    country: new FormControl(null, [
      Validators.required
    ]),
    city: new FormControl(null, [
      Validators.required
    ]),
    hasVeganFood: new FormControl(),
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router, private restaurantService: RestaurantService) {
  }

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

  create() {
    this.restaurant.cityDTO = this.cityDTO;
    this.restaurant.position = this.position;
    this.restaurantService.createRestaurant(this.restaurant).subscribe(data => {
      this.restaurant = data;
      this.photoUrl = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/places/' + this.restaurant.id + '/image';
      this.uploadPhoto = true;
    });
  }

  setWorkingDays(day: string) {
    if (this.restaurant.workingDays.includes(day)) {
      this.restaurant.workingDays.splice(this.restaurant.workingDays.indexOf(day), 1);
    } else {
      this.restaurant.workingDays.push(day);
    }
  }

  navigate() {
    this.router.navigateByUrl('/restaurants/' + this.restaurant.id);
  }
}
