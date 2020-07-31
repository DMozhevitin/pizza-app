import {Component, OnInit} from '@angular/core';
import {ProductService} from '../service/product.service';
import {Cart} from '../dto/dto';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart: Cart = new Cart();

  constructor(
    private productService: ProductService,
    private userService: UserService
    ) {
    this.loadCart();
  }

  ngOnInit(): void {
  }

  loadCart(): void {
    const userId = this.userService.loggedInUserId();
    this.productService.loadCart(userId).subscribe(res => {
      this.cart = res.body;
    });
  }

  isEmpty(): boolean {
    return !this.cart.cartItems.length;
  }
}
