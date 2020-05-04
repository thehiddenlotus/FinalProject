import { Injectable } from '@angular/core';
import { Rating } from '../models/rating';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private url = environment.baseUrl + 'api/rating'
  private rating : Rating [] = [];
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Rating[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RatingService.index: error retrieving rating: ' + err);
      })
    );
  }
  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Rating>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RatingService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(rating: Rating) {
   this.rating.push(rating);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Rating>(this.url, rating, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AddressService.create: error creating entry: ' + err);
      })
    );
  }
  public update(rating: Rating) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Rating>(`${this.url}/${rating.id}`, Rating, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('RatingService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RatingService.delete: error deleting entry: ' + err);
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
