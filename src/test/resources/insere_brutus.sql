insert into TB_UNIDADE VALUES
(1, 'Unidade Cristovão Colombo', 'Rua Cristovão Colombo, 100');

INSERT INTO TB_CLIENTE VALUES
(133, 'Ciclano Santos', '111.222.333-44', TRUE, 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (133, '2019-08-15', 'MAMIFERO', 'Brutus', 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (133, '2019-08-05', 'REPTIL', 'Rex', 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (133, '2019-09-05', 'MAMIFERO', 'Rex', 1);