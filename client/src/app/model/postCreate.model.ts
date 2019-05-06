import {Route} from "./route.model";
import {Account} from "./account.model";

export class PostCreateModel {
  id: number;
  name: String;
  content: String;
  photoPath: String;
  routeInfo: Route;
  author: Account;
}
