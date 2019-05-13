import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../token/token-storage.service';
import { CustomAuthService } from '../custom-auth.service';
import { AccountService } from '../account/account.service';
import * as $ from 'jquery';
import { ResetPasswordForm } from './reset-password-form.model';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  private token : string;
  private inputForm : any = {}; 
  private resetPasswordForm : ResetPasswordForm;

  constructor(private activatedRoute : ActivatedRoute, 
    private tokenStorage : TokenStorageService, private route : Router,
    private authService: CustomAuthService,
    private accountService: AccountService) { }

  ngOnInit() {
    this.fullHeight(5);
    this.activatedRoute.queryParams.subscribe(params => {
      this.token=params['token'];
    })
  }

  onSubmit() {
    this.resetPasswordForm = new ResetPasswordForm(this.inputForm.password,
      this.inputForm.confirmPassword, this.token);
      console.log(this.resetPasswordForm);
    this.authService.resetPassword(this.resetPasswordForm).subscribe();
    this.route.navigate(['login']);
  }

  fullHeight = function(divide) {
		$('.js-fullheight').css('height', $(window).height()/divide);
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height()/divide);
		});
    };

}
