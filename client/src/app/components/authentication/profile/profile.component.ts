import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Account} from 'src/app/model/account.model';
import {ProfileService} from './profile.service';
import {NewsService} from "../../news/news.service";
import {AccountFeed} from "../../../model/account-feed";
import {AccountFollowing} from "../../../model/account-following";
import {CustomAuthService} from "../custom-auth.service";
import {TripInfoDTO} from "../../../model/trip-info";
import {TripService} from "../../trip/trip/trip.service";




@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  accountProfile: Account;
  currentProfileId: number;
  accountFeed: AccountFeed;
  isFollowing: boolean = false;
  followingAccounts: AccountFollowing[];
  isButtonDisabled: boolean = false;
  trips: TripInfoDTO[] = [];


  constructor(private newsService: NewsService, private ngRoute: ActivatedRoute, private router: Router, private profileService: ProfileService, private authService: CustomAuthService, private tripService: TripService) {
  }


  public toggleStatus() {
    this.isButtonDisabled = true;
    if (this.isFollowing) {
      this.unSubscribe();
      this.isFollowing = false;
      console.log(this.isFollowing);
    } else {
      this.subscribeTo();
      this.isFollowing = true;
      console.log(this.isFollowing);
    }
    this.isButtonDisabled = false;
  }

  public subscribeTo() {
    this.profileService.subscribeTo(this.currentProfileId, this.accountProfile.id).subscribe();
  }

  public unSubscribe() {
    this.profileService.unSubscribe(this.currentProfileId, this.accountProfile.id).subscribe();
  }

  ngOnInit() {
    const id = Number(this.ngRoute.snapshot.paramMap.get('id'));
    this.authService.getCurrentUser().subscribe(data => {
      this.currentProfileId = data.id;
      console.log(data.id, Number(this.ngRoute.snapshot.paramMap.get('id')));
      this.profileService.isFollowing(data.id, Number(this.ngRoute.snapshot.paramMap.get('id'))).subscribe(data => {
        this.isFollowing = data;
        console.log(this.isFollowing);
      });

    });
    this.profileService.findById(id).subscribe(data => {
      if (data.id == this.currentProfileId) {
        this.router.navigate(['account']);
      } else {
        this.accountProfile = data;
      }

    });
    this.tripService.getByCreatorId(id).subscribe(data => this.trips = data);
  }

  getImageString() {
    return "http://localhost:8080/accounts/" + this.currentProfileId + "/image";
  }
}
