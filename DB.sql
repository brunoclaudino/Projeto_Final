use bluebank;

insert into
	endereco ( bairro, cep, cidade, estado, logradouro, numero)
values
	('Setor Marista', '79896-123','Juiz de Fora', 'MG', 'Rua da Misericórdia', 835),
    ('Bairro Alvorada', '27943-010','56980-000', 'PE', 'Avenida José Neto', 2048),
    ('Centro', '75831-100','Macaé', 'RJ', 'Rua Vila Muriá', 119),
    ('Itamaraty', '48970-000','Sr. do Bonfim', 'BA', 'Rua José Bonifácio', 117),
    ('Alto Petrópolis', '91260-020','Porto Alegre', 'RS', 'Rua João da Silva Bueno', 227),
    ('Val-de-Cans', '66613-010','Belém', 'PA', 'Viela Alegre', 1057);
select * from endereco;

insert into
	cliente ( ativo, data_cadastro,  email, telefone, id_endereco_fk)
values
	(1, '2021-11-24 14:42:19.768000','maria@gmail.com', '(29)98763-2854', 1),
    (1, '2018-03-07 09:23:46.751000','joao@hotmail.com', '(29)98763-2854', 2),
    (1, '2020-10-17 11:22:11.568000','jose@tre-pr.jus.br', '(56)98976-2453', 3),
    (1, '2007-12-04 08:35:02.425000','motoscentral@outlook.com', '(63)98851-7521', 4),
    (1, '2013-10-18 02:47:59.245000','juliamodas@bol.com.br', '(78)94126-6248', 5),
    (1, '2010-06-27 18:12:42.852000','hotelexpresso@yahoo.com', '(11)98624-4526', 6);
select * from cliente;

select * from pessoa_fisica;
insert into
	pessoa_fisica( cpf, data_nascimento,  nome, ocupacao, id_cliente)
values
	('12312365412', '2000-09-08','Maria Pereira da Silva', 'Enfermeira', 1),
    ('74185296324', '1986-05-05','João Paulo Oliveira', 'Advogado', 2),
    ('75315984261', '1995-04-11','José Carlos Murta', 'Padeiro', 3);
    
select * from pessoa_juridica;
insert into
	pessoa_juridica( cnpj, data_fundacao,  inscricao_estadual, nome_fantasia, razao_social, id_cliente)
values
	('71231283654125', '1999-09-08','789632451', 'Motos Central', 'Fernando Comércio LTDA', 4),
    ('47415852963324', '1995-05-05','456369750', 'Júlia Modas', 'Julia Confecção ME', 5),
    ('67531059842611', '2009-04-11','342157809', 'Hotel Expresso', ' Silva Hotel EIRELI ME', 6);

select * from conta;
insert into
	conta( data_abertura, numero, saldo, id_cliente_fk)
values
	('2014-01-06', 45270, 1004.87, 1),
    ('1998-07-15', 24538, 10257.63, 2),
    ('2013-07-18', 78563, 905.85, 3),
	('2005-12-15', 12539, 8054.96, 4),
    ('1998-09-08', 75412,12578.96, 5),
    ('2010-03-09', 84263,18754.23, 6);
    
select * from transferencia;