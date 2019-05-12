import {Component, OnInit, ViewChild} from '@angular/core';
import { Route } from 'src/app/model/route.model';
import { RouteService } from 'src/app/service/route.service';
import { AccountInfo } from 'src/app/model/account-info.model';
import { PlaceService } from 'src/app/place.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { FormControl } from '@angular/forms';
import {RouteMapComponent} from "../../map/route-map/route-map.component";

@Component({
  selector: 'app-edit-route',
  templateUrl: './edit-route.component.html',
  styleUrls: ['./edit-route.component.css']
})
export class EditRouteComponent implements OnInit {
  @ViewChild(RouteMapComponent) private child: RouteMapComponent;
  route: Route;
  placeName: string;
  myControl = new FormControl();
  filteredOptions: string[];
  options: string[] = [];
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
      this.child.placeList = this.route.places;
      this.child.getDirection();
      this.authService.getCurrentUser().subscribe(account => {
        if (!(account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator"
          || (!this.route.verified && this.route.authorInfo.id == account.id))) {
            this.router.navigate(['routes']);
          }
      });
    });
    this.myControl.valueChanges
      .subscribe(value=> {
        this.placeName = value;
        if (value != null && value.trim() != '')
        this.placeService.getNamesContaining(value).subscribe(data => this.filteredOptions = data);
      });
  }

  addPlace(): void {
    this.placeService.findByName(this.placeName).subscribe(data => {
      if (data.length != 0) {
        this.myControl.reset();
        if (!this.route.places.map(place => place.id).includes(data[0].id)) {
          this.route.places.push(data[0]);
          this.placeName = null;
          this.child.placeList = this.route.places;
          this.child.getDirection();
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
    this.child.placeList = this.route.places;
    this.child.getDirection();
  }

}
