import { Component, OnInit } from '@angular/core';
import { PlaceInfo } from 'src/app/model/place-info.model';
import { Position as PositionDTO } from 'src/app/model/position.model';

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
  //   this.places =[
  //     {
  //       id: 1,
  //       name: 'A',
  //       description: 'fkflflf',
  //       self: 'dldldl',
  //       category: 'kfkf',
  //       position: new PositionDTO(5,5);
  //     }
  //   ];
    // this.setXY = (x: number, y: number) => {
    //   console.log(x + " " + y);
    // };
    // this.updateAddress = (address: string) => {
    //   console.log('address: '+ address);
    // };
  }

  ngOnInit() {
  }

}
