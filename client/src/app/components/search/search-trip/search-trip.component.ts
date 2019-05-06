import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/service/search.service';
import { Router } from '@angular/router';
import { TripInfoDTO } from 'src/app/model/trip-info';
import { TripSearchParams } from 'src/app/model/search/trip-search-params';

@Component({
  selector: 'app-search-trip',
  templateUrl: './search-trip.component.html',
  styleUrls: ['./search-trip.component.css']
})
export class SearchTripComponent {

  trips: TripInfoDTO[];
  params: TripSearchParams;

  constructor(
    private searchService: SearchService,
    private router: Router
  ) { }

  init() {
    this.params = new TripSearchParams();
  }

  search(seek: string): void {
    this.params.name = seek;
    this.searchService.findTripsByParams(this.params).subscribe(data => this.trips = data);
  }

  navigate(seek: string) : void {
    this.router.navigate(['/search'], { queryParams: { seek: seek, category: 'trip' } });
  }

}
