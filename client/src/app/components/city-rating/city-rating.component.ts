import { Component, OnInit } from '@angular/core';
import { CityService } from '../city/city.service';
import { CountryService } from '../country/country.service';
import { Router } from '@angular/router';
import { Country } from '../../model/country.model';
import { CityRating } from 'src/app/model/city-rating.model';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-city-rating',
  templateUrl: './city-rating.component.html',
  styleUrls: ['./city-rating.component.css']
})
export class CityRatingComponent implements OnInit {
  countries: Country[] = null;
  cities: CityRating[] = null;
  closeResult: string;
  citiesCount: number;
  zoom: number = ZoomLevel.City;
  page: number = 0;
  prevPage: number;
  nextPage: number;
  pagesBefore: Array<number>;
  pagesAfter: Array<number>;
  pageSize: number = 6;
  countryId: number = -1;

  constructor(private cityService: CityService,
    private countryService: CountryService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.page = + params.get('page');
      if (this.countryId === -1) {
        this.cityService.getAllRating(this.page).subscribe(data => this.cities = data);
        this.cityService.countAllRating().subscribe(data => this.handlePaging(data / this.pageSize));
      } else {
        this.cityService.getAllRatingByCountryId(this.page, this.countryId).subscribe(data => this.cities = data);
        this.cityService.countAllRatingByCountryId(this.countryId).subscribe(data => this.handlePaging(data / this.pageSize));
      }
      this.countryService.getAll().subscribe(data => this.countries = data);
    })
  }

  handlePaging(pagesCount: number) {
    this.pagesBefore = Array.from(Array<number>(this.page - 1), (x, index) => index + 1);
    this.pagesAfter = Array.from(Array(Math.ceil(pagesCount - this.page)), (x, index) => this.page + index + 1);
    if (this.page < pagesCount) {
      this.nextPage = this.page + 1;
    } else {
      this.nextPage = this.page;
    }

    if (this.page - 1 > 0) {
      this.prevPage = this.page - 1;
    } else {
      this.prevPage = 1;
    }
  }

  setCountryId(value: number) {
    this.countryId = value;
    this.ngOnInit();
  }

  resetCountry() {
    this.countryId = -1;
    this.ngOnInit();
  }

}
