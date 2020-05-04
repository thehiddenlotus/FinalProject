import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private url = environment.baseUrl + 'api/addresses'
  private address: Address[] = [];
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Address[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AddressService.index: error retrieving address: ' + err);
      })
    );
  }
  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Address>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AddressService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(address: Address) {
    this.address.push(address);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Address>(this.url, address, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AddressService.create: error creating entry: ' + err);
      })
    );
  }
  public update(address: Address) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Address>(`${this.url}/${address.id}`, address, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('AddressService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AddressService.delete: error deleting entry: ' + err);
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

