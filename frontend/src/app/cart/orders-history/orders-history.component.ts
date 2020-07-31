import { Component, OnInit } from '@angular/core';
import {Order, ProductWithQty} from '../../dto/dto';
import {OrderService} from '../../service/order.service';
import {DateService} from '../../service/date.service';
import {CurrencyService} from '../../service/currency.service';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-orders-history',
  templateUrl: './orders-history.component.html',
  styleUrls: ['./orders-history.component.css']
})
export class OrdersHistoryComponent implements OnInit {
  orders: Order[];

  constructor(
    private orderService: OrderService,
    public dateService: DateService,
    public currencyService: CurrencyService,
    private userService: UserService
  ) {
    this.loadRecentOrders();
  }

  ngOnInit(): void {
  }

  loadRecentOrders(): void {
    const userId = this.userService.loggedInUserId();
    this.orderService.loadRecentOrders(userId).subscribe(res => {
      this.orders = res.body;
    });
  }

  sumLinesInOrder(orderIndex: number): number[] {
    const sums = [];

    this.orders[orderIndex].cart.cartItems.forEach(item => {
      sums.push(this.getPrice(item) * item.qty);
    });

    return sums;
  }

  totalInOrder(orderIndex: number): number {
    let sum = 3; // delivery cost
    const sums = this.sumLinesInOrder(orderIndex);

    sums.forEach(v => sum += v);

    return sum;
  }

  public getPrice(item: ProductWithQty): number {
    let idx = null;

    if (item.size === 10) {
      idx = 0;
    } else if (item.size === 12) {
      idx = 1;
    } else if (item.size === 14) {
      idx = 2;
    }

    return item.product.prices[idx];
  }
}
