import { AuthService } from './../../services/auth.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  public registrationForm!: FormGroup;

  constructor(
    private _router: Router,
    private _formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.createForm();
  }
  createForm() {
    this.registrationForm = this._formBuilder.group({
      email: ['', Validators.required],
      name: [''],
      password: ['', Validators.required],

      username: ['', Validators.required],
    });
  }

  register(values: any) {
    values.role = 'user';
    console.log('values', values);
    localStorage.clear();
    this.authService.addUser(values).subscribe((s: any) => {
      console.log(s);
      this._router.navigate(['/login']);
    });
  }
}
