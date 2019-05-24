import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs/index";
import {Hotel} from '../../../model/hotel.model';
import {HotelSearchCriteria} from "../../city-places/hotel-search-criteria.model";


@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }
  private _countryId;
  private _cityId;
  baseUrl: string = "http://localhost:8080/places/hotels/";

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

  createHotel(hotel: Hotel): Observable<Hotel> {
    return this.http.post<Hotel>(this.baseUrl + "create", hotel);
  }

  findById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.baseUrl + id);
  }

  filter(hotelSearchCriteria: HotelSearchCriteria): Observable<Hotel[]> {
    return this.http.post<Hotel[]>(this.baseUrl + "filter", hotelSearchCriteria);
  }

  deleteById(id: number) {
    this.http.delete(this.baseUrl + id).subscribe();
  }

  update(hotel: Hotel): Observable<Hotel> {
    return this.http.put<Hotel>(this.baseUrl + "update", hotel);
  }
}
