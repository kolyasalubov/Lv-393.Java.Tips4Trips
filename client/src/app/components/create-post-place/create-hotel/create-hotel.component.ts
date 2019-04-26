import { Component, OnInit } from '@angular/core';
import { CityService } from '../city.service';
import {Router} from '@angular/router';
import { City } from 'src/app/model/city.model';
import {Restaurant} from "../../../model/restaurant.model";
import {Position} from "../../../model/position.model";
import {RestaurantService} from "../create-restaurant/restaurant.service";
import {Hotel} from "../../../model/hotel.model";
import {HotelService} from "./hotel.service";

@Component({
  selector: 'app-create-hotel',
  templateUrl: './create-hotel.component.html',
  styleUrls: ['./create-hotel.component.css']
})
export class CreateHotelComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

  city: City[] = null;

  position: Position = new Position(0, 0);

  cityDTO: City = new City(0, '', this.position, 0, '');
  hotel: Hotel = new Hotel(0, '', '', [], '',
    '', "HOUSING", '', '','', this.position,"photo_path",
    this.cityDTO,0,0);

  constructor(private cityService: CityService, private router: Router, private hotelService: HotelService) { }

  setCityId(value) {
    this.hotelService.cityId = value;
    this.cityService.getById(value).subscribe(val => this.cityDTO = val);
  }

  ngOnInit() {
    this.cityService.getAll().subscribe(data => this.city = data);
    // this.restaurantService.updateRestaurant(this.restaurant);
  }

  create(){
    this.hotel.cityDTO = this.cityDTO;
    this.hotel.position = this.position;
    this.hotelService.createHotel(this.hotel).subscribe(data => this.hotel = data);
    setTimeout(() => {window.location.href = '/hotels/' + this.hotel.id;}, 2000);
  }

  setWorkingDays(day: string) {
    if (this.hotel.workingDays.includes(day)) {
      this.hotel.workingDays.splice(this.hotel.workingDays.indexOf(day), 1);
    } else {
      this.hotel.workingDays.push(day);
    }
  }

}
