import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUserSubject!: BehaviorSubject<any>;
  public currentUser!: Observable<any>;
  constructor(
    private http: HttpClient,
    private _router: Router,
    private _route: ActivatedRoute
  ) {}

  //POST /api/auth/signin
  login(username: string, password: string) {
    return this.http.post<any>('/signin', {
      username: username,
      password: password,
    });
  }

  addUser(user: any): Observable<any> {
    return this.http.post('/signup', user);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('token');
    localStorage.removeItem('currentuser');
  }
}
