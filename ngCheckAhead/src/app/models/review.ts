export class Review {
    id: number;
    userId: number;
    locationId: number;
    content: string;
    active: boolean;
    dateCreated: string;
    dateUpdated: string;
    dateVisited: string;

    constructor(
        id?: number,
        userId?: number,
        locationId?: number,
        content?: string,
        active?: boolean,
        dateCreated?: string,
        dateUpdated?: string,
        dateVisited?: string
    ) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.content = content;
        this.active = active;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.dateVisited = dateVisited;
    }
}
