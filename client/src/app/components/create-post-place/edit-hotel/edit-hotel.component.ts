import { Component, OnInit } from '@angular/core';
import {City} from "../../../model/city.model";
import {Country} from "../../../model/country.model";
import {Position} from "../../../model/position.model";
import {Hotel} from "../../../model/hotel.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CountryService} from "../../../country.service";
import {CityService} from "../city.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HotelService} from "../create-hotel/hotel.service";


@Component({
  selector: 'app-edit-hotel',
  templateUrl: './edit-hotel.component.html',
  styleUrls: ['./edit-hotel.component.css']
})
export class EditHotelComponent implements OnInit {

  workingDays: string[] = ["MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];


  position: Position = new Position(0, 0);

  hotel: Hotel = new Hotel(null, '', '', [], '',
    '', '', '','', null,null,
    null,0,0);

  id: number;

  uploadPhoto: boolean = false;
  photoUrl: string = '';
  imageURLs: string[] = new Array();

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }
  setAddress: Function = (address: string) => {
    this.hotel.address = address;
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
    workingDays: new FormControl(null, [
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
    minimumPrice: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(5)
    ]),
    maximumPrice: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(5)
    ]),
    positionX: new FormControl(null,[
      Validators.required
    ]),
    positionY: new FormControl(null,[
      Validators.required
    ])
  });

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router,
              private hotelService: HotelService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.hotelService.findById(this.id).subscribe(data => {
      this.hotel = data;
      this.position = data.position;
      this.imageURLs.push('http://localhost:8080/places/' + this.hotel.id + '/image');
    });
  }

  update(){
    this.hotel.position = this.position;
    this.hotelService.update(this.hotel).subscribe(data => {
      this.hotel = data;
      this.photoUrl = 'http://localhost:8080/places/' + this.hotel.id + '/image';
      this.uploadPhoto = true;
    });
    // setTimeout(() => {window.location.href = '/hotels/' + this.hotel.id;}, 2000);
  }

  setWorkingDays(day: string) {
    if (this.hotel.workingDays.includes(day)) {
      this.hotel.workingDays.splice(this.hotel.workingDays.indexOf(day), 1);
    } else {
      this.hotel.workingDays.push(day);
    }
  }

}
