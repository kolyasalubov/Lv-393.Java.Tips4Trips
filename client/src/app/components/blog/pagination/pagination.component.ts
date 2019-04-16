import {Component, Input, OnInit} from '@angular/core';
import {PagelittlepostModel} from '../../../model/pagelittlepost.model';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  @Input() number: number;
  @Input() total: number;

  constructor() {
  }

  page: number[] = [];
  back: boolean;
  front: boolean;
  first: number;
  index: number;

  ngOnInit() {
    this.back = false;
    this.front = false;
    if ((this.number + 1) > 1) {
      this.back = true;
    }
    if ((this.number + 1) < this.total) {
      this.front = true;
    }
    this.first = this.number;
    this.index = 0;
    for (let i = (this.number + 2); (this.number + i) < this.total; i++) {
      if (i == (this.number + 5)) {
        return;
      }
      this.page[this.index] = this.number + i;
      this.index++;
    }
  }

}
