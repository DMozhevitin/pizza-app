import {NgModule} from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {CartComponent} from './cart/cart.component';
import {ProductsListComponent} from './product/products-list/products-list.component';

const routes: Routes = [
  { path: '', component: ProductsListComponent },
  { path: 'cart', component: CartComponent},
  { path: '**', redirectTo: '/' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
