export class AverageRatings {
     cleanliness: number;
     traffic: number;
     checkout: number;
     stock: number;
     constructor(
        cleanliness?: number,
        traffic?: number,
        checkout?: number,
        stock?: number
     ){
         this.cleanliness = cleanliness;
         this.traffic = traffic;
         this.checkout = checkout;
         this.stock = stock;
     }
}
