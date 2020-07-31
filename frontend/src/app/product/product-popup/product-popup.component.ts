import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ProductWithQty, Product} from '../../dto/dto';
import {ProductService} from '../../service/product.service';
import {NotifierService} from 'angular-notifier';
import {CurrencyService} from '../../service/currency.service';
import {HttpResponse} from '@angular/common/http';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-product-popup',
  templateUrl: './product-popup.component.html',
  styleUrls: ['./product-popup.component.css']
})
export class ProductPopupComponent implements OnInit {
  product: Product;
  qty = 1;
  selectedSize = null;

  constructor(
    private dialogRef: MatDialogRef<ProductPopupComponent>,
    @Inject(MAT_DIALOG_DATA) data: Product,
    private productService: ProductService,
    private notifierService: NotifierService,
    public currencyService: CurrencyService,
    private userService: UserService
  ) {
    this.product = data;
    this.selectedSize = this.product.sizes[0];
  }

  ngOnInit(): void {
  }

  close(): void {
    this.dialogRef.close();
  }

  // FIXME
  incQty(): void {
    if (this.qty < 100) {
      this.qty++;
    }
  }

  decQty(): void {
    if (this.qty > 1) {
      this.qty--;
    }
  }

  calcPrice(): number {
    return this.getPrice(+this.selectedSize) * this.qty;
  }

  public getPrice(size: number): number {
    let idx = null;

    if (size === 10) {
      idx = 0;
    } else if (size === 12) {
      idx = 1;
    } else if (size === 14) {
      idx = 2;
    }

    return this.product.prices[idx];
  }

  updateCart(): void {
    // const user = JSON.parse(localStorage.getItem('user'));
    const userId = this.userService.loggedInUserId();
    const order = new ProductWithQty();
    order.product = this.product;
    order.qty = this.qty;
    order.size = +this.selectedSize;
    order.increase = true;
    this.productService.updateCart(userId, order).subscribe((res: HttpResponse<any>) => {
      this.notifierService.notify('success', 'Product was succesfully added to the cart');
      this.dialogRef.close();
    }, error => {
    });
  }
}


