import { Component, OnInit, Input, Output, EventEmitter, ViewEncapsulation } from '@angular/core';
import { RouteInfo } from 'src/app/model/route-info.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-route-info',
  templateUrl: './route-info.component.html',
  styleUrls: ['./route-info.component.css'],
})
export class RouteInfoComponent implements OnInit {

  @Input() routeInfo: RouteInfo;
  @Input() displayEditButtons: boolean = false;
  @Input() displayVerifyButton: boolean = false;
  @Output() delete = new EventEmitter();
  @Output() verify = new EventEmitter();
  constructor(private router: Router) { }

  ngOnInit() {
  }

  edit(): void {
    this.router.navigate(['routes/' + this.routeInfo.id + '/edit']);
  }

}
