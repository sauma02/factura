import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoServiceService } from '../service/producto-service.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Producto } from '../model/producto';

@Component({
  selector: 'app-producto-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './producto-form.component.html',
  styleUrls: ['./producto-form.component.scss']
})
export class ProductoFormComponent {

  producto: Producto = new Producto();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productoServicio: ProductoServiceService
  ) {}

  onSubmit() {
    this.productoServicio.save(this.producto).subscribe(() => this.goToProductoList());
  }

  goToProductoList() {
    this.router.navigate(['/productos']);
  }

}
