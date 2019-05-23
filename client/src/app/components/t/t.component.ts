import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-t',
  templateUrl: './t.component.html',
  styleUrls: ['./t.component.css']
})
export class TComponent implements OnInit {
  fixedSizeData = Array(10000).fill(30);
  constructor() { }

  ngOnInit() {
  }

}
