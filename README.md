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
### **2.4.4 – Notificação de Queda de Preço Personalizada**
- Cliente marca um produto e recebe alerta automático quando o preço cair.

**Como funciona?**
- O cliente clica em “Acompanhar preço” no produto desejado.
- O sistema monitora alterações de valor e envia notificação quando o preço for reduzido.
- As notificações podem ser enviadas por e-mail, SMS ou dentro do aplicativo.

**Benefícios:**
- Evita que o cliente perca promoções.
---
### **2.4.5 – Compra Compartilhada**
- Permite que amigos adicionem produtos juntos em um mesmo carrinho.

**Como funciona?**
- Um cliente cria um **carrinho compartilhado** e diferente de seu carrinho pessoal que gera um link único.  
- Esse link é enviado para amigos ou familiares.  
- Cada participante:
  - Pode adicionar seus próprios produtos.  
  - Define o método de pagamento individual.
  - Visualiza em tempo real o que os outros adicionaram.  
- O pedido só é confirmado quando **todos os participantes finalizam o pagamento**.  
- As notificações podem ser enviadas por e-mail, SMS ou dentro do aplicativo.

**Formas de pagamento aceitas:**  
- Cartão de crédito ou débito (individual por participante).  
- PIX (cada um com seu QR Code exclusivo).  

**Benefícios:**
- Facilita compras coletivas (ex: presentes, eventos).
- Estimula engajamento social entre usuários da plataforma.
---

### **2.4.6 – Modo “Comprar Novamente” Rápido**
- Um botão que refaz o último pedido com apenas um clique.

**Como funciona?**
- O sistema salva automaticamente o histórico de pedidos concluídos.
- Ao clicar em “Comprar novamente”, todos os itens anteriores são adicionados ao carrinho.
- O cliente pode revisar e confirmar em segundos.

**Benefícios:**
- Agiliza compras recorrentes.
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
### **2.5.4 – Avaliação de Desempenho e Histórico de Entregas**
- Estatísticas completas de entregas realizadas, atrasos e avaliações.

**Como funciona?**
- O sistema coleta dados das entregas finalizadas e gera métricas automáticas.  
- Os indicadores vêm de três fontes:
  - **Clientes:** avaliam pontualidade e cuidado com o pacote.  
  - **Distribuidora:** avalia cumprimento de prazos e rotas.  
  - **Sistema:** calcula tempo médio, desvios e taxa de sucesso.
 
**O que aparece no painel?**  
- Total de entregas realizadas (diário, semanal, mensal).  
- Entregas atrasadas (% e número absoluto).  
- Avaliação média (nota de 1 a 5 estrelas).

**Filtros disponíveis:**  
- Por período (dia, semana, mês, personalizado).  
- Por tipo de entrega (expressa, agendada, padrão).  

**Benefícios:**
- Incentiva produtividade.
- Permite acompanhar evolução e identificar melhorias.
---
### **2.5.5 – Relato Rápido de Problemas**
- Botão “Relatar problema” envia automaticamente foto e localização alem de um formulário.

**Como funciona?**
- O entregador acessa o botão “Relatar problema” dentro do pedido.  
- Abre-se um formulário simplificado, contendo:
  - Seleção do tipo de problema (cliente ausente, endereço incorreto, trânsito, avaria, etc.).  
  - Campo opcional para observação.  
  - Upload de foto (ex: fachada da casa, produto danificado).  
  - Localização automática via GPS.  
- O sistema envia automaticamente o relatório para o suporte e registra o status da entrega como **“em análise”**. 
- O cliente pode contestar após 3 dias

**Benefícios:**
- Reduz falhas e melhora a comunicação.
---
### **2.5.6 – Visualização de Rotas Otimizadas**
- Mapa com o trajeto ideal e informações detalhadas de entrega.

**Como funciona?**
- O sistema calcula o percurso mais rápido considerando distância e trânsito.
- Exibe pontos de parada, endereços e tempo estimado de entrega.
- Atualiza automaticamente o status conforme as entregas são concluídas.

**Benefícios:**
- Reduz custos operacionais e tempo de entrega.
---

## 2.6 – Recursos para GERENTES

### 2.6.1 – Dashboard Básico
- Mostra pedidos por dia, atrasos, clientes e vendedores ativos.

