import {Component, OnInit, ViewChild} from '@angular/core';
import { RouteService } from '../../route.service';
import { ActivatedRoute } from '@angular/router';
import { Route } from '../../model/route.model';
import { AccountInfo } from '../../model/account-info.model';
import { Location } from '@angular/common';
import { PlaceInfo } from '../../model/place-info.model';
import {RouteMapComponent} from "../map/route-map/route-map.component";

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  @ViewChild(RouteMapComponent) child: RouteMapComponent;
  route: Route;
  constructor(
    private routeService: RouteService,
    private ngRoute: ActivatedRoute,
    private location: Location
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
      // this.child.placeList = this.route.places;
      // this.child.getDirection();
    });
  }

  ngAfterViewInit() {
    this.child.placeList = this.route.places;
    this.child.getDirection();
  }

  goBack(): void {
    this.location.back();
  }

  getSelfLink(placeInfo: PlaceInfo): string {
    const url: string[] = placeInfo.self.split("/");
    return url[url.length-2] + "/" + url[url.length - 1];
  }

}
