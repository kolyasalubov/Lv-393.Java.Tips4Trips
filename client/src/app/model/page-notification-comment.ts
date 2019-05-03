import {NotificationCommentModel} from "./notification-comment.model";

export class PageNotificationComment {
  content: NotificationCommentModel[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  first: boolean;
  sort: string;
  number: number;
  numberOfElements: number;
}
