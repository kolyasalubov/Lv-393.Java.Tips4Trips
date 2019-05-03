import {Component, Input, OnInit} from '@angular/core';
import {LittlepostModel} from '../../../model/littlepost.model';

@Component({
  selector: 'app-little-post',
  templateUrl: './little-post.component.html',
  styleUrls: ['./little-post.component.css']
})

export class LittlePostComponent implements OnInit {
  @Input() post: LittlepostModel;

  date: number;
  year: number;
  month: any;


  constructor() {
  }

  ngOnInit() {
    let input = new Date(this.post.creationDate);
    this.date = input.getDay();
    this.year = input.getFullYear();
    this.month = input.toLocaleString('en-us', {month: "long"});
    this.post.content=this.post.content.substr(0,125);
  }
}
