import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../token/token-storage.service';
import {ACCESS_TOKEN} from '../../../constants'
import { from } from 'rxjs';

@Component({
  selector: 'app-social-handler',
  templateUrl: './social-handler.component.html',
  styleUrls: ['./social-handler.component.css']
})
export class SocialHandlerComponent implements OnInit {

  constructor(private activatedRoute : ActivatedRoute, 
    private tokenStorage : TokenStorageService, private route : Router) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      localStorage.setItem(ACCESS_TOKEN, params['token']);
      this.tokenStorage.saveToken(params['token']);      
    })
    console.log("success");
    this.route.navigate(['account']);
  }

}
