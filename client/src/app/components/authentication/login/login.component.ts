import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { UserService } from '../user/user.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  invalidLogin: boolean = false;
  constructor(private formBuilder: FormBuilder, private router: Router, private userService: UserService) { }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }
    const loginPayload = {
      username: this.loginForm.controls.username.value,
      password: this.loginForm.controls.password.value
    }
    this.userService.login(loginPayload).subscribe(data => {
      if(data.status === 200) {
        window.localStorage.setItem('token', data.result.token);
        this.router.navigate(['account']);
        alert(loginPayload);
      }else {
        this.invalidLogin = true;
        alert(data.message);
      }
    });
  }

  ngOnInit() {
    window.localStorage.removeItem('token');
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });

    function fullHeight() {
      $('.js-fullheight').css('height', $(window).height()/2);
      $(window).resize(function(){
          $('.js-fullheight').css('height', $(window).height());
      });
  };
  fullHeight();
  }

}
