import {Component, Input, OnInit} from '@angular/core';
import {Cart, ProductWithQty} from '../../dto/dto';
import {ProductService} from '../../service/product.service';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() cartItem: ProductWithQty;
  @Input() cart: Cart;
  deleted = false;

  constructor(
    private productService: ProductService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  removeItem(): void {
    // const user = JSON.parse(localStorage.getItem('user'));
    const userId = this.userService.loggedInUserId();
    this.productService.deleteItemInCart(userId, this.cartItem).subscribe(res => {
      this.cart.cartItems = this.cart.cartItems.filter(item => item !== this.cartItem);
      this.deleted = true;
    });
  }

  addQty(): void {
    this.cartItem.qty++;
    const userId = this.userService.loggedInUserId();
    // const user = JSON.parse(localStorage.getItem('user'));
    this.productService.updateCart(userId, this.cartItem).subscribe();
  }

  decQty(): void {
    if (this.cartItem.qty > 1) {
      this.cartItem.qty--;
      // const user = JSON.parse(localStorage.getItem('user'));
      const userId = this.userService.loggedInUserId();
      this.productService.updateCart(userId, this.cartItem).subscribe();
    }
  }


}
