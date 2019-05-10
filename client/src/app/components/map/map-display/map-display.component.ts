import { Component, OnInit, Input, NgZone, ViewChild } from '@angular/core';
// import { google } from '@types/googlemaps';
declare var google: any;

@Component({
  selector: 'app-map-display',
  templateUrl: './map-display.component.html',
  styleUrls: ['./map-display.component.css']
})
export class MapDisplayComponent implements OnInit {

  @Input() latitude: number;
  @Input() longitude: number;
  @Input() zoom: ZoomLevel;

  constructor() {}

  ngOnInit() {}

}
