import { Component, OnInit } from '@angular/core';
import { CityService } from '../city/city.service';
import { Position } from "../../model/position.model";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { City } from '../../model/city.model';
import { CountryService } from '../country/country.service';
import { Country } from 'src/app/model/country.model';


@Component({
  selector: 'app-city-edit',
  templateUrl: './city-edit.component.html',
  styleUrls: ['./city-edit.component.css']
})
export class CityEditComponent implements OnInit {
  id: number;
  city: City = new City(null, null, null, null, null);
  position: Position = new Position(0, 0);
  countries: Country[] = null;
  

  constructor(private cityService: CityService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private countryService: CountryService) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.cityService.getById(this.id).subscribe(data => {
      this.city = data;
      this.position = this.city.position;
    });
  }

  save(): void {
    this.city.position = this.position;
      this.cityService.updateCity(this.city);
      this.router.navigate(['/cities']);
      
  }

  setCountryId(value) {
    this.city.countryId = value;
  }

}
