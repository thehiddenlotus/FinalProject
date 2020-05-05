import { Pipe, PipeTransform } from '@angular/core';
import { Location } from 'src/app/models/location';


@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {
  
  transform(locations: Location[], search: string): Location[] {
    const results = [];
    
    if(search === null){
      return locations;
    }

    locations.forEach((location) => {
      if(location.name.includes(search)||location.address.address.includes(search))
        results.push(location);
    })

    locations.forEach((leftovers) => {
      if(!results.includes(leftovers)){
        results.push(leftovers)
      }
    })

    return results;
  }

}


// splice()	Adds or removes elements from the array
// unshift()	Adds one or more elements to the beginning of the array