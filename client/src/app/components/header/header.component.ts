import {Component, HostListener, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  show: boolean = false;
  seek: string;

  constructor(
    private router: Router
  ) {
    setTimeout(()=>{},400);
  }

  ngOnInit() {
    this.getScreenSize()
  }

  scrWidth: number;

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.scrWidth = window.innerWidth;
    if (this.scrWidth > 1000) {
      this.show = true;
    }
    else {
      this.show = false;
    }
  }

  showIt() {
    this.show = !this.show;
  }
  goToSearch(): void {
    this.router.navigate(['/search'], { queryParams: { seek: this.seek } });
  }

}
