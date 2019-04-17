import { Component, OnInit } from '@angular/core';
import { City } from '../../model/city.model';
import { Country } from '../../model/country.model';
import { Position } from "../../model/position.model";
import { CityService } from '../create-post-place/city.service'
import { CountryService } from '../country/country.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {

  city: City = new City(null, null, null, null, null);
  position: Position = new Position(0, 0);
  countries: Country[] = null;
  resultText: string = '';

  constructor(private cityService: CityService,
    private countryService: CountryService) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

  create() {
    this.city.id = null;
    this.city.position = this.position;
    this.cityService.createCity(this.city).subscribe(data => this.city = data);
    this.resultText = 'City created';
  }

  setCountryId(value) {
    this.city.countryId = value;
  }

}
