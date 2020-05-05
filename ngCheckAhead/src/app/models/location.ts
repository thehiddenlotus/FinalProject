import { AverageRatings } from './average-ratings';
import { User } from 'src/app/models/user';
import { Address } from './address';

export class Location {
    id: number;
    address: Address;
    creatorId: number;
    dateUpdated: string;
    name: string;
    dateCreated: string;
    description: string;
    googleId: string;
    creator: User;
    averageRatings: AverageRatings;

    constructor(
        id?: number,
        address?: Address,
        creatorId?: number,
        dateUpdated?: string,
        name?: string,
        dateCreated?: string,
        description?: string,
        googleId?:string,
        creator?: User,
        averageRatings?: AverageRatings
    ) {
        this.id = id;
        this.address = address;
        this.creatorId = creatorId;
        this.dateUpdated = dateUpdated;
        this.name = name;
        this.dateCreated = dateCreated;
        this.description = description;
        this.googleId = googleId;
        this.creator = creator;
        this.averageRatings = averageRatings;
    }
}
