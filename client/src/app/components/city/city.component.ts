import { Component, OnInit } from '@angular/core';
import { City } from '../../model/city.model';
import { Position } from "../../model/position.model";
import { CityService } from './city.service';
import { CountryService } from '../country/country.service';
import { Country } from 'src/app/model/country.model';
import { Router } from '@angular/router';

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
    private countryService: CountryService,
    private router: Router) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

  create() {
    this.cityService.findByName(this.city.name).subscribe(city => {
      if (city === null) {
        this.city.id = null;
        this.city.position = this.position;
        this.cityService.createCity(this.city).subscribe(data => this.city = data);
        this.router.navigate(['account']);
      } else {
        this.resultText = 'City with name "' + this.city.name + '" already exists!';
      }
    });
  }

  setCountryId(value) {
    this.city.countryId = value;
  }

  setCoordinates: Function = (x: number, y: number) => {
    this.position.coordinateX = x;
    this.position.coordinateY = y;
  }

}
