import { AccountDTO } from "./model/account.model";
import { User } from "./model/user.model";

export class UserAccount {
    account: AccountDTO;
    user: User;

    constructor(account: AccountDTO, user: User) {
        this.account = account;
        this.user = user;
    }
}
