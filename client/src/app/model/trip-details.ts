import {AccountDTO} from './account.model'
import {AccountInfo} from './account-info.model'
import {RouteInfo} from "./route-info.model";
import {Route} from "./route.model";
import {Image} from "./image.model";

export class TripDetailsDTO {
  id: number;
  name :string;
  description: string;
  creationDate: Date;
  startDate: Date;
  self: string;
  creator: AccountDTO;
  route: Route;
  subscribers: AccountInfo[];
  image: Image;
  chatId:number;


  constructor(id: number, name: string, description: string, creationDate: Date, startDate: Date, self: string, creator: AccountDTO, route: Route, subscribers: AccountInfo[], image: Image, chatId: number) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.creationDate = creationDate;
    this.startDate = startDate;
    this.self = self;
    this.creator = creator;
    this.route = route;
    this.subscribers = subscribers;
    this.image = image;
    this.chatId = chatId;
  }
}
