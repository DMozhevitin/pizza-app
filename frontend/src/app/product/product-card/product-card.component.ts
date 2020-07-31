import {Component, Input, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProductPopupComponent} from '../product-popup/product-popup.component';
import {Product} from '../../dto/dto';
import {CurrencyService} from '../../service/currency.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input() product: Product;

  constructor(
    private dialog: MatDialog,
    public currencyService: CurrencyService
  ) { }

  ngOnInit(): void {
  }

  minPrice(): number {
    return this.product && this.product.prices ? this.product.prices[0] : null;
  }

  openPopup(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '55%';
    dialogConfig.height = '60%';
    dialogConfig.panelClass = 'app-product-popup';

    dialogConfig.data = this.product;
    const dialogRef = this.dialog.open(ProductPopupComponent, dialogConfig);
  }

  imgPath(): string {
    return 'assets/img/pizza/' + this.product.name + '.png';
  }
}
