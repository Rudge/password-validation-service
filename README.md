# password-validation-service

Aplicação de exemplo da solução do desafio do ITI.

Irei sumarizar a estrutura da aplicação aqui e listar os principais motivos pela decisão.

## Estrutura de validação

Para iniciar um projeto, sempre penso começar com a estratégia de TDD, às vezes tenho dificuldade, mas nesse caso bem definido de escopo ficou mais fácil.

Primeiramente, desenvolvi os testes e a camada de serviço para que pudesse decidir o melhor design da aplicação para o contexto de SOLID, onde em uma possível evolução das regras de validação de senha,
houvessem um impacto menor na estrutura já criada e as regras pudessem ser injetadas no contexto da validação.

Portanto, criei a estrutura de interface `PasswordRule`, onde contém a definição de contrato que novas regras devem implementar e após isso criei cada regra em seu escopo de classe.

Nesse momento não me preocupei tanto com a performance, mais com a diminuição do acoplamento e buscando mais coesão no código.

## Frameworks

Como já estou mais adaptado com o Kotlin, usei como linguagem e usei o framework web que já estou mais familiarizado para API, ele é um framework mais lightweight o [Javalin](https://javalin.io/).

Para a injeção de dependência também utilizei um framework mais conhecido e lightweight [Koin](https://start.insert-koin.io/#/)

Para os testes unitários temos o [Junit 5](https://junit.org/junit5/) e o [Mockk](https://mockk.io/) que é um mock bem poderoso para Kotlin.

Para os testes de componente, usei o [RestAssured](http://rest-assured.io/) para realizar as requisições HTTP e realizar as validações já usando o próprio DSL.

## Build

Para executar o build é simples usando o gradle wraper.

``./gradlew build``

Após executar o build, que já executa os testes, podemos encontrar os relatórios dos testes e cobertura nos diretórios abaixo:
 
 Teste unitáro - build/reports/tests/test/index.html 
 
 Teste de componente - build/reports/tests/componentTest/index.html
  
 Jacoco - build/jacoco/index.html 

## RUN

``./gradlew run``

Pode executar os CURLs.

``curl -v http://localhost:7000/password/validate --data '{"value":"AAAbbbCc"}'``

``curl -v http://localhost:7000/password/validate --data '{"value":"AbTp9!foo"}'``

`` curl -v http://localhost:7000/password/validate --data '{"value":"AAAbbbCc"}' -H 'Content-Type:application/vnd.password_details+json'``