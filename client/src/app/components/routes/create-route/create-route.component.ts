import {Component, OnInit, ViewChild} from '@angular/core';
import { Route } from 'src/app/model/route.model';
import { RouteService } from 'src/app/service/route.service';
import { AccountInfo } from 'src/app/model/account-info.model';
import { PlaceService } from 'src/app/place.service';
import { Router } from '@angular/router';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { FormControl } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';
import {RouteMapComponent} from "../../map/route-map/route-map.component";

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
      data[0].imageUrl =  'http://localhost:8080/places/' + data[0].id + '/image';
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
    return this.route.places.length > 0 && this.route.name != null && this.route.name.length > 5;
  }

  removePlace(id: number): void {
    this.route.places = this.route.places.filter(p => p.id != id);
    this.child.placeList = this.route.places;
    this.child.getDirection();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
}
