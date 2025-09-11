# Sistema de Gestão de Contratos – API REST

Este projeto é uma **API REST para gestão de contratos**, desenvolvida com **Spring Boot**, **Spring Security** e **JPA**, projetada para ser **robusta, escalável e segura**.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL
- Gradle
- Jakarta Validation
- JUnit 5 (Testes Automatizados)

---

## 🏗️ Arquitetura e Padrões de Projeto

O sistema segue boas práticas de arquitetura e aplica diversos **padrões de projeto**, garantindo manutenção, flexibilidade e escalabilidade:

- **Singleton:** Serviços e repositórios do Spring possuem instâncias únicas, otimizando recursos e consistência.
- **Facade:** Controllers (`CompaniesController`, `ContractsController`, `AuthController`) oferecem interfaces simplificadas para subsistemas complexos.
- **Strategy:** Interface `Payable` permite que diferentes tipos de contrato (`ContractOfServices` e `ContractOfGoodsAndMaterials`) definam sua própria lógica de pagamento.
- **Template Method:** Classe abstrata `Contract` define o esqueleto do contrato, delegando detalhes específicos às subclasses.
- **Factory Method:** `ContractDto.mapToContract` cria dinamicamente contratos conforme o tipo.
- **Observer & Decorator:** Filtros de Spring Security (`JwtAuthenticationFilter`) aplicam autenticação e autorização de forma desacoplada e extensível.

---

## ⚡ Funcionalidades

- Cadastro, consulta, atualização e exclusão de **empresas** e **contratos**
- Registro de usuários e autenticação via JWT
- Visualização de contratos e próximos pagamentos
- Segurança baseada em Spring Security e filtros JWT
- **Testes automatizados** com JUnit 5 cobrindo controllers e lógica de contratos
  
---

## 🧪 Testes

O projeto inclui testes automatizados que verificam:

- CRUD de empresas e contratos
- Lógica de pagamento (`Payable`) para diferentes tipos de contrato
- Mapeamento de DTOs para modelos
- Integridade dos dados após operações de criação, atualização e exclusão

Os testes garantem **qualidade, confiabilidade e facilidade de manutenção** do sistema.

---

## 📦 Estrutura do Projeto
back-contracts/
├─ src/main/java/br/edu/ifal/contracts
│ ├─ controllers
│ ├─ dtos
│ ├─ filters
│ ├─ models
│ ├─ repositories
│ ├─ security/config
│ ├─ services
│ └─ views
├─ src/test/java/br/edu/ifal/contracts
│ ├─ controllers
│ ├─ models
│ └─ views
└─ resources
  └─ application.properties

