import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { CustomAuthService } from '../../authentication/custom-auth.service';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-routes-page',
  templateUrl: './routes-page.component.html',
  styleUrls: ['./routes-page.component.css']
})
export class RoutesPageComponent implements OnInit {
  routes: RouteInfo[] = [];
  page: number;
  totalPages: number;
  hasAuthority: boolean = false;

  constructor(
    private routeService: RouteService,
    private authService: CustomAuthService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      try {
        this.page = (params['page']) ? +params['page'] - 1 : 0;
      } catch (err) {
        this.page = 0;
      }
      this.routeService.findVerified(this.page)
        .subscribe(data => {
          this.routes = data.content;
          this.totalPages = data.totalPages;
        });
      this.authService.getCurrentUser().subscribe(account => {
        if (account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator") {
          this.hasAuthority = true;
        }
      });
    });
  }
}
