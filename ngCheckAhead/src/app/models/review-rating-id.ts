export class ReviewRatingId {

    ratingId: number;
    reviewId: number;

    constructor(
        ratingId?: number,
        reviewId?: number
    ){
        this.ratingId = ratingId;
        this.reviewId = reviewId;
    }
}
