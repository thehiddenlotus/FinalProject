import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Comment } from '../models/comment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})

export class ReviewCommentService {

  private url = environment.baseUrl + 'api/comment'
  private comment : Comment [] = [];

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Comment[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('CommentService.index: error retrieving comment: ' + err);
      })
    );
  }
  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Comment>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('CommentService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(comment: Comment) {
   this.comment.push(comment);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Comment>(environment.baseUrl+'api/locations/reviews/'
          + comment.review.id
          + '/comments',
          comment,
          httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('CommentService.create: error creating entry: ' + err);
      })
    );
  }
  public update(comment: Comment) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Comment>(`${this.url}/${comment.id}`, Comment, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('CommentService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('CommentService.delete: error deleting entry: ' + err);
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
