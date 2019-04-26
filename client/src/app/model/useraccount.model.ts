import { Account } from "./account.model";
import { User } from "./user.model";

export class UserAccount {
    account: Account;
    user: User;

    constructor(account: Account, user: User) {
        this.account = account;
        this.user = user;
    }
}
