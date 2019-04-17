import {Account} from './account.model'
import {AccountInfo} from './account-info.model'
import {RouteInfo} from "./route-info.model";

export class FindGroupDetailsDTO {
  creator: Account;
  route: RouteInfo;
  subscribers: AccountInfo[];

  constructor(creator: Account, route: RouteInfo, subscribers: AccountInfo[]) {
    this.creator = creator;
    this.route = route;
    this.subscribers = subscribers;
  }
}
