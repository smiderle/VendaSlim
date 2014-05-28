--
-- PostgreSQL database cluster dump
--

-- Started on 2013-09-04 19:39:05

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION PASSWORD 'md52a29a4f7eb0a98abca0992ca3fb555b6';
CREATE ROLE root;
ALTER ROLE root WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION PASSWORD 'md5b4b8daf4b8ea9d39568719e1e320076f' VALID UNTIL 'infinity';






--
-- Database creation
--

CREATE DATABASE "VENDASLIMDB" WITH TEMPLATE = template0 OWNER = postgres CONNECTION LIMIT = 10;
CREATE DATABASE "VENDASLIMDBT" WITH TEMPLATE = template0 OWNER = postgres;
CREATE DATABASE droidws WITH TEMPLATE = template0 OWNER = postgres;
REVOKE ALL ON DATABASE template1 FROM PUBLIC;
REVOKE ALL ON DATABASE template1 FROM postgres;
GRANT ALL ON DATABASE template1 TO postgres;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


\connect "VENDASLIMDB"

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-09-04 19:39:05

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 182 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 182
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 41144)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE empresa (
    idempresa integer NOT NULL,
    fone character(20),
    razaosocial character(200),
    nomefantasia character(200)
);


ALTER TABLE public.empresa OWNER TO postgres;

--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 169
-- Name: TABLE empresa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE empresa IS 'Dados da empresa, Cliente VendaSlim';


--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 169
-- Name: COLUMN empresa.fone; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN empresa.fone IS 'Telefone';


--
-- TOC entry 168 (class 1259 OID 41142)
-- Name: empresa_idempresa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE empresa_idempresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresa_idempresa_seq OWNER TO postgres;

--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 168
-- Name: empresa_idempresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresa_idempresa_seq OWNED BY empresa.idempresa;


--
-- TOC entry 170 (class 1259 OID 41153)
-- Name: filial; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE filial (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    nomefantasia character varying(100) NOT NULL,
    razaosocial character varying(120),
    fone character varying(20)
);


ALTER TABLE public.filial OWNER TO postgres;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 170
-- Name: TABLE filial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE filial IS 'Dados da filial da empresa do cliente.';


--
-- TOC entry 171 (class 1259 OID 41164)
-- Name: gruprep; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gruprep (
    idgrupo integer NOT NULL,
    descricao character varying(30),
    idempresa integer NOT NULL,
    idfilial integer,
    descmax double precision
);


ALTER TABLE public.gruprep OWNER TO postgres;

--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 171
-- Name: TABLE gruprep; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE gruprep IS 'Grupo dos representantes';


--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.descmax IS 'Desonto maximo permitido para o grupo de usuarios';


--
-- TOC entry 181 (class 1259 OID 49341)
-- Name: gruprepparcela; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gruprepparcela (
    idempresa integer,
    idfilial integer,
    idgrupo integer,
    idparcelamento integer
);


ALTER TABLE public.gruprepparcela OWNER TO postgres;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 181
-- Name: TABLE gruprepparcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE gruprepparcela IS 'Tabela que armazena os parcelamentos para o grupo de representantes';


--
-- TOC entry 179 (class 1259 OID 49296)
-- Name: grupreptabpreco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grupreptabpreco (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idgrupo integer NOT NULL,
    idtabela integer NOT NULL
);


ALTER TABLE public.grupreptabpreco OWNER TO postgres;

--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 179
-- Name: TABLE grupreptabpreco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE grupreptabpreco IS 'Tabelas de preço utilizadas pelo grupo de representatnes';


--
-- TOC entry 173 (class 1259 OID 41233)
-- Name: menuaplicacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE menuaplicacao (
    idmenu integer NOT NULL,
    icon character varying(20),
    label character varying(40),
    ordem integer,
    url character varying(50),
    menupai integer,
    separador boolean
);


ALTER TABLE public.menuaplicacao OWNER TO postgres;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.icon; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.icon IS 'icone do menu';


--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.label; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.label IS 'Descrição que sera mostrada no menu';


--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.ordem; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.ordem IS 'ordem de posicionamento do menu';


--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.url; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.url IS 'url  do menu';


