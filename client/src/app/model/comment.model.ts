import { AccountInfo } from "./account-info.model";
export class Comment{
  id:number;
  accountInfo:AccountInfo;
  shortText: String;
  creationDate: Date;
  self:String;
  postId:number;
  text: String;


  constructor(id: number, accountInfo: AccountInfo, shortText: String, creationDate: Date, self: String, postId: number, text: String) {
    this.id = id;
    this.accountInfo = accountInfo;
    this.shortText = shortText;
    this.creationDate = creationDate;
    this.self = self;
    this.postId = postId;
    this.text = text;
  }
}
