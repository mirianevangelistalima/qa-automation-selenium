# Plano de Teste de Automação - Sauce Demo
## 1. Objetivo

Validar os principais fluxos funcionais do sistema Sauce Demo através de testes automatizados utilizando Java + Selenium + JUnit + Page Object Model, garantindo estabilidade 
nas jornadas críticas de compra e identificando comportamentos específicos de usuários simulados.

## 2. Aplicação Testada
- Nome: Sauce Demo
- URL: https://www.saucedemo.com
- Tipo: E-commerce para prática de testes funcionais

## 3. Arquitetura do Projeto de Automação
- Framework Utilizado
- Java
- Selenium WebDriver
- JUnit 5
- Grandle
- Padrão Page Object Model (POM)

## 4. Estratégia de Testes

Tipos de Teste Automatizados

- Teste funcional
- Teste de regressão
- Teste de fluxo ponta a ponta (E2E)
- Teste baseado em perfis de usuário
- Validação negativa
- Navegação entre páginas

## 5. Usuários Utilizados
| Usuário                 | Objetivo               |
| ----------------------- | ---------------------- |
| standard_user           | fluxo normal           |
| problem_user            | validar bugs simulados |
| error_user              | erros inesperados      |
| visual_user             | falhas visuais/dados   |
| performance_glitch_user | lentidão               |
| locked_out_user         | bloqueado              |

## 6. Escopo de Testes
### Módulo 1 – Login
 
####  Cobertura
- Login com sucesso
- Usuário bloqueado
- Usuário inexistente
- Campos vazios
- Senha vazia
- Mensagens de erro corretas

#### Casos

| ID    | Cenário          | Resultado Esperado |
| ----- | ---------------- | ------------------ |
| LG001 | Login válido     | Acessar inventory  |
| LG002 | Locked user      | Mensagem bloqueado |
| LG003 | Usuário inválido | Mensagem erro      |
| LG004 | Username vazio   | Username required  |
| LG005 | Password vazio   | Password required  |

### Módulo 2 – Produtos

#### Cobertura

- Abrir menu lateral
- All Items
- About
- Logout
- Reset App State
- Ordenações
- Adicionar/remover produtos
- Múltiplos produtos

#### Casos

| ID    | Cenário                 |
| ----- | ----------------------- |
| PR001 | Ordenar A-Z             |
| PR002 | Ordenar preço crescente |
| PR003 | Adicionar item carrinho |
| PR004 | Remover item            |
| PR005 | Adicionar 5 produtos    |

### Módulo 3 – Carrinho

#### Cobertura

- Abrir carrinho
- Validar quantidade
- Remover item
- Continue Shopping
- Checkout

#### Casos

| ID    | Cenário          |
| ----- |------------------|
| CT001 | Entrar carrinho  |
| CT002 | Validar badge = 2 |
| CT003 | Remover item     |
| CT004 | Voltar produtos  |
| CT005 | Ir checkout      |

Módulo 4 – Checkout
Cobertura

- Fluxo até checkout
- Preenchimento de dados 
- Campos obrigatórios
- Last Name vazio
- Postal Code inválido
- Bugs do problem_user

#### Casos

| ID    | Cenário                   |
| ----- | ------------------------- |
| CH001 | Checkout válido           |
| CH002 | First Name vazio          |
| CH003 | Last Name vazio           |
| CH004 | CEP texto                 |
| CH005 | Campo bugado problem_user |

### Módulo 5 – Finalização da Compra
#### Cobertura

- Resumo final
- Finish
- Redirect inventory
- Comparação de preços
- Falhas por perfil de usuário

#### Casos

| ID    | Cenário                |
| ----- | ---------------------- |
| FN001 | Finalizar compra       |
| FN002 | Voltar home            |
| FN003 | Preço correto carrinho |
| FN004 | problem_user falha     |
| FN005 | error_user falha       |


## 7. Critérios de Aceite
- Os testes do standard_user devem ter 100% de taxa de sucesso.
- Os testes dos usuários problem, error e visual devem confirmar a falha (uso de assertNotEquals ou assertThrows).

