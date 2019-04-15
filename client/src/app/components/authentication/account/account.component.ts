import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/model/account.model';
import { AccountService } from './account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {


  accountProfile: Account = new Account(1,null,null,null,null,null, null, null);
  // username: String;
  // cars: Car[];
  // numberOfCars: Number = null;
  // today = new Date().toISOString().slice(0, 10)

  constructor(private accountService: AccountService,private router: Router) {
  }

  ngOnInit() {

    this.accountService.findById(1).subscribe(data=>this.accountProfile.id); 
    //.subscribe(data => this.accountProfile = data);

     
  }

  closeProfile() {
    this.router.navigate(['ui/home']);
  }

}
