delete from tblramo;
delete from tblproduto;
delete from tblprodutocomplemento;
delete from tblprodutocor;
delete from tblprodutodetalhe;
delete from tblprodutofabricante;
delete from tblprodutotipo;
delete from tblcontatotipo;
delete from tbldocumentotipo;

alter sequence tblramo_seq restart with 25;
alter sequence tblproduto_seq restart with 97;
alter sequence tblprodutocomplemento_seq restart with 18;
alter sequence tblprodutocor_seq restart with 12;
alter sequence tblprodutodetalhe_seq restart with 17;
alter sequence tblprodutofabricante_seq restart with 20;
alter sequence tblprodutotipo_seq restart with 13;
alter sequence tblcontatotipo_seq restart with 6;
alter sequence tbldocumentotipo_seq restart with 6;


insert into tblramo values 
(1,'AÇOUGUE', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'AUTO POSTO', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'BAR', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'BUFFET', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'CHURRASCARIA', now() at time zone 'utc', now() at time zone 'utc'),
(6, 'CONDOMÍNIO', now() at time zone 'utc', now() at time zone 'utc'),
(7, 'COZINHA', now() at time zone 'utc', now() at time zone 'utc'),
(8, 'DOCERIA', now() at time zone 'utc', now() at time zone 'utc'),
(9, 'EMPRESA', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'ESCOLA', now() at time zone 'utc', now() at time zone 'utc'),
(11, 'HABIBS', now() at time zone 'utc', now() at time zone 'utc'),
(12, 'INDÚSTRIA', now() at time zone 'utc', now() at time zone 'utc'),
(13, 'LANCHONETE', now() at time zone 'utc', now() at time zone 'utc'),
(14, 'LOJA', now() at time zone 'utc', now() at time zone 'utc'),
(15, 'MECÂNICA', now() at time zone 'utc', now() at time zone 'utc'),
(16, 'MERCEARIA', now() at time zone 'utc', now() at time zone 'utc'),
(17, 'OFICINA', now() at time zone 'utc', now() at time zone 'utc'),
(18, 'PADARIA', now() at time zone 'utc', now() at time zone 'utc'),
(19, 'PASTELARIA', now() at time zone 'utc', now() at time zone 'utc'),
(20, 'PASTIFÍCIO', now() at time zone 'utc', now() at time zone 'utc'),
(21, 'PEIXARIA', now() at time zone 'utc', now() at time zone 'utc'),
(22, 'PIZZARIA', now() at time zone 'utc', now() at time zone 'utc'),
(23, 'RESTAURANTE', now() at time zone 'utc', now() at time zone 'utc'),
(24, 'ROTISSERIA', now() at time zone 'utc', now() at time zone 'utc');

insert into tblprodutocomplemento values
(6, 'Monodensidade Sem bico', '0', now() at time zone 'utc', now() at time zone 'utc'),
(7, 'Bidensidade Sem bico', '5', now() at time zone 'utc', now() at time zone 'utc'),
(8, 'Monodensidade Bico PVC', '1', now() at time zone 'utc', now() at time zone 'utc'),
(9, 'Bidensidade Bico PVC', '6', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'Monodensidade Bico aço', '2', now() at time zone 'utc', now() at time zone 'utc'),
(11, 'Bidensidade Bico aço', '7', now() at time zone 'utc', now() at time zone 'utc'),
(12, 'Monodensidade Sem bico-Forrada', '3', now() at time zone 'utc', now() at time zone 'utc'),
(14, 'DUB', '4', now() at time zone 'utc', now() at time zone 'utc'),
(15, 'Não definido', '8', now() at time zone 'utc', now() at time zone 'utc'),
(16, 'Frete', 'F', now() at time zone 'utc', now() at time zone 'utc'),
(17, 'Apenas para Teste IT', '.', now() at time zone 'utc', now() at time zone 'utc');

