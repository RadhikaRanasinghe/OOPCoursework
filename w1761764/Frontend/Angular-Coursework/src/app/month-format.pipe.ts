import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'monthFormat'
})
export class MonthFormatPipe implements PipeTransform {
  transform(monthNum: number): string {
    if (monthNum === 1) {
      return 'JAN';
    } else if (monthNum === 2) {
      return 'FEB';
    } else if (monthNum === 3) {
      return 'MAR';
    } else if (monthNum === 4) {
      return 'APR';
    } else if (monthNum === 5) {
      return 'MAY';
    } else if (monthNum === 6) {
      return 'JUN';
    } else if (monthNum === 7) {
      return 'JUL';
    } else if (monthNum === 8) {
      return 'AUG';
    } else if (monthNum === 9) {
      return 'SEP';
    } else if (monthNum === 10) {
      return 'OCT';
    } else if (monthNum === 11) {
      return 'NOV';
    } else {
      return 'DEC';
    }
  }
}
