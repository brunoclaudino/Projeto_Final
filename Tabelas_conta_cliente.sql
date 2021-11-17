create schema conta;
use conta;
CREATE TABLE `conta` (
  `id` int unsigned NOT NULL,
  `numero`  integer,
  `agencia` integer,
  `saldo` double,
  `data_abertura` varchar(10),
  PRIMARY KEY (`id`)
  );
  
ALTER TABLE conta
RENAME COLUMN dataAbertura TO data_abertura;

ALTER TABLE conta MODIFY COLUMN id INT auto_increment;
  
insert into conta (id, numero, agencia, saldo, dataAbertura) values (1, 12345, 7894, 789.89, "10/10/2020");
  
select *
from conta;

CREATE TABLE `cliente` (
  `id` int unsigned NOT NULL auto_increment,
  `nome`  varchar(20),
  `data_nascimento` varchar(10),
  `email` varchar(45),
  `data_cadastro` varchar(10),
  PRIMARY KEY (`id`)
  );
  
ALTER TABLE cliente
RENAME COLUMN id TO id_cliente;
  
insert into cliente (id_cliente, nome, data_nascimento, email, data_cadastro) values (1, "José da Silva", "22/10/1986", "josesilva@gmail.com", "10/10/2020");
  
select *
from cliente;