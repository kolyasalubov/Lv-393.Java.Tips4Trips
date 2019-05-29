import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../token/token-storage.service';
import { CustomAuthService } from '../custom-auth.service';
import { AccountService } from '../account/account.service';
import { ForgotPasswordForm } from './forgot-password-form.model';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  private inputForm : any = {}; 
  private forgotPasswordForm : ForgotPasswordForm;

  constructor(private activatedRoute : ActivatedRoute, 
    private tokenStorage : TokenStorageService, private route : Router,
    private authService: CustomAuthService,
    private accountService: AccountService) { }

  ngOnInit() {
    this.fullHeight(5);
  }

  onSubmit() {
    this.forgotPasswordForm = new ForgotPasswordForm(this.inputForm.email);
    this.authService.forgotPassword(this.forgotPasswordForm).subscribe();
    this.route.navigate(['login']);
  }

  fullHeight = function(divide) {
		$('.js-fullheight').css('height', $(window).height()/divide);
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height()/divide);
		});
    };
}
