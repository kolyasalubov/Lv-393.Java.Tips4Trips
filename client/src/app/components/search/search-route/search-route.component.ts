import { Component, OnInit } from '@angular/core';
import { RouteInfo } from 'src/app/model/route-info.model';
import { RouteService } from 'src/app/service/route.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-route',
  templateUrl: './search-route.component.html',
  styleUrls: ['./search-route.component.css']
})
export class SearchRouteComponent {

  routes: RouteInfo[];

  constructor(
    private routeService: RouteService,
    private router: Router
  ) { }

  init() {
    
  }

  search(seek: string): void {
    this.routeService.searchByName(seek).subscribe(data => this.routes = data);
  }

  navigate(seek: string) : void {
    this.router.navigate(['/search'], { queryParams: { seek: seek, category: 'route' } });
  }

}
