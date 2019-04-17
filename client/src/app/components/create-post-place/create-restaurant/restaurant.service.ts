import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Restaurant} from "../../../model/restaurant.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }//todo country id as 1
  private _countryId;
  private _cityId;
  baseUrl: string = "http://localhost:8080/countries/1/cities/1/places/restaurants/"; //todo URL
  //Todo all methods

  get countryId() {
    return this._countryId;
  }

  set countryId(value) {
    this._countryId = value;
  }

  get cityId() {
    return this._cityId;
  }

  set cityId(value) {
    this.baseUrl = "http://localhost:8080/countries/1/cities/" + value +"/places/restaurants/create";
    this._cityId = value;
  }

  createRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.baseUrl, restaurant);
  }

//??????????????????
  updateRestaurant(myRestaurant: Restaurant): Observable<Restaurant> {
    console.log(myRestaurant);
    return this.http.post<Restaurant>(this.baseUrl + "update", Restaurant);
  }

  findById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(this.baseUrl + id);
  }
}
