import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchPlaceComponent } from '../search-place/search-place.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  seek: string;
  currCategory:string = "PLACE";

  @ViewChild(SearchPlaceComponent) searchPlace : SearchPlaceComponent;

  categoriesComponents = {
    "PLACE":this.searchPlace
  }

  constructor() { }

  ngOnInit() {
  }

  search():void {
    this.categoriesComponents[this.currCategory].search(this.seek);
  }
  navigate(category: string) {
    this.currCategory = category;
    this.search();
  }

}

const categories: string[] = ["PLACE", "TRIP", "POST", "ROUTE"];
