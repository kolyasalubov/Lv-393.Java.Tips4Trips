import { Component, OnInit } from '@angular/core';
import { RouteService } from 'src/app/route.service';
import { ActivatedRoute } from '@angular/router';
import { Route } from 'src/app/model/route.model';
import { AccountInfo } from 'src/app/model/account-info.model';

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  route: Route;
  constructor(
    private routeService: RouteService,
    private ngRoute: ActivatedRoute
  ) { 
    this.route = new Route();
    this.route.authorInfo = new AccountInfo();
    this.route.places = [];
  }

  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.routeService.findById(id).subscribe(data => {
      this.route = data;
      this.route.authorInfo = data.authorInfo;
      this.route.places = data.places;
    });
  }

}
