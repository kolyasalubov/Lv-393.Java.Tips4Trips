import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Hotel} from "../../model/hotel.model";
import {HotelService} from "../create-post-place/create-hotel/hotel.service";
import {Position} from "../../model/position.model";
import {City} from "../../model/city.model";

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private hotelService: HotelService) { }
  id: number;
  countryId: number;
  cityId: number;
  hotel: Hotel = new Hotel(0, '', '', [], '',
    '', '', '', '','',new Position(0, 0),'',
    new City(0, '', new Position(0, 0), 0, ''),0,0);

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.hotelService.findById(this.id).subscribe(value => this.hotel = value);
  }
}
