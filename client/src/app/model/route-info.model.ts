import {AccountInfo} from './account-info.model'
import { MiniPlace } from './mini-place';
export class RouteInfo {
    id: number;
    name: string;
    authorInfo: AccountInfo;
    creationDate: Date;
    begin: MiniPlace;
    end: MiniPlace;
    self: string;
}
