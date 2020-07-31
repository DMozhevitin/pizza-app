import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {ProductPopupComponent} from '../product/product-popup/product-popup.component';
import {RegisterPopupComponent} from '../register-popup/register-popup.component';
import {NotifierService} from 'angular-notifier';
import {CurrencyService} from '../service/currency.service';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  usdSelected = null;
  constructor(private dialog: MatDialog,
              private notifierService: NotifierService,
              private currencyService: CurrencyService,
              private userService: UserService
              ) {
    if (this.isLoggedIn()) {
      this.usdSelected = this.loggedInUser().currency.name === 'USD';
    } else {
      this.usdSelected = true;
    }
  }

  ngOnInit(): void {
  }

  isLoggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('user'));
    return user && !user.guest;
  }

  loggedInUser(): any {
    return JSON.parse(localStorage.getItem('user'));
  }

  loggedUserName(): string {
    const user = JSON.parse(localStorage.getItem('user'));
    return user ? user.name : null;
  }


  logout(): void {
    localStorage.removeItem('user');
    this.notifierService.notify('success', 'You were successfully logged out');
  }


  openRegisterPopup(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '35%';
    dialogConfig.height = '45%';
    dialogConfig.panelClass = 'app-register-popup';

    const dialogRef = this.dialog.open(RegisterPopupComponent, dialogConfig);
  }

  toggleCurrency(): void {

    const userId = this.userService.loggedInUserId();

    this.currencyService.toggleCurrency(userId).subscribe(res => {
      localStorage.setItem('user', JSON.stringify(res.body));
      this.usdSelected = res.body.currency.name === 'USD';
    });
  }
}
