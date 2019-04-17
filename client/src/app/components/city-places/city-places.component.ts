import { Component, OnInit } from '@angular/core';
import {CityService} from "../create-post-place/city.service";
import {PlaceService} from "../../place.service";
import {PlaceInfo} from "../../model/place-info.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-city-places',
  templateUrl: './city-places.component.html',
  styleUrls: ['./city-places.component.css']
})
export class CityPlacesComponent implements OnInit {

  constructor(private placeService: PlaceService, private activatedRoute: ActivatedRoute) { }

  places: PlaceInfo[] = null;
  cityId: number;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.cityId = +params.get('id');
    });
    this.placeService.getAllByCityId(this.cityId).subscribe(data => this.places = data);
  }

  transferSelfUrl(url: string): string {
    const urlArr = url.split('/');
    return urlArr[urlArr.length - 2] + "/" + urlArr[urlArr.length - 1];
  }

}
