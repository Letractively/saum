--
-- PostgreSQL database dump
--

-- Started on 2011-06-11 16:51:20

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1824 (class 1262 OID 16798)
-- Name: saum; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE saum WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


\connect saum

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1502 (class 1259 OID 17621)
-- Dependencies: 6
-- Name: boletos_gerados_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE boletos_gerados_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1827 (class 0 OID 0)
-- Dependencies: 1502
-- Name: boletos_gerados_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('boletos_gerados_seq', 1, false);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1503 (class 1259 OID 17623)
-- Dependencies: 1787 1788 1789 6
-- Name: boletos_gerados; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE boletos_gerados (
    id bigint DEFAULT nextval('boletos_gerados_seq'::regclass) NOT NULL,
    usuario_id_fk bigint NOT NULL,
    valor character varying(10) DEFAULT NULL::character varying,
    data_vencimento date,
    data_pagamento date,
    processamento date,
    data_vencimento_prorrogado date,
    pago boolean,
    valor_pago character varying(10),
    id_f2b bigint,
    url_boleto_f2b character varying(250),
    tipo_envio_f2b character varying(1) DEFAULT 'a'::character varying
);


--
-- TOC entry 1504 (class 1259 OID 17629)
-- Dependencies: 6
-- Name: contato_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE contato_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999999999
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1828 (class 0 OID 0)
-- Dependencies: 1504
-- Name: contato_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('contato_seq', 10, true);


--
-- TOC entry 1505 (class 1259 OID 17631)
-- Dependencies: 1790 6
-- Name: contato; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE contato (
    id bigint DEFAULT nextval('contato_seq'::regclass) NOT NULL,
    nome character varying(70),
    email character varying(70),
    telefone character varying(14),
    mensagem text
);


--
-- TOC entry 1506 (class 1259 OID 17638)
-- Dependencies: 6
-- Name: planos_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE planos_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1829 (class 0 OID 0)
-- Dependencies: 1506
-- Name: planos_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('planos_seq', 9, true);


--
-- TOC entry 1507 (class 1259 OID 17640)
-- Dependencies: 1791 1792 1793 1794 6
-- Name: planos; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE planos (
    id bigint DEFAULT nextval('planos_seq'::regclass) NOT NULL,
    valor numeric(6,2) DEFAULT NULL::numeric,
    nome character varying(20) DEFAULT NULL::character varying,
    descricao character varying(250),
    ativado boolean,
    desconto bigint,
    limite_desconto bigint DEFAULT 0,
    multa bigint,
    juro_mes bigint,
    quantidade_cliente_min bigint,
    quantidade_cliente_max bigint
);


--
-- TOC entry 1508 (class 1259 OID 17647)
-- Dependencies: 6
-- Name: usuario_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE usuario_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 999999999999999
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1830 (class 0 OID 0)
-- Dependencies: 1508
-- Name: usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('usuario_seq', 10, true);


--
-- TOC entry 1509 (class 1259 OID 17649)
-- Dependencies: 1795 1796 1797 1798 1799 1800 1801 1802 6
-- Name: usuario; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE usuario (
    id bigint DEFAULT nextval('usuario_seq'::regclass) NOT NULL,
    nome character varying(50),
    senha character varying(200),
    endereco character varying(50),
    complemento_endereco character varying(100),
    bairro character varying(40),
    cidade character varying(35),
    estado character varying(25),
    cep character varying(10) DEFAULT NULL::character varying,
    telefone1 character varying(14) DEFAULT NULL::character varying,
    telefone2 character varying(14) DEFAULT NULL::character varying,
    email character varying(50) DEFAULT NULL::character varying,
    cpf character varying(20) DEFAULT NULL::character varying,
    dt_pagamento bigint DEFAULT 8,
    dt_ativacao timestamp without time zone,
    bloqueado smallint,
    administrativo boolean,
    data_limite_desbloqueio timestamp without time zone,
    envio_email_cadastro boolean DEFAULT false,
    id_troca_senha character varying(50),
    nome_banco_dados character varying(25),
    url_sistema character varying(25),
    planos_id_fk bigint
);


