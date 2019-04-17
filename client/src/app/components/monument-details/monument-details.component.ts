import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MonumentService} from "../create-post-place/create-monument/monument.service";
import {Monument} from "../../model/monument.model";

@Component({
  selector: 'app-monument-details',
  templateUrl: './monument-details.component.html',
  styleUrls: ['./monument-details.component.css']
})
export class MonumentDetailsComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private monumentService: MonumentService) { }
  id: number;
  cityId: number;
  monument: Monument = new Monument(null, null, null, null, null,
    null, null);

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.id = +params.get('id');
    });
    this.monumentService.findById(this.id).subscribe(value => this.monument = value);
  }

}
