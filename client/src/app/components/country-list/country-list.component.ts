import { Component, OnInit, Input } from '@angular/core';
import { CountryService } from '../country/country.service';
import { Country } from '../../model/country.model';

@Component({
  selector: 'app-country-list',
  templateUrl: './country-list.component.html',
  styleUrls: ['./country-list.component.css']
})
export class CountryListComponent implements OnInit {
  countries: Country[] = null;
  closeResult: string;

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    this.countryService.getAll().subscribe(data => this.countries = data);
  }

  onClickMe(id: any) {
    this.countryService.deleteById(id);
    console.log("test", id);
  }

  update(country: Country) {
    this.countryService.updateCountry;
  }
}
