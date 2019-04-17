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

  accountProfile: Account;

  constructor(private accountService: AccountService,private router: Router) {
  }

  ngOnInit() {

    this.accountService.getCurrentUser().subscribe(data =>
      console.log(data) 
      //this.accountProfile = data
       ); 

  }

  initAcc(accountProfile: Account){
      this.accountProfile = accountProfile;
  }


  editAccount() {
    this.router.navigate(['account_edit']);
  }


}
