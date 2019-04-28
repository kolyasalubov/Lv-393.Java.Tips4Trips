import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Country } from '../../model/country.model';

@Injectable({
    providedIn: 'root'
})
export class CountryService {

    url: string = "http://localhost:8080/countries";

    constructor(private http: HttpClient) { }

    createCountry(country: Country): Observable<Country> {
        return this.http.post<Country>(this.url + '/create', country);
    }

    getAll(): Observable<Country[]> {
        return this.http.get<Country[]>(this.url);
    }

    deleteById(id: any) {
        this.http.delete(this.url + '/delete/' + id).subscribe((s) => {});
    }

    updateCountry(country: Country): Observable<Country> {
        return this.http.put<Country>(this.url + "/update", country);
      }

}
