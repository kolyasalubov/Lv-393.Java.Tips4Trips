import { Component, OnInit } from '@angular/core';
import {City} from "../../../model/city.model";
import {Position} from "../../../model/position.model";
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {MonumentService} from "./monument.service";
import {Monument} from "../../../model/monument.model";

@Component({
  selector: 'app-create-monument',
  templateUrl: './create-monument.component.html',
  styleUrls: ['./create-monument.component.css']
})
export class CreateMonumentComponent implements OnInit {

  city: City[] = null;

  position: Position = new Position(0, 0);
  cityDTO: City = new City(0, '', this.position, 0, '');
  monument: Monument = new Monument(0, '', '', '', this.position, "photo_path", this.cityDTO);



    constructor(private cityService: CityService, private router: Router, private monumentService: MonumentService) { }

    setCityId(value) {
      this.monumentService.cityId = value;
      this.cityService.getById(value).subscribe(val => this.cityDTO = val);
    }

    ngOnInit() {
      this.cityService.getAll().subscribe(data => this.city = data);
      // this.restaurantService.updateRestaurant(this.restaurant);
    }

    create(){
      this.monument.cityDTO = this.cityDTO;
      this.monument.position = this.position;
      this.monumentService.createMonument(this.monument).subscribe(data => this.monument = data);
      setTimeout(() => {window.location.href = '/monuments/' + this.monument.id;}, 2000);
  }

}
