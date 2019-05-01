import { Component, OnInit, Input } from '@angular/core';
import { RouteInfo } from '../../../model/route-info.model';
import { RouteService } from '../../../service/route.service';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-list-of-routes',
  templateUrl: './list-of-routes.component.html',
  styleUrls: ['./list-of-routes.component.css']
})
export class ListOfRoutesComponent implements OnInit {

  @Input() routes: RouteInfo[];
  @Input() displayEditButtons: boolean = false;
  @Input() displayVerifyButton: boolean = false;

  constructor(
    private routeService: RouteService
  ) { }

  ngOnInit() {

  }

  deleteRoute(id: number): void {
    this.routeService.deleteRoute(id).subscribe(data => this.routes = this.routes.filter(route => route.id != id));
  }
  verifyRoute(id: number): void {
    this.routeService.verifyRoute(id).subscribe(data => this.routes = this.routes.filter(route => route.id != id));
  }

}
