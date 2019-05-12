import { Component, OnInit } from '@angular/core';
import {CityService} from "../create-post-place/city.service";
import {PlaceService} from "../../place.service";
import {PlaceInfo} from "../../model/place-info.model";
import {ActivatedRoute} from "@angular/router";
import {RestaurantSearchCriteria} from "./restaurant-search-criteria.model";
import {RestaurantService} from "../create-post-place/create-restaurant/restaurant.service";
import {HotelService} from "../create-post-place/create-hotel/hotel.service";
import {HotelSearchCriteria} from "./hotel-search-criteria.model";
import {MonumentService} from "../create-post-place/create-monument/monument.service";

@Component({
  selector: 'app-city-places',
  templateUrl: './city-places.component.html',
  styleUrls: ['./city-places.component.css']
})
export class CityPlacesComponent implements OnInit {

  constructor(private placeService: PlaceService, private activatedRoute: ActivatedRoute,
              private restaurantService: RestaurantService, private hotelService: HotelService,
              private monumentService: MonumentService) { }

  places: any[];
  categorizedPlaces: any[];
  cityId: number;
  categories: string[];
  selectedCategory: string = "all";
  restaurantFilter: RestaurantSearchCriteria = new RestaurantSearchCriteria(null, "", null, null);
  hotelFilter: HotelSearchCriteria = new HotelSearchCriteria(null, "", null);

  ngOnInit() {
    this.places = new Array();
    this.categories = new Array();
    this.activatedRoute.paramMap.subscribe(params => {
      this.cityId = +params.get('id');
    });
    this.placeService.getAllByCityId(this.cityId).subscribe(data => {
      this.places = data;

      for (let place of this.places) {
        place.imageUrl =  'http://localhost:8080/places/' + place.id + '/image';
      }
    });
  }


  transferSelfUrl(url: string): string {
    const urlArr = url.split('/');
    return urlArr[urlArr.length - 2] + "/" + urlArr[urlArr.length - 1];
  }

  filterRestaurants() {
    this.restaurantFilter.cityId = this.cityId;
    this.restaurantService.filter(this.restaurantFilter).subscribe(data => {
      this.categorizedPlaces = data;

      for (let place of this.categorizedPlaces) {
        place.imageUrl =  'http://localhost:8080/places/' + place.id + '/image';
      }
    });
  }

  filterHotels() {
    this.hotelFilter.cityId = this.cityId;
    this.hotelService.filter(this.hotelFilter).subscribe(data => {
      this.categorizedPlaces = data;

      for (let place of this.categorizedPlaces) {
        place.imageUrl =  'http://localhost:8080/places/' + place.id + '/image';
      }
    });
  }

  filterMonuments() {
    this.monumentService.findByCity(this.cityId).subscribe(data => {
      this.categorizedPlaces = data;

      for (let place of this.categorizedPlaces) {
        place.imageUrl =  'http://localhost:8080/places/' + place.id + '/image';
      }
    });
  }

  selectCategory(value: string) {
    this.selectedCategory = value;
    if (value === "restaurants") {
      this.filterRestaurants();
    } else if (value === "hotels") {
      this.filterHotels();
    } else if (value === "monuments") {
      this.filterMonuments();
    }
  }

  clearRestaurantFilter() {
    this.restaurantFilter.worksAt = "";
    this.restaurantFilter.averageBill = null;
    this.restaurantFilter.hasVeganFood = null;
    this.filterRestaurants();
  }

  clearHotelFilter() {
    this.hotelFilter.priceIn = null;
    this.hotelFilter.worksAt = "";
    this.filterHotels();
  }
}
