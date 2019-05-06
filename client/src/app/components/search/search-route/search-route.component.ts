import { Component, OnInit } from '@angular/core';
import { RouteInfo } from 'src/app/model/route-info.model';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/service/search.service';
import { RouteSearchParams } from 'src/app/model/search/route-search-params';

@Component({
  selector: 'app-search-route',
  templateUrl: './search-route.component.html',
  styleUrls: ['./search-route.component.css']
})
export class SearchRouteComponent {

  routes: RouteInfo[];
  params: RouteSearchParams;

  constructor(
    private searchService: SearchService,
    private router: Router
  ) { }

  init() {
    this.params = new RouteSearchParams();
  }

  search(seek: string): void {
    this.params.name = seek;
    this.searchService.findRoutesByParams(this.params).subscribe(data => this.routes = data);
  }

  navigate(seek: string) : void {
    this.router.navigate(['/search'], { queryParams: { seek: seek, category: 'route' } });
  }

}
