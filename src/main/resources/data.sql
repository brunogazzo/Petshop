insert into TB_UNIDADE VALUES
(1, 'Unidade Cristovão Colombo', 'Rua Cristovão Colombo, 100');

INSERT INTO TB_CLIENTE VALUES
(null, 'Fulano da Silva', '000.111.222-33', FALSE, 1);
INSERT INTO TB_CLIENTE VALUES
(null, 'Ciclano Santos', '111.222.333-44', TRUE, 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (1, '2019-01-02', 'MAMIFERO', 'Totó', 1);

INSERT INTO TB_ANIMAL
(ID_CLIENTE, DATA_NASCIMENTO, ESPECIE, NOME, ID_UNIDADE)
VALUES (2, '2016-07-31', 'REPTIL', 'Rex', 1);

INSERT INTO TB_PRODUTO
(ID, VALOr, DESCRICAO, ID_ANIMAL)
VALUES (1, 130.00, 'Vacina aftosa', 1);

INSERT INTO TB_PRODUTO
(ID, VALOr, DESCRICAO, ID_ANIMAL)
VALUES (2, 40.00, 'Ração', 2);
