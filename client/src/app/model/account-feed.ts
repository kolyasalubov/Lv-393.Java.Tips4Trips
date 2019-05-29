import {AccountFollowing} from "./account-following";

export class AccountFeed {
  id: number;
  firstName: string;
  lastName: string;
  followingAccounts:AccountFollowing[];
  self:string;

  constructor(id: number, firstName: string, lastName: string, followingAccounts: AccountFollowing[], self: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.followingAccounts = followingAccounts;
    this.self = self;
  }
}
