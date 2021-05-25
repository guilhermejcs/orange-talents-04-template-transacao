# Desafio Transação

## Tecnologias:

* Spring 2.5.0
* Open JDK 16
* Maven

## Setup do Projeto - Transação

### Tag: v00

### Objetivo

Sabemos que está ansioso(a) para começar a codificar, porém antes precisamos preparar nosso ambiente, portanto esse será nosso objetivo nessa tarefa.

### Descrição

------

Nessa tarefa precisamos criar um projeto para atender as funcionalidades da **Transação**, para tal, temos alguns pré requisitos de linguagem de programação e tecnologia, pois precisamos que esse projeto seja evoluído e mantido por anos, portanto é extremamente importante a escolha das mesmas.

Nosso mais experiência membro do time, sugeriu os seguintes itens:

Linguagem de programação

- Java 11

Tecnologia

- Spring Boot 2.3.*

Gerenciador de dependência

- Maven

### Resultado Esperado

Projeto gerado com as tecnologias sugeridas:

- Java 11
- Spring Boot
- Maven

------

## Consultar compras recentes

### Tag: v10

### Objetivo

Buscar as últimas transações do cartão de crédito.

### Necessidades

O portador do cartão deseja realizar uma consulta para obter as últimas compras do cartão de crédito.

### Restrições

Devemos criar uma API com as seguintes restrições:

- Identificador do cartão é obrigatório e deve ser informado na URL (path parameter).

### Resultado Esperado

- Retornar status code **200** com as últimas 10 compras (transações)
- Retornar status code **404** quando o cartão não for encontrado.

------

## Autenticação e Autorização

### Contexto

Construir APIs é uma tarefa bem divertida, porém as APIs quando mal implementadas expõe dados sensíveis do negócio em questão, permite que haja vazamento de dados dos nossos clientes e outras coisa que causam uma reação bastante ruim para o negócio em si.

Implementar mecanismos de segurança em nossas APIs são primordiais e devem ser pensados desde o primeiro dia do projeto, devemos incorporar segurança em nossos design de código e arquitetura.

### Objetivo

Proteger nossa aplicação de ataques externos, vazamentos de dados e ataques nocivos ao nosso negócio.

### Necessidades

Configurar mecanismo de segurança na aplicação de fatura. Vamos adotar o padrão de mercado chamado OAuth2.

### Restrições

- Não vamos realizar a manipulação de usuários, então não podemos criar nenhum usuário no sistema.
- Utilizar a claim JWT **scope** para proteger a aplicação.

### Resultado Esperado

- Nossa aplicação **transação** deve atuar como entidade **Resource Server**!
- Configuração do Spring Security na nossa aplicação com o módulo OAuth2 apontando para o nosso servidor de Autorização, nesse caso o Keycloak!
