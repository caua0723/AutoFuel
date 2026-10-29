-- AutoFuel - banco de demonstração
-- PostgreSQL 14+ | Execute em um banco vazio chamado autofuel.
-- Todos os dados abaixo são fictícios. Senhas de demonstração são hashes gerados por pgcrypto.

BEGIN;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE public.funcionario (
    id_fun SERIAL PRIMARY KEY,
    nome_fun VARCHAR(100) NOT NULL,
    cpf_fun VARCHAR(14) NOT NULL,
    login_fun VARCHAR(50) NOT NULL UNIQUE,
    senha_fun VARCHAR(200) NOT NULL,
    tipo_fun VARCHAR(20) NOT NULL
);

CREATE TABLE public.bomba (
    id_bom SERIAL PRIMARY KEY,
    descricao_bom VARCHAR(50) NOT NULL,
    tipo_comb_bom VARCHAR(20) NOT NULL,
    ativa_bom BOOLEAN NOT NULL DEFAULT TRUE,
    preco_litro_bom NUMERIC(10,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE public.produto (
    id_prod SERIAL PRIMARY KEY,
    nome_prod VARCHAR(100) NOT NULL,
    preco_prod NUMERIC(10,2) NOT NULL,
    estoque_prod INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE public.abastecimento (
    id_abast SERIAL PRIMARY KEY,
    id_bom INTEGER NOT NULL REFERENCES public.bomba(id_bom),
    id_fun INTEGER NOT NULL REFERENCES public.funcionario(id_fun),
    placa_abast VARCHAR(10) NOT NULL,
    litros_abast NUMERIC(10,3) NOT NULL,
    val_litro_abast NUMERIC(10,2) NOT NULL,
    val_total_abast NUMERIC(10,2) NOT NULL,
    dat_abast DATE NOT NULL
);

CREATE TABLE public.venda_conv (
    id_vend SERIAL PRIMARY KEY,
    id_fun INTEGER NOT NULL REFERENCES public.funcionario(id_fun),
    val_total_vend NUMERIC(10,2) NOT NULL,
    dat_vend DATE NOT NULL
);

CREATE TABLE public.item_venda (
    id_vend INTEGER NOT NULL REFERENCES public.venda_conv(id_vend),
    id_prod INTEGER NOT NULL REFERENCES public.produto(id_prod),
    qtd_item INTEGER NOT NULL,
    val_unit_item NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (id_vend, id_prod)
);

-- Contas públicas de demonstração. Troque as senhas em qualquer uso real.
-- Login: admin      Senha: AutoFuel@123
-- Login: operador   Senha: Operador@123
INSERT INTO public.funcionario (id_fun, nome_fun, cpf_fun, login_fun, senha_fun, tipo_fun) VALUES
(1, 'ADMINISTRADOR DEMO', '000.000.000-00', 'admin', crypt('AutoFuel@123', gen_salt('bf', 8)), 'ADMIN'),
(2, 'OPERADOR DEMO', '111.111.111-11', 'operador', crypt('Operador@123', gen_salt('bf', 8)), 'OPERADOR');

INSERT INTO public.bomba (id_bom, descricao_bom, tipo_comb_bom, ativa_bom, preco_litro_bom) VALUES
(1, 'BOMBA GASOLINA 1', 'GASOLINA', TRUE, 5.99),
(2, 'BOMBA ETANOL 1', 'ETANOL', TRUE, 3.99),
(3, 'BOMBA DIESEL 1', 'DIESEL', TRUE, 6.19);

INSERT INTO public.produto (id_prod, nome_prod, preco_prod, estoque_prod) VALUES
(1, 'AGUA MINERAL 500ML', 3.50, 30),
(2, 'REFRIGERANTE 1L', 8.99, 10),
(3, 'SALGADINHO', 4.25, 20),
(4, 'BALA', 0.99, 50);

INSERT INTO public.abastecimento (id_abast, id_bom, id_fun, placa_abast, litros_abast, val_litro_abast, val_total_abast, dat_abast) VALUES
(1, 1, 2, 'ABC1D23', 30.000, 5.99, 179.70, CURRENT_DATE),
(2, 2, 2, 'DEF4G56', 20.000, 3.99, 79.80, CURRENT_DATE);

INSERT INTO public.venda_conv (id_vend, id_fun, val_total_vend, dat_vend) VALUES
(1, 1, 17.98, CURRENT_DATE),
(2, 2, 12.75, CURRENT_DATE);

INSERT INTO public.item_venda (id_vend, id_prod, qtd_item, val_unit_item) VALUES
(1, 2, 2, 8.99),
(2, 3, 3, 4.25);

SELECT setval(pg_get_serial_sequence('public.funcionario', 'id_fun'), (SELECT MAX(id_fun) FROM public.funcionario));
SELECT setval(pg_get_serial_sequence('public.bomba', 'id_bom'), (SELECT MAX(id_bom) FROM public.bomba));
SELECT setval(pg_get_serial_sequence('public.produto', 'id_prod'), (SELECT MAX(id_prod) FROM public.produto));
SELECT setval(pg_get_serial_sequence('public.abastecimento', 'id_abast'), (SELECT MAX(id_abast) FROM public.abastecimento));
SELECT setval(pg_get_serial_sequence('public.venda_conv', 'id_vend'), (SELECT MAX(id_vend) FROM public.venda_conv));

COMMIT;
