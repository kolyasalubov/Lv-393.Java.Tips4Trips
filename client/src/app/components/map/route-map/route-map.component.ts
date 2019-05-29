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
  public newPlaceList: PlaceInfo[] =[];
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

  private optimizationDestination: PositionModel;
  private optimizationWaypoint: PositionModel;
  
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

  optimize() {
    const map = new Map<number,PlaceInfo>();

    let length = this.placeList.length;
    map.set(0, this.placeList[0]);
    const destination = this.placeList[0];

    for(let i=1; i<length; i++) {
      map.set(this.decartLength(destination, this.placeList[i]),
        this.placeList[i]);
    }

    console.log(map);
    let mapkeys = [];
    map.forEach((value: PlaceInfo,key: number) => {
      mapkeys.push(key);
    });

    console.log(mapkeys);
    mapkeys.sort((n1,n2)=> {
      if (n1 > n2) return 1;
      else if (n1 == n2) return 0;
      else return -1;
    });

    console.log(mapkeys);
    this.newPlaceList = [];
    for(let key of mapkeys) {
      this.newPlaceList.push(map.get(key));
    }
    console.log(this.newPlaceList);
  }

  decartLength(destination: PlaceInfo, waypoint: PlaceInfo) {
    this.optimizationDestination = <PositionModel> (<unknown> destination.position);
    this.optimizationWaypoint = <PositionModel> (<unknown> waypoint.position);

    let lang = Math.pow(this.optimizationDestination.coordinateX -
        this.optimizationWaypoint.coordinateX, 2) +
      Math.pow(this.optimizationDestination.coordinateY -
        this.optimizationWaypoint.coordinateY, 2);
    return Math.sqrt(lang);
  }
}

