import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/model/account.model';
import { AccountService } from 'src/app/components/authentication/account/account.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {


  accountProfile: Account = new Account(null,null,null,null,null,null,null,null);

  constructor(private accountService: AccountService,private router: Router) {
  }

  ngOnInit() {
    this.accountService.findById(6).subscribe(data => this.accountProfile = data);
  }


  onSubmit(accountProfile :Account) {
      console.log(accountProfile);
      this.accountService.updateAccount(accountProfile).subscribe(data => this.accountProfile = data);
      this.router.navigate(['home']);
  }



  accountInfo() {
    this.router.navigate(['account']);
  }



}