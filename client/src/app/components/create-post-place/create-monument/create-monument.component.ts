import { Component, OnInit } from '@angular/core';
import {City} from "../../../model/city.model";
import {Position} from "../../../model/position.model";
import {CityService} from "../city.service";
import {Router} from "@angular/router";
import {MonumentService} from "./monument.service";
import {Monument} from "../../../model/monument.model";
import {Country} from "../../../model/country.model";
import {CountryService} from "../../../country.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-monument',
  templateUrl: './create-monument.component.html',
  styleUrls: ['./create-monument.component.css']
})
export class CreateMonumentComponent implements OnInit {

  city: City[] = null;
  countries: Country[] = null;

  position: Position = new Position(null, null);

  cityDTO: City = new City(null, '', this.position, 0, '');
  monument: Monument = new Monument(null, '', '', '', this.position, null, this.cityDTO);

  uploadPhoto: boolean = false;
  photoUrl: string = '';
  uploaded: boolean;

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }
  setAddress: Function = (address: string) => {
    this.monument.address = address;
  };

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
    address: new FormControl(null,[
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(60)
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
    ])
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router, private monumentService: MonumentService) { }

  setChosenCountry(value) {
    this.cityService.getAllByCountryId(value).subscribe(val => this.city = val);
  }

  setChosenCity(value) {
    this.monumentService.cityId = value;
    this.cityService.getById(value).subscribe(val => this.cityDTO = val);
  }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

    create(){
      this.monument.cityDTO = this.cityDTO;
      this.monument.position = this.position;
      this.monumentService.createMonument(this.monument).subscribe(data => {
        this.monument = data;
        this.photoUrl = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/places/' + this.monument.id + '/image';
        this.uploadPhoto = true;
      });
  }

  navigate() {
    this.router.navigateByUrl('/monuments/' + this.monument.id);
  }

}
