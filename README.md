# password-validation-service

Aplicação de exemplo de solução do desafio do ITI.

Irei sumarizar a estrutura da aplicação aqui e listar os principais motivos pela decisão.

## Estrutura de validação

Para iniciar um projeto, sempre penso começar com a estratégia de TDD, às vezes tenho dificuldade, mas nesse caso bem definido de escopo ficou mais fácil.

Primeiramente, desenvolvi os testes e a camada de serviço para que pudesse decidir o melhor design da aplicação para o contexto de SOLID, onde em uma possível evolução das regras de validação de senha,
houvessem um impacto menor na estrutura já criada e as regras pudessem ser injetadas no contexto da validação.

Portanto, criei a estrutura de interface `PasswordRule`, onde contém a definição de contrato que novas regras devem implementar e após isso criei cada regra em seu escopo de classe.

Nesse momento não me preocupei tanto com a performance, mais com a diminuição do acoplamento e buscando mais coesão no código.

