import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'monthsFormat'
})
export class MonthsFormatPipe implements PipeTransform {
  transform(monthsNum: number): string {
    if (monthsNum === 1) {
      return 'January';
    } else if (monthsNum === 2) {
      return 'February';
    } else if (monthsNum === 3) {
      return 'March';
    } else if (monthsNum === 4) {
      return 'April';
    } else if (monthsNum === 5) {
      return 'May';
    } else if (monthsNum === 6) {
      return 'June';
    } else if (monthsNum === 7) {
      return 'July';
    } else if (monthsNum === 8) {
      return 'August';
    } else if (monthsNum === 9) {
      return 'September';
    } else if (monthsNum === 10) {
      return 'October';
    } else if (monthsNum === 11) {
      return 'November';
    } else {
      return 'December';
    }
  }
}
