import { Component, OnInit, Input } from '@angular/core';
import { PlaceInfo } from 'src/app/model/place-info.model';
import { Position as PositionModel } from 'src/app/model/position.model';
import { MapsAPILoader, GoogleMapsAPIWrapper } from '@agm/core/services';
import { AgmMap } from '@agm/core';

class LngLat {
  lat: number;
  lng: number;

  constructor(lat: number, lng: number) {
    this.lat = lat;
    this.lng = lng;
  }
}

class Waypoint {
  location: LngLat;
  stopover: boolean;

  constructor(location: LngLat, stopover: boolean) {
    this.location = location;
    this.stopover = stopover;
  }
}

class MarkerOption {
  infoWindow: string;
  icon: string;

  constructor(infoWindow: string, icon: string) {
    this.infoWindow = infoWindow;
    this.icon = icon;
  }

}

@Component({
  selector: 'app-route-map',
  templateUrl: './route-map.component.html',
  styleUrls: ['./route-map.component.css']
})
export class RouteMapComponent implements OnInit {

  @Input() set places(places: PlaceInfo[]) {
    this.placeList = places;
    console.log(this.placeList);
    this.getDirection();
  }
  public placeList: PlaceInfo[];
  private lat: Number = 0;
  private lng: Number = 0;
  private icon = 'http://i.imgur.com/7teZKif.png';
  private currentPosition: PositionModel;

  private origin: any = 0;
  private destination: any = 0;
  private waypoints: any = [];
  private renderOptions: any;
  private markerOptions: any;
  private waypointMarkers: MarkerOption;
  private visible: boolean = false;
  travelMode = 'WALKING';
  
  ngOnInit() {
    this.getDirection();
  }

  getDirection() {
    this.origin = null;
    this.destination = null;
    this.waypoints = [];

    console.log(this.placeList);
    const length = this.placeList.length;

    if (length > 0) {
      this.lat = 0;
      this.lng = 0;
      this.visible = false;

      this.currentPosition = <PositionModel> (<unknown> this.placeList[0].position);
      this.origin = new LngLat(this.currentPosition.coordinateX,
       this.currentPosition.coordinateY);
      console.log(this.origin);
      this.lat += <any>(<number>this.currentPosition.coordinateX);
      this.lng += <any>(<number>this.currentPosition.coordinateY);
      let originMarker = new MarkerOption(this.placeList[0].name, this.icon);

      this.currentPosition = <PositionModel> (<unknown> this.placeList[length - 1].position);
      this.destination = new LngLat(this.currentPosition.coordinateX,
       this.currentPosition.coordinateY);
      console.log(this.destination);
      this.lat += <any>(<number>this.currentPosition.coordinateX);
      this.lng += <any>(<number>this.currentPosition.coordinateY);
      let destinationMarker = new MarkerOption(this.placeList[length - 1].name, this.icon);

      for (let i = 1; i < length - 1; i++) {
        this.currentPosition = <PositionModel> (<unknown> this.placeList[i].position);
        this.waypoints.push(new Waypoint(new LngLat(this.currentPosition.coordinateX,
          this.currentPosition.coordinateY), false));
        this.lat += <any>(<number>this.currentPosition.coordinateX);
        this.lng += <any>(<number>this.currentPosition.coordinateY);
        this.waypointMarkers = new MarkerOption(this.placeList[i].name, this.icon);
      }
      this.lat = <any>(<number>(this.lat) /length);
      this.lng = <any>(<number>(this.lng) /length);
      this.renderOptions = {
        suppressMarkers: true,
      };

      this.markerOptions = {
        origin: originMarker,
        waypoints: this.waypointMarkers,
        destination: destinationMarker
      };
    }
  }
}

