import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Account} from 'src/app/model/account.model';
import {ProfileService} from './profile.service';
import {AuthService} from "../auth.service";



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  accountProfile: Account;
  currentProfileId: number;

  constructor(private ngRoute: ActivatedRoute,private router: Router, private profileService: ProfileService,private authService: AuthService,) { }


  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.authService.getCurrentUser().subscribe(data => this.currentProfileId = data.id);
    this.profileService.findById(id).subscribe(data =>{
      if(data.id == this.currentProfileId){
        this.router.navigate(['account']);
      }else {
        this.accountProfile = data;
      }
    });
  }

}
