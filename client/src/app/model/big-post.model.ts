import {AccountInfo} from './account-info.model';
import {RouteInfo} from './route-info.model';
import {Image} from "./image.model";

export class BigPostModel {
  id: number;
  name: String;
  creationDate: Date;
  description: String;
  authorInfo: AccountInfo;
  countOfLikes: number;
  self: String;
  photoPath: String;
  content: String;
  routeInfo: RouteInfo;
  likes: String;
  comments: String;
  authorId:number;
  authorFirstName:string;
  authorLastName:string;
  accountId:number;
  firstName: String;
  lastName:String;
  images:Image[];
}
