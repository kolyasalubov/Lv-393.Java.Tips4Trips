import { Component, OnInit } from '@angular/core';
import { CityService } from '../city/city.service';
import { City } from '../../model/city.model';
import { CountryService } from '../country/country.service';
import { Router } from '@angular/router';
import { Country } from '../../model/country.model';


@Component({
  selector: 'app-city-rating',
  templateUrl: './city-rating.component.html',
  styleUrls: ['./city-rating.component.css']
})
export class CityRatingComponent implements OnInit {
  countries: Country[] = null;
  cities: City[] = null;
  closeResult: string;

  constructor(private cityService: CityService,
    private countryService: CountryService,
    private router: Router) { }

  ngOnInit() {
    this.cityService.getAll().subscribe(data => this.cities = data);
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

}
