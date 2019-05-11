import { Component, OnInit, ViewEncapsulation, Output, Input, EventEmitter } from '@angular/core';
import { viewParentEl } from '@angular/core/src/view/util';

@Component({
  selector: 'app-search-navbar',
  templateUrl: './search-navbar.component.html',
  styleUrls: ['./search-navbar.component.css']
})
export class SearchNavbarComponent implements OnInit {

  @Input() category: string;
  @Output() navigate = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }



}
