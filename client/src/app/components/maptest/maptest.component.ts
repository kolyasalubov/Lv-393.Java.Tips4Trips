import { Component, OnInit } from '@angular/core';
import { PlaceInfo } from 'src/app/model/place-info.model';

@Component({
  selector: 'app-maptest',
  templateUrl: './maptest.component.html',
  styleUrls: ['./maptest.component.css']
})
export class MaptestComponent implements OnInit {

  zoom: number = ZoomLevel.Country;
  places: PlaceInfo[] = [];
 setXY: Function;
 updateAddress: Function;

  constructor() {
    this.setXY = (x: number, y: number) => {
      console.log(x + " " + y);
    };
    this.updateAddress = (address: string) => {
      console.log('address: '+ address);
    };
  }

  ngOnInit() {
  }

}
