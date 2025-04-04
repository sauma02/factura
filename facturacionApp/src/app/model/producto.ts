export class Producto {

id: number;
nombre: string;
descripcion: string;
precio: number;
stock: number;
categoria: string;



constructor(
    id: number = 0,
    nombre: string = '',
    descripcion: string = '',
    precio: number = 0,
    stock: number = 0,
    categoria: string = ''
  ) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
    this.categoria = categoria;
  }


}