--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.menupai; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.menupai IS 'O menu pai onde esse menu se encontra';


--
-- TOC entry 178 (class 1259 OID 49273)
-- Name: parcelamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE parcelamento (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idparcelamento integer NOT NULL,
    descricao character varying(40),
    carencia integer,
    diasentreparcela integer,
    nroparcela integer,
    percentual double precision,
    ativo boolean
);


ALTER TABLE public.parcelamento OWNER TO postgres;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.idfilial IS '
';


--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.diasentreparcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.diasentreparcela IS 'Dias entre as parcelas';


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.nroparcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.nroparcela IS 'Numeros de parcelas';


--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.percentual; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.percentual IS 'Percentual de acrescimo';


--
-- TOC entry 176 (class 1259 OID 41328)
-- Name: representante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE representante (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idrepresentante integer NOT NULL,
    idusuario integer,
    nome character varying(80),
    login character varying(20),
    senha character varying(15),
    descmax double precision,
    ativo integer,
    fone character varying(15),
    fone2 character varying(15),
    rua character varying(80),
    bairro character varying(80),
    cep character varying(15),
    numero character varying(8),
    observacao character varying(100)
);


ALTER TABLE public.representante OWNER TO postgres;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.idusuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.idusuario IS 'Código interno do usuario  para acesso ao VendaSlim Monitor';


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.login; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.login IS 'Login utilizado para acessar o sistema no Smartphone';


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.senha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.senha IS 'Senha para acessar o sistema no smartphone
';


--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.descmax IS 'Desconto maximo no pedido permitido para este grupo';


--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.ativo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.ativo IS 'Usuario ativo 1 - Ativo 0 - Inativo';


--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.fone; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.fone IS 'Telefone';


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.fone2; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.fone2 IS 'Telefone 2';


--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.rua; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.rua IS 'Rua';


--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.bairro; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.bairro IS 'Bairro';


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.numero; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.numero IS 'Numero';


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.observacao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.observacao IS 'Observações';


--
-- TOC entry 180 (class 1259 OID 49319)
-- Name: reptabpreco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reptabpreco (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idrepresentante integer NOT NULL,
    idtabela integer NOT NULL
);


ALTER TABLE public.reptabpreco OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 180
-- Name: TABLE reptabpreco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE reptabpreco IS 'Tabela que armazena os tabelas de preços do representante';


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN reptabpreco.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN reptabpreco.idfilial IS '
';


--
-- TOC entry 177 (class 1259 OID 49258)
-- Name: tabpreco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tabpreco (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idtabela integer NOT NULL,
    descricao character varying(40),
    percentual double precision,
    acrescimo boolean,
    ativo boolean DEFAULT true
);


ALTER TABLE public.tabpreco OWNER TO postgres;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 177
-- Name: COLUMN tabpreco.acrescimo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN tabpreco.acrescimo IS 'Se o percentual da tabela de preço é de ACRESCIMO ou DESCONTO. Para ACRESCIMO definir como true';


--
-- TOC entry 175 (class 1259 OID 41315)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    idusuario integer NOT NULL,
    idempresa integer,
    idfilial integer,
    login character varying(10) NOT NULL,
    senha character varying(10) NOT NULL,
    email character varying(80),
    idgrupo integer,
    dtalteracao integer,
    dtcadastro integer,
    sexo character(1),
    ativo boolean,
    fone character varying(15),
    nome character varying(50),
    dthrultimoacesso integer,
    idrepresentante integer
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 175
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE usuario IS 'USUARIOS DO SISTEMA';


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.idusuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.idusuario IS 'PK DA TABELA USUARIO. ';


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.email; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.email IS 'Email do usuário';


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.idgrupo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.idgrupo IS 'grupo do usuario';


--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.fone; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.fone IS 'Telefone';


--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.dthrultimoacesso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.dthrultimoacesso IS 'Data e Hora do ultimo acesso ao sistema';


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.idrepresentante; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.idrepresentante IS 'Código do representante';


--
-- TOC entry 174 (class 1259 OID 41313)
-- Name: usuario_idusuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_idusuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_idusuario_seq OWNER TO postgres;

