import {Component, OnInit, ViewChild} from '@angular/core';
import { Route } from 'src/app/model/route.model';
import { RouteService } from 'src/app/service/route.service';
import { AccountInfo } from 'src/app/model/account-info.model';
import { PlaceService } from 'src/app/place.service';
import { Router } from '@angular/router';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { startWith, map } from 'rxjs/operators';
import {RouteMapComponent} from "../../map/route-map/route-map.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-post-route',
  templateUrl: './create-route.component.html',
  styleUrls: ['./create-route.component.css']
})
export class CreateRouteComponent implements OnInit {

  @ViewChild(RouteMapComponent) private child: RouteMapComponent;
  route: Route;
  placeName: string;
  myControl = new FormControl();
  filteredOptions: string[];
  options: string[] = [];
  alerts: string[] = [];
  formGroup: FormGroup = new FormGroup({
    name: new FormControl(null,[
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(35)
    ])
  });
  constructor(
    private routeService: RouteService,
    private placeService: PlaceService,
    private router: Router,
    private authService: CustomAuthService
  ) {
    this.route = new Route();
    this.route.authorInfo = new AccountInfo();
    this.route.places = [];
  }

  ngOnInit() {
    if (!this.authService.checkLoggedUser()) {
      this.router.navigate(['account']);
    } else {
      this.authService.getCurrentUser().subscribe(data => {
        this.route.authorInfo.id = data.id;
      });
      this.myControl.valueChanges
      .subscribe(value=> {
        this.placeName = value;
        if (value != null && value.trim() != '')
        this.placeService.getNamesContaining(value).subscribe(data => this.filteredOptions = data);
      });
    }
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
      this.routeService.createRoute(this.route).subscribe(result => {
        this.route = result;
        this.router.navigate(['routes/' + result.id]);
      });
    }
  }

  validate(): boolean {
    this.resetAlerts();
    let valid = true;
    if (this.route.name == null || this.route.name.length < 6) {
      this.alerts.push("Route name has to be at least 6 characters!");
      valid = false;
    } else if (this.route.name.length > 30) {
      this.alerts.push("Route name has to be less than 35 characters!");
      valid = false;
    }
    if (this.route.places.length < 2) {
      this.alerts.push("Route must include at least 2 places!");
      valid = false;
    }
    return valid;
  }

  removePlace(id: number): void {
    this.route.places = this.route.places.filter(p => p.id != id);
    this.child.placeList = this.route.places;
    this.child.getDirection();
  }

  close(alert: string) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  resetAlerts() {
    this.alerts = [];
  }

}
