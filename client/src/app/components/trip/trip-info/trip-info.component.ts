import { Component, OnInit } from '@angular/core';
import {FindGroupDetailsDTO} from '../../../model/trip-details'
import {TripInfoService} from './trip-info.service'

@Component({
  selector: 'app-trip-info',
  templateUrl: './trip-info.component.html',
  styleUrls: ['./trip-info.component.css']
})
export class TripInfoComponent implements OnInit {

  FindGroupDetailsDTO: FindGroupDetailsDTO = null;


  constructor(private tripInfoService: TripInfoService) { }


  ngOnInit() {
    //const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.tripInfoService.findById(1).subscribe(data =>console.log(data));
    console.log(this.FindGroupDetailsDTO);
  }
  }


