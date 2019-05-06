import {Component, Input, OnInit} from '@angular/core';
import {LittlepostModel} from "../../../model/littlepost.model";
import {TripInfoDTO} from "../../../model/trip-info";

@Component({
  selector: 'app-list-trips',
  templateUrl: './list-trips.component.html',
  styleUrls: ['./list-trips.component.css']
})
export class ListTripsComponent implements OnInit {

  @Input() postP:TripInfoDTO[]=null;

  constructor() { }

  ngOnInit() {
  }

}
