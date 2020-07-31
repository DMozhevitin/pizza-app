import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {CheckoutPopupComponent} from '../checkout-popup/checkout-popup.component';
import {Cart, ProductWithQty} from '../../dto/dto';
import {CurrencyService} from '../../service/currency.service';

@Component({
  selector: 'app-order-details-card',
  templateUrl: './order-details-card.component.html',
  styleUrls: ['./order-details-card.component.css']
})
export class OrderDetailsCardComponent implements OnInit {
  deliveryPrice = 3;
  @Input() cart: Cart;
  constructor(
    private matDialog: MatDialog,
    public currencyService: CurrencyService
    ) { }

  ngOnInit(): void {
  }

  openCheckoutPopup(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '35%';
    dialogConfig.height = '53%';
    dialogConfig.data = this.cart;

    this.matDialog.open(CheckoutPopupComponent, dialogConfig);
  }

  sumPrice(): number {
    let total = 0;

    this.cart.cartItems.forEach(item => {
      total += this.getPrice(item) * item.qty;
    });

    return total;
  }

  total(): number {
    return this.sumPrice() === 0 ? 0 : this.sumPrice() + this.deliveryPrice;
  }

  public getPrice(order: ProductWithQty): number {
    let idx = null;

    if (order.size === 10) {
      idx = 0;
    } else if (order.size === 12) {
      idx = 1;
    } else if (order.size === 14) {
      idx = 2;
    }

    return order.product.prices[idx];
  }
}
