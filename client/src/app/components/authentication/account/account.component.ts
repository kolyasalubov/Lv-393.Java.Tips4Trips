import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/model/account.model';
import { AccountService } from './account.service';
import {Router} from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  accountProfile: Account;

  constructor(private accountService: AccountService, private authService: AuthService,
    private router: Router) {
  }

  ngOnInit() {

    //this.accountService.findById(1).subscribe(data => this.accountProfile = data);
    this.accountService.getCurrentUser().subscribe(data => this.accountProfile = data);}




  logout() {
    this.authService.logout();
    this.router.navigate(['home']);
  }

  initAcc(accountProfile: Account){
      this.accountProfile = accountProfile;
  }


  editAccount() {
    this.router.navigate(['account_edit']);
  }


}
