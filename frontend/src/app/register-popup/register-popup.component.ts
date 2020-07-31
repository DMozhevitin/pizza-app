import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup} from '@angular/forms';
import {UserService} from '../service/user.service';
import {NotifierService} from 'angular-notifier';

@Component({
  selector: 'app-register-popup',
  templateUrl: './register-popup.component.html',
  styleUrls: ['./register-popup.component.css']
})
export class RegisterPopupComponent implements OnInit {
  registerForm: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
    phoneNumber: new FormControl(''),
    name: new FormControl('')
  });

  loginForm: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  });

  error = '';
  registerMode = false;
  selectedFormGroup = this.loginForm;

  constructor(
    private dialogRef: MatDialogRef<RegisterPopupComponent>,
    private userService: UserService,
    private notifierService: NotifierService
  ) { }



  ngOnInit(): void {
  }

  close(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.registerMode) {
      this.register();
    } else {
      this.login();
    }
  }

  register(): void {
    this.error = '';
    this.userService.register(
      this.registerForm.get('email').value,
      this.registerForm.get('password').value,
      this.registerForm.get('phoneNumber').value,
      this.registerForm.get('name').value
    ).subscribe(res => {
        localStorage.setItem('user', JSON.stringify(res));
        this.notifierService.notify('success', 'You were successfully registered!');
        this.close();
    }, resp => {
        this.error = resp.error.message;
      });
  }

  login(): void {
    this.error = '';
    this.userService.login(this.loginForm.get('email').value, this.loginForm.get('password').value)
      .subscribe(res => {
        localStorage.setItem('user', JSON.stringify(res));
        this.notifierService.notify('success', 'You were successfully logged in!');
        this.close();
      }, resp => {
        this.error = resp.error.message;
      });
  }

  toggleMode(): void {
    if (this.registerMode) {
      this.error = '';
      this.registerMode = false;
      this.selectedFormGroup = this.loginForm;
      this.dialogRef.updateSize('35%', '40%');
    } else {
      this.error = '';
      this.registerMode = true;
      this.selectedFormGroup = this.registerForm;
      this.dialogRef.updateSize('35%', '62%');
    }
  }
}
