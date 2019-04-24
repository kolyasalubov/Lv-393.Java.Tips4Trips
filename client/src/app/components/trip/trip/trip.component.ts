import { Component, OnInit } from '@angular/core';
import {TripService} from './trip.service';
import {Router} from '@angular/router';
import { TripInfoDTO } from 'src/app/model/trip-info';
@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.css']
})
export class TripComponent implements OnInit {

  tripInfoDTO: TripInfoDTO[] = [] ;

  constructor(private tripService: TripService,private router: Router) {
  }


  ngOnInit() {
    this.tripService.getAll().subscribe(data => {
        
      for (let entry of data) {
      this.tripInfoDTO.push(entry);
    }});
  }
}
