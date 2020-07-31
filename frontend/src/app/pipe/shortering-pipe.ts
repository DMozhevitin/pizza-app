import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'shorter'
})
export class ShorteringPipe implements PipeTransform {
  transform(value: string): string {
    if (value.length <= 12) {
      return value;
    }
    return value.substr(0, 12) + '...';
  }
}
