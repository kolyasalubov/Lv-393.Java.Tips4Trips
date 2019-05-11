import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { PlaceInfo } from 'src/app/model/place-info.model';

@Component({
  selector: 'app-place-info',
  templateUrl: './place-info.component.html',
  styleUrls: ['./place-info.component.css']
})
export class PlaceInfoComponent implements OnInit {

  @Input() place: PlaceInfo;
  @Input() displayRemoveButton: boolean = false;
  @Output() remove = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  transferSelfUrl(url: string): string {
    const urlArr = url.split('/');
    return urlArr[urlArr.length - 2] + "/" + urlArr[urlArr.length - 1];
  }
}
