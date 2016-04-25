# language: pt

Funcionalidade: Eu enquanto usuario desejo me cadastrar no site para poder utiliza-lo
Cenário: Cadastrar no site  informando nome, endereco e data de nascimento
Dado que o usuario criou um novo cadastro
E que o usuario informou o nome "Joaquim José da Silva Xavier"
E que o usuario informou o endereco "Rua da Forca, No 123"
E que o usuario informou a data de nascimento "12/11/1746"
Quando verificar se o usuario pode ser cadastrado
Entao recebera a mensagem true

Cenário: Cadastrar no site não informando o nome e recebendo que o cadastro não pode ser realizado
Dado que o usuario criou um novo cadastro
E que o usuario nao informou o nome
E que o usuario informou o endereco "Rua da Forca, No 123"
E que o usuario informou a data de nascimento "12/11/1746"
Quando verificar se o usuario pode ser cadastrado
Entao recebera booleano false
