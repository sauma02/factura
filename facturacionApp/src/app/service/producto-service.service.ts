import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from '../model/producto';
import { Observable } from 'rxjs';

@Injectable()
export class ProductoServiceService{
  private productoUrl: string;
constructor(private http: HttpClient){
this.productoUrl= 'http://localhost:1011/api/productos';
}

public findAll(): Observable<Producto[]>{
  return this.http.get<Producto[]>(this.productoUrl);
}

public save(producto: Producto){
  return this.http.post<Producto>(this.productoUrl, producto);
}

}
 
