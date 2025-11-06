import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-login', 
  standalone: true,    
  imports: [CommonModule], 
  templateUrl: './login-cliente.html',
  styleUrls: ['./login-cliente.css']
})
export class LoginComponent {

  abaAtual: 'login' | 'register' = 'login';

  loginPasswordType: string = 'password';
  loginPasswordIcon: 'open' | 'closed' = 'open';

  regPasswordType: string = 'password';
  regPasswordIcon: 'open' | 'closed' = 'open';

  regConfirmPasswordType = 'password';
  regConfirmPasswordIcon = 'closed';

  constructor() { }

  toggleLoginPassword() {
    if (this.loginPasswordType === 'password') {
      this.loginPasswordType = 'text';
      this.loginPasswordIcon = 'closed';
    } else {
      this.loginPasswordType = 'password';
      this.loginPasswordIcon = 'open';
    }
  }

  toggleRegPassword() {
    if (this.regPasswordType === 'password') {
      this.regPasswordType = 'text';
      this.regPasswordIcon = 'closed';
    } else {
      this.regPasswordType = 'password';
      this.regPasswordIcon = 'open';
    }
  }

  onFormSubmit(event: Event) {
    event.preventDefault(); 
    
    if (this.abaAtual === 'login') {
      console.log('Formulário de Login enviado!');
    } else {
      console.log('Formulário de Registro enviado!');
    }
  }

    toggleRegConfirmPassword(): void {
    this.regConfirmPasswordType = this.regConfirmPasswordType === 'password' ? 'text' : 'password';
    this.regConfirmPasswordIcon = this.regConfirmPasswordIcon === 'open' ? 'closed' : 'open';
  }
}