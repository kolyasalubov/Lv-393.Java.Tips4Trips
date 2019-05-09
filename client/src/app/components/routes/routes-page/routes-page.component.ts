import { Component, OnInit } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { CustomAuthService } from '../../authentication/custom-auth.service';

@Component({
  selector: 'app-routes-page',
  templateUrl: './routes-page.component.html',
  styleUrls: ['./routes-page.component.css']
})
export class RoutesPageComponent implements OnInit {
  routes: RouteInfo[] = [];
  hasAuthority: boolean = false;

  constructor(
    private routeService: RouteService,
    private authService: CustomAuthService
  ) { }

  ngOnInit() {
    this.routeService.findVerified()
      .subscribe(data => this.routes = data);
    this.authService.getCurrentUser().subscribe(account => {
      if (account.role.toLowerCase() == "admin" || account.role.toLowerCase() == "moderator") {
        this.hasAuthority = true;
      }
    });
  }
}
