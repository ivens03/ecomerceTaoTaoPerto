# Projeto E-commerce TaoTaoPerto MBA Unifametro 

## 1. Contexto Acadêmico 🎓

Este projeto foi desenvolvido como requisito do **MBA em Desenvolvimento Full Stack com DevOps**. 

O curso é ministrado pelo respeitado professor **Alessandro Feitoza**.

### Objetivo Principal

O objetivo do projeto é demonstrar a proficiência e a aplicação prática de conceitos avançados de desenvolvimento de software, arquitetura moderna e práticas de automação (DevOps) adquiridas ao longo do MBA, culminando na criação de uma plataforma de E-commerce robusta e escalável.

---

## 2. Diferenciais Competitivos do E-commerce TaoTaoPerto 🌟

O E-commerce **TaoTaoPerto** se destaca no mercado por sua arquitetura focada na segurança de conteúdo e na maximização da **Experiência do Usuário (UX)**, utilizando mecanismos inteligentes para retenção e conversão.

### 2.1. Segmentação de Conteúdo e Conformidade 🔞

O projeto implementa um modelo de nichos duplos em uma única plataforma, garantindo a separação de públicos:

* **Catálogo Geral:** Venda de produtos em gerais.
* **Conteúdo Adulto:** Uma seção exclusiva e filtrada, com regras de negócio que garantem que o acesso seja liberado **somente** após a validação de idade, cumprindo padrões de conformidade.

### 2.2. Foco na Experiência do Usuário (UX) e Retenção

A plataforma utiliza algoritmos para "prender" o usuário e otimizar o processo de compra:

| Recurso | Descrição e Vantagem Competitiva |
| :--- | :--- |
| **Pesquisa Preditiva Mutável** 🔍 | A barra de pesquisa ajusta suas sugestões e a ordem dos resultados **automaticamente** de acordo com as preferências e o histórico de navegação do usuário. |
| **Cupons Inteligentes** 🏷️ | Cupons de desconto são disponibilizados e personalizados por **categorias** que o usuário demonstrou maior interesse, impulsionando a conversão. |
| **Checkout Otimizado** 🛒 | O design do carrinho e do fluxo de checkout é altamente visível e foi planejado para requerer o **mínimo de cliques** possível, garantindo uma compra rápida e sem atrito. |
| **Seguir Lojas** 🛍️| Permite que o usuário acompanhe suas lojas favoritas e receba alertas sobre novos produtos, promoções e coleções exclusivas|
| **Ranking de Vendedores** ⭐| Mostra uma pontuação de reputação calculada com base em avaliações de clientes, tempo de resposta e pontualidade nas entregas.|
| **Desafio Semanal** 🎯| Oferece missões como “Compre 2 itens na mesma loja” ou “Avalie um produto hoje”, que rendem pequenas recompensas ao serem concluídas.|
| **Wishlist** 📝 | A lista de desejos ( wishlist ) será um ambiente para o usuário "salvar" seus interesses em produtos, tendo a possibilidade de criar inúmeras listas com temas editáveis. |
| **Sistema de Promoções** | O sistema permitirá que o descontos sejam aplicados automaticamente em produtos ou categorias durante um período específico de tempo, sem que o cliente precise usar cupom. |
| **Cálculo de Frete Automático** | Um campo simples onde o usuário insere o CEP e então é mostrado qual o valor será cobrado antes de uma possível compra. |
| **Lootbox +18 🔞🎁** | Caixa surpresa exclusiva da seção adulta, comprada por um valor fixo (ex: R$19,90), contendo itens aleatórios como: lubrificantes, mini acessórios, camisinhas especiais, vales-desconto para produtos +18, frete grátis ou até brinquedos completos. Embalagem totalmente discreta e sem identificação do conteúdo, fortalecendo a privacidade e estimulando recompra pela curiosidade. |
| **Seus Sonhos Empacotados 💀** | Um espaço paralelo ao marketplace principal, com ofertas imprevisíveis e regras mutáveis. Produtos podem ter preços aleatórios, cupons de risco (podem aumentar ou reduzir o valor final) e promoções-relâmpago que somem em minutos. Mantém o usuário curioso e engajado. |
| **Avatar Progressivo 🧬** | Usuários e vendedores possuem um avatar personalizável que evolui conforme interagem no site. A cada compra, avaliação ou meta cumprida, o avatar ganha novos visuais, emblemas ou animações — criando uma identidade visual e um senso de progresso contínuo. |


