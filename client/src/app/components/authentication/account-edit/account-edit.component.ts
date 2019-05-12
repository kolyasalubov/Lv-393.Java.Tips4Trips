import {Component, OnInit} from '@angular/core';
import {Account} from 'src/app/model/account.model';
import {AccountService} from 'src/app/components/authentication/account/account.service';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";

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
    null, null,null,null,null);


  myAccountFormGroup: FormGroup = new FormGroup({
    firstName: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(26)
    ]),
    lastName: new FormControl(null,[
      Validators.required,
      Validators.minLength(2),
      Validators.maxLength(26)
    ]),
    phoneNumber: new FormControl(null,[
      Validators.required,
      Validators.pattern('\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n' +
        '2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n' +
        '4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$')
    ]),
    email: new FormControl(null,[
      Validators.required,
      Validators.pattern('^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,6})+$'),
      Validators.maxLength(40)
    ]),
    about: new FormControl(null,[
      Validators.required,
      Validators.minLength(10),
      Validators.maxLength(255)
    ])
  });



  uploadPhoto: boolean = false;
  photoUrl: string = '';
  imageURLs: string[] = new Array();

  constructor(private accountService: AccountService,
              private router: Router) {
  }

  ngOnInit() {
    //this.accountService.findById(1).subscribe(data => this.accountProfile = data);
    this.accountService.getCurrentUser().subscribe(data => this.accountProfile = data);
  }

  update() {

    this.accountService.updateAccount(this.accountProfile).subscribe(data => {
      this.accountProfile = data;
      this.photoUrl = 'http://localhost:8080/accounts/' + this.accountProfile.id + '/image';
      this.uploadPhoto = true;
    });

    setTimeout(() => {
      this.router.navigate(['account']);
    }, 800);
  }

  navigateToAccount() {
    this.router.navigate(['account']);
  }




}
