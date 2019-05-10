import {Component, OnInit} from '@angular/core';
import {Position} from "../../../model/position.model";
import {Restaurant} from "../../../model/restaurant.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CountryService} from "../../../country.service";
import {CityService} from "../city.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RestaurantService} from "../create-restaurant/restaurant.service";

@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.css']
})
export class EditRestaurantComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

  position: Position = new Position(0, 0);

  restaurant: Restaurant = new Restaurant(null, '', '', [], '',
    '', '', '', '', this.position,"photo_path",
    null,0,false);

  id: number;

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
    hasVeganFood: new FormControl(),
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router,
              private restaurantService: RestaurantService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.restaurantService.findById(this.id).subscribe(data => {
      this.restaurant = data;
      this.position = data.position;
    });
  }

  update(){
    this.restaurant.position = this.position;
    this.restaurantService.update(this.restaurant).subscribe(data => this.restaurant = data);
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
