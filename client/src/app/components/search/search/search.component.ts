import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { SearchPlaceComponent } from '../search-place/search-place.component';
import { SearchRouteComponent } from '../search-route/search-route.component';
import { Router, ActivatedRoute } from '@angular/router';

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

  categoriesComponents: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
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
        "ROUTE": this.searchRoute
      };
      this.categoriesComponents[this.currCategory].init();
      this.categoriesComponents[this.currCategory].search(this.seek);
    });
  }

  ngAfterViewInit(): void {
    
  }

  navigate(category: string) {
    this.categoriesComponents[category].navigate(this.seek);
  }

}

const categories: string[] = ["PLACE", "TRIP", "POST", "ROUTE"];