### 2.3. Painel de Vendedor com Analytics Avançado 📈

Para capacitar os vendedores, a plataforma oferece ferramentas de gestão e análise de dados:

* **Integração de Gráficos:** Vendedores têm acesso a gráficos em tempo real de suas **Vendas, Usuários Alcançados e Visualizações** de produtos.
* **Impulsionamento:** Ferramenta para que o vendedor possa **impulsionar a visibilidade** de seus produtos dentro da plataforma, melhorando o engajamento e a performance.

## 2.4 – Recursos para CLIENTES

### **2.4.1 – Rastreamento com Mapa Simplificado**
- O cliente vê um ponto no mapa se movendo conforme o status do pedido.

**Como funciona?**  
- O ponto não representa o veículo em tempo real.  
- Ele muda de posição de acordo com o último status logístico recebido (ex: “A caminho do CD”, “Saiu para Entrega”).  
- A animação cria a sensação de movimento mesmo quando há apenas atualizações pontuais.

**Como seria o ponto?**  
- Um ícone visual simples: mini-caminhão ou caixa animada.  
- Opção de personalização por tema da plataforma.

**Onde ele veria?**  
- Na página de **Detalhes do Pedido**, sendo o primeiro elemento visual logo após o cabeçalho do pedido.

---

### **2.4.2 – Estimativa de Entrega pelo Histórico**
- Calcula o tempo médio de chegada baseado em pedidos anteriores.

**E se for cliente novo ou rota nova?**  
- A estimativa passa a usar o histórico geral de entregas na região do CEP.  
- Caso a loja seja nova, usa média de outras lojas próximas e histórico da transportadora da plataforma.  
- Quando o sistema acumular dados reais, a estimativa passa a ficar mais precisa automaticamente.

---

### **2.4.3 – Assinatura VIP**
- Cliente paga uma mensalidade para benefícios exclusivos.

**Como funciona?**   
- O usuário paga um valor mensal fixo.
- Em troca recebe:  
  ✅ Frete reduzido ou grátis em produtos selecionados  
  ✅ Cupons exclusivos  
  ✅ Suporte prioritário  
  ✅ Benefícios especiais em datas comemorativas

**Formas de pagamento**
- Cartão de crédito recorrente  
- Boleto com renovação mensal  
- Saldo interno da plataforma

---

## 2.5 – Recursos para ENTREGADORES

### 2.5.1 – Histórico de Ganhos
- Entregadores visualizam **quanto ganharam por dia**.

**Quais gráficos serão usados e para que servem?**  
- Gráfico de barras: **dia vs valor ganho**  
- Linha acumulando ganhos semanais/mensais  
- Objetivo: dar clareza sobre desempenho diário e incentivar produtividade.

### **2.5.2 – Registro de Gastos (Gasolina, manutenção, óleo, etc)**
- Entregador pode registrar os próprios gastos.

**Por qual motivo ele faria isso?**
- Ajuda a calcular o lucro real no mês.  
- Plataforma pode gerar relatórios que mostram se vale a pena pegar certas rotas ou horários.

**O relatório é exportável?**
- Sim, o sistema permite exportar em **PDF ou Excel**, permitindo controle contábil externo.

### 2.5.3 – Rotas Preferidas
- Ele escolhe regiões que quer ou não quer atender.
- Sistema prioriza pedidos naquelas áreas.

---

## 2.6 – Recursos para GERENTES

### 2.6.1 – Dashboard Básico
- Mostra pedidos por dia, atrasos, clientes e vendedores ativos.

### 2.6.2 – Modo de Intervenção
- Gerente pode assumir um chat entre cliente/vendedor e resolver diretamente.

### 2.6.3 – Alerta de Problemas
- Se uma loja tem muitas reclamações, ou um entregador com atrasos, o gerente recebe aviso.

---

## 2.7 – Recursos para SUPORTE

### **2.7.1 – Chatbot de Triagem Nível 1**
- Antes de chegar ao humano, um robô faz perguntas simples.

