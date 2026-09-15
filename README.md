# Lab 2 — REST API e CRUD

Template de partida para o **Lab 2** de Distributed Systems 2026/27.

Vais construir uma API CRUD para produtos com validação, tratamento de erros como deve ser, e documentação OpenAPI. Não há base de dados neste lab — o armazenamento é um mapa em memória.

## A usar este template

Carrega em **Use this template → Create a new repository** no GitHub. Dá-lhe o nome
`lab-02-rest-api-aXXXXXXXX` com o teu número de aluno.
Não faças fork, e não clones este repositório diretamente — precisas do teu próprio histórico.

## O que está aqui

O scaffolding: o POM com todas as dependências de que o lab precisa, a estrutura de packages,
a configuração, e a preparação do container. Constrói e arranca tal como está.

## O que não está aqui

`Product`, `Identifiable`, `InMemoryRepository`, os DTOs, o mapper, o service, o controller e o exception handler. Todos os packages de que precisas existem e estão vazios.

Isso é deliberado. Um template é um ponto de partida, não a resposta.

## A correr

```bash
mvn spring-boot:run
# http://localhost:8081/swagger-ui.html
```

## Versões

Java 25, Spring Boot 4.1.0, Maven 3.9.16. Vê
[Toolchain e versões](https://github.com/DistributedSystems-Lusofona27/course-docs/blob/main/toolchain-and-versions.md)
se algo não resolver — a maior parte dos problemas de versões nesta cadeira vêm de seguir
um tutorial escrito para o Spring Boot 3.
