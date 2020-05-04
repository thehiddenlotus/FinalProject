import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Review } from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private url = environment.baseUrl + 'api/reviews'
  private review: Review[] = [];
  constructor(
    private http: HttpClient
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
  public create(review: Review) {
    this.review.push(review);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Review>(this.url, review, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ReviewService.create: error creating entry: ' + err);
      })
    );
  }
  public update(review: Review) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Review>(`${this.url}/${review.id}`, Review, httpOptions)
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
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'my-auth-token'
      })
    };
    return httpOptions;
  }
}
