# BlueBank  API by BugBusters

![](https://github.com/TML45/Projeto_Final/blob/developer/BlueBank.png?raw=true)

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
​                        └── AwsSnsConfig.java                                // Configuração do Bean para executar o SNS da amazon
​                    ├── controller                                           // Contém as classes de controle da aplicação (endpoints)
​                        ├── ContaController.java                             // Endpoints para contas do banco
​                        ├── PessoaFisicaController.java                      // Endpoints para clientes pessoa física
​                        ├── PessoaJuridicaController.java                    // Endpoints para clientes pessoa jurídica
​                        └── TransferenciaController.java                     // Endpoints para transferência
​        	    ├── doc                                                  // Pasta de documentação do swagger
​                        └── SwaggerConfig.java                               // Configurações para gerar a documentação do swagger
​                    ├── model                                                // Contém as classes de modelagem do sistema
​                        ├── Cliente.java                                     // Classe super abstrada para os tipos de cliente
​                        ├── Conta.java                                       // Classe que modela uma conta
​                        ├── Endereco.java                                    // Classe que modela os endereços dos clientes
​                        ├── EnumEstado.java                                  // Enumera as siglas dos estados para usar no endereço
​                        ├── PessoaFisica.java                                // Classe especialização de cliente para pessoa física
​                        ├── PessoaJuridica.java                              // Classe especialização de cliente para pessoa jurídica
​                        └── Transferencia.java                               // Classe que modela as transferências entre contas
​        	    ├── repository                                           // Diretório que contem as classes de conexão com o MySQL
​                        ├── ContaRepository.java                             // Repositório das contas
​                        ├── EnderecoRepository.java                          // Repositório dos Endereços
​                        ├── PessoaFisicaRepository.java                      // Repositório de pessoa Física
​                        ├── PessoaJuridicaRepository.java                    // Repositório de pessoa jurídica
​                        └── TransferenciaRepository.java                     // Repositórios das transferências
​                    ├── service                                              // Implementações de comportamento em meio a solicitações
                         ├── exception                                        // Pacote de tratamento de exceções customizados
                             ├── AppExceptionHandler.java
                             └── EntityNotFoundException.java
                         ├── ContaService.Java                                // Comportamento das solicitações de conta
                         ├── EnderecoService.java                             // Comportamento das solicitações de Endereço
                         ├── PessoaFisicaServiceImpl.java                     // Comportamento das solicitações de pessoa física
                         ├── PessoaJuridicaServiceImpl.java                   // Comportamento das solicitações de pessoa jurídica
                         └── TransferenciaService.java                        // Comportamento das solicitações de Transferência
                     └── ProjectCaptainTechApplicationTests.java              // Classe que contem a main
            └── resources
                ├── application.properties                                    // Contém as configurações de BD da aplicação
        └── test/java/com/bugsbuster/projectCap                               // Caminho para códigos de teste
            └── ProjectCaptainTechApplicationTests.java         	          // teste do classe principal
   ├── target
   ├── .gitattributes
   ├── BlueBank-Diagram.asta
   ├── BlueBank-mysql.mwb
   ├── DB.sql                                                                // Script para popular o banco de dados
   ├── READ.md                                                               // ---> Você está aqui
   ├── mvnw
   ├── mvnw.cmd
   └── pom.xml                                                               // Arquivo pom com as configurações do Maven

```

### Diagrama de Banco de Dados

![](https://github.com/TML45/Projeto_Final/blob/developer/RelationalDiagram.png?raw=true)

### Diagrama de Classes

![](https://github.com/TML45/Projeto_Final/blob/developer/ClassDiagram.png?raw=true)

### Diagrama de Objeto

![](https://github.com/TML45/Projeto_Final/blob/developer/ObjectDiagram.png?raw=true)

### Endpoints da Aplicação

```
Contas
  ├── GET "/contas" -> Retorna todas as contas
  └── GET "/contas/{numero} -> Retorna uma conta a partir do número
Pessoa Física
  ├── GET "/clientePF" -> Retorna todos os clientes pessoa física
  ├── GET "/clientePF/{id}" -> Retorna um cliente PF pelo id
  ├── GET "/clientePF/cpf/{cpf}" -> Retorna um cliente PF pelo cpf
  ├── GET "/clientePF/nome/{nome}" -> Retorna clientes em que o nome contenha o que foi passado
  ├── GET "/clientePF/ocupacao/{ocupacao}" -> Retorna clientes PF por ocupação
  ├── GET "/clientePF/telefone/{telefone}" -> Retorna cliente pf pelo telefone
  ├── POST "/clientePF" -> Cria um cliente pessoa física passado por json
  ├── POST "/clientePF/mensagem" -> Envia uma mensagem  para os clientes subscritos no email
  ├── PUT "/clientePF/atualizar" -> Atualiza os dados de uma cliente pessoa física
  └── PUT "/clientePF/desativar/{id}" -> Desativa o cliente de id passado
Pessoa Jurídica
  ├── GET "/clientePJ" -> Retorna todos os clientes PJ
  ├── GET "/clientePJ/{id}" -> Retorna um cliente PJ pelo id
  ├── GET "/clientePJ/cnpj/{cnpj}" -> Retorna um cliente PJ pelo cnpj
  ├── GET "/clientePJ/inscricao/{inscricao}" -> Retorna cliente PJ pela inscrição estadual
  ├── GET "/clientePJ/nf/{nf}" -> Retorna clientes PJ em que o nome fantasia contenha a string passada
  ├── GET "/clientePJ/razao/{razao}" -> Retorna cliente PJ pela razão social
  ├── GET "/clientePJ/telefone/{telefone}" -> Retorna cliente PJ pelo número de telefone
  ├── POST "/clientePJ" -> Cria um cliente PJ passado por Json
  ├── POST "/clientePJ/mensagem" -> Envia uma mensagem para os clientes subscritos no email
  ├── PUT "/clientePJ/atualizar" -> Atualiza um cliente PJ
  └── PUT "/clientePJ/desativar/{id}" -> Desativa um cliente PJ
Transferencias
  ├── GET "/transferencias" -> Retorna todas as transferências do Banco
  ├── GET "/transferencias/destino/{destino}" -> Retorna transferências pela conta de destino passada (de onde entra dinheiro)
  ├── GET "/transferencias/historico/{conta}" -> Retorna todas as transferências de uma conta
  ├── GET "/transferencias/ordenadas/{conta}" -> Retorna últimas 3 transferências ordenadas
  ├── GET "/transferencias/origem/{origem}" -> Retornas tranferência pela conta de origem passada (de onde saí dinheiro)
  └── POST "/transferencias" -> Adiciona novas transferências
```

Também é possível ver a documentação de Endpoints no swagger, e acessando o endpoint "/swagger-ui.html".

## **Grupo de Desenvolvedores BugBusters:**

| <a href="https://www.linkedin.com/in/brunoclaudino/" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4D03AQHkHiqXw0XaTQ/profile-displayphoto-shrink_800_800/0/1572375836252?e=1640217600&v=beta&t=qFbgqvzb7j4XWUMK7njC4cHtLvWifbdDXOgAPE-x1EA" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/charllyson-souza-248576108/" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4E03AQH9Nv9sPVRdag/profile-displayphoto-shrink_200_200/0/1637415485830?e=1643241600&v=beta&t=HFJ6rtiQsLOn8Tsi16HDkBlr6dS4iQz0evx6X_9Sf6o" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/paulo-queiroz-7048b1a0" target="blank"><img style="background-color: #abc" align="center" src="https://ca.slack-edge.com/T02FTTBGALF-U02GFCYJPBN-9868142ab62f-512" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/jadergreiner/" target="blank"><img style="background-color: #abc" align="center" src="https://media-exp1.licdn.com/dms/image/C4D03AQFEoP9EyJQb8A/profile-displayphoto-shrink_800_800/0/1629280866731?e=1643241600&v=beta&t=YqOTfjreEJd2fyAtW2OExNGcd7H3f2iO58sKjyBvKcA" height="200" width="200" /></a> | <a href="https://www.linkedin.com/in/tassio-linhares-6b3b07226/"><img style="background-color: #abc" align="center" src="https://ca.slack-edge.com/T02FTTBGALF-U02GGE4376Y-9e8d00e36951-512" height="200" width="200" /></a> |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                    Bruno Claudino Matias                     |                       Charllyson Souza                       |                        Paulo Queiroz                         |                        Jader Greiner                         |                       Tassio Linhares                        |
|TechLead                                                              |  Eng. Dados                                                            |                                                     Developer         |           Developer                                                   |           Eng. Cloud                                                   |

**Obs: ** Clique nas imagens para os respectivos LinkedIns.



#### Turma 1 - Squad 5 - Bugbusters

<a href="https://www.linkedin.com/in/brunoclaudino/" target="blank"><img style="background-color: #abc" align="center" src="https://github.com/TML45/Projeto_Final/blob/developer/BugBusters.png?raw=true" height="200" width="200" /></a>

