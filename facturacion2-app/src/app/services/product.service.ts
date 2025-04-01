import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = "http://localhost:1011/api/productos";

  constructor(private http: HttpClient) { } 

  getProducts(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  saveProducts(product: any): Observable<any> {
    return this.http.post(this.apiUrl, product);
  } 
}
