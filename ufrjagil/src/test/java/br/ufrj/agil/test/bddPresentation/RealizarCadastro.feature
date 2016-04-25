# language: pt

Funcionalidade: Eu enquanto usuario desejo me cadastrar no site para poder utiliza-lo
Cenário: Cadastrar no site  informando nome, endereco e data de nascimento
Dado que o usuario criou um novo cadastro
E que o usuario informou o nome "Joaquim José da Silva Xavier"
E que o usuario informou o endereco "Rua da Forca, No 123"
E que o usuario informou a data de nascimento "12/11/1746"
Quando verificar se o usuario pode ser cadastrado
Entao receberá a mensagem "Usuu&#225;rio cadastrado com sucesso."

Cenário: Cadastrar no site não informando o nome e recebendo o erro.
Dado que o usuario criou um novo cadastro
E que o usuario nao informou o nome
E que o usuario informou o endereco "Rua da Forca, No 123"
E que o usuario informou a data de nascimento "12/11/1746"
Quando verificar se o usuario pode ser cadastrado
Entao receberá a mensagem "Nome do usu&#225;rio n&#227;o foi informado."

Cenário: Cadastrar no site não informando uma idade inferior a 18 anos
Dado que o usuario criou um novo cadastro
E que o usuario informou o nome "Joaquim José da Silva Xavier"
E que o usuario informou o endereco "Rua da Forca, No 123"
E que o usuario informou a data de nascimento menor do que 18 anos
Quando verificar se o usuario pode ser cadastrado
Entao receberá a mensagem "Usu&#225;rio n&#227;o tem idade m&#237;nima para cadastro."
