
import {BigPostModel} from "./big-post.model";

export class AccountFollowing{
  id: number;
  firstName: string;
  lastName: string;
  post:BigPostModel[];
  self:string;


  constructor(id: number, firstName: string, lastName: string, post: BigPostModel[], self: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.post = post;
    this.self = self;
  }
}
