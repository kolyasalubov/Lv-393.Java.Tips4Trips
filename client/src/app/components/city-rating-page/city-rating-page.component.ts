import { Component, OnInit } from '@angular/core';
import { CityService } from '../city/city.service';
import { City } from '../../model/city.model';
import { Router } from '@angular/router';
import { Position } from "../../model/position.model";
import { ActivatedRoute } from "@angular/router";
import { CityFeedback } from 'src/app/model/city-feedback.model';

@Component({
  selector: 'app-city-rating-page',
  templateUrl: './city-rating-page.component.html',
  styleUrls: ['./city-rating-page.component.css']
})
export class CityRatingPageComponent implements OnInit {
  id: number;
  city: City = new City(0, null, null, 0, null);
  cityFeedback: CityFeedback = new CityFeedback(0, null, null, null,  0, 0, 0, 0, 0, 0);
  cityFeedbacks: CityFeedback[] = null;
  position: Position = new Position(0, 0);
  closeResult: string;
  cityRating: number;
  zoom: number = ZoomLevel.City;

  constructor(private cityService: CityService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.cityService.getById(this.id).subscribe(data => {
      this.city = data;
      this.position = this.city.position;
    });
    this.cityService.getFeedbacks(this.id).subscribe(data => this.cityFeedbacks = data);
    this.cityService.getCityRating(this.id).subscribe(data => this.cityRating = data);
  }

  saveFeedback() {
    console.log('before');
    this.cityFeedback.cityId = this.city.id;
    this.cityService.saveFeedback(this.cityFeedback);
    console.log(this.cityFeedback);
    window.location.reload();
  }

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }

}
