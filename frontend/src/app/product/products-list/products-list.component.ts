import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product.service';
import {Product} from '../../dto/dto';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  products: Product[] = [];

  constructor(
    private productService: ProductService
  ) {
    this.loadProducts();
  }

  ngOnInit(): void {
  }

  private loadProducts(): void {
    this.productService.loadAll().subscribe(res => {
      this.products = res;
    });
  }
}
