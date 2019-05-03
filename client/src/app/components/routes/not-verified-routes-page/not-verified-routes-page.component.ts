import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { AuthService } from '../../authentication/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-not-verified-routes-page',
  templateUrl: './not-verified-routes-page.component.html',
  styleUrls: ['./not-verified-routes-page.component.css']
})
export class NotVerifiedRoutesPageComponent implements OnInit {
  routes: RouteInfo[] = [];
  hasAuthority: boolean = false;

  constructor(
    private routeService: RouteService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(account => {
      if (!(account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator")) {
        this.router.navigate(['routes']);
      } else {
        this.hasAuthority = true;
      }
    });
    this.routeService.findNotVerified()
      .subscribe(data => this.routes = data);
  }

  deleteRoute(id: number): void {
    this.routeService.deleteRoute(id).subscribe(data=> this.routes = this.routes.filter(route=>route.id != id));
  }
}
