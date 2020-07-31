import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public register(email: string, password: string, phoneNumber: string, name: string): Observable<any> {
    return this.http.post('/api/register', {
      email,
      password,
      phoneNumber,
      name
    });
  }

  public login(email: string, password: string): Observable<any> {
    return this.http.post('/api/login', {
      email,
      password
    });
  }

  public loggedInUserId(): number {
    const user = JSON.parse(localStorage.getItem('user'));
    return user ? user.id : -1;
  }
}
