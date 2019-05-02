import {Like} from "./like.model";

export class PostDetailsModel {
  id: number;
  name: String;
  creationDate: Date;
  content: String;
  photoPath: String;
  likes: Like[];
  comments: Comment[];
}
