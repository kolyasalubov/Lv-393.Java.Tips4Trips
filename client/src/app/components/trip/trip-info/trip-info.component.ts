import { Component, OnInit } from '@angular/core';
import {FindGroupDetailsDTO} from '../../../model/trip-details'
import {TripInfoService} from './trip-info.service'

@Component({
  selector: 'app-trip-info',
  templateUrl: './trip-info.component.html',
  styleUrls: ['./trip-info.component.css']
})
export class TripInfoComponent implements OnInit {

  findGroupDetailsDTO: FindGroupDetailsDTO = new FindGroupDetailsDTO(null,"default",null,null,null,null,null,null,null);


  constructor(private tripInfoService: TripInfoService) { }


  ngOnInit() {
    //const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.tripInfoService.findById(1).subscribe(data =>{


      this.findGroupDetailsDTO.id = data.id;
      this.findGroupDetailsDTO.name = data.name;
      this.findGroupDetailsDTO.creationDate = data.creationDate;
      this.findGroupDetailsDTO.startDate = data.startDate;
      this.findGroupDetailsDTO.description = data.description;
      this.findGroupDetailsDTO.self = data.self;
      this.findGroupDetailsDTO.route = data.route;
      this.findGroupDetailsDTO.creator = data.creator;
      this.findGroupDetailsDTO.subscribers = data.subscribers;


      console.log(data);
    });
    console.log(this.findGroupDetailsDTO);
  }
  }