--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 174
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 172 (class 1259 OID 41195)
-- Name: usuariopermissao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuariopermissao (
    idusuario integer NOT NULL,
    permissao character varying(20) NOT NULL
);


ALTER TABLE public.usuariopermissao OWNER TO postgres;

--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN usuariopermissao.permissao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuariopermissao.permissao IS 'PERMISSÃO DO USUARIO PARA ACESSAR AS PAGINAS';


--
-- TOC entry 1963 (class 2604 OID 41147)
-- Name: idempresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN idempresa SET DEFAULT nextval('empresa_idempresa_seq'::regclass);


--
-- TOC entry 1964 (class 2604 OID 41318)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2018 (class 0 OID 41144)
-- Dependencies: 169
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY empresa (idempresa, fone, razaosocial, nomefantasia) FROM stdin;
1	322465010           	EMPRESA TESTE RAZÃO SOCIAL                                                                                                                                                                              	TESTE NOME FANTASIA                                                                                                                                                                                     
\.


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 168
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 1, false);


--
-- TOC entry 2019 (class 0 OID 41153)
-- Dependencies: 170
-- Data for Name: filial; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY filial (idempresa, idfilial, nomefantasia, razaosocial, fone) FROM stdin;
1	1	FILIAL 1	RAZÃO 1	5555555
1	2	FILIAL 2	RAZÃO 2	3226650
1	3	FILIAL 3	RAZAO 3	2313213
\.


--
-- TOC entry 2020 (class 0 OID 41164)
-- Dependencies: 171
-- Data for Name: gruprep; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gruprep (idgrupo, descricao, idempresa, idfilial, descmax) FROM stdin;
1	VENDEDORES	1	1	50
2	NORTE	1	1	10
3	SUL	1	1	10
32	teste	1	1	10
4	Carros	1	1	3
5	PARALELO	1	1	6
6	DIA	1	1	30
15	PROMOCOES	1	1	10
22	TESTANDO	1	1	1
33	AÇOUGUE	1	1	10
\.


--
-- TOC entry 2030 (class 0 OID 49341)
-- Dependencies: 181
-- Data for Name: gruprepparcela; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gruprepparcela (idempresa, idfilial, idgrupo, idparcelamento) FROM stdin;
\.


--
-- TOC entry 2028 (class 0 OID 49296)
-- Dependencies: 179
-- Data for Name: grupreptabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupreptabpreco (idempresa, idfilial, idgrupo, idtabela) FROM stdin;
1	1	1	1
1	1	2	2
\.


--
-- TOC entry 2022 (class 0 OID 41233)
-- Dependencies: 173
-- Data for Name: menuaplicacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY menuaplicacao (idmenu, icon, label, ordem, url, menupai, separador) FROM stdin;
16	ui-icon-contact	Sobre	5	\N	\N	f
17	ui-icon-close	Sair	6	/j_spring_security_logout	\N	f
1	ui-icon-document	Cadastros	1	\N	\N	f
3	\N	Produto	2	\N	1	f
20	\N	Representantes	2	\N	18	f
4	ui-icon-pencil	Relatorios	2	\N	\N	f
19	\N	Grupo de Represent	1	/admin/lista_representante_grupo.jsf	18	f
18	\N	Representante	3	\N	1	f
2	ui-icon-contact	Usuário	1	\N	1	f
7	\N	Usuários	1	/restrito/lista/lista_usuarios.jsf	2	f
8	\N	Grupo de Produtos	1	\N	3	f
9	\N	Produtos	2	\N	3	f
10	\N	Tabela de Preço	3	\N	3	f
11	\N	Consultas	3	\N	\N	f
14	\N	Configurações	4	\N	\N	f
13	\N	Metas	2	\N	11	f
12	\N	Vendas	1	\N	11	f
15	\N	Smartphones	1	\N	14	f
\.


--
-- TOC entry 2027 (class 0 OID 49273)
-- Dependencies: 178
-- Data for Name: parcelamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY parcelamento (idempresa, idfilial, idparcelamento, descricao, carencia, diasentreparcela, nroparcela, percentual, ativo) FROM stdin;
1	1	1	30x60x90	30	30	3	10	t
1	1	2	15 DIAS	15	15	1	1	t
\.


