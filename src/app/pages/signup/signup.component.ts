import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { LoginService } from '../../services/login.service';
import { ToastService } from '../../services/toast.service';

interface SignupForm {
  name: FormControl;
  email: FormControl;
  password: FormControl;
  passwordConfirm: FormControl;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    DefaultLoginLayoutComponent,
    ReactiveFormsModule,
    PrimaryInputComponent,
  ],
  providers: [
    LoginService
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  signupForm!: FormGroup<SignupForm>;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastService: ToastService
  ) {
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)]) 
    });
  }

  submit() {
    const { name, email, password, passwordConfirm } = this.signupForm.value;

    if (password !== passwordConfirm) {
      this.toastService.error("As senhas não coincidem!");
      return;
    }

    this.loginService.signup(name, email, password).subscribe({
      next: () => {
        this.toastService.success("Conta criada com sucesso!");
        this.router.navigate(["login"]);
      },
      error: () => this.toastService.error("Erro inesperado! Tente novamente mais tarde")
    });
  }

  navigate() {
    this.router.navigate(["login"]);
  }
}