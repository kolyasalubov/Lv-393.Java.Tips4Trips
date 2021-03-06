import { Component, OnInit, NgZone, ViewChild, Input} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MapsAPILoader, GoogleMapsAPIWrapper } from '@agm/core/services';
import { AgmMap } from '@agm/core';
declare var google: any;

interface Location {
  latitude: number;
  longitude: number;
  viewport?: Object;
  zoom: number;
  address_level_1?: string;
  address_level_2?: string;
  address_country?: string;
  address_zip?: string;
  address_state?: string;
  marker?: Marker;
}

@Component({
  selector: 'app-map-picker',
  templateUrl: './map-picker.component.html',
  styleUrls: ['./map-picker.component.css']
})
export class MapPickerComponent implements OnInit {
  @Input() addressSearch: boolean = false;
  @Input() citySearch: boolean = false;
  @Input() stateSearch: boolean = false;
  @Input() countrySearch: boolean = false;
  @Input() zipSearch: boolean = false;
  @Input() setAddress: Function;
  @Input() locationUpdate: Function;
  @Input() initialX = 0;
  @Input() initialY = 0;

  geocoder: any;
  public location: Location = {
    latitude: 0,
    longitude: 0,
    marker: {
      latitude: 0,
      longitude: 0,
      draggable: true
    },
    zoom: ZoomLevel.Country
  };
  @ViewChild(AgmMap) map: AgmMap;

  constructor(public mapsApiLoader: MapsAPILoader,
              private zone: NgZone,
              private wrapper: GoogleMapsAPIWrapper,
              private formBind: ReactiveFormsModule) {
    this.mapsApiLoader = mapsApiLoader;
    this.zone = zone;
    this.wrapper = wrapper;
    this.formBind = formBind;
    this.mapsApiLoader.load().then(() => {
      this.geocoder = new google.maps.Geocoder();
    });
    this.location.latitude= this.initialX;
    this.location.longitude= this.initialX;
  }

  ngOnInit() {
    this.location.marker.draggable = true;
  }

  updateOnMap() {
    let full_address:string = this.location.address_level_1 || "";
    if (this.location.address_level_2) full_address = full_address + " " + this.location.address_level_2;
    if (this.location.address_state) full_address = full_address + " " + this.location.address_state;
    if (this.location.address_country) full_address = full_address + " " + this.location.address_country;

    //let outputAddress = this.location.address_level_1 + " " + this.location.address_level_2 + " ";
    //outputAddress.trim();
    //outputAddress.replace('undefined', '');
    //this.setAddress(outputAddress);
    this.findLocation(full_address);
  }

  findLocation(address) {
    if (!this.geocoder) this.geocoder = new google.maps.Geocoder();
    this.geocoder.geocode({
      'address': address
    }, (results, status) => {
      console.log(results);

      if (status == google.maps.GeocoderStatus.OK) {
        for (var i = 0; i < results[0].address_components.length; i++) {
          let types = results[0].address_components[i].types;
 
          if (types.indexOf('locality') != -1) {
            this.location.address_level_2 = results[0].address_components[i].long_name;
          }
          if (types.indexOf('country') != -1) {
            this.location.address_country = results[0].address_components[i].long_name;
          }
          if (types.indexOf('postal_code') != -1) {
            this.location.address_zip = results[0].address_components[i].long_name;
          }
          if (types.indexOf('administrative_area_level_1') != -1) {
            this.location.address_state = results[0].address_components[i].long_name;
          }
        } 
        if (results[0].geometry.location) {
          this.location.latitude = results[0].geometry.location.lat();
          this.location.longitude = results[0].geometry.location.lng();
          this.location.marker.latitude = results[0].geometry.location.lat();
          this.location.marker.longitude = results[0].geometry.location.lng();
          this.location.marker.draggable = true;
          this.location.viewport = results[0].geometry.viewport;
        }
        this.map.triggerResize();
        this.locationUpdate(this.location.marker.latitude,
          this.location.marker.longitude);
        let outputAddress= this.location.address_level_1;
        outputAddress.trim();
        outputAddress.replace('undefined', '');
        this.setAddress(outputAddress);
        outputAddress= undefined;
      } else {
        alert('Sorry, this search produced no results.');
      }
    });
  }

  markerDragEnd(m: any, $event: any) {
    this.location.marker.latitude = m.coords.lat;
    this.location.marker.longitude = m.coords.lng;
    this.locationUpdate(this.location.marker.latitude,
      this.location.marker.longitude);
    this.findAddressByCoordinates();
   }

   findAddressByCoordinates() {
    this.geocoder.geocode({
      'location': {
        lat: this.location.marker.latitude,
        lng: this.location.marker.longitude
      }
    }, (results, status) => {
      this.decomposeAddressComponents(results);
    });
  }

  decomposeAddressComponents = (addressArray) => {
    let outputAddress = '';
    if (addressArray.length == 0) return false;
    let address = addressArray[0].address_components;
 
    for(let element of address) {
      if (element.length == 0 && !element['types'])
        continue;
 
      if (element['types'].indexOf('street_number') > -1) {
        this.location.address_level_1 = element['long_name'];
        //outputAddress += this.location.address_level_1 + " ";
        continue;
      }
      if (element['types'].indexOf('route') > -1) {
        //const location_1_before = this.location.address_level_1;
        this.location.address_level_1 += ', ' + element['long_name'];
        // = outputAddress.replace(location_1_before, this.location.address_level_1);
        continue;
      }
      if (element['types'].indexOf('locality') > -1) {
        this.location.address_level_2 = element['long_name'];
        continue;
      }
      if (element['types'].indexOf('administrative_area_level_1') > -1) {
        this.location.address_state = element['long_name'];
        continue;
      }
      if (element['types'].indexOf('country') > -1) {
        this.location.address_country = element['long_name'];
        continue;
      }
      if (element['types'].indexOf('postal_code') > -1) {
        this.location.address_zip = element['long_name'];
        continue;
      }
    }
    outputAddress= this.location.address_level_1;
    outputAddress.trim();
    outputAddress.replace('undefined', '');
    this.setAddress(outputAddress);
    outputAddress= undefined;
  }

}
