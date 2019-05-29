import {Component, Input, OnInit} from '@angular/core';
import {Route} from "../../../model/route.model";

@Component({
  selector: 'app-little-route',
  templateUrl: './little-route.component.html',
  styleUrls: ['./little-route.component.css']
})
export class LittleRouteComponent implements OnInit {
  @Input() route: Route;

  date: number;
  year: number;
  month: any;

  constructor() {
  }

  ngOnInit() {
    let input = new Date(this.route.creationDate);
    this.date = input.getDay();
    this.year = input.getFullYear();
    this.month = input.toLocaleString('en-us', {month: "long"});
  }

}
