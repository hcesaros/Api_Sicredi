# API Automation - Sicredi Challenge

Este projeto contém a automação de testes de API desenvolvida como parte do desafio técnico para QA na Sicredi.

---

## 📋 Informações do Projeto

Automação desenvolvida com o objetivo de validar a API DummyJSON utilizada como base para o desafio. O projeto cobre cenários positivos e negativos de autenticação, consulta de produtos e verificação de campos obrigatórios e aninhados na resposta.

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Maven**
- **RestAssured**
- **Cucumber (Gherkin)**
- **JUnit**
- **Allure Reports**

---

## 📁 Estrutura do Projeto

```
api-automation-sicredi/
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── steps/
│       │   └── utils/
│       └── resources/
│           ├── features/
│           └── cucumber.properties
│
├── pom.xml
└── README.md
```

---

## ▶️ Como Executar os Testes

### Clonar o repositório
```bash
git clone https://github.com/seu-usuario/api-automation-sicredi.git
cd api-automation-sicredi
```

### Executar via Maven
```bash
mvn test
```

### Gerar relatório Allure
```bash
mvn allure:serve
```

> **Nota:** Instale o Allure CLI com:
```bash
npm install -g allure-commandline --save-dev
```

---

## ✅ Plano de Testes & Estratégia

- **Abordagem:** Funcional e exploratória
- **Técnicas:** Particionamento de equivalência, valores limites, análise de campo obrigatório
- **Cobertura:**
  - Login com credenciais válidas e inválidas
  - Acesso autenticado e não autenticado
  - Retorno de todos os campos detalhados do endpoint `/products`
  - Consulta de produto por ID (válido e inválido)

---

## 🐞 Bugs Encontrados

- A API retorna `200 OK` para payloads vazios, sem validação robusta no login
- Erro genérico em caso de token inválido: mensagem não informativa

---

## 💡 Melhorias Sugeridas

- Incluir validação de campos obrigatórios no backend para autenticação
- Melhorar mensagens de erro para retorno 401/403
- Adicionar paginação no endpoint `/auth/products`
- Criar schema JSON para validação de contrato

---

## 👤 Autor

**Henrique Cesar**  
📧 henrique.teste@gmail.com  
💼 [LinkedIn](https://www.linkedin.com/in/henrique-cesaros/)  
📂 Projeto criado como parte de um desafio para a Sicredi

---

© 2025 Henrique Cesar - MIT License
