import {Component, HostListener, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomAuthService} from "../authentication/custom-auth.service";
import {AccountDTO} from "../../model/account.model";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  show = false;
  seek: string;

  accountProfile: AccountDTO;
  newNotification = false;
  scrWidth: number;
  login = 'My Account';

  constructor(
    private router: Router,
    private authService: CustomAuthService,
    private activatedRoute: ActivatedRoute
  ) {
    setTimeout(() => {
      this.ngOnInit();
    }, 400);
  }

  ngOnInit() {
    this.authService.getCurrentUser().subscribe(data => {
      this.accountProfile = data;
      if (data.id > 0) {
        this.login = data.email;
        if (this.activatedRoute.snapshot.url[0] !== undefined && this.activatedRoute.snapshot.url[0].path === 'account') {
          this.newNotification = false;
        } else {
          this.newNotification = data.newNotification;
        }
      }
    });
    this.getScreenSize();
  }


  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.scrWidth = window.innerWidth;
    if (this.scrWidth > 1000) {
      this.show = true;
    } else {
      this.show = false;
    }
  }

  showIt() {
    this.show = !this.show;
  }

  goToSearch(): void {
    this.router.navigate(['/search'], {queryParams: {seek: this.seek}});
  }

}
