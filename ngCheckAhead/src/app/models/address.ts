export class Address {
  id: number;
  address: string;
  city: string;
  state: string;
  zip: number;

  constructor(
    id?: number,
    address?: string,
    city?: string,
    state?:string,
    zip?: number

  ){
    this.id = id;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }
}
