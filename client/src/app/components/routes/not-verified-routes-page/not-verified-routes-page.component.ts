import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-not-verified-routes-page',
  templateUrl: './not-verified-routes-page.component.html',
  styleUrls: ['./not-verified-routes-page.component.css']
})
export class NotVerifiedRoutesPageComponent implements OnInit {
  routes: RouteInfo[] = [];
  hasAuthority: boolean = false;
  page: number;
  totalPages: number;
  constructor(
    private routeService: RouteService,
    private authService: CustomAuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.forEach((params:Params) => {
      try {
        this.page = (params['page']) ? +params['page'] - 1 : 0;
      } catch (err) {
        this.page = 0;
      }
      this.authService.getCurrentUser().subscribe(account => {
        if (!(account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator")) {
          this.router.navigate(['routes']);
        } else {
          this.hasAuthority = true;
        }
      });
      this.routeService.findNotVerified(this.page)
        .subscribe(data => {
          this.routes = data.content;
          this.totalPages = data.totalPages;
        });
    });
  }

  deleteRoute(id: number): void {
    this.routeService.deleteRoute(id).subscribe(data=> this.routes = this.routes.filter(route=>route.id != id));
  }
}
