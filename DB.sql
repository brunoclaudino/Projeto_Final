use bluebank;

insert into	endereco ( bairro, cep, cidade, estado, logradouro, numero)
values
	('Setor Marista', '79896-123','Juiz de Fora', 'MG', 'Rua da Misericórdia', 835),
    ('Bairro Alvorada', '27943-010','Serra Talhada', 'PE', 'Avenida José Neto', 2048),
    ('Centro', '75831-100','Macaé', 'RJ', 'Rua Vila Muriá', 119),
    ('Itamaraty', '48970-000','Sr. do Bonfim', 'BA', 'Rua José Bonifácio', 117),
    ('Alto Petrópolis', '91260-020','Porto Alegre', 'RS', 'Rua João da Silva Bueno', 227),
    ('Val-de-Cans', '66613-010','Belém', 'PA', 'Viela Alegre', 1057),
    ('Jardim Alvorada', '87030-000','Maringá', 'PR', 'Av. Pedro Taques', 1256),
    ('Diamantina', '79420-000','Camapuã', 'MS', 'R. Pioneiros', 458),
    ('Centro', '64640-000','Santo Antônio de Lisboa', 'PI', 'R. Antonio Serafim', 102),
    ('Jocely Dantas', '62042-120','Sobral', 'CE', 'Rua Santos Medeiros', 200),
    ('Vale do Sol', '59143-265','Parnamirim', 'RN', 'R. Dr. Átila Paiva', 304),
    ('Distrito Industrial', '58411-170','Campina Grande', 'PB', 'Av. João Wallig', 1187);
select * from endereco;

insert into cliente ( ativo, data_cadastro,  email, telefone, id_endereco_fk)
values
	(1, '2021-11-24 14:42:19.768000','maria@gmail.com', '(29)98763-2854', 1),
    (1, '2018-03-07 09:23:46.751000','joao@hotmail.com', '(29)98763-2854', 2),
    (1, '2020-10-17 11:22:11.568000','jose@tre-pr.jus.br', '(56)98976-2453', 3),
    (1, '2007-12-04 08:35:02.425000','motoscentral@outlook.com', '(63)98851-7521', 4),
    (1, '2013-10-18 02:47:59.245000','juliamodas@bol.com.br', '(78)94126-6248', 5),
    (1, '2010-06-27 18:12:42.852000','hotelexpresso@yahoo.com', '(11)98624-4526', 6),
    (1, '2020-05-01 19:42:27.267000','joana@usp.br', '(12)99863-2458', 3),
    (1, '2017-04-22 05:13:04.456287','luiza@yahoo.com.br', '(12)98852-4875', 2),
    (1, '2018-11-14 17:37:06.197530','Pedro@gmail.com.br', '(22)99542-7316', 1),
    (1, '2014-02-09 16:43:13.187054','casadecarnes@gmail.com.br', '(33)98795-6421', 7),
    (1, '2011-11-06 12:21:08.287563','ditolanches@oi.com', '(46)98846-7319', 8),
    (1, '2003-10-12 20:31:27.102486','lavaaltobrilho@hotmail.com', '(57)98561-4128', 9),
    (1, '2007-09-26 15:45:13.452796','carlos@gmail.com', '(62)98627-3258', 10),
    (1, '2014-07-26 12:52:09.321056','clinicabela@outlook.com', '(73)99421-7896', 11),
    (1, '2010-12-21 10:41:02.245816','flavia@outlook.com', '(81)98752-8624', 11),
    (1, '2002-11-17 08:20:38.324089','tess@outlook.com', '(93)98120-4210', 12);
select * from cliente;

insert into pessoa_fisica( cpf, data_nascimento,  nome, ocupacao, id_cliente)
values
	('12312365412', '2000-09-08','Maria Pereira da Silva', 'Enfermeira', 1),
    ('74185296324', '1986-05-05','João Paulo Oliveira', 'Advogado', 2),
    ('75315984261', '1995-04-11','José Carlos Murta', 'Padeiro', 3),
    ('15207896530', '1997-09-27','Joana Gough Murta', 'Dentista', 7),
    ('42675134029', '1990-08-29','Luiza Santos Oliveira', 'Artista', 8),
    ('15632180265', '1997-05-16','Pedro Guimarães da Silva', 'Jornalista', 9),
    ('04581230497', '1992-02-13','Carlos Gama Vieira', 'Marceneiro', 13),
    ('98563210054', '1991-04-30','Flavia Costa Vieira', 'Professora', 15);
select * from pessoa_fisica;
    
insert into	pessoa_juridica( cnpj, data_fundacao,  inscricao_estadual, nome_fantasia, razao_social, id_cliente)
values
	('71231283000125', '1999-09-08','789632451', 'Motos Central', 'Fernando Comércio LTDA', 4),
    ('47415852000124', '1995-05-05','456369750', 'Júlia Modas', 'Julia Confecção ME', 5),
    ('67531059000111', '2009-04-11','342157809', 'Hotel Expresso', ' Silva Hotel EIRELI ME', 6),
    ('78953019000121', '2008-06-28','159741028', 'Casa de Carnes Ribeiro', 'Ribeiro Casa de carnes LTDA', 10),
    ('45237891000176', '2012-07-09','109872964', 'Lanchonete do Dito', 'Dito Comércio EIRELI ME', 11),
    ('76910302000138', '2009-05-12','875681049', 'Lava Jato Alto Brilho', 'Alto Brilho Limpeza ME', 12),
    ('24635702000116', '2015-11-27','156324750', 'Clinica Bela', 'Julia Beleza LTDA', 14),
    ('12036620000171', '2004-07-05','234568102', 'Indústria Tess', 'Tess Industria LTDA', 16);
select * from pessoa_juridica;

insert into conta( data_abertura, numero, saldo, id_cliente_fk)
values
	('2014-01-06 14:42:19.768000', 45270, 1004.87, 1),
    ('1998-07-15 14:42:19.768000', 24538, 10257.63, 2),
    ('2013-07-18 14:42:19.768000', 78563, 905.85, 3),
	('2005-12-15 14:42:19.768000', 12539, 8054.96, 4),
    ('1998-09-08 14:42:19.768000', 75412,12578.96, 5),
    ('2010-03-09 14:42:19.768000', 84263,18754.23, 6),
    ('2006-03-12 14:42:19.768000', 24587, 854.96, 7),
    ('1999-06-13 14:42:19.768000', 87560, 542.63, 8),
    ('2005-11-26 14:42:19.768000', 63204, 962.51, 9),
	('2003-06-10 14:42:19.768000', 63210, 102.48, 10),
    ('1997-07-02 14:42:19.768000', 10234,205.23, 11),
    ('2012-05-19 14:42:19.768000', 75123,845.62, 12),
    ('2003-07-12 14:42:19.768000', 40598,76.85, 13),
    ('2017-09-27 14:42:19.768000', 30127,304.30, 14),
    ('2001-03-10 14:42:19.768000', 20334,2087.22, 15),
    ('1999-07-17 14:42:19.768000', 10872,30782.05, 16);
select * from conta;
    
select * from transferencia;