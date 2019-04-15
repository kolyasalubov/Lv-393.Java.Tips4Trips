import { Account } from "./account.model";
export class Comment{
  id:number;
  account:Account;
  text: String;
  date: Date;
  postId:number;

  constructor(id: number, account: Account, text: String, date: Date, postId: number) {
    this.id = id;
    this.account = account;
    this.text = text;
    this.date = date;
    this.postId = postId;
  }
}