--
-- TOC entry 2025 (class 0 OID 41328)
-- Dependencies: 176
-- Data for Name: representante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY representante (idempresa, idfilial, idrepresentante, idusuario, nome, login, senha, descmax, ativo, fone, fone2, rua, bairro, cep, numero, observacao) FROM stdin;
1	1	2	\N	VENDEDOR	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	1	1	4	VENDEDOR CALOS	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	2	1	\N	REPRESENTANTE COMERCIAL	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	3	1	5	REPRE 3	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
1	3	2	\N	TesteRepre 3	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 2029 (class 0 OID 49319)
-- Dependencies: 180
-- Data for Name: reptabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY reptabpreco (idempresa, idfilial, idrepresentante, idtabela) FROM stdin;
\.


--
-- TOC entry 2026 (class 0 OID 49258)
-- Dependencies: 177
-- Data for Name: tabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tabpreco (idempresa, idfilial, idtabela, descricao, percentual, acrescimo, ativo) FROM stdin;
1	1	1	TABELA AVISTA	10	f	t
1	1	2	A PRAZO	20	t	t
\.


--
-- TOC entry 2024 (class 0 OID 41315)
-- Dependencies: 175
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (idusuario, idempresa, idfilial, login, senha, email, idgrupo, dtalteracao, dtcadastro, sexo, ativo, fone, nome, dthrultimoacesso, idrepresentante) FROM stdin;
71	1	3	LADAIR2	1	1	\N	\N	\N	\N	t	1	1	\N	\N
72	1	1	LALA	1	1	\N	\N	\N	\N	t	1	1	\N	\N
73	1	2	CAROL	1	carol@gmail.com	\N	\N	\N	\N	t	32246501	CAROLINE PELECHATI	\N	\N
74	1	1	TESTE	1	1	\N	\N	\N	\N	t	1	1	\N	\N
5	1	1	LADA	1	\N	\N	\N	\N	\N	t	\N	\N	\N	1
68	1	1	LADAIR	123	1	\N	\N	\N	\N	t	1	1	\N	\N
69	1	1	LADAI	1	1	\N	\N	\N	\N	t	1	1	\N	\N
70	1	1	LA	1	1	\N	\N	\N	\N	t	1	1	\N	\N
\.


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 174
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 74, true);


--
-- TOC entry 2021 (class 0 OID 41195)
-- Dependencies: 172
-- Data for Name: usuariopermissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuariopermissao (idusuario, permissao) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
5	"ROLE_ADMIN"
5	ROLE_ADMIN
5	ROLE_USER
\.


--
-- TOC entry 1997 (class 2606 OID 49318)
-- Name: PK_GRUPREPRABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "PK_GRUPREPRABPRECO" PRIMARY KEY (idempresa, idfilial, idgrupo, idtabela);


--
-- TOC entry 1976 (class 2606 OID 41250)
-- Name: PK_IDMENU; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY menuaplicacao
    ADD CONSTRAINT "PK_IDMENU" PRIMARY KEY (idmenu);


--
-- TOC entry 1980 (class 2606 OID 41342)
-- Name: PK_IDUSUARIO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "PK_IDUSUARIO" PRIMARY KEY (idusuario);


--
-- TOC entry 1990 (class 2606 OID 49283)
-- Name: PK_PARCELAMENTO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "PK_PARCELAMENTO" PRIMARY KEY (idempresa, idfilial, idparcelamento);


--
-- TOC entry 1984 (class 2606 OID 41332)
-- Name: PK_REPRESENTANTE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY representante
    ADD CONSTRAINT "PK_REPRESENTANTE" PRIMARY KEY (idempresa, idfilial, idrepresentante);


--
-- TOC entry 2001 (class 2606 OID 49340)
-- Name: PK_REPTABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "PK_REPTABPRECO" PRIMARY KEY (idempresa, idfilial, idrepresentante, idtabela);


--
-- TOC entry 1986 (class 2606 OID 49262)
-- Name: PK_TABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "PK_TABPRECO" PRIMARY KEY (idempresa, idfilial, idtabela);


