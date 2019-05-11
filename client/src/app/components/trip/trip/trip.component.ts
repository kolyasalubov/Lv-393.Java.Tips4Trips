import {Component, OnInit} from '@angular/core';
import {TripService} from './trip.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TripInfoDTO} from 'src/app/model/trip-info';
import {PageTripsmodel} from "../../../model/pageTripsmodel";
import {Image} from "../../../model/image.model";

@Component({
  selector: 'app-trip',
  templateUrl: './trip.component.html',
  styleUrls: ['./trip.component.css']
})
export class TripComponent implements OnInit {

  id: number;
  max: number;
  num: number;
  postP: TripInfoDTO[] = null;
  pagePost: PageTripsmodel = null;
  imageURL: string = 'http://localhost:8080/trips/';

  constructor(private tripService: TripService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  getPageTrip(page: number): void {
    this.tripService.getPageTrips(page)
      .subscribe(data => {
        this.pagePost = data;
        this.max = this.pagePost.totalPages;
        this.num = this.pagePost.number;
        this.postP = this.pagePost.content;
      });
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    if (isNaN(this.id) || this.id < 1) {
      this.id = 1;
    }
    this.getPageTrip(this.id);

  }

  //  getImageString(image: Image) {
  //   return this.imageURL + image.id +  '/image';
  // }
}
