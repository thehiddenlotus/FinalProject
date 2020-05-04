import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { User } from '../models/user';
import { Address } from '../models/address';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = environment.baseUrl + 'api/users'
  private user: User[] = [];
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<User[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.index: error retrieving user: ' + err);
      })
    );
  }
  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<User>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(user: User) {
    this.user.push(user);
    const httpOptions = this.getHttpOptions();
    return this.http.post<User>(this.url, user, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.create: error creating entry: ' + err);
      })
    );
  }
  public update(user: User) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<User>(`${this.url}/${user.id}`, User, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('UserService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.delete: error deleting entry: ' + err);
      })
    );
  }
  private getHttpOptions() {
    const credentials = this.auth.getCredentials();
    let httpOptions = {};
    if (credentials) {

      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest',
          'Authorization': `Basic ${credentials}`
        })
      };
    } else {
      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest'
        })
      };
    }
    return httpOptions;
  }
}
