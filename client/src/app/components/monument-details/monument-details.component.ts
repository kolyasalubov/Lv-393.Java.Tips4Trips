import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MonumentService} from "../create-post-place/create-monument/monument.service";
import {Monument} from "../../model/monument.model";
import {Position} from "../../model/position.model";
import {City} from "../../model/city.model";
import {TokenStorageService} from "../authentication/token/token-storage.service";
import {Location} from "@angular/common"
import {CustomAuthService} from "../authentication/custom-auth.service";
import {FeedbackPlaceService} from "../feedback/feedback-place.service";
import {PageFeedbackPlaceModel} from "../../model/page-feedback-place.model";

@Component({
  selector: 'app-monument-details',
  templateUrl: './monument-details.component.html',
  styleUrls: ['./monument-details.component.css']
})
export class MonumentDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private monumentService: MonumentService,
              private location: Location, private authService: CustomAuthService) {
  }

  id: number;
  cityId: number;
  monument: Monument = new Monument(0, '', '', '', new Position(0, 0),
    null, new City(0, '', new Position(0, 0), 0, ''));

  role: string;

  zoom: number = ZoomLevel.Place;
  imageURL: string = '';


  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.monumentService.findById(this.id).subscribe(value => {
      this.monument = value;
      this.imageURL = 'http://localhost:8080/places/' + this.monument.id + '/image';
    });
    this.authService.getCurrentUser().subscribe(data => this.role = data.role);

  }

  deletePlace() {
    let confirmation = confirm("Delete " + this.monument.name + "?");
    if (confirmation) {
      this.monumentService.deleteById(this.monument.id);
    }
    this.location.back();
  }
}
