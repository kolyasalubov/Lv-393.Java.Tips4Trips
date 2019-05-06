import {Component, Input, OnInit} from '@angular/core';
import {LittlepostModel} from "../../../model/littlepost.model";

@Component({
  selector: 'app-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.css']
})
export class ListPostsComponent implements OnInit {

  @Input() postP:LittlepostModel[]=null;

  constructor() { }

  ngOnInit() {
  }

}
