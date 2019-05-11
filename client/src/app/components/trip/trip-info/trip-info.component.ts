import { Component, OnInit } from '@angular/core';
import {TripDetailsDTO} from '../../../model/trip-details'
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

  tripDetailsDTO: TripDetailsDTO = new TripDetailsDTO(null,"default",null,null,null,null,null,null,null,null);


  constructor(private tripInfoService: TripInfoService,    private ngRoute: ActivatedRoute, private location: Location) { }


  ngOnInit() {
    //const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    console.log(id);
    this.tripInfoService.findById(id).subscribe(data =>{


      this.tripDetailsDTO.id = data.id;
      this.tripDetailsDTO.name = data.name;
      this.tripDetailsDTO.creationDate = data.creationDate;
      this.tripDetailsDTO.startDate = data.startDate;
      this.tripDetailsDTO.description = data.description;
      this.tripDetailsDTO.self = data.self;
      this.tripDetailsDTO.route = data.route;
      this.tripDetailsDTO.creator = data.creator;
      this.tripDetailsDTO.subscribers = data.subscribers;
      this.tripDetailsDTO.chatId = data.chatId;

    });
    console.log(this.tripDetailsDTO);
  }

  getSelfLink(placeInfo: PlaceInfo): string {
    const url: string[] = placeInfo.self.split("/");
    return url[url.length-2] + "/" + url[url.length - 1];
  }

  goBack(): void {
    this.location.back();
  }
  }


