import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../token/token-storage.service';
import {ACCESS_TOKEN} from '../../../constants'
import { from } from 'rxjs';
import { AccountDTO } from 'src/app/model/account.model';
import { CustomAuthService } from '../custom-auth.service';
import { AccountService } from '../account/account.service';
import * as $ from 'jquery';
import { AccountPhoneInfo } from 'src/app/model/account-phone-info.model';

@Component({
  selector: 'app-social-handler',
  templateUrl: './social-handler.component.html',
  styleUrls: ['./social-handler.component.css']
})
export class SocialHandlerComponent implements OnInit {

  private account:  AccountDTO;
  infoForm: string;
  private phoneNumber:  string;
  private accountPhoneNumber: AccountPhoneInfo;

  constructor(private activatedRoute : ActivatedRoute, 
    private tokenStorage : TokenStorageService, private route : Router,
    private authService: CustomAuthService,
    private accountService: AccountService) { }

  ngOnInit() {
    this.fullHeight(5);
    this.activatedRoute.queryParams.subscribe(params => {
      localStorage.setItem(ACCESS_TOKEN, params['token']);
      this.tokenStorage.saveToken(params['token']);
      this.authService.getCurrentUser().subscribe(data => {
        console.log(data.phoneNumber);
        if(data.phoneNumber!='tmpnull'){
          this.route.navigate(['account']);
        }
      });      
    })
  }

  onSubmit() {
    this.tokenStorage.saveToken(localStorage.getItem(ACCESS_TOKEN));
    this.phoneNumber = this.infoForm;
    this.authService.getCurrentUser().subscribe(data => {
      this.account = data;
      this.accountPhoneNumber = new AccountPhoneInfo(data.id, this.phoneNumber);
      this.accountService.updateAccountNumber(this.accountPhoneNumber).subscribe(data => {
        this.route.navigate(['account']);
      });
    });
  }

  fullHeight = function(divide) {
		$('.js-fullheight').css('height', $(window).height()/divide);
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height()/divide);
		});
    };

}
