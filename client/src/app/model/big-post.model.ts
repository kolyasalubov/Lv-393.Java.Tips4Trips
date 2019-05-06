import {AccountInfo} from './account-info.model';
import {RouteInfo} from './route-info.model';

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
  firstName: String;
  lastName:String;
}
