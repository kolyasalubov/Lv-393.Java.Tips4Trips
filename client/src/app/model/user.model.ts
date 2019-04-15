import { Account } from "./account.model";

export class User {
    id: number;
    login: string;
    password: string;
    account: Account
}
