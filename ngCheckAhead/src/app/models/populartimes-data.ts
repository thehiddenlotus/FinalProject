import { TrafficData } from './traffic-data';
export class PopulartimesData {
    data: TrafficData[];
    constructor(
        data?: TrafficData[]
    ){
        this.data = data
    }
}
