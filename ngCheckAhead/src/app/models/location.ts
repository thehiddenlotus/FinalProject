import { Address } from './address';

export class Location {
    id: number;
    addressId: number;
    creatorId: number;
    dateUpdated: string;
    name: string;
    dateCreated: string;
    description: string;
    address: Address;

    constructor(
        id?: number,
        addressId?: number,
        creatorId?: number,
        dateUpdated?: string,
        name?: string,
        dateCreated?: string,
        description?: string,
        address?: Address
    ) {
        this.id = id;
        this.addressId = addressId;
        this.creatorId = creatorId;
        this.dateUpdated = dateUpdated;
        this.name = name;
        this.dateCreated = dateCreated;
        this.description = description;
        this.address = address;
    }
}