**Esse chat é com quem e quem responde?**
- Primeiro nível: chatbot automatizado.  
- Caso não resolva, encaminha para um atendente humano do setor de suporte.
- Futuramente podendo encaminhar para algum outro setor se necessário

### **2.7.2 – Histórico Unificado de Reclamações**
- O suporte pode visualizar histórico daquele cliente ou vendedor.

**Por que esse nível de acesso?**
- Para identificar abusos, golpes, clientes repetidamente insatisfeitos, problemas recorrentes com uma loja ou produto.
- Evita reembolsos indevidos e agiliza casos urgentes.

### **2.7.3 – Reembolso Simplificado**
- O suporte pode clicar em “aprovar” e gerar reembolso interno imediatamente.

**Como funciona o processo?**
1. Caso aprovado, o sistema devolve o valor como **crédito interno** na conta do cliente.
2. Crédito pode ser usado para nova compra.
3. Em casos especiais, é possível fazer reversão via cartão ou pix.

---

## 2.8 – Recursos para VENDEDORES

### 2.8.1 – Etiqueta de 'Vendido Pela Loja'
- Exibe nos produtos a informação de que são vendidos e enviados pela própria loja.

### **2.8.2 – Cupom Relâmpago**
- Cupom válido por tempo curto (ex: 1h ou 1 dia).

**Por que o vendedor tem cupom?**
- Para limpar estoque rápido, destacar produtos, atrair tráfego para a vitrine da loja.

**Onde o vendedor usa?**
- No painel da loja, criando cupons para produtos específicos ou toda a loja.

### **2.8.3 – “Por que este preço?” ❔**
- Ícone ao lado do preço do carrinho **explica para o cliente**:  
  - Preço do produto  
  - Frete  
  - Impostos  

**Objetivo:**  
- Criar transparência e empatia com o valor final, ajudando o cliente a entender de onde vem o preço.



## 3. Stack Tecnológica (Tecnologias Utilizadas) 🛠️

O projeto **TaoTaoPerto** foi construído utilizando uma arquitetura moderna e tecnologias de ponta, garantindo performance, escalabilidade e facilidade de manutenção.

### 3.1. Back-end e Lógica de Negócios (APIs)

| Camada | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Linguagem Principal** | **Java** | Escolhida pela sua robustez, performance e escalabilidade, ideal para a lógica transacional do E-commerce. |
| **Framework** | **Java com Spring Boot** | Utilizado para acelerar o desenvolvimento de APIs RESTful e micro-serviços, integrando segurança, injeção de dependência e gerenciamento de banco de dados. |

### 3.2. Front-end (Web)

| Camada | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Framework** | **Angular** | Escolhido para o desenvolvimento da interface web (Desktop e Mobile Web), proporcionando uma arquitetura modular, componentizada e de alta performance. |

### 3.3. Aplicação Mobile (Cross-Platform)

| Camada | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Linguagem/SDK** | **Dart** | Base para o desenvolvimento nativo multiplataforma. |
| **Framework** | **Flutter** | Utilizado para construir o aplicativo móvel com uma única base de código, garantindo interfaces ricas e desempenho nativo tanto para Android quanto para iOS. |

### 3.4. Banco de Dados e Persistência

| Camada | Tecnologia | Descrição |
| :--- | :--- | :--- |
| **Banco de Dados** | **PostgreSQL** | Escolhido como SGBD relacional robusto, open-source e focado em integridade de dados, ideal para armazenar informações cruciais do E-commerce (pedidos, usuários e catálogo de produtos). |

## 4. Equipe de Desenvolvimento 🧑‍💻

O projeto **TaoTaoPerto** foi concebido e implementado pelos seguintes alunos, como parte do trabalho de conclusão do MBA em Desenvolvimento Full Stack com DevOps:

| Membro da Equipe |  |
| :--- | :--- |
| **Ivens Magno Da Costa Lisboa** | Engenheiro de software |
| **Rodrigo Santos Cavalcante** | Engenheiro de software |
| **Luiz Eduardo De Paula Maia** | Engenheiro de software |
| **Rikelme Botelho Pessoa** | Engenheiro de software |
