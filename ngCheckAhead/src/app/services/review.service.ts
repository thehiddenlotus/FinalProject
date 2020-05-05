import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Review } from '../models/review';
import { AuthService } from './auth.service';
import { Location } from '../models/location';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private url = environment.baseUrl + 'api/reviews'
  private review: Review[] = [];
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Review[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewService.index: error retrieving review: ' + err);
      })
    );
  }
  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Review>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewService.show: error retrieving entry: ' + err);
      })
    );
  }
  public getReviewsByLocationId(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Review[]>(`${environment.baseUrl}api/locations/${id}/reviews`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(' error retrieving location: ' + err);
      })
    );
  }
  public create(review: Review, ratings: number[], locationId: number) {
    this.review.push(review);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Review>(environment.baseUrl+'api/locations/'+`${locationId}/reviews/${ratings[0]}/${ratings[1]}/${ratings[2]}/${ratings[3]}`, review, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewService.create: error creating entry: ' + err);
      })
    );
  }
  public update(review: Review) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Review>(`${this.url}/${review.id}`, review, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('ReviewService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewService.delete: error deleting entry: ' + err);
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
