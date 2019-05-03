import { Component, OnInit } from '@angular/core';
import { PlaceService } from 'src/app/place.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PlaceInfo } from 'src/app/model/place-info.model';

@Component({
  selector: 'app-search-place',
  templateUrl: './search-place.component.html',
  styleUrls: ['./search-place.component.css']
})
export class SearchPlaceComponent implements OnInit {

  constructor(private placeService: PlaceService, private activatedRoute: ActivatedRoute, private router: Router) { }

  places: PlaceInfo[] = null;
  cityId: number;

  ngOnInit() {
    console.log("serarch places");
    this.activatedRoute.paramMap.subscribe(params => {
      this.cityId = +params.get('id');
    });
    this.placeService.getAllByCityId(this.cityId).subscribe(data => this.places = data);
  }

  transferSelfUrl(url: string): string {
    const urlArr = url.split('/');
    return urlArr[urlArr.length - 2] + "/" + urlArr[urlArr.length - 1];
  }

  search(seek: string) {
    console.log("seek" + seek);
  }

  init(): void {

  }

  navigate(seek: string) : void {
    this.router.navigate(['/search'], { queryParams: { seek: seek, category: 'place' } });
  }

}
