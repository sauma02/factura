import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [NgFor],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
  products: any[] = [];

constructor(private productService: ProductService){}

  ngOnInit(): void{
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }


}
