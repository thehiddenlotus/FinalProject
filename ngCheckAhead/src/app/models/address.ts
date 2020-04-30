export class Address {
    id: number;
    address: string;
    city: string;
    zip: number;
    state: string;

    constructor(
        id?: number,
        address?: string,
        city?: string,
        zip?: number,
        state?: string,
    ) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }
}
