import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from '../model/producto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductoServiceService{
  private productoUrl: string;
constructor(private http: HttpClient){
this.productoUrl= 'https://super-duper-space-halibut-vx7g95wr465hwj9j-8080.github.dev/api';
}

public findAll(): Observable<Producto[]>{
  return this.http.get<Producto[]>(this.productoUrl);
}

public save(producto: Producto){
  return this.http.post<Producto>(this.productoUrl, producto);
}

}
 
