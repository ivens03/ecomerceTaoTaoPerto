// src/app/login/login.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // IMPORTANTE!

@Component({
  selector: 'app-login', // <--- Essa é a tag HTML do seu componente
  standalone: true,     // Projetos novos usam 'standalone: true'
  imports: [CommonModule], // Precisa disso para o [ngClass] e [hidden] funcionarem
  templateUrl: './login-cliente.html',
  styleUrls: ['./login-cliente.css']
})
export class LoginComponent {

  // --- Lógica para alternar Abas ---
  // Esta variável controla qual aba está ativa.
  // O HTML lê ela para saber o que mostrar.
  abaAtual: 'login' | 'register' = 'login';

  // --- Lógica para Senha do Login ---
  loginPasswordType: string = 'password';
  loginPasswordIcon: 'open' | 'closed' = 'open';

  // --- Lógica para Senha do Registro ---
  regPasswordType: string = 'password';
  regPasswordIcon: 'open' | 'closed' = 'open';

  constructor() { }

  // Esta função é chamada pelo (click) no HTML do login
  toggleLoginPassword() {
    if (this.loginPasswordType === 'password') {
      this.loginPasswordType = 'text';
      this.loginPasswordIcon = 'closed';
    } else {
      this.loginPasswordType = 'password';
      this.loginPasswordIcon = 'open';
    }
  }

  // Esta função é chamada pelo (click) no HTML do registro
  toggleRegPassword() {
    if (this.regPasswordType === 'password') {
      this.regPasswordType = 'text';
      this.regPasswordIcon = 'closed';
    } else {
      this.regPasswordType = 'password';
      this.regPasswordIcon = 'open';
    }
  }

  // Esta função impede o form de recarregar a página
  onFormSubmit(event: Event) {
    event.preventDefault(); // Impede o recarregamento
    
    if (this.abaAtual === 'login') {
      console.log('Formulário de Login enviado!');
      // Aqui você colocaria a lógica de login real
    } else {
      console.log('Formulário de Registro enviado!');
      // Aqui você colocaria a lógica de registro real
    }
  }

}