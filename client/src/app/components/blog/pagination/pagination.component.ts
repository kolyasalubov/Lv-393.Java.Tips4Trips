import {Component, Input, OnInit, OnChanges, SimpleChanges} from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit, OnChanges {
  @Input() number: number;
  @Input() total: number;
  @Input() link: string;
  @Input() queryParams: object = {};

  constructor(private router: Router,
    private route: ActivatedRoute) {
  }

  page: number[] = [];
  back: boolean;
  front: boolean;
  first: number;
  index: number;

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      console.log("QWERTY" + "  " + this.number + " "+ this.total);
      this.page = [];
      if (this.number > this.total) {
        this.router.navigate([this.link + '1']);
        return;
      }
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
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    this.ngOnInit();
  }
}
