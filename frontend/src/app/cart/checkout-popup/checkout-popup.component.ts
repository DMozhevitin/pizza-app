import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup} from '@angular/forms';
import {OrderService} from '../../service/order.service';
import {Cart, Order} from '../../dto/dto';
import {NotifierService} from 'angular-notifier';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-checkout-popup',
  templateUrl: './checkout-popup.component.html',
  styleUrls: ['./checkout-popup.component.css']
})
export class CheckoutPopupComponent implements OnInit {
  cart: Cart;
  error = '';
  form: FormGroup = new FormGroup({
    name: new FormControl(this.loggedInUser() ? this.loggedInUser().name : ''),
    address: new FormControl(''),
    phone: new FormControl(this.loggedInUser() ? this.loggedInUser().phoneNumber : '')
  });


  constructor(
    private dialogRef: MatDialogRef<CheckoutPopupComponent>,
    private orderService: OrderService,
    private notifierService: NotifierService,
    public userService: UserService,
    @Inject(MAT_DIALOG_DATA) data: Cart) {
    this.cart = data;
  }

  ngOnInit(): void {
  }

  close(): void {
    this.dialogRef.close();
  }

  loggedInUser(): any {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user == null) {
      return null;
    }

    return user;
  }

  createOrder(): void {
    const userId = this.userService.loggedInUserId();
    const order = new Order();
    order.address = this.form.get('address').value;
    order.phoneNumber = this.form.get('phone').value;
    order.name = this.form.get('name').value;

    this.orderService.createOrder(userId, order).subscribe(__ => {
      this.cart.cartItems = [];
      this.notifierService.notify('success', 'Your order has been successfully completed. We will call you soon');
      this.close();
    }, resp => {
      this.error = resp.error.message;
    });
  }
}
