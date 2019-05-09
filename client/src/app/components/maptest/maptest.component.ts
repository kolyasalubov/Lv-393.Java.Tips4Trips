import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-maptest',
  templateUrl: './maptest.component.html',
  styleUrls: ['./maptest.component.css']
})
export class MaptestComponent implements OnInit {

  zoom: number = ZoomLevel.Country;
  constructor() { }

  ngOnInit() {
  }

}
