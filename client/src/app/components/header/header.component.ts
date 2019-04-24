import {Component, HostListener, OnInit} from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  show: boolean = false;

  constructor() {
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

}
