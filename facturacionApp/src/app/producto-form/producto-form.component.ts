import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoServiceService } from '../service/producto-service.service';
import { Producto } from '../model/producto';

@Component({
  selector: 'app-producto-form',
  imports: [],
  templateUrl: './producto-form.component.html',
  styleUrl: './producto-form.component.scss'
})
export class ProductoFormComponent {

  producto: Producto;

  constructor(private route: ActivatedRoute, private router: Router, 
    private productoServicio: ProductoServiceService){

      this.producto = new Producto();

  }

}
