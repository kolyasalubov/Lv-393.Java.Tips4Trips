import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Restaurant} from "../../../model/restaurant.model";
import {RestaurantSearchCriteria} from "../../city-places/restaurant-search-criteria.model";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }//todo country id as 1
  private _countryId;
  private _cityId;
  baseUrl: string = "http://localhost:8080/places/restaurants/"; //todo URL
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
    this._cityId = value;
  }

  createRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.baseUrl + "create", restaurant);
  }

  findById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(this.baseUrl + id);
  }

  filter(restaurantFilter: RestaurantSearchCriteria): Observable<Restaurant[]> {
    return this.http.post<Restaurant[]>(this.baseUrl + "filter", restaurantFilter);
  }

  deleteById(id: number) {
    this.http.delete(this.baseUrl + id).subscribe();
  }

  update(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.put<Restaurant>(this.baseUrl + "update", restaurant);
  }
}
