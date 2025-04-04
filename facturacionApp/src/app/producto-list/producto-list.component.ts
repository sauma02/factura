import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { Producto } from '../model/producto';
import { ProductoServiceService } from '../service/producto-service.service';

@Component({
  selector: 'app-producto-list',
  standalone: true, 
  imports: [CommonModule], 
  templateUrl: './producto-list.component.html',
  styleUrls: ['./producto-list.component.scss']
})
export class ProductoListComponent implements OnInit {

  productos: Producto[] = [];

  constructor(private productoServicio: ProductoServiceService) {}

  ngOnInit(): void {
    this.productoServicio.findAll().subscribe(data => {
      this.productos = data;
    });
  }

}
