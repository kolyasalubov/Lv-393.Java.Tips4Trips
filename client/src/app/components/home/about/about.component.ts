import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  account:number=0;
  trip:number=0;
  city:number=0;
  post:number=0;
  aa:number=0;

  private urlPage = 'http://test2-env.2hvwm638dp.us-east-2.elasticbeanstalk.com/';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {

    this.http.get<number>(this.urlPage + "accounts/count").subscribe(data=>{
      this.account=data;
    });
    this.http.get<number>(this.urlPage + "trips/count").subscribe(data=>{
      this.trip=data;
    });
    this.http.get<number>(this.urlPage + "cities/count").subscribe(data=>{
      this.city=data;
    });
    this.http.get<number>(this.urlPage + "posts/count").subscribe(data=>{
      this.post=data;
    });
  }

}
