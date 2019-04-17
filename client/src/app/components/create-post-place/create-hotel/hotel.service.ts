import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Hotel} from '../../../model/hotel.model';


@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }//todo country id as 1
  private _countryId;
  private _cityId;
  baseUrl: string = "http://localhost:8080/countries/1/cities/1/places/hotels/"; //todo URL
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
    this.baseUrl = "http://localhost:8080/countries/1/cities/" + value +"/places/hotels/create";
    this._cityId = value;
  }

  createHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.post<Hotel>(this.baseUrl, hotel);
  }

  findById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.baseUrl + id);
  }


}