--
-- TOC entry 1988 (class 2606 OID 49290)
-- Name: UK_FILIAL_DESCRICAO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "UK_FILIAL_DESCRICAO" UNIQUE (idempresa, idfilial, descricao);


--
-- TOC entry 1992 (class 2606 OID 49292)
-- Name: UK_PARCELAMENTO_DESCRICAO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "UK_PARCELAMENTO_DESCRICAO" UNIQUE (idempresa, idfilial, descricao);


--
-- TOC entry 1982 (class 2606 OID 41322)
-- Name: UK_USUARIO_LOGIN; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "UK_USUARIO_LOGIN" UNIQUE (login);


--
-- TOC entry 1967 (class 2606 OID 41152)
-- Name: pk_idempresa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT pk_idempresa PRIMARY KEY (idempresa);


--
-- TOC entry 1970 (class 2606 OID 41157)
-- Name: pk_idempresa_idfilial; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filial
    ADD CONSTRAINT pk_idempresa_idfilial PRIMARY KEY (idempresa, idfilial);


--
-- TOC entry 1972 (class 2606 OID 41168)
-- Name: pk_idempresa_idusuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gruprep
    ADD CONSTRAINT pk_idempresa_idusuario PRIMARY KEY (idempresa, idgrupo);


--
-- TOC entry 1974 (class 2606 OID 41228)
-- Name: pk_usuariopermisao; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuariopermissao
    ADD CONSTRAINT pk_usuariopermisao PRIMARY KEY (idusuario, permissao);


--
-- TOC entry 2002 (class 1259 OID 49349)
-- Name: FKI_FILIAL_GRUPREPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_GRUPREPPARCELA" ON gruprepparcela USING btree (idempresa, idfilial);


--
-- TOC entry 1993 (class 1259 OID 49304)
-- Name: FKI_FILIAL_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idfilial);


--
-- TOC entry 1994 (class 1259 OID 49316)
-- Name: FKI_GRUPREP_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_GRUPREP_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idgrupo);


--
-- TOC entry 1998 (class 1259 OID 49332)
-- Name: FKI_REPRESENTANTE_REPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTE_REPTABPRECO" ON reptabpreco USING btree (idempresa, idfilial, idrepresentante);


--
-- TOC entry 1995 (class 1259 OID 49310)
-- Name: FKI_TABELAPRECO_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_TABELAPRECO_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idfilial, idtabela);


--
-- TOC entry 1999 (class 1259 OID 49338)
-- Name: FKI_TABPRECO_REPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_TABPRECO_REPTABPRECO" ON reptabpreco USING btree (idempresa, idfilial, idtabela);


--
-- TOC entry 1977 (class 1259 OID 41598)
-- Name: FKI_USUARIO_REPRESENTANTE; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_REPRESENTANTE" ON usuario USING btree (idempresa, idfilial, idrepresentante);


--
-- TOC entry 1978 (class 1259 OID 41577)
-- Name: FKI_USUARIO_USUARIOGRUPO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_USUARIOGRUPO" ON usuario USING btree (idempresa, idgrupo);


--
-- TOC entry 1968 (class 1259 OID 41163)
-- Name: fki_filial_empresa; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_filial_empresa ON filial USING btree (idempresa);


--
-- TOC entry 2016 (class 2606 OID 49344)
-- Name: FK_FILIAL_GRUPREPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprepparcela
    ADD CONSTRAINT "FK_FILIAL_GRUPREPPARCELA" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2010 (class 2606 OID 49299)
-- Name: FK_FILIAL_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_FILIAL_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2009 (class 2606 OID 49284)
-- Name: FK_FILIAL_PARCELAMENTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "FK_FILIAL_PARCELAMENTO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2007 (class 2606 OID 41354)
-- Name: FK_FILIAL_REPRESENTANTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY representante
    ADD CONSTRAINT "FK_FILIAL_REPRESENTANTE" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2013 (class 2606 OID 49322)
-- Name: FK_FILIAL_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_FILIAL_REPTABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2008 (class 2606 OID 49263)
-- Name: FK_FILIAL_TABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "FK_FILIAL_TABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2012 (class 2606 OID 49311)
-- Name: FK_GRUPREP_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_GRUPREP_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idgrupo) REFERENCES gruprep(idempresa, idgrupo);


