import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-under',
  templateUrl: './under.component.html',
  styleUrls: ['./under.component.css']
})
export class UnderComponent implements OnInit {

  @Input() str: string;

  constructor() {
  }

  ngOnInit() {
  }

}
