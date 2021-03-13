# beerstock-api
Projeto desenvolvido para estudo de testes unitários em Java Spring Boot

## Estrutura do Projeto

```
beerstock-api
└───.mvn         
└───src
    │   
    └───main
      └───java
        └───dev.wellington.beerstock
          └───config                            # Configuração do Projeto
          └───controller                        # Controllers do Projeto
          └───dto                               # DTOs do Projeto
          └───entity                            # Entidades/Classes/Model do projeto
          └───enums                             # Enums do Projeto
          └───exeception                        # Classes de excessões do projeto
          └───mapper                            # Classes de mapeamento
          └───repository                        # Repository
          └───service                           # Services
          └───BeerstockApplication.java         # Arquivo principal de execução
      └───resources
        └───application.properties              # Váriaveis de ambiente
    └───test
      └───java
        └───dev.wellington.beerstock
          └───builder                           # Builders/Factories do projeto
          └───controller                        # Arquivos de testes dos controllers
          └───service                           # Arquivos de testes dos services
          └───utils                             # Utils de testes
          └───BeerstockApplicationTests.java    # Teste do arquivo de execução do projeto
└───target
└───gitignore
└───beerstock.iml
└───LICENSE
└───mvnw
└───mvnw.cmd
└───pom.xml
└───README.md
```

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Para executar a suíte de testes desenvolvida durante a live coding, basta executar o seguinte comando:

```shell script
mvn clean test
```

Para visualizar a documentação do projeto através do Swegger deve-se chamar o seguinte end-point:

```shell script
http://localhost:8080/v2/api-docs
```
Os end-points para criação, deleção, update e consulta das cervejas tem como base:

```shell script
http://localhost:8080/api/v1/beers
```