### 2.6.2 – Modo de Intervenção
- Gerente pode assumir um chat entre cliente/vendedor e resolver diretamente.

### 2.6.3 – Alerta de Problemas
- Se uma loja tem muitas reclamações, ou um entregador com atrasos, o gerente recebe aviso.

---
### **2.6.4 – Mapa de Calor de Vendas**
- Mostra visualmente as regiões com maior volume de pedidos.

**Como funciona?**
- O mapa utiliza cores para representar intensidade de vendas por região.
- Permite filtragem por data, categoria de produto e loja.

**Benefícios:**
- Identifica áreas estratégicas e oportunidades de expansão.
---
### **2.6.5 – Simulador de Lucro**
- Mostra o impacto estimado nos lucros antes de aplicar descontos.

**Como funciona?**
- O gerente informa o percentual de desconto ou promoção.
- O sistema calcula a nova margem de lucro automaticamente.
- Exibe comparativo entre lucro atual e projetado.

### **2.6.6 – Alerta de Vendedores Inativos**
- Notifica automaticamente quando um vendedor ultrapassa o limite de dias sem atividade.

**Como funciona?**
- O sistema monitora eventos de atividade do vendedor (login, atualização de produtos, mensagens, vendas, etc.).  
- Se nenhuma ação for registrada por 15 dias consecutivos, o sistema envia um alerta:  
  - Primeiro alerta via painel administrativo.  
  - Segundo alerta por e-mail, após 30 dias.  
- Caso o vendedor complete 45 dias sem atividade, o sistema pode:
  - Pausar automaticamente os anúncios e divulgações. 

**Benefícios:**
- Mantém a plataforma ativa e saudável.

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
### **2.7.4 – Relatórios de Atendimento**
- Acompanha métricas de desempenho do suporte e satisfação dos clientes.

**Como funciona?**
- O sistema registra tempo médio de resposta, taxa de resolução e avaliações dos usuários.
- Os dados são exibidos em gráficos e relatórios exportáveis.

**Benefícios:**
- Melhora o controle de qualidade.
---
### **2.7.5 – Auto-Sugestão de Solução**
- Sugere respostas automáticas com base em reclamações anteriores.

**Como funciona?**
- Ao digitar uma reclamação, o sistema identifica casos semelhantes.
- Exibe sugestões de respostas e ações baseadas em soluções anteriores.
- O atendente pode editar e enviar rapidamente.

**Benefícios:**
- Reduz tempo de atendimento.
---
### **2.7.6 – Chat Pré-Configurado por Tipo de Problema**
- Cliente escolhe o motivo e o suporte recebe formulário filtrado.

**Como funciona?**
- O cliente seleciona o tipo de problema (ex: entrega, pagamento, produto).
- O sistema direciona o atendimento para o setor correto com dados já filtrados.
- O suporte recebe as informações organizadas.

**Benefícios:**
- Reduz tempo de triagem.
- Garante atendimento mais rápido e assertivo.

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
---
### **2.8.4 – Comparador de Preços Concorrentes**
- Mostra o preço médio de produtos semelhantes em outras lojas da plataforma.

**Como funciona?**
- O vendedor acessa o painel e visualiza o preço médio de produtos equivalentes.
- Pode ajustar seu preço em tempo real com base nos concorrentes.

**Benefícios:**
- Mantém a loja competitiva.
---
### **2.8.5 – Estoque Inteligente**
- Sugere quais produtos repor com base no histórico de vendas.

**Como funciona?**
- O sistema analisa o histórico de vendas e a velocidade de retirada dos produtos.
- Recomenda reposições automáticas e alerta sobre baixos estoques.

**Benefícios:**
- Evita falta de produtos.
- Melhora planejamento de compras e reposições.
---
### **2.8.6 – Análise de Desistências**
- Informa quantas vezes o produto foi visto ou adicionado ao carrinho sem compra.

**Como funciona?**
- O sistema registra visualizações e abandonos de carrinho por produto.
- Exibe relatórios com taxa de desistência e possíveis causas (preço, frete, prazo).

**Benefícios:**
- Ajuda o vendedor a otimizar preços e descrições.
- Reduz abandono de carrinho e melhora conversão.
---

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
