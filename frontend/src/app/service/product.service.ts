import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cart, ProductWithQty, Product} from '../dto/dto';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    observe: 'response' as 'response',
    withCredentials: true
  };

  constructor(private http: HttpClient) { }

  public loadAll(): Observable<Product[]> {
    return this.http.get<Product[]>('/api/products');
  }

  public updateCart(userId: number, order: ProductWithQty): Observable<any> {
    return this.http.put('/api/cart/' + userId, order, this.httpOptions);
  }

  public loadCart(userId: number): Observable<any> {
    return this.http.get<Cart>('/api/cart/' + userId, this.httpOptions);
  }

  public deleteItemInCart(userId: number, order: ProductWithQty): Observable<any> {
    return this.http.post<any>('/api/cart/' + userId, order, this.httpOptions);
  }
}
