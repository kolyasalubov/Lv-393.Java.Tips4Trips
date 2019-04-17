import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Country } from '../../model/country.model';
import { Position } from "../../model/position.model";
import { CountryService } from './country.service'

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {

  country: Country = new Country(null, null, null);
  position: Position = new Position(0, 0);
  resultText: string = '';

  constructor(private countryService: CountryService, private router: Router) { }

  ngOnInit() {
  }

  create() {
    this.country.id = null;
    this.country.position = this.position;
    this.countryService.createCountry(this.country).subscribe(data => this.country = data);
    this.resultText = 'Country created';
  }

}