--
-- TOC entry 2014 (class 2606 OID 49327)
-- Name: FK_REPRESENTANTE_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "FK_REPRESENTANTE_REPTABPRECO" FOREIGN KEY (idempresa, idfilial, idrepresentante) REFERENCES representante(idempresa, idfilial, idrepresentante);


--
-- TOC entry 2011 (class 2606 OID 49305)
-- Name: FK_TABELAPRECO_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_TABELAPRECO_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idfilial, idtabela) REFERENCES tabpreco(idempresa, idfilial, idtabela);


--
-- TOC entry 2015 (class 2606 OID 49333)
-- Name: FK_TABPRECO_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "FK_TABPRECO_REPTABPRECO" FOREIGN KEY (idempresa, idfilial, idtabela) REFERENCES tabpreco(idempresa, idfilial, idtabela);


--
-- TOC entry 2006 (class 2606 OID 41593)
-- Name: FK_USUARIO_REPRESENTANTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "FK_USUARIO_REPRESENTANTE" FOREIGN KEY (idempresa, idfilial, idrepresentante) REFERENCES representante(idempresa, idfilial, idrepresentante);


--
-- TOC entry 2003 (class 2606 OID 41158)
-- Name: fk_filial_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filial
    ADD CONSTRAINT fk_filial_empresa FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2004 (class 2606 OID 41578)
-- Name: fk_idempresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprep
    ADD CONSTRAINT fk_idempresa FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2005 (class 2606 OID 41323)
-- Name: fk_idfilial; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_idfilial FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-04 19:39:05

--
-- PostgreSQL database dump complete
--

\connect "VENDASLIMDBT"

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-09-04 19:39:05

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 169 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1920 (class 0 OID 0)
-- Dependencies: 169
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 168 (class 1259 OID 41508)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO root;

--
-- TOC entry 1921 (class 0 OID 0)
-- Dependencies: 168
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 1919 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-04 19:39:06

--
-- PostgreSQL database dump complete
--

\connect droidws

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-09-04 19:39:06

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 171 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1932 (class 0 OID 0)
-- Dependencies: 171
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 170 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 1933 (class 0 OID 0)
-- Dependencies: 170
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 16397)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    id integer NOT NULL,
    nome text,
    cpf text NOT NULL,
    endereco text
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 16395)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 1934 (class 0 OID 0)
-- Dependencies: 168
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_id_seq OWNED BY cliente.id;


--
-- TOC entry 1918 (class 2604 OID 16400)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN id SET DEFAULT nextval('cliente_id_seq'::regclass);


--
-- TOC entry 1924 (class 0 OID 16397)
-- Dependencies: 169
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (id, nome, cpf, endereco) FROM stdin;
5	LADAIR	23136456	WENCESLAU BRAZ
6	CARLOS	123456	WENCESLAU BRAZ
7	MAICO	1	WENCESLAU BRAZ
3	Jorjão	98765421	Rua do lado, 33
\.


--
-- TOC entry 1935 (class 0 OID 0)
-- Dependencies: 168
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_id_seq', 3, true);


--
-- TOC entry 1920 (class 2606 OID 16407)
-- Name: cpf; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cpf UNIQUE (cpf);


--
-- TOC entry 1922 (class 2606 OID 16405)
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 1931 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-04 19:39:07

--
-- PostgreSQL database dump complete
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-09-04 19:39:07

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1916 (class 1262 OID 12002)
-- Dependencies: 1915
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 169 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1919 (class 0 OID 0)
-- Dependencies: 169
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 168 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 1920 (class 0 OID 0)
-- Dependencies: 168
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- TOC entry 1918 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-04 19:39:07

--
-- PostgreSQL database dump complete
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-09-04 19:39:07

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1915 (class 1262 OID 1)
-- Dependencies: 1914
-- Name: template1; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- TOC entry 168 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1918 (class 0 OID 0)
-- Dependencies: 168
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1917 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-04 19:39:08

--
-- PostgreSQL database dump complete
--

-- Completed on 2013-09-04 19:39:08

--
-- PostgreSQL database cluster dump complete
--

