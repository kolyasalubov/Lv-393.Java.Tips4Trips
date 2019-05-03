import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Account} from 'src/app/model/account.model';
import {ProfileService} from './profile.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  accountProfile: Account;

  constructor(private ngRoute: ActivatedRoute, private profileService: ProfileService) { }


  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.profileService.findById(id).subscribe(data =>{
      this.accountProfile = data;
    });
  }

}
