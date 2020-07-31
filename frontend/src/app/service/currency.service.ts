import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    observe: 'response' as 'response',
    withCredentials: true
  };

  constructor(
    private http: HttpClient
  ) { }

  public toggleCurrency(userId: number): Observable<any> {
    return this.http.patch('/api/currency/' + userId, {}, this.httpOptions);
  }

  public getSelectedCurrency(): any {
    const user = JSON.parse(localStorage.getItem('user'));

    if (!user) {
      return null;
    }

    return user.currency;
  }

  public formatNumber(n: number): string {
    const currency = this.getSelectedCurrency();

    if (!currency || currency.name === 'USD') {
      return '$' + n.toFixed(2);
    } else {
      return '€' + (n * currency.coeff).toFixed(2);
    }
  }
}
