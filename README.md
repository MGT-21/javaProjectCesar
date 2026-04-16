# ArenaPE API

API REST para gerenciamento de eventos, com autenticação JWT e controle de acesso por roles (ADMIN/USER).

## Como rodar

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

---

## Swagger

Acesse a documentação interativa em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Usuário admin padrão

Criado automaticamente ao subir a aplicação:

| Campo | Valor |
|-------|-------|
| E-mail | admin@arenape.com |
| Senha | admin123 |

---

## Como autenticar no Swagger

1. Faça login em `POST /auth/login` com as credenciais acima
2. Copie o `token` retornado no response
3. Clique em **Authorize** 🔒 no topo do Swagger
4. Digite `Bearer <seu token>` e confirme
5. As rotas protegidas agora estão liberadas

---

## Rotas

### Auth — público

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/auth/register` | Cria um novo usuário (role USER) |
| POST | `/auth/login` | Retorna o token JWT |

#### Body — register
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "123456"
}
```

#### Body — login
```json
{
  "email": "joao@email.com",
  "password": "123456"
}
```

#### Response — login
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "name": "João Silva",
  "role": "USER"
}
```

---

### Events — leitura pública

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/events` | Lista todos os eventos |
| GET | `/events/{id}` | Busca evento por ID |

---

### Events — requer ADMIN 🔒

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/events` | Cria um novo evento |
| PUT | `/events/{id}` | Edita um evento |
| DELETE | `/events/{id}` | Remove um evento |

#### Body — criar/editar evento
```json
{
  "name": "Show de Rock",
  "location": "Recife, PE",
  "description": "Uma noite incrível",
  "imageUrl": "https://exemplo.com/imagem.jpg",
  "price": 50.00,
  "availableTickets": 200,
  "eventDate": "2026-12-31T21:00"
}
```

---

## Regras de negócio

- Não é possível criar evento com data no passado
- Não é possível editar um evento com status `CANCELLED`
- Não é possível excluir um evento com status `CONFIRMED`
- O registro público sempre cria usuários com role `USER`
- O token JWT expira em 24 horas