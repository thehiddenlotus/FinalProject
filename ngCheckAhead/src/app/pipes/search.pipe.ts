import { Pipe, PipeTransform } from '@angular/core';
import { Location } from 'src/app/models/location';
import { getLocaleDayNames } from '@angular/common';


@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {
  
  transform(locations: Location[], search: string): Location[] {
    const results = [];
    if(search === null){
      return locations;
    }
    var searchKey: string = search.toLowerCase().toString();
    
    locations.forEach((location) => {
      var locName: string = location.name.toLowerCase().toString();
      var locAdd: string = location.address.address.toLowerCase().toString();
      if(locName.includes(searchKey)||locAdd.includes(searchKey))
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