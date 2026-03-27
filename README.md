#  QA Automação-Selenium+Java

Automação de testes end-to-end simulando fluxo real de e-commerce (login → produtos → carrinho).

---

##  Objetivo do projeto

- Demonstrar na prática:
- Estrutura com Page Object Model
- Testes independentes e legíveis
- Automação próxima de um cenário real 
- Cobertura de diferentes perfis de usuários

---

##  Tecnologia

- [Java 17](https://www.oracle.com/java/)
- [Gradle](https://gradle.org/)
- [JUnit 5](https://junit.org/junit5/)
- [Selenium 4](https://www.selenium.dev/documentation/webdriver/troubleshooting/upgrade_to_selenium_4/)

---
## Perfis de usuários testados

- **standard_user** → fluxo normal
- **problem_user** → falhas em remoção de produto e inconsistências no carrinho
- **error_user** → campos bugados no checkout (não inputa corretamente ou sobrescreve valores)
- **visual_user** → preços incorretos exibidos
- **performance_glitch_user** → lentidão no fluxo

---

##  Fluxos cobertos

- Login (sucesso e falha)
- Validação de navegação
- Adição e remoção de produtos
- Validação de preços
- Checkout (campos obrigatórios e inválidos)
- Finalização da compra

---

##  Estrutura:
- src
- ├─ main/java
- │   ├─ core    # Driver, BasePage, BaseTest
- │   └─ pages   # Page Objects
- │
- └─ test/java
-     └─ tests   # Testes automatizados
---

## ▶️ Como rodar

### 1. Clonar o repositório
```bash
git clone https://github.com/seu-usuario/qa-web-automation-selenium-java.git
cd qa-web-automation-selenium-java

2. Instalar dependências

Certifique-se de ter Java 17 e Gradle instalados.Para verificar:

java -version
gradle -v

3. Rodar todos os testes

./gradlew test

4. Rodar testes específicos

./gradlew test --tests "tests.CheckoutTest"
./gradlew test --tests "tests.FinishCartTest.t03_deveFinalizarCompraUsuariosValidos"

```
---
### Autora

- Mirian Evangelista de Lima
- QA Analyst