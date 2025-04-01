import { Component } from '@angular/core';
import { NgIf, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { response } from 'express';


@Component({
    selector: 'app_product-form',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './form.component.html',
    styleUrls: ['./form.component.scss']
})
export class FormComponent {
  product = {nombre: '', descripcion: '', precio: null, stock: null, categoria: ''};

  constructor(private productService: ProductService) {}


  saveProduct(): void{
    this.productService.saveProducts(this.product).subscribe(()=>{
      console.log("Producto guardado con exito", response);
    })
    console.log(this.product);
  }

}
