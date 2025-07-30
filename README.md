# FORUM-API

## ✨ Sobre o projeto

Esta é uma API desenvolvida com Spring Boot que simula o backend de um fórum. O objetivo é permitir que usuários se autentiquem e possam criar, listar, atualizar e excluir tópicos de discussão.  

O projeto utiliza autenticação via JWT, senhas criptografadas com `BCrypt`, e persistência de dados em um banco de dados MySQL.

Foi desenvolvido com foco em aprendizado e boas práticas no desenvolvimento de APIs seguras com Java.

---

## ✅ Funcionalidades

- 🔐 Login com autenticação JWT (`/login`)
- 📝 Cadastro de tópicos
- 📋 Listagem de tópicos
- ✏️ Atualização de tópicos
- ❌ Exclusão de tópicos
- 🛡️ Proteção de rotas autenticadas via token
- 🔑 Criptografia de senhas com BCrypt

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- MySQL rodando localmente (porta `3306`)
- Uma IDE como IntelliJ ou VS Code

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/DeboraESNeves/FORUM-API.git
   cd FORUM-API
   ```

2. **Configure o banco de dados em `src/main/resources/application.properties`**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/frm
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha

   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

   api.security.token.secret=${JWT_SECRET:123456}
   ```

3. **Execute o projeto**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📡 Como usar a API

### 🔐 Login (para gerar token JWT)
**Requisição:**
```http
POST /login
Content-Type: application/json
```

**Corpo (JSON):**
```json
{
  "login": "usuario@email.com",
  "senha": "123456"
}
```

**Resposta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 📋 Listar tópicos
**Requisição:**
```http
GET /topicos
Authorization: Bearer SEU_TOKEN_JWT
```

---

### 📝 Criar tópico
**Requisição:**
```http
POST /topicos
Authorization: Bearer SEU_TOKEN_JWT
Content-Type: application/json
```

**Corpo (JSON):**
```json
{
  "titulo": "Dúvida sobre Spring Boot",
  "mensagem": "Como funciona a injeção de dependência?",
  "autor": "Debora Neves",
  "curso": "Spring Framework"
}
```

---

### 💻 Exemplos com cURL

**Login e obtenção do token:**
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"login":"usuario@email.com","senha":"123456"}'
```

**Listar tópicos:**
```bash
curl -X GET http://localhost:8080/topicos \
  -H "Authorization: Bearer SEU_TOKEN_JWT"
```

---

## ❓ Precisa de ajuda?

- Consulte a [documentação oficial do Spring Boot](https://spring.io/projects/spring-boot)
- Entenda mais sobre tokens JWT em [jwt.io](https://jwt.io)
- Ou abra uma **issue** neste repositório e vamos conversar!

---

## 👩‍💻 Autora

Este projeto foi criado por **Debora Neves**.  
Sinta-se à vontade para contribuir abrindo *pull requests*, relatando bugs ou trocando ideias! 😄

🔗 Repositório: [https://github.com/DeboraESNeves/FORUM-API](https://github.com/DeboraESNeves/FORUM-API)


   
