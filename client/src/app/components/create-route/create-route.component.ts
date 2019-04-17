import { Component, OnInit } from '@angular/core';
import { Route } from 'src/app/model/route.model';
import { RouteService } from 'src/app/route.service';
import { AccountInfo } from 'src/app/model/account-info.model';
import { PlaceService } from 'src/app/place.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-post-route',
  templateUrl: './create-route.component.html',
  styleUrls: ['./create-route.component.css']
})
export class CreateRouteComponent implements OnInit {

  route: Route;
  placeName: string;
  constructor(
    private routeService: RouteService,
    private placeService: PlaceService,
    private router: Router
  ) {
    this.route = new Route();
    this.route.authorInfo = new AccountInfo();
    this.route.places = [];
  }

  ngOnInit() {
  }

  addPlace(): void {
    this.placeService.findByName(this.placeName).subscribe(data => {
      if (data.length != 0) {
        if (!this.route.places.map(place => place.id).includes(data[0].id)) {
          this.route.places.push(data[0]);
          this.placeName = null;
        }
      }
    });
  }

  save(): void {
    if (this.validate()) {
      this.route.photoPath="no photo";
      this.routeService.createRoute(this.route).subscribe(result=>console.log(result));
      setTimeout(() => {this.router.navigate(['routes']);}, 2000);
    }
  }

  validate(): boolean {
    return this.route.places.length > 0 && this.route.name != null && this.route.name.length > 5;
  }

  removePlace(id: number): void {
    this.route.places = this.route.places.filter(p => p.id != id);
  }
}
