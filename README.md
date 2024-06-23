# Sobre API para o aplicativo Voluntariei

## Endpoints

Os seguintes endpoints estão disponíveis na aplicação:

### Endpoints de Usuário

- `GET /user`: Obter todos os usuários
- `GET /user/{id}`: Obter um usuário pelo ID
- `POST /user`: Criar um novo usuário
- `PUT /user/{id}`: Atualizar um usuário existente
- `DELETE /user/{id}`: Deletar um usuário pelo ID
- `POST /user/login`: Login com email e senha

### Endpoints de Ong

- `GET /ongs`: Obter todas as ONGs
- `GET /ongs/{id}`: Obter uma ONG pelo ID
- `POST /ongs`: Criar uma nova ONG
- `PUT /ongs/{id}`: Atualizar uma ONG existente
- `DELETE /ongs/{id}`: Deletar uma ONG pelo ID
- `POST /ongs/login`: Login com email e senha

### Endpoints de Evento

- `GET /events`: Obter todos os eventos
- `GET /events/{id}`: Obter um evento pelo ID
- `POST /events`: Criar um novo evento
- `PUT /events/{id}`: Atualizar um evento existente
- `DELETE /events/{id}`: Deletar um evento pelo ID

### Endpoints de Help

- `GET /helps`: Obter todos os helps
- `GET /helps/{id}`: Obter um help pelo ID
- `POST /helps`: Criar um novo help
- `PUT /helps/{id}`: Atualizar um help existente
- `DELETE /helps/{id}`: Deletar um help pelo ID
