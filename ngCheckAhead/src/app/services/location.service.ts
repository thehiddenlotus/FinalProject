import { PopulartimesData } from './../models/populartimes-data';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Location } from '../models/location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  [x: string]: any;
  private url = environment.baseUrl + 'api/locations'
  private address : Location [] = [];
  constructor(
    private http: HttpClient
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Location[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.index: error retrieving location: ' + err);
      })
    );
  }

  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Location>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(location: Location) {
   this.location.push(location);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Location>(this.url, location, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.create: error creating entry: ' + err);
      })
    );
  }
  public update(location: Location) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Location>(`${this.url}/${location.id}`, Location, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('LocationService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.delete: error deleting entry: ' + err);
      })
    );
  }
  private getHttpOptions() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Athorization': 'my-auth-token'
      })
    };
    return httpOptions;
  }
}



