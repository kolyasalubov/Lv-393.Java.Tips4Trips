import { Component, OnInit } from '@angular/core';
import { CityService } from '../city/city.service';
import { CountryService } from '../country/country.service';
import { Router } from '@angular/router';
import { Country } from '../../model/country.model';
import { CityRating } from 'src/app/model/city-rating.model';


@Component({
  selector: 'app-city-rating',
  templateUrl: './city-rating.component.html',
  styleUrls: ['./city-rating.component.css']
})
export class CityRatingComponent implements OnInit {
  countries: Country[] = null;
  cities: CityRating[] = null;
  closeResult: string;
  

  constructor(private cityService: CityService,
    private countryService: CountryService,
    private router: Router) { }

  ngOnInit() {
    this.cityService.getAllRating().subscribe(data => this.cities = data);
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

}
