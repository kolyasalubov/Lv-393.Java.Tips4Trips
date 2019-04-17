import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../model/route-info.model';
import { RouteService } from '../route.service';

@Component({
  selector: 'app-list-of-routes',
  templateUrl: './list-of-routes.component.html',
  styleUrls: ['./list-of-routes.component.css']
})
export class ListOfRoutesComponent implements OnInit {

  routes: RouteInfo[] = [];

  constructor(private routeService: RouteService) { }

  ngOnInit() {
    this.routeService.findAll()
      .subscribe(data => this.routes = data);
  }

}
