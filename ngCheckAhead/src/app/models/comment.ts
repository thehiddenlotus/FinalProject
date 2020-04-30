export class Comment {

    id: number;
    reviewId: number;
    userId: number;
    content: string;
    createdAt: string;
    updatedAt: string;
    reviewRating: number;
    replyId: number;
    active: boolean;

    constructor(
        id?: number,
        reviewId?: number,
        userId?: number,
        content?: string,
        createdAt?: string,
        updatedAt?: string,
        reviewRating?: number,
        replyId?: number,
        active?: boolean
    ) {
        this.id = id;
        this.reviewId = reviewId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reviewRating = reviewRating;
        this.replyId = replyId;
        this.active = active;
    }
}
