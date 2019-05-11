import {Component, OnInit} from '@angular/core';
import {Account} from 'src/app/model/account.model';
import {AccountService} from 'src/app/components/authentication/account/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {


  accountProfile: Account = new Account(
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null);

  constructor(private accountService: AccountService,
              private router: Router) {
  }

  ngOnInit() {
    //this.accountService.findById(1).subscribe(data => this.accountProfile = data);
    this.accountService.getCurrentUser().subscribe(data => this.accountProfile = data);
  }

  onSubmit(accountProfile: Account) {
    console.log(accountProfile);
    this.accountService.updateAccount(accountProfile).subscribe(data => this.accountProfile = data);

    setTimeout(() => {
      this.router.navigate(['account']);
    }, 800);
  }

  accountInfo() {
    this.router.navigate(['account']);
  }

}
