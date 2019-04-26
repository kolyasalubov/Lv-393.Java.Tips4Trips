import {Account} from './account.model'
import {AccountInfo} from './account-info.model'
import {RouteInfo} from "./route-info.model";
import {Route} from "./route.model";

export class FindGroupDetailsDTO {
  id: number;
  name :string;
  description: string;
  creationDate: Date;
  startDate: Date;
  self: string;
  creator: Account;
  route: Route;
  subscribers: AccountInfo[];



  constructor(id: number, name: string, description: string, creationDate: Date, startDate: Date, self: string, creator: Account, route: Route, subscribers: AccountInfo[]) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.creationDate = creationDate;
    this.startDate = startDate;
    this.self = self;
    this.creator = creator;
    this.route = route;
    this.subscribers = subscribers;
  }
}
