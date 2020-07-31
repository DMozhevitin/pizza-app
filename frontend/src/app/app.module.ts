// import { BrowserModule } from '@angular/platform-browser';
// import { NgModule } from '@angular/core';
//
// import { AppComponent } from './app.component';
// import { ProductCardComponent } from './product/product-card/product-card.component';
// import { NavBarComponent } from './nav-bar/nav-bar.component';
// import { ProductPopupComponent } from './product/product-popup/product-popup.component';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import {MatDialogModule} from '@angular/material/dialog';
// import {MatButtonToggle, MatButtonToggleModule} from '@angular/material/button-toggle';
// import {MatButtonModule} from '@angular/material/button';
// import {FormsModule, ReactiveFormsModule} from '@angular/forms';
// import { RegisterPopupComponent } from './register-popup/register-popup.component';
// import {MatFormFieldModule} from '@angular/material/form-field';
// import {MatInputModule} from '@angular/material/input';
// import {MatCardModule} from '@angular/material/card';
// import {UserService} from './service/user.service';
// import {HttpClient, HttpClientModule} from '@angular/common/http';
// import {Router, RouterModule} from '@angular/router';
// import {NotifierModule, NotifierOptions} from 'angular-notifier';
// import {MatIconModule} from '@angular/material/icon/icon-module';
// import { CartComponent } from './cart/cart.component';
// import {AppRoutingModule} from './app-routing.module';
// import { ProductsListComponent } from './product/products-list/products-list.component';
// import {MatSliderModule} from '@angular/material/slider/slider-module';
// import { CartItemComponent } from './cart/cart-item/cart-item.component';
// import { CheckoutPopupComponent } from './cart/checkout-popup/checkout-popup.component';
// import { OrderDetailsCardComponent } from './cart/order-details-card/order-details-card.component';
// import {ProductService} from './service/product.service';
// import { OrdersHistoryComponent } from './cart/orders-history/orders-history.component';
// import { OrderComponent } from './cart/order/order.component';
// import {MatListModule} from '@angular/material/list/list-module';
// import {ShorteringPipe} from './pipe/shortering-pipe';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ProductCardComponent } from './product/product-card/product-card.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ProductPopupComponent } from './product/product-popup/product-popup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonToggle, MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegisterPopupComponent } from './register-popup/register-popup.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {UserService} from './service/user.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {Router, RouterModule} from '@angular/router';
import {NotifierModule, NotifierOptions} from 'angular-notifier';
import {MatIconModule} from '@angular/material/icon';
import { CartComponent } from './cart/cart.component';
import {AppRoutingModule} from './app-routing.module';
import { ProductsListComponent } from './product/products-list/products-list.component';
import {MatSliderModule} from '@angular/material/slider';
import { CartItemComponent } from './cart/cart-item/cart-item.component';
import { CheckoutPopupComponent } from './cart/checkout-popup/checkout-popup.component';
import { OrderDetailsCardComponent } from './cart/order-details-card/order-details-card.component';
import {ProductService} from './service/product.service';
import { OrdersHistoryComponent } from './cart/orders-history/orders-history.component';
import { OrderComponent } from './cart/order/order.component';
import {MatListModule} from '@angular/material/list';
import {ShorteringPipe} from './pipe/shortering-pipe';

const notofierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: 'right',
      distance: 12
    },
    vertical: {
      position: 'bottom',
      distance: 12,
      gap: 10
    }
  }
};


@NgModule({
  declarations: [
    AppComponent,
    ProductCardComponent,
    NavBarComponent,
    ProductPopupComponent,
    RegisterPopupComponent,
    CartComponent,
    ProductsListComponent,
    CartItemComponent,
    CheckoutPopupComponent,
    OrderDetailsCardComponent,
    OrdersHistoryComponent,
    OrderComponent,
    ShorteringPipe
  ],
  imports: [
    BrowserModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatButtonToggleModule,
    MatButtonModule,
    FormsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCardModule,
    HttpClientModule,
    AppRoutingModule,
    NotifierModule.withConfig(notofierOptions),
    MatIconModule,
    MatSliderModule,
    MatListModule
  ],
  providers: [UserService, HttpClient, RouterModule, ProductService],
  bootstrap: [AppComponent],
  entryComponents: [ProductPopupComponent, RegisterPopupComponent]
})
export class AppModule { }
