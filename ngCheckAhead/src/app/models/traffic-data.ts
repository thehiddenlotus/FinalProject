import { TrafficDetail } from './traffic-detail';

export class TrafficData {
        address: String; 
        id: String; 
        name: String; 
        populartimes: TrafficDetail[]; 
        rating: number; 
        "types": String[]
      
        constructor(
            address?: String ,
            id?: String,
            name?: String ,
            populartimes?: TrafficDetail[] ,
            rating?: number ,
            types?: String[]
        ){
            this.address = address;
            this.id = id;
            this.name = name;
            this.populartimes = populartimes;
            this.rating = rating;
            this.types = types;

        }
    
}
