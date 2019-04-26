import {Component, OnInit} from '@angular/core';
import {CountryService} from "../../country.service";
import {City} from "../../model/city.model";
import {Country} from "../../model/country.model";
import {CityService} from "../create-post-place/city.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private countryService: CountryService, private cityService: CityService, private router: Router) { }

  cities: City[] = null;
  countries: Country[] = null;
  chosenCity: number;

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

  setCityId(value) {
    this.cityService.getAllByCountryId(value).subscribe(val => this.cities = val);
  }

  setChosenCity(value) {
    this.chosenCity = value;
  }

  viewCityPlaces(value) {
    this.router.navigate(["city_places/" + this.chosenCity]);
  }
}
