import { Component, OnInit } from '@angular/core';
import {FindGroupDetailsDTO} from '../../../model/trip-details'
import {TripInfoService} from './trip-info.service'
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';
import {PlaceInfo} from "../../../model/place-info.model";

@Component({
  selector: 'app-trip-info',
  templateUrl: './trip-info.component.html',
  styleUrls: ['./trip-info.component.css']
})
export class TripInfoComponent implements OnInit {

  findGroupDetailsDTO: FindGroupDetailsDTO = new FindGroupDetailsDTO(null,"default",null,null,null,null,null,null,null);


  constructor(private tripInfoService: TripInfoService,    private ngRoute: ActivatedRoute, private location: Location) { }


  ngOnInit() {
    //const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    console.log(id);
    this.tripInfoService.findById(id).subscribe(data =>{


      this.findGroupDetailsDTO.id = data.id;
      this.findGroupDetailsDTO.name = data.name;
      this.findGroupDetailsDTO.creationDate = data.creationDate;
      this.findGroupDetailsDTO.startDate = data.startDate;
      this.findGroupDetailsDTO.description = data.description;
      this.findGroupDetailsDTO.self = data.self;
      this.findGroupDetailsDTO.route = data.route;
      this.findGroupDetailsDTO.creator = data.creator;
      this.findGroupDetailsDTO.subscribers = data.subscribers;
      //this.findGroupDetailsDTO.route[0] = data.route[0];

      console.log(data);
    });
    console.log(this.findGroupDetailsDTO);
  }

  getSelfLink(placeInfo: PlaceInfo): string {
    const url: string[] = placeInfo.self.split("/");
    return url[url.length-2] + "/" + url[url.length - 1];
  }

  goBack(): void {
    this.location.back();
  }
  }


