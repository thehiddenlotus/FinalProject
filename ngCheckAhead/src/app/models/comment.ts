export class Comment {
  id: number;
  content: string;
  createdAt: number;
  updatedAt: number;
  reviewRating: number;
  active: boolean;

  constructor(
    id?: number,
    content?: string,
    createdAt?: number,
    updatedAt?: number,
    reviewRating?: number,
    active?: boolean

  ){
this.id = id;
this.content = content;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
  this.reviewRating = reviewRating;
  this.active = active;
  }
}
