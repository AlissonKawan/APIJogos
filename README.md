# 🎮 API Jogos - RAWG + Quarkus

API REST desenvolvida com Quarkus que consome a API externa RAWG para buscar jogos e também permite salvar jogos em banco de dados.

---

# 🚀 Tecnologias utilizadas

- Java 17+
- Quarkus
- Hibernate ORM Panache
- PostgreSQL
- REST Client (MicroProfile)
- JPA
- Maven

---

# 📌 Funcionalidades

✅ Buscar jogos na API RAWG  
✅ Listar jogos encontrados  
✅ Salvar jogos no banco  
✅ Listar jogos salvos  
✅ Buscar jogo salvo por ID  
✅ Atualizar jogo salvo  
✅ Deletar jogo salvo

---

# 📂 Estrutura do projeto

```text
src/main/java/br/com/alisson
│
├── client        -> comunicação com API externa RAWG
├── dto           -> objetos de transferência de dados
├── entity        -> entidade do banco de dados
├── repository    -> acesso ao banco
├── resource      -> endpoints da API
└── service       -> regras de negócio