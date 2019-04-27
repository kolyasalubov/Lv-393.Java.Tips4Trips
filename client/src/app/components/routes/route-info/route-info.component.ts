import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RouteInfo } from 'src/app/model/route-info.model';

@Component({
  selector: 'app-route-info',
  templateUrl: './route-info.component.html',
  styleUrls: ['./route-info.component.css']
})
export class RouteInfoComponent implements OnInit {

  @Input() routeInfo: RouteInfo;
  @Input() displayDeleteButton: boolean = false;
  @Output() delete = new EventEmitter();
  constructor() { }

  ngOnInit() {
  }

}
