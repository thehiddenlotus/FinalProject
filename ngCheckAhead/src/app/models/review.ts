import { User } from './user';
import { Location } from './location';
import { Comment } from './comment';
import { ReviewRating } from './review-rating';

export class Review {
    id: number;
    user: User;
    location: Location;
    content: string;
    active: boolean;
    dateCreated: string;
    dateUpdated: string;
    dateVisited: string;
    comments: Comment[];
    reviewRatings: ReviewRating[];

    constructor(
        id?: number,
        user?: User,
        location?: Location,
        content?: string,
        active?: boolean,
        dateCreated?: string,
        dateUpdated?: string,
        dateVisited?: string,
        comments?: Comment[],
        reviewRatings?: ReviewRating[]
    ) {
        this.id = id;
        this.user = user;
        this.location = location;
        this.content = content;
        this.active = active;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.dateVisited = dateVisited;
        this.comments = comments;
        this.reviewRatings = reviewRatings;
    }
}
