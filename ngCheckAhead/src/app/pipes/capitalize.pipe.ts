import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalize'
})
export class CapitalizePipe implements PipeTransform {

  transform(str: string): string {
    let result = '';
    result += str.charAt(0).toUpperCase();
    result += str.substring(1);
    return result;
  }

}