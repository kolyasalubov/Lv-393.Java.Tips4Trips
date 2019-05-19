import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {Monument} from "../../../model/monument.model";

@Injectable({
  providedIn: 'root'
})
export class MonumentService {

  constructor(private http: HttpClient) { }//todo country id as 1
  private _countryId;
  private _cityId;
  baseUrl: string = "http://localhost:8080/places/monuments/"; //todo URL
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

  createMonument(monument: Monument): Observable<Monument> {
    return this.http.post<Monument>(this.baseUrl + "create", monument);
  }

  findById(id: number): Observable<Monument> {
    return this.http.get<Monument>(this.baseUrl + id);
  }

  findByCity(id: number): Observable<Monument[]> {
    return this.http.get<Monument[]>(this.baseUrl + "city/" + id);
  }

  deleteById(id: number) {
    this.http.delete(this.baseUrl + id).subscribe();
  }

  update(monument: Monument): Observable<Monument> {
    return this.http.put<Monument>(this.baseUrl + "update", monument);
  }
}
