import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BlogService} from '../blog/blog/blog.service';
import {PagelittlepostModel} from '../../model/pagelittlepost.model';
import {CustomAuthService} from '../authentication/custom-auth.service';

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
  pagePost: PagelittlepostModel;

  constructor(private blogserv: BlogService) {
  }

  ngOnInit() {
    this.blogserv.getPosts(1)
      .subscribe(data => {
        this.pagePost = data;
      });
  };
}
