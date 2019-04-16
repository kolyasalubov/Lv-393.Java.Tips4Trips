import {AccountInfo} from './account-info.model'
export class RouteInfo {
    id: number;
    name: string;
    authorInfo: AccountInfo;
    creationDate: Date;
    self: string;
}
