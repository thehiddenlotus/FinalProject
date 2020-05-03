import { ReviewRatingId } from './review-rating-id';
export class ReviewRating {
    id: ReviewRatingId;
    ratingId: number;
    reviewId: number;
    ratingValue: number;

    constructor(
        id?: ReviewRatingId,
        ratingId?: number,
        reviewId?: number,
        ratingValue?: number
    ) {
        this.id = id;
        this.ratingId = ratingId;
        this.reviewId = reviewId;
        this.ratingValue = ratingValue;
    }
}

