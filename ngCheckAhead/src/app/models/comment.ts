import { Review } from './review';
import { ReviewRating } from './review-rating';
import { User } from './user';

export class Comment {
  id: number;
  content: string;
  createdAt: number;
  updatedAt: number;
  reviewRating: number;
  active: boolean;
  review: Review;
  user: User;

  constructor(
    id?: number,
    content?: string,
    createdAt?: number,
    updatedAt?: number,
    reviewRating?: number,
    active?: boolean,
    review?: Review,
    user?: User

  ){
    this.id = id;
    this.content = content;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.reviewRating = reviewRating;
    this.active = active;
    this.review = review;
    this.user = user;
  }
}
