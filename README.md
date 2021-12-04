# BlueBank  API by BugBusters

## Sobre o Projeto

Este projeto consiste em uma API de banco digital desenvolvida para o bootcamp Gama Pan Academy, uma parceria da Gama como o Banco Pan.

A API foi construída em Java utilizando a framework Spring Boot com Maven e suas dependências para a modelagem do problema. Foi utilizado banco de dados relacional MySQL, no qual as tabelas e  relações são criadas pela aplicação em si, usando as notações fornecidas pelo Spring Boot. A aplicação foi subida para a AWS utilizando EC2. Uma função lambda foi criada para realizar o rendimento da conta dos clientes periódicamente. A organização dos diretórios e endpoints são listados mais a frente.

O quadro Kanban do projeto é encontrado no [Trello](https://trello.com/invite/b/Xe8WKBZ4/82ca1738ea2e196b0192582c6951c74d/quadro-kanban-bugbusters). A aplicação está disponível no [github](https://github.com/TML45/Projeto_Final) e na plataforma AWS. Uma esteira em jenkins foi usada para realizar deploy automatizado do repositório no github para a nuvem.

### Organização do Diretório do Projeto

```
├── Projeto_Final                                                        // Diretório Principal
    ├── src                                                              // Diretório com os códigos fontes
        ├── main
        	└── java/com/bugsbuster/projectCaptainTech/                  // Diretório com os arquivos java
        	    ├── config                                               // Pasta de arquivos de configurações
​				    └── AwsSnsConfig.java                                // Configuração do Bean para executar o SNS da amazon
​			    ├── controller                                           // Contém as classes de controle da aplicação (endpoints)
​        		    ├── ContaController.java                             // Endpoints para contas do banco
​				    ├── PessoaFisicaController.java                      // Endpoints para clientes pessoa física
​                   ├── PessoaJuridicaController.java                    // Endpoints para clientes pessoa jurídica
​                   └── TransferenciaController.java                     // Endpoints para transferência
​        	    ├── doc                                                  // Pasta de documentação do swagger
​			        └── SwaggerConfig.java                               // Configurações para gerar a documentação do swagger
​			    ├── model                                                // Contém as classes de modelagem do sistema
​        		    ├── Cliente.java                                     // Classe super abstrada para os tipos de cliente
​                   ├── Conta.java                                       // Classe que modela uma conta
​                   ├── Endereco.java                                    // Classe que modela os endereços dos clientes
​                   ├── EnumEstado.java                                  // Enumera as siglas dos estados para usar no endereço
​                   ├── PessoaFisica.java                                // Classe especialização de cliente para pessoa física
​                   ├── PessoaJuridica.java                              // Classe especialização de cliente para pessoa jurídica
​                   └── Transferencia.java                               // Classe que modela as transferências entre contas
​        	    ├── repository                                           // Diretório que contem as classes de conexão com o MySQL
​        		    ├── ContaRepository.java                             // Repositório das contas
​                   ├── EnderecoRepository.java                          // Repositório dos Endereços
​                   ├── PessoaFisicaRepository.java                      // Repositório de pessoa Física
​                   ├── PessoaJuridicaRepository.java                    // Repositório de pessoa jurídica
​                   └── TransferenciaRepository.java                     // Repositórios das transferências
​        		├── service                                              // Implementações de comportamento em meio a solicitações
                    ├── exception                                        // Pacote de tratamento de exceções customizados
                        ├── AppExceptionHandler.java
                        └── EntityNotFoundException.java
​        		    ├── ContaService.Java                                // Comportamento das solicitações de conta
​				    ├── EnderecoService.java                             // Comportamento das solicitações de Endereço
​				    ├── InterfacePessoaFisica.java                       // Interface de omportamento das solicitações de pessoa física
​                   ├── InterfaceTransferenciaService.java               // Interface de comportamento de transferência
​                   ├── PessoaFisicaServiceImpl.java                     // Comportamento das solicitações de pessoa física
​                   ├── PessoaJuridicaServiceImpl.java                   // Comportamento das solicitações de pessoa jurídica
​                   └── TransferenciaService.java                        // Comportamento das solicitações de Transferência
                └── ProjectCaptainTechApplicationTests.java              // Classe que contem a main
​        	└── resources
​        			└── application.properties                           // Contém as configurações de BD da aplicação
​       └── test/java/com/bugsbuster/projectCap                          // Caminho para códigos de teste
​        	└── ProjectCaptainTechApplicationTests.java         	     // teste do classe principal
​   ├── target
​   ├── .gitattributes
​   ├── BlueBank-Diagram.asta
​   ├── BlueBank-mysql.mwb
​   ├── DB.sql                                                           // Script para popular o banco de dados
​	├── READ.md                                                          // ---> Você está aqui
​   ├── mvnw
​   ├── mvnw.cmd
​   └── pom.xml                                                          // Arquivo pom com as configurações do Maven

 ```

### Diagrama de Banco de Dados

### Diagrama de Classes

### Endpoints da Aplicação

* Os endpoints da aplicação são listados pela documentação do swagger que é gerada e se encontra no projeto.

## **Grupo de Desenvolvedores BugBusters:**

| <a href="https://www.linkedin.com/in/brunoclaudino/" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4D03AQHkHiqXw0XaTQ/profile-displayphoto-shrink_800_800/0/1572375836252?e=1640217600&v=beta&t=qFbgqvzb7j4XWUMK7njC4cHtLvWifbdDXOgAPE-x1EA" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/charllyson-souza-248576108/" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4E03AQH9Nv9sPVRdag/profile-displayphoto-shrink_200_200/0/1637415485830?e=1643241600&v=beta&t=HFJ6rtiQsLOn8Tsi16HDkBlr6dS4iQz0evx6X_9Sf6o" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/paulo-queiroz-7048b1a0" target="blank"><img style="background-color: #abc" align="center" src="https://ca.slack-edge.com/T02FTTBGALF-U02GFCYJPBN-9868142ab62f-512" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/paulo-queiroz-7048b1a0" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4D03AQFEoP9EyJQb8A/profile-displayphoto-shrink_800_800/0/1629280866731?e=1643241600&v=beta&t=YqOTfjreEJd2fyAtW2OExNGcd7H3f2iO58sKjyBvKcA" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/tassio-linhares-6b3b07226/"><img style="background-color: #abc" align="center" src="https://ca.slack-edge.com/T02FTTBGALF-U02GGE4376Y-9e8d00e36951-512" height="200" width="200" /></a> |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                    Bruno Claudino Matias                     |                       Charllyson Souza                       |                        Paulo Queiroz                         |                        Jader Greiner                         |                       Tassio Linhares                        |
|TechLead                                                              |  Eng. Dados                                                            |                                                     Developer         |           Developer                                                   |           Eng. Cloud                                                   |

**Obs: ** Clique nas imagens para os respectivos LinkedIns.

