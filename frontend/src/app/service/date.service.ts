import { Injectable } from '@angular/core';
import * as moment from 'moment';
import {Moment} from 'moment';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  constructor() { }

  formatDate(date: string): string {
    return date ? this.formatMoment(moment(date).utc(true)) : null;
  }

  private formatMoment(m: Moment): string {
    if (!m) {
      return null;
    }
    return m.format('L') + ' ' + m.format('LT');
  }
}