insert into tblprodutocor values
(3, 'Branco', 'B', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Marrom', 'M', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Preto', 'P', now() at time zone 'utc', now() at time zone 'utc'),
(6, 'Rosa', 'R', now() at time zone 'utc', now() at time zone 'utc'),
(7, 'MA', 'A', now() at time zone 'utc', now() at time zone 'utc'),
(8, 'Bege', 'G', now() at time zone 'utc', now() at time zone 'utc'),
(9, 'Ameixa', 'X', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'Frete', 'F', now() at time zone 'utc', now() at time zone 'utc'),
(11, 'Apenas para Teste IT', '.', now() at time zone 'utc', now() at time zone 'utc'),
(12, 'Apenas para Teste 2', ',', now() at time zone 'utc', now() at time zone 'utc');

insert into tblprodutodetalhe values 
(1, 'Cadarço', 'A', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'Elástico', 'E', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'Cano curto', 'C', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Cano médio', 'M', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Cano longo', 'L', now() at time zone 'utc', now() at time zone 'utc'),
(8, 'Alça', 'K', now() at time zone 'utc', now() at time zone 'utc'),
(9, 'Social', 'S', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'EVA', 'V', now() at time zone 'utc', now() at time zone 'utc'),
(11, 'STICKY SHOE', 'P', now() at time zone 'utc', now() at time zone 'utc'),
(13, 'EVA c/ palmilha 90', 'F', now() at time zone 'utc', now() at time zone 'utc'),
(14, 'EVA c/ palmilha 61', 'G', now() at time zone 'utc', now() at time zone 'utc'),
(15, '-', 'B', now() at time zone 'utc', now() at time zone 'utc'),
(16, 'Apenas para Teste IT', '.', now() at time zone 'utc', now() at time zone 'utc');

insert into tblprodutofabricante values 
(1, 'Susa', '02', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'Le Sportiff', '03', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'Italbotas', '04', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Vulcaflex', '05', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Calbras', '01', now() at time zone 'utc', now() at time zone 'utc'),
(6, 'Marluvas', '06', now() at time zone 'utc', now() at time zone 'utc'),
(7, 'Kemo', '07', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'Darlo', '08', now() at time zone 'utc', now() at time zone 'utc'),
(12, 'Talk Flex', '09', now() at time zone 'utc', now() at time zone 'utc'),
(13, 'Soft Works', '10', now() at time zone 'utc', now() at time zone 'utc'),
(14, 'Vulcabras', '11', now() at time zone 'utc', now() at time zone 'utc'),
(15, 'Canadá', '12', now() at time zone 'utc', now() at time zone 'utc'),
(16, 'Fujiwara', '13', now() at time zone 'utc', now() at time zone 'utc'),
(17, 'Não definido', '14', now() at time zone 'utc', now() at time zone 'utc'),
(18, '-', '00', now() at time zone 'utc', now() at time zone 'utc'),
(19, 'Apenas para Teste IT', '.', now() at time zone 'utc', now() at time zone 'utc');

insert into tblprodutotipo values 
(1, 'Bota', 'B', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'Sapato', 'S', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'Botina', 'T', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Croc', 'C', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Babuch', 'H', now() at time zone 'utc', now() at time zone 'utc'),
(6, 'Sapatilha', 'P', now() at time zone 'utc', now() at time zone 'utc'),
(7, 'Tênis', 'E', now() at time zone 'utc', now() at time zone 'utc'),
(8, 'Palmilha', 'Z', now() at time zone 'utc', now() at time zone 'utc'),
(9, 'Avental', 'A', now() at time zone 'utc', now() at time zone 'utc'),
(10, 'Frete', 'F', now() at time zone 'utc', now() at time zone 'utc'),
(11, 'Sapatênis', 'N', now() at time zone 'utc', now() at time zone 'utc'),
(12, 'Apenas para Teste IT', '.', now() at time zone 'utc', now() at time zone 'utc');

