import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Order} from '../dto/dto';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    observe: 'response' as 'response',
    withCredentials: true
  };

  constructor(
    private http: HttpClient
  ) { }

  public createOrder(userId: number, order: Order): Observable<any> {
    return this.http.post('/api/order/' + userId, order, this.httpOptions);
  }

  public loadRecentOrders(userId: number): Observable<any> {
    return this.http.get<any>('/api/orders/' + userId, this.httpOptions);
  }
}
