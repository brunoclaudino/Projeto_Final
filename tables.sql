<-- A aplicação está gerando o schema de banco de dados automaticamente através do JPA.

CREATE SCHEMA IF NOT EXISTS bluebank;

USE bluebank;

CREATE TABLE IF NOT EXISTS endereco (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	bairro VARCHAR(45) NULL,
	cep VARCHAR(9) NOT NULL,
	cidade VARCHAR(80) NOT NULL,
	estado ENUM('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 
		'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO') NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero INT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS cliente (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	ativo BIT(1) NOT NULL,
	data_cadastro DATETIME NOT NULL,
	email VARCHAR(100) NOT NULL,
	telefone VARCHAR(14) NOT NULL,
	id_endereco_fk INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_endereco_fk) REFERENCES endereco(id)
);

CREATE TABLE IF NOT EXISTS pessoa_fisica (
	cpf VARCHAR(11) NOT NULL,
	data_nascimento DATE NOT NULL, 
	nome VARCHAR(100) NOT NULL,
	ocupacao VARCHAR(45) NOT NULL,
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_cliente),
	FOREIGN KEY(id_cliente) REFERENCES cliente(id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS pessoa_juridica (
	cnpj VARCHAR(14) NOT NULL,
	data_fundacao DATE NOT NULL,
	inscricao_estadual VARCHAR(9) NOT NULL DEFAULT 'ISENTO',
	nome_fantasia VARCHAR(60) NULL,
	razao_social VARCHAR(80) NOT NULL, 
	id_cliente INT UNSIGNED NOT NULL,
	PRIMARY KEY(id_cliente),
	FOREIGN KEY(id_cliente) REFERENCES cliente(id)
	ON DELETE CASCADE ON UPDATE CASCADE
); 

CREATE TABLE IF NOT EXISTS conta (
	id INT UNSIGNED NOT NULL,
	data_abertura DATETIME NOT NULL,
	numero INT(5) NOT NULL,
	saldo DOUBLE NOT NULL,
	id_cliente_fk INT UNSIGNED NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(id_cliente) REFERENCES cliente(id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS transferencia (
	id INT UNSIGNED NOT NULL,
	conta_origem INT UNSIGNED NOT NULL,
	conta_destino INT UNSIGNED NOT NULL, 
	valor DOUBLE UNSIGNED NOT NULL,
	data DATETIME NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(conta_origem) REFERENCES conta(id),
    	FOREIGN KEY(conta_destino) REFERENCES conta(id)
);

