import {Component, OnInit, ViewChild} from '@angular/core';
import { RouteService } from 'src/app/service/route.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Route } from 'src/app/model/route.model';
import { AccountInfo } from 'src/app/model/account-info.model';
import { Location } from '@angular/common';
import { PlaceInfo } from 'src/app/model/place-info.model';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { AccountDTO } from '../../../model/account.model';
import {RouteMapComponent} from "../../map/route-map/route-map.component";

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {
  //@ViewChild(RouteMapComponent) child: RouteMapComponent;
  route: Route;
  hasAuthority: boolean = false;
  currUser: AccountDTO;
  constructor(
    private routeService: RouteService,
    private authService: CustomAuthService,
    private ngRoute: ActivatedRoute,
    private router: Router,
    private location: Location
  ) {
    this.route = new Route();
    this.route.places = [];
  }

  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.routeService.findById(id).subscribe(data => {
      this.route = data;
      for (let place of this.route.places) {
        place.imageUrl =  'http://localhost:8080/places/' + place.id + '/image';
      }
      this.route.authorInfo = data.authorInfo;
      this.route.places = data.places;
    });
    this.authService.getCurrentUser().subscribe(account => {
      this.currUser = account;
      if (account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator") {
        this.hasAuthority = true;
      }
    });
  }

  ngAfterViewInit() {
  }

  goBack(): void {
    this.location.back();
  }

  getSelfLink(placeInfo: PlaceInfo): string {
    const url: string[] = placeInfo.self.split("/");
    return url[url.length - 2] + "/" + url[url.length - 1];
  }

  delete(): void {
    this.routeService.deleteRoute(this.route.id)
      .subscribe(data => this.router.navigate(['routes']));
  }

  edit(): void {
    this.router.navigate(['routes/' + this.route.id + '/edit']);
  }

  verify(): void {
    this.routeService.verifyRoute(this.route.id).subscribe(data => this.route.verified = true);
  }

}
