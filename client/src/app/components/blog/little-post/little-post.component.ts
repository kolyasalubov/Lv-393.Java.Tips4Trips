import {Component, Input, OnInit} from '@angular/core';
import {LittlepostModel} from '../../../model/littlepost.model';

@Component({
  selector: 'app-little-post',
  templateUrl: './little-post.component.html',
  styleUrls: ['./little-post.component.css']
})

export class LittlePostComponent implements OnInit {
  @Input() post: LittlepostModel;

  constructor() { }

  ngOnInit() {
  }

}
