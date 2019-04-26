import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-list-of-routes',
  templateUrl: './list-of-routes.component.html',
  styleUrls: ['./list-of-routes.component.css']
})
export class ListOfRoutesComponent implements OnInit {

  routes: RouteInfo[] = [];
  hasAuthority: boolean = false;

  constructor(
    private routeService: RouteService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.routeService.findAll()
      .subscribe(data => this.routes = data);
    this.authService.getCurrentUser().subscribe(account => {
      if (account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator") {
        this.hasAuthority = true;
      }
    });
  }

  deleteRoute(id: number): void {
    this.routeService.deleteRoute(id).subscribe(data=> this.routes = this.routes.filter(route=>route.id != id));
  }

}
