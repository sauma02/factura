export class Producto {

id: number;
nombre: string;
descripcion: string;
precio: number;
stock: number;
categoria: string;

constructor();

constructor(id: number, nombre: string, descripcion: string, precio: number, stock: number, categoria: string){
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
    this.categoria = categoria;    
    }


}
