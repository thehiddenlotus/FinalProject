export class Location {
    id: number;
    addressId: number;
    creatorId: number;
    dateUpdated: string;
    name: string;
    dateCreated: string;
    description: string;

    constructor(
        id?: number,
        addressId?: number,
        creatorId?: number,
        dateUpdated?: string,
        name?: string,
        dateCreated?: string,
        description?: string
    ) {
        this.id = id;
        this.addressId = addressId;
        this.creatorId = creatorId;
        this.dateUpdated = dateUpdated;
        this.name = name;
        this.dateCreated = dateCreated;
        this.description = description;
    }
}
