export class ReviewRating {

    ratingId: number;
    reviewId: number;
    ratingValue: number;

    constructor(
        ratingId?: number,
        reviewId?: number,
        ratingValue?: number
    ) {
        this.ratingId = ratingId;
        this.reviewId = reviewId;
        this.ratingValue = ratingValue;
    }
}

