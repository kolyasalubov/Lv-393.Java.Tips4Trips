import { Component, OnInit } from '@angular/core';
import { Route } from 'src/app/model/route.model';
import { RouteService } from 'src/app/service/route.service';
import { AccountInfo } from 'src/app/model/account-info.model';
import { PlaceService } from 'src/app/place.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomAuthService } from '../../authentication/custom-auth.service';

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements OnInit {
  route: Route;
  placeName: string;
  constructor(
    private routeService: RouteService,
    private placeService: PlaceService,
    private ngRoute: ActivatedRoute,
    private router: Router,
    private authService: CustomAuthService
  ) {
    this.route = new Route();
    this.route.authorInfo = new AccountInfo();
    this.route.places = [];
  }

  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.routeService.findById(id).subscribe(data => {
      this.route = data;
      this.authService.getCurrentUser().subscribe(account => {
        if (!(account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator"
          || (!this.route.verified && this.route.authorInfo.id == account.id))) {
            this.router.navigate(['routes']);
          }
      });
    });
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
      this.routeService.updateRoute(this.route).subscribe(result => {
        this.route = result;
        this.router.navigate(['routes/' + this.route.id]);
      });
    }
  }

  validate(): boolean {
    return this.route.places.length > 0 && this.route.name != null && this.route.name.length > 5;
  }

  removePlace(id: number): void {
    this.route.places = this.route.places.filter(p => p.id != id);
  }

}
