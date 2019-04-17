import { Component, OnInit } from '@angular/core';
import { CityService } from '../city.service';
import {Router} from '@angular/router';
import { City } from 'src/app/model/city.model';

@Component({
  selector: 'app-create-hotel',
  templateUrl: './create-hotel.component.html',
  styleUrls: ['./create-hotel.component.css']
})
export class CreateHotelComponent implements OnInit {

  
  city: City[] = null;

  constructor(private cityService: CityService,private router: Router) {
  }
  ngOnInit() {

    this.cityService.getAll().subscribe(data => this.city = data);

  }

}
