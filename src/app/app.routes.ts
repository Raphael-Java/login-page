import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component'; 
import { SignupComponent } from './pages/signup/signup.component';

export const routes: Routes = [
    {
       path: "login", //"Login com L maiúsculo deu erro"
       component: LoginComponent
    },

    {
       path: "signup", //"Login com L maiúsculo deu erro"
       component: SignupComponent
    }
];
