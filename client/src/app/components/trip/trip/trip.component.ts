import { Component, OnInit } from '@angular/core';
import {TripService} from './trip.service';
import {Router} from '@angular/router';
import { FindGroupInfoDTO } from 'src/app/model/trip-info';
@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.css']
})
export class TripComponent implements OnInit {

  findGroupInfoDTO: FindGroupInfoDTO[] = [new FindGroupInfoDTO(null,null,null,null,null,null)] ;//= [new TripInfo(null,null,null,null,null,null)];

  constructor(private tripService: TripService,private router: Router) {
  }


  ngOnInit() {
    this.tripService.getAll().subscribe(data => {this.findGroupInfoDTO  [0] = data[0];
        console.log(data);
    });
    console.log(this.findGroupInfoDTO);
  }
}
