import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: [
    './home.component.css',
    '../../../assets/css/open-iconic-bootstrap.min.css',
    '../../../assets/css/animate.css',
    '../../../assets/css/owl.carousel.min.css',
    '../../../assets/css/owl.theme.default.min.css',
    '../../../assets/css/magnific-popup.css',
    '../../../assets/css/aos.css',
    '../../../assets/css/ionicons.min.css',
    '../../../assets/css/bootstrap-datepicker.css',
    '../../../assets/css/jquery.timepicker.css',
    '../../../assets/css/flaticon.css',
    '../../../assets/css/icomoon.css'
  ]
})
export class HomeComponent implements OnInit {

  title = 'tips4trips-app';

  constructor(private router: Router) {}
  ngOnInit() {
    function fullHeight() {
      $('.js-fullheight').css('height', $(window).height()/2);
      $(window).resize(function(){
          $('.js-fullheight').css('height', $(window).height()/2);
      });
  };
  fullHeight();
   }

}
