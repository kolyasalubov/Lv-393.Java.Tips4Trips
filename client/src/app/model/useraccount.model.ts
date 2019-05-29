import { AccountDTO } from "./account.model";
import { User } from "./user.model";

export class UserAccount {
    account: AccountDTO;
    user: User;

    constructor(account: AccountDTO, user: User) {
        this.account = account;
        this.user = user;
    }
}
