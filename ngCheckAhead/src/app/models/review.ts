import { User } from './user';
import { Location } from './location';
import { Comment } from './comment';

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

    constructor(
        id?: number,
        user?: User,
        location?: Location,
        content?: string,
        active?: boolean,
        dateCreated?: string,
        dateUpdated?: string,
        dateVisited?: string,
        comments?: Comment[]
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
    }
}
