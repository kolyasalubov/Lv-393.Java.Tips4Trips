export class Like{
  id: number;
  accountId: number;
  postId: number;

  constructor(id: number, accountId: number, postId: number) {
    this.id = id;
    this.accountId = accountId;
    this.postId = postId;
  }
}