insert into tblproduto VALUES
(6,2,5,2,8,1,'SPE102','', 58, now() at time zone 'utc', now() at time zone 'utc'),
(7,2,3,2,8,1,'SBE102','', 62, now() at time zone 'utc', now() at time zone 'utc'),
(8,2,5,1,8,4,'SPA105','', 68, now() at time zone 'utc', now() at time zone 'utc'),
(9,1,3,3,6,3,'BBC004','P4', 50, now() at time zone 'utc', now() at time zone 'utc'),
(10,1,3,4,6,3,'BBM004','P2', 55, now() at time zone 'utc', now() at time zone 'utc'),
(11,1,3,5,6,3,'BBL004','', 62, now() at time zone 'utc', now() at time zone 'utc'),
(12,1,5,3,6,3,'BPC004','P3', 45, now() at time zone 'utc', now() at time zone 'utc'),
(13,1,5,4,6,3,'BPM004','P1', 52, now() at time zone 'utc', now() at time zone 'utc'),
(14,1,5,5,6,3,'BPL004','', 54, now() at time zone 'utc', now() at time zone 'utc'),
(15,3,5,2,8,4,'TPE105','', 65, now() at time zone 'utc', now() at time zone 'utc'),
(16,3,5,2,10,4,'TPE205','', 72, now() at time zone 'utc', now() at time zone 'utc'),
(17,2,5,9,6,2,'SPS003','S1', 88, now() at time zone 'utc', now() at time zone 'utc'),
(18,2,5,2,7,5,'SPE501','C4', 79, now() at time zone 'utc', now() at time zone 'utc'),
(19,2,3,2,7,5,'SBE501','C1', 80, now() at time zone 'utc', now() at time zone 'utc'),
(20,2,3,3,6,6,'SBC006','', 0, now() at time zone 'utc', now() at time zone 'utc'),
(21,4,3,8,6,7,'CBK007','', 37, now() at time zone 'utc', now() at time zone 'utc'),
(22,2,5,1,9,5,'SPA501','C6', 33, now() at time zone 'utc', now() at time zone 'utc'),
(24,2,5,9,6,10,'SPS008','S2', 120, now() at time zone 'utc', now() at time zone 'utc'),
(25,2,5,9,6,12,'SPS009','S3', 150, now() at time zone 'utc', now() at time zone 'utc'),
(26,2,5,10,6,13,'SPV010','E3', 75, now() at time zone 'utc', now() at time zone 'utc'),
(27,2,3,10,6,13,'SBV010','E1', 72, now() at time zone 'utc', now() at time zone 'utc'),
(28,5,3,10,6,13,'HBV010','E2', 72, now() at time zone 'utc', now() at time zone 'utc'),
(29,5,5,10,6,13,'HPV010','E4', 72, now() at time zone 'utc', now() at time zone 'utc'),
(30,6,5,10,6,13,'PPV010','E7', 50, now() at time zone 'utc', now() at time zone 'utc'),
(32,6,3,10,6,13,'PBV010','E5', 50, now() at time zone 'utc', now() at time zone 'utc'),
(33,2,5,2,9,4,'SPE605','', 65, now() at time zone 'utc', now() at time zone 'utc'),
(34,3,5,2,11,5,'TPE701','', 80, now() at time zone 'utc', now() at time zone 'utc'),
(35,3,5,2,9,5,'TPE601','C3', 78, now() at time zone 'utc', now() at time zone 'utc'),
(36,1,3,4,12,3,'BBM304','', 63, now() at time zone 'utc', now() at time zone 'utc'),
(37,1,3,4,6,13,'BBM010','E9', 120, now() at time zone 'utc', now() at time zone 'utc'),
(38,1,5,4,6,13,'BPM010','', 105, now() at time zone 'utc', now() at time zone 'utc'),
(39,2,4,9,6,12,'SMS009','', 140, now() at time zone 'utc', now() at time zone 'utc'),
(40,7,3,10,6,13,'EBV010','E6', 76, now() at time zone 'utc', now() at time zone 'utc'),
(41,7,5,10,6,13,'EPV010','E8', 76, now() at time zone 'utc', now() at time zone 'utc'),
(43,1,3,4,6,14,'BBM011','', 45, now() at time zone 'utc', now() at time zone 'utc'),
(44,2,3,2,9,5,'SBE601','C2', 69, now() at time zone 'utc', now() at time zone 'utc'),
(45,2,5,2,9,5,'SPE601','C5', 72, now() at time zone 'utc', now() at time zone 'utc'),
(46,1,3,3,6,13,'BBC010','E10', 95, now() at time zone 'utc', now() at time zone 'utc'),
(48,2,3,11,6,15,'SBP012','T1', 75, now() at time zone 'utc', now() at time zone 'utc'),
(50,2,5,11,6,15,'SPP012','E16', 75, now() at time zone 'utc', now() at time zone 'utc'),
(51,1,3,4,6,16,'BBM013','P6', 42, now() at time zone 'utc', now() at time zone 'utc'),
(52,5,6,10,6,13,'HRV010','E11', 75, now() at time zone 'utc', now() at time zone 'utc'),
(63,7,7,10,6,13,'EAV010','E12', 67, now() at time zone 'utc', now() at time zone 'utc'),
(64,8,8,10,14,13,'ZGV410','Z1', 8.5, now() at time zone 'utc', now() at time zone 'utc'),
(65,3,3,2,9,1,'TBE602','C7', 120, now() at time zone 'utc', now() at time zone 'utc'),
(67,2,5,1,6,10,'SPA008','S4', 98, now() at time zone 'utc', now() at time zone 'utc'),
(69,1,5,10,6,13,'BPV010','E13', 78, now() at time zone 'utc', now() at time zone 'utc'),
(70,9,3,8,15,17,'ABK814','A1', 30, now() at time zone 'utc', now() at time zone 'utc'),
(73,2,3,2,6,6,'SBE006','C8', 95, now() at time zone 'utc', now() at time zone 'utc'),
(74,2,5,2,6,6,'SPE006','C9', 92, now() at time zone 'utc', now() at time zone 'utc'),
(78,2,5,2,11,4,'SPE705','C10', 83, now() at time zone 'utc', now() at time zone 'utc'),
(81,1,5,3,6,13,'BPC010','E14', 89, now() at time zone 'utc', now() at time zone 'utc'),
(82,7,9,10,6,13,'EXV010','E15', 69, now() at time zone 'utc', now() at time zone 'utc'),
(84,10,10,13,16,18,'FFFF00','F0', 30, now() at time zone 'utc', now() at time zone 'utc'),
(86,6,3,10,15,13,'PBV810','E20', 75, now() at time zone 'utc', now() at time zone 'utc'),
(87,6,5,10,15,13,'PPV810','E21', 75, now() at time zone 'utc', now() at time zone 'utc'),
(88,7,6,10,6,13,'ERV010','E22', 75, now() at time zone 'utc', now() at time zone 'utc'),
(89,11,5,10,6,13,'NPV010','E25', 80, now() at time zone 'utc', now() at time zone 'utc'),
(90,5,5,13,6,13,'HPF010','E26', 75, now() at time zone 'utc', now() at time zone 'utc'),
(91,5,3,13,6,13,'HBF010','E27', 75, now() at time zone 'utc', now() at time zone 'utc'),
(92,5,3,14,6,13,'HBG010','E28', 80, now() at time zone 'utc', now() at time zone 'utc'),
(93,5,5,14,6,13,'HPG010','E29', 80, now() at time zone 'utc', now() at time zone 'utc'),
(95,10,10,15,16,18,'FFBF00','E30', 40, now() at time zone 'utc', now() at time zone 'utc'),
(96,10,10,15,16,18,'FFBFFF','EFF', 40.41, now() at time zone 'utc', now() at time zone 'utc');

insert into tblcontatotipo values 
(1, 'Telefone Comercial', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'Telefone Celular', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'Email', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Apenas para Tst IT', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Apenas para Tst IT2', now() at time zone 'utc', now() at time zone 'utc');

insert into tbldocumentotipo values 
(1, 'CPF', now() at time zone 'utc', now() at time zone 'utc'),
(2, 'CNPJ', now() at time zone 'utc', now() at time zone 'utc'),
(3, 'Inscrição Estadual', now() at time zone 'utc', now() at time zone 'utc'),
(4, 'Apenas para Tst IT', now() at time zone 'utc', now() at time zone 'utc'),
(5, 'Apenas para Tst IT2', now() at time zone 'utc', now() at time zone 'utc');