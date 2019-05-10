import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { SearchPlaceComponent } from '../search-place/search-place.component';
import { SearchRouteComponent } from '../search-route/search-route.component';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SearchPostComponent } from '../search-post/search-post.component';
import { SearchTripComponent } from '../search-trip/search-trip.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, AfterViewInit {

  seek: string;
  currCategory: string = "PLACE";

  @ViewChild(SearchPlaceComponent) searchPlace: SearchPlaceComponent;
  @ViewChild(SearchRouteComponent) searchRoute: SearchRouteComponent;
  @ViewChild(SearchPostComponent) searchPost: SearchPostComponent;
  @ViewChild(SearchTripComponent) searchTrip: SearchTripComponent;

  categoriesComponents: any;
  page: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    
    this.route.params.forEach((params: Params) => {
      try {
        this.page = (params['page']) ? +params['page'] - 1 : 0 ;
      } catch (err) {
        this.page = 0;
      }
      this.route.queryParams.subscribe(params => {
        this.seek = params['seek'] || '';
        this.currCategory = params['category'] || 'PLACE';
        if (!categories.includes(this.currCategory.toUpperCase())) {
          this.currCategory = 'PLACE';
        } else {
          this.currCategory = this.currCategory.toUpperCase();
        }
        this.categoriesComponents = {
          "PLACE": this.searchPlace,
          "ROUTE": this.searchRoute,
          "POST": this.searchPost,
          "TRIP": this.searchTrip
        };
        this.categoriesComponents[this.currCategory].init();
        this.categoriesComponents[this.currCategory].search(this.seek, this.page);
      });
    })
  }

  ngAfterViewInit(): void {

  }

  navigate(category: string) {
    this.categoriesComponents[category].navigate(this.seek);
  }

}

const categories: string[] = ["PLACE", "TRIP", "POST", "ROUTE"];
