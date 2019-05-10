import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/index";
import { City } from '../../model/city.model';
import { CityFeedback } from 'src/app/model/city-feedback.model';



@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private http: HttpClient) {
  }

  url: string = 'http://localhost:8080/cities';

  getAll(): Observable<City[]> {
    return this.http.get<City[]>(this.url);
  }

  getById(id: number): Observable<City> {
    return this.http.get<City>(this.url + '/' + id);
  }

  createCity(city: City): Observable<City> {
    return this.http.post<City>(this.url + '/create', city);
  }

  deleteById(id: number) {
    this.http.delete(this.url + '/delete/' + id).subscribe((s) => { });
  }

  updateCity(city: City) {
    this.http.put(this.url + '/update', city).subscribe((s) => { console.log(s) });
  }

  saveFeedback(cityFeedback: CityFeedback) {
    this.http.post(this.url + '/addFeedback', cityFeedback).subscribe((s) => { console.log(s) });
  }

  getFeedbacks(cityId: number): Observable<CityFeedback[]>{
    return this.http.get<CityFeedback[]>(this.url + '/getFeedbacks/' + cityId);
  }

}
