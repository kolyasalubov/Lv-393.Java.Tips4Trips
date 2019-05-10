import { Component, OnInit } from '@angular/core';
import { CountryService } from '../country/country.service';
import { Position } from "../../model/position.model";
import { Router } from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import { Country } from '../../model/country.model';

@Component({
  selector: 'app-country-edit',
  templateUrl: './country-edit.component.html',
  styleUrls: ['./country-edit.component.css']
})
export class CountryEditComponent implements OnInit {
  id: number;
  country: Country = new Country(null, null, null);
  position: Position = new Position(0, 0);

  constructor(private countryService: CountryService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.countryService.getById(this.id).subscribe(data => {
      this.country = data;
      this.position = this.country.position;
    });
  }

  save(): void {
    this.country.position = this.position;
      this.countryService.updateCountry(this.country);
      this.router.navigate(['/countries']);
      
  }

}
