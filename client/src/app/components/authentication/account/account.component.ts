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



  accountProfile: Account = new Account(null,null,null,null,null,null,null,null);

  constructor(private accountService: AccountService,private router: Router) {
  }

  ngOnInit() {

    this.accountService.findById(6).subscribe(data => this.accountProfile = data);

     
  }


  onSubmit(accountProfile :any) {

  
      console.log(accountProfile.firstName);
  
  }



  closeProfile() {
    this.router.navigate(['home']);
  }


}
