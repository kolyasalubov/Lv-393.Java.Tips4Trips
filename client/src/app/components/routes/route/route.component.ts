import { Component, OnInit } from '@angular/core';
import { RouteService } from 'src/app/service/route.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Route } from 'src/app/model/route.model';
import { AccountInfo } from 'src/app/model/account-info.model';
import { Location } from '@angular/common';
import { PlaceInfo } from 'src/app/model/place-info.model';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent implements OnInit {

  route: Route;
  hasAuthority: boolean = false;
  constructor(
    private routeService: RouteService,
    private authService: AuthService,
    private ngRoute: ActivatedRoute,
    private router: Router,
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
    });
    this.authService.getCurrentUser().subscribe(account => {
      if (account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator") {
        this.hasAuthority = true;
      }
    });
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

}
