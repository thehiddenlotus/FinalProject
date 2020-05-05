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

    constructor(
        id?: number,
        address?: Address,
        creatorId?: number,
        dateUpdated?: string,
        name?: string,
        dateCreated?: string,
        description?: string,
        googleId?:string,
        creator?: User
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
    }
}