--
-- TOC entry 1818 (class 0 OID 17623)
-- Dependencies: 1503
-- Data for Name: boletos_gerados; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 1819 (class 0 OID 17631)
-- Dependencies: 1505
-- Data for Name: contato; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO contato (id, nome, email, telefone, mensagem) VALUES (7, 'teste', 'efrenlixo@gmail.com', '6134432227', 'teste de mensagem');
INSERT INTO contato (id, nome, email, telefone, mensagem) VALUES (8, 'teste', 'efrenlixo@gmail.com', '42342342', 'df sd fsdfasdf asdfasdfa sfd');
INSERT INTO contato (id, nome, email, telefone, mensagem) VALUES (9, 'teste', 'efrenjunior@gmail.com', '(33)3333-3333', 'dfa dasd fasdfasdf');
INSERT INTO contato (id, nome, email, telefone, mensagem) VALUES (10, 'Genis de Oliveira', 'ginfo@uol.com.br', '1735251901', 'Bom dia, estive dando uma olhada no seu sistema, e queria saber mais 
detalhes sobre o mesmo, tipo ele da suporte a PPPoE ou IP+MAC, e se 
os valores é pago mensal?, e se a comunicação é feita por SSH ou por 
radius.

Desde já fica os meus agradecimentos

Gênis

');


--
-- TOC entry 1820 (class 0 OID 17640)
-- Dependencies: 1507
-- Data for Name: planos; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (1, 0.00, 'Básico 1', 'Por um período de 3 meses, para que você possa testar o sistema. Após o perído o plano será o Básico 3', true, 0, 0, 2, 2, 1, 10);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (2, 14.00, 'Básico 2', 'Para quem possuir o máximo de 41 clientes.', true, 0, 0, 2, 2, 11, 40);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (3, 25.00, 'Básico 3', 'Para quem possuir o máximo de 70 clientes.', true, 0, 0, 2, 2, 41, 70);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (4, 45.00, 'Premium I', 'Para o máximo de 110 clientes. Se quiser o registro do seu domínio adicionar R$ 9,00 à mensalidade', true, 0, 0, 2, 2, 71, 110);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (5, 72.00, 'Premium II', 'Para o máximo de 170 clientes. Se quiser o registro do seu domínio adicionar R$ 7,00 à mensalidade', true, 0, 0, 2, 2, 111, 170);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (6, 99.00, 'Premium III', 'Para o máximo de 250 clientes. Se quiser o registro do seu domínio adicionar R$ 6,00 à mensalidade', true, 0, 0, 2, 2, 171, 250);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (7, 129.00, 'Profissional I', 'Para o máximo de 400 clientes. Registro do domínio grátis', true, 0, 0, 2, 2, 251, 400);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (8, 169.00, 'Profissional II', 'Para o máximo de 700 clientes. Registro do domínio grátis', true, 0, 0, 2, 2, 401, 700);
INSERT INTO planos (id, valor, nome, descricao, ativado, desconto, limite_desconto, multa, juro_mes, quantidade_cliente_min, quantidade_cliente_max) VALUES (9, 999.00, 'Especial Full', 'favor consultar o valor, pois colocaremos um servidor dedicado ao seu negócio.', true, 0, 0, 2, 2, 701, 9999999999999);


--
-- TOC entry 1821 (class 0 OID 17649)
-- Dependencies: 1509
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO usuario (id, nome, senha, endereco, complemento_endereco, bairro, cidade, estado, cep, telefone1, telefone2, email, cpf, dt_pagamento, dt_ativacao, bloqueado, administrativo, data_limite_desbloqueio, envio_email_cadastro, id_troca_senha, nome_banco_dados, url_sistema, planos_id_fk) VALUES (6, 'erwerqweq', NULL, 'CONJUNTO RESIDENCIAL 78', 'CASA 08', 'VALE DO AMANHECER (PLANALTINA)', 'BRASÍLIA', 'AM', '73370-052', '(61)3388-2341', '(61)9163-7180', 'analistadesisternas@gmail.com', '903.002.701-00', 10, '2011-06-05 00:23:21.919', 0, false, NULL, false, NULL, NULL, 'adfadsfasd', 5);
INSERT INTO usuario (id, nome, senha, endereco, complemento_endereco, bairro, cidade, estado, cep, telefone1, telefone2, email, cpf, dt_pagamento, dt_ativacao, bloqueado, administrativo, data_limite_desbloqueio, envio_email_cadastro, id_troca_senha, nome_banco_dados, url_sistema, planos_id_fk) VALUES (7, 'erwerqweq', NULL, 'CONJUNTO RESIDENCIAL 59', 'apto 203', 'VALE DO AMANHECER (PLANALTINA)', 'BRASÍLIA', 'PA', '70258-060', '(33)3333-3333', '(33)3333-3333', 'adfa', '007.842.927-79', 10, '2011-06-09 00:28:43.04', 0, false, NULL, false, NULL, NULL, 'adfadsfasd', 8);
INSERT INTO usuario (id, nome, senha, endereco, complemento_endereco, bairro, cidade, estado, cep, telefone1, telefone2, email, cpf, dt_pagamento, dt_ativacao, bloqueado, administrativo, data_limite_desbloqueio, envio_email_cadastro, id_troca_senha, nome_banco_dados, url_sistema, planos_id_fk) VALUES (9, 'Cyber Internet Telecom', NULL, 'R. Euzébio de Oliveira, 259', 'Em frente a rádio lider fm ', 'Centro', 'Vianópolis', 'GO', '75260-000', '(62)3335-1278', '(62)8154-1304', 'olivionet@hotmail.com', '07.237.857/0001-79', 10, '2011-06-10 09:44:58.168', 0, false, NULL, false, NULL, NULL, 'cyber', 1);
INSERT INTO usuario (id, nome, senha, endereco, complemento_endereco, bairro, cidade, estado, cep, telefone1, telefone2, email, cpf, dt_pagamento, dt_ativacao, bloqueado, administrativo, data_limite_desbloqueio, envio_email_cadastro, id_troca_senha, nome_banco_dados, url_sistema, planos_id_fk) VALUES (10, 'Flávio Neves', NULL, 'R Tefé, 498', 'frente', 'Centro', 'Goaituba', 'GO', '75600-000', '(64)3495-2883', '(64)9282-4048', 'fbn.antonio@gmail.com', '950.798.901-34', 10, '2011-06-10 15:08:40.852', 0, false, NULL, false, NULL, NULL, 'http', 1);


--
-- TOC entry 1805 (class 2606 OID 17664)
-- Dependencies: 1503 1503
-- Name: boletos_gerados_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY boletos_gerados
    ADD CONSTRAINT boletos_gerados_pkey PRIMARY KEY (id);


--
-- TOC entry 1807 (class 2606 OID 17666)
-- Dependencies: 1505 1505
-- Name: contatokey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY contato
    ADD CONSTRAINT contatokey PRIMARY KEY (id);


--
-- TOC entry 1811 (class 2606 OID 17668)
-- Dependencies: 1509 1509
-- Name: cpf_PK; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "cpf_PK" UNIQUE (cpf);


--
-- TOC entry 1813 (class 2606 OID 17670)
-- Dependencies: 1509 1509
-- Name: email_unico; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT email_unico UNIQUE (email);


--
-- TOC entry 1815 (class 2606 OID 17672)
-- Dependencies: 1509 1509
-- Name: pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pkey PRIMARY KEY (id);


--
-- TOC entry 1809 (class 2606 OID 17674)
-- Dependencies: 1507 1507
-- Name: planos_pacotes_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY planos
    ADD CONSTRAINT planos_pacotes_pkey PRIMARY KEY (id);


--
-- TOC entry 1803 (class 1259 OID 17675)
-- Dependencies: 1503
-- Name: boletos_gerados_FKIndex1; Type: INDEX; Schema: public; Owner: -; Tablespace: 
--

CREATE INDEX "boletos_gerados_FKIndex1" ON boletos_gerados USING btree (usuario_id_fk);


--
-- TOC entry 1816 (class 2606 OID 17676)
-- Dependencies: 1503 1509 1814
-- Name: boletos_gerados_usuario_id_fk_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY boletos_gerados
    ADD CONSTRAINT boletos_gerados_usuario_id_fk_fkey FOREIGN KEY (usuario_id_fk) REFERENCES usuario(id) ON DELETE CASCADE;


--
-- TOC entry 1817 (class 2606 OID 17681)
-- Dependencies: 1509 1507 1808
-- Name: planos_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT planos_fk FOREIGN KEY (planos_id_fk) REFERENCES planos(id) ON UPDATE SET NULL ON DELETE SET NULL;


--
-- TOC entry 1826 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-06-11 16:51:37

--
-- PostgreSQL database dump complete
--

