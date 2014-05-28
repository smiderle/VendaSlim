--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2014-03-24 19:49:43

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 201 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 201
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 214 (class 1255 OID 74432)
-- Name: atualiza_estoque(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION atualiza_estoque() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN               
	UPDATE produto set quantidade  = (quantidade -  NEW.quantidade) , dthralteracao = now() where idempresa = NEW.idempresa and idfilial = NEW.idfilial and idproduto = NEW.idproduto;        
         RETURN NEW;
    END;
$$;


ALTER FUNCTION public.atualiza_estoque() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 57503)
-- Name: cidade; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cidade (
    idcidade integer NOT NULL,
    nome character varying,
    codigoibge integer,
    idestado integer,
    populacao_2010 integer,
    densidade_demo numeric,
    gentilico character varying(250),
    area numeric
);


ALTER TABLE public.cidade OWNER TO postgres;

--
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 183
-- Name: TABLE cidade; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE cidade IS '-- id               ...
-- nome             Nome da Cidade
-- codigo_ibge      Código do IBGE referente a cidade
-- populacao_2012   População em 2010, segundo IBGE
-- densidade_demo   População por KM quadrado
-- area             Área do município ex: "3916.507" - 3.916,507 KM Quadrados

Fonte: https://gist.github.com/manfe/3975938';


--
-- TOC entry 190 (class 1259 OID 74001)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idcliente integer NOT NULL,
    nome character varying(60),
    apelido character varying(30),
    inscriestad character(15),
    tipopessoa integer,
    cpfcnpj character varying(15),
    contato character varying(40),
    fonecomercial character varying(20),
    foneresidencial character varying(20),
    celular character varying(20),
    fax character varying(20),
    cep character varying(10),
    complemento character varying(60),
    observacao character varying(500),
    dthrcadastro timestamp with time zone DEFAULT now(),
    dthralteracao timestamp with time zone DEFAULT now(),
    rua character varying(60),
    bairro character varying(60),
    numero character varying(8),
    email character varying(40),
    cidade integer,
    inativo boolean,
    dtnascimento date,
    tabpreco integer,
    parcelamento integer,
    descmax double precision,
    desconto_padrao double precision,
    limite_credito double precision,
    representante_padrao integer
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN cliente.tabpreco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN cliente.tabpreco IS 'Tabela de Preco padrão';


--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN cliente.parcelamento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN cliente.parcelamento IS 'Parcelamento Padrão';


--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN cliente.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN cliente.descmax IS 'Desconto Maximo';


--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN cliente.desconto_padrao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN cliente.desconto_padrao IS 'Desconto padrão deste cliente. Será sugerido este desconto na venda.';


--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN cliente.representante_padrao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN cliente.representante_padrao IS 'Representante Padrão';


--
-- TOC entry 200 (class 1259 OID 74436)
-- Name: devices; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE devices (
    id integer NOT NULL,
    serial character varying(70),
    dthrcadastro timestamp with time zone DEFAULT now(),
    dthralteracao time with time zone,
    versao_android character varying,
    email character varying
);


ALTER TABLE public.devices OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 74434)
-- Name: devices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE devices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.devices_id_seq OWNER TO postgres;

--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 199
-- Name: devices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE devices_id_seq OWNED BY devices.id;


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
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 169
-- Name: TABLE empresa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE empresa IS 'Dados da empresa, Cliente VendaSlim';


--
-- TOC entry 2264 (class 0 OID 0)
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
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 168
-- Name: empresa_idempresa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE empresa_idempresa_seq OWNED BY empresa.idempresa;


--
-- TOC entry 184 (class 1259 OID 57509)
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    idestado integer NOT NULL,
    nome character varying,
    uf character varying
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 41153)
-- Name: filial; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE filial (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    nomefantasia character varying(100) NOT NULL,
    razaosocial character varying(120),
    fone character varying(20),
    rua character varying(80),
    bairro character varying(80),
    numero character varying(6),
    cep character varying(12),
    fax character varying(20),
    dthralteracao timestamp with time zone,
    website character varying(60),
    dthrcadastro timestamp with time zone DEFAULT now()
);


ALTER TABLE public.filial OWNER TO postgres;

--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 170
-- Name: TABLE filial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE filial IS 'Dados da filial da empresa do cliente.';


--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 170
-- Name: COLUMN filial.rua; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial.rua IS '
';


--
-- TOC entry 196 (class 1259 OID 74319)
-- Name: filial_mobile_config; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE filial_mobile_config (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    exibe_estoque boolean,
    estoque_negativo boolean,
    cadastrar_cliente boolean,
    email_pedido_cliente boolean,
    email_pedido_admin boolean,
    email_pedidos character varying(60),
    acao_titulo_vencido integer,
    acao_limite_credito integer,
    dthralteracao timestamp without time zone DEFAULT now(),
    dias_vencimento integer
);


ALTER TABLE public.filial_mobile_config OWNER TO postgres;

--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.exibe_estoque; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.exibe_estoque IS 'Mostar o estoque dos produtos.';


--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.estoque_negativo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.estoque_negativo IS 'Permite venda de produtos com estoque negativo';


--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.cadastrar_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.cadastrar_cliente IS 'Permite cadastrar Clientes';


--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.email_pedido_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.email_pedido_cliente IS 'Enviar email ao cliente da efetivação do pedido.';


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.email_pedido_admin; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.email_pedido_admin IS 'Enviar email de efetivação para a conta configurada';


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.email_pedidos; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.email_pedidos IS 'Email no qual sera enviado os pedidos efetuados.';


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.acao_titulo_vencido; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.acao_titulo_vencido IS 'Ação ao efetuar a venda quando o cliente possui titulos vencidos.';


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.acao_limite_credito; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.acao_limite_credito IS 'Ação quando o limite de crédito do cliente excedeu.';


--
-- TOC entry 2276 (class 0 OID 0)
-- Dependencies: 196
-- Name: COLUMN filial_mobile_config.dias_vencimento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN filial_mobile_config.dias_vencimento IS 'Quantos dias após o vencimento  é considerado titulo vencido. 0 para o dia do venciemeto, 1 para 1 dia após o vencimento';


--
-- TOC entry 187 (class 1259 OID 65754)
-- Name: grupprod; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grupprod (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idgrupo integer NOT NULL,
    descricao character varying(40),
    dthralteracao timestamp(6) with time zone,
    descmax double precision
);


ALTER TABLE public.grupprod OWNER TO postgres;

--
-- TOC entry 2277 (class 0 OID 0)
-- Dependencies: 187
-- Name: TABLE grupprod; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE grupprod IS 'GRUPO DE PRODUTOS';


--
-- TOC entry 2278 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN grupprod.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN grupprod.descmax IS 'Desconto maximo para o grupo';


--
-- TOC entry 171 (class 1259 OID 41164)
-- Name: gruprep; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gruprep (
    idgrupo integer NOT NULL,
    descricao character varying(30),
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    descmax double precision,
    comicaovenda double precision,
    visualizatodosclientes boolean,
    minvenda double precision,
    dthralteracao timestamp(6) with time zone,
    dthrcadastro timestamp(6) with time zone DEFAULT now(),
    grupoproduto character varying(1500)
);


ALTER TABLE public.gruprep OWNER TO postgres;

--
-- TOC entry 2279 (class 0 OID 0)
-- Dependencies: 171
-- Name: TABLE gruprep; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE gruprep IS 'Grupo dos representantes';


--
-- TOC entry 2280 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.descmax IS 'Desonto maximo permitido para o grupo de usuarios';


--
-- TOC entry 2281 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.comicaovenda; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.comicaovenda IS 'Comição de venda para o representante';


--
-- TOC entry 2282 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.visualizatodosclientes; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.visualizatodosclientes IS 'Visulaiza dotos os clientes ou somente os seus';


--
-- TOC entry 2283 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.minvenda; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.minvenda IS 'Valor Minimo para venda por pedido';


--
-- TOC entry 2284 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.dthrcadastro; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.dthrcadastro IS 'Data e Hora do cadastro';


--
-- TOC entry 2285 (class 0 OID 0)
-- Dependencies: 171
-- Name: COLUMN gruprep.grupoproduto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN gruprep.grupoproduto IS '"Grupos de Produtos" que o "Grupo de Representante" vendem.';


--
-- TOC entry 181 (class 1259 OID 49341)
-- Name: gruprepparcela; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE gruprepparcela (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idgrupo integer NOT NULL,
    idparcelamento integer NOT NULL,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.gruprepparcela OWNER TO postgres;

--
-- TOC entry 2286 (class 0 OID 0)
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
    idtabela integer NOT NULL,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.grupreptabpreco OWNER TO postgres;

--
-- TOC entry 2287 (class 0 OID 0)
-- Dependencies: 179
-- Name: TABLE grupreptabpreco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE grupreptabpreco IS 'Tabelas de preço utilizadas pelo grupo de representatnes';


--
-- TOC entry 195 (class 1259 OID 74284)
-- Name: mensagem; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mensagem (
    idempresa integer NOT NULL,
    representante_origem integer,
    representante_destino integer,
    mensagem character varying(400),
    dthrcadastro timestamp with time zone DEFAULT now(),
    usuario_origem integer,
    usuario_destino integer,
    idmensagem integer NOT NULL,
    titulo character varying(30)
);


ALTER TABLE public.mensagem OWNER TO postgres;

--
-- TOC entry 2288 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN mensagem.representante_origem; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN mensagem.representante_origem IS 'Representante que esta enviando a mensagem.';


--
-- TOC entry 2289 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN mensagem.representante_destino; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN mensagem.representante_destino IS 'Representante que recebera a mensagem.';


--
-- TOC entry 2290 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN mensagem.usuario_origem; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN mensagem.usuario_origem IS 'Usuario que esta enviando a mensagem.';


--
-- TOC entry 2291 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN mensagem.usuario_destino; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN mensagem.usuario_destino IS 'Usuario que recebera a mensagem.';


--
-- TOC entry 2292 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN mensagem.idmensagem; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN mensagem.idmensagem IS 'Identificador da Mensagem.';


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
-- TOC entry 2293 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.icon; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.icon IS 'icone do menu';


--
-- TOC entry 2294 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.label; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.label IS 'Descrição que sera mostrada no menu';


--
-- TOC entry 2295 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.ordem; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.ordem IS 'ordem de posicionamento do menu';


--
-- TOC entry 2296 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN menuaplicacao.url; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN menuaplicacao.url IS 'url  do menu';


--
-- TOC entry 2297 (class 0 OID 0)
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
    inativo boolean,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.parcelamento OWNER TO postgres;

--
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.idfilial IS '
';


--
-- TOC entry 2299 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.diasentreparcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.diasentreparcela IS 'Dias entre as parcelas';


--
-- TOC entry 2300 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.nroparcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.nroparcela IS 'Numeros de parcelas';


--
-- TOC entry 2301 (class 0 OID 0)
-- Dependencies: 178
-- Name: COLUMN parcelamento.percentual; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN parcelamento.percentual IS 'Percentual de acrescimo';


--
-- TOC entry 191 (class 1259 OID 74026)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idpedido integer NOT NULL,
    idcliente integer,
    dthremissao timestamp with time zone,
    idrepresentante integer,
    tabelapreco integer,
    parcelamento integer,
    valor_bruto double precision,
    valor_liquido double precision,
    desconto_total double precision,
    status integer,
    dthralteracao timestamp with time zone,
    observacao character varying
);


ALTER TABLE public.pedido OWNER TO postgres;

--
-- TOC entry 2302 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN pedido.valor_bruto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido.valor_bruto IS 'Valor total Bruto';


--
-- TOC entry 2303 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN pedido.valor_liquido; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido.valor_liquido IS 'Valor total liquido';


--
-- TOC entry 2304 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN pedido.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido.status IS 'Status do Pedido';


--
-- TOC entry 193 (class 1259 OID 74070)
-- Name: pedido_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido_item (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idpedido integer NOT NULL,
    idproduto integer NOT NULL,
    sequencia integer NOT NULL,
    quantidade double precision,
    precovenda double precision,
    desconto double precision
);


ALTER TABLE public.pedido_item OWNER TO postgres;

--
-- TOC entry 2305 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN pedido_item.idempresa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido_item.idempresa IS '
';


--
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 193
-- Name: COLUMN pedido_item.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido_item.idfilial IS '
';


--
-- TOC entry 194 (class 1259 OID 74092)
-- Name: pedido_pagamento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pedido_pagamento (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idpedido integer NOT NULL,
    sequencia integer NOT NULL,
    data_vencimento date,
    data_pagamento date,
    valor_pago double precision,
    valor_parcela double precision
);


ALTER TABLE public.pedido_pagamento OWNER TO postgres;

--
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pedido_pagamento.data_vencimento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido_pagamento.data_vencimento IS 'data de vencimento';


--
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pedido_pagamento.data_pagamento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido_pagamento.data_pagamento IS 'data de pagamento';


--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN pedido_pagamento.valor_parcela; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN pedido_pagamento.valor_parcela IS 'Valor da parcela';


--
-- TOC entry 188 (class 1259 OID 73931)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idproduto integer NOT NULL,
    idgrupo integer,
    descricao character varying(100),
    referencia character varying(60),
    unidade character varying(3),
    codbar character varying(30),
    quantidade double precision,
    descmax double precision,
    precovenda double precision,
    precopromocao double precision,
    promocao boolean,
    inativo boolean,
    dtcadastro timestamp without time zone DEFAULT now(),
    dthralteracao timestamp with time zone
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.idgrupo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.idgrupo IS 'Grupo de produtos';


--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.descricao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.descricao IS 'Descrição do Produto';


--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.codbar; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.codbar IS 'Código de barras';


--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.quantidade; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.quantidade IS 'Estoque atual';


--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.descmax IS 'Desconto maximo para o protudo';


--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.precopromocao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.precopromocao IS 'Preço de promoção';


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.promocao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.promocao IS 'true - Produto esta em promoção
fasle - Produto não esta em promoção';


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.inativo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.inativo IS 'Define se o produto esta inativo';


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN produto.dthralteracao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN produto.dthralteracao IS 'Data e Hora da ultima alteração';


--
-- TOC entry 185 (class 1259 OID 57544)
-- Name: repfilial; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE repfilial (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idrepresentante integer NOT NULL,
    idgrupo integer,
    descmax double precision,
    comicaovenda double precision,
    visualizatodosclientes boolean,
    minvenda double precision,
    grupoproduto character varying(1500),
    dthralteracao timestamp without time zone
);


ALTER TABLE public.repfilial OWNER TO postgres;

--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 185
-- Name: TABLE repfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE repfilial IS 'Filiais as quais o representante vende.';


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.idfilial IS '
';


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.idgrupo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.idgrupo IS 'Grupo do representante';


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.descmax; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.descmax IS 'Desconto maximo para este representante';


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.comicaovenda; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.comicaovenda IS 'Comição de Venda';


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.visualizatodosclientes; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.visualizatodosclientes IS 'Visualiza todos os clientes ou somente os cliente pertencente a ele.';


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.minvenda; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.minvenda IS 'Valor minimo permitido para a venda.';


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN repfilial.grupoproduto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN repfilial.grupoproduto IS 'Grupo de Produtos visualizados pelo representante.';


--
-- TOC entry 182 (class 1259 OID 49375)
-- Name: repparcela; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE repparcela (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idrepresentante integer NOT NULL,
    idparcelamento integer NOT NULL,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.repparcela OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 41328)
-- Name: representante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE representante (
    idempresa integer NOT NULL,
    idrepresentante integer NOT NULL,
    idusuario integer,
    nome character varying(80),
    login character varying(20),
    senha character varying(15),
    rua character varying(80),
    bairro character varying(80),
    cep character varying(15),
    numero character varying(8),
    observacao character varying(300),
    cpfcnpj character varying(14),
    placa character varying(9),
    veiculo character varying(20),
    contato character varying(20),
    fonecomercial character varying(12),
    foneresidencial character varying(12),
    celular character varying(12),
    fax character varying(12),
    complemento character varying(40),
    dthrcadastro timestamp with time zone DEFAULT now(),
    dthralteracao timestamp(6) with time zone,
    cidade integer,
    tipopessoa integer,
    email character varying(50),
    inscriestad character varying(20),
    inativo boolean NOT NULL,
    idcidade integer,
    dtnascimento date
);


ALTER TABLE public.representante OWNER TO postgres;

--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.idusuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.idusuario IS 'Código interno do usuario  para acesso ao VendaSlim Monitor';


--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.login; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.login IS 'Login utilizado para acessar o sistema no Smartphone';


--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.senha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.senha IS 'Senha para acessar o sistema no smartphone
';


--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.rua; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.rua IS 'Rua';


--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.bairro; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.bairro IS 'Bairro';


--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.numero; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.numero IS 'Numero';


--
-- TOC entry 2333 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.observacao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.observacao IS 'Observações';


--
-- TOC entry 2334 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.tipopessoa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.tipopessoa IS '1 para Fisica, 2 para juridica ';


--
-- TOC entry 2335 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.inscriestad; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.inscriestad IS 'Inscrição estadual, RG.';


--
-- TOC entry 2336 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.inativo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.inativo IS 'Inativo';


--
-- TOC entry 2337 (class 0 OID 0)
-- Dependencies: 176
-- Name: COLUMN representante.idcidade; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representante.idcidade IS 'Código da Cidade';


--
-- TOC entry 189 (class 1259 OID 73982)
-- Name: representanterota; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE representanterota (
    idempresa integer NOT NULL,
    idrepresentante integer NOT NULL,
    dthrrota timestamp(6) with time zone NOT NULL,
    latitude numeric,
    longitude numeric,
    hrinsert timestamp with time zone DEFAULT now()
);


ALTER TABLE public.representanterota OWNER TO postgres;

--
-- TOC entry 2338 (class 0 OID 0)
-- Dependencies: 189
-- Name: COLUMN representanterota.dthrrota; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN representanterota.dthrrota IS 'Data e Hora da marcação do ponto da  rota.';


--
-- TOC entry 180 (class 1259 OID 49319)
-- Name: reptabpreco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reptabpreco (
    idempresa integer NOT NULL,
    idfilial integer NOT NULL,
    idrepresentante integer NOT NULL,
    idtabela integer NOT NULL,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.reptabpreco OWNER TO postgres;

--
-- TOC entry 2339 (class 0 OID 0)
-- Dependencies: 180
-- Name: TABLE reptabpreco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE reptabpreco IS 'Tabela que armazena os tabelas de preços do representante';


--
-- TOC entry 2340 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN reptabpreco.idfilial; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN reptabpreco.idfilial IS '
';


--
-- TOC entry 198 (class 1259 OID 74394)
-- Name: sincronizacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sincronizacao (
    idsincronizacao integer NOT NULL,
    idfilial integer NOT NULL,
    idempresa integer NOT NULL,
    idrepresentante integer NOT NULL,
    dthrsincronizacao timestamp with time zone,
    tabela character varying(25),
    sinccompleta boolean
);


ALTER TABLE public.sincronizacao OWNER TO postgres;

--
-- TOC entry 2341 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE sincronizacao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE sincronizacao IS 'Armazena toas as sincronizações efetuadas pelos representantes.';


--
-- TOC entry 2342 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN sincronizacao.idempresa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN sincronizacao.idempresa IS '
';


--
-- TOC entry 2343 (class 0 OID 0)
-- Dependencies: 198
-- Name: COLUMN sincronizacao.sinccompleta; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN sincronizacao.sinccompleta IS 'Indica se a sincronização foi bem sucedida. Só será alterado o stados para true quando o smartphone receber os registros';


--
-- TOC entry 197 (class 1259 OID 74392)
-- Name: sincronizacao_idsincronizacao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sincronizacao_idsincronizacao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sincronizacao_idsincronizacao_seq OWNER TO postgres;

--
-- TOC entry 2344 (class 0 OID 0)
-- Dependencies: 197
-- Name: sincronizacao_idsincronizacao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sincronizacao_idsincronizacao_seq OWNED BY sincronizacao.idsincronizacao;


--
-- TOC entry 192 (class 1259 OID 74029)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE status (
    idstatus integer NOT NULL,
    descricao character varying(20)
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 2345 (class 0 OID 0)
-- Dependencies: 192
-- Name: TABLE status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE status IS 'Tabela que contem os status de aprovação';


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
    inativo boolean,
    dthralteracao timestamp with time zone
);


ALTER TABLE public.tabpreco OWNER TO postgres;

--
-- TOC entry 2346 (class 0 OID 0)
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
    idempresa integer NOT NULL,
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
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 175
-- Name: TABLE usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE usuario IS 'USUARIOS DO SISTEMA';


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.idusuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.idusuario IS 'PK DA TABELA USUARIO. ';


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.email; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.email IS 'Email do usuário';


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.idgrupo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.idgrupo IS 'grupo do usuario';


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.fone; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.fone IS 'Telefone';


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN usuario.dthrultimoacesso; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuario.dthrultimoacesso IS 'Data e Hora do ultimo acesso ao sistema';


--
-- TOC entry 2353 (class 0 OID 0)
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
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 174
-- Name: usuario_idusuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_idusuario_seq OWNED BY usuario.idusuario;


--
-- TOC entry 186 (class 1259 OID 65743)
-- Name: usuarioacesso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarioacesso (
    idusuario integer NOT NULL,
    dthracesso timestamp(6) with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.usuarioacesso OWNER TO postgres;

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
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN usuariopermissao.permissao; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN usuariopermissao.permissao IS 'PERMISSÃO DO USUARIO PARA ACESSAR AS PAGINAS';


--
-- TOC entry 2059 (class 2604 OID 74439)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY devices ALTER COLUMN id SET DEFAULT nextval('devices_id_seq'::regclass);


--
-- TOC entry 2046 (class 2604 OID 41147)
-- Name: idempresa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa ALTER COLUMN idempresa SET DEFAULT nextval('empresa_idempresa_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 74397)
-- Name: idsincronizacao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sincronizacao ALTER COLUMN idsincronizacao SET DEFAULT nextval('sincronizacao_idsincronizacao_seq'::regclass);


--
-- TOC entry 2049 (class 2604 OID 41318)
-- Name: idusuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN idusuario SET DEFAULT nextval('usuario_idusuario_seq'::regclass);


--
-- TOC entry 2230 (class 0 OID 57503)
-- Dependencies: 183
-- Data for Name: cidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cidade (idcidade, nome, codigoibge, idestado, populacao_2010, densidade_demo, gentilico, area) FROM stdin;
3	Brasiléia	120010	1	21398	5.46	brasileense	3916.507
5	Capixaba	120017	1	8798	5.17	capixabense	1702.581
7	Epitaciolândia	120025	1	15100	9.13	epitaciolandense	1654.773
8	Feijó	120030	1	32412	1.16	feijoense	27974.551
10	Mâncio Lima	120033	1	15206	2.79	mancio-limense	5453.042
12	Marechal Thaumaturgo	120035	1	14227	1.74	thaumaturguense	8191.728
14	Porto Acre	120080	1	14880	5.71	portoacrense	2604.725
15	Porto Walter	120039	1	9176	1.42	portowaltense	6443.851
17	Rodrigues Alves	120042	1	14389	4.68	rodriguesalvense	3076.99
19	Sena Madureira	120050	1	38029	1.6	sena-madureirense	23751.268
21	Tarauacá	120060	1	35590	1.76	tarauacaense	20171.019
22	Xapuri	120070	1	16091	3.01	xapuriense	5347.283
24	Anadia	270020	2	17424	91.96	anadiense	189.472
26	Atalaia	270040	2	44322	83.82	atalaiense	528.769
28	Barra De São Miguel	270060	2	7574	98.86	barrense	76.615
29	Batalha	270070	2	17076	53.21	batalhense	320.921
31	Belo Monte	270090	2	7030	21.04	belo-montense	334.145
33	Branquinha	270110	2	10583	63.63	branquinhense	166.322
34	Cacimbinhas	270120	2	10195	37.35	cacimbense	272.979
36	Campestre	270135	2	6598	99.39	camprestrense	66.385
38	Campo Grande	270150	2	9032	53.98	campo-grandense	167.32
40	Capela	270170	2	17077	70.39	capelense	242.617
41	Carneiros	270180	2	8290	73.32	carneirense	113.061
43	Coité Do Nóia	270200	2	10926	123.45	coitenense	88.509
45	Coqueiro Seco	270220	2	5526	139.09	coqueirense	39.729
47	Craíbas	270235	2	22641	83.44	craibense	271.332
49	Dois Riachos	270250	2	10880	77.45	riachense	140.474
50	Estrela De Alagoas	270255	2	17251	66.41	estrelense	259.767
52	Feliz Deserto	270270	2	4345	47.31	feliz-desertense	91.839
54	Girau Do Ponciano	270290	2	36600	73.11	ponciense	500.618
55	Ibateguara	270300	2	15149	57.1	ibateguarense	265.313
57	Igreja Nova	270320	2	23292	54.55	igreja-novense	426.976
59	Jacaré Dos Homens	270340	2	5413	38.03	jacarezeiro	142.34
61	Japaratinga	270360	2	7754	90.22	japaratinguense	85.948
63	Jequiá Da Praia	270375	2	12029	34.21	jequiaenses	351.611
64	Joaquim Gomes	270380	2	22575	75.68	juruquense	298.288
66	Junqueiro	270400	2	23836	98.66	junqueirense	241.592
68	Limoeiro De Anadia	270420	2	26992	85.48	limoeirense	315.777
70	Major Isidoro	270440	2	18897	41.63	isidorense	453.893
71	Mar Vermelho	270490	2	3652	39.23	mar-vermelhense	93.102
73	Maravilha	270460	2	10284	34.05	maravilhense	302.056
75	Maribondo	270480	2	13619	78.14	maribondense	174.28
77	Matriz De Camaragibe	270510	2	23785	108.12	matrizense	219.99
78	Messias	270520	2	15682	137.77	messiense	113.825
80	Monteirópolis	270540	2	6935	80.54	guaribense	86.103
82	Novo Lino	270560	2	12060	51.67	novo-linense	233.408
84	Olho D`Água Do Casado	270580	2	8491	26.29	casadense	322.944
85	Olho D`Água Grande	270590	2	4957	41.83	olho-grandense	118.509
87	Ouro Branco	270610	2	10912	53.29	ouro-branquense	204.771
89	Palmeira Dos Índios	270630	2	70368	155.44	palmeirense	452.703
91	Pariconha	270642	2	10264	39.7	pariconhense	258.524
92	Paripueira	270644	2	11347	122.05	paripueirense	92.972
94	Paulo Jacinto	270660	2	7426	62.69	paulo-jacintense	118.457
96	Piaçabuçu	270680	2	17203	71.68	piaçabuçuense	240.013
97	Pilar	270690	2	33305	133.37	pilarense	249.713
99	Piranhas	270710	2	23045	56.47	piranhense	408.105
101	Porto Calvo	270730	2	25708	83.49	porto-calvense	307.914
103	Porto Real Do Colégio	270750	2	19334	79.91	colegiense	241.939
104	Quebrangulo	270760	2	11480	35.89	quebrangulense	319.831
106	Roteiro	270780	2	6656	51.48	roteirense	129.289
108	Santana Do Ipanema	270800	2	44932	102.61	santanense	437.875
109	Santana Do Mundaú	270810	2	10961	48.76	mundauense	224.811
111	São José Da Laje	270830	2	22686	88.4	lajense	256.641
113	São Luís Do Quitunde	270850	2	32412	81.61	quitundense	397.173
114	São Miguel Dos Campos	270860	2	54577	151.27	miguelense	360.791
116	São Sebastião	270880	2	32010	101.59	salomeense	315.103
117	Satuba	270890	2	14603	342.57	satubense	42.628
119	Tanque D`Arca	270900	2	6122	47.27	tanquense	129.508
121	Teotônio Vilela	270915	2	41152	138.15	vilelano	297.88
122	Traipu	270920	2	25702	36.82	traipusense	697.963
124	Viçosa	270940	2	25407	74	viçosense	343.356
126	Amaturá	130006	3	9467	1.99	amaturaense	4758.748
2713	Princesa Isabel	251230	15	21283	57.84	princesense	367.973
129	Apuí	130014	3	18007	0.33	apuiense	54239.911
131	Autazes	130030	3	32135	4.23	autazense	7599.353
132	Barcelos	130040	3	25718	0.21	barcelense	122476.006
134	Benjamin Constant	130060	3	33411	3.8	benjamin-constantense	8793.422
136	Boa Vista Do Ramos	130068	3	14979	5.79	boa-vistense	2586.841
138	Borba	130080	3	34961	0.79	borbense	44251.701
140	Canutama	130090	3	12738	0.43	canutamense	29819.725
141	Carauari	130100	3	25774	1	carauariense	25767.709
143	Careiro Da Várzea	130115	3	23930	9.09	careirense-da-várzea	2631.141
145	Codajás	130130	3	23206	1.24	codajasense	18711.544
147	Envira	130150	3	16338	2.18	envirense	7499.426
149	Guajará	130165	3	13974	1.84	guajaraense	7579.603
150	Humaitá	130170	3	44227	1.34	humaitaense	33071.803
152	Iranduba	130185	3	40781	18.42	irandubense	2214.25
154	Itamarati	130195	3	8038	0.32	itamaratiense	25275.968
156	Japurá	130210	3	7326	0.13	japuraense	55791.877
158	Jutaí	130230	3	17992	0.26	jutaiense	69551.928
159	Lábrea	130240	3	37701	0.55	labrense	68233.961
161	Manaquiri	130255	3	22801	5.73	manaquirense	3975.766
163	Manicoré	130270	3	47017	0.97	manicoreense	48282.659
165	Maués	130290	3	52236	1.31	maueense	39989.873
166	Nhamundá	130300	3	18278	1.3	nhamundaense	14105.553
168	Novo Airão	130320	3	14723	0.39	novo-airãoense	37771.337
170	Parintins	130340	3	102033	17.14	parintinense	5952.378
171	Pauini	130350	3	18166	0.44	pauiniense	41610.271
173	Rio Preto Da Eva	130356	3	25719	4.42	rio-pretense	5813.216
175	Santo Antônio Do Içá	130370	3	24481	1.99	santoense	12307.205
177	São Paulo De Olivença	130390	3	31422	1.59	paulivense	19745.931
178	São Sebastião Do Uatumã	130395	3	10705	1	uatumaense	10741.06
179	Silves	130400	3	8444	2.25	silvense	3748.826
181	Tapauá	130410	3	19077	0.21	tapauense	89325.274
183	Tonantins	130423	3	17079	2.66	tonantinense	6432.682
185	Urucará	130430	3	17094	0.61	urucaraense	27903.372
187	Amapá	160010	4	8069	0.88	amapaense	9175.945
188	Calçoene	160020	4	9000	0.63	calçoenense	14269.299
190	Ferreira Gomes	160023	4	5802	1.15	ferreirense	5046.236
192	Laranjal Do Jari	160027	4	39942	1.29	laranjalense	30971.775
194	Mazagão	160040	4	17032	1.3	mazaganistas	13130.93
196	Pedra Branca Do Amaparí	160015	4	10772	1.13	pedrabrancanienses	9495.48
198	Pracuúba	160055	4	3793	0.77	pracuubenses	4956.454
199	Santana	160060	4	101262	64.11	santanenses	1579.6
201	Tartarugalzinho	160070	4	12563	1.87	tartarugalense	6709.632
203	Abaíra	290010	5	8316	15.68	abairense	530.494
204	Abaré	290020	5	17064	11.49	abareense	1484.961
206	Adustina	290035	5	15702	24.84	adustinense	632.14
208	Aiquara	290060	5	4602	28.82	aiquarense	159.691
210	Alcobaça	290080	5	21271	14.36	alcobacense	1481.265
211	Almadina	290090	5	6357	25.32	almadinense	251.11
213	Amélia Rodrigues	290110	5	25190	145.2	ameliense	173.484
215	Anagé	290120	5	25516	13.1	anageense	1947.358
217	Andorinha	290135	5	14414	11.55	andorinhense	1247.623
218	Angical	290140	5	14073	9.21	angicalense	1527.945
220	Antas	290160	5	17072	53.42	antense	319.602
222	Antônio Gonçalves	290180	5	11015	35.09	antônio-gonçalvense	313.937
224	Apuarema	290195	5	7459	48.17	apuaremense	154.857
225	Araças	290205	5	11561	23.73	araçaense	487.121
227	Araci	290210	5	51651	33.19	araciense	1556.133
229	Arataca	290225	5	10392	27.7	arataquense	375.205
231	Aurelino Leal	290240	5	13595	29.7	aurelinense	457.783
233	Baixa Grande	290260	5	20060	21.19	baixa-grandense	946.649
234	Banzaê	290265	5	11814	51.99	banzaêense	227.231
236	Barra Da Estiva	290280	5	21187	15.73	barrestivense	1346.632
238	Barra Do Mendes	290300	5	13987	9.08	barra-mendense	1540.645
239	Barra Do Rocha	290310	5	6313	30.3	barra-rochense	208.364
241	Barro Alto	290323	5	13612	32.67	barro-altino	416.676
243	Barro Preto	290330	5	6453	50.26	barro-pretense	128.38
245	Belo Campo	290350	5	16021	25.47	belo-campense	629.095
246	Biritinga	290360	5	14836	26.97	biritinguense	550.033
248	Boa Vista Do Tupim	290380	5	17991	6.4	tupinense	2811.146
250	Bom Jesus Da Serra	290395	5	10113	23.99	bom-jesuense	421.517
252	Bonito	290405	5	14834	20.42	bonitense	726.607
253	Boquira	290410	5	22037	14.86	boquirense	1482.704
255	Brejões	290430	5	14282	29.7	brejoense	480.832
2716	Quixabá	251260	15	1699	10.84	quixabense	156.682
258	Brumado	290460	5	64602	29.01	brumadense	2226.818
260	Buritirama	290475	5	19600	4.97	buritiramense	3942.193
262	Cabaceiras Do Paraguaçu	290485	5	17327	76.66	cabaceirense	226.022
264	Caculé	290500	5	22236	33.27	caculense	668.367
265	Caém	290510	5	10368	18.91	caenense	548.147
267	Caetité	290520	5	47515	19.45	caetiteense	2442.887
269	Cairu	290540	5	15374	33.35	cairuense	460.981
271	Camacan	290560	5	31472	50.22	camacaense	626.65
272	Camaçari	290570	5	242970	309.65	camaçariense	784.655
275	Campo Formoso	290600	5	66616	9.18	campo-formosense	7258.574
276	Canápolis	290610	5	9410	21.52	canapolense	437.212
278	Canavieiras	290630	5	32336	24.37	canavieirense	1326.954
279	Candeal	290640	5	8895	19.99	candealense	445.083
281	Candiba	290660	5	13210	31.6	candibense	417.975
283	Cansanção	290680	5	32908	24.47	cansançãoense	1344.82
285	Capela Do Alto Alegre	290685	5	11527	17.75	capelense	649.473
287	Caraíbas	290689	5	10222	12.69	caraibense	805.627
288	Caravelas	290690	5	21414	8.95	caravelense	2393.442
290	Carinhanha	290710	5	28380	10.37	carinhanhense	2736.874
292	Castro Alves	290730	5	25408	35.7	castro-alvense	711.727
293	Catolândia	290740	5	2612	4.06	catolandiano	642.584
295	Caturama	290755	5	8843	13.31	caturamense	664.553
297	Chorrochó	290770	5	10734	3.57	chorrochoense	3005.31
299	Cipó	290790	5	15755	122.79	cipoense	128.312
300	Coaraci	290800	5	20964	74.17	coaraciense	282.648
302	Conceição Da Feira	290820	5	20391	125.18	conceiçoense	162.892
304	Conceição Do Coité	290840	5	62040	61.06	coiteense	1015.984
306	Conde	290860	5	23620	24.48	condense	964.673
307	Condeúba	290870	5	16898	13.14	condeubense	1285.914
309	Coração De Maria	290890	5	22401	64.34	mariense	348.156
310	Cordeiros	290900	5	8168	15.25	cordeirense	535.484
312	Coronel João Sá	290920	5	17066	19.32	joão-saense	883.432
314	Cotegipe	290940	5	13636	3.25	cotegipano	4196.145
316	Crisópolis	290960	5	20046	32.99	crisopolense	607.662
317	Cristópolis	290970	5	13280	12.73	cristopolense	1043.1
319	Curaçá	290990	5	32168	5.29	curaçaense	6079.042
321	Dias D`Ávila	291005	5	66440	360.64	diasdavilense	184.229
322	Dom Basílio	291010	5	11355	16.78	dom-basiliense	676.894
324	Elísio Medrado	291030	5	7947	41.06	medradense	193.527
326	Entre Rios	291050	5	39872	32.81	entrerriense	1215.289
328	Esplanada	291060	5	32802	24.84	esplanadense	1320.739
329	Euclides Da Cunha	291070	5	56289	25.18	euclidense	2235.268
331	Fátima	291075	5	17652	49.11	fatimense	359.402
333	Feira De Santana	291080	5	556642	416.03	feirense	1337.988
334	Filadélfia	291085	5	16740	29.36	filadelfense	570.077
336	Floresta Azul	291100	5	10660	36.33	floresta-azulense	293.457
338	Gandu	291120	5	30336	124.76	ganduense	243.15
339	Gavião	291125	5	4561	12.33	gavionense	369.876
341	Glória	291140	5	15076	12.01	gloriense	1255.647
343	Governador Mangabeira	291160	5	19818	186.41	mangabeirense	106.316
344	Guajeru	291165	5	10412	11.12	guajeruense	936.082
346	Guaratinga	291180	5	22165	9.53	guaratinguense	2325.382
348	Iaçu	291190	5	25736	10.5	iaçuense	2451.485
350	Ibicaraí	291210	5	24272	104.65	ibicaraiense	231.938
352	Ibicuí	291230	5	15785	13.41	ibicuiense	1176.843
353	Ibipeba	291240	5	17008	12.29	ibipebense	1383.66
355	Ibiquera	291260	5	4866	5.15	ibiquerense	945.269
357	Ibirapuã	291280	5	7956	10.1	ibirapuense	787.738
359	Ibitiara	291300	5	15508	8.39	ibitiarense	1847.57
360	Ibititá	291310	5	17840	28.58	ibititaense	624.104
362	Ichu	291330	5	5255	41.16	ichuense	127.668
364	Igrapiúna	291345	5	13343	25.31	igrapiunense	527.212
366	Ilhéus	291360	5	184236	104.68	ilheuense	1760.004
368	Ipecaetá	291380	5	15331	41.45	ipecaetense	369.883
369	Ipiaú	291390	5	44390	166.05	ipiauense	267.327
371	Ipupiara	291410	5	9285	8.75	ipupiarense	1061.239
373	Iramaia	291430	5	11990	6.16	iramaense	1947.34
375	Irará	291450	5	27466	98.87	iraraense	277.791
376	Irecê	291460	5	66181	207.45	ireceense	319.028
378	Itaberaba	291470	5	61631	26.3	itaberabense	2343.549
380	Itacaré	291490	5	24318	32.96	itacareense	737.85
382	Itagi	291510	5	13051	50.36	itagiense	259.172
384	Itagimirim	291530	5	7110	8.47	itagimiriense	839.058
387	Itajuípe	291550	5	21081	74.11	itajuipense	284.474
389	Itamari	291570	5	7903	71.14	itamariense	111.088
391	Itanagra	291590	5	7598	15.49	itanagrense	490.526
392	Itanhém	291600	5	20216	13.81	itanhense	1463.83
394	Itapé	291620	5	10995	23.94	itapeense	459.369
396	Itapetinga	291640	5	68273	41.95	itapetinguense	1627.462
398	Itapitanga	291660	5	10207	24.99	itapitanguense	408.372
400	Itarantim	291680	5	18539	10.27	itarantinense	1805.121
401	Itatim	291685	5	14522	24.89	itatinhense	583.435
403	Itiúba	291700	5	36113	20.96	itiubense	1722.706
405	Ituaçu	291720	5	18127	14.9	ituaçuense	1216.253
407	Iuiú	291733	5	10900	7.34	iuiuense	1485.775
409	Jacaraci	291740	5	13651	11.05	jacaraciense	1235.622
410	Jacobina	291750	5	79247	33.58	jacobinense	2359.965
412	Jaguarari	291770	5	30343	12.35	jaguarariense	2456.548
414	Jandaíra	291790	5	10331	16.11	jandairense	641.203
416	Jeremoabo	291810	5	37680	8.09	jeremoabense	4656.094
417	Jiquiriçá	291820	5	14118	58.97	jiquiriçaense	239.403
420	Juazeiro	291840	5	197965	30.45	juazeirense	6500.679
421	Jucuruçu	291845	5	10290	7.36	jucuruçuense	1398.811
423	Jussari	291855	5	6474	18.14	jussariense	356.847
424	Jussiape	291860	5	8031	13.72	jussiapense	585.184
426	Lagoa Real	291875	5	13934	15.88	lagoa-realense	877.435
428	Lajedão	291890	5	3733	6.06	lajedãoense	615.518
430	Lajedo Do Tabocal	291905	5	8305	19.23	lagedense	431.947
431	Lamarão	291910	5	9560	54.84	lamarãoense	174.322
433	Lauro De Freitas	291920	5	163449	2.833	lauro-freitense	57.686
435	Licínio De Almeida	291940	5	12311	14.6	licínio-de-almeidense	843.374
437	Luís Eduardo Magalhães	291955	5	60105	15.25	luiseduardense	3941.139
438	Macajuba	291960	5	11229	17.27	macajubense	650.285
440	Macaúbas	291980	5	47051	15.71	macaubense	2994.135
442	Madre De Deus	291992	5	17376	539.58	madre-deusense	32.203
444	Maiquinique	292000	5	8782	17.85	maiquiniquense	491.978
446	Malhada	292020	5	16014	7.97	malhadense	2008.353
448	Manoel Vitorino	292040	5	14387	6.38	manoel-vitorinense	2254.42
449	Mansidão	292045	5	12592	3.96	mansidãoense	3177.42
451	Maragogipe	292060	5	42815	97.27	maragogipano	440.159
453	Marcionílio Souza	292080	5	10500	8.22	marcionilense	1277.171
455	Mata De São João	292100	5	40183	63.46	matense	633.189
456	Matina	292105	5	11145	14.37	matinense	775.727
458	Miguel Calmon	292120	5	26475	16.88	calmonense	1568.22
460	Mirangaba	292140	5	16279	9.59	mirangabense	1697.691
461	Mirante	292145	5	10507	9.9	mirantense	1061.005
463	Morpará	292160	5	8280	4.88	morparaense	1696.892
465	Mortugaba	292180	5	12477	20.38	mortugabense	612.191
467	Mucuri	292200	5	36026	20.23	mucuriense	1780.595
469	Mundo Novo	292210	5	24395	16.33	mundo-novense	1493.422
470	Muniz Ferreira	292220	5	7317	66.45	ferreirense	110.114
472	Muritiba	292230	5	28899	323.58	muritibano	89.31
473	Mutuípe	292240	5	21449	75.74	mutuipense	283.205
475	Nilo Peçanha	292260	5	12530	31.38	nilo-peçanhense	399.349
477	Nova Canaã	292270	5	16713	19.58	canaense	853.709
479	Nova Ibiá	292275	5	6648	37.19	nova-ibiaense	178.74
481	Nova Redenção	292285	5	8034	18.63	nova-redençoense	431.322
482	Nova Soure	292290	5	24136	25.4	nova-souriense	950.377
484	Novo Horizonte	292303	5	10673	17.52	novo-horizontino	609.188
486	Olindina	292310	5	24943	46	olindinense	542.198
488	Ouriçangas	292330	5	8298	53.5	ouriçanguense	155.089
489	Ourolândia	292335	5	16425	11.04	ourolandense	1487.743
491	Palmeiras	292350	5	8410	12.79	palmeirense	657.73
492	Paramirim	292360	5	21001	17.95	paramirinhense	1170.128
494	Paripiranga	292380	5	27778	63.75	paripiranguense	435.701
496	Paulo Afonso	292400	5	108396	68.62	paulo-afonsino	1579.723
498	Pedrão	292410	5	6876	43.03	pedronense	159.794
500	Piatã	292430	5	17982	10.49	piatãense	1713.474
501	Pilão Arcado	292440	5	32860	2.8	pilão-arcadense	11732.231
503	Pindobaçu	292460	5	20121	40.54	pindobaçuense	496.279
505	Piraí Do Norte	292467	5	9799	52.32	piraiense	187.278
506	Piripá	292470	5	12783	29.08	piripaense	439.655
508	Planaltino	292490	5	8822	9.52	planaltinense	926.971
510	Poções	292510	5	44701	54.08	poçoense	826.535
512	Ponto Novo	292525	5	15742	31.65	ponto-novense	497.347
514	Potiraguá	292540	5	9810	9.95	potiragüense	985.482
515	Prado	292550	5	27627	15.87	pradense	1740.299
518	Presidente Tancredo Neves	292575	5	23846	57.16	tancredense	417.198
519	Queimadas	292580	5	24602	12.15	queimadense	2024.24
521	Quixabeira	292593	5	9554	24.64	quixabeirense	387.7
523	Remanso	292600	5	38957	8.32	remansense	4683.959
525	Riachão Das Neves	292620	5	21937	3.87	riachão-nevense	5670.417
527	Riacho De Santana	292640	5	30646	11.87	riachense	2582.194
528	Ribeira Do Amparo	292650	5	14276	22.22	amparense	642.585
530	Ribeirão Do Largo	292665	5	8602	6.77	ribeirense	1271.344
531	Rio De Contas	292670	5	13007	12.23	rio-contense	1063.747
533	Rio Do Pires	292690	5	11918	14.54	rio-pirense	819.807
535	Rodelas	292710	5	7775	2.85	rodelense	2723.521
536	Ruy Barbosa	292720	5	29887	13.76	rui-barbosense	2171.443
538	Salvador	292740	5	2675656	3.859	soteropolitano	693.292
540	Santa Brígida	292760	5	15060	17.06	santa-brigidense	882.852
542	Santa Cruz Da Vitória	292780	5	6673	22.38	santa-cruzense	298.209
543	Santa Inês	292790	5	10363	32.83	santinense	315.656
545	Santa Maria Da Vitória	292810	5	40309	20.49	santa-mariense	1966.777
547	Santa Teresinha	292850	5	9648	13.64	santa-teresinhense	707.239
548	Santaluz	292800	5	33838	21.7	luzense	1559.714
550	Santanópolis	292830	5	8776	38.02	santanopolinense	230.832
552	Santo Antônio De Jesus	292870	5	90985	348.14	santo-antoniense	261.348
554	São Desidério	292890	5	27659	1.82	são-desideriano	15156.986
556	São Felipe	292910	5	20305	98.57	são-felipense	205.989
557	São Félix	292900	5	14098	142.11	são-felista	99.204
559	São Francisco Do Conde	292920	5	33183	126.24	franciscano	262.855
561	São Gonçalo Dos Campos	292930	5	33283	110.67	são-gonçalense	300.729
563	São José Do Jacuípe	292937	5	10180	25.07	jacuipense	406.028
564	São Miguel Das Matas	292940	5	10414	48.57	miguelense	214.417
566	Sapeaçu	292960	5	16585	141.51	sapeaçuense	117.204
567	Sátiro Dias	292970	5	18964	18.78	satirense	1010.029
569	Saúde	292980	5	11845	23.49	saudense	504.312
571	Sebastião Laranjeiras	293000	5	10371	5.32	sebastianense	1948.468
573	Sento Sé	293020	5	37425	2.95	sento-seense	12698.8
574	Serra Do Ramalho	293015	5	31638	12.2	serra-malhense	2593.427
576	Serra Preta	293040	5	15401	28.71	serra-pretense	536.499
577	Serrinha	293050	5	76762	116.5	serrinhense	658.925
579	Simões Filho	293070	5	118047	586.65	simões-filhense	201.222
581	Sítio Do Quinto	293076	5	12592	17.94	sítio-quintense	702.06
583	Souto Soares	293080	5	15899	16.01	souto-soarense	993.312
585	Tanhaçu	293100	5	20013	16.21	tanhaçuense	1234.471
586	Tanque Novo	293105	5	16128	22.31	tanque-novense	722.898
588	Taperoá	293120	5	18748	45.64	taperoense	410.784
590	Teixeira De Freitas	293135	5	138341	118.86	teixeirense	1163.871
592	Teofilândia	293150	5	21482	64.02	teofilandense	335.529
593	Teolândia	293160	5	14836	46.68	teolandense	317.826
595	Tremedal	293180	5	17029	10.14	tremedalense	1679.6
597	Uauá	293200	5	24294	8	uauaense	3035.151
598	Ubaíra	293210	5	19750	27.19	ubairense	726.259
600	Ubatã	293230	5	25004	93.22	ubatense	268.223
602	Umburanas	293245	5	17000	10.18	umburanense	1670.484
604	Urandi	293260	5	16466	16.99	urandiense	969.441
606	Utinga	293280	5	18173	28.47	utinguense	638.224
608	Valente	293300	5	24560	63.9	valentense	384.321
609	Várzea Da Roça	293305	5	13786	26.83	varzeano	513.915
611	Várzea Nova	293315	5	13073	10.96	várzea-novense	1192.899
613	Vera Cruz	293320	5	37567	125.33	vera-cruzense	299.733
615	Vitória Da Conquista	293330	5	306866	90.11	conquistense	3405.58
616	Wagner	293340	5	8983	21.34	wagnense	420.997
619	Xique Xique	293360	5	45536	8.28	xiquexiquense	5502.297
620	Abaiara	230010	6	10496	58.69	abaiarense	178.833
621	Acarape	230015	6	15338	95.69	acarapense	160.296
623	Acopiara	230030	6	51160	22.7	acopiarense	2253.779
625	Alcântaras	230050	6	10771	77.71	alcantarense	138.604
627	Alto Santo	230070	6	16359	12.22	alto-santense	1338.207
629	Antonina Do Norte	230080	6	6984	26.85	antonino	260.102
630	Apuiarés	230090	6	13925	25.21	apuiareense	552.338
632	Aracati	230110	6	69159	55.45	aracatiense	1247.301
634	Ararendá	230125	6	10491	30.49	ararendaense	344.13
636	Aratuba	230140	6	11529	100.44	aratubano	114.784
637	Arneiroz	230150	6	7650	7.17	arneirozense	1066.356
639	Aurora	230170	6	24566	27.61	aurorense	889.876
641	Banabuiú	230185	6	17315	16.03	banabuiense	1079.989
2718	Riachão	251274	15	3266	36.23	riachãoense	90.15
643	Barreira	230195	6	19573	81.25	barreirense	240.903
645	Barroquinha	230205	6	14476	37.76	barroquinhense	383.403
647	Beberibe	230220	6	49311	30.37	beberibense	1623.881
649	Boa Viagem	230240	6	52498	18.51	boa-viagense	2836.764
650	Brejo Santo	230250	6	45193	68.12	brejo-santense	663.421
652	Campos Sales	230270	6	26506	24.48	campos-salense	1082.766
654	Capistrano	230290	6	17062	76.67	capistranense	222.547
656	Cariré	230310	6	18347	24.24	carireense	756.87
658	Cariús	230330	6	18567	17.49	cariuense	1061.797
659	Carnaubal	230340	6	16746	45.9	carnaubalense	364.804
661	Catarina	230360	6	18745	38.5	catarinense	486.861
663	Caucaia	230370	6	325441	265.93	caucaiense	1223.796
665	Chaval	230390	6	12615	52.95	chavalense	238.234
666	Choró	230393	6	12853	15.76	choroense	815.765
668	Coreaú	230400	6	21954	28.3	coreauense	775.792
670	Crato	230420	6	121428	104.87	cratense	1157.914
672	Cruz	230425	6	22479	67.28	cruzense	334.121
674	Ererê	230427	6	6840	17.27	erereense	396.016
675	Eusébio	230428	6	46033	582.64	eusebiano	79.008
677	Forquilha	230435	6	21786	42.14	forquilhense	516.99
679	Fortim	230445	6	14817	52.53	fortinense	282.066
680	Frecheirinha	230450	6	12991	71.68	frecheirinhense	181.239
682	Graça	230465	6	15049	53.39	gracense	281.871
684	Granjeiro	230480	6	4629	46.23	granjeirense	100.127
686	Guaiúba	230495	6	24091	94.83	guaiubano	254.044
687	Guaraciaba Do Norte	230500	6	37775	61.78	guaraciabense	611.461
689	Hidrolândia	230520	6	19325	20.84	hidrolandiense	927.377
691	Ibaretama	230526	6	12922	14.73	ibaretamense	877.252
693	Ibicuitinga	230533	6	11335	26.72	ibicuitinguense	424.243
694	Icapuí	230535	6	18392	43.43	icapuiense	423.446
696	Iguatu	230550	6	96495	94.87	iguatuense	1017.089
698	Ipaporanga	230565	6	11343	16.16	ipaporanguense	702.131
700	Ipu	230580	6	40296	64.03	ipuense	629.312
701	Ipueiras	230590	6	37862	25.63	ipueirense	1477.399
703	Irauçuba	230610	6	22324	15.39	irauçubense	1450.707
705	Itaitinga	230625	6	35817	236.52	itaitiguense	151.436
707	Itapipoca	230640	6	116065	72.38	itapipoquense	1603.654
708	Itapiúna	230650	6	18626	31.64	itapiunense	588.696
710	Itatira	230660	6	18894	24.12	itatirense	783.433
712	Jaguaribara	230680	6	10399	15.55	jaguaribarense	668.734
714	Jaguaruana	230700	6	32236	38.05	jaguaruanense	847.261
715	Jardim	230710	6	26688	51.41	jardinense	519.101
718	Juazeiro Do Norte	230730	6	249939	1.006	juazeirense	248.223
719	Jucás	230740	6	23807	25.4	jucaense	937.184
721	Limoeiro Do Norte	230760	6	56264	74.84	limoeirense	751.834
722	Madalena	230763	6	18088	17.63	madalenense	1026.256
724	Maranguape	230770	6	113561	192.19	maranguapense	590.886
726	Martinópole	230790	6	10214	34.17	martinolopolitano	298.959
728	Mauriti	230810	6	44240	41	mauritiense	1078.963
729	Meruoca	230820	6	13693	91.38	meruoquense	149.844
731	Milhã	230835	6	13086	26.05	milhanense	502.342
733	Missão Velha	230840	6	34274	52.69	missanvelhense	650.538
735	Monsenhor Tabosa	230860	6	16705	18.69	tabosense	893.644
736	Morada Nova	230870	6	62065	22.33	morada-novense	2779.231
738	Morrinhos	230890	6	20700	49.81	morrinhense	415.551
740	Mulungu	230910	6	11485	120.16	mulunguense	95.58
742	Nova Russas	230930	6	30965	41.69	nova-russano	742.764
744	Ocara	230945	6	24007	31.37	ocarense	765.407
745	Orós	230950	6	21389	37.12	oroense	576.267
747	Pacatuba	230970	6	72299	498.35	pacatubano	145.077
749	Pacujá	230990	6	5986	78.63	pacujaense	76.128
750	Palhano	231000	6	8866	20.13	palhanense	440.378
752	Paracuru	231020	6	31636	106.8	paracuruense	296.215
754	Parambu	231030	6	31309	13.54	parambuense	2312.398
756	Pedra Branca	231050	6	41890	32.14	pedra-branquense	1303.28
758	Pentecoste	231070	6	35400	25.68	pentecostense	1378.303
760	Pindoretama	231085	6	18683	256.06	pindoretamense	72.964
762	Pires Ferreira	231095	6	10216	42.02	pires-ferreirense	243.097
763	Poranga	231100	6	12001	9.17	poranguense	1309.253
765	Potengi	231120	6	10276	30.34	potengiense	338.727
767	Quiterianópolis	231126	6	19921	19.14	quiterianopolense	1040.984
769	Quixelô	231135	6	15000	25.72	quixeloense	583.236
771	Quixeré	231150	6	19412	31.78	quixereense	610.776
772	Redenção	231160	6	26415	117.09	redencionista	225.592
2721	Riacho De Santo Antônio	251278	15	1722	18.86	riachoantoniense	91.323
776	Salitre	231195	6	15453	19.21	salitrense	804.349
778	Santana Do Acaraú	231200	6	29946	30.89	santanense-do-acaraú	969.321
780	São Benedito	231230	6	44178	130.61	beneditense	338.243
781	São Gonçalo Do Amarante	231240	6	43890	52.34	gonçalense	838.513
783	São Luís Do Curu	231260	6	12332	100.74	curuense	122.42
784	Senador Pompeu	231270	6	26469	27.68	pompeuense	956.122
786	Sobral	231290	6	188233	88.67	sobralense	2122.885
788	Tabuleiro Do Norte	231310	6	29204	33.89	tabuleirense	861.817
790	Tarrafas	231325	6	8910	19.61	tarrafense	454.389
791	Tauá	231330	6	55716	13.9	tauaense	4009.271
793	Tianguá	231340	6	68892	75.8	tianguaense	908.883
795	Tururu	231355	6	14408	71.23	tururuense	202.275
797	Umari	231370	6	7545	28.59	umariense	263.928
798	Umirim	231375	6	18802	59.35	umiriense	316.816
800	Uruoca	231390	6	12883	18.49	uruoquense	696.749
802	Várzea Alegre	231400	6	38434	45.99	varzea-alegrense	835.705
804	Brasília	530010	7	2570160	444.07	brasiliense	5787.784
806	Água Doce Do Norte	320016	8	11771	24.32	água-docense	484.047
807	Águia Branca	320013	8	9519	21.17	aguiabranquense	449.633
809	Alfredo Chaves	320030	8	13955	22.67	alfredense	615.595
811	Anchieta	320040	8	23902	58.61	anchietense	407.811
812	Apiacá	320050	8	7512	38.8	apiacaense	193.587
815	Baixo Guandu	320080	8	29081	31.68	guanduense	917.892
816	Barra De São Francisco	320090	8	40649	43.53	franciscano	933.754
818	Bom Jesus Do Norte	320110	8	9476	106.45	bom-jesuense	89.02
819	Brejetuba	320115	8	11915	34.79	brejetubense	342.509
821	Cariacica	320130	8	348738	1.245	cariaciquense	279.976
823	Colatina	320150	8	111788	78.54	colatinense	1423.277
825	Conceição Do Castelo	320170	8	11681	31.63	conceiçãoense	369.279
826	Divino De São Lourenço	320180	8	4516	25.69	são-lourencense	175.792
828	Dores Do Rio Preto	320200	8	6397	40.35	rio-pretense	158.528
829	Ecoporanga	320210	8	23212	10.17	ecoporanguense	2283.227
832	Guaçuí	320230	8	27851	59.54	guaçuiense	467.759
833	Guarapari	320240	8	105286	176.81	guarapariense	595.483
835	Ibiraçu	320250	8	11178	55.93	ibiraçuense	199.849
836	Ibitirama	320255	8	8957	27.19	ibitiranense	329.449
838	Irupi	320265	8	11723	63.56	irupiense	184.428
840	Itapemirim	320280	8	30988	55.6	itapemirinense	557.325
842	Iúna	320300	8	27328	59.36	iunense	460.365
843	Jaguaré	320305	8	24678	37.6	jaguarense	656.37
845	João Neiva	320313	8	15809	57.94	joão-neivense	272.834
847	Linhares	320320	8	141306	40.35	linharense	3501.627
849	Marataízes	320332	8	34140	252.23	marataizense	135.35
850	Marechal Floriano	320334	8	14262	49.85	florianense	286.103
852	Mimoso Do Sul	320340	8	25902	29.87	mimosense	867.283
854	Mucurici	320360	8	5655	10.5	mucuriciense	538.813
856	Muqui	320380	8	14396	44.04	muquiense	326.874
857	Nova Venécia	320390	8	46031	31.78	veneciano	1448.362
859	Pedro Canário	320405	8	23794	54.82	canariense	434.054
861	Piúma	320420	8	18123	242.79	piumense	74.645
862	Ponto Belo	320425	8	6979	19.6	pontobelense	356.158
864	Rio Bananal	320435	8	17530	27.16	ribanense	645.482
866	Santa Leopoldina	320450	8	12240	17.08	leopoldinense	716.444
868	Santa Teresa	320460	8	21823	31.42	teresense	694.534
869	São Domingos Do Norte	320465	8	8001	26.72	dominguense	299.49
871	São José Do Calçado	320480	8	10408	38.17	calçadense	272.645
872	São Mateus	320490	8	109028	46.53	mateense	2343.15
874	Serra	320500	8	409267	739.38	serrano	553.526
875	Sooretama	320501	8	23843	40.18	sooretamense	593.387
877	Venda Nova Do Imigrante	320506	8	20447	108.82	venda-novense	187.895
879	Vila Pavão	320515	8	8672	20.04	pavoense	432.745
881	Vila Velha	320520	8	414586	1.951	vila-velhense	212.392
882	Vitória	320530	8	327801	3.327	capixaba	98.506
884	Abadiânia	520010	9	15757	15.08	abadiense	1045.126
886	Adelândia	520015	9	2477	21.47	adelandense	115.353
888	Água Limpa	520020	9	2013	4.45	água-limpense	452.858
889	Águas Lindas De Goiás	520025	9	159378	846.03	águas lindense	188.384
891	Aloândia	520050	9	2051	20.08	aloandense	102.16
893	Alto Paraíso De Goiás	520060	9	6885	2.65	alto-paraisense	2593.901
894	Alvorada Do Norte	520080	9	8084	6.42	alvoradense	1259.364
896	Americano Do Brasil	520085	9	5508	41.24	americanense-do-Brasil	133.563
898	Anápolis	520110	9	334613	358.58	anapolino	933.156
2724	Salgadinho	251300	15	3508	19.04	salgadinhense	184.239
902	Aparecida Do Rio Doce	520145	9	2427	4.03	riodocense	602.134
903	Aporé	520150	9	3803	1.31	aporeano	2900.162
905	Aragarças	520170	9	18305	27.61	aragarcense	662.917
907	Araguapaz	520215	9	7510	3.42	araguapaense	2193.699
909	Aruanã	520250	9	7496	2.46	aruanense	3050.304
910	Aurilândia	520260	9	3650	6.46	aurilandense	565.34
912	Baliza	520310	9	3714	2.08	balizense	1782.596
914	Bela Vista De Goiás	520330	9	24554	19.56	bela-vistense	1255.419
916	Bom Jesus De Goiás	520350	9	20727	14.75	bom-jesuense	1405.119
917	Bonfinópolis	520355	9	7536	61.62	bonfinopolino	122.29
919	Brazabrantes	520360	9	3232	26.26	brazabrantino	123.072
921	Buriti Alegre	520390	9	9054	10.11	buriti-alegrense	895.456
923	Buritinópolis	520396	9	3321	13.44	buritinopolense	247.047
924	Cabeceiras	520400	9	7354	6.52	cabeceirense	1127.604
926	Cachoeira De Goiás	520420	9	1417	3.35	cachoeirense	422.751
928	Caçu	520430	9	13283	5.9	caçuense	2251.008
929	Caiapônia	520440	9	16757	1.94	caiaponiense	8637.872
931	Caldazinha	520455	9	3325	13.25	caldazinhense	250.887
933	Campinaçu	520465	9	3656	1.85	campinaçuense	1974.372
934	Campinorte	520470	9	11111	10.41	campinortense	1067.196
936	Campo Limpo De Goiás	520485	9	6241	39.11	campolimpense	159.557
938	Campos Verdes	520495	9	5020	11.37	campo-verdense	441.645
940	Castelândia	520505	9	3638	12.23	castelandense	297.428
941	Catalão	520510	9	86647	22.67	catalano	3821.461
943	Cavalcante	520530	9	9392	1.35	cavalcantense	6953.654
944	Ceres	520540	9	20722	96.69	ceresino	214.321
946	Chapadão Do Céu	520547	9	7001	3.2	chapadense	2185.125
948	Cocalzinho De Goiás	520551	9	17407	9.73	cocalzinhense	1789.039
950	Córrego Do Ouro	520570	9	2632	5.69	corregorino	462.304
951	Corumbá De Goiás	520580	9	10361	9.76	corumbaense	1061.954
953	Cristalina	520620	9	46580	7.56	cristalinense	6162.056
955	Crixás	520640	9	15760	3.38	crixasense	4661.158
957	Cumari	520660	9	2964	5.2	cumarino	570.541
958	Damianópolis	520670	9	3292	7.93	damianopolino	415.348
960	Davinópolis	520690	9	2056	4.27	davinopolino	481.296
962	Divinópolis De Goiás	520830	9	4962	5.97	divinopolino	830.976
964	Edealina	520735	9	3733	6.18	edealinense	603.654
965	Edéia	520740	9	11266	7.71	edeiense	1461.503
967	Faina	520753	9	6983	3.59	fainense	1945.657
969	Firminópolis	520780	9	11580	27.33	firminopolense	423.649
971	Formosa	520800	9	100085	17.22	formosense	5811.782
972	Formoso	520810	9	4883	5.78	formosense 	844.288
974	Goianápolis	520840	9	10695	65.84	goianapolino	162.435
975	Goiandira	520850	9	5265	9.32	goiandirense	564.687
977	Goiânia	520870	9	1302001	1.776	goianiense	732.801
979	Goiás	520890	9	24727	7.96	goiano	3108.018
981	Gouvelândia	520915	9	4949	6	gouvelandense	824.26
982	Guapó	520920	9	13976	27.04	guapoense	516.844
984	Guarani De Goiás	520940	9	4258	3.46	guaraniense	1229.145
986	Heitoraí	520960	9	3571	15.55	heitoraiense	229.638
988	Hidrolina	520980	9	4029	6.94	hidrolinense	580.39
989	Iaciara	520990	9	12427	8.02	iaciarense	1550.373
991	Indiara	520995	9	13687	14.31	indiarense	956.474
993	Ipameri	521010	9	24735	5.66	ipamerino	4368.985
995	Iporá	521020	9	31274	30.47	iporaense	1026.383
996	Israelândia	521030	9	2887	5	israelandense	577.482
998	Itaguari	521056	9	4513	30.78	itaguarino	146.638
1000	Itajá	521080	9	5062	2.42	itajaense	2091.399
1002	Itapirapuã	521100	9	7835	3.83	itapirapuano	2043.714
1003	Itapuranga	521120	9	26125	20.47	itapuranguense	1276.478
1005	Itauçu	521140	9	8575	22.34	itauçuense	383.842
1007	Ivolândia	521160	9	2663	2.12	ivolandense	1257.663
1009	Jaraguá	521180	9	41870	22.64	jaraguense	1849.551
1011	Jaupaci	521200	9	3000	5.69	jaupacino	527.103
1012	Jesúpolis	521205	9	2300	18.78	jesupolino	122.475
1014	Jussara	521220	9	19153	4.69	jussariano	4084.113
1016	Leopoldo De Bulhões	521230	9	7882	16.39	leopoldense	480.891
1018	Mairipotaba	521260	9	2374	5.08	mairipotabense	467.428
1020	Mara Rosa	521280	9	10649	6.31	mara-rosense	1687.903
1021	Marzagão	521290	9	2072	9.32	marzagonense	222.428
1023	Maurilândia	521300	9	11521	29.56	maurilandense	389.756
1025	Minaçu	521308	9	31154	10.89	minaçuense	2860.731
1027	Moiporá	521340	9	1763	3.83	moiporaense	460.624
2863	Ipojuca	260720	16	80637	151.39	ipojuquense	532.644
1029	Montes Claros De Goiás	521370	9	7987	2.75	montes-clarense	2899.176
1031	Montividiu Do Norte	521377	9	4122	3.09	montividense	1332.994
1033	Morro Agudo De Goiás	521385	9	2356	8.34	morro-agudense	282.616
1034	Mossâmedes	521390	9	5007	7.32	mossamedino	684.452
1036	Mundo Novo	521405	9	6438	3	mundo-novense	2146.649
1038	Nazário	521440	9	7874	29.26	nazarinense	269.103
1039	Nerópolis	521450	9	24210	118.55	neropolino	204.217
1041	Nova América	521470	9	2259	10.65	nova-americano	212.025
1043	Nova Crixás	521483	9	11927	1.63	nova-crixaense	7298.775
1045	Nova Iguaçu De Goiás	521487	9	2826	4.5	nova iguaçuense	628.444
1046	Nova Roma	521490	9	3471	1.63	nova-romano	2135.956
1048	Novo Brasil	521520	9	3519	5.41	novo-brasilense	649.954
1050	Novo Planalto	521525	9	3956	3.18	planaltense	1242.733
1052	Ouro Verde De Goiás	521540	9	4034	19.32	ouro-verdense	208.769
1054	Padre Bernardo	521560	9	27671	8.82	padre-bernardense	3138.86
1056	Palmeiras De Goiás	521570	9	23338	15.16	palmeirense	1539.692
1057	Palmelo	521580	9	2335	39.6	palmelino	58.959
1059	Panamá	521600	9	2682	6.18	panamenho	433.761
1061	Paraúna	521640	9	10863	2.87	paraunense	3779.384
1062	Perolândia	521645	9	2950	2.87	perolandense	1029.625
1064	Pilar De Goiás	521690	9	2773	3.06	pilarense	906.649
1066	Piranhas	521720	9	11266	5.5	piranhense	2047.765
1068	Pires Do Rio	521740	9	28762	26.8	piresino	1073.36
1069	Planaltina	521760	9	81649	32.17	planaltinense	2538.196
1071	Porangatu	521800	9	42355	8.79	porangatuense	4820.508
1073	Portelândia	521810	9	3839	6.9	portelandense	556.576
1074	Posse	521830	9	31419	15.52	possense	2024.533
1076	Quirinópolis	521850	9	43220	11.41	quirinopolino	3786.695
1078	Rianápolis	521870	9	4566	28.67	rianapolino	159.255
1080	Rio Verde	521880	9	176424	21.05	rio-verdense	8379.661
1081	Rubiataba	521890	9	18915	25.28	rubiatabense	748.264
1083	Santa Bárbara De Goiás	521910	9	5751	41.2	santa-barbarense	139.598
1085	Santa Fé De Goiás	521925	9	4762	4.07	santa-feense	1169.167
1087	Santa Isabel	521935	9	3686	4.57	santa-isabelense	807.204
1088	Santa Rita Do Araguaia	521940	9	6924	5.08	santa-ritense	1361.772
1090	Santa Rosa De Goiás	521950	9	2909	17.73	santa-rosense	164.097
1092	Santa Terezinha De Goiás	521970	9	10302	8.57	terezinhense	1202.238
1093	Santo Antônio Da Barra	521971	9	4423	9.79	santatoniense	451.598
1095	Santo Antônio Do Descoberto	521975	9	63248	67	descobertense	944.046
1096	São Domingos	521980	9	11272	3.42	dominicano	3295.727
1098	São João D`Aliança	522000	9	10257	3.08	são-joanense	3327.374
1100	São Luís De Montes Belos	522010	9	30034	36.36	monte-belense	825.999
1101	São Luíz Do Norte	522015	9	4617	7.88	são-luizense	586.058
1103	São Miguel Do Passa Quatro	522026	9	3757	6.99	passa-quatrense	537.785
1104	São Patrício	522028	9	1991	11.58	sampatriciense	171.957
1106	Senador Canedo	522045	9	84443	344.27	canedense	245.283
1108	Silvânia	522060	9	19089	8.14	silvaniense	2345.939
1109	Simolândia	522068	9	6514	18.72	simolandense	347.976
1111	Taquaral De Goiás	522100	9	3541	17.34	taquaralense	204.218
1113	Terezópolis De Goiás	522119	9	6561	61.37	terezopolino	106.913
1115	Trindade	522140	9	104488	147.02	trindadense	710.713
1116	Trombas	522145	9	3452	4.32	trombense	799.124
1118	Turvelândia	522155	9	4399	4.71	turvelandense	933.957
1120	Uruaçu	522160	9	36929	17.24	uruaçuense	2141.815
1122	Urutaí	522180	9	3074	4.9	urutaíno	626.722
1124	Varjão	522190	9	3659	7.05	varjãoense	519.194
1125	Vianópolis	522200	9	12548	13.15	vianopolino	954.283
1127	Vila Boa	522220	9	4735	4.47	vilaboense	1060.17
1128	Vila Propício	522230	9	5145	2.36	propiciense	2181.58
1130	Afonso Cunha	210010	10	5905	15.9	afonso-cunhense	371.336
1132	Alcântara	210020	10	21851	14.7	alcantarense	1486.67
1133	Aldeias Altas	210030	10	23952	12.33	aldeias-altense	1942.105
1135	Alto Alegre Do Maranhão	210043	10	24599	64.18	alto-alegrense 	383.303
1137	Alto Parnaíba	210050	10	10766	0.97	alto-parnaibano	11132.142
1138	Amapá Do Maranhão	210055	10	6431	12.8	amapaense	502.397
1140	Anajatuba	210070	10	25291	25.01	anajatubense	1011.124
1141	Anapurus	210080	10	13939	22.91	anapuruense	608.293
1143	Araguanã	210087	10	13973	17.35	araguanaense 	805.194
1145	Arame	210095	10	31702	10.54	aramense	3008.675
1147	Axixá	210110	10	11407	56.15	axixaense	203.152
1148	Bacabal	210120	10	100014	59.42	bacabalense	1683.064
1152	Balsas	210140	10	83528	6.36	balsense	13141.688
1154	Barra Do Corda	210160	10	82830	15.92	barra-cordense	5202.679
1156	Bela Vista Do Maranhão	210177	10	12049	47.17	bela-vistense	255.415
1157	Belágua	210173	10	6524	13.06	belaguaense 	499.424
1159	Bequimão	210190	10	20344	26.46	bequimãoense	768.951
1161	Boa Vista Do Gurupi	210197	10	7949	19.7	boa-vistense	403.457
1162	Bom Jardim	210200	10	39049	5.93	bom-jardinense	6590.501
1164	Bom Lugar	210207	10	14818	33.23	bom-lugarense	445.978
1166	Brejo De Areia	210215	10	5577	5.26	brejareiense 	1059.279
1167	Buriti	210220	10	27013	18.33	buritiense	1473.994
1169	Buriticupu	210232	10	65237	25.63	buriticupuense	2545.566
1171	Cachoeira Grande	210237	10	8446	11.97	cachoeirense	705.641
1173	Cajari	210250	10	18338	27.7	cajariense	662.063
1175	Cândido Mendes	210260	10	18505	11.33	cândido-mendense	1633.72
1176	Cantanhede	210270	10	20448	26.45	cantanhedense	773.006
1178	Carolina	210280	10	23959	3.72	carolinense	6441.581
1179	Carutapera	210290	10	22006	17.86	carutaperense	1232.074
1181	Cedral	210310	10	10297	35.63	cedralense	289.024
1183	Centro Do Guilherme	210315	10	12565	11.7	centroguilhermense	1074.061
1185	Chapadinha	210320	10	73350	22.59	chapadinhense	3247.366
1186	Cidelândia	210325	10	13681	9.34	cidelandense	1464.027
1188	Coelho Neto	210340	10	46750	47.92	coelho-netense	975.544
1190	Conceição Do Lago Açu	210355	10	14436	19.69	lagoaçuense	733.227
1191	Coroatá	210360	10	61725	27.27	coroataense	2263.769
1193	Davinópolis	210375	10	12579	37.44	davinopolitano	335.969
1195	Duque Bacelar	210390	10	10649	33.5	bacelarense	317.918
1197	Estreito	210405	10	35835	13.18	estreitense	2718.97
1199	Fernando Falcão	210408	10	9241	1.82	fernandense	5086.563
1200	Formosa Da Serra Negra	210409	10	17757	4.49	formosense	3950.516
1202	Fortuna	210420	10	15098	21.72	fortunense	694.992
1203	Godofredo Viana	210430	10	10635	15.77	godofredense	674.419
1205	Governador Archer	210450	10	10205	22.63	archense	450.894
1207	Governador Eugênio Barros	210460	10	15991	19.57	 eugênio-barrense	816.99
1209	Governador Newton Bello	210465	10	11921	10.27	newton-belense	1160.49
1210	Governador Nunes Freire	210467	10	25401	24.49	nunes-freirense	1037.125
1212	Grajaú	210480	10	62093	7.03	grajauense	8830.886
1213	Guimarães	210490	10	12081	20.29	vimaranense	595.379
1215	Icatu	210510	10	25145	17.36	icatuense	1448.793
1217	Igarapé Grande	210520	10	11041	29.5	igarapé-grandense	374.246
1218	Imperatriz	210530	10	247505	180.79	imperatrizense	1368.982
1220	Itapecuru Mirim	210540	10	62110	42.21	itapecuruense	1471.431
1222	Jatobá	210545	10	8526	14.42	jatobaense	591.38
1224	João Lisboa	210550	10	20381	32	joão-lisboense	636.889
1225	Joselândia	210560	10	15433	22.64	joselandense	681.688
1227	Lago Da Pedra	210570	10	46083	37.68	lago-pedrense	1223.171
1229	Lago Dos Rodrigues	210594	10	7794	43.21	lago-rodriguense	180.369
1230	Lago Verde	210590	10	15412	24.73	lago-verdense	623.231
1232	Lagoa Grande Do Maranhão	210596	10	10517	14.13	lagoa-grandense	744.293
1234	Lima Campos	210600	10	11423	35.48	lima-campense	321.93
1235	Loreto	210610	10	11390	3.17	lorentense	3596.826
1237	Magalhães De Almeida	210630	10	17587	40.6	magalhense	433.148
1239	Marajá Do Sena	210635	10	8051	5.56	marajaense	1447.669
1241	Mata Roma	210640	10	15150	27.63	mata-romense	548.411
1242	Matinha	210650	10	21885	53.54	matinhense	408.725
1244	Matões Do Norte	210663	10	13794	17.36	norte-matõense	794.647
1246	Mirador	210670	10	20452	2.42	miradoense	8450.811
1247	Miranda Do Norte	210675	10	24427	71.61	mirandense-do-norte	341.105
1249	Monção	210690	10	31738	24.38	monçonense	1301.961
1251	Morros	210710	10	17783	10.37	morroense	1715.13
1252	Nina Rodrigues	210720	10	12464	21.77	ninense	572.506
1254	Nova Iorque	210730	10	4590	4.7	nova-iorquino	976.849
1256	Olho D`Água Das Cunhãs	210740	10	18601	26.75	olho-daguense	695.345
1258	Paço Do Lumiar	210750	10	105121	842.63	luminense	124.753
1259	Palmeirândia	210760	10	18764	35.7	palmeirandense	525.58
1261	Parnarama	210780	10	34586	10.06	parnaramense	3439.208
1262	Passagem Franca	210790	10	17562	12.93	passagense	1358.321
1264	Paulino Neves	210805	10	14519	14.83	paulinoense	979.173
1266	Pedreiras	210820	10	39448	136.77	pedreirense	288.431
1268	Penalva	210830	10	34267	46.42	penalvense	738.25
1269	Peri Mirim	210840	10	13803	34.06	peri-miriense	405.3
1271	Pindaré Mirim	210850	10	31152	127.25	pindareense	244.815
1273	Pio Xii	210870	10	22016	40.39	piodocense 	545.137
1276	Porto Franco	210900	10	21530	15.19	porto-franquino	1417.488
1277	Porto Rico Do Maranhão	210905	10	6030	28.31	porto-riquense	213.009
1279	Presidente Juscelino	210920	10	11541	32.54	juscelinense	354.694
1281	Presidente Sarney	210927	10	17165	23.7	sarneyense	724.151
1282	Presidente Vargas	210930	10	10717	23.33	presidentino	459.358
1284	Raposa	210945	10	26327	409.1	raposense	64.353
1286	Ribamar Fiquene	210955	10	7318	9.75	fiquenense	750.55
1287	Rosário	210960	10	39576	57.77	rosariense	685.032
1290	Santa Helena	210980	10	39110	16.94	santa-helenense	2308.182
1291	Santa Inês	210990	10	77282	188.49	santa-inesense	410.001
1293	Santa Luzia Do Paruá	211003	10	22644	25.24	santa-luziense-do-paruá	897.142
1295	Santa Rita	211020	10	32366	45.82	santa-ritense	706.381
1296	Santana Do Maranhão	211023	10	11661	12.51	santanense	932.011
1298	Santo Antônio Dos Lopes	211030	10	14288	18.53	santo-antoense	770.919
1300	São Bento	211050	10	40736	88.74	sambentuense	459.068
1301	São Bernardo	211060	10	26476	26.29	bernardense	1006.913
1303	São Domingos Do Maranhão	211070	10	33607	29.17	são-dominguense	1151.973
1304	São Félix De Balsas	211080	10	4702	2.31	são-felense	2032.356
1306	São Francisco Do Maranhão	211090	10	12146	5.17	são-franciscano	2347.187
1308	São João Do Carú	211102	10	12309	19.99	caruense	615.692
1310	São João Do Soter	211107	10	17238	11.99	 sotense	1438.063
1311	São João Dos Patos	211110	10	24928	16.61	patoense	1500.621
1313	São José Dos Basílios	211125	10	7496	20.67	basiliense	362.69
1314	São Luís	211130	10	1014837	1.215	são-luisense	834.78
1316	São Mateus Do Maranhão	211150	10	39093	49.91	são-mateuense 	783.22
1317	São Pedro Da Água Branca	211153	10	12028	16.69	agua-braquense	720.456
1319	São Raimundo Das Mangabeiras	211160	10	17474	4.96	mangabeirense	3521.511
1321	São Roberto	211167	10	5957	26.19	são-robertense	227.462
1322	São Vicente Ferrer	211170	10	20863	53.38	vicentino	390.843
1324	Senador Alexandre Costa	211174	10	10256	24.05	alexandrecostense	426.432
1326	Serrano Do Maranhão	211178	10	10940	9.06	serranense	1207.053
1327	Sítio Novo	211180	10	17002	5.46	sitio-novense	3114.859
1329	Sucupira Do Riachão	211195	10	4613	8.17	sucupirense	564.965
1331	Timbiras	211210	10	27997	18.83	timbirense	1486.58
1332	Timon	211220	10	155460	89.18	timonense	1743.228
1334	Tufilândia	211227	10	5596	20.65	tufilandense	271.009
1336	Turiaçu	211240	10	33933	13.16	turiense	2578.483
1337	Turilândia	211245	10	22846	15.11	turilandense	1511.849
1339	Urbano Santos	211260	10	24573	20.35	urbano-santense	1207.628
1341	Viana	211280	10	49496	42.36	vianense	1168.437
1343	Vitória Do Mearim	211290	10	31217	43.56	vitoriense	716.716
1344	Vitorino Freire	211300	10	31658	24.26	vitorinense	1305.088
1346	Abadia Dos Dourados	310010	11	6704	7.61	abadiense	881.063
1348	Abre Campo	310030	11	13311	28.29	abre-campense	470.551
1350	Açucena	310050	11	10276	12.6	açucenense	815.421
1351	Água Boa	310060	11	15195	11.51	água-boense	1320.265
1353	Aguanil	310080	11	4054	17.47	aguanilense	232.091
1355	Águas Vermelhas	310100	11	12722	10.11	águas-vermelhense	1258.795
1357	Aiuruoca	310120	11	6162	9.48	aiuruocano	649.68
1358	Alagoa	310130	11	2709	16.79	alagoense	161.356
1360	Além Paraíba	310150	11	34349	67.3	além-paraíbano	510.354
1362	Alfredo Vasconcelos	310163	11	6075	46.44	vasconcelense	130.815
1364	Alpercata	310180	11	7172	42.95	alpercatense	166.972
1366	Alterosa	310200	11	13717	37.89	alterosense	362.01
1367	Alto Caparaó	310205	11	5297	51.08	alto caparoense	103.69
1369	Alto Rio Doce	310210	11	12159	23.47	alto-rio-docense	518.052
1371	Alvinópolis	310230	11	15261	25.46	alvinopolense	599.443
1373	Amparo Do Serra	310250	11	5053	34.63	serrense	145.908
1374	Andradas	310260	11	37270	79.4	andradense	469.37
1376	Angelândia	310285	11	8003	43.21	angelandense	185.21
1378	Antônio Dias	310300	11	9565	12.15	antônio-diense	787.06
1380	Araçaí	310320	11	2243	12.02	araçaiense	186.543
1381	Aracitaba	310330	11	2058	19.3	aracitabense	106.608
1383	Araguari	310350	11	109801	40.23	araguarino	2729.507
1384	Arantina	310360	11	2823	31.57	arantinense	89.42
1386	Araporã	310375	11	6144	20.77	araporense	295.837
1388	Araújos	310390	11	7883	32.08	araujense	245.721
1390	Arceburgo	310410	11	9509	58.38	arceburguense	162.875
1392	Areado	310430	11	13731	48.5	areadense	283.124
1393	Argirita	310440	11	2901	18.2	argiritense	159.378
1395	Arinos	310450	11	17674	3.35	arinense	5279.411
2726	Santa Cecília	251315	15	6658	29.22	ceciliense	227.874
1398	Augusto De Lima	310480	11	4960	3.95	augusto-limense	1254.83
1399	Baependi	310490	11	18307	24.39	baependiano	750.554
1401	Bambuí	310510	11	22734	15.62	bambuiense	1455.818
1403	Bandeira Do Sul	310530	11	5338	113.41	bandeirante-do-sul	47.067
1405	Barão De Monte Alto	310550	11	5720	28.84	monte-altense	198.313
1406	Barbacena	310560	11	126284	166.34	barbacenense	759.185
1408	Barroso	310590	11	19599	238.81	barroense	82.07
1410	Belmiro Braga	310610	11	3403	8.66	belmirense	393.13
1412	Belo Oriente	310630	11	23397	69.86	belo-orientino	334.909
1413	Belo Vale	310640	11	7536	20.59	belo-valense	365.923
1415	Berizal	310665	11	4370	8.94	berizalense	488.755
1417	Betim	310670	11	378089	1.102	betinense	342.846
1419	Bicas	310690	11	13653	97.46	biquense	140.082
1420	Biquinhas	310700	11	2630	5.73	biquinhense	458.947
1422	Bocaina De Minas	310720	11	5007	9.94	bocainense	503.793
1424	Bom Despacho	310740	11	45624	37.28	bom-despachense	1223.864
1426	Bom Jesus Da Penha	310760	11	3887	18.66	bom-jesuense	208.349
1427	Bom Jesus Do Amparo	310770	11	5491	28.07	bom-jesuense	195.611
1429	Bom Repouso	310790	11	10457	45.5	bom-repousense	229.845
1431	Bonfim	310810	11	6818	22.59	bonfinense 	301.865
1433	Bonito De Minas	310825	11	9673	2.48	bonitense	3904.904
1434	Borda Da Mata	310830	11	17118	56.85	borda-matense	301.108
1436	Botumirim	310850	11	6497	4.14	botumiriense	1568.881
1438	Brasilândia De Minas	310855	11	14226	5.67	brasilandense	2509.691
1440	Brasópolis	310890	11	14661	39.87	brasopolense	367.688
1441	Braúnas	310880	11	5030	13.3	braunense	378.318
1443	Bueno Brandão	310910	11	10892	30.58	bueno-brandense	356.151
1445	Bugre	310925	11	3992	24.66	bugrense	161.906
1446	Buritis	310930	11	22737	4.35	buritisense	5225.179
1448	Cabeceira Grande	310945	11	6453	6.26	cabeceirense	1031.313
1450	Cachoeira Da Prata	310960	11	3654	59.53	cachoeirense	61.381
1452	Cachoeira De Pajeú	310270	11	8959	12.88	cachoeirense	695.671
1454	Caetanópolis	310990	11	10218	65.48	caetanopolitano	156.039
1455	Caeté	311000	11	40750	75.11	caeteense	542.571
1457	Cajuri	311020	11	4047	48.74	cajuriense	83.038
1459	Camacho	311040	11	3154	14.14	camachense	223.001
1460	Camanducaia	311050	11	21080	39.89	camanducaiense	528.477
1462	Cambuquira	311070	11	12602	51.15	cambuquirense	246.38
1464	Campanha	311090	11	15433	45.99	campanhense	335.587
1466	Campina Verde	311110	11	19324	5.29	campina-verdense	3650.751
1468	Campo Belo	311120	11	51544	97.58	campo-belense	528.225
1470	Campo Florido	311140	11	6870	5.43	campo-floridense	1264.246
1471	Campos Altos	311150	11	14206	19.99	campos-altense	710.645
1473	Cana Verde	311190	11	5589	26.27	cana-verdense	212.721
1475	CanÁpolis	311180	11	11365	13.53	canapolense	839.737
1476	Candeias	311200	11	14595	20.26	candeense	720.512
1478	Caparaó	311210	11	5209	39.86	caparaoense	130.694
1480	Capelinha	311230	11	34803	36.05	capelinhense	965.367
1482	Capim Branco	311250	11	8881	93.16	capim-branquense	95.333
1484	Capitão Andrade	311265	11	4925	17.65	capitão andradense	279.088
1486	Capitólio	311280	11	8183	15.68	capitolino	521.802
1487	Caputira	311290	11	9030	48.11	caputirense	187.703
1489	Caranaíba	311310	11	3288	20.56	caranaibense	159.957
1491	Carangola	311330	11	32296	91.39	carangolense	353.404
1493	Carbonita	311350	11	9148	6.28	carbonitense	1456.093
1494	Careaçu	311360	11	6298	34.79	careaçuense	181.009
1496	Carmésia	311380	11	2446	9.44	carmesense	259.103
1498	Carmo Da Mata	311400	11	10927	30.59	carmense	357.178
1500	Carmo Do Cajuru	311420	11	20012	43.9	cajuruense	455.807
1501	Carmo Do Paranaíba	311430	11	29735	22.74	carmense	1307.862
1503	Carmópolis De Minas	311450	11	17048	42.62	carmopolitano	400.01
1504	Carneirinho	311455	11	9471	4.59	carneirinhense	2063.316
1506	Carvalhópolis	311470	11	3341	41.2	carvalhense	81.101
1508	Casa Grande	311490	11	2244	14.23	casa-grandense	157.727
1510	CÁssia	311510	11	17412	26.15	cassiense	665.802
1511	Cataguases	311530	11	69757	141.85	cataguasense	491.767
1514	Catuji	311545	11	6708	15.99	catujiense	419.525
1515	Catuti	311547	11	5102	17.73	catutiense	287.812
1517	Cedro Do Abaeté	311560	11	1210	4.27	cedrense	283.211
1518	Central De Minas	311570	11	6772	33.14	centralense	204.328
1520	ChÁcara	311590	11	2792	18.27	chacarense	152.807
1522	Chapada Do Norte	311610	11	15189	18.28	chapadense	830.968
2865	Itacuruba	260740	16	4369	10.16	itacurubense	430.031
1525	Cipotânea	311630	11	6547	42.66	cipotanense	153.479
1527	Claro Dos Poções	311650	11	7775	10.79	claro-pocense	720.423
1529	Coimbra	311670	11	7054	66	coimbraense	106.875
1531	Comendador Gomes	311690	11	2972	2.85	comendadorense	1041.047
1533	Conceição Da Aparecida	311710	11	9820	27.86	aparecidense	352.521
1535	Conceição Das Alagoas	311730	11	23043	17.19	garimpense	1340.25
1536	Conceição Das Pedras	311720	11	2749	26.9	pedrense	102.206
1538	Conceição Do Mato Dentro	311750	11	17908	10.37	conceicionense	1726.829
1539	Conceição Do ParÁ	311760	11	5158	20.6	conceição-paraense	250.33
1541	Conceição Dos Ouros	311780	11	10388	56.77	ourense	182.97
1543	Confins	311787	11	5936	140.15	confinense	42.355
1544	Congonhal	311790	11	10468	51.03	congonhalense	205.125
1546	Congonhas Do Norte	311810	11	4943	12.39	congonhense	398.851
1548	Conselheiro Lafaiete	311830	11	116512	314.69	lafaietense	370.245
1550	Consolação	311850	11	1727	19.99	consolense	86.388
1551	Contagem	311860	11	603442	3.09	contagense	195.268
1553	Coração De Jesus	311880	11	26033	11.7	corjesuense	2225.212
1555	Cordislândia	311900	11	3435	19.13	cordislandense	179.543
1556	Corinto	311910	11	23914	9.47	corintiano	2525.394
1558	Coromandel	311930	11	27547	8.31	coromandelense	3313.115
1560	Coronel Murta	311950	11	9117	11.18	murtense	815.411
1562	Coronel Xavier Chaves	311970	11	3301	23.42	xavierense	140.954
1563	Córrego Danta	311980	11	3391	5.16	córrego-dantense	657.425
1565	Córrego Fundo	311995	11	5790	57.26	corregofundense	101.112
1567	Couto De Magalhães De Minas	312010	11	4204	8.66	couto-magalhense	485.653
1568	Crisólita	312015	11	6047	6.26	crisolitense	966.2
1570	CristÁlia	312030	11	5760	6.85	cristalense	840.701
1572	Cristina	312050	11	10210	32.79	cristinense	311.33
1574	Cruzeiro Da Fortaleza	312070	11	3934	20.91	cruzeirense	188.131
1575	Cruzília	312080	11	14591	27.93	cruzilense	522.419
1577	Curral De Dentro	312087	11	6913	12.17	curraldentense	568.262
1579	Datas	312100	11	5211	16.8	datense	310.099
1581	Delfinópolis	312120	11	6830	4.95	delfinopolitano	1378.423
1582	Delta	312125	11	8089	78.66	deltense	102.84
1584	Desterro De Entre Rios	312140	11	7002	18.56	desterrense	377.164
1586	Diamantina	312160	11	45880	11.79	diamantinense	3891.654
1588	Dionísio	312180	11	8739	25.37	dionisiano	344.442
1589	Divinésia	312190	11	3293	28.15	divinesiano	116.97
1591	Divino Das Laranjeiras	312210	11	4937	14.43	divinense	342.248
1593	Divinópolis	312230	11	213016	300.82	divinopolitano	708.115
1594	Divisa Alegre	312235	11	5884	49.95	divisalegrense	117.801
1596	Divisópolis	312245	11	8974	15.66	divisopolense	572.924
1597	Dom Bosco	312247	11	3814	4.67	dom bosquense	817.382
1599	Dom Joaquim	312260	11	4535	11.37	dom-joaquinense	398.821
1601	Dom Viçoso	312280	11	2994	26.28	dom-viçosense	113.921
1603	Dores De Campos	312300	11	9299	74.49	dorense	124.842
1604	Dores De Guanhães	312310	11	5223	13.67	dorense	382.123
1606	Dores Do Turvo	312330	11	4462	19.3	dorense	231.169
1607	Doresópolis	312340	11	1440	9.42	doresopolitano	152.912
1609	Durandé	312352	11	7423	34.13	durandeense	217.461
1611	Engenheiro Caldas	312370	11	10280	54.96	engenheiro-caldense	187.058
1613	Entre Folhas	312385	11	5175	60.73	entrefolhense	85.209
1614	Entre Rios De Minas	312390	11	14242	31.18	entrerrianos	456.796
1616	Esmeraldas	312410	11	60271	66.13	esmeraldense	911.418
1618	Espinosa	312430	11	31113	16.65	espinosense	1868.965
1620	Estiva	312450	11	10845	44.47	estivense	243.872
1621	Estrela Dalva	312460	11	2470	18.8	estrela-dalvense	131.365
1623	Estrela Do Sul	312480	11	7446	9.05	estrela-sulense	822.454
1625	Ewbank Da Câmara	312500	11	3753	36.14	ewbanquense	103.834
1626	Extrema	312510	11	28599	116.93	extremense	244.575
1628	Faria Lemos	312530	11	3376	20.43	faria-lemense	165.224
1630	Felisburgo	312560	11	6877	11.53	felisburguense	596.214
1631	Felixlândia	312570	11	14121	9.08	felixlandense	1554.626
1633	Ferros	312590	11	10837	9.95	ferrense	1088.794
1635	Florestal	312600	11	6600	34.48	florestalense	191.421
1637	Formoso	312620	11	8177	2.22	formosense	3685.696
1638	Fortaleza De Minas	312630	11	4098	18.73	fortalezense	218.792
1640	Francisco Badaró	312650	11	10248	22.21	badarosense	461.344
1642	Francisco SÁ	312670	11	24912	9.07	francisco-saense	2747.283
1644	Frei Gaspar	312680	11	5879	9.38	frei-gasparense	626.671
1645	Frei Inocêncio	312690	11	8920	19	frei-inocenciano	469.556
2867	Itambé	260765	16	35398	116.13	itambeense 	304.811
1649	Fruta De Leite	312707	11	5940	7.79	fruta de leitense	762.783
1650	Frutal	312710	11	53468	22.03	frutalense	2426.966
1652	Galiléia	312730	11	6951	9.65	galileense	720.354
1654	Glaucilândia	312735	11	2962	20.31	glaucilandense	145.861
1655	Goiabeira	312737	11	3053	27.15	goiabeirense	112.442
1657	Gonçalves	312740	11	4220	22.52	gonçalvense	187.353
1659	Gouveia	312760	11	11681	13.48	gouveano	866.6
1661	Grão Mogol	312780	11	15024	3.87	grão-mogolense	3885.286
1662	Grupiara	312790	11	1373	7.11	grupiarense	193.141
1664	Guapé	312810	11	13872	14.85	guapense	934.345
1666	Guaraciama	312825	11	4718	12.09	guaraciamense	390.262
1668	Guarani	312840	11	8678	32.85	guaraniense	264.193
1669	GuararÁ	312850	11	3929	44.32	guararense	88.655
1671	Guaxupé	312870	11	49430	172.59	guaxupeano	286.398
1673	Guimarânia	312890	11	7265	19.8	guimaranense	366.833
1675	Gurinhatã	312910	11	6137	3.32	gurinhantense	1849.138
1677	Iapu	312930	11	10315	30.29	iapuense	340.579
1678	Ibertioga	312940	11	5036	14.54	ibertiogano	346.24
1680	Ibiaí	312960	11	7839	8.96	ibiaiense	874.759
1682	Ibiraci	312970	11	12176	21.66	ibiraciense	562.093
1684	Ibitiúra De Minas	312990	11	3382	49.51	ibitiurense	68.316
1686	Icaraí De Minas	313005	11	10746	17.18	icaraiense	625.663
1687	Igarapé	313010	11	34851	316.07	igarapeense	110.262
1689	Iguatama	313030	11	8029	12.78	iguatamense	628.2
1691	Ilicínea	313050	11	11488	30.53	ilicineaense	376.341
1693	Inconfidentes	313060	11	6908	46.17	inconfidentino	149.611
1694	Indaiabira	313065	11	7330	7.3	indaiabirense	1004.146
1696	Ingaí	313080	11	2629	8.6	ingaiense	305.591
1698	Inhaúma	313100	11	5760	23.51	inhaumense	244.996
1700	Ipaba	313115	11	16708	147.69	ipabense	113.128
1701	Ipanema	313120	11	18170	39.79	ipanemense	456.64
1703	Ipiaçu	313140	11	4107	8.81	ipiaçuense	466.02
1705	Iraí De Minas	313160	11	6467	18.15	iraiense	356.264
1707	Itabirinha	313180	11	10692	51.16	itabirense	208.982
1708	Itabirito	313190	11	45449	83.76	itabiritense	542.609
1710	Itacarambi	313210	11	17720	14.46	itacarambiense	1225.27
1712	Itaipé	313230	11	11798	24.54	itaipeense	480.828
1714	Itamarandiba	313250	11	32175	11.76	itamarandibano	2735.569
1716	Itambacuri	313270	11	22809	16.07	itambacuriense	1419.207
1718	Itamogi	313290	11	10349	42.46	itamogiense	243.734
1719	Itamonte	313300	11	14003	32.43	itamontense	431.786
1721	Itanhomi	313320	11	11856	24.25	itanhomense	488.842
1722	Itaobim	313330	11	21001	30.93	itaobinhense	679.022
1724	Itapecerica	313350	11	21377	20.54	itapecericano	1040.518
1726	Itatiaiuçu	313370	11	9928	33.64	itatiaiuçuense	295.145
1728	Itaúna	313380	11	85463	172.39	itaunense	495.768
1729	Itaverava	313390	11	5799	20.4	itaveravense	284.22
1731	Itueta	313410	11	5830	12.88	ituetano	452.675
1733	Itumirim	313430	11	6139	26.15	itumirinense	234.802
1735	Itutinga	313450	11	3913	10.52	itutinguense	372.018
1737	Jacinto	313470	11	12134	8.71	jacintense	1393.606
1738	Jacuí	313480	11	7502	18.33	jacuiense	409.229
1740	Jaguaraçu	313500	11	2990	18.26	jaguaraçuense	163.76
1742	Jampruca	313507	11	5067	9.8	jampruquense	517.094
1743	Janaúba	313510	11	66803	30.63	janaubense	2181.315
1745	Japaraíba	313530	11	3939	22.88	japaraíbano	172.141
1747	Jeceaba	313540	11	5395	22.84	jeceabense 	236.25
1749	Jequeri	313550	11	12848	23.45	jequeriense	547.897
1750	Jequitaí	313560	11	8005	6.31	jequitaiense	1268.441
1753	Jesuânia	313590	11	4768	30.99	jesuanense	153.852
1754	Joaíma	313600	11	14941	8.98	joaimense	1664.186
1756	João Monlevade	313620	11	73610	742.35	monlevadense	99.158
1758	Joaquim Felício	313640	11	4305	5.44	feliciano	790.934
1759	Jordânia	313650	11	10324	18.88	jordainense	546.704
1761	José Raydan	313655	11	4376	24.2	josé raydanense	180.822
1763	Juatuba	313665	11	22202	223.18	juatubense	99.482
1764	Juiz De Fora	313670	11	516247	359.59	juiz-forano 	1435.664
1766	Juruaia	313690	11	9238	41.92	juruaiense	220.353
1768	Ladainha	313700	11	16994	19.62	ladainhense	866.288
1770	Lagoa Da Prata	313720	11	45984	104.51	lago-pratense	439.984
1771	Lagoa Dos Patos	313730	11	4225	7.04	lagoa-patense	600.546
1773	Lagoa Formosa	313750	11	17161	20.41	lagoense	840.92
1775	Lagoa Santa	313760	11	52520	228.27	lagoa-santense	230.082
1776	Lajinha	313770	11	19609	45.4	lajinhense	431.92
2870	Itaquitinga	260780	16	15692	151.73	itaquitinguense	103.423
1779	Laranjal	313800	11	6465	31.55	laranjalense	204.882
1781	Lavras	313820	11	92200	163.26	lavrense	564.743
1783	Leme Do Prado	313835	11	4804	17.15	lemepradense	280.036
1785	Liberdade	313850	11	5346	13.32	libertense	401.337
1786	Lima Duarte	313860	11	16149	19.03	limaduartino	848.563
1788	Lontra	313865	11	8397	32.4	lontrense	259.2
1790	Luislândia	313868	11	6400	15.54	luislandense	411.714
1792	Luz	313880	11	17486	14.92	luzense	1171.659
1793	Machacalis	313890	11	6976	20.99	machacalisense	332.377
1795	Madre De Deus De Minas	313910	11	4904	9.95	madre-deusense	492.909
1797	Mamonas	313925	11	6321	21.69	mamonense	291.429
1799	Manhuaçu	313940	11	79574	126.65	manhuaçuense	628.318
1800	Manhumirim	313950	11	21382	116.91	manhumiriense	182.899
1802	Mar De Espanha	313980	11	11749	31.62	mar-de-espanhense	371.6
1804	Maria Da Fé	313990	11	14216	70.06	mariense	202.898
1806	Marilac	314010	11	4219	26.57	marilaquense	158.809
1808	MaripÁ De Minas	314020	11	2788	36.05	maripaense	77.338
1809	Marliéria	314030	11	4012	7.35	marlierense	545.812
1811	Martinho Campos	314050	11	12611	12.03	martinho-campense	1048.098
1813	Mata Verde	314055	11	7874	34.61	mataverdense	227.515
1815	Mateus Leme	314070	11	27856	92	mateus-lemense	302.773
1816	Mathias Lobato	317150	11	3370	19.56	matiense	172.302
1818	Matias Cardoso	314085	11	9979	5.12	matiense	1949.734
1820	Mato Verde	314100	11	12684	26.86	mato-verdense	472.244
1821	Matozinhos	314110	11	33955	134.59	matozinhense	252.28
1823	Medeiros	314130	11	3444	3.64	medeirense	946.437
1825	Mendes Pimentel	314150	11	6331	20.72	pimentelense	305.507
1827	Mesquita	314170	11	6069	22.07	mesquitense	274.938
1828	Minas Novas	314180	11	30794	16.99	minas-novense	1812.395
1830	Mirabela	314200	11	13042	18.03	mirabelense	723.276
1832	Miraí	314220	11	13808	43.06	miraiense	320.695
1834	Moeda	314230	11	4689	30.23	moedense	155.112
1836	Monjolos	314250	11	2360	3.63	monjolense	650.91
1838	Montalvânia	314270	11	15862	10.55	montalvanense	1503.783
1839	Monte Alegre De Minas	314280	11	19619	7.56	monte-alegrense	2595.957
1841	Monte Belo	314300	11	13061	31	monte-belano	421.283
1843	Monte Formoso	314315	11	4656	12.08	monte formosense	385.552
1845	Monte Sião	314340	11	21203	72.71	monte-sionense	291.594
1846	Montes Claros	314330	11	361915	101.41	montes-clarense	3568.935
1848	Morada Nova De Minas	314350	11	8255	3.96	moradense	2084.274
1850	Morro Do Pilar	314370	11	3399	7.12	morrense	477.548
1851	Munhoz	314380	11	6257	32.66	munhozense	191.564
1853	Mutum	314400	11	26661	21.31	mutuense	1250.822
1855	Nacip Raydan	314420	11	3154	13.51	nacipense	233.493
1856	Nanuque	314430	11	40834	26.9	nanuquense	1517.966
1858	Natalândia	314437	11	3280	7	natalandense	468.659
1860	Nazareno	314450	11	7954	24.17	nazarenense	329.128
1862	Ninheira	314465	11	9815	8.86	ninheirense	1108.232
1864	Nova Era	314470	11	17528	48.43	nova-erense	361.926
1865	Nova Lima	314480	11	80998	188.78	nova-limense	429.063
1867	Nova Ponte	314500	11	12812	11.53	nova-pontense	1111.011
1869	Nova Resende	314510	11	15374	39.41	resendense	390.152
1871	Nova União	313660	11	5555	32.27	nova-uniense	172.131
1872	Novo Cruzeiro	314530	11	30725	18.04	novo-cruzeirense	1702.978
1874	Novorizonte	314537	11	4963	18.26	novorizontino	271.87
1876	Olhos D`Água	314545	11	5267	2.52	olhos-d'aguense	2092.075
1878	Oliveira	314560	11	39466	43.98	oliveirense	897.294
1879	Oliveira Fortes	314570	11	2123	19.1	oliveira-fortense	111.133
1881	Oratórios	314585	11	4493	50.44	oratoriense	89.068
1883	Ouro Branco	314590	11	35268	136.31	ouro-branquense	258.726
1884	Ouro Fino	314600	11	31568	59.15	ouro-finense	533.659
1886	Ouro Verde De Minas	314620	11	6016	34.28	ouro-verdense	175.482
1888	Padre Paraíso	314630	11	18849	34.63	padre-paraisense	544.374
1890	Paineiras	314640	11	4631	7.27	paineirense	637.308
1891	Pains	314650	11	8014	19	painense	421.862
1893	Palma	314670	11	6545	20.68	palmense	316.486
1895	Papagaios	314690	11	14175	25.61	papagaiense	553.576
1897	Paracatu	314700	11	84718	10.29	paracatuense	8229.588
1898	Paraguaçu	314720	11	20245	47.71	paraguaçuense	424.296
1900	Paraopeba	314740	11	22563	36.06	paraopebense	625.623
1902	Passa Tempo	314770	11	8197	19.1	passa-tempense	429.172
1904	Passabém	314750	11	1766	18.75	passabenense	94.182
1905	Passos	314790	11	106290	79.44	passense	1338.07
2728	Santa Helena	251330	15	5369	25.53	santa-helenense	210.321
1908	Patrocínio	314810	11	82471	28.69	patrocinense	2874.343
1910	Paula Cândido	314830	11	9271	34.55	paula-candense	268.321
1912	Pavão	314850	11	8589	14.29	pavoense	601.189
1913	Peçanha	314860	11	17260	17.32	peçanhense	996.645
1915	Pedra Bonita	314875	11	6673	38.37	pedrabonitense	173.928
1917	Pedra Do IndaiÁ	314890	11	3875	11.14	andaiaense	347.92
1919	Pedralva	314910	11	11467	52.6	pedralvense	217.99
1920	Pedras De Maria Da Cruz	314915	11	10315	6.76	pedrense	1525.49
1922	Pedro Leopoldo	314930	11	58740	200.49	pedro-leopoldense	292.989
1924	Pequeri	314950	11	3165	34.84	pequeriense	90.833
1925	Pequi	314960	11	4076	19.98	pequiense	203.991
1927	Perdizes	314980	11	14404	5.88	perdizense	2450.814
1929	Periquito	314995	11	7036	30.74	periquitense	228.907
1931	Piau	315010	11	2841	14.78	piauense	192.196
1933	Piedade De Ponte Nova	315020	11	4062	48.51	piedadense	83.733
1934	Piedade Do Rio Grande	315030	11	4709	14.59	piedadense	322.814
1936	Pimenta	315050	11	8236	19.85	pimentense	414.969
1938	Pintópolis	315057	11	7211	5.87	pintopolense	1228.734
1939	Piracema	315060	11	6406	22.85	piracemense	280.335
1941	Piranga	315080	11	17232	26.16	piranguense	658.811
1943	Piranguinho	315100	11	8016	64.23	piranguinhense	124.803
1944	Pirapetinga	315110	11	10364	54.35	pirapetinguense	190.676
1946	Piraúba	315130	11	10862	75.28	piraubano	144.289
1948	Piumhi	315150	11	31883	35.33	piuiense	902.468
1950	Poço Fundo	315170	11	15959	33.65	poço-fundense	474.244
1952	Pocrane	315190	11	8986	13	pocranense	691.065
1953	Pompéu	315200	11	29105	11.41	pompeano	2551.072
1955	Ponto Chique	315213	11	3966	6.58	ponto chiquense	602.798
1957	Porteirinha	315220	11	37627	21.51	porteirinhense	1749.679
1958	Porto Firme	315230	11	10417	36.58	porto-firmense	284.777
1960	Pouso Alegre	315250	11	130615	240.51	pouso-alegrense	543.068
1962	Prados	315270	11	8391	31.77	pradense	264.115
1964	PratÁpolis	315290	11	8807	40.86	pratapolense	215.516
1966	Presidente Bernardes	315310	11	5537	23.38	bernardense	236.798
1968	Presidente Kubitschek	315330	11	2959	15.64	kubitschekano	189.235
1969	Presidente OlegÁrio	315340	11	18577	5.3	olegariense	3503.795
1971	Quartel Geral	315370	11	3303	5.94	quartelense	556.435
1972	Queluzito	315380	11	1861	12.12	queluzitano	153.56
1974	Raul Soares	315400	11	23818	31.2	raul-soarense	763.364
1976	Reduto	315415	11	6569	43.26	redutense	151.859
1978	Resplendor	315430	11	17089	15.8	resplendorense	1081.794
1979	Ressaquinha	315440	11	4711	25.52	ressaquinhense	184.609
1981	Riacho Dos Machados	315450	11	9360	7.11	riachense	1315.537
1983	Ribeirão Vermelho	315470	11	3826	77.68	ribeirense	49.251
1985	Rio Casca	315490	11	14201	36.95	rio-casquense	384.362
1986	Rio Do Prado	315510	11	5217	10.87	rio-pradense	479.814
1988	Rio Espera	315520	11	6070	25.44	rio-esperense	238.602
1990	Rio Novo	315540	11	8712	41.62	rio-novense	209.31
1992	Rio Pardo De Minas	315560	11	29099	9.33	rio-pardense	3117.43
1993	Rio Piracicaba	315570	11	14149	37.93	piracicabense	373.037
1995	Rio Preto	315590	11	5292	15.2	rio-pretense	348.14
1997	RitÁpolis	315610	11	4925	12.17	ritapolitano	404.804
1999	Rodeiro	315630	11	6867	94.49	rodeirense	72.673
2000	Romaria	315640	11	3596	8.82	romariense	407.557
2002	Rubelita	315650	11	7772	7	rubelitense	1110.292
2004	SabarÁ	315670	11	126269	417.87	sabaraense	302.173
2006	Sacramento	315690	11	23896	7.78	sacramentense	3073.268
2007	Salinas	315700	11	39178	20.75	salinense	1887.642
2009	Santa BÁrbara	315720	11	27876	40.75	santa-barbarense	684.059
2011	Santa BÁrbara Do Monte Verde	315727	11	2788	6.67	barbarense	417.83
2012	Santa BÁrbara Do Tugúrio	315730	11	4570	23.49	tugurense	194.562
2014	Santa Cruz De Salinas	315737	11	4397	7.46	santacruzense	589.572
2016	Santa Efigênia De Minas	315750	11	4600	34.86	santa-efigense	131.965
2017	Santa Fé De Minas	315760	11	3968	1.36	santa-feense	2917.445
2019	Santa Juliana	315770	11	11337	15.66	santa-julianense	723.783
2020	Santa Luzia	315780	11	202942	862.38	luziense	235.327
2022	Santa Maria De Itabira	315800	11	10552	17.66	santa-mariense	597.437
2024	Santa Maria Do Suaçuí	315820	11	14395	23.07	santa-mariense	624.046
2026	Santa Rita De Ibitipoca	315940	11	3583	11.05	ibitipoquense	324.234
2027	Santa Rita De Jacutinga	315930	11	4993	11.86	santa-ritense 	420.94
2029	Santa Rita Do Itueto	315950	11	5697	11.74	santa-ritense 	485.081
2033	Santana Da Vargem	315830	11	7231	41.93	vargense	172.444
2035	Santana De Pirapama	315850	11	8009	6.38	pirapamenho	1255.83
2036	Santana Do Deserto	315860	11	3860	21.13	santanense	182.655
2038	Santana Do Jacaré	315880	11	4607	43.39	santanense	106.169
2040	Santana Do Paraíso	315895	11	27265	98.76	paraisense	276.067
2041	Santana Do Riacho	315900	11	4023	5.94	riachense	677.206
2043	Santo Antônio Do Amparo	315990	11	17345	35.48	amparense	488.884
2045	Santo Antônio Do Grama	316010	11	4085	31.37	gramense	130.212
2046	Santo Antônio Do Itambé	316020	11	4135	13.52	itambeano	305.736
2048	Santo Antônio Do Monte	316040	11	25975	23.07	santo-antoniense	1125.779
2050	Santo Antônio Do Rio Abaixo	316050	11	1777	16.57	santo-antoniense	107.269
2051	Santo Hipólito	316060	11	3238	7.52	santo-hipolitense	430.656
2053	São Bento Abade	316080	11	4577	56.93	são-bentista	80.403
2055	São Domingos Das Dores	316095	11	5408	88.85	sandominguense	60.865
2056	São Domingos Do Prata	316100	11	17357	23.34	pratense	743.767
2058	São Francisco	316110	11	53828	16.27	são-franciscano	3308.094
2060	São Francisco De Sales	316130	11	5776	5.12	são-francisco-salense	1128.865
2062	São Geraldo	316150	11	10263	55.3	são-geraldense	185.578
2063	São Geraldo Da Piedade	316160	11	4389	28.81	são-geraldense	152.336
2065	São Gonçalo Do Abaeté	316170	11	6264	2.33	são-gonçalense	2692.168
2066	São Gonçalo Do ParÁ	316180	11	10398	39.13	são-gonçalense	265.73
2068	São Gonçalo Do Rio Preto	312550	11	3056	9.72	são-gonçalense	314.458
2070	São Gotardo	316210	11	31819	36.74	são-gotardense	866.087
2071	São João Batista Do Glória	316220	11	6887	12.57	gloriense	547.908
2073	São João Da Mata	316230	11	2731	22.66	são-joanense-da-mata	120.536
2075	São João Das Missões	316245	11	11715	17.27	missionense	678.273
2076	São João Del Rei	316250	11	84469	57.68	são-joanense	1464.327
2078	São João Do Manteninha	316257	11	5188	37.61	manteniense	137.927
2080	São João Do Pacuí	316265	11	4060	9.76	pacuíense	415.921
2081	São João Do Paraíso	316270	11	22319	11.59	paraisense	1925.571
2083	São João Nepomuceno	316290	11	25057	61.5	são-joanense	407.427
2085	São José Da Barra	316294	11	6778	21.57	são josé barrense	314.253
2086	São José Da Lapa	316295	11	19799	413.09	lapense	47.929
2088	São José Da Varginha	316310	11	4198	20.43	varginense-de-são-josé	205.501
2090	São José Do Divino	316330	11	3834	11.66	são-josé-divinense	328.703
2091	São José Do Goiabal	316340	11	5636	30.55	goiabalense	184.511
2093	São José Do Mantimento	316360	11	2592	47.38	mantimentense	54.701
2095	São Miguel Do Anta	316380	11	6760	44.44	são-miguelense	152.111
2096	São Pedro Da União	316390	11	5040	19.32	são-pedrense	260.827
2098	São Pedro Dos Ferros	316400	11	8356	20.75	ferrense	402.757
2099	São Romão	316420	11	10276	4.22	são-romano	2434.001
2101	São Sebastião Da Bela Vista	316440	11	4948	29.6	bela-vistense	167.157
2103	São Sebastião Do Anta	316447	11	5739	71.19	antense	80.618
2104	São Sebastião Do Maranhão	316450	11	10647	20.56	maranhense	517.829
2106	São Sebastião Do Paraíso	316470	11	64980	79.74	paraisense	814.925
2108	São Sebastião Do Rio Verde	316490	11	2110	22.89	rio-verdense	92.194
2109	São Thomé Das Letras	316520	11	6655	18	são-tomeense 	369.747
2110	São Tiago	316500	11	10561	18.45	são-tiaguense	572.4
2112	São Vicente De Minas	316530	11	7008	17.85	vicenciano	392.651
2114	SardoÁ	316550	11	5594	39.42	sardoense	141.904
2115	Sarzedo	316553	11	25814	415.46	sarzedense	62.134
2117	Senador Amaral	316557	11	5219	34.54	amaralense	151.098
2119	Senador Firmino	316570	11	7230	43.42	firminense	166.495
2121	Senador Modestino Gonçalves	316590	11	4574	4.8	modestinense	952.054
2122	Senhora De Oliveira	316600	11	5683	33.28	oliveirense	170.749
2124	Senhora Dos Remédios	316620	11	10196	42.87	remediense	237.816
2126	Seritinga	316640	11	1789	15.59	seritinguense	114.769
2127	Serra Azul De Minas	316650	11	4220	19.31	serra-azulense	218.594
2129	Serra Do Salitre	316680	11	10549	8.14	serralitrense 	1295.272
2131	Serrania	316690	11	7542	36.04	serraniense	209.27
2133	Serranos	316700	11	1995	9.36	serranense	213.173
2134	Serro	316710	11	20835	17.11	serrano	1217.812
2136	Setubinha	316555	11	10885	20.36	setubinhense	534.654
2137	Silveirânia	316730	11	2192	13.92	silveiranense	157.456
2139	Simão Pereira	316750	11	2537	18.7	simonense	135.689
2141	SobrÁlia	316770	11	5830	28.19	sobraliense	206.786
2143	Tabuleiro	316790	11	4079	19.32	tabuleirense	211.084
2144	Taiobeiras	316800	11	30917	25.88	taiobeirense	1194.524
2731	Santa Rita	251370	15	120310	165.52	santa-ritense	726.843
2147	Tapiraí	316820	11	1873	4.59	tapiraiense	407.919
2149	Tarumirim	316840	11	14293	19.53	tarumirinhense	731.752
2150	Teixeiras	316850	11	11355	68.1	teixeirense	166.735
2152	Timóteo	316870	11	81243	562.7	timotense	144.381
2154	Tiros	316890	11	6906	3.3	tirense	2091.772
2155	Tocantins	316900	11	15823	91.01	tocantinense	173.866
2157	Toledo	316910	11	5764	42.14	toledense	136.776
2159	Três Corações	316930	11	72765	87.88	tricordiano	828.038
2161	Três Pontas	316940	11	53860	78.08	três-pontano	689.794
2162	Tumiritinga	316950	11	6293	12.58	tumiritinguense	500.072
2164	Turmalina	316970	11	18055	15.66	turmalinense	1153.109
2166	UbÁ	316990	11	101519	249.16	ubaense	407.452
2167	Ubaí	317000	11	11681	14.24	ubaiense	820.523
2169	Uberaba	317010	11	295988	65.43	uberabense	4523.957
2171	Umburatiba	317030	11	2705	6.67	umburatibense	405.832
2173	União De Minas	317043	11	4418	3.85	uniense	1147.407
2174	Uruana De Minas	317047	11	3235	5.41	uruanense	598.501
2176	Urucuia	317052	11	13604	6.55	urucuiano	2076.939
2178	Vargem Bonita	317060	11	2163	5.28	vargiano	409.888
2180	Varginha	317070	11	123081	311.29	varginhense	395.396
2181	Varjão De Minas	317075	11	6054	9.29	varjonense	651.555
2183	Varzelândia	317090	11	19116	23.46	varzelandense	814.993
2184	Vazante	317100	11	19723	10.31	vazantino	1913.395
2186	Veredinha	317107	11	5549	8.78	veredinhense	631.69
2188	Vermelho Novo	317115	11	4689	40.69	vermelhense	115.242
2190	Viçosa	317130	11	72220	241.2	viçosense	299.418
2191	Vieiras	317140	11	3731	33.11	vieirense	112.691
2193	Virgínia	317170	11	8623	26.41	virginense	326.515
2195	Virgolândia	317190	11	5658	20.13	virgolandense	281.022
2197	Volta Grande	317210	11	5070	24.36	volta-grandense	208.131
2198	Wenceslau Braz	317220	11	2553	24.91	wenceslauense	102.487
2200	Alcinópolis	500025	12	4569	1.04	alcinopolense	4399.685
2202	Anastácio	500070	12	23835	8.08	anastaciano	2949.135
2203	Anaurilândia	500080	12	8493	2.5	anaurilandense	3395.443
2205	Antônio João	500090	12	8208	7.17	antônio-joanense	1145.178
2207	Aquidauana	500110	12	45614	2.69	aquidauanense	16957.783
2209	Bandeirantes	500150	12	6609	2.12	bandeirantense	3115.688
2210	Bataguassu	500190	12	19839	8.21	bataguassuense	2415.301
2212	Bela Vista	500210	12	23181	4.74	bela-vistense	4892.616
2214	Bonito	500220	12	19587	3.97	bonitense	4934.425
2216	Caarapó	500240	12	25767	12.33	caarapoense	2089.605
2217	Camapuã	500260	12	13625	2.19	camapuense 	6229.628
2219	Caracol	500280	12	5398	1.84	caracolense	2940.259
2221	Chapadão Do Sul	500295	12	19648	5.1	chapadense	3851.004
2222	Corguinho	500310	12	4862	1.84	corguinhense 	2639.855
2224	Corumbá	500320	12	103703	1.6	corumbaense	64962.836
2226	Coxim	500330	12	32159	5.02	coxinense	6409.232
2227	Deodápolis	500345	12	12139	14.6	deodapolense	831.213
2229	Douradina	500350	12	5364	19.1	douradinense	280.787
2231	Eldorado	500375	12	11694	11.49	eldoradense	1017.788
2233	Figueirão	500390	12	2928	0.6		4882.879
2234	Glória De Dourados	500400	12	9927	20.19	glória-douradense	491.749
2236	Iguatemi	500430	12	14875	5.05	iguatemiense	2946.524
2237	Inocência	500440	12	7669	1.33	inocentino	5776.033
2239	Itaquiraí	500460	12	18614	9.02	itaquirense 	2063.785
2241	Japorã	500480	12	7731	18.43	japoraense 	419.398
2243	Jardim	500500	12	24346	11.06	jardinense	2201.52
2245	Juti	500515	12	5900	3.72	jutiense	1584.543
2246	Ladário	500520	12	19617	57.57	ladarense	340.765
2248	Maracaju	500540	12	37405	7.06	maracajuense 	5299.195
2250	Mundo Novo	500568	12	17043	35.67	mundo-novense	477.783
2252	Nioaque	500580	12	14391	3.67	nioaquense	3923.799
2254	Nova Andradina	500620	12	45585	9.54	nova-andradinense 	4776.011
2255	Novo Horizonte Do Sul	500625	12	4940	5.82	novo horizontino do sul	849.095
2257	Paranhos	500635	12	12350	9.43	paranhense	1309.159
2259	Ponta Porã	500660	12	77872	14.61	ponta-poranense	5330.461
2261	Ribas Do Rio Pardo	500710	12	20946	1.21	rio-pardense	17308.107
2262	Rio Brilhante	500720	12	30663	7.69	rio-brilhantense	3987.405
2264	Rio Verde De Mato Grosso	500740	12	18890	2.32	rio-verdense	8153.911
2266	Santa Rita Do Pardo	500755	12	7259	1.18	santa-ritense	6143.081
2268	Selvíria	500780	12	6287	1.93	selvirense	3258.329
2269	Sete Quedas	500770	12	10780	12.93	sete-quedense	833.735
2271	Sonora	500793	12	14833	3.64	sonorense	4075.426
2733	Santana De Mangueira	251350	15	5331	13.26	santanense	402.15
2274	Terenos	500800	12	17146	6.03	terenense	2844.513
2276	Vicentina	500840	12	5901	19.03	vicentinense	310.164
2278	Água Boa	510020	13	20856	2.77	água-boense	7537.949
2280	Alto Araguaia	510030	13	15644	2.84	araguaiano 	5514.513
2281	Alto Boa Vista	510035	13	5247	2.34	alto boa vistense	2240.445
2283	Alto Paraguai	510050	13	10066	5.45	alto-paraguaiense	1846.3
2285	Apiacás	510080	13	8567	0.42	apiacaense	20379.906
2286	Araguaiana	510100	13	3197	0.5	araguaianense	6429.38
2288	Araputanga	510125	13	15342	9.59	araputanguense	1600.26
2290	Aripuanã	510140	13	18656	0.76	aripuanense	24612.987
2292	Barra Do Bugres	510170	13	31793	5.31	barrense	5992.573
2294	Bom Jesus Do Araguaia	510185	13	5314	1.24	 bom-jesuense 	4274.205
2295	Brasnorte	510190	13	15357	0.96	brasnortense	15959.066
2297	Campinápolis	510260	13	14305	2.45	campinapolense	5835.481
2299	Campo Verde	510267	13	31589	6.61	campo-verdense	4782.116
2300	Campos De Júlio	510268	13	5154	0.76	campo juliense	6801.862
2302	Canarana	510270	13	18754	1.73	canaranense	10854.335
2304	Castanheira	510285	13	8231	2.22	castanheirense	3703.671
2306	Cláudia	510305	13	11028	2.86	claudiense	3849.989
2307	Cocalinho	510310	13	5490	0.33	cocalinhense	16530.643
2309	Colniza	510325	13	26381	0.94	colnizense	27924.534
2310	Comodoro	510330	13	18178	0.83	comodorense	21774.219
2313	Cotriguaçu	510337	13	14983	1.58	cotriguaçuenses	9460.472
2314	Cuiabá	510340	13	551098	163.88	cuiabano (papa peixe)	3362.755
2316	Denise	510345	13	8523	6.52	denisiense	1307.19
2318	Dom Aquino	510360	13	8171	3.71	dom-aquinense	2204.158
2320	Figueirópolis D`Oeste	510380	13	3796	4.22	figueiropolense	899.385
2321	Gaúcha Do Norte	510385	13	6293	0.37	gauchenses-do-norte	16930.653
2323	Glória D`Oeste	510395	13	3135	3.67	glorienses-do-oeste	853.848
2325	Guiratinga	510420	13	13934	2.75	guiratinguense 	5061.689
2327	Ipiranga Do Norte	510452	13	5123	1.48	ipiranguense	3467.047
2328	Itanhangá	510454	13	5276	1.82	itanhangaense	2898.069
2330	Itiquira	510460	13	11478	1.32	itiquirense	8722.486
2332	Jangada	510490	13	7696	6.14	jangadense	1253.768
2334	Juara	510510	13	32791	1.45	juarense	22641.187
2335	Juína	510515	13	39255	1.49	juinense	26395.845
2337	Juscimeira	510520	13	11430	5.18	juscimeirense	2206.045
2339	Lucas Do Rio Verde	510525	13	45556	12.43	luquense	3663.995
2341	Marcelândia	510558	13	12006	0.98	marcelandense	12281.242
2342	Matupá	510560	13	14174	2.71	matupaense	5238.844
2344	Nobres	510590	13	15002	3.85	nobrense	3892.051
2346	Nossa Senhora Do Livramento	510610	13	11609	2.1	livramentense	5540.737
2348	Nova Brasilândia	510620	13	4587	1.4	brasilandense	3281.885
2349	Nova Canaã Do Norte	510621	13	12127	2.03	canaense	5966.193
2351	Nova Lacerda	510618	13	5436	1.15	novo-lacerdenses	4729.876
2353	Nova Maringá	510890	13	6590	0.57	nova maringaense	11557.344
2355	Nova Mutum	510622	13	31649	3.31	mutuense	9556.036
2356	Nova Nazaré	510617	13	3029	0.75	nova-nazareenses	4038.054
2358	Nova Santa Helena	510619	13	3468	1.57	nova-santa-helenenses	2205.057
2360	Nova Xavantina	510625	13	19643	3.47	nova-xavantinense	5667.912
2362	Novo Mundo	510626	13	7332	1.27	novo-mundenses	5790.26
2363	Novo Santo Antônio	510631	13	2005	0.46	novo-santo-antoniense	4393.789
2365	Paranaíta	510629	13	10684	2.23	paranaitense	4796.01
2367	Pedra Preta	510637	13	15755	3.83	pedra-pretense	4108.592
2369	Planalto Da Serra	510645	13	2726	1.11	planaltenses-da-serra	2455.431
2370	Poconé	510650	13	31779	1.84	poconeano	17271.014
2372	Ponte Branca	510670	13	1768	2.58	ponte-branquense	685.98
2374	Porto Alegre Do Norte	510677	13	10748	2.71	porto-alegrense	3972.131
2376	Porto Esperidião	510682	13	11031	1.9	portense	5808.173
2377	Porto Estrela	510685	13	3649	1.77	portoestrelense	2062.757
2379	Primavera Do Leste	510704	13	52066	9.52	primaverense	5471.654
2381	Reserva Do Cabaçal	510715	13	2572	1.92	reservense	1337.044
2383	Ribeirãozinho	510719	13	2199	3.52	ribeirãozense	625.58
2384	Rio Branco	510720	13	5070	9.01	rio-branquense	562.838
2386	Rondonópolis	510760	13	195476	47	rondonopolitano	4159.122
2388	Salto Do Céu	510775	13	3908	2.23	saltense	1752.336
2389	Santa Carmem	510724	13	4085	1.06	santa-carmense 	3855.365
2391	Santa Rita Do Trivelato	510776	13	2491	0.53	trivelatenses	4728.207
2393	Santo Afonso	510726	13	2991	2.55	santo-afonsense 	1174.191
2395	Santo Antônio Do Leverger	510780	13	18463	1.57	santo-antoniense (papa abóbora)	11753.581
2736	São Bentinho	251392	15	4138	21.12	sãobentinhense	195.964
2399	São José Do Xingu	510735	13	5240	0.7	São-xinguano	7459.635
2401	São Pedro Da Cipa	510740	13	4158	12.12	cipense	342.969
2402	Sapezal	510787	13	18094	1.33	sapezalense	13624.249
2403	Serra Nova Dourada	510788	13	1365	0.91	serra douradense	1500.387
2405	Sorriso	510792	13	66521	7.13	sorrisiense	9329.554
2407	Tangará Da Serra	510795	13	83431	7.32	tangaraense	11391.314
2409	Terra Nova Do Norte	510805	13	11291	4.16	terra-novense	2716.993
2410	Tesouro	510810	13	3418	0.82	tesourense	4169.564
2412	União Do Sul	510830	13	3760	0.82	União-sulense	4581.907
2414	Várzea Grande	510840	13	252596	284.45	várzea-grandense	888.004
2415	Vera	510850	13	10235	3.45	verense	2963.49
2417	Vila Rica	510860	13	21382	2.88	vila-riquense	7431.064
2419	Abel Figueiredo	150013	14	6780	11.04	abel-figueiredense	614.269
2421	Afuá	150030	14	35042	4.19	afuaense	8372.759
2422	Água Azul Do Norte	150034	14	25057	3.52	agua-azulense	7113.941
2424	Almeirim	150050	14	33614	0.46	almeiriense 	72954.532
2426	Anajás	150070	14	24759	3.58	anajaense	6921.715
2428	Anapu	150085	14	20543	1.73	anapuense	11895.467
2430	Aurora Do Pará	150095	14	26546	14.65	auroenses	1811.82
2431	Aveiro	150100	14	15849	0.93	aveirense	17073.793
2433	Baião	150120	14	36882	9.81	baionense	3758.282
2435	Barcarena	150130	14	99859	76.21	barcarenense	1310.33
2436	Belém	150140	14	1393399	1.315	belenense	1059.402
2438	Benevides	150150	14	51651	275	benevidense	187.825
2440	Bonito	150160	14	13630	23.23	bonitense	586.734
2442	Brasil Novo	150172	14	15690	2.47	brasil-novense	6362.555
2444	Breu Branco	150178	14	52493	13.32	breuense	3941.913
2445	Breves	150180	14	92860	9.72	brevense	9550.474
2447	Cachoeira Do Arari	150200	14	20443	6.59	cachoeirense	3101.743
2449	Cametá	150210	14	120896	39.23	cametaense	3081.354
2450	Canaã Dos Carajás	150215	14	26716	8.49	canaãnense	3146.397
2452	Capitão Poço	150230	14	51893	17.9	capitão-pocense	2899.54
2454	Chaves	150250	14	21005	1.61	chaveense	13084.897
2456	Conceição Do Araguaia	150270	14	45557	7.81	araguaiano 	5829.466
2457	Concórdia Do Pará	150275	14	28216	40.84	concordiense	690.944
2459	Curionópolis	150277	14	18288	7.72	curionopolense	2368.735
2461	Curuá	150285	14	12254	8.56	curuaense	1431.15
2463	Dom Eliseu	150293	14	51319	9.74	dom-eliseuense	5268.794
2465	Faro	150300	14	8177	0.69	farense	11770.601
2466	Floresta Do Araguaia	150304	14	17768	5.16	floresta-araguaiense	3444.274
2468	Goianésia Do Pará	150309	14	30436	4.33	goianesiense 	7023.884
2469	Gurupá	150310	14	29062	3.4	gurupaense	8540.103
2471	Igarapé Miri	150330	14	58077	29.08	igarapé-miriense	1996.835
2473	Ipixuna Do Pará	150345	14	51309	9.84	ipixunense	5215.533
2475	Itaituba	150360	14	97493	1.57	itaitubense	62040.111
2476	Itupiranga	150370	14	51220	6.5	itupiranguense	7880.08
2478	Jacundá	150380	14	51360	25.57	jacundaense	2008.308
2480	Limoeiro Do Ajuru	150400	14	25021	16.79	ajuruense	1490.18
2482	Magalhães Barata	150410	14	8115	25.07	magalhães-baratense	323.735
2484	Maracanã	150430	14	28376	33.1	maracanaense	857.188
2485	Marapanim	150440	14	26605	33.42	marapaniense	795.983
2487	Medicilândia	150445	14	27328	3.3	medicilandense	8272.604
2489	Mocajuba	150460	14	26731	30.7	mocajubense	870.806
2491	Monte Alegre	150480	14	55462	3.06	montalegrense 	18152.508
2492	Muaná	150490	14	34204	9.08	muanaense	3765.534
2494	Nova Ipixuna	150497	14	14645	9.36	nova-ipixunense	1564.178
2496	Novo Progresso	150503	14	25124	0.66	progressense	38162.371
2498	Óbidos	150510	14	49333	1.76	obidense 	28021.339
2499	Oeiras Do Pará	150520	14	28595	7.42	oeirense	3852.275
2501	Ourém	150540	14	16311	29	ouremense	562.385
2503	Pacajá	150548	14	39979	3.38	pacajaense	11832.261
2504	Palestina Do Pará	150549	14	7475	7.59	palestinenses	984.359
2506	Parauapebas	150553	14	153908	22.12	parauapebense	6957.318
2508	Peixe Boi	150560	14	7854	17.4	peixe-boiense	451.337
2509	Piçarra	150563	14	12697	3.83	Piçarrense	3312.65
2511	Ponta De Pedras	150570	14	25999	7.73	ponta-pedrense	3365.133
2513	Porto De Moz	150590	14	33956	1.95	porto-mozense	17423.225
2515	Primavera	150610	14	10268	39.71	primaverense	258.599
2517	Redenção	150613	14	75556	19.76	redencense	3823.799
2518	Rio Maria	150616	14	17697	4.3	rio-mariense	4114.598
2520	Rurópolis	150619	14	40087	5.71	ruropolense	7021.305
2522	Salvaterra	150630	14	20183	19.42	salvaterrense	1039.068
2872	Jaqueira	260795	16	11501	131.88	jaqueirense	87.208
2526	Santa Luzia Do Pará	150655	14	19424	14.32	santaluziense 	1356.118
2527	Santa Maria Das Barreiras	150658	14	17206	1.67	barreirense	10330.19
2529	Santana Do Araguaia	150670	14	56153	4.84	araguaiense 	11591.538
2530	Santarém	150680	14	294580	12.87	santareno 	22886.761
2532	Santo Antônio Do Tauá	150700	14	26674	49.61	tauaense	537.623
2534	São Domingos Do Araguaia	150715	14	23130	16.61	são dominguense do araguaia	1392.457
2536	São Félix Do Xingu	150730	14	91340	1.08	xinguense 	84213.092
2537	São Francisco Do Pará	150740	14	15060	31.4	franciscano	479.572
2539	São João Da Ponta	150746	14	5265	26.87	são joão pontense 	195.917
2541	São João Do Araguaia	150750	14	13155	10.28	são-joanense	1279.887
2542	São Miguel Do Guamá	150760	14	51567	46.45	guamaense	1110.168
2544	Sapucaia	150775	14	5047	3.89	sapucaiense	1298.186
2545	Senador José Porfírio	150780	14	13045	0.91	porfiriense	14374.229
2547	Tailândia	150795	14	79297	17.9	tailandense	4430.203
2549	Terra Santa	150797	14	16949	8.94	terrasantense	1896.501
2550	Tomé Açu	150800	14	56518	10.98	tomé-açuense	5145.338
2552	Trairão	150805	14	16875	1.41	trairense	11991.063
2554	Tucuruí	150810	14	97128	46.56	tucuruiense	2086.196
2556	Uruará	150815	14	44789	4.15	uruaraense	10791.341
2557	Vigia	150820	14	47889	88.84	vigiense 	539.077
2559	Vitória Do Xingu	150835	14	13431	4.28	vitoriense	3135.168
2561	Água Branca	250010	15	9449	39.94	agua branquense	236.607
2563	Alagoa Grande	250030	15	28479	88.84	alagoa-grandense	320.561
2565	Alagoinha	250050	15	13576	139.99	alagoinhense	96.979
2567	Algodão De Jandaíra	250057	15	2366	10.74	algodoense	220.248
2568	Alhandra	250060	15	18007	98.58	alhandrense	182.663
2570	Aparecida	250077	15	7676	25.96	aparecidense	295.704
2572	Arara	250090	15	12653	127.66	araraense 	99.111
2574	Areia	250110	15	23829	88.42	areiense	269.492
2576	Areial	250120	15	6470	195.22	areialense	33.142
2577	Aroeiras	250130	15	19082	50.93	aroeirense	374.694
2579	Baía Da Traição	250140	15	8012	78.27	baianense 	102.368
2581	Baraúna	250153	15	4220	83.43	baraunense	50.581
2583	Barra De Santana	250157	15	8206	21.77	barrasantense	376.91
2584	Barra De São Miguel	250170	15	5611	9.43	barrense	595.208
2586	Belém	250190	15	17093	170.67	belenense 	100.153
2588	Bernardino Batista	250205	15	3075	60.74	batistense	50.628
2589	Boa Ventura	250210	15	5751	33.71	boa-venturense	170.579
2591	Bom Jesus	250220	15	2400	50.39	bom-jesuense	47.631
2593	Bonito De Santa Fé	250240	15	10804	47.32	bonitense	228.325
2595	Borborema	250270	15	5111	196.74	borboremense 	25.979
2596	Brejo Do Cruz	250280	15	13123	32.9	brejo-cruzense 	398.918
2598	Caaporã	250300	15	20362	135.6	caaporãense	150.167
2600	Cabedelo	250320	15	57944	1.815	cabedelense	31.915
2602	Cacimba De Areia	250340	15	3557	16.14	cacimbense (de Areia) 	220.379
2603	Cacimba De Dentro	250350	15	16748	102.32	cacimbense (de Dentro)	163.686
2605	Caiçara	250360	15	7220	56.44	caiçarense	127.913
2607	Cajazeirinhas	250375	15	3033	10.54	cajazeirinhense	287.893
2609	Camalaú	250390	15	5749	10.57	camalauense	543.685
2610	Campina Grande	250400	15	385213	648.31	campinense	594.179
2612	Caraúbas	250407	15	3899	7.84	caraúbense	497.202
2614	Casserengue	250415	15	7058	35.05	cassereguense	201.38
2616	Catolé Do Rocha	250430	15	28759	52.09	catoleense	552.108
2617	Caturité	250435	15	4543	38.47	caturiteense	118.08
2619	Condado	250450	15	6584	23.44	condadense	280.915
2621	Congo	250470	15	4687	14.06	congoense	333.469
2623	Coxixola	250485	15	1771	10.43	coxixolense	169.877
2625	Cubati	250500	15	6866	50.13	cubatiense	136.966
2626	Cuité	250510	15	19978	26.93	cuiteense	741.835
2628	Cuitegi	250520	15	6889	175.29	cuitegiense	39.301
2630	Curral Velho	250530	15	2505	11.24	curral-velhense	222.956
2631	Damião	250535	15	4900	26.39	damiãoense	185.684
2633	Diamante	250560	15	6616	24.58	diamantense	269.11
2634	Dona Inês	250570	15	10517	63.29	inesense	166.169
2636	Emas	250590	15	3317	13.77	emense	240.899
2638	Fagundes	250610	15	11405	60.34	fagundense	189.025
2640	Gado Bravo	250625	15	8376	43.53	gadobravense	192.405
2641	Guarabira	250630	15	55326	333.81	guarabirense	165.743
2643	Gurjão	250650	15	3159	9.2	gurjaense	343.196
2645	Igaracy	250260	15	6156	32.02	igaraciense	192.259
2647	Ingá	250680	15	18180	63.13	ingaense	287.99
2648	Itabaiana	250690	15	24481	111.86	itabaianense	218.847
2714	Puxinanã	251240	15	12923	177.81	puxinanaense	72.68
2715	Queimadas	251250	15	41049	102.17	queimadense	401.774
2717	Remígio	251270	15	17581	98.77	remigioense 	177.998
2719	Riachão Do Bacamarte	251275	15	4264	111.13	riachonense	38.369
2720	Riachão Do Poço	251276	15	4164	104.35	riachãoense	39.905
2722	Riacho Dos Cavalos	251280	15	8314	31.49	riachoense	264.023
2723	Rio Tinto	251290	15	22976	49.42	rio-tintense	464.883
2725	Salgado De São Félix	251310	15	11976	59.33	salgadense	201.852
2727	Santa Cruz	251320	15	6471	30.79	santa-cruzense	210.164
2729	Santa Inês	251335	15	3539	10.91	santineense	324.423
2730	Santa Luzia	251340	15	14719	32.3	santa-luziense	455.7
2732	Santa Teresinha	251380	15	4581	12.8	santa-teresinhense	357.951
2734	Santana Dos Garrotes	251360	15	7266	20.54	santana-garrotense	353.813
2735	Santo André	251385	15	2638	11.72	santoandreense	225.167
2737	São Bento	251390	15	30879	124.41	são-bentense 	248.199
2739	São Domingos Do Cariri	251394	15	2420	11.06	sãodominguense	218.8
2740	São Francisco	251398	15	3364	35.39	francisquense	95.055
2742	São João Do Rio Do Peixe	250070	15	18201	38.36	são-joanense	474.428
2743	São João Do Tigre	251410	15	4396	5.39	são-joão-tigrense	816.111
2745	São José De Caiana	251430	15	6010	34.08	caianense	176.326
2747	São José De Piranhas	251450	15	19096	28.19	piranhense	677.301
2748	São José De Princesa	251455	15	4219	26.7	sãojoseense	158.023
2750	São José Do Brejo Do Cruz	251465	15	1684	6.66	sãojoseense	253.018
2751	São José Do Sabugi	251470	15	4010	19.38	sabugiense	206.914
2753	São José Dos Ramos	251445	15	5508	56.07	sanjoseense	98.231
2754	São Mamede	251490	15	7748	14.6	são-mamedense	530.725
2756	São Sebastião De Lagoa De Roça	251510	15	11041	221.16	lagoense (de Roça)	49.923
2758	Sapé	251530	15	50143	158.92	sapeense	315.531
2652	Jacaraú	250730	15	13942	55.1	jacarauense	253.008
2654	João Pessoa	250750	15	723515	3.421	pessoense	211.474
2656	Juarez Távora	250760	15	7459	105.29	tavorense	70.841
2657	Juazeirinho	250770	15	16776	35.88	juazeirinhense	467.523
2659	Juripiranga	250790	15	10237	129.84	juripiranguense	78.845
2660	Juru	250800	15	9826	24.37	juruense	403.277
2662	Lagoa De Dentro	250820	15	7370	87.21	lagoa-dentrense	84.508
2664	Lastro	250840	15	2841	27.67	lastrense	102.669
2666	Logradouro	250855	15	3942	103.75	logradourense	37.996
2668	Mãe D`Água	250870	15	4019	16.49	mãe-daguense	243.753
2669	Malta	250880	15	5613	35.93	maltense 	156.241
2671	Manaíra	250900	15	10759	30.52	manairense	352.568
2673	Mari	250910	15	21176	136.78	mariense	154.822
2675	Massaranduba	250920	15	12902	62.64	massarandubense	205.956
2676	Mataraca	250930	15	7407	40.19	mataraquense	184.298
2678	Mato Grosso	250937	15	2702	32.35	matogrossense	83.522
2680	Mogeiro	250940	15	12491	64.41	mogeirense	193.943
2682	Monte Horebe	250960	15	4508	38.8	horebense	116.172
2683	Monteiro	250970	15	30852	31.28	monteirense	986.351
2685	Natuba	250990	15	10566	51.53	natubense	205.041
2687	Nova Floresta	251010	15	10533	222.31	nova-florestense	47.379
2689	Nova Palmeira	251030	15	4361	14.05	nova-palmeirense	310.35
2691	Olivedos	251050	15	3627	11.41	olivedense	317.913
2693	Parari	251065	15	1256	9.78	parariense	128.484
2694	Passagem	251070	15	2233	19.96	passagense	111.875
2696	Paulista	251090	15	11788	20.43	paulistense 	576.897
2698	Pedra Lavrada	251110	15	7475	21.26	pedra-lavradense	351.678
2700	Pedro Régis	251272	15	5765	78.37	pedroregense	73.559
2701	Piancó	251130	15	15465	27.38	piancoense	564.732
2703	Pilar	251150	15	11191	109.29	pilarense 	102.398
2705	Pilõezinhos	251170	15	5155	117.42	pilõezinhense	43.901
2707	Pitimbu	251190	15	17024	124.78	pitimbuense	136.434
2708	Pocinhos	251200	15	17032	27.12	pocinhense	628.08
2710	Poço De José De Moura	251207	15	3978	39.4	pocense	100.971
2712	Prata	251220	15	3854	20.07	prataense	192.01
2759	Seridó	251540	15	10230	37	seridoense	276.47
2761	Serra Da Raiz	251560	15	3204	110.17	serra-raizense 	29.082
2762	Serra Grande	251570	15	2975	35.64	serra-grandense	83.474
2764	Serraria	251590	15	6238	95.53	serrariense	65.299
2766	Sobrado	251597	15	7373	119.42	sobradense	61.742
2767	Solânea	251600	15	26693	115.01	solanense	232.094
2769	Sossêgo	251615	15	3169	20.48	sosseguense	154.747
2771	Sumé	251630	15	16060	19.16	sumeense	838.066
2773	Taperoá	251650	15	14936	22.53	taperoaense	662.904
2774	Tavares	251660	15	14103	59.42	tavarense	237.329
2777	Triunfo	251680	15	9220	41.93	triunfense	219.865
2779	Umbuzeiro	251700	15	9298	51.28	umbuzeirense	181.326
2780	Várzea	251710	15	2504	13.15	varzense	190.446
2782	Vista Serrana	250550	15	3512	57.24	vista-serranense	61.361
2784	Abreu E Lima	260005	16	94429	724.9	abreu-limense	130.265
2786	Afrânio	260020	16	17586	11.8	afraniense	1490.589
2787	Agrestina	260030	16	22679	112.58	agrestinense	201.445
2789	Águas Belas	260050	16	40235	45.41	águas-belense	885.982
2791	Aliança	260070	16	37415	137.16	aliancense	272.788
2793	Amaraji	260090	16	21939	93.38	amarajinense 	234.955
2794	Angelim	260100	16	10202	86.43	angelinense	118.036
2796	Araripina	260110	16	77302	40.84	araripinense	1892.588
2798	Barra De Guabiraba	260130	16	12776	111.44	guabirabense	114.649
2800	Belém De Maria	260150	16	11353	153.96	belenense	73.741
2802	Belo Jardim	260170	16	72432	111.83	belo-jardinense	647.694
2803	Betânia	260180	16	12003	9.65	betaniense	1244.067
2805	Bodocó	260200	16	35158	21.75	bodocense 	1616.494
2807	Bom Jardim	260220	16	37826	173.17	bom-jardinense	218.432
2808	Bonito	260230	16	37566	94.96	bonitense	395.611
2810	Brejinho	260250	16	7307	68.76	brejinhense	106.275
2812	Buenos Aires	260270	16	12537	134.54	buenairense	93.187
2813	Buíque	260280	16	52105	38.66	buiquense	1347.648
2815	Cabrobó	260300	16	30873	18.62	cabroboense 	1657.937
2817	Caetés	260320	16	26577	80.66	caeteense	329.475
2818	Calçado	260330	16	11125	91.23	calçadense	121.947
2820	Camaragibe	260345	16	144466	2.821	camaragibense	51.194
2822	Camutanga	260360	16	8156	217.39	camutanguense	37.518
2823	Canhotinho	260370	16	24521	57.96	canhotinhense	423.08
2825	Carnaíba	260390	16	18574	43.42	carnaibano 	427.8
2827	Carpina	260400	16	74858	516.51	carpinense	144.93
2828	Caruaru	260410	16	314912	342.07	caruaruense 	920.606
2830	Catende	260420	16	37820	182.49	catendense	207.243
2832	Chã De Alegria	260440	16	12404	255.98	alegriense	48.456
2834	Condado	260460	16	24282	270.87	condadense	89.644
2836	Cortês	260480	16	12452	122.9	cortesense	101.315
2837	Cumaru	260490	16	17183	58.8	cumaruense	292.23
2839	Custódia	260510	16	33855	24.11	custodiense	1404.125
2841	Escada	260520	16	63517	183.07	escadense	346.957
2843	Feira Nova	260540	16	20571	190.96	feira-novense	107.725
2845	Ferreiros	260550	16	11430	127.93	ferreirense	89.348
2846	Flores	260560	16	22169	22.27	florense	995.553
2848	Frei Miguelinho	260580	16	14293	67.2	frei-miguelinhense	212.706
2850	Garanhuns	260600	16	129408	282.21	garanhuense	458.55
2852	Goiana	260620	16	75644	150.72	goianense	501.881
2853	Granito	260630	16	6855	13.13	granitense	521.94
2855	Iati	260650	16	18360	28.91	iatiense	635.133
2857	Ibirajuba	260670	16	7534	39.74	ibirajubense	189.595
2859	Iguaraci	260690	16	11779	14.05	iguaraciense	838.127
2861	Inajá	260700	16	19081	16.14	inajaense 	1182.545
2862	Ingazeira	260710	16	4496	18.45	ingazeirense	243.668
2864	Ipubi	260730	16	28120	32.64	ipubiense	861.415
2866	Itaíba	260750	16	26256	24.23	itaibense	1083.718
2868	Itapetim	260770	16	13881	34.29	itapetinense	404.849
2869	Itapissuma	260775	16	23769	320.19	itapissumense	74.235
2871	Jaboatão Dos Guararapes	260790	16	644620	2.493	jaboatãoense	258.566
2873	Jataúba	260800	16	15819	23.53	jataubense	672.179
2875	João Alfredo	260810	16	30743	222.34	alfredense	138.269
2877	Jucati	260825	16	10604	87.92	jucatiense	120.603
2878	Jupi	260830	16	13705	130.54	jupiense	104.99
2880	Lagoa Do Carro	260845	16	16007	229.77	lagoense do carro	69.665
2882	Lagoa Do Ouro	260860	16	12132	61.04	lagoa-do-ourense	198.76
2884	Lagoa Grande	260875	16	22760	12.29	lagoa-grandense	1852.34
2885	Lajedo	260880	16	36628	193.7	lajedense	189.095
2887	Macaparana	260900	16	23925	221.43	macaparanense 	108.048
2889	Manari	260915	16	18083	47.43	manariense	381.275
2891	Mirandiba	260930	16	14308	17.41	mirandibense	821.672
2893	Moreno	260940	16	56696	289.16	morenense	196.071
2894	Nazaré Da Mata	260950	16	30796	204.95	nazareno	150.263
2896	Orobó	260970	16	22878	164.99	orobense 	138.661
2898	Ouricuri	260990	16	64358	26.56	ouricuriense 	2422.882
2899	Palmares	261000	16	59526	175.44	palmarense	339.29
2901	Panelas	261020	16	25645	69.14	panelense	370.938
2903	Parnamirim	261040	16	20224	7.79	parnamirinense 	2595.917
2905	Paudalho	261060	16	51357	185.07	paudalhense	277.507
5414	Valparaíso	355630	26	22576	26.33	valparaisense	857.505
2908	Pesqueira	261090	16	62931	63.21	pesqueirense	995.531
2909	Petrolândia	261100	16	32492	30.75	petrolandense	1056.59
2911	Poção	261120	16	11242	45.56	poçãoense	246.747
2913	Primavera	261140	16	13439	121.97	primaverense	110.185
2915	Quixaba	261153	16	6739	31.98	quixabense	210.704
2916	Recife	261160	16	1537704	7.037	recifense	218.498
2918	Ribeirão	261180	16	44439	154.36	ribeirãoense	287.9
2920	Sairé	261200	16	11240	58.85	saireense	191.009
2922	Salgueiro	261220	16	56629	33.57	salgueirense	1686.805
2923	Saloá	261230	16	15309	60.73	saloaense	252.078
2925	Santa Cruz	261245	16	13594	10.82	santacruzense	1255.931
2927	Santa Cruz Do Capibaribe	261250	16	87582	261.23	santa-cruzense	335.271
2929	Santa Maria Da Boa Vista	261260	16	39435	13.14	boa-vistense	3001.165
2930	Santa Maria Do Cambucá	261270	16	13021	141.31	santa-mariense	92.147
2932	São Benedito Do Sul	261290	16	13941	86.87	são-beneditense	160.476
2934	São Caitano	261310	16	35274	92.23	são-caitanense	382.463
2935	São João	261320	16	21312	82.5	são-joanense	258.333
2937	São José Da Coroa Grande	261340	16	18180	262.19	são-josé-coroa-grandense	69.338
2939	São José Do Egito	261360	16	31829	39.84	egipsiense	798.873
2941	São Vicente Ferrer	261380	16	17000	149.14	são-vicentino	113.984
2942	Serra Talhada	261390	16	79232	26.59	serra-talhadense	2979.991
2944	Sertânia	261410	16	33787	13.95	sertaniense	2421.514
2946	Solidão	261440	16	5744	41.5	solidanense	138.398
2947	Surubim	261450	16	58515	231.42	surubinense	252.854
2949	Tacaimbó	261470	16	12725	55.91	tacaimboense	227.599
2951	Tamandaré	261485	16	20715	96.66	tamandareense	214.306
2953	Terezinha	261510	16	6737	44.48	terezinhense	151.449
2954	Terra Nova	261520	16	9278	28.87	terra-novense	321.427
2956	Toritama	261540	16	35554	1.383	toritamense 	25.704
2958	Trindade	261560	16	26116	113.77	trindadense	229.545
2960	Tupanatinga	261580	16	24425	27.62	tupanatinguense	884.413
2961	Tuparetama	261590	16	7925	44.38	tuparetamense	178.569
2963	Verdejante	261610	16	9142	19.2	verdejantense	476.037
2965	Vertentes	261620	16	18222	92.82	vertentense	196.324
2967	Vitória De Santo Antão	261640	16	129974	349.58	vitoriense	371.803
2968	Xexéu	261650	16	14093	127.18	xexeuense	110.812
2970	Agricolândia	220010	17	5098	45.35	agricolandiense	112.424
2972	Alagoinha Do Piauí	220025	17	7341	13.77	alagoinense	532.979
2974	Alto Longá	220030	17	13646	7.85	longaense	1737.828
2975	Altos	220040	17	38822	40.54	altoense	957.65
2977	Amarante	220050	17	17135	14.83	amarantino	1155.197
2979	Anísio De Abreu	220070	17	9098	26.93	anisiense	337.876
2981	Aroazes	220090	17	5779	7.03	aroazense	821.659
2982	Aroeiras Do Itaim	220095	17	2440	9.49		257.138
2984	Assunção Do Piauí	220105	17	7503	4.44	assunçãoense	1690.694
2985	Avelino Lopes	220110	17	11067	8.48	avelino-lopense	1305.518
2987	Barra D`Alcântara	220117	17	3852	14.63	barra dalcantarense	263.381
2989	Barreiras Do Piauí	220130	17	3234	1.59	barreirense	2028.287
2991	Batalha	220150	17	25774	16.22	batalhense	1588.892
2993	Belém Do Piauí	220157	17	3284	13.5	belenense	243.281
2994	Beneditinos	220160	17	9911	12.57	beneditinense	788.58
2996	Betânia Do Piauí	220173	17	6015	10.65	betanhense	564.709
2997	Boa Hora	220177	17	6296	18.65	boa horense	337.566
2999	Bom Jesus	220190	17	22629	4.14	bom-jesuense	5469.161
3001	Bonfim Do Piauí	220192	17	5393	18.65	bonfinense	289.208
3003	Brasileira	220196	17	7966	9.04	brasileirense	880.906
3004	Brejo Do Piauí	220198	17	3850	1.76	brejense	2183.346
3006	Buriti Dos Montes	220202	17	7974	3.01	buritiense	2652.093
3008	Cajazeiras Do Piauí	220207	17	3343	6.5	cajazerense	514.361
3009	Cajueiro Da Praia	220208	17	7163	26.36	cajueirense	271.705
3011	Campinas Do Piauí	220210	17	5408	6.51	campinense	831.197
3012	Campo Alegre Do Fidalgo	220211	17	4693	7.13	campo alegrense	657.811
3014	Campo Largo Do Piauí	220217	17	6803	14.24	campolargoense	477.792
3015	Campo Maior	220220	17	45177	26.96	campo-maiorense	1675.706
3018	Capitão De Campos	220240	17	10953	18.5	capitão-de-campense	592.164
3019	Capitão Gervásio Oliveira	220245	17	3878	3.42	gervasense	1134.137
3021	Caraúbas Do Piauí	220253	17	5525	11.72	carubense	471.45
3023	Castelo Do Piauí	220260	17	18336	9.01	castelense	2035.182
3024	Caxingó	220265	17	5039	10.32	caxingoense	488.166
3026	Cocal De Telha	220271	17	4525	16.04	cocatelhense	282.104
3028	Coivaras	220273	17	3811	7.85	coivarense	485.491
5416	Vargem Grande Do Sul	355640	26	39266	146.94	vargem-grandense	267.232
3032	Coronel José Dias	220285	17	4541	2.37	coronelino	1914.811
3033	Corrente	220290	17	25407	8.33	correntino	3048.418
3035	Cristino Castro	220310	17	9981	5.41	cristino-castrense	1846.296
3037	Currais	220323	17	4704	1.49	curralense	3156.647
3039	Curralinhos	220325	17	4183	12.09	curralinhense	345.846
3040	Demerval Lobão	220330	17	13278	61.24	morrinhense	216.805
3042	Dom Expedito Lopes	220340	17	6569	29.99	dom-expedito-lopense	219.072
3044	Domingos Mourão	220342	17	4264	5.04	domingos-mouronense	846.839
3046	Eliseu Martins	220360	17	4665	4.28	eliseu-martinino 	1090.446
3047	Esperantina	220370	17	37767	41.45	esperantinense	911.21
3049	Flores Do Piauí	220380	17	4366	4.61	florentino-do-piauí	946.727
3051	Floriano	220390	17	57690	16.92	florianense	3409.634
3053	Francisco Ayres	220410	17	4477	6.82	airense	656.472
3054	Francisco Macedo	220415	17	2879	18.54	francisco macedense	155.278
3056	Fronteiras	220430	17	11117	14.33	fronteirense	775.677
3058	Gilbués	220440	17	10402	2.98	gilbuense	3494.947
3059	Guadalupe	220450	17	10268	10.03	guadalupense	1023.588
3061	Hugo Napoleão	220460	17	3771	16.8	hugo-napoleonense	224.454
3063	Inhuma	220470	17	14845	15.18	inhumense	978.212
3065	Isaías Coelho	220490	17	8221	10.59	isaiense	776.05
3067	Itaueira	220510	17	10678	4.18	itaueirense	2554.17
3068	Jacobina Do Piauí	220515	17	5722	4.17	jacobinense	1370.724
3070	Jardim Do Mulato	220525	17	4309	8.45	jardimulatense	509.849
3072	Jerumenha	220530	17	4390	2.35	jerumenhense	1867.305
3073	João Costa	220535	17	2960	1.64	joão costense	1800.236
3075	Joca Marques	220545	17	5100	30.64	jocamarquense	166.442
3077	Juazeiro Do Piauí	220551	17	4757	5.75	juazeirense	827.236
3079	Jurema	220553	17	4517	3.55	juremense	1271.883
3080	Lagoa Alegre	220555	17	8008	20.29	lagoalegrense	394.659
3082	Lagoa Do Barro Do Piauí	220556	17	4523	3.58	lagoa do barrense	1261.936
3084	Lagoa Do Sítio	220559	17	4850	6.03	sitiolagoense	804.7
3085	Lagoinha Do Piauí	220554	17	2656	39.35	lagoinense	67.503
3087	Luís Correia	220570	17	28406	26.52	luís-correiense	1070.922
3089	Madeiro	220585	17	7816	44.12	madeirense	177.152
3091	Marcolândia	220595	17	7812	54.3	marcolandense	143.875
3092	Marcos Parente	220600	17	4456	6.58	marcos-parentense	677.411
3094	Matias Olímpio	220610	17	10473	46.26	matiense	226.373
3096	Miguel Leão	220630	17	1253	13.4	leonino	93.514
3098	Monsenhor Gil	220640	17	10333	18.17	monsenhorgilense	568.728
3099	Monsenhor Hipólito	220650	17	7391	18.41	hipolitano	401.432
3101	Morro Cabeça No Tempo	220665	17	4068	1.92	morrense	2116.929
3103	Murici Dos Portelas	220669	17	8464	17.57	muriciense	481.705
3104	Nazaré Do Piauí	220670	17	7321	5.56	nazareno-do-piauí	1315.833
3106	Nossa Senhora De Nazaré	220675	17	4556	12.79	nazareno	356.262
3108	Nova Santa Rita	220795	17	4187	4.6	Santaritense 	909.731
3109	Novo Oriente Do Piauí	220690	17	6498	12.37	novo-orientino	525.331
3111	Oeiras	220700	17	35640	13.19	oeirense	2702.481
3113	Padre Marcos	220720	17	6657	24.47	padre-marquense	272.034
3114	Paes Landim	220730	17	4059	10.11	paes-landinense	401.377
3116	Palmeira Do Piauí	220740	17	4993	2.47	palmeirino	2023.505
3117	Palmeirais	220750	17	13745	9.17	palmeirense	1499.175
3119	Parnaguá	220760	17	10276	3	parnaguaense	3429.272
3121	Passagem Franca Do Piauí	220775	17	4546	5.35	passagemfranquense	849.604
3123	Pau D`Arco Do Piauí	220779	17	3757	8.72	paudarquiense	430.814
3125	Pavussu	220785	17	3663	3.36	pavussuense	1090.695
3126	Pedro Ii	220790	17	37496	24.7	pedro-segundense	1518.225
3128	Picos	220800	17	73414	137.3	picoense	534.713
3130	Pio Ix	220820	17	17671	9.08	pio-nonense	1947.148
3132	Piripiri	220840	17	61834	43.89	piripiriense	1408.926
3133	Porto	220850	17	11897	47.08	portuense	252.714
3135	Prata Do Piauí	220860	17	3085	15.71	pratense	196.325
3137	Redenção Do Gurguéia	220870	17	8400	3.4	gurgueíno	2467.999
3139	Riacho Frio	220885	17	4241	1.91	riacho friense	2222.107
3140	Ribeira Do Piauí	220887	17	4263	4.25	ribeirense	1004.223
3142	Rio Grande Do Piauí	220900	17	6273	9.86	rio-grandense-do-piauí	635.949
3144	Santa Cruz Dos Milagres	220915	17	3794	3.87	santacruzense	979.652
3145	Santa Filomena	220920	17	6096	1.15	filomense 	5285.42
3147	Santa Rosa Do Piauí	220937	17	5149	15.14	santarosense	340.196
3149	Santo Antônio De Lisboa	220940	17	6007	15.51	santo-antoense	387.401
3273	Colorado	410590	18	22345	55.41	colorados	403.263
3152	São Braz Do Piauí	220955	17	4313	6.57	san-brazense	656.359
3154	São Francisco De Assis Do Piauí	220965	17	5567	5.06	sãofranciscoense 	1100.393
3156	São Gonçalo Do Gurguéia	220975	17	2825	2.04	são gonçalense	1385.293
3158	São João Da Canabrava	220985	17	4445	9.26	canabravense	480.277
3159	São João Da Fronteira	220987	17	5608	7.33	são jão fronteirense	764.862
3161	São João Da Varjota	220995	17	4651	11.77	sanjoanense	395.305
3163	São João Do Piauí	221000	17	19548	12.8	são-joanense	1527.774
3164	São José Do Divino	221005	17	5148	16.13	divinense	319.128
3166	São José Do Piauí	221020	17	6591	18.06	são-joseense	364.943
3167	São Julião	221030	17	5675	22.07	são-julianense	257.19
3169	São Luis Do Piauí	221037	17	2561	11.62	sãoluisense	220.374
3171	São Miguel Do Fidalgo	221039	17	2976	3.66	fidalguense	813.44
3172	São Miguel Do Tapuio	221040	17	18134	3.48	tapuiense	5206.989
3174	São Raimundo Nonato	221060	17	32327	13.38	são-raimundense	2415.592
3176	Sebastião Leal	221063	17	4116	1.31	sebastião-lealense	3151.579
3177	Sigefredo Pacheco	221065	17	9619	9.95	sigefredense	966.984
3179	Simplício Mendes	221080	17	12077	8.97	simplício-mendense	1345.784
3181	Sussuapara	221093	17	6229	29.7	sussuaparense	209.699
3183	Tanque Do Piauí	221097	17	2620	6.57	tanquense	398.721
3184	Teresina	221100	17	814230	584.95	teresinense	1391.974
3186	Uruçuí	221120	17	20149	2.4	uruçuiense	8411.877
3188	Várzea Branca	221135	17	4913	10.9	varzea-branquense	450.753
3190	Vera Mendes	221150	17	2986	8.73	veramendense 	341.973
3191	Vila Nova Do Piauí	221160	17	3076	14.09	vilanovense	218.315
3193	Abatiá	410010	18	7764	33.95	abatiense	228.717
3195	Agudos Do Sul	410030	18	8270	43.02	agudense-do-sul 	192.229
3197	Altamira Do Paraná	410045	18	4306	11.13	altamirense	386.946
3198	Alto Paraíso	412862	18	3206	3.31	altoparaisense	967.775
3200	Alto Piquiri	410070	18	10179	22.74	alto-piquirense	447.667
3201	Altônia	410050	18	20516	31.01	altoniano 	661.562
3203	Amaporã	410090	18	5443	14.15	amaporense	384.735
3205	Anahy	410105	18	2874	28	anaiense	102.648
3207	Ângulo	410115	18	2859	26.97	angulense	106.021
3208	Antonina	410120	18	18891	21.41	antoninense 	882.318
3210	Apucarana	410140	18	120919	216.55	apucaranense	558.39
3212	Arapoti	410160	18	25855	19	arapotiense	1360.496
3214	Araruna	410170	18	13419	27.21	ararunense	493.192
3215	Araucária	410180	18	119123	253.9	araucariano 	469.168
3217	Assaí	410190	18	16354	37.14	assaiense	440.348
3219	Astorga	410210	18	24698	56.8	astorgano 	434.792
3221	Balsa Nova	410230	18	11300	32.38	balsa-novense 	348.97
3222	Bandeirantes	410240	18	32184	72.29	bandeirantense	445.193
3224	Barra Do Jacaré	410270	18	2727	23.56	barrense	115.725
3226	Bela Vista Da Caroba	410275	18	3945	26.64	boaesperencense	148.107
3228	Bituruna	410290	18	15880	13.07	biturenense	1214.915
3229	Boa Esperança	410300	18	4568	14.86	boa-esperansense	307.382
3231	Boa Ventura De São Roque	410304	18	6554	10.53	boa venturense 	622.185
3233	Bocaiúva Do Sul	410310	18	10987	13.3	bocaiuvense 	826.345
3234	Bom Jesus Do Sul	410315	18	3796	21.82	bonjesuense	173.972
3236	Bom Sucesso Do Sul	410322	18	3293	16.81	bomsucessense do sul	195.932
3238	Braganey	410335	18	5735	16.7	braganense	343.322
3239	Brasilândia Do Sul	410337	18	3209	11.03	brasilandiense	291.036
3241	Cafelândia	410345	18	14662	53.96	cafelandense	271.725
3243	Califórnia	410350	18	8069	56.9	californiano	141.817
3245	Cambé	410370	18	96733	195.54	cambeense	494.685
3246	Cambira	410380	18	7236	44.29	cambirense	163.388
3248	Campina Do Simão	410395	18	4076	9.09	campineiro do simão 	448.425
3250	Campo Bonito	410405	18	4407	10.16	campo-bonitense	433.835
3252	Campo Largo	410420	18	112377	89.94	campo-larguense	1249.419
3253	Campo Magro	410425	18	24843	90.15	campomagrense	275.573
3255	Cândido De Abreu	410440	18	16655	11.03	cândido-abreuense	1510.163
3257	Cantagalo	410445	18	12952	22.2	cantagalense	583.541
3258	Capanema	410450	18	18526	44.25	capanemense	418.706
3260	Carambeí	410465	18	19163	29.5	carambiense	649.681
3262	Cascavel	410480	18	286205	136.23	cascavelense	2100.837
3263	Castro	410490	18	67084	26.5	castrense	2531.507
3265	Centenário Do Sul	410510	18	11190	30.09	centenariense	371.834
3267	Céu Azul	410530	18	11032	9.35	céu-azulense	1179.449
3269	Cianorte	410550	18	69958	86.19	cianortense	811.668
3271	Clevelândia	410570	18	17240	24.47	clevelandense	704.636
3272	Colombo	410580	18	212967	1.079	colombense	197.36
5419	Vera Cruz	355660	26	10769	43.41	vera-cruzense	248.072
3276	Contenda	410620	18	15891	53.14	contendense	299.038
3278	Cornélio Procópio	410640	18	46928	73.89	procopense	635.101
3280	Coronel Vivida	410650	18	21749	31.78	coronel-vividense	684.418
3281	Corumbataí Do Sul	410655	18	4002	24.35	corumbataiense	164.341
3283	Cruzeiro Do Iguaçu	410657	18	4278	26.43	cruzeirense	161.863
3285	Cruzeiro Do Sul	410670	18	4563	17.61	cruzeirense-do-sul	259.103
3286	Cruzmaltina	410685	18	3162	10.12	cruzmaltinense	312.299
3288	Curiúva	410700	18	13923	24.16	curiuvense	576.264
3290	Diamante Do Norte	410710	18	5516	22.71	diamantense 	242.886
3292	Dois Vizinhos	410720	18	36179	86.42	dois-vizinhense	418.649
3293	Douradina	410725	18	7445	17.73	douradinense	419.854
3295	Doutor Ulysses	412863	18	5727	7.33	ulyssense	781.451
3297	Engenheiro Beltrão	410750	18	13906	29.75	engenheiro-beltrense	467.471
3298	Entre Rios Do Oeste	410753	18	3926	32.16	entreriense	122.072
3300	Espigão Alto Do Iguaçu	410754	18	4677	14.33	espigãoense	326.441
3302	Faxinal	410760	18	16314	22.79	faxinalense	715.945
3304	Fênix	410770	18	4802	20.51	fenexense	234.1
3305	Fernandes Pinheiro	410773	18	5932	14.59	fernandespinheirense	406.5
3307	Flor Da Serra Do Sul	410785	18	4726	19.75	sulflorense	239.301
3309	Floresta	410790	18	5931	37.48	florestense 	158.226
3311	Flórida	410810	18	2543	30.62	floridense	83.046
3313	Foz Do Iguaçu	410830	18	256088	414.58	iguaçuense	617.702
3314	Foz Do Jordão	410845	18	5420	23.03	foz jordanense 	235.383
3316	Francisco Beltrão	410840	18	78943	107.39	francisco-beltrense	735.113
3318	Godoy Moreira	410855	18	3337	25.47	godoense	131.012
3319	Goioerê	410860	18	29018	51.44	goio-erense	564.165
3321	Grandes Rios	410870	18	6625	21.09	grande-riense	314.199
3323	Guairaçá	410890	18	6197	12.55	guairaçaense	493.941
3325	Guapirama	410900	18	3891	20.58	guapiramense	189.1
3326	Guaporema	410910	18	2219	11.08	guaporemense	200.189
3328	Guaraniaçu	410930	18	14582	11.9	guaraniaçuano 	1225.61
3330	Guaraqueçaba	410950	18	7871	3.9	guaraqueçabano	2020.093
3332	Honório Serpa	410965	18	5955	11.86	honório serpense	502.236
3334	Ibema	410975	18	6066	41.71	ibemense	145.445
3335	Ibiporã	410980	18	48198	161.88	ibiporanense	297.743
3337	Iguaraçu	411000	18	3982	24.14	iguaraçuense	164.983
3339	Imbaú	411007	18	11274	34.03	imbauense	331.276
3341	Inácio Martins	411020	18	10943	11.68	inácio-martinense 	936.915
3343	Indianópolis	411040	18	4299	35.06	indianopolitano	122.622
3344	Ipiranga	411050	18	14150	15.26	ipiranguense	927.089
3346	Iracema Do Oeste	411065	18	2578	31.62	iracemense	81.539
3348	Iretama	411080	18	10622	18.62	iretamense	570.461
3350	Itaipulândia	411095	18	9026	27.25	itaipulandiense	331.289
3352	Itambé	411110	18	5979	24.52	itambenense	243.822
3354	Itaperuçu	411125	18	23887	75.97	itaperuçuense	314.419
3355	Itaúna Do Sul	411130	18	3583	27.8	itaunense	128.87
3357	Ivaiporã	411150	18	31816	73.73	ivaiporãnense	431.503
3359	Ivatuba	411160	18	3010	31.14	ivatubense	96.661
3360	Jaboti	411170	18	4902	35.2	jabotiense	139.277
3362	Jaguapitã	411190	18	12225	25.74	jaguapitãense 	475.005
3364	Jandaia Do Sul	411210	18	20269	108.04	jandaiense-do-sul 	187.601
3366	Japira	411230	18	4903	26.04	japirense	188.288
3368	Jardim Alegre	411250	18	12324	30.4	jardim-alegrense 	405.435
3370	Jataizinho	411270	18	11875	74.6	jatainhense 	159.178
3371	Jesuítas	411275	18	9001	36.37	jesuitense	247.497
3373	Jundiaí Do Sul	411290	18	3433	10.7	jundiaiense-do-sul 	320.817
3375	Jussara	411300	18	6610	31.35	jussarense	210.87
3376	Kaloré	411310	18	4506	23.31	kaloreense	193.299
3378	Laranjal	411325	18	6360	11.37	laranjaense	559.44
3380	Leópolis	411340	18	4145	12.02	leopolense	344.919
3382	Lindoeste	411345	18	5361	14.84	lindo-estense	361.368
3383	Loanda	411350	18	21201	29.34	loandense	722.498
3385	Londrina	411370	18	506701	306.49	londrinense	1653.263
3387	Lunardelli	411375	18	5160	25.9	lunardelliense	199.214
3389	Mallet	411390	18	12973	17.94	malletense	723.082
3390	Mamborê	411400	18	13961	17.72	mamborense	788.063
3392	Mandaguari	411420	18	32658	97.25	mandaguariense	335.815
3394	Manfrinópolis	411435	18	3127	14.45	manfrinopolitano	216.416
3396	Manoel Ribas	411450	18	13169	23.06	manoel-ribense	571.136
3398	Maria Helena	411470	18	5956	12.25	maria-helenense	486.225
3399	Marialva	411480	18	31959	67.2	marialvense	475.565
3401	Marilena	411500	18	6858	29.51	marilenense	232.368
5556	Sucupira	172085	27	1742	1.7	sucupirense	1025.517
3404	Mariópolis	411530	18	6268	27.16	mariopolitano 	230.742
3406	Marmeleiro	411540	18	13900	35.85	marmeleirense	387.677
3408	Marumbi	411550	18	4603	22.08	marumbiense	208.47
3409	Matelândia	411560	18	16078	25.13	matelandiense	639.748
3411	Mato Rico	411573	18	3818	9.68	mato-riquense	394.534
3413	Medianeira	411580	18	41817	127.21	medianeirense	328.733
3415	Mirador	411590	18	2327	10.5	miradorense	221.708
3416	Miraselva	411600	18	1862	20.62	miraselvano 	90.294
3418	Moreira Sales	411610	18	12606	35.63	moreira-salense 	353.773
3420	Munhoz De Melo	411630	18	3672	26.8	munhozense	137.018
3422	Nova Aliança Do Ivaí	411650	18	1431	10.9	ivaiense	131.272
3424	Nova Aurora	411670	18	11866	25.03	nova-aurorense 	474.013
3425	Nova Cantu	411680	18	7425	13.37	nova-cantuense	555.489
3427	Nova Esperança Do Sudoeste	411695	18	5098	24.45	novaesperancense	208.472
3429	Nova Laranjeiras	411705	18	11241	9.81	nova laranjeirense	1145.492
3431	Nova Olímpia	411720	18	5503	40.36	olimpiense	136.348
3432	Nova Prata Do Iguaçu	411725	18	10377	29.43	pratense	352.566
3434	Nova Santa Rosa	411722	18	7626	37.26	nova-santa-rosense  	204.666
3435	Nova Tebas	411727	18	7398	13.56	nova-tebense	545.687
3437	Ortigueira	411730	18	23380	9.62	ortigueirense	2429.569
3439	Ouro Verde Do Oeste	411745	18	5692	19.42	ouro-verdense	293.043
3441	Palmas	411760	18	42888	27.36	palmense	1567.365
3442	Palmeira	411770	18	32123	22.04	palmeirense	1457.26
3444	Palotina	411790	18	28683	44.04	palotinense	651.239
3446	Paranacity	411810	18	10250	29.4	paranacitense	348.631
3448	Paranapoema	411830	18	2791	15.87	paranapoemense	175.875
3449	Paranavaí	411840	18	81590	67.86	paranavaiense	1202.268
3451	Pato Branco	411850	18	72370	134.24	pato-branquense	539.089
3453	Paulo Frontin	411870	18	6913	18.72	frontinense 	369.212
3455	Perobal	411885	18	5653	13.87	perobalense	407.581
3456	Pérola	411890	18	10208	42.42	perolense	240.635
3458	Piên	411910	18	11236	44.08	pienense	254.903
3460	Pinhal De São Bento	411925	18	2625	26.93	pinhalense	97.464
3462	Pinhão	411930	18	30208	15.09	pinhãoense	2001.593
3463	Piraí Do Sul	411940	18	23424	16.69	piraiense	1403.068
3465	Pitanga	411960	18	32638	19.62	pitanguense	1663.751
3467	Planaltina Do Paraná	411970	18	4095	11.5	planaltinense	356.192
3469	Ponta Grossa	411990	18	311611	150.72	ponta-grossense	2067.551
3470	Pontal Do Paraná	411995	18	20920	104.67	pontalense	199.873
3472	Porto Amazonas	412010	18	4514	24.19	porto-amazonense	186.581
3474	Porto Rico	412020	18	2530	11.62	porto-riquense 	217.676
3476	Prado Ferreira	412033	18	3434	22.39	prado ferreirense	153.399
3477	Pranchita	412035	18	5628	24.92	pranchitano	225.842
3479	Primeiro De Maio	412050	18	10832	26.14	primaiense	414.442
3481	Quarto Centenário	412065	18	4856	15.09	quarto centenariense	321.876
3483	Quatro Barras	412080	18	19851	109.59	quatro-barrense 	181.131
3485	Quedas Do Iguaçu	412090	18	30605	37.25	quedas-iguaçuense-	821.505
3486	Querência Do Norte	412100	18	11729	12.82	querenciano	914.765
3488	Quitandinha	412120	18	17089	38.23	quitandinhense	447.025
3490	Rancho Alegre	412130	18	3955	23.59	alegrense	167.646
3492	Realeza	412140	18	16338	46.23	realezense	353.417
3493	Rebouças	412150	18	14176	29.42	reboucense	481.841
3495	Reserva	412170	18	25172	15.4	reservense	1634.954
3497	Ribeirão Claro	412180	18	10678	16.97	ribeirão-clarense	629.224
3499	Rio Azul	412200	18	14093	22.38	rio-azulense	629.747
3500	Rio Bom	412210	18	3334	18.75	rio-bonense	177.836
3502	Rio Branco Do Ivaí	412217	18	3898	10.2	riobranquense	382.329
3503	Rio Branco Do Sul	412220	18	30650	37.73	rio-branquense	812.327
3505	Rolândia	412240	18	57862	125.74	rolandense	460.165
3507	Rondon	412260	18	8996	16.18	rondonense	556.088
3509	Sabáudia	412270	18	6096	32.03	sabaudiense	190.325
3510	Salgado Filho	412280	18	4403	23.26	salgadense	189.316
3512	Salto Do Lontra	412300	18	13689	43.77	salto-lontrense 	312.718
3514	Santa Cecília Do Pavão	412320	18	3646	33.09	pavonense	110.2
3516	Santa Fé	412340	18	10432	37.76	santa-feense	276.242
3517	Santa Helena	412350	18	23413	30.88	santa-helenense	758.229
3519	Santa Isabel Do Ivaí	412370	18	8760	25.06	santa-isabelense 	349.498
3521	Santa Lúcia	412382	18	3925	33.59	santaluciense	116.858
3522	Santa Maria Do Oeste	412385	18	11500	13.58	santa-mariense	847.139
3524	Santa Mônica	412395	18	3571	13.74	moniquense	259.957
3526	Santa Terezinha De Itaipu	412405	18	20841	80.34	terezinhense	259.394
3529	Santo Antônio Do Caiuá	412420	18	2727	12.45	santo-antoniense	219.069
3530	Santo Antônio Do Paraíso	412430	18	2408	14.51	santo-antoniense	165.904
3532	Santo Inácio	412450	18	5269	17.17	santo-inaciense	306.871
3534	São Jerônimo Da Serra	412470	18	11337	13.76	jeronimense	823.776
3535	São João	412480	18	10599	27.31	são-joanense 	388.06
3537	São João Do Ivaí	412500	18	11525	32.62	são-joanense	353.332
3538	São João Do Triunfo	412510	18	13704	19.02	triunfense	720.409
3540	São Jorge Do Ivaí	412530	18	5517	17.51	são-jorgense 	315.089
3542	São José Da Boa Vista	412540	18	6511	16.29	boa-vistense	399.668
3543	São José Das Palmeiras	412545	18	3830	21	são-joseliense	182.419
3545	São Manoel Do Paraná	412555	18	2098	22	são manoelense	95.382
3546	São Mateus Do Sul	412560	18	41257	30.73	são-mateuense	1342.637
3548	São Pedro Do Iguaçu	412575	18	6491	21.05	São pedrense	308.329
3550	São Pedro Do Paraná	412590	18	2491	9.94	são-pedrense	250.654
3552	São Tomé	412610	18	5349	24.47	são-tomeense	218.624
3553	Sapopema	412620	18	6736	9.94	sapopemense	677.611
3555	Saudade Do Iguaçu	412627	18	5028	33.06	saudadense	152.085
3556	Sengés	412630	18	18414	12.81	sengeano	1437.366
3558	Sertaneja	412640	18	5817	13.09	sertanejano 	444.493
3560	Siqueira Campos	412660	18	18454	66.37	siqueirense	278.035
3561	Sulina	412665	18	3394	19.88	sulinense	170.76
3563	Tamboara	412670	18	4664	24.12	tamboarense	193.348
3565	Tapira	412690	18	5836	13.44	tapirense	434.372
3567	Telêmaco Borba	412710	18	69872	50.53	telêmaco-borbense 	1382.863
3568	Terra Boa	412720	18	15776	49.17	terra-bonense	320.851
3570	Terra Roxa	412740	18	16759	20.93	terra-roxense	800.809
3572	Tijucas Do Sul	412760	18	14537	21.63	tijucano-do-sul 	672.202
3574	Tomazina	412780	18	8791	14.86	tomazinense	591.439
3576	Tunas Do Paraná	412788	18	6256	9.36	tunense	668.479
3577	Tuneiras Do Oeste	412790	18	8695	12.44	tuneirense	698.872
3579	Turvo	412796	18	13811	15.07	turvense	916.487
3581	Umuarama	412810	18	100676	81.67	umuaramense	1232.77
3583	Uniflor	412830	18	2466	26.01	uniflorense	94.82
3584	Uraí	412840	18	11472	48.24	uraiense	237.81
3586	Vera Cruz Do Oeste	412855	18	8973	27.43	vera-cruzense	327.091
3588	Virmond	412865	18	3950	16.24	virmondense	243.174
3589	Vitorino	412870	18	6513	21.13	vitorinense	308.276
3591	Xambrê	412880	18	6012	16.71	xambrense	359.713
3593	Aperibé	330015	19	10213	107.92	aperibeense	94.635
3594	Araruama	330020	19	112008	175.55	araruamense	638.023
3596	Armação Dos Búzios	330023	19	27560	392.16	buziano	70.278
3598	Barra Do Piraí	330030	19	94778	163.7	barrense	578.965
3600	Belford Roxo	330045	19	469332	6.031	belford-roxense	77.815
3601	Bom Jardim	330050	19	25333	65.86	bom-jardinense	384.639
3603	Cabo Frio	330070	19	186227	453.75	cabo-friense	410.415
3605	Cambuci	330090	19	14827	26.4	cambuciense	561.698
3606	Campos Dos Goytacazes	330100	19	463731	115.16	campista	4026.712
3608	Carapebus	330093	19	13359	43.36	carapebuense	308.127
3610	Carmo	330120	19	17434	54.07	carmense	322.415
3611	Casimiro De Abreu	330130	19	35347	76.71	casimirense	460.773
3613	Conceição De Macabu	330140	19	21211	61.08	macabuense	347.272
3615	Duas Barras	330160	19	10930	29.14	bibarrense	375.126
3617	Engenheiro Paulo De Frontin	330180	19	13237	99.57	fronteense	132.936
3618	Guapimirim	330185	19	51483	142.7	guapimiriense	360.766
3620	Itaboraí	330190	19	218008	506.56	itaboraiense	430.373
3621	Itaguaí	330200	19	109091	395.45	itaguaiense	275.867
3623	Itaocara	330210	19	22899	53.09	itaocarense	431.335
3625	Itatiaia	330225	19	28783	117.41	itatiaiense	245.146
3627	Laje Do Muriaé	330230	19	7487	29.95	lajense	249.974
3628	Macaé	330240	19	206728	169.89	macaense	1216.845
3630	Magé	330250	19	227322	585.13	mageense	388.496
3632	Maricá	330270	19	127461	351.55	maricaense	362.571
3634	Mesquita	330285	19	168376	4.31	mesquitnese	39.062
3636	Miracema	330300	19	26843	88.15	miracemense	304.515
3637	Natividade	330310	19	15082	39	natividadense	386.74
3639	Niterói	330330	19	487562	3.64	niteroiense	133.916
3641	Nova Iguaçu	330350	19	796257	1.527	iguaçuano	521.247
3642	Paracambi	330360	19	47124	262.27	paracambiense	179.68
3644	Parati	330380	19	37533	40.57	paratiense	925.053
3646	Petrópolis	330390	19	295917	371.85	petropolitano	795.798
3648	Piraí	330400	19	26314	52.07	piraiense	505.374
3649	Porciúncula	330410	19	17760	58.8	porciunculense	302.024
3653	Quissamã	330415	19	20242	28.4	quissamaense	712.852
3655	Rio Bonito	330430	19	55551	121.7	rio-bonitense	456.455
3657	Rio Das Flores	330450	19	8561	17.9	rio-florense 	478.312
3659	Rio De Janeiro	330455	19	6320446	5.265	carioca	1200.279
3660	Santa Maria Madalena	330460	19	10321	12.67	madalenense	814.763
3662	São Fidélis	330480	19	37543	36.39	fidelense	1031.558
3664	São Gonçalo	330490	19	999728	4.035	gonçalense	247.709
3665	São João Da Barra	330500	19	32747	71.96	são-joanense	455.044
3667	São José De Ubá	330513	19	7003	27.98	ubaense	250.28
3668	São José Do Vale Do Rio Preto	330515	19	20251	91.87	rio-pretano	220.432
3670	São Sebastião Do Alto	330530	19	8895	22.36	altense	397.897
3671	Sapucaia	330540	19	17525	32.38	sapucaiense	541.239
3673	Seropédica	330555	19	78186	275.53	seropediquense	283.762
3675	Sumidouro	330570	19	14900	37.67	sumidourense	395.516
3676	Tanguá	330575	19	30732	211.21	tanguaense	145.503
3678	Trajano De Moraes	330590	19	10289	17.44	trajanense	589.811
3680	Valença	330610	19	71843	55.06	valenciano	1304.813
3681	Varre Sai	330615	19	9475	49.85	varresaiense	190.061
3684	Acari	240010	20	11035	18.13	acariense	608.568
3685	Açu	240020	20	53227	40.84	açuense	1303.437
3687	Água Nova	240040	20	2980	58.8	água-novense	50.684
3688	Alexandria	240050	20	13507	35.43	alexandrinense	381.203
3690	Alto Do Rodrigues	240070	20	12305	64.31	alto-rodriguense	191.327
3692	Antônio Martins	240090	20	6907	28.24	antônio-martinense	244.624
3694	Areia Branca	240110	20	25315	70.79	areia-branquense	357.623
3696	Augusto Severo	240130	20	9289	10.36	augusto-severense	896.949
3698	Baraúna	240145	20	24182	29.29	baraunense	825.701
3699	Barcelona	240150	20	3950	25.88	barcelonense	152.625
3701	Bodó	240165	20	2425	9.57	bodoense	253.517
3703	Brejinho	240180	20	11577	188.07	brejinense	61.558
3705	Caiçara Do Rio Do Vento	240190	20	3308	12.67	caiçarense-do-rio-do-vento	261.192
3706	Caicó	240200	20	62709	51.04	caicoense	1228.576
3708	Canguaretama	240220	20	30916	125.98	canguaretamense	245.406
3710	Carnaúba Dos Dantas	240240	20	7429	30.24	carnaubense	245.649
3712	Ceará Mirim	240260	20	68141	94.07	ceará-miriense	724.377
3713	Cerro Corá	240270	20	10916	27.74	cerro-coraense	393.572
3715	Coronel João Pessoa	240290	20	4772	40.74	pessoense	117.138
3717	Currais Novos	240310	20	42652	49.35	currais-novense	864.344
3719	Encanto	240330	20	5231	41.6	encantense	125.748
3720	Equador	240340	20	5822	21.97	equatoriano	264.984
3722	Extremoz	240360	20	24569	176.03	extremozense	139.569
3724	Fernando Pedroza	240375	20	2854	8.85	fernando-pedrozense	322.626
3726	Francisco Dantas	240390	20	2874	15.83	francisco-dantense	181.557
3728	Galinhos	240410	20	2159	6.31	galinhense	342.213
3729	Goianinha	240420	20	22481	116.92	goianiense	192.278
3731	Grossos	240440	20	9393	74.28	grossense	126.457
3732	Guamaré	240450	20	12404	47.9	guamareense	258.962
3734	Ipanguaçu	240470	20	13856	37.02	ipanguaçuense	374.236
3736	Itajá	240485	20	6932	34.04	itajaense	203.621
3738	Jaçanã	240500	20	7925	145.25	jaçanãense	54.56
3739	Jandaíra	240510	20	6801	15.6	jandairense	435.945
3741	Januário Cicco	240530	20	9011	48.13	januarense	187.228
3743	Jardim De Angicos	240550	20	2607	10.26	jardim-angicanense	254.02
3745	Jardim Do Seridó	240570	20	12113	32.86	jardinense	368.645
3747	João Dias	240590	20	2601	29.5	joão-diense	88.172
3748	José Da Penha	240600	20	5868	49.88	josé-penhense	117.634
3750	Jundiá	240615	20	3582	80.24		44.641
3752	Lagoa De Pedras	240630	20	6989	59.4	lagoa-dantense	117.663
3753	Lagoa De Velhos	240640	20	2668	23.64	lagoa-pedrense	112.844
3755	Lagoa Salgada	240660	20	7564	95.37	lagoa-salgadense	79.313
3757	Lajes Pintadas	240680	20	4612	35.42	lajes-pintadense	130.211
3759	Luís Gomes	240700	20	9610	57.67	luís-gomense	166.637
3761	Macau	240720	20	28954	36.74	macauense	788.027
3762	Major Sales	240725	20	3536	110.6	major-salense	31.971
3764	Martins	240740	20	8218	48.49	martinense	169.463
3766	Messias Targino	240760	20	4188	31	messias-targinense	135.096
3768	Monte Alegre	240780	20	20685	97.87	monte-alegrense	211.341
3770	Mossoró	240800	20	259815	123.76	mossoroense	2099.328
3771	Natal	240810	20	803739	4.808	natalense	167.16
3773	Nova Cruz	240830	20	35490	127.82	nova-cruzense	277.657
3775	Ouro Branco	240850	20	4699	18.55	ouro-branquense	253.302
3776	Paraná	240860	20	3952	48.56	paranaense	81.39
3779	Parelhas	240890	20	20354	39.67	parelhense	513.053
3781	Passa E Fica	240910	20	11100	263.43	passa-fiquense	42.136
3783	Patu	240930	20	11964	37.49	patuense	319.127
3785	Pedra Grande	240950	20	3521	15.9	pedra-grandense	221.426
3786	Pedra Preta	240960	20	2590	8.78	pedra-pretense	294.983
3788	Pedro Velho	240980	20	14114	73.24	pedro-velhense	192.707
3789	Pendências	240990	20	13432	32.05	pendenciano 	419.143
3792	Portalegre	241020	20	7320	66.51	portalegrense	110.053
3793	Porto Do Mangue	241025	20	5217	16.36	porto-manguense	318.966
3795	Pureza	241040	20	8424	16.7	purezense	504.293
3796	Rafael Fernandes	241050	20	4692	59.98	rafael-fernandense	78.23
3798	Riacho Da Cruz	241070	20	3165	24.88	riacho-cruzense	127.222
3800	Riachuelo	241090	20	7067	26.88	riachuelense	262.886
3801	Rio Do Fogo	240895	20	10059	66.94	rio-foguense	150.26
3803	Ruy Barbosa	241110	20	3595	28.58	rui-barbosense	125.808
3805	Santa Maria	240933	20	4762	21.69	santa-mariense	219.567
3807	Santana Do Seridó	241142	20	2526	13.41	santanense	188.403
3809	São Bento Do Norte	241160	20	2975	10.3	são-bento-nortense	288.724
3810	São Bento Do Trairí	241170	20	3905	20.46	trairiense	190.817
3812	São Francisco Do Oeste	241190	20	3874	51.25	oestense	75.587
3814	São João Do Sabugi	241210	20	5922	21.38	sabugiense	277.01
3815	São José De Mipibu	241220	20	39776	137	mipibuense	290.329
3817	São José Do Seridó	241240	20	4231	24.25	são-josé-seridoense 	174.504
3819	São Miguel Do  Gostoso	241255	20	8670	25.35	micaelense de touros	342.037
3821	São Pedro	241270	20	6235	31.94	são-pedrense	195.237
3822	São Rafael	241280	20	8111	17.29	são-rafaelense 	469.099
3824	São Vicente	241300	20	6028	30.47	são-vicentense 	197.816
3826	Senador Georgino Avelino	241320	20	3924	151.31	georginense	25.934
3827	Serra De São Bento	241330	20	5743	59.43	serra-bentense 	96.627
3829	Serra Negra Do Norte	241340	20	7770	13.82	serra-negrense-do-norte	562.394
3831	Serrinha Dos Pintos	241355	20	4540	37.02	serriense dos pintos	122.648
3833	Sítio Novo	241370	20	5020	23.52	sítio-novense	213.458
3834	Taboleiro Grande	241380	20	2317	18.67	taboleirense	124.093
3836	Tangará	241400	20	14175	39.72	tangarense	356.832
3838	Tenente Laurentino Cruz	241415	20	5406	72.68	tenente-laurentinense	74.376
3840	Tibau Do Sul	241420	20	11385	111.81	tibauense	101.821
3842	Touros	241440	20	31089	36.99	tourense	840.375
3843	Triunfo Potiguar	241445	20	3368	12.53	triunfense potiguar	268.725
3845	Upanema	241460	20	12992	14.87	upanemense	873.926
3847	Venha Ver	241475	20	3821	53.35	venha-verense	71.622
3848	Vera Cruz	241480	20	10719	128.43	vera-cruzense	83.463
3850	Vila Flor	241500	20	2872	60.27	vila-florense	47.656
3852	Alto Alegre Dos Parecis	110037	21	12816	3.24	alto-alegrense	3958.279
3854	Alvorada D`Oeste	110034	21	16853	5.56	alvoradense	3029.193
3855	Ariquemes	110002	21	90353	20.41	ariquemense	4426.576
3857	Cabixi	110003	21	6313	4.8	cabixiense	1314.364
3859	Cacoal	110004	21	78574	20.72	cacoaense	3792.805
3861	Candeias Do Jamari	110080	21	19779	2.89	candeiense	6843.889
3862	Castanheiras	110090	21	3575	4	castanheirense	892.843
3864	Chupinguaia	110092	21	8301	1.62	chupinguaiense	5126.73
3866	Corumbiara	110007	21	8783	2.87	corumbiarense	3060.326
3867	Costa Marques	110008	21	13678	2.74	costa-marquense	4987.187
3869	Espigão D`Oeste	110009	21	28729	6.36	espigãoense	4518.038
3871	Guajará Mirim	110010	21	41656	1.68	guajará-mirense 	24855.772
3872	Itapuã Do Oeste	110110	21	8566	2.1	jamariense	4081.587
3874	Ji Paraná	110012	21	116610	16.91	ji-paranaense	6896.744
3876	Ministro Andreazza	110120	21	10352	12.97	andreazense	798.084
3878	Monte Negro	110140	21	14091	7.3	monte-negrino	1931.381
3879	Nova Brasilândia D`Oeste	110014	21	19874	17.2	brasilandense	1155.356
3881	Nova União	110143	21	7493	9.28	nova-uniense	807.127
3883	Ouro Preto Do Oeste	110015	21	37928	19.25	ouro-pretense	1969.852
3884	Parecis	110145	21	4810	1.89	parecisense	2548.686
3886	Pimenteiras Do Oeste	110146	21	2315	0.38	pimenteirense	6014.743
3888	Presidente Médici	110025	21	22319	12.69	mediciense	1758.467
3890	Rio Crespo	110026	21	3316	1.93	rio-crespense	1717.642
3891	Rolim De Moura	110028	21	50648	34.74	rolimorense	1457.889
3893	São Felipe D`Oeste	110148	21	6018	11.11	são-felipense	541.647
3895	São Miguel Do Guaporé	110032	21	21828	2.73	miguelense	8007.885
3896	Seringueiras	110150	21	11629	3.08	seringueinense	3773.511
3898	Theobroma	110160	21	10649	4.85	theobromense	2197.415
3899	Urupá	110170	21	12974	15.6	urupaense	831.858
5422	Vista Alegre Do Alto	355690	26	6886	72.5	vista-alegrense	94.981
3902	Vilhena	110030	21	76202	6.62	vilhenense	11518.952
3903	Alto Alegre	140005	22	16448	0.64	alto-alegrense	25566.965
3905	Boa Vista	140010	22	284313	49.99	boa-vistense	5687.022
3907	Cantá	140017	22	13902	1.81	cantaense	7664.813
3909	Caroebe	140023	22	8114	0.67	caroebense	12066.188
3911	Mucajaí	140030	22	14792	1.19	mucajaiense	12461.185
3912	Normandia	140040	22	8940	1.28	normandiense	6966.793
3914	Rorainópolis	140047	22	24279	0.72	rorainopolitano	33593.988
3916	São Luiz	140060	22	6750	4.42	são-luizense	1526.884
3917	Uiramutã	140070	22	8375	1.04	uiramutansense	8065.54
3919	Água Santa	430005	23	3722	12.76	água-santense	291.793
3921	Ajuricaba	430020	23	7255	22.44	ajuricabense	323.24
3923	Alegrete	430040	23	77653	9.95	alegretense	7803.99
3925	Almirante Tamandaré Do Sul	430047	23	2067	7.79	tamandarense                        	265.369
3927	Alto Alegre	430055	23	1848	16.15	alto-alegrense	114.446
3929	Alvorada	430060	23	195673	2.743	alvoradense	71.311
3930	Amaral Ferrador	430063	23	6353	12.54	amaralense	506.459
3932	André Da Rocha	430066	23	1216	3.75	andré-rochense	324.327
3934	Antônio Prado	430080	23	12833	36.92	pradense	347.618
3936	Araricá	430087	23	4864	137.83	arariquense	35.291
3937	Aratiba	430090	23	6565	19.23	aratibense	341.324
3939	Arroio Do Padre	430107	23	2730	21.96	arroio padrense	124.318
3941	Arroio Do Tigre	430120	23	12648	39.74	tigrense	318.236
3942	Arroio Dos Ratos	430110	23	13606	31.94	ratense	425.934
3944	Arvorezinha	430140	23	10225	37.64	arvorezinhense	271.643
3946	Áurea	430155	23	3665	23.15	aurense	158.291
3947	Bagé	430160	23	116794	28.52	bageense	4095.553
3949	Barão	430165	23	5741	46.12	baronense	124.489
3951	Barão Do Triunfo	430175	23	7018	16.08	baronense	436.397
3953	Barra Do Quaraí	430187	23	4012	3.8	barrense	1056.149
3954	Barra Do Ribeiro	430190	23	12572	17.25	barrense	728.95
3956	Barra Funda	430195	23	2367	39.43	barra-fundense	60.033
3957	Barracão	430180	23	5357	10.38	barraconense	516.291
3959	Benjamin Constant Do Sul	430205	23	2307	17.42	benjaminense	132.396
3961	Boa Vista Das Missões	430215	23	2114	10.85	boa-vistense 	194.816
3963	Boa Vista Do Cadeado	430222	23	2441	3.48	cadeadense	701.105
3964	Boa Vista Do Incra	430223	23	2425	4.82	boa vistense do incra	503.473
3966	Bom Jesus	430230	23	11519	4.39	bom-jesuense	2625.695
3968	Bom Progresso	430237	23	2328	26.23	bom-progressense	88.742
3969	Bom Retiro Do Sul	430240	23	11472	112.11	bom-retirense	102.327
3971	Bossoroca	430250	23	6884	4.27	bossoroquense	1610.58
3973	Braga	430260	23	3702	28.7	braguense	128.993
3974	Brochier	430265	23	4675	43.8	brochiense	106.734
3976	Caçapava Do Sul	430280	23	33690	11.06	caçapavano	3047.126
3978	Cachoeira Do Sul	430300	23	83827	22.44	cachoeirense	3735.179
3980	Cacique Doble	430320	23	4868	23.87	caciquense	203.909
3981	Caibaté	430330	23	4954	19.08	caibateense	259.665
3983	Camaquã	430350	23	62764	37.37	camaquense	1679.441
3985	Cambará Do Sul	430360	23	6542	5.4	cambaraense	1212.541
3987	Campina Das Missões	430370	23	6117	27.09	campinense	225.763
3989	Campo Bom	430390	23	60074	992.79	campo-bonense	60.51
3990	Campo Novo	430400	23	5459	24.58	campo-novense	222.074
3992	Candelária	430420	23	30171	31.96	candelariense	943.949
3994	Candiota	430435	23	8771	9.39	candiotense	933.839
3995	Canela	430440	23	39229	154.58	canelense	253.773
3997	Canoas	430460	23	323827	2.47	canoense	131.097
3999	Capão Bonito Do Sul	430462	23	1754	3.33	capão bonitense	527.12
4001	Capão Do Cipó	430465	23	3104	3.08	cipoense	1008.654
4002	Capão Do Leão	430466	23	24298	30.94	leonense	785.377
4004	Capitão	430469	23	2636	35.64	capitanense	73.967
4006	Caraá	430471	23	7312	24.84	caraense	294.324
4007	Carazinho	430470	23	59317	89.19	carazinhense	665.094
4009	Carlos Gomes	430485	23	1607	19.33	carlos-gomense	83.155
4011	Caseiros	430495	23	3007	12.76	caseirense	235.706
4012	Catuípe	430500	23	9323	15.98	catuipano	583.26
4014	Centenário	430511	23	2965	22.07	centenariense	134.331
4016	Cerro Branco	430513	23	4454	28.05	cerro-branquense	158.766
4018	Cerro Grande Do Sul	430517	23	10268	31.61	sul-cerro-grandense	324.79
4020	Chapada	430530	23	9377	13.71	chapadense	684.043
4021	Charqueadas	430535	23	35320	163.13	charqueadense	216.513
4023	Chiapetta	430540	23	4044	10.2	chiapetense	396.553
4025	Chuvisca	430544	23	4944	22.42	chuvisquense	220.472
4028	Colinas	430558	23	2420	41.46	colinense	58.374
4030	Condor	430570	23	6552	14.08	condorense	465.189
4032	Coqueiro Baixo	430583	23	1528	13.61	coqueirense	112.277
4034	Coronel Barros	430587	23	2459	15.09	coronel-barrense	162.949
4036	Coronel Pilar	430593	23	1725	16.36	coronel pilarense	105.447
4037	Cotiporã	430595	23	3917	22.72	cotiporanense	172.376
4039	Crissiumal	430600	23	14084	38.89	crissiumalense	362.151
4041	Cristal Do Sul	430607	23	2826	28.92	cristalense	97.715
4043	Cruzaltense	430613	23	2141	12.83	cruzaltino	166.883
4045	David Canabarro	430630	23	4683	26.77	canabarrense	174.94
4046	Derrubadas	430632	23	3190	8.83	derrubadense	361.286
4048	Dilermando De Aguiar	430637	23	3064	5.1	dilermandense	600.549
4050	Dois Irmãos Das Missões	430642	23	2157	9.56	dois-irmãozense	225.681
4051	Dois Lajeados	430645	23	3278	24.58	dois-lajeense	133.373
4053	Dom Pedrito	430660	23	38898	7.49	pedritense	5192.12
4055	Dona Francisca	430670	23	3401	29.74	francisquense	114.346
4056	Doutor Maurício Cardoso	430673	23	5313	21.03	mauriciense	252.69
4058	Eldorado Do Sul	430676	23	34343	67.38	eldoradense	509.728
4059	Encantado	430680	23	20510	147.38	encantadense	139.16
4061	Engenho Velho	430692	23	1527	21.45	engenho-velhense	71.191
4063	Entre Ijuís	430693	23	8938	16.17	entre-ijuiense	552.603
4065	Erechim	430700	23	96087	223.11	erechinense	430.67
4066	Ernestina	430705	23	3088	12.91	ernestinense	239.148
4068	Erval Seco	430730	23	7878	21.65	erval-sequense	363.894
4070	Esperança Do Sul	430745	23	3272	22.05	esperançulense	148.379
4072	Estação	430755	23	6011	59.95	estacionense	100.266
4074	Esteio	430770	23	80755	2.917	esteiense	27.676
4075	Estrela	430780	23	30619	166.25	estrelense	184.177
4077	Eugênio De Castro	430783	23	2798	6.67	eugenio-castrense	419.321
4079	Farroupilha	430790	23	63635	176.57	farroupilhense	360.392
4080	Faxinal Do Soturno	430800	23	6672	39.27	soturnense	169.903
4082	Fazenda Vilanova	430807	23	3697	43.6	vilanovense	84.794
4084	Flores Da Cunha	430820	23	27126	99.2	florense	273.453
4086	Fontoura Xavier	430830	23	10719	18.37	fontourense	583.467
4087	Formigueiro	430840	23	7014	12.05	formigueirense	581.992
4089	Fortaleza Dos Valos	430845	23	4575	7.03	fortalezense	650.328
4091	Garibaldi	430860	23	30689	181.34	garibaldense	169.237
4092	Garruchos	430865	23	3234	4.04	garruchense	799.852
4094	General Câmara	430880	23	8447	16.56	camaraense	510.012
4096	Getúlio Vargas	430890	23	16154	56.37	getuliense	286.567
4098	Glorinha	430905	23	6891	21.29	glorinhense	323.642
4099	Gramado	430910	23	32273	135.7	gramadense	237.828
4101	Gramado Xavier	430915	23	3970	18.25	gramado-xavierense	217.526
4103	Guabiju	430925	23	1598	10.77	guabijuense	148.394
4105	Guaporé	430940	23	22814	76.64	guaporense	297.66
4107	Harmonia	430955	23	4254	95.04	harmoniense	44.761
4108	Herval	430710	23	6753	3.84	hervalense	1757.846
4110	Horizontina	430960	23	18348	78.92	horizontinense	232.477
4111	Hulha Negra	430965	23	6043	7.34	hulha-negrense	822.903
4113	Ibarama	430975	23	4371	22.63	ibaramense	193.11
4115	Ibiraiaras	430990	23	7171	23.85	ibiraiense	300.651
4117	Ibirubá	431000	23	19310	31.79	ibirubense	607.456
4119	Ijuí	431020	23	78915	114.51	ijuiense	689.136
4120	Ilópolis	431030	23	4102	35.22	ilopolitano	116.481
4122	Imigrante	431036	23	3023	41.21	imigrantense	73.356
4124	Inhacorá	431041	23	2267	19.87	inhacorense	114.111
4125	Ipê	431043	23	6016	10.04	ipeense	599.249
4127	Iraí	431050	23	8078	44.34	iraiense	182.184
4129	Itacurubi	431055	23	3441	3.07	itacurubiense	1120.878
4131	Itaqui	431060	23	38159	11.21	itaquiense	3404.053
4132	Itati	431065	23	2584	12.49	itatiense  	206.911
4134	Ivorá	431075	23	2156	17.54	ivorense	122.93
4136	Jaboticaba	431085	23	4098	32	jaboticabense	128.053
4138	Jacutinga	431090	23	3633	20.26	jacutinguense	179.297
4139	Jaguarão	431100	23	27931	13.6	jaguarense	2054.392
4141	Jaquirana	431112	23	4177	4.6	jaquiranense	907.939
4143	Jóia	431115	23	8331	6.74	joiense	1235.888
4145	Lagoa Bonita Do Sul	431123	23	2662	24.53	lagobonitense	108.499
4147	Lagoa Vermelha	431130	23	27525	21.78	lagoense	1263.507
4148	Lagoão	431125	23	6185	16.12	lagoense	383.603
4150	Lajeado Do Bugre	431142	23	2487	36.61	lajeado-bugrense	67.934
4152	Liberato Salzano	431160	23	5780	23.53	salzanense	245.628
4153	Lindolfo Collor	431162	23	5227	158.44	lindolfo-collorense	32.991
5424	Votorantim	355700	26	108809	592.47	votorantinense	183.653
4156	Machadinho	431170	23	5510	16.48	machadinhense	334.446
4158	Manoel Viana	431175	23	7072	5.09	vianense	1390.702
4160	Maratá	431179	23	2527	31.13	marataense	81.179
4161	Marau	431180	23	36364	56	marauense	649.302
4163	Mariana Pimentel	431198	23	3768	11.15	marianense	337.794
4165	Marques De Souza	431205	23	4068	32.5	marquesouzense	125.176
4166	Mata	431210	23	5111	16.39	matense	311.884
4168	Mato Leitão	431215	23	3865	84.2	mato-leitoense	45.903
4170	Maximiliano De Almeida	431220	23	4911	23.55	almeidense	208.526
4171	Minas Do Leão	431225	23	7631	17.98	leonense	424.341
4173	Montauri	431235	23	1542	18.79	montauriense	82.079
4175	Monte Belo Do Sul	431238	23	2670	39.05	monte-belense	68.369
4176	Montenegro	431240	23	59415	140.13	montenegrino	424.013
4178	Morrinhos Do Sul	431244	23	3182	19.23	morrinhense	165.441
4180	Morro Reuter	431247	23	5676	64.76	morroreutense	87.641
4182	Muçum	431260	23	4791	43.2	muçuense	110.893
4184	Muliterno	431262	23	1813	16.31	muliternense	111.133
4185	Não Me Toque	431265	23	15936	44.06	não-me-toquense	361.672
4187	Nonoai	431270	23	12074	25.73	nonoaiense	469.311
4189	Nova Araçá	431280	23	4001	53.81	araçaense	74.361
4190	Nova Bassano	431290	23	8840	41.77	bassanense	211.612
4192	Nova Bréscia	431300	23	3184	30.97	bresciense	102.818
4194	Nova Esperança Do Sul	431303	23	4671	24.46	nova-esperancense	191.001
4195	Nova Hartz	431306	23	18346	293.26	nova-hartense	62.558
4197	Nova Palma	431310	23	6342	20.23	nova-palmense	313.508
4199	Nova Prata	431330	23	22830	88.23	nova-pratense	258.744
4201	Nova Roma Do Sul	431335	23	3343	22.43	nova-romense	149.054
4203	Novo Barreiro	431349	23	3978	32.19	novo-barreirense	123.583
4204	Novo Cabrais	431339	23	3855	20.05	cabraisense	192.29
4206	Novo Machado	431342	23	3925	17.95	novo-machadense	218.67
4208	Novo Xingu	431346	23	1757	21.8	xinguense	80.591
4209	Osório	431350	23	40906	61.65	osoriense	663.555
4211	Palmares Do Sul	431365	23	10969	11.56	palmarense	949.213
4213	Palmitinho	431380	23	6920	48.04	palmitense	144.046
4214	Panambi	431390	23	38058	77.53	panambiense	490.859
4216	Paraí	431400	23	6812	56.57	paraiense	120.419
4218	Pareci Novo	431403	23	3511	61.16	pareciense	57.407
4219	Parobé	431405	23	51502	474.03	parobeense	108.646
4222	Passo Fundo	431410	23	184826	235.92	passo-fundense	783.423
4223	Paulo Bento	431413	23	2196	14.8	paulobentense	148.365
4225	Pedras Altas	431417	23	2212	1.61	pedras altense	1377.378
4227	Pejuçara	431430	23	3973	9.59	pejuçarense	414.24
4228	Pelotas	431440	23	328275	203.89	pelotense	1610.091
4230	Pinhal	431445	23	2513	36.84	pinhalense	68.209
4232	Pinhal Grande	431447	23	4471	9.37	pinhal-grandense	477.127
4233	Pinheirinho Do Vale	431449	23	4497	42.69	pinheirinhense	105.345
4235	Pirapó	431455	23	2757	9.45	pirapoense	291.744
4237	Planalto	431470	23	10524	45.67	planaltense	230.421
4239	Pontão	431477	23	3857	7.63	pontanense	505.715
4240	Ponte Preta	431478	23	1750	17.52	ponte-pretense	99.873
4242	Porto Alegre	431490	23	1409351	2.837	porto-alegrense	496.684
4244	Porto Mauá	431505	23	2542	24.08	porto-mauense	105.561
4246	Porto Xavier	431510	23	10558	37.64	porto-xavierense	280.511
4247	Pouso Novo	431513	23	1875	17.6	pouso-novense	106.533
4249	Progresso	431515	23	6163	24.09	progressense	255.862
4251	Putinga	431520	23	4141	20.19	putinguense	205.053
4252	Quaraí	431530	23	23021	7.31	quaraiense	3147.647
4254	Quevedos	431532	23	2710	4.99	quevedense	543.361
4256	Redentora	431540	23	10222	33.77	redentorense	302.681
4257	Relvado	431545	23	2155	17.46	relvadense	123.437
4259	Rio Dos Índios	431555	23	3616	15.26	riodinhense	236.966
4261	Rio Pardo	431570	23	37591	18.33	rio-pardense	2050.597
4263	Roca Sales	431580	23	10284	49.29	roca-salense	208.63
4264	Rodeio Bonito	431590	23	5743	69.03	rodeiense	83.199
4266	Rolante	431600	23	19485	65.91	rolantense	295.638
4268	Rondinha	431620	23	5518	21.88	rondinhense	252.209
4270	Rosário Do Sul	431640	23	39707	9.09	rosariense	4369.669
4271	Sagrada Família	431642	23	2595	33.16	sagradense	78.254
4273	Salto Do Jacuí	431645	23	11880	23.41	salto-jacuiense	507.425
4275	Salvador Do Sul	431650	23	6747	67.59	salvadorense	99.825
4276	Sananduva	431660	23	15373	30.47	sananduvense	504.551
4278	Santa Cecília Do Sul	431673	23	1655	8.3	ceciliense	199.396
4282	Santa Maria	431690	23	261031	145.98	santa-mariense	1788.129
4284	Santa Rosa	431720	23	68587	140.03	santa-rosense	489.8
4285	Santa Tereza	431725	23	1720	23.76	santa-teresense	72.39
4287	Santana Da Boa Vista	431700	23	8242	5.8	santanense-da-boa-vista	1420.622
4289	Santiago	431740	23	49071	20.33	santiaguense	2413.143
4290	Santo Ângelo	431750	23	76275	112.09	santo-angelense ou angelopolitano	680.5
4292	Santo Antônio Das Missões	431770	23	11210	6.55	santo-antoniense	1710.876
4294	Santo Antônio Do Planalto	431775	23	1987	9.77	santo-antoniense	203.441
4296	Santo Cristo	431790	23	14378	39.19	santo-cristense	366.887
4297	Santo Expedito Do Sul	431795	23	2461	19.57	expeditense	125.736
4299	São Domingos Do Sul	431805	23	2926	37.06	são-dominguense	78.952
4301	São Francisco De Paula	431820	23	20537	6.27	serrano	3274.035
4302	São Gabriel	431830	23	60425	12.03	gabrielense	5023.843
4304	São João Da Urtiga	431842	23	4726	27.61	urtiguense	171.177
4306	São Jorge	431844	23	2774	23.5	são-jorgense	118.052
4307	São José Das Missões	431845	23	2720	27.74	são-josezense	98.071
4309	São José Do Hortêncio	431848	23	4094	63.86	hortenciense	64.113
4311	São José Do Norte	431850	23	25503	22.81	nortense	1118.109
4312	São José Do Ouro	431860	23	6904	20.62	ourense	334.775
4314	São José Dos Ausentes	431862	23	3290	2.8	ausentino	1176.688
4315	São Leopoldo	431870	23	214087	2.083	leopoldense	102.739
4317	São Luiz Gonzaga	431890	23	34556	26.67	são-luizense	1295.683
4318	São Marcos	431900	23	20103	78.45	são-marquense	256.253
4320	São Martinho Da Serra	431912	23	3201	4.78	martinhense	669.55
4322	São Nicolau	431920	23	5727	11.8	são-nicolauense	485.326
4324	São Pedro Da Serra	431935	23	3315	93.68	são-pedrense	35.387
4325	São Pedro Das Missões	431936	23	1886	23.59	são pedrense 	79.965
4327	São Pedro Do Sul	431940	23	16368	18.74	são-pedrense	873.597
4328	São Sebastião Do Caí	431950	23	21932	196.81	caiense	111.435
4330	São Valentim	431970	23	3632	23.56	valentinense	154.189
4332	São Valério Do Sul	431973	23	2647	24.52	são-valerense	107.971
4333	São Vendelino	431975	23	1944	60.59	são-vendelinense	32.087
4335	Sapiranga	431990	23	74985	542.13	sapiranguense	138.315
4337	Sarandi	432010	23	21285	60.23	sarandiense	353.389
4338	Seberi	432020	23	10897	36.15	seberiense	301.421
4340	Segredo	432026	23	7158	28.93	segredense	247.44
4342	Senador Salgado Filho	432032	23	2814	19.12	salgadofilhense	147.21
4344	Serafina Corrêa	432040	23	14253	87.29	serafinense	163.284
4345	Sério	432045	23	2281	22.9	seriense	99.627
4347	Sertão Santana	432055	23	5850	23.23	sertanense	251.847
4349	Severiano De Almeida	432060	23	3842	22.92	severianense	167.614
4351	Sinimbu	432067	23	10068	19.74	sinimbuense 	510.122
4352	Sobradinho	432070	23	14283	109.54	sobradinhense	130.391
4354	Tabaí	432085	23	4131	43.6	tabaiense	94.755
4356	Tapera	432100	23	10448	58.15	taperense	179.663
4357	Tapes	432110	23	16629	20.62	tapense	806.299
4359	Taquari	432130	23	26092	74.56	taquariense	349.968
4361	Tavares	432135	23	5351	8.86	tavarense	604.253
4363	Terra De Areia	432143	23	9878	69.67	terrareense	141.773
4364	Teutônia	432145	23	27272	152.68	teutoniense	178.625
4366	Tiradentes Do Sul	432147	23	6461	27.55	tiradentense	234.483
4368	Torres	432150	23	34656	216.34	torrense	160.194
4370	Travesseiro	432162	23	2314	28.52	travesseirense	81.122
4372	Três Cachoeiras	432166	23	10217	40.7	três cachoeirense	251.059
4373	Três Coroas	432170	23	23848	128.53	três-coroense	185.54
4375	Três Forquilhas	432183	23	2914	13.4	forquilhense	217.39
4377	Três Passos	432190	23	23965	89.29	três-passense	268.397
4378	Trindade Do Sul	432195	23	5787	21.56	trindadense	268.418
4380	Tucunduva	432210	23	5898	32.62	tucunduvense	180.81
4382	Tupanci Do Sul	432218	23	1573	11.64	tupancisense	135.115
4384	Tupandi	432225	23	3924	65.9	tupandiense	59.542
4385	Tuparendi	432230	23	8557	27.81	tuparendiense	307.677
4387	Ubiretama	432234	23	2296	18.12	ubiretamense	126.693
4389	Unistalda	432237	23	2450	4.07	unistaldense	602.39
4391	Vacaria	432250	23	61342	28.88	vacariense 	2123.683
4392	Vale Do Sol	432253	23	11077	33.75	vale-solense	328.228
4394	Vale Verde	432252	23	3253	9.87	valeverdense	329.728
4396	Venâncio Aires	432260	23	65946	85.28	venâncio-airense	773.244
4398	Veranópolis	432280	23	22810	78.83	veranense	289.343
4400	Viadutos	432290	23	5311	19.79	viadutense	268.36
4401	Viamão	432300	23	239384	159.91	viamense	1497.023
4404	Vila Flores	432330	23	3207	29.72	vila-florense	107.91
4406	Vila Maria	432340	23	4221	23.26	vila-mariense	181.44
4408	Vista Alegre	432350	23	2832	36.56	vista-alegrense	77.455
4409	Vista Alegre Do Prata	432360	23	1569	13.15	vista-alegrense	119.328
4411	Vitória Das Missões	432375	23	3485	13.42	vitoriano	259.61
4412	Westfália	432377	23	2793	43.64	westfaliano	63.998
4414	Abdon Batista	420005	24	2653	11.26	abdonense	235.598
4416	Agrolândia	420020	24	9323	45.01	agrolandense	207.12
4418	Água Doce	420040	24	6961	5.3	água-docense	1313.02
4420	Águas Frias	420055	24	2424	32.25	águasfriense	75.162
4421	Águas Mornas	420060	24	5548	16.99	águas-mornense	326.524
4423	Alto Bela Vista	420075	24	2005	19.35	bela-vistense	103.593
4425	Angelina	420090	24	5250	10.5	angelinense	499.949
4427	Anitápolis	420110	24	3214	5.93	anitapolitano	542.381
4428	Antônio Carlos	420120	24	7458	32.55	antônio-carlense	229.119
4430	Arabutã	420127	24	4193	31.71	arabutanense	132.232
4432	Araranguá	420140	24	61310	201.74	araranguaense	303.907
4433	Armazém	420150	24	7753	44.69	armazenense	173.485
4435	Arvoredo	420165	24	2260	24.91	arvoredense	90.709
4437	Atalanta	420180	24	3300	34.91	atalantense	94.526
4439	Balneário Arroio Do Silva	420195	24	9586	101.33	arroio-silvense	94.601
4441	Balneário Camboriú	420200	24	108089	2.309	praiano	46.797
4442	Balneário Gaivota	420207	24	8234	55.83	gaivotense	147.496
4444	Bandeirante	420208	24	2906	19.87	bandeirantense	146.256
4445	Barra Bonita	420209	24	1878	20.09	barrabonitense	93.471
4447	Bela Vista Do Toldo	420213	24	6004	11.23	bela vistense	534.62
4449	Benedito Novo	420220	24	10336	26.62	benedito-novense	388.209
4451	Blumenau	420240	24	309011	594.44	blumenauense	519.835
4453	Bom Jardim Da Serra	420250	24	4395	4.7	bom-jardinense	935.176
4454	Bom Jesus	420253	24	2526	39.75	bonjesuense	63.553
4456	Bom Retiro	420260	24	8942	8.47	bom-retirense	1055.504
4457	Bombinhas	420245	24	14293	423.28	bombinense	33.767
4459	Braço Do Norte	420280	24	29018	137.12	braço-nortense	211.626
4461	Brunópolis	420287	24	2850	8.49	brunopolitense	335.513
4463	Caçador	420300	24	70762	72.07	caçadorense	981.903
4464	Caibi	420310	24	6219	36.22	caibiense	171.712
4466	Camboriú	420320	24	62361	290.73	camboriuense	214.499
4468	Campo Belo Do Sul	420340	24	7483	7.28	campo-belense	1027.411
4470	Campos Novos	420360	24	32824	19.09	campos-novense	1719.18
4471	Canelinha	420370	24	10603	70.03	canelense	151.411
4473	Capão Alto	420325	24	2753	2.06	capão altense	1335.281
4475	Capivari De Baixo	420395	24	21674	407.68	capivariense	53.164
4477	Caxambu Do Sul	420410	24	4411	31.38	caxambuense	140.58
4479	Cerro Negro	420417	24	3581	8.59	cerronegrense	416.775
4481	Chapecó	420420	24	183530	293.98	chapecoense	624.304
4482	Cocal Do Sul	420425	24	15159	212.88	cocalense	71.209
4484	Cordilheira Alta	420435	24	3767	44.97	cordilheiraltense	83.77
4486	Coronel Martins	420445	24	2458	22.88	coronel martiense	107.408
4488	Corupá	420450	24	13852	34.2	corupaense	405.002
4489	Criciúma	420460	24	192308	816.15	criciumense	235.627
4491	Cunhataí	420475	24	1882	34.53	cunhataiense	54.509
4493	Descanso	420490	24	8634	30.23	descansense	285.565
4495	Dona Emma	420510	24	3721	20.56	donemense	181.019
4496	Doutor Pedrinho	420515	24	3604	9.59	pedrinhense	375.753
4498	Ermo	420519	24	2050	32.09	ermense	63.88
4499	Erval Velho	420520	24	4352	21	ervalense	207.238
4501	Flor Do Sertão	420535	24	1588	27.05	flor-sertanense	58.709
4503	Formosa Do Sul	420543	24	2601	26.12	formoense do sul	99.576
4505	Fraiburgo	420550	24	34553	63.25	fraiburgense	546.254
4506	Frei Rogério	420555	24	2474	15.67	frei rogeriense	157.846
4508	Garopaba	420570	24	18138	156.96	garopabense	115.56
4510	Gaspar	420590	24	57981	150.07	gasparense	386.357
4512	Grão Pará	420610	24	6223	18.51	grão-paraense	336.17
4513	Gravatal	420620	24	10635	63.15	gravatalense	168.418
4515	Guaraciaba	420640	24	10498	31.75	guaraciabense	330.647
4517	Guarujá Do Sul	420660	24	4908	48.82	guarujaense	100.54
4519	Herval D`Oeste	420670	24	21239	97.95	hervalense	216.842
4520	Ibiam	420675	24	1945	13.2	ibianense	147.329
4522	Ibirama	420690	24	17330	70.25	ibiramense	246.705
4524	Ilhota	420710	24	12355	48.75	ilhotense	253.442
4526	Imbituba	420730	24	40170	220.06	imbitubense	182.541
4527	Imbuia	420740	24	5707	46.82	imbuiense	121.9
4529	Iomerê	420757	24	2739	23.87	iomerense	114.733
5426	Zacarias	355715	26	2335	7.32	zacariense	319.139
4532	Ipuaçu	420768	24	6798	26.01	ipuaçuense	261.391
4533	Ipumirim	420770	24	7220	29.22	ipumiriense	247.067
4535	Irani	420780	24	9531	29.14	iraniense	327.049
4537	Irineópolis	420790	24	10448	17.67	irineopolitense	591.292
4538	Itá	420800	24	6426	38.84	itaense	165.463
4540	Itajaí	420820	24	183373	633.75	itajaiense	289.345
4542	Itapiranga	420840	24	15409	55.01	itapiranguense	280.114
4544	Ituporanga	420850	24	22250	66.03	ituporanguense	336.957
4545	Jaborá	420860	24	4041	21.14	jaboraense	191.119
4547	Jaguaruna	420880	24	17290	52.49	jaguarunense	329.371
4549	Jardinópolis	420895	24	1766	25.93	jardinopolense	68.098
4551	Joinville	420910	24	515288	449.3	joinvilense	1146.873
4552	José Boiteux	420915	24	4721	11.64	josé-boatense	405.52
4554	Lacerdópolis	420920	24	2199	32.12	lacerdopolitano	68.453
4556	Laguna	420940	24	51562	117	lagunense	440.707
4558	Laurentino	420950	24	6004	75.52	laurentinense	79.506
4559	Lauro Muller	420960	24	14367	53.11	lauro-milense	270.511
4561	Leoberto Leal	420980	24	3365	11.56	leobertense	291.192
4563	Lontras	420990	24	10244	51.63	lontrense	198.398
4564	Luiz Alves	421000	24	10438	40.13	luiz-alvense	260.081
4566	Macieira	421005	24	1826	7.02	macieirense	260.074
4568	Major Gercino	421020	24	3279	11.48	majorense	285.679
4570	Maracajá	421040	24	6404	101.01	maracajaense	63.401
4571	Maravilha	421050	24	22101	130.43	maravilhense	169.447
4573	Massaranduba	421060	24	14674	39.31	massarandubense	373.297
4575	Meleiro	421080	24	7000	37.51	meleirense	186.619
4577	Modelo	421090	24	4045	43.63	modelense	92.717
4579	Monte Carlo	421105	24	9312	48.06	montecarlense	193.763
4581	Morro Da Fumaça	421120	24	16126	194.44	fumacense	82.935
4582	Morro Grande	421125	24	2890	11.27	morrograndense	256.416
4584	Nova Erechim	421140	24	4275	66.38	nova-erechinense	64.402
4586	Nova Trento	421150	24	12190	30.31	nova-trentino	402.119
4587	Nova Veneza	421160	24	13309	45.34	veneziano	293.54
4589	Orleans	421170	24	21393	38.91	orleanense	549.827
4591	Ouro	421180	24	7372	34.66	ourense	212.669
4592	Ouro Verde	421185	24	2271	12	ouro-verdense	189.269
4594	Painel	421189	24	2353	3.18	painelense	739.842
4596	Palma Sola	421200	24	7765	23.4	palma-solense	331.777
4598	Palmitos	421210	24	16020	45.68	palmitense	350.692
4599	Papanduva	421220	24	17928	23.59	papanduvense	759.834
4601	Passo De Torres	421225	24	6627	69.61	passotorrense	95.196
4603	Paulo Lopes	421230	24	6692	14.86	paulo-lopense	450.374
4605	Penha	421250	24	25141	405.72	penhense	61.966
4606	Peritiba	421260	24	2988	31	peritibense	96.402
4608	Pinhalzinho	421290	24	16332	127.3	pinhalense	128.298
4610	Piratuba	421310	24	4786	32.85	piratubense	145.704
4612	Pomerode	421320	24	27759	128.57	pomerodense	215.905
4613	Ponte Alta	421330	24	4894	8.64	ponte-altense	566.753
4615	Ponte Serrada	421340	24	11031	19.56	ponte-serradense	564.001
4617	Porto União	421360	24	33493	39.35	porto-unionense	851.244
4619	Praia Grande	421380	24	7267	26.09	praia-grandense	278.576
4620	Presidente Castello Branco	421390	24	1725	26.39	castelinense	65.361
4622	Presidente Nereu	421410	24	2284	10.17	nereusense	224.674
4624	Quilombo	421420	24	10248	36.69	quilombense	279.281
4626	Rio Das Antas	421440	24	6143	19.37	rio-antense	317.188
4627	Rio Do Campo	421450	24	6192	12.23	rio-campense	506.199
4629	Rio Do Sul	421480	24	61198	236.83	rio-sulense	258.402
4631	Rio Fortuna	421490	24	4446	14.73	rio-fortunense	301.932
4632	Rio Negrinho	421500	24	39846	43.86	rio-negrinhense	908.401
4634	Riqueza	421507	24	4838	25.43	riquezense	190.279
4636	Romelândia	421520	24	5551	24.81	romelandino	223.75
4637	Salete	421530	24	7370	41.1	saletense	179.309
4639	Salto Veloso	421540	24	4301	40.95	velosoense	105.041
4641	Santa Cecília	421550	24	15757	13.76	ceciliense	1145.324
4643	Santa Rosa De Lima	421560	24	2065	10.17	rosa-limense	202.977
4645	Santa Terezinha	421567	24	8767	12.24	terezinhense	716.254
4646	Santa Terezinha Do Progresso	421568	24	2896	24.34	terezinhano	118.998
4648	Santo Amaro Da Imperatriz	421570	24	19823	57.46	santo-amarense	344.968
4650	São Bernardino	421575	24	2677	18.47	bernardinense	144.96
4651	São Bonifácio	421590	24	3008	6.52	são-bonifacense	461.302
4653	São Cristovão Do Sul	421605	24	5012	14.36	são-cristovense 	348.967
4655	São Francisco Do Sul	421620	24	42520	86.25	francisquense	492.973
4658	São João Do Oeste	421625	24	6036	36.88	são-joanense	163.651
4660	São Joaquim	421650	24	24812	13.16	joaquinense	1885.61
4661	São José	421660	24	209804	1.388	josefense	151.137
4663	São José Do Cerrito	421680	24	9273	9.8	cerritense	946.246
4665	São Ludgero	421700	24	10993	102.19	são-ludgerense	107.572
4667	São Miguel Da Boa Vista	421715	24	1904	26.47	boa-vistense	71.922
4668	São Miguel Do Oeste	421720	24	36306	154.89	miguel-oestino	234.399
4670	Saudades	421730	24	9016	43.86	saudadense	205.557
4671	Schroeder	421740	24	15316	106.68	cheredense	143.567
4673	Serra Alta	421755	24	3285	36.32	serra-altense	90.444
4675	Sombrio	421770	24	26613	186.43	sombriense	142.753
4677	Taió	421780	24	17260	24.91	taioense	693.026
4678	Tangará	421790	24	8674	22.29	tangaraense	389.187
4680	Tijucas	421800	24	30960	111.69	tijucano	277.204
4682	Timbó	421820	24	36774	288.99	timboense	127.249
4684	Três Barras	421830	24	18129	41.38	três-barrense	438.066
4685	Treviso	421835	24	3527	22.37	trevisano	157.668
4687	Treze Tílias	421850	24	6341	34.24	treze-tiliense	185.206
4689	Tubarão	421870	24	97235	323.76	tubaronense	300.335
4690	Tunápolis	421875	24	4633	34.86	tunapolitano	132.909
4692	União Do Oeste	421885	24	2910	31.27	união-oestense	93.059
4694	Urupema	421895	24	2482	7.03	urupemense	353.126
4696	Vargeão	421910	24	3532	21.22	vargeonense	166.446
4697	Vargem	421915	24	2808	8.02	vargense	350.125
4699	Vidal Ramos	421920	24	6290	18.55	vidal-ramense	339.061
4701	Vitor Meireles	421935	24	5207	14.01	vitor-meirelense	371.564
4703	Xanxerê	421950	24	44128	116.88	xanxerense	377.554
4705	Xaxim	421970	24	25713	87.25	xaxiense	294.716
4706	Zortéa	421985	24	2991	15.73	zorteense	190.149
4708	Aquidabã	280020	25	20056	55.82	aquidabãense	359.284
4710	Arauá	280040	25	10878	56.44	arauaense	192.728
4712	Barra Dos Coqueiros	280060	25	24976	276.52	barra-coqueirense	90.322
4713	Boquim	280067	25	25533	123.98	boquinense	205.938
4715	Campo Do Brito	280100	25	16749	83.03	campo-britense	201.724
4717	Canindé De São Francisco	280120	25	24686	27.36	canindense	902.241
4718	Capela	280130	25	30761	69.48	capelense	442.742
4720	Carmópolis	280150	25	13503	294.15	carmopolense	45.905
4722	Cristinápolis	280170	25	16519	69.94	cristinapolense	236.185
4724	Divina Pastora	280200	25	4326	47.13	divina-pastorense	91.791
4726	Feira Nova	280220	25	5324	28.79	feira-novense	184.932
4727	Frei Paulo	280230	25	13874	34.65	frei-paulense	400.361
4729	General Maynard	280250	25	2929	146.63	mainardense	19.975
4731	Ilha Das Flores	280270	25	8348	152.78	ilha-florense	54.639
4733	Itabaiana	280290	25	86967	258.3	itabaianense	336.692
4734	Itabaianinha	280300	25	38910	78.88	itabaianinhense	493.311
4736	Itaporanga D`Ajuda	280320	25	30419	41.11	itaporanguense	739.922
4738	Japoatã	280340	25	12938	31.76	japoatãnense	407.419
4740	Laranjeiras	280360	25	26902	165.78	laranjeirense	162.279
4741	Macambira	280370	25	6401	46.74	macambirense	136.936
4743	Malhador	280390	25	12042	119.3	malhadorense	100.941
4745	Moita Bonita	280410	25	11001	114.81	moita-bonitense	95.819
4747	Muribeca	280430	25	7344	96.81	muribequense	75.863
4748	Neópolis	280440	25	18506	69.58	neopolense	265.951
4750	Nossa Senhora Da Glória	280450	25	32497	42.96	glorense	756.486
4752	Nossa Senhora De Lourdes	280470	25	6238	76.95	lourdense	81.061
4753	Nossa Senhora Do Socorro	280480	25	160827	1.025	socorrense	156.77
4755	Pedra Mole	280500	25	2974	36.26	pedra-molense	82.026
4757	Pinhão	280520	25	5973	38.32	pinhãoense	155.887
4758	Pirambu	280530	25	8369	40.65	pirambuense	205.878
4760	Poço Verde	280550	25	21983	49.95	poço-verdense	440.131
4762	Propriá	280570	25	28451	307.71	propriaense	92.461
4764	Riachuelo	280590	25	9355	118.51	riachuelense	78.937
4765	Ribeirópolis	280600	25	17173	66.42	ribeiropolense	258.533
4767	Salgado	280620	25	19365	78.14	salgadense	247.827
4769	Santa Rosa De Lima	280650	25	3749	55.45	santa-rosense	67.607
4770	Santana Do São Francisco	280640	25	7038	154.27	santanense	45.62
4772	São Cristóvão	280670	25	78864	180.52	são-cristóvense	436.861
4774	São Francisco	280690	25	3393	40.46	são-francisquense	83.854
4775	São Miguel Do Aleixo	280700	25	3698	25.66	aleixense	144.088
4777	Siriri	280720	25	8004	48.27	siririense	165.812
4779	Tobias Barreto	280740	25	48040	47.04	tobiense	1021.304
4781	Umbaúba	280760	25	22434	185.25	umbaubense	121.1
4784	Aguaí	350030	26	32148	67.72	aguaiano	474.741
4786	Águas De Lindóia	350050	26	17266	287.16	lindoiense	60.126
4788	Águas De São Pedro	350060	26	2707	504.1	água-pedrense	5.37
4789	Agudos	350070	26	34524	35.73	agudense	966.161
4791	Alfredo Marcondes	350080	26	3891	32.86	marcondense	118.399
4793	Altinópolis	350100	26	15607	16.78	altinopolense	929.836
4794	Alto Alegre	350110	26	4102	12.86	alto-alegrense	319.035
4797	Álvares Machado	350130	26	23513	67.69	machadense	347.378
4798	Álvaro De Carvalho	350140	26	4650	30.36	álvaro-carvalhense	153.165
4800	Americana	350160	26	210638	1.579	americanense	133.35
4802	Américo De Campos	350180	26	5706	22.54	americampense	253.101
4803	Amparo	350190	26	65829	147.75	amparense	445.553
4805	Andradina	350210	26	55334	57.39	andradinense	964.191
4807	Anhembi	350230	26	5653	7.68	anhembiense	736.509
4808	Anhumas	350240	26	3738	11.67	anhumense	320.419
4810	Aparecida D`Oeste	350260	26	4450	24.86	aparecidense	179.016
4812	Araçariguama	350275	26	17080	116.72	araçariguamense	146.339
4814	Araçoiaba Da Serra	350290	26	27299	106.87	araçoiabano	255.446
4816	Arandu	350310	26	6123	21.42	aranduense	285.908
4817	Arapeí	350315	26	2493	15.9	arapeiense	156.835
4819	Araras	350330	26	118843	184.3	ararense	644.831
4821	Arealva	350340	26	7841	15.53	arealvense	504.974
4823	Areiópolis	350360	26	10579	123.34	areiopolitano	85.768
4825	Artur Nogueira	350380	26	44177	248.15	nogueirense	178.026
4826	Arujá	350390	26	74905	777.35	arujaense	96.359
4828	Assis	350400	26	95144	206.7	assisense	460.308
4830	Auriflama	350420	26	14202	32.72	auriflamense	433.986
4832	Avanhandava	350440	26	11310	33.4	avanhandavense	338.645
4833	Avaré	350450	26	82934	68.37	avareense	1213.057
4835	Balbinos	350470	26	3702	40.4	balbinense	91.635
4837	Bananal	350490	26	10223	16.59	bananalense	616.04
4839	Barbosa	350510	26	6593	32.14	barbosano	205.15
4840	Bariri	350520	26	31593	71.14	baririense	444.069
4842	Barra Do Chapéu	350535	26	5244	12.93	barrense	405.681
4844	Barretos	350550	26	112101	71.6	barretense	1565.64
4845	Barrinha	350560	26	28496	195.66	barrinhense	145.643
4847	Bastos	350580	26	20445	118.95	bastense	171.885
4849	Bauru	350600	26	343937	515.12	bauruense	667.681
4851	Bento De Abreu	350620	26	2674	8.87	bento-abreuense	301.395
4853	Bertioga	350635	26	47645	97.23	bertioguense	490.03
4854	Bilac	350640	26	7048	44.63	bilaquense	157.903
4856	Biritiba Mirim	350660	26	28575	90.1	biritibano	317.158
4858	Bocaina	350680	26	10859	29.84	bocainense	363.927
4859	Bofete	350690	26	9618	14.72	bofetense	653.542
4861	Bom Jesus Dos Perdões	350710	26	19708	183.08	perdoense	107.647
4863	Borá	350720	26	805	6.8	boraense	118.45
4864	Boracéia	350730	26	4268	34.95	boraceense	122.11
4866	Borebi	350745	26	2293	6.59	borebiense	347.989
4868	Bragança Paulista	350760	26	146744	286.26	bragantino	512.622
4870	Brejo Alegre	350775	26	2573	24.41	brejoalegrense	105.399
4871	Brodowski	350780	26	21107	75.51	brodosquiano	279.521
4873	Buri	350800	26	18563	15.52	buriense	1195.911
4875	Buritizal	350820	26	4053	15.21	buritinense	266.42
4877	Cabreúva	350840	26	41604	159.91	cabreuvano	260.164
4878	Caçapava	350850	26	84752	228.91	caçapavense	370.247
4880	Caconde	350870	26	18538	39.44	cacondense	469.98
4882	Caiabu	350890	26	4072	16.11	caiabuense	252.84
4884	Caiuá	350910	26	5039	9.13	caiuense	552.141
4885	Cajamar	350920	26	64114	487.92	cajamarense	131.403
4887	Cajobi	350930	26	9768	55.22	cajobiense	176.897
4889	Campina Do Monte Alegre	350945	26	5567	30.09	campinense	185.031
4891	Campo Limpo Paulista	350960	26	74074	930.79	campo-limpense	79.582
4893	Campos Novos Paulista	350980	26	4539	9.38	campos-novense	483.98
4894	Cananéia	350990	26	12226	9.84	cananeiense	1242.949
4896	Cândido Mota	351000	26	29884	50.12	cândido-motense	596.211
4898	Canitar	351015	26	4369	76.34	canitarense	57.231
4900	Capela Do Alto	351030	26	17532	103.2	capelense	169.89
4901	Capivari	351040	26	48576	150.53	capivariano	322.707
4903	Carapicuíba	351060	26	369584	10.68	carapicuibano	34.605
4905	Casa Branca	351080	26	28307	32.76	casa-branquense	864.181
4907	Castilho	351100	26	18003	16.89	castilhense	1065.804
4908	Catanduva	351110	26	112820	388.24	catanduvense	290.596
4910	Cedral	351130	26	7972	40.38	cedralense	197.429
4913	Cesário Lange	351160	26	15540	81.46	cesariano-lange	190.767
4915	Chavantes	355720	26	12114	64.4	chavantense	188.102
4916	Clementina	351190	26	7065	41.85	clementinense	168.836
4918	Colômbia	351210	26	5994	8.22	colombiano	729.254
4920	Conchas	351230	26	16288	34.95	conchense	466.024
4922	Coroados	351250	26	5238	21.26	coroadense	246.357
4924	Corumbataí	351270	26	3874	13.9	corumbataiense	278.622
4925	Cosmópolis	351280	26	58827	380.37	cosmopolense	154.659
4927	Cotia	351300	26	201150	622.55	cotiano	323.104
4928	Cravinhos	351310	26	31691	101.84	cravinhense	311.193
4930	Cruzália	351330	26	2274	15.26	cruzaliense	149.054
4932	Cubatão	351350	26	118720	833.81	cubatonense	142.382
4934	Descalvado	351370	26	31056	41.2	descalvadense	753.706
4935	Diadema	351380	26	386089	12.519	diademense	30.84
4937	Divinolândia	351390	26	11208	50.46	divinolandense	222.127
4939	Dois Córregos	351410	26	24761	39.12	dois-correguense	632.973
4941	Dourado	351430	26	8609	41.82	douradense	205.875
4943	Duartina	351450	26	12251	46.31	duartinense	264.556
4944	Dumont	351460	26	8143	73.19	dumonense	111.254
4946	Eldorado	351480	26	14641	8.85	eldoradense	1654.259
4948	Elisiário	351492	26	3120	33.2	elisiarense	93.98
4950	Embu Das Artes	351500	26	240230	3.412	embuense	70.397
4951	Embu Guaçu	351510	26	62769	405.11	embu-guaçuense	154.945
4953	Engenheiro Coelho	351515	26	15721	142.99	engenheiro coelhense	109.941
4955	Espírito Santo Do Turvo	351519	26	4244	21.92	espírito santense	193.656
4957	Estrela D`Oeste	351520	26	8208	27.69	estrelense	296.41
4958	Estrela Do Norte	351530	26	2658	10.09	estrelense	263.42
4960	Fartura	351540	26	15320	35.7	farturense	429.172
4962	Fernandópolis	351550	26	64696	117.62	fernandopolense	550.033
4963	Fernão	351565	26	1563	15.51	fernãoense	100.761
4965	Flora Rica	351580	26	1752	7.78	flora-riquense	225.299
4966	Floreal	351590	26	3003	14.7	florealense	204.296
4968	Flórida Paulista	351600	26	12848	24.47	floridense	525.083
4970	Francisco Morato	351630	26	154472	3.13	moratense	49.345
4972	Gabriel Monteiro	351650	26	2708	19.55	monteirense	138.546
4973	Gália	351660	26	7011	19.69	galiense	356.011
4975	Gastão Vidigal	351680	26	4193	23.17	vidigalense	180.938
4977	General Salgado	351690	26	10669	21.63	salgadense	493.348
4979	Glicério	351710	26	4565	16.69	glicerense	273.553
4980	Guaiçara	351720	26	10670	39.48	guaiçarense	270.23
4982	Guaíra	351740	26	37404	29.72	guairense	1258.476
4984	Guapiara	351760	26	17998	44.08	guapiense	408.293
4986	Guaraçaí	351780	26	8435	14.8	guaraçaiense	569.87
4988	Guarani D`Oeste	351800	26	1970	23.03	guaraniense	85.53
4989	Guarantã	351810	26	6404	13.89	guarantãense	461.149
4991	Guararema	351830	26	25844	95.5	guararemense	270.604
4993	Guareí	351850	26	14565	25.72	guareiense	566.347
4995	Guarujá	351870	26	290752	2.034	guarujaense	142.882
4996	Guarulhos	351880	26	1221979	3.828	guarulhense	319.191
4998	Guzolândia	351890	26	4754	18.86	guzolandense	252.015
5000	Holambra	351905	26	11299	172.3	holambrense	65.579
5002	Iacanga	351910	26	10013	18.29	iacanguense	547.393
5003	Iacri	351920	26	6419	19.9	iacriano	322.633
5005	Ibaté	351930	26	30734	105.74	ibateense	290.663
5007	Ibirarema	351950	26	6725	29.45	ibiraremense	228.318
5009	Ibiúna	351970	26	71217	67.34	ibiunense	1057.542
5010	Icém	351980	26	7462	20.58	icense	362.594
5012	Igaraçu Do Tietê	352000	26	23362	239.07	igaraçuense	97.72
5014	Igaratá	352020	26	8831	30.14	igaratense	292.955
5016	Ilha Comprida	352042	26	9025	47.9	ilha compridense	188.404
5018	Ilhabela	352040	26	28196	81.13	ilhabelense	347.537
5019	Indaiatuba	352050	26	201619	647.54	indaiatubano	311.363
5021	Indiaporã	352070	26	3903	13.96	indiaporãense	279.597
5023	Ipaussu	352090	26	13663	65.17	ipauçuense	209.657
5024	Iperó	352100	26	28300	166.42	iperoense	170.048
5026	Ipiguá	352115	26	4463	32.62	ipiguarense	136.824
5028	Ipuã	352130	26	14148	30.37	ipuãnense	465.884
5030	Irapuã	352150	26	7275	28.21	irapuense	257.908
5031	Irapuru	352160	26	7789	36.24	irapuruense	214.904
5033	Itaí	352180	26	24008	21.61	itaiense	1111.182
5035	Itaju	352200	26	3246	14.12	itajuense	229.824
5037	Itaóca	352215	26	3228	17.64	itaoquense	183.015
5039	Itapetininga	352230	26	144377	80.65	itapetingano	1790.212
5040	Itapeva	352240	26	87753	48.05	itapevense	1826.261
5043	Itapirapuã Paulista	352265	26	3880	9.55	itapirapuã paulistense	406.479
5044	Itápolis	352270	26	40051	40.18	itapolitano	996.853
5046	Itapuí	352290	26	12173	86.46	itapuiense	140.799
5048	Itaquaquecetuba	352310	26	321770	3.877	itaquaquecetubano	82.979
5050	Itariri	352330	26	15471	56.5	itaririense	273.845
5052	Itatinga	352350	26	18052	18.42	itatinguense	979.819
5054	Itirapuã	352370	26	5914	36.71	itirapuãnense	161.118
5055	Itobi	352380	26	7546	54.2	itobiano	139.214
5057	Itupeva	352400	26	44859	223.46	itupevense	200.748
5059	Jaborandi	352420	26	6592	24.11	jaborandiense	273.438
5061	Jacareí	352440	26	211214	459.48	jacareiense	459.684
5063	Jacupiranga	352460	26	17208	24.44	jacupiranguense	704.09
5064	Jaguariúna	352470	26	44311	312.56	jaguariunense	141.769
5066	Jambeiro	352490	26	5349	29.03	jambeirense	184.258
5068	Jardinópolis	352510	26	37661	74.95	jardinopolense	502.482
5070	Jaú	352530	26	131040	191.09	jauense	685.762
5071	Jeriquara	352540	26	3160	22.26	jeriquarense	141.971
5073	João Ramalho	352560	26	4150	9.99	ramalhense	415.249
5075	Júlio Mesquita	352580	26	4430	34.55	júlio-mesquitense	128.22
5077	Jundiaí	352590	26	370126	857.77	jundiaiense	431.498
5079	Juquiá	352610	26	19246	23.41	juquiaense	821.976
5080	Juquitiba	352620	26	28737	55.04	juquitibense	522.064
5082	Laranjal Paulista	352640	26	25251	65.75	laranjalense	384.022
5084	Lavrinhas	352660	26	6590	39.45	lavrinhense	167.067
5085	Leme	352670	26	91756	227.75	lemense	402.873
5087	Limeira	352690	26	276022	475.08	limeirense	581.002
5089	Lins	352710	26	71432	125.27	linense	570.238
5090	Lorena	352720	26	82537	199.19	lorenense	414.358
5092	Louveira	352730	26	37125	673.37	louveirense	55.133
5094	Lucianópolis	352750	26	2249	11.85	lucianopolense	189.816
5096	Luiziânia	352770	26	5030	30.2	luiziano	166.549
5097	Lupércio	352780	26	4353	28.18	lupercense	154.485
5099	Macatuba	352800	26	16259	72.19	macatubense	225.212
5101	Macedônia	352820	26	3664	11.18	macedoniense	327.725
5103	Mairinque	352840	26	43223	206.18	mairinquense	209.636
5105	Manduri	352860	26	8992	39.26	mandurinense	229.053
5107	Maracaí	352880	26	13332	24.97	maracaiense	533.938
5108	Marapoama	352885	26	2633	23.66	marapoamense	111.267
5110	Marília	352900	26	216745	185.21	mariliense	1170.252
5112	Martinópolis	352920	26	24219	19.33	martinopolense	1252.716
5113	Matão	352930	26	76786	146.3	matonense	524.855
5115	Mendonça	352950	26	4640	23.79	mendoncino	195.039
5117	Mesópolis	352965	26	1886	12.67	mesopolense	148.856
5119	Mineiros Do Tietê	352980	26	12038	56.45	mineirense	213.243
5120	Mira Estrela	353000	26	2820	13.01	mira-estrelense	216.831
5122	Mirandópolis	353010	26	27483	29.91	mirandopolense	918.801
5124	Mirassol	353030	26	53792	221.22	mirassolense	243.161
5125	Mirassolândia	353040	26	4295	25.85	mirassolandense	166.168
5127	Mogi Das Cruzes	353060	26	387779	543.65	mogiano	713.291
5129	Moji Mirim	353080	26	86505	173.78	mogi-miriano	497.779
5131	Monções	353100	26	2132	20.45	monçolense	104.237
5132	Mongaguá	353110	26	46293	325.72	mongaguano	142.126
5134	Monte Alto	353130	26	46642	134.61	monte-altense	346.501
5136	Monte Azul Paulista	353150	26	18931	71.86	monte-azulense	263.444
5138	Monte Mor	353180	26	48949	203.55	monte-morense 	240.481
5139	Monteiro Lobato	353170	26	4120	12.36	lobatense	333.332
5141	Morungaba	353200	26	11769	80.14	morungabense	146.852
5143	Murutinga Do Sul	353210	26	4186	16.69	murutinguense	250.837
5144	Nantes	353215	26	2707	9.46	nantense	286.162
5146	Natividade Da Serra	353230	26	6678	8.01	nativense	833.372
5148	Neves Paulista	353250	26	8772	40.18	nevense	218.34
5149	Nhandeara	353260	26	10725	24.61	nhandearense	435.772
5151	Nova Aliança	353280	26	5891	27.11	nova-aliancense	217.311
5153	Nova Canaã Paulista	353284	26	2114	16.99	novacanaense	124.419
5155	Nova Europa	353290	26	9300	58	nova-europense	160.353
5156	Nova Granada	353300	26	19180	36.06	granadense	531.885
5158	Nova Independência	353320	26	3068	11.54	independentino	265.777
5160	Nova Odessa	353340	26	51242	694.34	novaodessense	73.8
5161	Novais	353325	26	4592	38.99	novaense	117.772
5163	Nuporanga	353360	26	6817	19.57	nuporanguense	348.265
5165	Óleo	353380	26	2673	13.49	oleense	198.136
5166	Olímpia	353390	26	50024	62.32	olimpiense	802.652
5168	Oriente	353410	26	6097	27.89	orientense	218.608
5170	Orlândia	353430	26	39781	136.34	orlandino	291.774
5428	Aguiarnópolis	170030	27	5162	21.93	aguiarnopolense	235.393
5173	Osvaldo Cruz	353460	26	30917	124.47	osvaldo-cruzense	248.391
5174	Ourinhos	353470	26	103035	347.78	ourinhense	296.269
5176	Ouroeste	353475	26	8405	29.1	ouroestense	288.839
5178	Palestina	353500	26	11051	15.89	palestinense	695.457
5180	Palmeira D`Oeste	353520	26	9584	30.02	palmeirense	319.222
5181	Palmital	353530	26	21186	38.67	palmitalense	547.806
5183	Paraguaçu Paulista	353550	26	42278	42.28	paraguaçuense	999.935
5185	Paraíso	353570	26	5898	37.85	paraisense	155.842
5187	Paranapuã	353590	26	3815	27.16	paranapuense	140.475
5188	Parapuã	353600	26	10844	29.65	parapuense	365.695
5190	Pariquera Açu	353620	26	18446	51.34	pariquerense	359.305
5192	Patrocínio Paulista	353630	26	13000	21.56	patrocinense	602.848
5194	Paulínia	353650	26	82146	591.72	paulinense	138.826
5195	Paulistânia	353657	26	1779	6.93	paulistaniense	256.654
5197	Pederneiras	353670	26	41497	56.92	pederneirense	729.001
5199	Pedranópolis	353690	26	2558	9.83	pedranopolense	260.185
5201	Pedreira	353710	26	41558	380.45	pedreirense	109.233
5202	Pedrinhas Paulista	353715	26	2940	19.28	pedrinhense	152.515
5204	Penápolis	353730	26	58510	82.31	penapolitano	710.826
5206	Pereiras	353750	26	7454	33.38	pereirense	223.275
5207	Peruíbe	353760	26	59773	191.95	peruibense	311.394
5209	Piedade	353780	26	52143	69.82	piedadense	746.868
5211	Pindamonhangaba	353800	26	146995	201.39	pindamonhangabense	729.886
5213	Pinhalzinho	353820	26	13105	84.8	pinhalense	154.531
5215	Piquete	353850	26	14107	80.16	piquetense	175.996
5216	Piracaia	353860	26	25116	65.23	piracaiense	385.028
5218	Piraju	353880	26	28475	56.44	pirajuense	504.505
5220	Pirangi	353900	26	10623	49.3	piranginense	215.459
5222	Pirapozinho	353920	26	24694	51.49	pirapozense	479.559
5223	Pirassununga	353930	26	70081	96.38	pirassununguense	727.118
5225	Pitangueiras	353950	26	35307	81.99	pitangueirense	430.638
5227	Platina	353970	26	3192	9.77	platinense	326.734
5229	Poloni	353990	26	5395	40.4	poloniense	133.54
5230	Pompéia	354000	26	19964	25.46	pompeiano	784.058
5232	Pontal	354020	26	40244	112.94	pontalense	356.32
5234	Pontes Gestal	354030	26	2518	11.58	pontes-gestalense	217.378
5236	Porangaba	354050	26	8326	31.34	porangabense	265.706
5238	Porto Ferreira	354070	26	51400	209.88	ferreirense	244.906
5240	Potirendaba	354080	26	15449	45.12	potirendabano	342.376
5241	Pracinha	354085	26	2858	45.48	pracinhense	62.841
5243	Praia Grande	354100	26	262051	1.776	praia-grandense	147.544
5245	Presidente Alves	354110	26	4123	14.36	alvense	287.185
5247	Presidente Epitácio	354130	26	41318	32.82	epitaciano	1259.089
5248	Presidente Prudente	354140	26	207610	368.89	prudentino	562.795
5250	Promissão	354160	26	35674	45.65	promissense	781.489
5251	Quadra	354165	26	3236	15.73	quadrense	205.663
5253	Queiroz	354180	26	2808	12.01	queirozense	233.79
5255	Quintana	354200	26	6004	18.79	quintanense	319.566
5257	Rancharia	354220	26	28804	18.14	ranchariense	1587.473
5259	Regente Feijó	354240	26	18494	69.76	regentense	265.105
5260	Reginópolis	354250	26	7323	17.83	reginopolitano	410.816
5262	Restinga	354270	26	6587	26.8	restinguense	245.746
5264	Ribeirão Bonito	354290	26	12135	25.73	ribeirão-bonitense	471.553
5266	Ribeirão Corrente	354310	26	4273	28.81	ribeirão-correntense	148.332
5268	Ribeirão Dos Índios	354323	26	2187	11.14	ribeirindio	196.341
5269	Ribeirão Grande	354325	26	7422	22.26	ribeirão grandense	333.364
5271	Ribeirão Preto	354340	26	604682	928.46	ribeirão-pretano	651.276
5273	Rincão	354370	26	10414	32.97	rinconense	315.851
5275	Rio Claro	354390	26	186253	373.47	rio-clarense	498.707
5276	Rio Das Pedras	354400	26	29501	130.16	rio-pedrense	226.657
5278	Riolândia	354420	26	10575	16.7	riolandense	633.375
5280	Rosana	354425	26	19691	26.51	rosanense	742.872
5282	Rubiácea	354440	26	2729	11.52	rubiacense	236.927
5283	Rubinéia	354450	26	2862	12.08	rubineiense	236.873
5285	Sagres	354470	26	2395	16.2	sagrense	147.802
5287	Sales Oliveira	354490	26	10568	34.58	salense	305.644
5289	Salmourão	354510	26	4818	27.96	salmourense	172.292
5290	Saltinho	354515	26	7059	70.77	saltinhense	99.739
5292	Salto De Pirapora	354530	26	40132	143.07	saltense	280.502
5294	Sandovalina	354550	26	3699	8.13	sandovalinense	455.116
5296	Santa Albertina	354570	26	5723	20.98	santa-albertinense	272.774
5297	Santa Bárbara D`Oeste	354580	26	180009	663.08	barbarense	271.476
5413	Valinhos	355620	26	106793	721.02	valinhense	148.113
5415	Vargem	355635	26	8801	61.64	vargense	142.786
5417	Vargem Grande Paulista	355645	26	42997	1.021	vargem-grandense	42.08
5418	Várzea Paulista	355650	26	107089	3.076	varzino	34.807
5420	Vinhedo	355670	26	63611	779.51	vinhedense	81.604
5421	Viradouro	355680	26	17297	79.44	viradourense	217.727
5301	Santa Cruz Da Esperança	354625	26	1953	13.19	santacruzense	148.062
5303	Santa Cruz Do Rio Pardo	354640	26	43921	39.44	santa-cruzense	1113.504
5304	Santa Ernestina	354650	26	5568	41.42	santa-ernestinense	134.421
5306	Santa Gertrudes	354670	26	21634	220.74	santa-gertrudense	98.007
5308	Santa Lúcia	354690	26	8248	53.55	santa-luciense	154.033
5309	Santa Maria Da Serra	354700	26	5413	21.05	serrense	257.188
5311	Santa Rita D`Oeste	354740	26	2543	12.1	santa-ritense	210.081
5313	Santa Rosa De Viterbo	354760	26	23862	82.69	santa-rosense	288.577
5314	Santa Salete	354765	26	1447	18.23	saletense	79.389
5316	Santana De Parnaíba	354730	26	108813	605.17	parnaibano	179.807
5317	Santo Anastácio	354770	26	20475	37.06	anastaciano	552.537
5319	Santo Antônio Da Alegria	354790	26	6304	20.31	alegriense	310.339
5321	Santo Antônio Do Aracanguá	354805	26	7626	5.83	aracanguaense	1308.236
5323	Santo Antônio Do Pinhal	354820	26	6486	48.76	pinhalense	133.008
5324	Santo Expedito	354830	26	2803	29.68	expeditense	94.444
5326	Santos	354850	26	419400	1.492	santista	281.056
5327	São Bento Do Sapucaí	354860	26	10468	41.44	são-bentista	252.58
5329	São Caetano Do Sul	354880	26	149263	9.708	sul-caetanense	15.374
5330	São Carlos	354890	26	221950	195.15	são-carlense	1137.303
5332	São João Da Boa Vista	354910	26	83639	161.96	são-joanense	516.418
5334	São João De Iracema	354925	26	1780	9.97	iracemense	178.61
5336	São Joaquim Da Barra	354940	26	46512	113.28	joaquinense	410.597
5337	São José Da Bela Vista	354950	26	8406	30.35	bela-vistense	276.952
5339	São José Do Rio Pardo	354970	26	51900	123.81	rio-pardense	419.186
5340	São José Do Rio Preto	354980	26	408258	946.53	rio-pretense	431.321
5342	São Lourenço Da Serra	354995	26	13973	74.96	são-lourensano	186.401
5344	São Manuel	355010	26	38342	58.92	são-manuelense	650.768
5345	São Miguel Arcanjo	355020	26	31450	33.8	são-miguelense	930.34
5347	São Pedro	355040	26	31662	51.82	são-pedrense	610.997
5349	São Roque	355060	26	78821	257.3	são-roquense	306.34
5350	São Sebastião	355070	26	73942	184.68	sebastianense	400.387
5352	São Simão	355090	26	14346	23.24	simonense	617.202
5353	São Vicente	355100	26	332445	2.232	vicentino	148.926
5355	Sarutaiá	355120	26	3622	25.58	sarutaiano	141.608
5357	Serra Azul	355140	26	11256	39.77	serra-azulense	283.031
5358	Serra Negra	355160	26	26387	129.52	serrano	203.736
5360	Sertãozinho	355170	26	110074	273.43	sertanezino	402.57
5362	Severínia	355190	26	15501	110.38	severinense	140.432
5364	Socorro	355210	26	36686	81.7	socorrense	449.029
5365	Sorocaba	355220	26	586625	1.306	sorocabano	448.989
5367	Sumaré	355240	26	241311	1.577	sumareense	153.005
5369	Suzano	355250	26	262480	1.27	suzanense	206.617
5370	Tabapuã	355260	26	11363	32.88	tabapuãnense	345.565
5372	Taboão Da Serra	355280	26	244528	12.049	taboense	20.293
5374	Taguaí	355300	26	10828	74.51	taguaíno	145.332
5376	Taiúva	355320	26	5447	41.12	taiuvense	132.459
5378	Tanabi	355340	26	24055	32.25	tanabiense	745.8
5379	Tapiraí	355350	26	8012	10.61	tapiraiense	755.101
5381	Taquaral	355365	26	2726	50.58	taquaralense	53.892
5383	Taquarituba	355380	26	22291	49.71	taquaritubense	448.429
5385	Tarabai	355390	26	6607	33.57	tarabaíno	196.791
5386	Tarumã	355395	26	12885	42.5	tarumaense	303.184
5388	Taubaté	355410	26	278686	445.98	taubateano	624.885
5390	Teodoro Sampaio	355430	26	21386	13.74	teodorense	1555.996
5392	Tietê	355450	26	36835	91	tieteense	404.792
5394	Torre De Pedra	355465	26	2254	31.59	torrepedrense	71.348
5395	Torrinha	355470	26	9330	30.03	torrinhense	310.699
5397	Tremembé	355480	26	40984	214.17	tremembeense	191.363
5399	Tuiuti	355495	26	5930	46.8	tuiutiense	126.699
5401	Tupi Paulista	355510	26	14269	58.16	tupinense-paulista	245.336
5402	Turiúba	355520	26	1930	12.6	turiubano	153.126
5404	Ubarana	355535	26	5289	25.23	ubaranense	209.631
5406	Ubirajara	355550	26	4427	15.68	ubirajarense	282.368
5408	União Paulista	355570	26	1599	20.21	união-paulistense	79.111
5410	Uru	355590	26	1251	8.51	uruense	146.966
5411	Urupês	355600	26	12714	39.27	urupeense	323.746
5423	Vitória Brasil	355695	26	1737	34.95	vitoriabrasiliense	49.696
5425	Votuporanga	355710	26	84692	199.69	votuporanguense	424.115
5427	Abreulândia	170025	27	2391	1.26	abreulandense 	1895.207
5429	Aliança Do Tocantins	170035	27	5671	3.59	aliancense	1579.748
5430	Almas	170040	27	7586	1.89	almense	4013.234
5432	Ananás	170100	27	9865	6.26	ananaense 	1576.967
5434	Aparecida Do Rio Negro	170110	27	4213	3.63	aparecidense	1160.365
5436	Araguacema	170190	27	6317	2.27	araguacemense	2778.468
5437	Araguaçu	170200	27	8786	1.7	araguaçuense	5167.943
5439	Araguanã	170215	27	5030	6.02	araguanaense	836.028
5441	Arapoema	170230	27	6742	4.34	arapoemense	1552.217
5443	Augustinópolis	170255	27	15950	40.38	augustinopolino	394.974
5445	Axixá Do Tocantins	170290	27	9275	61.75	axixaense	150.212
5446	Babaçulândia	170300	27	10424	5.83	babaçulense	1788.455
5448	Barra Do Ouro	170307	27	4123	3.73	barraourense	1106.341
5450	Bernardo Sayão	170320	27	4456	4.81	bernardense	926.885
5451	Bom Jesus Do Tocantins	170330	27	3768	2.83	bonjesuense	1332.668
5453	Brejinho De Nazaré	170370	27	5185	3.01	brejinense	1724.446
5454	Buriti Do Tocantins	170380	27	9768	38.77	buritinense	251.918
5456	Campos Lindos	170384	27	8139	2.51	campolindense	3240.166
5458	Carmolândia	170388	27	2316	6.82	carmolandense	339.404
5460	Caseara	170390	27	4601	2.72	casearense	1691.61
5461	Centenário	170410	27	2566	1.31	centenarense	1954.693
5463	Chapada De Areia	170460	27	1335	2.03	chapadareiense	659.247
5465	Colméia	171670	27	8611	8.69	colmeiense	990.718
5466	Combinado	170555	27	4669	22.27	combinadense	209.614
5468	Couto Magalhães	170600	27	5009	3.16	coutense	1585.783
5469	Cristalândia	170610	27	7234	3.91	cristalandense	1848.237
5471	Darcinópolis	170650	27	5273	3.22	darcinopolino	1639.156
5472	Dianópolis	170700	27	19112	5.94	dianopolino	3217.143
5474	Dois Irmãos Do Tocantins	170720	27	7161	1.91	doisirmanense	3757.027
5476	Esperantina	170740	27	9476	18.8	esperantinense	504.021
5477	Fátima	170755	27	3805	9.94	fatimense	382.907
5479	Filadélfia	170770	27	8505	4.28	filadelfiense	1988.074
5481	Fortaleza Do Tabocão	170825	27	2419	3.89	tabocoense	621.56
5483	Goiatins	170900	27	12064	1.88	goiatinense	6408.581
5484	Guaraí	170930	27	23200	10.23	guaraiense	2268.155
5486	Ipueiras	170980	27	1639	2.01	ipueirense	815.253
5488	Itaguatins	171070	27	6029	8.15	itaguatinense	739.846
5490	Itaporã Do Tocantins	171110	27	2445	2.51	itaporanense	972.975
5491	Jaú Do Tocantins	171150	27	3507	1.61	jauense	2173.044
5493	Lagoa Da Confusão	171190	27	10210	0.97	lagoense	10564.565
5495	Lajeado	171200	27	2773	8.6	lajeadense	322.484
5497	Lizarda	171240	27	3725	0.65	lizardense	5723.217
5498	Luzinópolis	171245	27	2622	9.38	luzinopolino	279.562
5500	Mateiros	171270	27	2223	0.23	mateirense	9583.458
5502	Miracema Do Tocantins	171320	27	20684	7.79	miracemense	2656.084
5503	Miranorte	171330	27	12623	12.24	miranortense	1031.622
5505	Monte Santo Do Tocantins	171370	27	2085	1.91	montesantense	1091.551
5507	Natividade	171420	27	9000	2.78	nativitano	3240.708
5508	Nazaré	171430	27	4386	11.08	nazareno 	395.906
5510	Nova Rosalândia	171500	27	3770	7.3	rosalandense 	516.307
5512	Novo Alegre	171515	27	2286	11.42	novoalegrense 	200.103
5514	Oliveira De Fátima	171550	27	1037	5.04	oliverense 	205.849
5515	Palmas	172100	27	228332	102.9	palmense 	2218.937
5517	Palmeiras Do Tocantins	171380	27	5740	7.67		747.895
5519	Paraíso Do Tocantins	171610	27	44417	35.03	paraisense	1268.058
5520	Paranã	171620	27	10338	0.92	paranãense	11260.187
5522	Pedro Afonso	171650	27	11539	5.74	pedro afonsino	2010.896
5524	Pequizeiro	171665	27	5054	4.18	pequizeirense	1209.796
5526	Piraquê	171720	27	2920	2.14	piraquêense	1367.608
5527	Pium	171750	27	6694	0.67	piuense	10013.865
5529	Ponte Alta Do Tocantins	171790	27	7180	1.11	pontealtense do tocantins	6491.108
5531	Porto Nacional	171820	27	49146	11.04	portuense	4449.908
5532	Praia Norte	171830	27	7659	26.5	praianortense	289.053
5534	Pugmil	171845	27	2369	5.9	pugmilense	401.833
5536	Riachinho	171855	27	4191	8.1	riachiense	517.476
5537	Rio Da Conceição	171865	27	1714	2.18	conceiçãoense	787.114
5539	Rio Sono	171875	27	6254	0.98	riosonense	6357.135
5541	Sandolândia	171884	27	3326	0.94	sandolandense	3528.616
5543	Santa Maria Do Tocantins	171888	27	2894	2.05	santamarinense	1410.453
5544	Santa Rita Do Tocantins	171889	27	2128	0.65	santa ritense	3274.941
1	Acrelândia	120001	1	12538	6.94	acrelandense	1807.891
2	Assis Brasil	120005	1	6072	1.22	assis-brasiliense	4974.193
4	Bujari	120013	1	8471	2.79	bujariense	3034.849
6	Cruzeiro Do Sul	120020	1	78507	8.94	cruzeirense	8779.19
9	Jordão	120032	1	6577	1.23	jordãoense	5357.299
11	Manoel Urbano	120034	1	7981	0.75	manoel-urbanense	10634.539
13	Plácido De Castro	120038	1	17209	8.86	placidiano	1943.249
16	Rio Branco	120040	1	336038	38.03	rio-branquense	8835.675
18	Santa Rosa Do Purus	120043	1	4691	0.76	santarosense	6145.625
20	Senador Guiomard	120045	1	20179	8.69	guiomaense	2321.452
23	Água Branca	270010	2	19377	42.62	água-branquense	454.622
25	Arapiraca	270030	2	214006	600.84	arapiraquense	356.179
27	Barra De Santo Antônio	270050	2	14230	102.79	barrense	138.433
30	Belém	270080	2	4551	93.58	belenense 	48.63
32	Boca Da Mata	270100	2	25776	138.19	matense	186.529
35	Cajueiro	270130	2	20409	164.24	cajueirense	124.263
37	Campo Alegre	270140	2	50816	172.2	campo-alegrense	295.1
39	Canapi	270160	2	17250	30.02	canapiense	574.563
42	Chã Preta	270190	2	7146	41.34	chã-pretense	172.849
44	Colônia Leopoldina	270210	2	20019	96.29	leopoldinense	207.893
46	Coruripe	270230	2	52130	56.77	coruripense	918.208
48	Delmiro Gouveia	270240	2	48096	79.13	delmirense	607.81
51	Feira Grande	270260	2	21321	123.42	feira-grandense	172.746
53	Flexeiras	270280	2	12325	36.99	flexeirense	333.221
56	Igaci	270310	2	25188	75.31	igaciense	334.452
58	Inhapi	270330	2	17898	47.49	inhapiense	376.853
60	Jacuípe	270350	2	6997	33.26	jacuipense	210.383
62	Jaramataia	270370	2	5558	53.59	jaramataiense	103.71
65	Jundiá	270390	2	4202	45.56	jundiaense	92.223
67	Lagoa Da Canoa	270410	2	18250	206.33	canoense	88.45
69	Maceió	270430	2	932748	1.854	maceioense	503.069
72	Maragogi	270450	2	28749	86.06	maragogiense	334.042
74	Marechal Deodoro	270470	2	45977	138.62	deodorense	331.68
76	Mata Grande	270500	2	24698	27.2	mata-grandense	907.977
79	Minador Do Negrão	270530	2	5275	31.47	negrense	167.605
81	Murici	270550	2	26710	62.58	muriciense	426.816
83	Olho D`Água Das Flores	270570	2	20364	111.01	olho-daguense	183.441
86	Olivença	270600	2	11047	63.87	olivense	172.961
88	Palestina	270620	2	5112	104.55	palestinense	48.894
90	Pão De Açúcar	270640	2	23811	34.86	pão-de-açucarense	682.986
93	Passo De Camaragibe	270650	2	14763	60.39	camaragibense	244.472
95	Penedo	270670	2	60378	87.61	penedense	689.156
98	Pindoba	270700	2	2866	24.37	pindobense	117.594
100	Poço Das Trincheiras	270720	2	13872	47.52	pocense	291.935
102	Porto De Pedras	270740	2	8429	32.71	porto-pedrense	257.655
105	Rio Largo	270770	2	68481	223.56	rio-larguense	306.326
107	Santa Luzia Do Norte	270790	2	6891	232.77	nortense	29.604
110	São Brás	270820	2	6718	48	são-braense	139.944
112	São José Da Tapera	270840	2	30088	60.77	taperense	495.112
115	São Miguel Dos Milagres	270870	2	7163	93.34	milagrense	76.744
118	Senador Rui Palmeira	270895	2	13047	38.07	rui-palmeirense	342.721
120	Taquarana	270910	2	19020	114.55	taquaranense	166.045
123	União Dos Palmares	270930	2	62358	148.24	palmarino	420.658
125	Alvarães	130002	3	14088	2.38	alvarãense	5911.77
127	Anamã	130008	3	10214	4.16	anamãense	2453.935
128	Anori	130010	3	16317	2.82	anoriense	5795.308
130	Atalaia Do Norte	130020	3	15153	0.2	atalaiense	76351.882
133	Barreirinha	130050	3	27355	4.76	barreirinhense	5750.554
135	Beruri	130063	3	15486	0.9	beruriense	17250.713
137	Boca Do Acre	130070	3	30632	1.4	bocacrense	21952.76
139	Caapiranga	130083	3	10975	1.16	caapiranguense	9456.611
142	Careiro	130110	3	32734	5.37	careirense	6091.547
144	Coari	130120	3	75965	1.31	coariense	57921.914
146	Eirunepé	130140	3	30665	2.04	eirunepeense	15011.814
5547	Santa Terezinha Do Tocantins	172000	27	2474	9.17	terezinense do tocantins	269.676
5549	São Félix Do Tocantins	172015	27	1437	0.75	são felense	1908.673
5550	São Miguel Do Tocantins	172020	27	10481	26.28	são miguelense	398.819
5552	São Sebastião Do Tocantins	172030	27	4283	14.91	sansebastianense	287.274
5554	Silvanópolis	172065	27	5068	4.03	silvanopolino	1258.828
5555	Sítio Novo Do Tocantins	172080	27	9148	28.23	sítionovense	324.105
5557	Taguatinga	172090	27	15051	6.18	taguatinense	2437.393
5559	Talismã	172097	27	2562	1.19	talismãense	2156.897
5560	Tocantínia	172110	27	6736	2.59	tocantiniense	2601.596
5562	Tupirama	172125	27	1574	2.21	tupiramense	712.204
5564	Wanderlândia	172208	27	10981	8	wanderlandiense	1373.057
148	Fonte Boa	130160	3	22817	1.88	fonte-boense	12110.933
151	Ipixuna	130180	3	22254	1.85	ipixunense	12044.755
153	Itacoatiara	130190	3	86839	9.77	itacoatiarense	8892.021
155	Itapiranga	130200	3	8211	1.94	itapiranguense	4231.145
157	Juruá	130220	3	10802	0.56	juruaense	19400.708
160	Manacapuru	130250	3	85141	11.62	manacapuruense	7330.066
162	Manaus	130260	3	1802014	158.06	manauara	11401.077
164	Maraã	130280	3	17528	1.04	maraãense	16910.372
167	Nova Olinda Do Norte	130310	3	30696	5.47	olindense	5608.557
169	Novo Aripuanã	130330	3	21451	0.52	aripuanense	41188.524
172	Presidente Figueiredo	130353	3	27175	1.07	figueirense	25422.259
174	Santa Isabel Do Rio Negro	130360	3	18146	0.29	santa-isabelense	62846.382
176	São Gabriel Da Cachoeira	130380	3	37896	0.35	são-gabrielense	109183.45
180	Tabatinga	130406	3	52272	16.21	tabatinguense	3224.88
182	Tefé	130420	3	61453	2.59	tefeense	23704.488
184	Uarini	130426	3	11891	1.16	uarinense	10246.239
186	Urucurituba	130440	3	17837	6.14	urucuritubense	2906.698
189	Cutias	160021	4	4696	2.22	cutienses	2114.237
191	Itaubal	160025	4	4265	2.5	itaubenses	1703.962
193	Macapá	160030	4	398204	62.14	macapaense	6408.517
195	Oiapoque	160050	4	20509	0.91	oiapoquenses	22625.075
197	Porto Grande	160053	4	16809	3.82	portograndenses	4401.774
200	Serra Do Navio	160005	4	4380	0.56	serranavienses	7756.102
202	Vitória Do Jari	160080	4	12428	5.01	vitorenses	2482.879
205	Acajutiba	290030	5	14653	75.75	acajutibense	193.444
207	Água Fria	290040	5	15731	23.77	água-friense	661.856
209	Alagoinhas	290070	5	141949	188.66	alagoinhense	752.389
212	Amargosa	290100	5	34351	74.16	amargosense	463.181
214	América Dourada	290115	5	15961	19.02	américo-douradense	839.261
216	Andaraí	290130	5	13960	7.5	andaraiense	1861.657
219	Anguera	290150	5	10242	57.85	anguerense	177.042
221	Antônio Cardoso	290170	5	11554	39.24	cardosense	294.45
223	Aporá	290190	5	17731	31.56	aporense	561.822
226	Aracatu	290200	5	13743	9.22	aracatuense	1489.767
228	Aramari	290220	5	10036	30.45	aramariense	329.641
230	Aratuípe	290230	5	8599	47.47	aratuipense	181.139
232	Baianópolis	290250	5	13850	4.14	baianopolense	3342.642
235	Barra	290270	5	49325	4.32	barrense	11412.794
237	Barra Do Choça	290290	5	34788	53.8	barra-chocense	646.631
240	Barreiras	290320	5	137427	17.49	barreirense	7859.128
242	Barrocas	290327	5	14191	70.62	barroquense	200.96
244	Belmonte	290340	5	21798	11.11	belmontense	1961.193
247	Boa Nova	290370	5	15411	17.74	boa-novense	868.78
249	Bom Jesus Da Lapa	290390	5	63480	15.11	lapense	4200.3
251	Boninal	290400	5	13695	14.66	boninalense	934.018
254	Botuporã	290420	5	11154	17.28	botuporãense	645.512
256	Brejolândia	290440	5	11077	4.04	brejolandense	2744.487
257	Brotas De Macaúbas	290450	5	10717	4.78	brotense	2240.002
259	Buerarema	290470	5	18605	80.73	bueraremense	230.459
261	Caatiba	290480	5	11420	23.24	caatibense	491.347
263	Cachoeira	290490	5	32026	81.04	cachoeirano	395.211
266	Caetanos	290515	5	13639	17.61	caetanense	774.692
268	Cafarnaum	290530	5	17209	25.48	cafarnauense	675.439
270	Caldeirão Grande	290550	5	12491	27.44	caldeirão-grandense	455.172
273	Camamu	290580	5	35180	38.22	camamuense	920.366
274	Campo Alegre De Lourdes	290590	5	28090	10.1	campo-alegrense	2781.357
277	Canarana	290620	5	24067	41.76	canaraense	576.384
280	Candeias	290650	5	83158	321.87	candeense	258.357
282	Cândido Sales	290670	5	27918	17.26	cândido-salense	1617.522
284	Canudos	290682	5	15732	4.89	canudense	3219.297
286	Capim Grosso	290687	5	26577	79.06	capim-grossense	336.183
289	Cardeal Da Silva	290700	5	8899	40.3	cardinalense	220.842
291	Casa Nova	290720	5	64940	6.73	casa-novense	9646.956
294	Catu	290750	5	51077	122.72	catuense	416.211
296	Central	290760	5	17013	28.24	centralense	602.41
298	Cícero Dantas	290780	5	32300	47.99	cícero-dantense	673.048
301	Cocos	290810	5	18153	1.79	coquense	10148.089
303	Conceição Do Almeida	290830	5	17889	61.7	almeidense	289.936
305	Conceição Do Jacuípe	290850	5	30123	256.3	conjacuipense	117.529
308	Contendas Do Sincorá	290880	5	4663	4.46	contendense	1044.683
311	Coribe	290910	5	14307	5.67	coribense	2523.154
313	Correntina	290930	5	31249	2.62	correntinense	11941
315	Cravolândia	290950	5	5041	31.09	cravolandense	162.168
318	Cruz Das Almas	290980	5	58606	402.11	cruz-almense	145.747
320	Dário Meira	291000	5	12836	28.82	dário-meirense	445.421
323	Dom Macedo Costa	291020	5	3874	45.71	macedense	84.759
325	Encruzilhada	291040	5	23766	11.99	encruzilhadense	1982.467
327	Érico Cardoso	290050	5	10859	15.48	érico-cardosense	701.413
330	Eunápolis	291072	5	100196	84.98	eunapolitano	1179.115
332	Feira Da Mata	291077	5	6184	3.71	matense	1668.528
335	Firmino Alves	291090	5	5384	33.15	firmino-alvense	162.425
337	Formosa Do Rio Preto	291110	5	22528	1.37	formosense	16404.396
340	Gentio Do Ouro	291130	5	10622	2.87	gentiense	3700.124
342	Gongogi	291150	5	8357	42.28	gongogiense	197.65
345	Guanambi	291170	5	78833	60.8	guanambiense	1296.656
347	Heliópolis	291185	5	13192	42.22	heliopoliense	312.455
349	Ibiassucê	291200	5	10062	23.58	ibiassuceense	426.671
351	Ibicoara	291220	5	17282	20.33	ibicoarense	849.872
354	Ibipitanga	291250	5	14171	14.85	ibipitanguense	954.369
356	Ibirapitanga	291270	5	22598	50.53	ibirapitanguense	447.259
358	Ibirataia	291290	5	18943	64.24	ibirataense	294.864
361	Ibotirama	291320	5	25424	14.76	ibotiramense	1722.335
363	Igaporã	291340	5	15205	18.26	igaporaense	832.525
365	Iguaí	291350	5	25705	31.05	iguaiense	827.824
367	Inhambupe	291370	5	36306	29.7	inhambupense	1222.58
370	Ipirá	291400	5	59343	19.47	ipiraense	3048.505
372	Irajuba	291420	5	7002	16.93	irajubense	413.524
374	Iraquara	291440	5	22601	21.96	iraquarense	1029.375
377	Itabela	291465	5	28390	33.37	itabelense	850.663
379	Itabuna	291480	5	204667	473.5	itabunense	432.243
381	Itaeté	291500	5	14924	12.35	itaeteense	1208.673
383	Itagibá	291520	5	15193	19.26	itagibaense	788.835
385	Itaguaçu Da Bahia	291535	5	13209	2.97	itaguaçuense	4451.214
386	Itaju Do Colônia	291540	5	7309	5.98	itajuense	1222.708
388	Itamaraju	291560	5	63069	27.73	itamarajuense	2274.269
390	Itambé	291580	5	23089	16.02	itambeense	1441.58
393	Itaparica	291610	5	20725	175.58	itaparicano	118.04
395	Itapebi	291630	5	10495	10.44	itapebiense	1005.369
397	Itapicuru	291650	5	32261	20.35	itapicuruense	1585.567
399	Itaquara	291670	5	7678	23.77	itaquarense	322.975
402	Itiruçu	291690	5	12693	40.46	itiruçuense	313.701
404	Itororó	291710	5	19914	63.5	itororoense	313.585
406	Ituberá	291730	5	26591	63.73	ituberense	417.255
408	Jaborandi	291735	5	8973	0.94	jaborandiense	9525.655
411	Jaguaquara	291760	5	51011	54.95	jaguaquarense	928.235
413	Jaguaripe	291780	5	16467	18.32	jaguaripense	898.656
415	Jequié	291800	5	151895	47.07	jequieense	3227.338
418	Jitaúna	291830	5	14115	64.48	jitaunense	218.906
419	João Dourado	291835	5	22549	24.65	joão-douradense	914.874
422	Jussara	291850	5	15052	15.87	jussaraense	948.64
425	Lafaiete Coutinho	291870	5	3901	9.63	lafaietense	405.253
427	Laje	291880	5	22201	48.5	lajista	457.744
429	Lajedinho	291900	5	3936	5.07	lajedinhense	776.087
432	Lapão	291915	5	25646	42.57	lapoense	602.44
434	Lençóis	291930	5	10368	8.12	lençoense	1277.029
436	Livramento De Nossa Senhora	291950	5	42693	19.99	livramentense	2135.585
439	Macarani	291970	5	17093	13.28	macaraniense	1287.525
441	Macururé	291990	5	8073	3.52	macururense	2294.272
443	Maetinga	291995	5	7038	10.32	maetinguense	681.668
445	Mairi	292010	5	19326	20.29	mairiense	952.65
447	Malhada De Pedras	292030	5	8468	16.01	malhada-pedrense	529.047
450	Maracás	292050	5	24613	10.92	maracaense	2253.171
452	Maraú	292070	5	19101	23.2	marauense	823.398
454	Mascote	292090	5	14640	18.95	mascotense	772.461
457	Medeiros Neto	292110	5	21560	17.4	medeirense	1238.739
459	Milagres	292130	5	10306	36.24	milagrense	284.375
462	Monte Santo	292150	5	52338	16.42	monte-santense	3186.872
464	Morro Do Chapéu	292170	5	35164	6.12	morrense	5742.91
466	Mucugê	292190	5	10545	4.3	mucugeense	2455.018
468	Mulungu Do Morro	292205	5	12249	21.64	mulunguense	566.008
471	Muquém De São Francisco	292225	5	10272	2.82	sanfranciscano	3638.055
474	Nazaré	292250	5	27274	107.47	nazareno	253.779
476	Nordestina	292265	5	12371	26.82	nordestinense	461.223
478	Nova Fátima	292273	5	7602	21.73	fatimense	349.899
480	Nova Itarana	292280	5	7435	15.8	nova-itaranense	470.428
483	Nova Viçosa	292300	5	38556	29.15	nova-viçosense	1322.897
485	Novo Triunfo	292305	5	15051	59.86	novo-triunfense	251.422
487	Oliveira Dos Brejinhos	292320	5	21831	6.21	brejinhense	3512.658
490	Palmas De Monte Alto	292340	5	20775	8.23	monte-altense	2524.942
493	Paratinga	292370	5	29504	11.28	paratinguense	2614.768
495	Pau Brasil	292390	5	10852	17.89	pau-brasilense	606.546
497	Pé De Serra	292405	5	13752	22.32	pé-de-serrense	616.209
499	Pedro Alexandre	292420	5	16995	18.96	pedro-alexandrense	896.162
502	Pindaí	292450	5	15628	25.45	pindaiense	614.062
504	Pintadas	292465	5	10342	18.96	pintadense	545.534
507	Piritiba	292480	5	22399	22.96	piritibano	975.576
509	Planalto	292500	5	24481	25.46	planaltense	961.689
511	Pojuca	292520	5	33066	113.98	pojucano	290.113
513	Porto Seguro	292530	5	126929	52.7	porto-segurense	2408.492
516	Presidente Dutra	292560	5	13750	84.07	utrense	163.55
517	Presidente Jânio Quadros	292570	5	13652	11.52	janio-quadrense	1185.134
520	Quijingue	292590	5	27228	20.27	quijinguense	1342.948
522	Rafael Jambeiro	292595	5	22874	18.77	jambeirense	1218.877
524	Retirolândia	292610	5	12055	66.43	retirolandense	181.471
526	Riachão Do Jacuípe	292630	5	33172	27.87	jacuipense	1190.203
529	Ribeira Do Pombal	292660	5	47518	60.25	pombalense	788.626
532	Rio Do Antônio	292680	5	14815	18.19	rio-antoniense	814.348
534	Rio Real	292700	5	37164	51.84	rio-realense	716.881
537	Salinas Da Margarida	292730	5	13456	89.81	salinense	149.821
539	Santa Bárbara	292750	5	19064	55.15	barbarense	345.645
541	Santa Cruz Cabrália	292770	5	26264	16.81	santa-cruzense	1562.701
544	Santa Luzia	292805	5	13344	17.22	santa-luziense	774.877
546	Santa Rita De Cássia	292840	5	26250	4.39	santa-ritense 	5977.746
549	Santana	292820	5	24750	13.6	santanense	1820.1
551	Santo Amaro	292860	5	57800	117.26	santo-amarense	492.912
553	Santo Estêvão	292880	5	47880	131.92	santo-estevense	362.96
555	São Domingos	292895	5	9226	28.22	são-dominguense	326.94
558	São Félix Do Coribe	292905	5	13048	13.74	são-felense	949.381
560	São Gabriel	292925	5	18427	15.36	são-gabrielense	1199.498
562	São José Da Vitória	292935	5	5715	78.83	são-joseense	72.494
565	São Sebastião Do Passé	292950	5	42153	78.3	sebastianense	538.32
568	Saubara	292975	5	11201	68.51	saubarense	163.495
570	Seabra	292990	5	41798	16.6	seabrense	2517.271
572	Senhor Do Bonfim	293010	5	74419	89.93	bonfinense	827.477
575	Serra Dourada	293030	5	18112	13.45	serra-douradense	1346.608
578	Serrolândia	293060	5	12344	41.73	serrolandense	295.823
580	Sítio Do Mato	293075	5	12050	6.88	sítio-matense	1751.025
582	Sobradinho	293077	5	22000	17.76	sobradinhense	1238.905
584	Tabocas Do Brejo Velho	293090	5	11431	8.31	taboquense	1375.78
587	Tanquinho	293110	5	8008	36.42	tanquinhense	219.87
589	Tapiramutá	293130	5	16516	24.88	tapiramutaense	663.879
591	Teodoro Sampaio	293140	5	7895	34.1	teodorense	231.539
594	Terra Nova	293170	5	12803	64.36	terra-novense	198.925
596	Tucano	293190	5	52418	18.73	tucanense	2799.12
599	Ubaitaba	293220	5	20691	115.71	ubaitabense	178.815
601	Uibaí	293240	5	13625	24.73	uibaiense	550.998
603	Una	293250	5	24110	20.48	unense	1177.473
605	Uruçuca	293270	5	19837	50.61	uruçuquense	391.97
607	Valença	293290	5	88673	74.35	valenciano	1192.61
610	Várzea Do Poço	293310	5	8661	42.27	varzeapense	204.913
612	Varzedo	293317	5	9109	40.16	varzedense	226.794
614	Vereda	293325	5	6800	7.78	veredense	874.24
617	Wanderley	293345	5	12485	4.22	wanderleiense	2959.513
618	Wenceslau Guimarães	293350	5	22189	32.92	wenceslau-guimarãense	674.028
622	Acaraú	230020	6	57551	68.31	acarauense	842.555
624	Aiuaba	230040	6	16203	6.66	aiuabense	2434.41
626	Altaneira	230060	6	6856	93.54	altaneirense	73.296
628	Amontada	230075	6	39232	33.27	amontadense	1179.031
631	Aquiraz	230100	6	72628	150.5	aquirazense	482.566
633	Aracoiaba	230120	6	25391	38.67	aracoiabense	656.593
635	Araripe	230130	6	20685	18.81	araripense	1099.926
638	Assaré	230160	6	22445	20.11	assareense	1116.325
640	Baixio	230180	6	6026	41.15	baixiense	146.433
642	Barbalha	230190	6	55323	92.31	barbalhense	599.307
644	Barro	230200	6	21514	30.22	barrense	711.883
646	Baturité	230210	6	33321	107.98	baturiteense	308.579
648	Bela Cruz	230230	6	30878	37.45	bela-cruzense	824.409
651	Camocim	230260	6	60158	52.81	camocimense	1139.206
653	Canindé	230280	6	74473	23.14	canindeense	3218.462
655	Caridade	230300	6	20020	23.65	caridadense	846.5
657	Caririaçu	230320	6	26393	41.41	caririaçuense	637.353
660	Cascavel	230350	6	66142	78.99	cascavelense	837.321
662	Catunda	230365	6	9952	12.71	catundense	783.192
664	Cedro	230380	6	24527	33.79	cedrense	725.794
667	Chorozinho	230395	6	18915	67.94	chorozinhense	278.411
669	Crateús	230410	6	72812	24.37	crateuense	2988.29
671	Croatá	230423	6	17069	24.49	croataense	696.978
673	Deputado Irapuan Pinheiro	230426	6	9095	19.33	irapuense	470.422
676	Farias Brito	230430	6	19007	37.74	farias-britense	503.619
678	Fortaleza	230440	6	2452185	7.786	fortalezense	314.927
681	General Sampaio	230460	6	6218	33.23	sampaiense	187.131
683	Granja	230470	6	52645	19.51	granjense	2698.104
685	Groaíras	230490	6	10228	65.59	groairense	155.946
688	Guaramiranga	230510	6	4164	41.29	guaramiranguense	100.856
690	Horizonte	230523	6	55187	344.96	horizontino	159.979
692	Ibiapina	230530	6	23808	57.38	ibiapinense	414.936
695	Icó	230540	6	65456	34.97	icoense	1872.003
697	Independência	230560	6	25573	7.95	independenciense	3218.661
699	Ipaumirim	230570	6	12009	43.86	ipaumirinense	273.825
702	Iracema	230600	6	13722	16.65	iracemense	824.034
704	Itaiçaba	230620	6	7316	34.86	itaiçabense	209.851
706	Itapagé	230630	6	48350	112.33	itapageense	430.414
709	Itarema	230655	6	37471	52	itaremense	720.66
711	Jaguaretama	230670	6	17863	10.15	jaguaretamense	1759.724
713	Jaguaribe	230690	6	34409	18.33	jaguaribano	1876.796
716	Jati	230720	6	7660	21.21	jatiense	361.069
717	Jijoca De Jericoacoara	230725	6	17002	83.02	jijoquense	204.792
720	Lavras Da Mangabeira	230750	6	31090	32.8	lavrense	947.963
723	Maracanaú	230765	6	209057	1.877	maracanauense	111.334
725	Marco	230780	6	24703	43.03	marquense	574.134
727	Massapê	230800	6	35191	62.11	massapeense	566.578
730	Milagres	230830	6	28316	49.08	milagrense	576.96
732	Miraíma	230837	6	12800	18.29	miraimense	699.96
734	Mombaça	230850	6	42690	20.14	mombaçano	2119.469
737	Moraújo	230880	6	8070	19.42	moraujense	415.631
739	Mucambo	230900	6	14102	73.99	mucambense	190.601
741	Nova Olinda	230920	6	14256	50.13	novo-olindense	284.399
743	Novo Oriente	230940	6	27453	29.01	novo-oriental	946.225
746	Pacajus	230960	6	61838	243	pacajuense	254.477
748	Pacoti	230980	6	11607	105.92	pacotiense	109.586
751	Palmácia	231010	6	12005	101.9	palmaciano	117.813
753	Paraipaba	231025	6	30041	99.83	paraipabense	300.919
755	Paramoti	231040	6	11308	23.43	paramotiense	482.589
757	Penaforte	231060	6	8226	57.96	penafortense	141.926
759	Pereiro	231080	6	15757	37.24	pereirense	423.117
761	Piquet Carneiro	231090	6	15467	26.31	piquet-carneirense	587.873
764	Porteiras	231110	6	15061	69.22	porteirense	217.577
766	Potiretama	231123	6	6126	15.14	potiretamense	404.602
768	Quixadá	231130	6	80604	39.91	quixadaense	2019.822
770	Quixeramobim	231140	6	71887	21.59	quixeramobinense	3330.068
773	Reriutaba	231170	6	19455	50.75	reriutabano	383.316
774	Russas	231180	6	69833	43.88	russano	1591.281
775	Saboeiro	231190	6	15752	11.39	saboeirense	1383.477
777	Santa Quitéria	231220	6	42763	10.04	quiteriense	4260.455
779	Santana Do Cariri	231210	6	17170	20.07	santanense-do-cariri	855.558
782	São João Do Jaguaribe	231250	6	7900	28.17	jaguaribense	280.454
785	Senador Sá	231280	6	6852	16.16	saense	423.917
787	Solonópole	231300	6	17665	11.5	solonopolitano	1536.156
789	Tamboril	231320	6	25451	12.72	tamborilense	2000.767
792	Tejuçuoca	231335	6	16827	21.53	tejuçuoquense	781.741
794	Trairi	231350	6	51422	55.55	trairiense	925.717
796	Ubajara	231360	6	31787	75.5	ubajarense	421.031
799	Uruburetama	231380	6	19765	183.75	uruburetamense	107.566
801	Varjota	231395	6	17593	98.07	varjotense	179.396
803	Viçosa Do Ceará	231410	6	54955	41.9	viçosense	1311.62
805	Afonso Cláudio	320010	8	31091	32.57	afonso-claudense	954.658
808	Alegre	320020	8	30768	39.82	alegrense	772.717
810	Alto Rio Novo	320035	8	7317	32.13	alto-rio-novense	227.728
813	Aracruz	320060	8	81832	56.99	aracruzense	1435.97
814	Atilio Vivacqua	320070	8	9850	43.43	atilio-vivacquense	226.814
817	Boa Esperança	320100	8	14199	33.13	esperancense	428.557
820	Cachoeiro De Itapemirim	320120	8	189889	216.57	cachoeirense	876.795
822	Castelo	320140	8	34747	52.31	castelense	664.226
824	Conceição Da Barra	320160	8	28449	23.95	barrense	1187.766
827	Domingos Martins	320190	8	31847	25.99	martinense	1225.331
830	Fundão	320220	8	17025	60.9	fundãoense	279.54
831	Governador Lindenberg	320225	8	10869	30.22	lindenberguense	359.616
834	Ibatiba	320245	8	22366	92.77	ibatibense	241.084
837	Iconha	320260	8	12523	61.71	iconhense	202.92
839	Itaguaçu	320270	8	14134	26.65	itaguaçuense	530.389
841	Itarana	320290	8	10881	36.38	itaranense	299.078
844	Jerônimo Monteiro	320310	8	10879	67.09	monteirense	162.164
846	Laranja Da Terra	320316	8	10826	23.69	laranjense	456.987
848	Mantenópolis	320330	8	13612	42.44	mantenopolisense	320.744
851	Marilândia	320335	8	11107	35.89	marilandense	309.447
853	Montanha	320350	8	17849	16.26	montanhense	1097.93
855	Muniz Freire	320370	8	18397	27.06	muniz-freirense	679.926
858	Pancas	320400	8	21548	26.16	panquense	823.835
860	Pinheiros	320410	8	23895	24.5	pinheirense	975.36
863	Presidente Kennedy	320430	8	10314	17.59	kennediense	586.517
865	Rio Novo Do Sul	320440	8	11325	55.59	novense-do-sul	203.721
867	Santa Maria De Jetibá	320455	8	34176	46.46	santa-mariense	735.555
870	São Gabriel Da Palha	320470	8	31859	73.61	gabrielense	432.815
873	São Roque Do Canaã	320495	8	11273	32.92	são-roquense	342.395
876	Vargem Alta	320503	8	19130	46.13	vargem-altense	414.739
878	Viana	320510	8	65001	208.6	vianense	311.606
880	Vila Valério	320517	8	13830	29.78	vila-valense	464.387
883	Abadia De Goiás	520005	9	6876	46.85	abadiense	146.778
885	Acreúna	520013	9	20279	12.95	acreunense	1565.997
887	Água Fria De Goiás	520017	9	5090	2.51	água-friense	2029.413
890	Alexânia	520030	9	23814	28.09	alexaniense	847.893
892	Alto Horizonte	520055	9	4505	8.94	alto horizontino	503.763
895	Amaralina	520082	9	3434	2.56	amaralinense	1343.172
897	Amorinópolis	520090	9	3609	8.83	amorinopolense	408.525
899	Anhanguera	520120	9	1020	17.91	anhanguerino	56.95
900	Anicuns	520130	9	20239	20.67	anicuense	979.23
901	Aparecida De Goiânia	520140	9	455657	1.58	aparecidense	288.342
904	Araçu	520160	9	3802	25.53	araçuense	148.936
906	Aragoiânia	520180	9	8365	38.1	aragoianense	219.55
908	Arenópolis	520235	9	3277	3.05	arenopolino	1074.595
911	Avelinópolis	520280	9	2450	14.11	avelinopense	173.64
913	Barro Alto	520320	9	8716	7.97	barro-altense	1093.247
915	Bom Jardim De Goiás	520340	9	8423	4.43	bom-jardinense	1899.478
918	Bonópolis	520357	9	3503	2.15	bonopolino	1628.484
920	Britânia	520380	9	5509	3.77	britaniense	1461.186
922	Buriti De Goiás	520393	9	2560	12.85	buritiense	199.292
925	Cachoeira Alta	520410	9	10553	6.38	cachoeira-altense	1654.555
927	Cachoeira Dourada	520425	9	8254	15.84	cachoeirense-do-sul	521.134
930	Caldas Novas	520450	9	70473	44.16	caldense	1595.965
932	Campestre De Goiás	520460	9	3387	12.37	campestrino	273.815
935	Campo Alegre De Goiás	520480	9	6060	2.46	campo-alegrense	2462.991
937	Campos Belos	520490	9	18410	25.43	campo-belense	724.066
939	Carmo Do Rio Verde	520500	9	8928	21.33	carmo-rio-verdino	418.543
942	Caturaí	520520	9	4686	22.61	caturaiense	207.264
945	Cezarina	520545	9	7545	18.15	cezarinense	415.811
947	Cidade Ocidental	520549	9	55915	143.4	ocidentalense	389.92
949	Colinas Do Sul	520552	9	3523	2.06	colinense	1708.185
952	Corumbaíba	520590	9	8181	4.34	corumbaibense	1883.665
954	Cristianópolis	520630	9	2932	13.01	cristianopolino	225.358
956	Cromínia	520650	9	3555	9.76	crominiense	364.105
959	Damolândia	520680	9	2747	32.51	damolandense	84.495
961	Diorama	520710	9	2479	3.61	dioramense	687.348
963	Doverlândia	520725	9	7892	2.45	doverlandense	3222.942
966	Estrela Do Norte	520750	9	3320	11.01	estrela-nortense	301.641
968	Fazenda Nova	520760	9	6322	4.93	fazenda-novense	1281.419
970	Flores De Goiás	520790	9	12066	3.25	florense	3709.421
973	Gameleira De Goiás	520815	9	3275	5.53	gameleirense	591.994
976	Goianésia	520860	9	59549	38.49	goianesiense	1547.272
978	Goianira	520880	9	34060	162.94	goianirense	209.037
980	Goiatuba	520910	9	32492	13.13	goiatubense	2475.112
983	Guaraíta	520929	9	2376	11.57	guaraitense	205.306
985	Guarinos	520945	9	2299	3.86	guarinense	595.866
987	Hidrolândia	520970	9	17398	18.43	hidrolandense	943.896
990	Inaciolândia	520993	9	5699	8.28	inaciolandense	688.404
992	Inhumas	521000	9	48246	78.68	inhumense	613.225
994	Ipiranga De Goiás	521015	9	2844	11.79	ipiranguense	241.289
997	Itaberaí	521040	9	35371	24.27	itaberino	1457.279
999	Itaguaru	521060	9	5437	22.68	itaguaruense	239.677
1001	Itapaci	521090	9	18458	19.31	itapacino	956.125
1004	Itarumã	521130	9	6300	1.83	itarumaense	3433.628
1006	Itumbiara	521150	9	92883	37.71	itumbiarense	2462.93
1008	Jandaia	521170	9	6164	7.13	jandaiense	864.106
1010	Jataí	521190	9	88006	12.27	jataiense	7174.231
1013	Joviânia	521210	9	7118	15.98	jovianiense	445.487
1015	Lagoa Santa	521225	9	1254	2.73	lagosentense	458.868
1017	Luziânia	521250	9	174531	44.06	luzianiense	3961.118
1019	Mambaí	521270	9	6871	7.8	mambaiense	880.622
1022	Matrinchã	521295	9	4414	3.84	matrinchaense	1150.893
1024	Mimoso De Goiás	521305	9	2685	1.94	mimosense	1386.914
1026	Mineiros	521310	9	52935	5.84	mineirense	9060.096
1028	Monte Alegre De Goiás	521350	9	7730	2.48	monte-alegrense	3119.802
1030	Montividiu	521375	9	10572	5.64	montividiuense	1874.153
1032	Morrinhos	521380	9	41460	14.57	morrinhense	2846.198
1035	Mozarlândia	521400	9	13404	7.73	mozarlandense	1734.363
1037	Mutunópolis	521410	9	3849	4.03	mutunopolino	955.874
1040	Niquelândia	521460	9	42361	4.3	niquelandense	9843.235
1042	Nova Aurora	521480	9	2062	6.81	nova-aurorense	302.655
1044	Nova Glória	521486	9	8508	20.6	nova-glorino	412.953
1047	Nova Veneza	521500	9	8129	65.89	nova-venezino	123.377
1049	Novo Gama	521523	9	95018	489.41	novo-gamense	194.148
1051	Orizona	521530	9	14300	7.25	orizonense	1972.883
1053	Ouvidor	521550	9	5467	13.21	ouvidorense	413.784
1055	Palestina De Goiás	521565	9	3371	2.55	palestinense	1320.687
1058	Palminópolis	521590	9	3557	9.17	palminopolino	387.693
1060	Paranaiguara	521630	9	9100	7.89	paranaiguarense	1153.834
1063	Petrolina De Goiás	521680	9	10283	19.35	petrolinense	531.3
1065	Piracanjuba	521710	9	24026	9.99	piracanjubense	2405.12
1067	Pirenópolis	521730	9	23006	10.43	pirenopolino	2205.008
1070	Pontalina	521770	9	17121	11.91	pontalinense	1436.954
1072	Porteirão	521805	9	3347	5.54	porteirense	603.941
1075	Professor Jamil	521839	9	3239	9.32	jamilense	347.465
1077	Rialma	521860	9	10523	39.2	rialmense	268.466
1079	Rio Quente	521878	9	3312	12.94	rio-quentense	255.961
1082	Sanclerlândia	521900	9	7550	15.2	sanclerlandense	496.825
1084	Santa Cruz De Goiás	521920	9	3142	2.83	santa-cruzano	1108.962
1086	Santa Helena De Goiás	521930	9	36469	31.95	santa-helenense	1141.33
1089	Santa Rita Do Novo Destino	521945	9	3173	3.32	santaritense	956.04
1091	Santa Tereza De Goiás	521960	9	3995	5.03	santerezino	794.555
1094	Santo Antônio De Goiás	521973	9	4703	35.41	santoantoniense	132.805
1097	São Francisco De Goiás	521990	9	6120	14.72	franciscano	415.791
1099	São João Da Paraúna	522005	9	1689	5.87	joanino	287.825
1102	São Miguel Do Araguaia	522020	9	22283	3.63	são-miguelense	6144.4
1105	São Simão	522040	9	17088	41.27	canalense	414.011
1107	Serranópolis	522050	9	7481	1.35	serranopolino	5526.726
1110	Sítio D`Abadia	522070	9	2825	1.77	sitiense	1598.344
1112	Teresina De Goiás	522108	9	3016	3.89	teresinense	774.637
1114	Três Ranchos	522130	9	2819	9.99	triranchense	282.069
1117	Turvânia	522150	9	4839	10.06	turvaniense	480.775
1119	Uirapuru	522157	9	2933	2.54	uirapuruense	1153.473
1121	Uruana	522170	9	13826	26.46	uruanense	522.505
1123	Valparaíso De Goiás	522185	9	132982	2.197	valparaisense	60.525
1126	Vicentinópolis	522205	9	7371	10	vicentinopolino	737.255
1129	Açailândia	210005	10	104047	17.92	açailandense	5806.371
1131	Água Doce Do Maranhão	210015	10	11581	26.13	aguadocense	443.264
1134	Altamira Do Maranhão	210040	10	11063	15.33	altamirense	721.504
1136	Alto Alegre Do Pindaré	210047	10	31057	16.07	alto-alegrense	1932.283
1139	Amarante Do Maranhão	210060	10	37932	5.1	amarantino	7438.019
1142	Apicum Açu	210083	10	14959	42.36	apicum-açuense	353.164
1144	Araioses	210090	10	42505	23.84	araiosense	1782.59
1146	Arari	210100	10	28488	25.89	arariense	1100.269
1149	Bacabeira	210125	10	14925	24.25	bacabeirense	615.586
1150	Bacuri	210130	10	16604	21.08	bacuriense	787.851
1151	Bacurituba	210135	10	5293	7.85	bacuritubense	674.508
1153	Barão De Grajaú	210150	10	17841	7.94	baronense	2247.229
1155	Barreirinhas	210170	10	54930	17.65	barreirinhense	3111.974
1158	Benedito Leite	210180	10	5469	3.07	beneleitense	1781.727
1160	Bernardo Do Mearim	210193	10	5996	22.93	bernardense	261.45
1163	Bom Jesus Das Selvas	210203	10	28459	10.62	bom-jesuense	2679.093
1165	Brejo	210210	10	33359	31.04	brejense	1074.578
1168	Buriti Bravo	210230	10	22899	14.47	buriti-bravense	1582.545
1170	Buritirana	210235	10	14784	18.06	buritiranense 	818.421
1172	Cajapió	210240	10	10593	11.66	cajapioense	908.724
1174	Campestre Do Maranhão	210255	10	13369	21.72	campestrense	615.381
1177	Capinzal Do Norte	210275	10	10698	18.12	capinzalense	590.526
1180	Caxias	210300	10	155129	30.12	caxiense	5150.647
1182	Central Do Maranhão	210312	10	7887	24.72	centralense	319.051
1184	Centro Novo Do Maranhão	210317	10	17622	2.13	centronovense	8258.387
1187	Codó	210330	10	118038	27.06	codoense	4361.318
1189	Colinas	210350	10	39132	19.76	colinense	1980.545
1192	Cururupu	210370	10	32652	26.69	cururupuense	1223.364
1194	Dom Pedro	210380	10	22681	63.27	dom-pedrense	358.492
1196	Esperantinópolis	210400	10	18452	38.37	esperantinopense 	480.914
1198	Feira Nova Do Maranhão	210407	10	8126	5.52	nova-feirense	1473.41
1201	Fortaleza Dos Nogueiras	210410	10	11646	7	fortalezense	1664.323
1204	Gonçalves Dias	210440	10	17482	19.9	gonçalvino	878.545
1206	Governador Edison Lobão	210455	10	15895	25.81	edison-lobense 	615.84
1208	Governador Luiz Rocha	210462	10	7337	19.66	luiz-rochense 	373.162
1211	Graça Aranha	210470	10	6140	22.62	graçaranhense	271.442
1214	Humberto De Campos	210500	10	26189	12.29	humbertoense	2131.25
1216	Igarapé Do Meio	210515	10	12550	34.04	igarapeense	368.683
1219	Itaipava Do Grajaú	210535	10	14297	11.54	itaipavense	1238.815
1221	Itinga Do Maranhão	210542	10	24863	6.94	itinguense	3581.702
1223	Jenipapo Dos Vieiras	210547	10	15440	7.87	jenipapoense	1962.889
1226	Junco Do Maranhão	210565	10	4020	7.24	juncoense	555.085
1228	Lago Do Junco	210580	10	10729	34.72	juncoense	309.019
1231	Lagoa Do Mato	210592	10	10934	6.48	lagoense	1688.038
1233	Lajeado Novo	210598	10	6923	6.61	lajeadense	1047.729
1236	Luís Domingues	210620	10	6510	14.03	luís-dominguense	464.057
1238	Maracaçumé	210632	10	19155	30.44	maracaçumeense	629.302
1240	Maranhãozinho	210637	10	14065	14.46	maranhãozinense	972.612
1243	Matões	210660	10	31015	15.69	matoense	1976.13
1245	Milagres Do Maranhão	210667	10	8118	12.79	milagrense	634.734
1248	Mirinzal	210680	10	14218	20.67	mirinzalense	687.744
1250	Montes Altos	210700	10	9413	6.32	monte-altense	1488.331
1253	Nova Colinas	210725	10	4885	6.57	nova-colinense	743.103
1255	Nova Olinda Do Maranhão	210735	10	19134	7.8	novaolindense 	2452.603
1257	Olinda Nova Do Maranhão	210745	10	13181	66.69	olindense	197.635
1260	Paraibano	210770	10	20103	37.89	paraibanense	530.515
1263	Pastos Bons	210800	10	18067	11.05	pastos-bonense	1635.298
1265	Paulo Ramos	210810	10	20079	19.06	paulo-ramense	1053.409
1267	Pedro Do Rosário	210825	10	22732	12.99	pedro-rosariense	1749.876
1270	Peritoró	210845	10	21201	25.71	peritoroense	824.718
1272	Pinheiro	210860	10	78162	51.66	pinheirense	1512.958
1274	Pirapemas	210880	10	17381	25.24	pirapemense	688.758
1275	Poção De Pedras	210890	10	19708	20.13	poção-pedrense	979.199
1278	Presidente Dutra	210910	10	44731	57.97	presidutrense	771.57
1280	Presidente Médici	210923	10	6374	14.56	medicense	437.685
1283	Primeira Cruz	210940	10	13954	10.2	primeira-cruzense	1367.669
1285	Riachão	210950	10	20209	3.17	riachãoense	6372.995
1288	Sambaíba	210970	10	5487	2.21	sambaibense	2478.687
1289	Santa Filomena Do Maranhão	210975	10	7061	11.72	santa-filomenense	602.338
1292	Santa Luzia	211000	10	74043	15.54	santa-luziense	4766.115
1294	Santa Quitéria Do Maranhão	211010	10	29191	15.22	quiteriense	1917.58
1297	Santo Amaro Do Maranhão	211027	10	13820	8.63	santamarense	1601.171
1299	São Benedito Do Rio Preto	211040	10	17799	19.11	são-beneditense	931.476
1302	São Domingos Do Azeitão	211065	10	6983	7.27	são-dominguense	960.925
1305	São Francisco Do Brejão	211085	10	10261	13.76	brejãoense	745.603
1307	São João Batista	211100	10	19920	28.84	juanino ou joanino	690.679
1309	São João Do Paraíso	211105	10	10814	5.27	paraisense	2053.835
1312	São José De Ribamar	211120	10	163045	419.82	ribamarense	388.369
1315	São Luís Gonzaga Do Maranhão	211140	10	20153	20.81	gonzaguense	968.567
1318	São Pedro Dos Crentes	211157	10	4425	4.52	são-pedrense	979.628
1320	São Raimundo Do Doca Bezerra	211163	10	6090	14.52	são-raimundense	419.35
1323	Satubinha	211172	10	11990	27.14	satubinhense	441.809
1325	Senador La Rocque	211176	10	17998	14.55	laroquense	1236.678
1328	Sucupira Do Norte	211190	10	10444	9.72	sucupirense	1074.46
1330	Tasso Fragoso	211200	10	7796	1.78	fragosense	4382.96
1333	Trizidela Do Vale	211223	10	18953	85.01	trizidelense	222.945
1335	Tuntum	211230	10	39183	11.56	tuntuense	3389.981
1338	Tutóia	211250	10	52788	31.96	tutoiense	1651.647
1340	Vargem Grande	211270	10	49412	25.24	vargem-grandense	1957.74
1342	Vila Nova Dos Martírios	211285	10	11258	9.47	vila-novense	1188.765
1345	Zé Doca	211400	10	50173	20.77	zé-doquense	2416.053
1347	Abaeté	310020	11	22690	12.49	abaetense	1817.066
1349	Acaiaca	310040	11	3920	38.47	acaiaquense	101.886
1352	Água Comprida	310070	11	2025	4.12	água-compridense	491.045
1354	Águas Formosas	310090	11	18479	22.53	águas-formosense	820.077
1356	Aimorés	310110	11	24959	18.5	aimorense	1348.774
1359	Albertina	310140	11	2913	50.22	albertinense	58.01
1361	Alfenas	310160	11	73774	86.75	alfenense	850.446
1363	Almenara	310170	11	38775	16.9	almenarense	2294.42
1365	Alpinópolis	310190	11	18488	40.66	alpinopolense	454.751
1368	Alto JequitibÁ	315350	11	8318	54.63	jequitibaense	152.272
1370	Alvarenga	310220	11	4444	15.98	alvarenguense	278.173
1372	Alvorada De Minas	310240	11	3546	9.48	alvoradense	374.008
1375	Andrelândia	310280	11	12173	12.11	andrelandense	1005.284
1377	Antônio Carlos	310290	11	11114	20.97	antônio-carlense	529.915
1379	Antônio Prado De Minas	310310	11	1671	19.94	pradense-de-minas	83.802
1382	Araçuaí	310340	11	36013	16.1	araçuaiense	2236.275
1385	Araponga	310370	11	8152	26.83	araponguense	303.792
1387	ArapuÁ	310380	11	2775	15.96	arapuaense	173.894
1389	AraxÁ	310400	11	93672	80.45	araxaense	1164.358
1391	Arcos	310420	11	36597	71.78	arcoense	509.873
1394	Aricanduva	310445	11	4770	19.6	aricanduvense 	243.328
1396	Astolfo Dutra	310460	11	13049	82.13	astolfo-dutrense	158.89
1397	Ataléia	310470	11	14455	7.87	ataleiense	1836.974
1400	Baldim	310500	11	7913	14.23	baldinense	556.266
1402	Bandeira	310520	11	4987	10.31	bandeirense	483.788
1404	Barão De Cocais	310540	11	28442	83.51	cocaiense	340.601
1407	Barra Longa	310570	11	6143	16.01	barra-longuense	383.628
1409	Bela Vista De Minas	310600	11	10004	91.66	bela-vistano	109.143
1411	Belo Horizonte	310620	11	2375151	7.167	belo-horizontino	331.4
1414	Berilo	310650	11	12300	20.95	berilense	587.105
1416	Bertópolis	310660	11	4498	10.51	bertopolitano	427.802
1418	Bias Fortes	310680	11	3793	13.38	bias-fortense	283.535
1421	Boa Esperança	310710	11	38516	44.75	esperancense	860.669
1423	Bocaiúva	310730	11	46654	14.45	bocaiuvense	3227.622
1425	Bom Jardim De Minas	310750	11	6501	15.78	bom-jardinense	412.021
1428	Bom Jesus Do Galho	310780	11	15364	25.94	bom-jesuense	592.288
1430	Bom Sucesso	310800	11	17243	24.46	bom-sucessense	705.046
1432	Bonfinópolis De Minas	310820	11	5865	3.28	bonfinopolitano	1789.165
1435	Botelhos	310840	11	14920	44.66	botelhense	334.089
1437	BrÁs Pires	310870	11	4637	20.76	brás-pirense	223.351
1439	Brasília De Minas	310860	11	31213	22.3	brasilminense	1399.482
1442	Brumadinho	310900	11	33973	53.13	brumadinhense	639.434
1444	Buenópolis	310920	11	10292	6.43	buenopolitano 	1599.879
1447	Buritizeiro	310940	11	26922	3.73	buritizeirense	7218.392
1449	Cabo Verde	310950	11	13823	37.54	cabo-verdense	368.206
1451	Cachoeira De Minas	310970	11	11034	36.27	cachoeirense	304.243
1453	Cachoeira Dourada	310980	11	2505	12.47	cachoeirense	200.928
1456	Caiana	311010	11	4968	46.67	caianense	106.459
1458	Caldas	311030	11	13633	19.16	caldense	711.414
1461	Cambuí	311060	11	26488	108.31	cambuiense	244.567
1463	CampanÁrio	311080	11	3564	8.06	campanarense	442.397
1465	Campestre	311100	11	20686	35.8	campestrense	577.843
1467	Campo Azul	311115	11	3684	7.28	campoazulense	505.913
1469	Campo Do Meio	311130	11	11476	41.67	campo-meiense	275.426
1472	Campos Gerais	311160	11	27600	35.87	campos-geraiense	769.504
1474	Canaã	311170	11	4628	26.46	canaãense	174.9
1477	Cantagalo	311205	11	4195	29.57	cantagalense	141.855
1479	Capela Nova	311220	11	4755	42.81	capela-novense	111.073
1481	Capetinga	311240	11	7089	23.79	capetinguense	297.937
1483	Capinópolis	311260	11	15290	24.63	capinopolino	620.716
1485	Capitão Enéas	311270	11	14206	14.62	capitão-eneense	971.581
1488	Caraí	311300	11	22343	17.99	caraiense	1242.197
1490	Carandaí	311320	11	23346	48.06	carandaiense	485.723
1492	Caratinga	311340	11	85239	67.72	caratinguense	1258.776
1495	Carlos Chagas	311370	11	20069	6.27	carlos-chaguense	3202.977
1497	Carmo Da Cachoeira	311390	11	11836	23.38	carmo-cachoeirense	506.333
1499	Carmo De Minas	311410	11	13750	42.66	carmoense	322.285
1502	Carmo Do Rio Claro	311440	11	20426	19.17	carmelitano	1065.685
1505	Carrancas	311460	11	3948	5.42	carranquense	727.893
1507	Carvalhos	311480	11	4556	16.14	carvalhense	282.254
1509	Cascalho Rico	311500	11	2857	7.78	cascalho-riquense	367.308
1512	Catas Altas	311535	11	4846	20.19	catas-altense 	240.042
1513	Catas Altas Da Noruega	311540	11	3462	24.45	catas-altense	141.622
1516	Caxambu	311550	11	21705	216.01	caxambuense	100.483
1519	Centralina	311580	11	10266	31.38	centralinense	327.191
1521	Chalé	311600	11	5645	26.54	chaleense	212.674
1523	Chapada Gaúcha	311615	11	10805	3.32	chapadense	3255.182
1524	Chiador	311620	11	2785	11.01	chiadorense	252.938
1526	Claraval	311640	11	4542	19.95	claravalense	227.627
1528	ClÁudio	311660	11	25771	40.86	claudiense	630.706
1530	Coluna	311680	11	9024	25.89	colunense	348.491
1532	Comercinho	311700	11	8298	12.67	comerciense	654.959
1534	Conceição Da Barra De Minas	311520	11	3954	14.48	conceicionense	273.014
1537	Conceição De Ipanema	311740	11	4456	17.55	ipanemense	253.935
1540	Conceição Do Rio Verde	311770	11	12949	35.03	conceicionense	369.681
1542	Cônego Marinho	311783	11	7101	4.32	cônego marinhense	1641.994
1545	Congonhas	311800	11	48519	159.57	congonhense	304.066
1547	Conquista	311820	11	6526	10.55	conquistense	618.363
1549	Conselheiro Pena	311840	11	22242	14.99	conselheiro-penense	1483.882
1552	Coqueiral	311870	11	9289	31.36	coqueirense	296.163
1554	Cordisburgo	311890	11	8667	10.52	cordisburguense	823.653
1557	Coroaci	311920	11	10270	17.82	coroaciense	576.273
1559	Coronel Fabriciano	311940	11	103694	468.67	fabricianense	221.252
1561	Coronel Pacheco	311960	11	2983	22.68	pachequense	131.511
1564	Córrego Do Bom Jesus	311990	11	3730	30.17	correguense	123.651
1566	Córrego Novo	312000	11	3127	15.23	córrego-novense	205.385
1569	Cristais	312020	11	11286	17.96	cristalense	628.434
1571	Cristiano Otoni	312040	11	5007	37.68	cristianense	132.872
1573	Crucilândia	312060	11	4757	28.46	crucilandense	167.164
1576	Cuparaque	312083	11	4680	20.64	cuparaquense	226.75
1578	Curvelo	312090	11	74219	22.5	curvelano	3298.789
1580	Delfim Moreira	312110	11	7971	19.51	delfinense	408.473
1583	Descoberto	312130	11	4768	22.37	descobertense	213.168
1585	Desterro Do Melo	312150	11	3015	21.19	melense	142.279
1587	Diogo De Vasconcelos	312170	11	3848	23.31	vasconcelense	165.091
1590	Divino	312200	11	19133	56.64	divinense	337.776
1592	Divinolândia De Minas	312220	11	7024	52.76	divinolandense	133.12
1595	Divisa Nova	312240	11	5763	26.56	divisa-novense	216.955
1598	Dom Cavati	312250	11	5209	87.52	dom-cavatiano	59.52
1600	Dom Silvério	312270	11	5196	26.65	dom-silveriense	194.972
1602	Dona Eusébia	312290	11	6001	85.45	euzebense 	70.231
1605	Dores Do IndaiÁ	312320	11	13778	12.4	dorense	1111.201
1608	Douradoquara	312350	11	1841	5.88	douradoquarense	312.878
1610	Elói Mendes	312360	11	25220	50.49	elói-mendense	499.537
1612	Engenheiro Navarro	312380	11	7122	11.71	navarrense	608.305
1615	ErvÁlia	312400	11	17946	50.2	ervalense	357.489
1617	Espera Feliz	312420	11	22856	71.96	espera-felizense	317.637
1619	Espírito Santo Do Dourado	312440	11	4429	16.78	douradense 	263.879
1622	Estrela Do IndaiÁ	312470	11	3516	5.53	estrelense	635.981
1624	Eugenópolis	312490	11	10540	34.07	eugenopolense	309.395
1627	Fama	312520	11	2350	27.32	famense	86.024
1629	Felício Dos Santos	312540	11	5142	14.38	feliz-santense	357.621
1632	Fernandes Tourinho	312580	11	3030	19.95	fernandes-tourinhense	151.874
1634	Fervedouro	312595	11	10349	28.93	fervedourense	357.683
1636	Formiga	312610	11	65128	43.36	formiguense	1501.915
1639	Fortuna De Minas	312640	11	2705	13.61	fortunense	198.709
1641	Francisco Dumont	312660	11	4863	3.09	francisco-dumonsense	1576.126
1643	Franciscópolis	312675	11	5800	8.09	franciscopolitano	717.086
1646	Frei Lagonegro	312695	11	3329	19.88	frei lagonegrense	167.474
1647	Fronteira	312700	11	14041	70.21	fronteirense	199.987
1648	Fronteira Dos Vales	312705	11	4687	14.61	fronteirista-dos-vales	320.757
1651	Funilândia	312720	11	3855	19.29	funilandense	199.796
1653	Gameleiras	312733	11	5139	2.97	gameleirense	1733.199
1656	GoianÁ	312738	11	3659	24.07	goianaense	152.039
1658	Gonzaga	312750	11	5921	28.28	gonzaguense	209.347
1660	Governador Valadares	312770	11	263689	112.58	valadarense	2342.316
1663	Guanhães	312800	11	31262	29.08	guanhanense	1075.122
1665	Guaraciaba	312820	11	10223	29.33	guaraciabense	348.595
1667	Guaranésia	312830	11	18714	63.47	guaranesiano	294.828
1670	Guarda Mor	312860	11	6565	3.17	guarda-morense	2069.789
1672	Guidoval	312880	11	7206	45.5	guidovalense	158.375
1674	Guiricema	312900	11	8707	29.66	guiricemense	293.578
1676	Heliodora	312920	11	6121	39.76	heliodorense	153.95
1679	IbiÁ	312950	11	23218	8.59	ibiaense	2704.131
1681	Ibiracatu	312965	11	6155	17.42	ibiracatuense	353.413
1683	Ibirité	312980	11	158954	2.19	ibiritenense	72.573
1685	Ibituruna	313000	11	2866	18.72	ibiturunense	153.106
1688	Igaratinga	313020	11	9264	42.43	igaratinguense	218.343
1690	Ijaci	313040	11	5859	55.67	ijaciense	105.246
1692	Imbé De Minas	313055	11	6424	32.65	imbeense	196.735
1695	Indianópolis	313070	11	6190	7.46	indianopolense	830.03
1697	Inhapim	313090	11	24294	28.31	inhapinhense	858.023
1699	Inimutaba	313110	11	6724	12.82	inimutabano	524.467
1702	Ipatinga	313130	11	239468	1.452	ipatinguense	164.884
1704	Ipuiúna	313150	11	9521	31.93	ipuiunense	298.195
1706	Itabira	313170	11	109783	87.57	itabirano	1253.702
1709	Itacambira	313200	11	4988	2.79	itacambirano	1788.442
1711	Itaguara	313220	11	12372	30.14	itaguarense	410.468
1713	ItajubÁ	313240	11	90658	307.49	itajubense	294.835
1715	Itamarati De Minas	313260	11	4079	43.13	tamaratiense	94.568
1717	Itambé Do Mato Dentro	313280	11	2283	6	itambeense	380.34
1720	Itanhandu	313310	11	14175	98.87	itanhanduense	143.368
1723	Itapagipe	313340	11	13656	7.58	itapagipense	1802.437
1725	Itapeva	313360	11	8664	48.85	itapevense	177.347
1727	Itaú De Minas	313375	11	14945	97.41	itauense	153.421
1730	Itinga	313400	11	14407	8.73	itinguense	1649.618
1732	Ituiutaba	313420	11	97171	37.4	ituiutabano	2598.046
1734	Iturama	313440	11	34456	24.53	ituramense	1404.664
1736	Jaboticatubas	313460	11	17134	15.38	jaboticatubense	1114.155
1739	Jacutinga	313490	11	22772	65.48	jacutinguense	347.75
1741	Jaíba	313505	11	33587	12.79	jaibense	2626.326
1744	JanuÁria	313520	11	65463	9.83	januarense	6661.653
1746	Japonvar	313535	11	8298	22.13	japonvaense	374.905
1748	Jenipapo De Minas	313545	11	7116	25.02	jenipapense	284.452
1751	JequitibÁ	313570	11	5156	11.59	jequitibaense	445.029
1752	Jequitinhonha	313580	11	24131	6.87	jequitinhonhense	3514.208
1755	Joanésia	313610	11	5425	23.25	joanense	233.292
1757	João Pinheiro	313630	11	45260	4.22	pinheirense	10727.46
1760	José Gonçalves De Minas	313652	11	4553	11.94	gonçalvense	381.331
1762	Josenópolis	313657	11	4566	8.43	josenopolense	541.392
1765	Juramento	313680	11	4113	9.53	juramentense	431.629
1767	Juvenília	313695	11	5708	5.36	juveniliense	1064.695
1769	Lagamar	313710	11	7600	5.15	lagamaraense	1474.561
1772	Lagoa Dourada	313740	11	12256	25.71	lagoense	476.693
1774	Lagoa Grande	313753	11	8631	6.98	lagoa grandense	1236.3
1777	Lambari	313780	11	19554	91.76	lambariense	213.11
1778	Lamim	313790	11	3452	29.11	laminense	118.602
1780	Lassance	313810	11	6484	2.02	lassancense	3204.213
1782	Leandro Ferreira	313830	11	3205	9.1	leandrense	352.107
1784	Leopoldina	313840	11	51130	54.22	leopoldinense	943.075
1787	Limeira Do Oeste	313862	11	6890	5.22	limeirense	1319.036
1789	Luisburgo	313867	11	6234	42.87	luisburguense	145.418
1791	LuminÁrias	313870	11	5422	10.84	luminarense	500.143
1794	Machado	313900	11	38688	66.03	machadense	585.958
1796	Malacacheta	313920	11	18776	25.8	malacachetense	727.885
1798	Manga	313930	11	19813	10.16	manguense	1950.18
1801	Mantena	313960	11	27111	39.57	mantenense	685.207
1803	Maravilhas	313970	11	7163	27.38	maravilhense	261.604
1805	Mariana	314000	11	54219	45.4	marianense	1194.207
1807	MÁrio Campos	314015	11	13192	374.82	mario-campense	35.196
1810	Marmelópolis	314040	11	2968	27.51	marmelopolense	107.902
1812	Martins Soares	314053	11	7173	63.33	martinsoarense	113.268
1814	Materlândia	314060	11	4595	16.38	materlandiense	280.53
1817	Matias Barbosa	314080	11	13435	85.51	matiense	157.107
1819	Matipó	314090	11	17639	66.07	matipoense	266.99
1822	Matutina	314120	11	3761	14.41	matutinense	260.957
1824	Medina	314140	11	21026	14.64	medinense	1435.9
1826	Mercês	314160	11	10368	29.77	mercesano	348.271
1829	Minduri	314190	11	3840	17.47	mindurense	219.774
1831	Miradouro	314210	11	10251	33.98	miradourense	301.672
1833	Miravânia	314225	11	4549	7.55	miravaniense	602.127
1835	Moema	314240	11	7028	34.67	moemense	202.705
1837	Monsenhor Paulo	314260	11	8161	37.69	paulense	216.54
1840	Monte Azul	314290	11	21994	22.12	monte-azulense	994.229
1842	Monte Carmelo	314310	11	45772	34.08	carmelitano	1343.035
1844	Monte Santo De Minas	314320	11	21234	35.71	monte-santense	594.632
1847	Montezuma	314345	11	7464	6.6	montesumense	1130.416
1849	Morro Da Garça	314360	11	2660	6.41	morrense	414.771
1852	Muriaé	314390	11	100765	119.72	muriaense	841.692
1854	Muzambinho	314410	11	20430	49.84	muzambinhense	409.948
1857	Naque	314435	11	6341	49.86	naquense	127.173
1859	Natércia	314440	11	4658	24.68	naterciano	188.719
1861	Nepomuceno	314460	11	25733	44.17	nepomucenense	582.553
1863	Nova Belém	314467	11	3732	25.43	belenense	146.774
1866	Nova Módica	314490	11	3790	10.08	neomodicano	375.972
1868	Nova Porteirinha	314505	11	7398	61.17	novaporteirinhense	120.943
1870	Nova Serrana	314520	11	73699	261	nova-serranense	282.369
1873	Novo Oriente De Minas	314535	11	10339	13.69	novo orientense	755.149
1875	Olaria	314540	11	1976	11.09	olariense	178.242
1877	Olímpio Noronha	314550	11	2533	46.36	olímpio-noroense	54.633
1880	Onça De Pitangui	314580	11	3055	12.37	oncense	246.976
1882	Orizânia	314587	11	7284	59.8	orizanense	121.8
1885	Ouro Preto	314610	11	70281	56.41	ouro-pretano	1245.864
1887	Padre Carvalho	314625	11	5834	13.07	padre carvaliense	446.325
1889	Pai Pedro	314655	11	5934	7.07	paipedrense	839.804
1892	Paiva	314660	11	1558	26.67	paivense	58.419
1894	Palmópolis	314675	11	6931	16	palmopolense	433.153
1896	ParÁ De Minas	314710	11	84215	152.77	paraense	551.247
1899	Paraisópolis	314730	11	19379	58.5	paraisopolense	331.238
1901	Passa Quatro	314760	11	15582	56.21	passa-quatrense	277.221
1903	Passa Vinte	314780	11	2079	8.43	passa-vintense	246.564
1906	Patis	314795	11	5579	12.56	patiense	444.196
1907	Patos De Minas	314800	11	138710	43.49	patense	3189.769
1909	Patrocínio Do Muriaé	314820	11	5287	48.84	patrocinense	108.245
1911	Paulistas	314840	11	4918	22.3	paulistano	220.564
1914	Pedra Azul	314870	11	23839	14.94	pedra-azulense	1595.129
1916	Pedra Do Anta	314880	11	3365	20.59	antense	163.445
1918	Pedra Dourada	314900	11	2191	31.3	douradense	69.99
1921	Pedrinópolis	314920	11	3490	9.75	pedrinopolense	357.891
1923	Pedro Teixeira	314940	11	1785	15.8	pedro-teixeirense	112.959
1926	Perdigão	314970	11	8912	35.77	perdiguense	249.123
1928	Perdões	314990	11	20087	74.22	perdoense	270.657
1930	Pescador	315000	11	4128	13	pescadorense	317.462
1932	Piedade De Caratinga	315015	11	7110	65.02	piedade-caratinguense	109.345
1935	Piedade Dos Gerais	315040	11	4640	17.87	piedadense	259.638
1937	Pingo D`Água	315053	11	4420	66.4	pingodaguense	66.57
1940	Pirajuba	315070	11	4656	13.78	pirajubense	337.98
1942	Piranguçu	315090	11	5217	25.62	piranguçuense	203.619
1945	Pirapora	315120	11	53368	97.12	piraporense	549.514
1947	Pitangui	315140	11	25311	44.44	pitanguense	569.611
1949	Planura	315160	11	10384	32.7	planurense	317.52
1951	Poços De Caldas	315180	11	152435	278.54	poços-caldense	547.261
1954	Ponte Nova	315210	11	57390	121.94	ponte-novense	470.642
1956	Ponto Dos Volantes	315217	11	11345	9.36	ponto volantense	1212.411
1959	Poté	315240	11	15667	25.06	poteense	625.11
1961	Pouso Alto	315260	11	6213	23.74	pouso-altense	261.688
1963	Prata	315280	11	25802	5.32	pratense	4847.544
1965	Pratinha	315300	11	3265	5.25	pratinhense	622.478
1967	Presidente Juscelino	315320	11	3908	5.62	juscelinense	695.882
1970	Prudente De Morais	315360	11	9573	77.08	prudentino	124.189
1973	Raposos	315390	11	15342	212.58	raposense	72.169
1975	Recreio	315410	11	10299	43.96	recreiense	234.296
1977	Resende Costa	315420	11	10913	17.65	resende-costense	618.311
1980	Riachinho	315445	11	8007	4.5	riachiense	1780.583
1982	Ribeirão Das Neves	315460	11	296317	1.917	nevense	154.501
1984	Rio Acima	315480	11	9090	39.55	rio-acimense	229.812
1987	Rio Doce	315500	11	2465	21.99	rio-docense	112.094
1989	Rio Manso	315530	11	5276	22.79	rio-mansense	231.54
1991	Rio Paranaíba	315550	11	11885	8.79	rio-paraibano	1352.353
1994	Rio Pomba	315580	11	17110	67.78	rio-pombense	252.418
1996	Rio Vermelho	315600	11	13645	13.83	rio-vermelhense	986.56
1998	Rochedo De Minas	315620	11	2116	26.65	rochedense	79.402
2001	RosÁrio Da Limeira	315645	11	4247	38.21	limeirense	111.155
2003	Rubim	315660	11	9919	10.28	rubinense	965.172
2005	Sabinópolis	315680	11	15704	17.07	sabinopolense	919.81
2008	Salto Da Divisa	315710	11	6859	7.31	saltense	937.921
2010	Santa BÁrbara Do Leste	315725	11	7682	71.53	santa barbarense	107.402
2013	Santa Cruz De Minas	315733	11	7865	2.206	santacruzense	3.565
2015	Santa Cruz Do Escalvado	315740	11	4992	19.29	santa-cruzense	258.726
2018	Santa Helena De Minas	315765	11	6055	21.9	santaelenense de minas	276.432
2021	Santa Margarida	315790	11	15011	58.7	santa-margaridense	255.73
2023	Santa Maria Do Salto	315810	11	5284	11.99	santa-mariense	440.604
2025	Santa Rita De Caldas	315920	11	9027	17.95	santa-ritense	503.012
2028	Santa Rita De Minas	315935	11	6547	96.06	santa-ritense	68.153
2030	Santa Rita Do Sapucaí	315960	11	37754	106.96	santa-ritense 	352.969
2031	Santa Rosa Da Serra	315970	11	3224	11.34	rosalense	284.334
2032	Santa Vitória	315980	11	18138	6.04	santa-vitoriense	3001.358
2034	Santana De Cataguases	315840	11	3622	22.43	santanense	161.486
2037	Santana Do Garambéu	315870	11	2234	11	santanense	203.074
2039	Santana Do Manhuaçu	315890	11	8582	24.71	santanense	347.362
2042	Santana Dos Montes	315910	11	3822	19.44	santanense	196.565
2044	Santo Antônio Do Aventureiro	316000	11	3538	17.51	aventureirense	202.033
2047	Santo Antônio Do Jacinto	316030	11	11775	23.39	santo-antoniense	503.375
2049	Santo Antônio Do Retiro	316045	11	6955	8.73	retirense	796.288
2052	Santos Dumont	316070	11	46284	72.62	sandumonense	637.373
2054	São BrÁs Do Suaçuí	316090	11	3513	31.93	suaçuiense	110.018
2057	São Félix De Minas	316105	11	3382	20.8	são felense	162.56
2059	São Francisco De Paula	316120	11	6483	20.46	francisco-paulense	316.822
2061	São Francisco Do Glória	316140	11	5178	31.46	são-franciscano-do-glória	164.613
2064	São Geraldo Do Baixio	316165	11	3486	12.41	baixiense	280.954
2067	São Gonçalo Do Rio Abaixo	316190	11	9777	26.87	são-gonçalense	363.811
2069	São Gonçalo Do Sapucaí	316200	11	23906	46.27	são-gonçalense	516.683
2072	São João Da Lagoa	316225	11	4656	4.67	lagoano	998.013
2074	São João Da Ponte	316240	11	25358	13.7	pontense	1851.099
2077	São João Do Manhuaçu	316255	11	10245	71.6	sanjoanense	143.095
2079	São João Do Oriente	316260	11	7874	65.55	são-joanense	120.122
2082	São João Evangelista	316280	11	15553	32.53	evangelistano	478.183
2084	São Joaquim De Bicas	316292	11	25537	356.88	sanjoaquimbiquense	71.557
2087	São José Da Safira	316300	11	4075	19.05	safirense	213.88
2089	São José Do Alegre	316320	11	3996	45	alegrense	88.794
2092	São José Do Jacuri	316350	11	6553	18.99	jacuriense	345.145
2094	São Lourenço	316370	11	41657	717.99	são-lourenciano	58.019
2097	São Pedro Do Suaçuí	316410	11	5570	18.08	são-pedrense 	308.105
2100	São Roque De Minas	316430	11	6686	3.19	são-roquense	2098.866
2102	São Sebastião Da Vargem Alegre	316443	11	2798	38	são sebastião vargem alegrense	73.629
2105	São Sebastião Do Oeste	316460	11	5805	14.22	sebastianense	408.09
2107	São Sebastião Do Rio Preto	316480	11	1613	12.6	são-sebastianense	128.002
2111	São TomÁs De Aquino	316510	11	7093	25.52	aquinense	277.928
2113	Sapucaí Mirim	316540	11	6241	21.89	sapucaiense	285.075
2116	Sem Peixe	316556	11	2847	16.12	sempeixiano	176.634
2118	Senador Cortes	316560	11	1988	20.22	senador-cortense	98.336
2120	Senador José Bento	316580	11	1868	19.9	senabentense	93.892
2123	Senhora Do Porto	316610	11	3497	9.17	portuense	381.328
2125	Sericita	316630	11	7128	42.94	sericitense	166.012
2128	Serra Da Saudade	316660	11	815	2.43	serrano-saudalense	335.659
2130	Serra Dos Aimorés	316670	11	8412	39.39	serrense	213.577
2132	Serranópolis De Minas	316695	11	4425	8.02	serranopolitano de minas	551.953
2135	Sete Lagoas	316720	11	214152	398.32	sete-lagoano	537.638
2138	Silvianópolis	316740	11	6027	19.31	silvianopolense	312.166
2140	Simonésia	316760	11	18298	37.61	simonense	486.542
2142	Soledade De Minas	316780	11	5676	28.83	soledadense	196.866
2145	Taparuba	316805	11	3137	16.25	taparubense	193.081
2146	Tapira	316810	11	4112	3.49	tapirense	1179.248
2148	Taquaraçu De Minas	316830	11	3794	11.52	taquaraçuense	329.24
2151	Teófilo Otoni	316860	11	134745	41.56	teófilo-otonense	3242.263
2153	Tiradentes	316880	11	6961	83.82	tiradentino	83.047
2156	Tocos Do Moji	316905	11	3950	34.44	tocos-mogiense	114.705
2158	Tombos	316920	11	9537	33.45	tomboense	285.125
2160	Três Marias	316935	11	28318	10.57	trimariense	2678.251
2163	Tupaciguara	316960	11	24188	13.26	tupaciguarense	1823.96
2165	Turvolândia	316980	11	4658	21.08	turvolandense	221
2168	Ubaporanga	317005	11	12040	63.69	ubaporanguense	189.045
2170	Uberlândia	317020	11	604013	146.78	uberlandense	4115.206
2172	Unaí	317040	11	77565	9.18	unaiense	8447.098
2175	Urucânia	317050	11	10291	74.15	urucaniense	138.792
2177	Vargem Alegre	317057	11	6461	55.38	vargealegrense	116.664
2179	Vargem Grande Do Rio Pardo	317065	11	4733	9.63	vargengrandense	491.511
2182	VÁrzea Da Palma	317080	11	35809	16.13	várzea-palmense	2220.276
2185	Verdelândia	317103	11	8346	5.31	verdelandense	1570.574
2187	Veríssimo	317110	11	3483	3.38	verissimense	1031.824
2189	Vespasiano	317120	11	104527	1.468	vespasianense	71.18
2192	Virgem Da Lapa	317160	11	13619	15.67	virgem-lapense	868.913
2194	Virginópolis	317180	11	10572	24.03	virginopolitano	439.877
2196	Visconde Do Rio Branco	317200	11	37942	155.91	rio-branquense	243.351
2199	Água Clara	500020	12	14424	1.31	água-clarense	11031.131
2201	Amambai	500060	12	34730	8.26	amambaiense	4202.335
2204	Angélica	500085	12	9185	7.21	angeliquense	1273.271
2206	Aparecida Do Taboado	500100	12	22320	8.12	aparecidense 	2750.153
2208	Aral Moreira	500124	12	10251	6.19	aral-moreirense	1655.665
2211	Batayporã	500200	12	10936	5.98	bataiporãense	1828.028
2213	Bodoquena	500215	12	7985	3.18	bodoquenense	2507.325
2215	Brasilândia	500230	12	11826	2.04	brasilandense	5806.911
2218	Campo Grande	500270	12	786797	97.22	campo-grandense	8092.966
2220	Cassilândia	500290	12	20966	5.74	cassilandense	3649.572
2223	Coronel Sapucaia	500315	12	14064	13.72	sapucaense	1025.052
2225	Costa Rica	500325	12	19695	3.67	costa-riquense	5371.805
2228	Dois Irmãos Do Buriti	500348	12	10363	4.42	buritiense	2344.598
2230	Dourados	500370	12	196035	47.97	douradense	4086.244
2232	Fátima Do Sul	500380	12	19035	60.4	fátima-sulense	315.161
2235	Guia Lopes Da Laguna	500410	12	10366	8.56	lagunense	1210.609
2238	Itaporã	500450	12	20865	15.79	itaporanense	1321.817
2240	Ivinhema	500470	12	22341	11.11	ivinhemense	2010.172
2242	Jaraguari	500490	12	6341	2.18	jaraguariense	2912.826
2244	Jateí	500510	12	4011	2.08	jateiense	1927.951
2247	Laguna Carapã	500525	12	6491	3.74	lagunense 	1734.072
2249	Miranda	500560	12	25595	4.67	mirandense	5478.836
2251	Naviraí	500570	12	46424	14.54	naviraiense	3193.549
2253	Nova Alvorada Do Sul	500600	12	16432	4.09	novalvoradense	4019.331
2256	Paranaíba	500630	12	40192	7.44	paranaibano	5402.656
2258	Pedro Gomes	500640	12	7967	2.18	pedro-gomense	3651.179
2260	Porto Murtinho	500690	12	15372	0.87	murtinhense	17744.452
2263	Rio Negro	500730	12	5036	2.79	rio-negrense	1807.67
2265	Rochedo	500750	12	4928	3.16	rochedense	1561.059
2267	São Gabriel Do Oeste	500769	12	22203	5.75	gabrielense	3864.696
2270	Sidrolândia	500790	12	42132	7.97	sidrolandense	5286.416
2272	Tacuru	500795	12	10215	5.72	tacuruense	1785.327
2273	Taquarussu	500797	12	3518	3.38	taquarussuense	1041.124
2275	Três Lagoas	500830	12	101791	9.97	três-lagoense	10207.046
2277	Acorizal	510010	13	5516	6.56	acorizano	840.591
2279	Alta Floresta	510025	13	49164	5.34	alta-florestense	9212.45
2282	Alto Garças	510040	13	10350	2.76	alto-garcense	3748.056
2284	Alto Taquari	510060	13	8072	5.7	taquariense	1416.528
2287	Araguainha	510120	13	1096	1.59	araguainhense	687.968
2289	Arenápolis	510130	13	10316	24.77	arenapolitano	416.448
2291	Barão De Melgaço	510160	13	7591	0.67	melgaciano	11377.273
2293	Barra Do Garças	510180	13	56560	6.23	barra-garcense 	9078.982
2296	Cáceres	510250	13	87942	3.61	cacerense	24351.446
2298	Campo Novo Do Parecis	510263	13	27577	2.92	campo-novense	9434.431
2301	Canabrava Do Norte	510269	13	4786	1.39	canabravense	3452.679
2303	Carlinda	510279	13	10990	5.1	carlindense	2156.748
2305	Chapada Dos Guimarães	510300	13	17821	2.98	chapadense	5983.595
2308	Colíder	510320	13	30766	9.94	colidense	3093.643
2311	Confresa	510335	13	25124	4.33	confresense	5801.377
2312	Conquista D`Oeste	510336	13	3385	1.27	conquistense doeste	2672.211
2315	Curvelândia	510343	13	4866	13.53	curvelandenses	359.762
2317	Diamantino	510350	13	20341	2.47	diamantinense	8230.046
2319	Feliz Natal	510370	13	10933	0.95	feliz-natalenses	11462.366
2322	General Carneiro	510390	13	5027	1.32	general-carneirense	3794.939
2324	Guarantã Do Norte	510410	13	32216	6.8	guarantanhense	4735.331
2326	Indiavaí	510450	13	2397	3.97	indiavaiense	603.295
2329	Itaúba	510455	13	4575	1.01	itaubense	4528.431
2331	Jaciara	510480	13	25647	15.51	jaciarense	1653.542
2333	Jauru	510500	13	10455	8.03	jauruense	1302.113
2336	Juruena	510517	13	11201	3.48	juruenense	3222.65
2338	Lambari D`Oeste	510523	13	5431	3.08	lambarienses	1763.904
2340	Luciára	510530	13	2224	0.52	luciarense	4243.058
2343	Mirassol D`Oeste	510562	13	25299	23.5	miradolense	1076.36
2345	Nortelândia	510600	13	6436	4.77	nortelandense	1348.933
2347	Nova Bandeirantes	510615	13	11643	1.21	nova bandeirantense	9606.256
2350	Nova Guarita	510880	13	4932	4.43	nova  guaritense	1114.126
2352	Nova Marilândia	510885	13	2951	1.52	nova marilandense	1939.798
2354	Nova Monte Verde	510895	13	8093	1.54	nova monte verdense	5248.541
2357	Nova Olímpia	510623	13	17515	11.3	nova-olimpiense	1549.823
2359	Nova Ubiratã	510624	13	9218	0.73	novo-ubiratãenses	12706.164
2361	Novo Horizonte Do Norte	510627	13	3749	4.26	novo-horizontino	879.662
2364	Novo São Joaquim	510628	13	6042	1.2	são-joaquinense	5035.15
2366	Paranatinga	510630	13	19290	0.8	paranatinguense	24166.136
2368	Peixoto De Azevedo	510642	13	30812	2.16	peixotense	14257.26
2371	Pontal Do Araguaia	510665	13	5395	1.97	pontalense	2738.631
2373	Pontes E Lacerda	510675	13	41408	4.84	lacerdense	8559.824
2375	Porto Dos Gaúchos	510680	13	5449	0.78	porto-gauchense	6993.81
2378	Poxoréo	510700	13	17599	2.55	poxoreano 	6910.101
2380	Querência	510706	13	13033	0.73	querenciano	17786.176
2382	Ribeirão Cascalheira	510718	13	8881	0.78	cascalheirense	11354.803
2385	Rondolândia	510757	13	3604	0.28	rondolandense	12670.803
2387	Rosário Oeste	510770	13	17679	2.31	rosariense	7647.978
2390	Santa Cruz Do Xingu	510774	13	1900	0.34	santa-cruzense-do-xingu	5651.731
2392	Santa Terezinha	510777	13	7397	1.14	santa-terezinhense	6467.4
2394	Santo Antônio Do Leste	510779	13	3754	1.04	santo-antoniense-do-leste	3600.707
2396	São Félix Do Araguaia	510785	13	10625	0.64	são-felixcense	16711.854
2397	São José Do Povo	510729	13	3592	8.09	sãojoseenses-do-povo	443.875
2398	São José Do Rio Claro	510730	13	17124	3.77	rio-clarense	4536.203
2400	São José Dos Quatro Marcos	510710	13	18998	14.74	quatro-marquense	1289.09
2404	Sinop	510790	13	113099	28.69	sinopense	3942.224
2406	Tabaporã	510794	13	9932	1.19	tabapoaense	8317.062
2408	Tapurah	510800	13	10392	2.3	tapuraense	4510.646
2411	Torixoréu	510820	13	4071	1.7	torixorino	2399.462
2413	Vale De São Domingos	510835	13	3052	1.58	vale-dominguenses	1932.816
2416	Vila Bela Da Santíssima Trindade	510550	13	14493	1.08	vila-belense	13420.99
2418	Abaetetuba	150010	14	141100	87.61	abaetetubense	1610.603
2420	Acará	150020	14	53569	12.33	acaraense	4343.786
2423	Alenquer	150040	14	52626	2.23	alenquerense	23645.373
2425	Altamira	150060	14	99075	0.62	altamirense	159533.401
2427	Ananindeua	150080	14	471980	2.477	ananindeuense	190.502
2429	Augusto Corrêa	150090	14	40497	37.1	augusto-correense	1091.536
2432	Bagre	150110	14	23864	5.43	bagrense	4397.304
2434	Bannach	150125	14	3431	1.16	bannaquense	2956.641
2437	Belterra	150145	14	16318	3.71	belterrense	4398.407
2439	Bom Jesus Do Tocantins	150157	14	15298	5.43	bom-jesuense	2816.469
2441	Bragança	150170	14	113227	54.13	bragantino	2091.919
2443	Brejo Grande Do Araguaia	150175	14	7317	5.68	brejo-grandense	1288.473
2446	Bujaru	150190	14	25695	25.56	bujaruense	1005.163
2448	Cachoeira Do Piriá	150195	14	26484	10.76	cachoeira-piriaense	2461.961
2451	Capanema	150220	14	63639	103.72	capanemense	613.572
2453	Castanhal	150240	14	173149	168.29	castanhalense	1028.888
2455	Colares	150260	14	11381	18.66	colarense	609.789
2458	Cumaru Do Norte	150276	14	10466	0.61	curaruense 	17084.963
2460	Curralinho	150280	14	28549	7.89	curralinense 	3617.237
2462	Curuçá	150290	14	34294	50.98	curuçaense	672.674
2464	Eldorado Dos Carajás	150295	14	31786	10.75	eldoradense	2956.727
2467	Garrafão Do Norte	150307	14	25034	15.66	garrafaense	1599.021
2470	Igarapé Açu	150320	14	35887	45.66	igarapé-açuense	785.978
2472	Inhangapi	150340	14	10037	21.29	inhangapiense	471.433
2474	Irituia	150350	14	31364	22.74	irituense 	1379.356
2477	Jacareacanga	150375	14	14103	0.26	jacareacanguenses	53303.017
2479	Juruti	150390	14	47086	5.67	jurutiense	8305.125
2481	Mãe Do Rio	150405	14	27904	59.43	mãe-riense	469.489
2483	Marabá	150420	14	233669	15.45	marabaense	15128.368
2486	Marituba	150442	14	108246	1.047	maritubense	103.343
2488	Melgaço	150450	14	24808	3.66	melgacense	6773.969
2490	Moju	150470	14	70018	7.7	mojuense	9094.107
2493	Nova Esperança Do Piriá	150495	14	20158	7.17	piriaense	2809.61
2495	Nova Timboteua	150500	14	13670	27.91	timboteuense	489.85
2497	Novo Repartimento	150506	14	62050	4.03	novo-repartimentense	15398.678
2500	Oriximiná	150530	14	62794	0.58	oriximinaense	107603.221
2502	Ourilândia Do Norte	150543	14	27359	1.91	ourilandense	14339.402
2505	Paragominas	150550	14	97819	5.06	paragominense	19341.858
2507	Pau D`Arco	150555	14	6033	3.61	paudarquense	1671.415
2510	Placas	150565	14	23934	3.34	plaquense	7173.175
2512	Portel	150580	14	52172	2.06	portelense	25384.865
2514	Prainha	150600	14	29349	1.98	prainhense	14786.673
2516	Quatipuru	150611	14	12411	38.28	quatipuruense	324.253
2519	Rondon Do Pará	150618	14	46964	5.7	rondonense	8246.426
2521	Salinópolis	150620	14	37421	157.57	salinopolitano 	237.487
2523	Santa Bárbara Do Pará	150635	14	17141	61.62	santa-barbarense	278.154
2524	Santa Cruz Do Arari	150640	14	8155	7.58	arariense	1075.151
2525	Santa Isabel Do Pará	150650	14	59466	82.86	isabelense	717.658
2528	Santa Maria Do Pará	150660	14	23026	50.31	santa-marianense	457.723
2531	Santarém Novo	150690	14	6141	26.76	santareno	229.509
2533	São Caetano De Odivelas	150710	14	16891	22.72	odivelense	743.454
2535	São Domingos Do Capim	150720	14	29846	17.79	capinense	1677.252
2538	São Geraldo Do Araguaia	150745	14	25587	8.08	são-geraldense	3168.37
2540	São João De Pirabas	150747	14	20647	29.25	pirabense	705.788
2543	São Sebastião Da Boa Vista	150770	14	22904	14.03	boa-vistense	1632.244
2546	Soure	150790	14	23001	6.54	sourense	3517.302
2548	Terra Alta	150796	14	10262	49.72	terraltense	206.413
2551	Tracuateua	150803	14	27455	29.33	tracuateuense	936.125
2553	Tucumã	150808	14	33690	13.41	tucumaense	2512.587
2555	Ulianópolis	150812	14	43341	8.52	ulianopolense	5088.447
2558	Viseu	150830	14	56716	11.54	visinense 	4915.048
2560	Xinguara	150840	14	40573	10.74	xinguarense	3779.348
2562	Aguiar	250020	15	5530	16.04	aguiarense	344.706
2564	Alagoa Nova	250040	15	19681	160.98	alagoa-novense	122.255
2566	Alcantil	250053	15	5239	17.16	alcantilense 	305.392
2569	Amparo	250073	15	2088	17.12	amparense	121.984
2571	Araçagi	250080	15	17224	74.51	araçagiense 	231.154
2573	Araruna	250100	15	18879	76.83	ararunense	245.722
2575	Areia De Baraúnas	250115	15	1927	20	baraunense	96.343
2578	Assunção	250135	15	3522	27.86	assunçãoense	126.427
2580	Bananeiras	250150	15	21851	84.72	bananeirense	257.93
2582	Barra De Santa Rosa	250160	15	14157	18.25	santa rosense	775.654
2585	Bayeux	250180	15	99716	3.118	baienense	31.973
2587	Belém Do Brejo Do Cruz	250200	15	7143	11.84	belenense do brejo do cruz 	603.04
2590	Boa Vista	250215	15	6227	13.07	boavistense	476.538
2592	Bom Sucesso	250230	15	5035	27.35	bom-sucessense	184.101
2594	Boqueirão	250250	15	16888	45.4	boqueirãoense	371.982
2597	Brejo Dos Santos	250290	15	6198	66.05	brejo-santense	93.845
2599	Cabaceiras	250310	15	5035	11.12	cabaceirense	452.92
2601	Cachoeira Dos Índios	250330	15	9546	49.44	cachoeirense (dos Índios)	193.067
2604	Cacimbas	250355	15	6814	53.85	cacimbense	126.542
2606	Cajazeiras	250370	15	58446	103.28	cajazeirense	565.896
2608	Caldas Brandão	250380	15	5637	100.92	caldas-brandense	55.854
2611	Capim	250403	15	5601	71.66	capiense	78.166
2613	Carrapateira	250410	15	2378	43.61	carrapateirense	54.523
2615	Catingueira	250420	15	4812	9.09	catingueirense	529.451
2618	Conceição	250440	15	18363	31.69	conceiçãoense	579.432
2620	Conde	250460	15	21400	123.74	condense	172.949
2622	Coremas	250480	15	15149	39.92	coremense	379.491
2624	Cruz Do Espírito Santo	250490	15	16257	83.12	Santo espírito-santense 	195.595
2627	Cuité De Mamanguape	250523	15	6202	57.19	cuiteense	108.448
2629	Curral De Cima	250527	15	5209	61.21	curralense de cima	85.096
2632	Desterro	250540	15	7991	44.55	desterrense	179.386
2635	Duas Estradas	250580	15	3638	138.53	duas-estradense	26.261
2637	Esperança	250600	15	31095	189.86	esperancense	163.78
2639	Frei Martinho	250620	15	2933	12	frei-martinhense	244.316
2642	Gurinhém	250640	15	13872	40.08	gurinheense 	346.065
2644	Ibiara	250660	15	6031	24.67	ibiarense	244.484
2646	Imaculada	250670	15	11352	35.81	imaculadense	316.982
2649	Itaporanga	250700	15	23192	49.55	itaporanguense	468.057
2650	Itapororoca	250710	15	16997	116.37	itapororoquense	146.066
2651	Itatuba	250720	15	10201	41.77	itatubense	244.221
2653	Jericó	250740	15	7538	42.04	jericoense	179.31
2655	Joca Claudino	251365	15	2615	35.33	Joca-Claudinense	74.006
2658	Junco Do Seridó	250780	15	6643	38.98	juncoense	170.419
2661	Lagoa	250810	15	4681	26.31	lagoense	177.901
2663	Lagoa Seca	250830	15	25900	240.73	lagoa-sequense	107.589
2665	Livramento	250850	15	7164	27.53	livramentense	260.219
2667	Lucena	250860	15	11730	131.88	lucenense	88.943
2670	Mamanguape	250890	15	42303	124.23	mamanguapense	340.531
2672	Marcação	250905	15	7609	61.91	marcaçãoense	122.895
2674	Marizópolis	250915	15	6173	97.04	marizopolense	63.61
2677	Matinhas	250933	15	4321	113.34	matinhense	38.123
2679	Maturéia	250939	15	5939	70.97	matureense	83.687
2681	Montadas	250950	15	4990	157.98	montadense	31.587
2684	Mulungu	250980	15	9469	48.48	mulunguense	195.313
2686	Nazarezinho	251000	15	7280	38.02	nazarezinhense	191.486
2688	Nova Olinda	251020	15	6070	72.04	nova-olindense	84.253
2690	Olho D`Água	251040	15	6931	11.63	olho-daguense	596.126
2692	Ouro Velho	251060	15	2928	22.63	ouro-velhense	129.399
2695	Patos	251080	15	100674	212.82	patense	473.054
2697	Pedra Branca	251100	15	3721	32.95	pedra-branquense	112.932
2699	Pedras De Fogo	251120	15	27032	67.51	pedras-foguense	400.388
2702	Picuí	251140	15	18222	27.54	picuíense	661.654
2704	Pilões	251160	15	6978	108.28	piloense 	64.446
2706	Pirpirituba	251180	15	10326	129.33	pirpiritubense	79.844
2709	Poço Dantas	251203	15	3751	38.57	poçodantense	97.249
2711	Pombal	251210	15	32110	36.13	pombalense	888.802
2738	São Domingos	251396	15	2855	16.88	sãodominguense	169.104
2741	São João Do Cariri	251400	15	4344	6.65	caririense 	653.598
2744	São José Da Lagoa Tapada	251420	15	7564	22.13	são-joseense	341.804
2746	São José De Espinharas	251440	15	4760	6.56	espinharense	725.652
2749	São José Do Bonfim	251460	15	3233	24	bonfinense	134.723
2752	São José Dos Cordeiros	251480	15	3985	9.54	são-joseense (dos Cordeiros)	417.743
2755	São Miguel De Taipu	251500	15	6696	72.37	taipuense	92.526
2757	São Sebastião Do Umbuzeiro	251520	15	3235	7.02	são-sebastianense	460.571
2760	Serra Branca	251550	15	12973	18.89	serra-branquense	686.911
2763	Serra Redonda	251580	15	7050	126.11	serra-redondense	55.905
2765	Sertãozinho	251593	15	4395	134	sertãozienhense	32.798
2768	Soledade	251610	15	13739	24.53	soledadense	560.039
2770	Sousa	251620	15	65803	89.1	sousense	738.543
2772	Tacima	251640	15	10262	41.6	tacimense	246.657
2775	Teixeira	251670	15	14153	87.96	teixeirense	160.899
2776	Tenório	251675	15	2813	26.72	tenorense	105.27
2778	Uiraúna	251690	15	14584	49.52	uiraunense	294.497
2781	Vieirópolis	251720	15	5045	34.37	vieiropolense	146.778
2783	Zabelê	251740	15	2075	18.97	zabeleense	109.394
2785	Afogados Da Ingazeira	260010	16	35088	92.9	afogadense	377.694
2788	Água Preta	260040	16	33095	62.05	água-pretense	533.328
2790	Alagoinha	260060	16	13759	63.16	alagoinhense	217.827
2792	Altinho	260080	16	22353	49.18	altinense	454.482
2795	Araçoiaba	260105	16	18156	196.74	araçoiabense	92.282
2797	Arcoverde	260120	16	68793	196.05	arcoverdense	350.899
2799	Barreiros	260140	16	40732	174.54	barreirense	233.372
2801	Belém De São Francisco	260160	16	20253	11.06	belenense	1830.793
2804	Bezerros	260190	16	58668	119.53	bezerrense	490.815
2806	Bom Conselho	260210	16	45503	57.44	conselhense	792.182
2809	Brejão	260240	16	8844	55.35	brejonense	159.786
2811	Brejo Da Madre De Deus	260260	16	45180	59.26	brejense	762.377
2814	Cabo De Santo Agostinho	260290	16	185025	414.32	cabense	446.578
2816	Cachoeirinha	260310	16	18819	104.98	cachoeirinhense	179.261
2819	Calumbi	260340	16	5648	31.5	calumbiense	179.314
2821	Camocim De São Félix	260350	16	17104	236	camocinense 	72.476
2824	Capoeiras	260380	16	19593	58.26	capoeirense	336.311
2826	Carnaubeira Da Penha	260392	16	11782	11.73	carnaubeirense	1004.662
2829	Casinhas	260415	16	13766	118.81	casinhense	115.867
2831	Cedro	260430	16	10778	62.79	cedrense	171.64
2833	Chã Grande	260450	16	20137	237.33	chã-grandense	84.848
2835	Correntes	260470	16	17419	53	correntense 	328.653
2838	Cupira	260500	16	23390	221.58	cupirense 	105.559
2840	Dormentes	260515	16	16917	11	dormentense	1537.636
2842	Exu	260530	16	31636	23.65	exuense 	1337.489
2844	Fernando De Noronha	260545	16	2630	154.55	noronhense	17.017
2847	Floresta	260570	16	29285	8.04	florestano	3644.15
2849	Gameleira	260590	16	27912	109.05	gameleirense	255.96
2851	Glória Do Goitá	260610	16	29019	125.17	gloriense	231.831
2854	Gravatá	260640	16	76458	151.36	gravataense	505.137
2856	Ibimirim	260660	16	26954	13.79	ibimiriense	1954.705
2858	Igarassu	260680	16	102021	333.88	igarassuano 	305.559
2860	Ilha De Itamaracá	260760	16	21884	328.18	itamaracaense	66.683
2874	Jatobá	260805	16	13963	50.25	jatobaense	277.861
2876	Joaquim Nabuco	260820	16	15773	129.39	nabuquense	121.901
2879	Jurema	260840	16	14541	98.08	juremense 	148.253
2881	Lagoa Do Itaenga	260850	16	20659	360.65	itaenguense	57.282
2883	Lagoa Dos Gatos	260870	16	15615	70.06	lagoense 	222.869
2886	Limoeiro	260890	16	55439	202.53	limoeirense	273.737
2888	Machados	260910	16	13596	226.46	machadense	60.036
2890	Maraial	260920	16	12230	61.19	maraialense	199.864
2892	Moreilândia	261430	16	11132	27.52	moreirense	404.57
2895	Olinda	260960	16	377779	9.068	olindense	41.659
2897	Orocó	260980	16	13180	23.76	orocoense	554.757
2900	Palmeirina	261010	16	8189	51.82	palmeirinense	158.02
2902	Paranatama	261030	16	11001	47.65	paranatamense	230.887
2904	Passira	261050	16	28628	87.61	passirense	326.756
2906	Paulista	261070	16	300466	3.086	paulistano	97.364
2907	Pedra	261080	16	20944	26.08	pedrense	803.065
2910	Petrolina	261110	16	293962	64.49	petrolinense	4558.398
2912	Pombos	261130	16	24046	117.84	pomboense	204.052
2914	Quipapá	261150	16	24186	104.88	quipapaense 	230.616
2917	Riacho Das Almas	261170	16	19162	61.03	riachense	314.001
2919	Rio Formoso	261190	16	22151	97.39	rio-formosense	227.457
2921	Salgadinho	261210	16	9312	104.84	salgadinense	88.817
2924	Sanharó	261240	16	21955	81.71	sanharoense 	268.685
2926	Santa Cruz Da Baixa Verde	261247	16	11768	102.39	santacruzense	114.931
2928	Santa Filomena	261255	16	13371	13.3	filomense	1005.04
2931	Santa Terezinha	261280	16	10991	56.2	santa-terezinhense	195.585
2933	São Bento Do Una	261300	16	53242	74.03	são-bentense	719.16
2936	São Joaquim Do Monte	261330	16	20488	88.39	são-joaquinense	231.803
2938	São José Do Belmonte	261350	16	32617	22.13	belmontense	1474.078
2940	São Lourenço Da Mata	261370	16	102895	392.49	são-lourensense	262.157
2943	Serrita	261400	16	18331	12.1	serritense	1514.37
2945	Sirinhaém	261420	16	40296	109.18	sirinhaense 	369.069
2948	Tabira	261460	16	26427	68.11	tabirense	388.003
2950	Tacaratu	261480	16	22068	17.45	tacaratuense 	1264.525
2952	Taquaritinga Do Norte	261500	16	24903	52.41	taquaritinguense 	475.181
2955	Timbaúba	261530	16	53825	184.16	timbaubense	292.28
2957	Tracunhaém	261550	16	13055	110.27	tracunhaense	118.388
2959	Triunfo	261570	16	15006	78.35	triunfense	191.517
2962	Venturosa	261600	16	16052	50.05	venturosense	320.73
2964	Vertente Do Lério	261618	16	7873	106.93	vertentense do lério	73.63
2966	Vicência	261630	16	30732	134.78	vicenciense	228.016
2969	Acauã	220005	17	6749	5.27	acauãnense	1279.58
2971	Água Branca	220020	17	16451	169.53	água-branquense	97.04
2973	Alegrete Do Piauí	220027	17	5153	18.23	alegretense	282.709
2976	Alvorada Do Gurguéia	220045	17	5050	2.37	alvoradense	2131.951
2978	Angical Do Piauí	220060	17	6672	29.86	angicalense	223.434
2980	Antônio Almeida	220080	17	3039	4.71	antônio-almeidense	645.742
2983	Arraial	220100	17	4688	6.87	arraialense	682.757
2986	Baixa Grande Do Ribeiro	220115	17	10516	1.35	baixagrandense do ribeiro	7808.878
2988	Barras	220120	17	44850	26.08	barrense	1719.789
2990	Barro Duro	220140	17	6607	50.39	barro-durense	131.119
2992	Bela Vista Do Piauí	220155	17	3778	7.57	bela  vistense	499.391
2995	Bertolínia	220170	17	5319	4.34	bertolinense	1225.331
2998	Bocaina	220180	17	4369	16.27	bocainense	268.575
3000	Bom Princípio Do Piauí	220191	17	5304	10.17	bomprincipiense	521.585
3002	Boqueirão Do Piauí	220194	17	6193	22.25	boqueirãoense	278.291
3005	Buriti Dos Lopes	220200	17	19074	27.6	buritiense	691.158
3007	Cabeceiras Do Piauí	220205	17	9928	16.31	cabeceirense	608.526
3010	Caldeirão Grande Do Piauí	220209	17	5671	11.46	caldeirão grandense	494.889
3013	Campo Grande Do Piauí	220213	17	5592	17.93	campo grandense	311.827
3016	Canavieira	220225	17	3921	1.81	canavieirense	2162.865
3017	Canto Do Buriti	220230	17	20020	4.63	canto-buritiense	4325.622
3020	Caracol	220250	17	10212	6.34	caracolense	1610.951
3022	Caridade Do Piauí	220255	17	4826	9.63	caridadense	501.326
3025	Cocal	220270	17	26036	20.51	cocalense	1269.491
3027	Cocal Dos Alves	220272	17	5572	15.58	cocalalvense	357.687
3029	Colônia Do Gurguéia	220275	17	6036	14.02	coloniense	430.619
3030	Colônia Do Piauí	220277	17	7433	7.84	coloniense	947.881
3031	Conceição Do Canindé	220280	17	4475	5.38	conceiçãonense	831.408
3034	Cristalândia Do Piauí	220300	17	7831	6.51	cristalandense	1202.891
3036	Curimatá	220320	17	10761	4.6	curimataense	2337.529
3038	Curral Novo Do Piauí	220327	17	4869	6.47	curral novense	752.308
3041	Dirceu Arcoverde	220335	17	6675	6.56	arcoverdense	1017.053
3043	Dom Inocêncio	220345	17	9245	2.39	inocentino	3870.151
3045	Elesbão Veloso	220350	17	14512	10.77	elesbonense	1347.477
3048	Fartura Do Piauí	220375	17	5074	7.12	farturense	712.915
3050	Floresta Do Piauí	220385	17	2482	12.75	florestense	194.698
3052	Francinópolis	220400	17	5235	19.48	francinopolitano	268.7
3055	Francisco Santos	220420	17	8592	17.47	francisco-santense	491.86
3057	Geminiano	220435	17	5475	11.84	geminianense	462.521
3060	Guaribas	220455	17	4401	1.41	guaribano	3118.216
3062	Ilha Grande	220465	17	8914	66.37	ilhagrandense	134.317
3064	Ipiranga Do Piauí	220480	17	9327	17.67	ipiranguense	527.724
3066	Itainópolis	220500	17	11109	13.41	itainopolense	828.147
3069	Jaicós	220520	17	18035	20.85	jaicoense	865.14
3071	Jatobá Do Piauí	220527	17	4656	7.13	jatobaense	653.231
3074	Joaquim Pires	220540	17	13817	18.68	joaquim-pirense	739.575
3076	José De Freitas	220550	17	37085	24.11	freitense	1538.168
3078	Júlio Borges	220552	17	5373	4.14	julio borgense	1297.104
3081	Lagoa De São Francisco	220557	17	6422	41.26	lagoense	155.638
3083	Lagoa Do Piauí	220558	17	3863	9.05	lagoense	426.632
3086	Landri Sales	220560	17	5281	4.85	landri-salesiano	1088.579
3088	Luzilândia	220580	17	24721	35.1	luzilandense	704.343
3090	Manoel Emídio	220590	17	5213	3.22	manoel-emidense	1618.976
3093	Massapê Do Piauí	220605	17	6220	11.94	massapêense	521.123
3095	Miguel Alves	220620	17	32289	23.17	miguel-alvense	1393.707
3097	Milton Brandão	220635	17	6769	4.93	milton brandãoense	1371.736
3100	Monte Alegre Do Piauí	220660	17	10345	4.28	montealegrense	2417.924
3102	Morro Do Chapéu Do Piauí	220667	17	6499	19.8	morrochapeuense	328.287
3105	Nazária	220672	17	8068	22.19		363.588
3107	Nossa Senhora Dos Remédios	220680	17	8206	22.89	remediense	358.49
3110	Novo Santo Antônio	220695	17	3260	6.77	santantoniense	481.705
3112	Olho D`Água Do Piauí	220710	17	2626	11.96	olho daguense	219.597
3115	Pajeú Do Piauí	220735	17	3363	3.12	pajeuense	1079.168
3118	Paquetá	220755	17	4147	9.25	paquetaense	448.456
3120	Parnaíba	220770	17	145705	334.52	parnaíbano	435.57
3122	Patos Do Piauí	220777	17	6105	8.12	patoense	751.594
3124	Paulistana	220780	17	19785	10.04	paulistanense	1969.946
3127	Pedro Laurentino	220793	17	2407	2.77	pedro laurentinense	870.334
3129	Pimenteiras	220810	17	11733	2.57	pimenteirense	4563.103
3131	Piracuruca	220830	17	27553	11.57	piracuruquense	2380.401
3134	Porto Alegre Do Piauí	220855	17	2559	2.19	porto  alegrense	1169.439
3136	Queimada Nova	220865	17	8553	6.32	queimadanovense	1352.392
3138	Regeneração	220880	17	17556	14.03	regenerense	1251.029
3141	Ribeiro Gonçalves	220890	17	6845	1.72	ribeiro-gonçalvino	3978.946
3143	Santa Cruz Do Piauí	220910	17	6027	9.85	santa-cruzense	611.614
3146	Santa Luz	220930	17	5513	4.65	santa-luzense	1186.839
3148	Santana Do Piauí	220935	17	4917	34.84	santanense	141.117
3150	Santo Antônio Dos Milagres	220945	17	2059	62.12	santoantonhense	33.147
3151	Santo Inácio Do Piauí	220950	17	3648	4.28	santinacense	852.876
3153	São Félix Do Piauí	220960	17	3069	4.67	são-felicense	657.241
3155	São Francisco Do Piauí	220970	17	6298	4.7	são-franciscano	1340.659
3157	São Gonçalo Do Piauí	220980	17	4754	31.65	são-gonçalense	150.215
3160	São João Da Serra	220990	17	6157	6.12	serra-jonense	1006.495
3162	São João Do Arraial	220997	17	7336	34.38	são jãoense	213.354
3165	São José Do Peixe	221010	17	3700	2.87	são-joseense	1287.168
3168	São Lourenço Do Piauí	221035	17	4427	6.58	lourenciano	672.706
3170	São Miguel Da Baixa Grande	221038	17	2110	5.49	sãomiquelense	384.19
3173	São Pedro Do Piauí	221050	17	13639	26.32	são-pedrense	518.285
3175	Sebastião Barros	221062	17	3560	3.98	sebastião barrense	893.712
3178	Simões	221070	17	14180	13.23	simonense	1071.532
3180	Socorro Do Piauí	221090	17	4522	5.94	socorrense	761.85
3182	Tamboril Do Piauí	221095	17	2753	1.73	tamborilense	1587.29
3185	União	221110	17	42654	36.35	unionense	1173.441
3187	Valença Do Piauí	221130	17	20326	15.23	valenciano	1334.623
3189	Várzea Grande	221140	17	4336	18.29	várzea-grandense	237.012
3192	Wall Ferraz	221170	17	4280	15.85	wal farrazense	269.986
3194	Adrianópolis	410020	18	6376	4.73	adrianopolitano 	1349.335
3196	Almirante Tamandaré	410040	18	103204	529.94	tamandareense	194.746
3199	Alto Paraná	410060	18	13663	33.51	alto-paranaense	407.72
3202	Alvorada Do Sul	410080	18	10283	24.24	alvoradense-do-sul	424.25
3204	Ampére	410100	18	17308	58.01	amperense	298.35
3206	Andirá	410110	18	20610	87.3	andiraense	236.075
3209	Antônio Olinto	410130	18	7351	15.65	antoniolintense 	469.759
3211	Arapongas	410150	18	104150	273.3	araponguense	381.081
3213	Arapuã	410165	18	3561	16.34	arapuãense	217.974
3216	Ariranha Do Ivaí	410185	18	2453	10.24	ariranhense do ivaí	239.563
3218	Assis Chateaubriand	410200	18	33025	34.06	assis-chateaubriense 	969.589
3220	Atalaia	410220	18	3913	28.42	atalaiense	137.664
3223	Barbosa Ferraz	410250	18	12656	23.5	barbosense	538.637
3225	Barracão	410260	18	9735	56.67	barraconense	171.77
3227	Bela Vista Do Paraíso	410280	18	15079	62.13	bela-vistense	242.69
3230	Boa Esperança Do Iguaçu	410302	18	2764	18.21	boaesperencense	151.798
3232	Boa Vista Da Aparecida	410305	18	7911	30.87	boa-vistense	256.298
3235	Bom Sucesso	410320	18	6561	20.33	bom-sucessense	322.756
3237	Borrazópolis	410330	18	7878	23.56	borrazopolitano	334.378
3240	Cafeara	410340	18	2695	14.5	cafearense	185.8
3242	Cafezal Do Sul	410347	18	4290	12.79	cafezalense	335.393
3244	Cambará	410360	18	23886	65.23	cambaraense	366.175
3247	Campina Da Lagoa	410390	18	15394	19.32	campinense-da-lagoa	796.616
3249	Campina Grande Do Sul	410400	18	38769	71.93	campinense-do-sul	538.974
3251	Campo Do Tenente	410410	18	7125	23.4	tenentiano 	304.489
3254	Campo Mourão	410430	18	87194	115.05	campo-mourense	757.876
3256	Candói	410442	18	14983	9.9	candoianos	1512.79
3259	Capitão Leônidas Marques	410460	18	14970	54.29	leônidas-marquesiense 	275.748
3261	Carlópolis	410470	18	13706	30.36	carlopolitano 	451.418
3264	Catanduvas	410500	18	10202	17.54	catanduvense	581.757
3266	Cerro Azul	410520	18	16938	12.63	cerro-azulense	1341.192
3268	Chopinzinho	410540	18	19679	20.51	chopinzinhense	959.695
3270	Cidade Gaúcha	410560	18	11062	27.45	cidade-gauchense 	403.046
3274	Congonhinhas	410600	18	8279	15.45	congonhinhense	535.964
3275	Conselheiro Mairinck	410610	18	3636	17.76	mairinquense	204.706
3277	Corbélia	410630	18	16312	30.81	corbeliano 	529.386
3279	Coronel Domingos Soares	410645	18	7238	4.59	dominguense	1576.225
3282	Cruz Machado	410680	18	18040	12.2	cruz-machadense	1478.354
3284	Cruzeiro Do Oeste	410660	18	20416	26.2	cruzeirense 	779.226
3287	Curitiba	410690	18	1751907	4.024	curitibano	435.274
3289	Diamante D`Oeste	410715	18	5027	16.26	sul-diamantino 	309.111
3291	Diamante Do Sul	410712	18	3510	9.75	diamantense 	359.947
3294	Doutor Camargo	410730	18	5828	49.27	camarguense	118.279
3296	Enéas Marques	410740	18	6103	31.75	enéas-marquense	192.204
3299	Esperança Nova	410752	18	1970	14.22	esperançanovense	138.561
3301	Farol	410755	18	3472	12	farolense	289.233
3303	Fazenda Rio Grande	410765	18	81675	700.02	fazendense	116.676
3306	Figueira	410775	18	8293	63.91	figueirense	129.77
3308	Floraí	410780	18	5050	26.42	floraiense	191.134
3310	Florestópolis	410800	18	11222	45.56	florestopolitano	246.331
3312	Formosa Do Oeste	410820	18	7541	27.35	formosense-do-oeste 	275.712
3315	Francisco Alves	410832	18	6418	19.94	alvense	321.899
3317	General Carneiro	410850	18	13669	12.77	carneirense	1070.256
3320	Goioxim	410865	18	7503	10.68	goioxinhense	702.472
3322	Guaíra	410880	18	30704	54.78	guairense	560.487
3324	Guamiranga	410895	18	7900	32.27	guamiranguense	244.795
3327	Guaraci	410920	18	5227	24.69	guaraciense	211.719
3329	Guarapuava	410940	18	167328	53.69	guarapuavano	3116.313
3331	Guaratuba	410960	18	32095	24.21	guaratubense 	1325.909
3333	Ibaiti	410970	18	28751	32.03	ibaitiense	897.737
3336	Icaraíma	410990	18	8839	13.09	icaraimense	675.242
3338	Iguatu	411005	18	2234	20.89	iguatuense	106.938
3340	Imbituva	411010	18	28455	37.61	imbituvense	756.536
3342	Inajá	411030	18	2988	15.35	inajaense	194.705
3345	Iporã	411060	18	14981	23.12	iporãnense	647.896
3347	Irati	411070	18	56207	56.23	iratiense	999.519
3349	Itaguajé	411090	18	4568	24	itaguajeense	190.371
3351	Itambaracá	411100	18	6759	32.6	itambaracaense	207.343
3353	Itapejara D`Oeste	411120	18	10531	41.46	itapejarense	254.015
3356	Ivaí	411140	18	12815	21.08	ivaiense	607.849
3358	Ivaté	411155	18	7514	18.29	ivateense	410.908
3361	Jacarezinho	411180	18	39121	64.93	jacarezinhense	602.529
3363	Jaguariaíva	411200	18	32606	22.44	jaguariaivense	1453.069
3365	Janiópolis	411220	18	6532	19.46	janiopolitano	335.651
3367	Japurá	411240	18	8549	51.75	japuraense	165.185
3369	Jardim Olinda	411260	18	1409	10.96	jardinolindense	128.515
3372	Joaquim Távora	411280	18	10736	37.13	tavorense	289.173
3374	Juranda	411295	18	7641	21.85	jurandense	349.723
3377	Lapa	411320	18	44932	21.46	lapeano	2093.832
3379	Laranjeiras Do Sul	411330	18	30777	45.79	laranjeirense-do-sul	672.086
3381	Lidianópolis	411342	18	3973	25.02	lidianopolitano	158.806
3384	Lobato	411360	18	4401	18.27	lobatense	240.905
3386	Luiziana	411373	18	7315	8.05	luizianense	908.603
3388	Lupionópolis	411380	18	4592	37.93	lupionopolense 	121.066
3391	Mandaguaçu	411410	18	19781	67.28	mandaguaçuense	294.02
3393	Mandirituba	411430	18	22220	58.6	mandiritubano 	379.179
3395	Mangueirinha	411440	18	17048	16.15	mangueirinhense	1055.461
3397	Marechal Cândido Rondon	411460	18	46819	62.59	rondonense	748.004
3400	Marilândia Do Sul	411490	18	8863	23.06	marilandense	384.425
3402	Mariluz	411510	18	10224	23.6	mariluzense	433.171
3403	Maringá	411520	18	357077	732.12	maringaense	487.73
3405	Maripá	411535	18	5684	20.03	maripaense	283.794
3407	Marquinho	411545	18	4981	9.74	marquinhense	511.149
3410	Matinhos	411570	18	29428	249.93	matinhense	117.743
3412	Mauá Da Serra	411575	18	8555	78.98	mauaense da serra	108.325
3414	Mercedes	411585	18	5046	25.12	mercedense	200.865
3417	Missal	411605	18	10474	32.29	missalense	324.398
3419	Morretes	411620	18	15718	22.96	morretense 	684.582
3421	Nossa Senhora Das Graças	411640	18	3836	20.65	gracense	185.731
3423	Nova América Da Colina	411660	18	3478	26.86	nova-americanense	129.476
3426	Nova Esperança	411690	18	26615	66.27	nova-esperancense	401.588
3428	Nova Fátima	411700	18	8147	28.75	fatimense	283.423
3430	Nova Londrina	411710	18	13067	48.51	nova-londrinense	269.389
3433	Nova Santa Bárbara	411721	18	3908	54.46	bárbaraense	71.764
3436	Novo Itacolomi	411729	18	2827	17.51	itacolomiense	161.411
3438	Ourizona	411740	18	3380	19.15	ourizonense	176.457
3440	Paiçandu	411750	18	35936	210.35	paiçanduense	170.838
3443	Palmital	411780	18	14865	18.18	palmitalense	817.649
3445	Paraíso Do Norte	411800	18	11772	57.55	paraisense-do-norte 	204.564
3447	Paranaguá	411820	18	140469	169.92	parnanguara	826.676
3450	Pato Bragado	411845	18	4822	35.64	pato bragadense	135.286
3452	Paula Freitas	411860	18	5434	12.93	paula-freitense 	420.327
3454	Peabiru	411880	18	13624	29.07	peabiruense	468.596
3457	Pérola D`Oeste	411900	18	6761	32.81	pérola-oestense 	206.048
3459	Pinhais	411915	18	117008	1.926	pinhaense	60.749
3461	Pinhalão	411920	18	6215	28.17	pinhalense ou pinhalãoense	220.626
3464	Piraquara	411950	18	93207	410.54	piraquarense	227.033
3466	Pitangueiras	411965	18	2814	22.84	pitangueirense	123.23
3468	Planalto	411980	18	13654	39.49	planaltense 	345.74
3471	Porecatu	412000	18	14189	48.65	porecatuense	291.665
3473	Porto Barreiro	412015	18	3663	10.15	porto barreirense	361.021
3475	Porto Vitória	412030	18	4020	18.93	porto-vitoriense	212.388
3478	Presidente Castelo Branco	412040	18	4784	30.72	castelo-branquense 	155.734
3480	Prudentópolis	412060	18	48792	21.14	prudentopolitano	2308.505
3482	Quatiguá	412070	18	7045	62.52	quatiguaense	112.689
3484	Quatro Pontes	412085	18	3803	33.25	quatro pontense	114.393
3487	Quinta Do Sol	412110	18	5088	15.6	quinta-solense 	326.178
3489	Ramilândia	412125	18	4134	17.43	ramilandiense	237.196
3491	Rancho Alegre D`Oeste	412135	18	2847	11.79	rancho alegrense	241.387
3494	Renascença	412160	18	6812	16.03	renascenseano 	425.085
3496	Reserva Do Iguaçu	412175	18	7307	8.76	reservense do iguçú	834.234
3498	Ribeirão Do Pinhal	412190	18	13524	36.09	ribeiro-pinhalense	374.733
3501	Rio Bonito Do Iguaçu	412215	18	13661	18.31	rio bonitense	746.123
3504	Rio Negro	412230	18	31274	51.84	rio-negrense	603.248
3506	Roncador	412250	18	11537	15.55	roncadorense 	742.123
3508	Rosário Do Ivaí	412265	18	5588	15.05	rosariense	371.251
3511	Salto Do Itararé	412290	18	5178	25.82	saltense-do-itararé 	200.519
3513	Santa Amélia	412310	18	3803	48.73	ameliense	78.045
3515	Santa Cruz De Monte Castelo	412330	18	8092	18.31	monte-castelense	442.014
3518	Santa Inês	412360	18	1818	13.13	santa-ineense	138.481
3520	Santa Izabel Do Oeste	412380	18	13132	40.89	santa-izabelense 	321.183
3523	Santa Mariana	412390	18	12435	29.11	santa-marianense	427.194
3525	Santa Tereza Do Oeste	412402	18	10332	31.67	santa-terezense 	326.191
3527	Santana Do Itararé	412400	18	5249	20.89	santanense	251.267
3528	Santo Antônio Da Platina	412410	18	42707	59.19	platinense	721.473
3531	Santo Antônio Do Sudoeste	412440	18	18893	58	santo-antoniense	325.744
3533	São Carlos Do Ivaí	412460	18	6354	28.23	são-carlense	225.078
3536	São João Do Caiuá	412490	18	5911	19.42	caiuense 	304.414
3539	São Jorge D`Oeste	412520	18	9085	23.94	são-jorgense ou jorgense	379.546
3541	São Jorge Do Patrocínio	412535	18	6041	14.93	patrocinense	404.691
3544	São José Dos Pinhais	412550	18	264210	279.16	são-joseense	946.443
3547	São Miguel Do Iguaçu	412570	18	25769	30.27	são-miguelense	851.304
3549	São Pedro Do Ivaí	412580	18	10167	31.51	ivaiense 	322.693
3551	São Sebastião Da Amoreira	412600	18	8626	37.84	amoreirense	227.982
3554	Sarandi	412625	18	82847	801.79	sarandiense	103.327
3557	Serranópolis Do Iguaçu	412635	18	4568	9.44	serranopolitano	483.659
3559	Sertanópolis	412650	18	15638	30.93	sertanopolense	505.533
3562	Tamarana	412667	18	12262	25.97	tamaraense	472.156
3564	Tapejara	412680	18	14598	24.68	tapejarense	591.4
3566	Teixeira Soares	412700	18	10283	11.39	teixeira-soarense	902.795
3569	Terra Rica	412730	18	15221	21.73	terra-riquense	700.588
3571	Tibagi	412750	18	19344	6.55	tibagiense	2951.573
3573	Toledo	412770	18	119313	99.68	toledense	1197.002
3575	Três Barras Do Paraná	412785	18	11824	23.45	tribarrense	504.172
3578	Tupãssi	412795	18	7997	25.72	tupãciense	310.91
3580	Ubiratã	412800	18	21558	33.03	ubiratãense	652.583
3582	União Da Vitória	412820	18	52735	73.22	união-vitoriense	720.207
3585	Ventania	412853	18	9957	13.11	ventaniense	759.368
3587	Verê	412860	18	7878	25.27	vereense	311.802
3590	Wenceslau Braz	412850	18	19298	48.5	brazense 	397.917
3592	Angra Dos Reis	330010	19	169511	205.45	angrense	825.088
3595	Areal	330022	19	11423	102.99	arealense	110.919
3597	Arraial Do Cabo	330025	19	27715	172.91	cabista	160.286
3599	Barra Mansa	330040	19	177813	324.94	barra-mansense	547.226
3602	Bom Jesus Do Itabapoana	330060	19	35411	59.13	bom-jesuense	598.824
3604	Cachoeiras De Macacu	330080	19	54273	56.9	cachoeirense	953.801
3607	Cantagalo	330110	19	19830	26.47	cantagalense	749.278
3609	Cardoso Moreira	330115	19	12600	24.02	cardosense	524.633
3612	Comendador Levy Gasparian	330095	19	8180	76.53	gaspariense	106.888
3614	Cordeiro	330150	19	20430	175.59	cordeirense	116.349
3616	Duque De Caxias	330170	19	855048	1.828	caxiense	467.619
3619	Iguaba Grande	330187	19	22851	439.91	iguabense	51.945
3622	Italva	330205	19	14063	47.86	italvense	293.82
3624	Itaperuna	330220	19	95841	86.71	itaperunense	1105.341
3626	Japeri	330227	19	95492	1.166	japeriense	81.871
3629	Macuco	330245	19	5269	67.8	macuquense	77.719
3631	Mangaratiba	330260	19	36456	103.25	mangaratibano	353.083
3633	Mendes	330280	19	17935	184.83	mendense	97.035
3635	Miguel Pereira	330290	19	24642	85.21	miguelense	289.183
3638	Nilópolis	330320	19	157425	8.117	nilopolitano	19.393
3640	Nova Friburgo	330340	19	182082	195.07	friburguense	933.414
3643	Paraíba Do Sul	330370	19	41084	70.77	sul-paraibano 	580.525
3645	Paty Do Alferes	330385	19	26359	82.68	patiense	318.801
3647	Pinheiral	330395	19	22719	296.86	pinheiralense	76.53
3650	Porto Real	330411	19	16592	326.95	porto realense	50.748
3651	Quatis	330412	19	12793	44.72	quatiense	286.093
3652	Queimados	330414	19	137962	1.822	queimadense	75.695
3654	Resende	330420	19	119769	109.35	resendense	1095.254
3656	Rio Claro	330440	19	17425	20.73	rio-clarense	840.59
3658	Rio Das Ostras	330452	19	105676	461.38	rio ostrense	229.043
3661	Santo Antônio De Pádua	330470	19	40589	67.27	paduano	603.355
3663	São Francisco De Itabapoana	330475	19	41354	36.84	são franciscano	1122.437
3666	São João De Meriti	330510	19	458673	13.024	meritiense	35.216
3669	São Pedro Da Aldeia	330520	19	87875	264.05	aldeiense	332.792
3672	Saquarema	330550	19	74234	209.96	saquaremense	353.566
3674	Silva Jardim	330560	19	21349	22.77	silva-jardinense	937.547
3677	Teresópolis	330580	19	163746	212.49	teresopolitano	770.601
3679	Três Rios	330600	19	77432	237.42	trirriense	326.135
3682	Vassouras	330620	19	34410	63.94	vassourense	538.134
3683	Volta Redonda	330630	19	257803	1.412	volta-redondense	182.483
3686	Afonso Bezerra	240030	20	10844	18.82	afonso-bezerrense	576.183
3689	Almino Afonso	240060	20	4871	38.04	almino-afonsense	128.037
3691	Angicos	240080	20	11549	15.57	angicano	741.574
3693	Apodi	240100	20	34763	21.69	apodiense	1602.471
3695	Arês	240120	20	12924	111.89	aresense	115.504
3697	Baía Formosa	240140	20	8573	34.9	baía-formosense	245.66
3700	Bento Fernandes	240160	20	5113	16.98	bento-fernandense	301.068
3702	Bom Jesus	240170	20	9440	77.35	bom-jesuense	122.037
3704	Caiçara Do Norte	240185	20	6016	31.74	caiçarense do norte	189.548
3707	Campo Redondo	240210	20	10266	48.03	campo-redondense	213.724
3709	Caraúbas	240230	20	19576	17.88	caraubense	1095
3711	Carnaubais	240250	20	9762	17.99	carnaubaense	542.527
3714	Coronel Ezequiel	240280	20	5405	29.1	coronel-ezequielense	185.747
3716	Cruzeta	240300	20	7967	26.93	cruzetense	295.829
3718	Doutor Severiano	240320	20	6492	59.96	severianense	108.278
3721	Espírito Santo	240350	20	10475	77.11	espírito-santense	135.837
3723	Felipe Guerra	240370	20	5734	21.35	felipe-guerrense 	268.587
3725	Florânia	240380	20	8959	17.74	floraniense	504.927
3727	Frutuoso Gomes	240400	20	4233	66.89	frutuoso-gomense	63.279
3730	Governador Dix Sept Rosado	240430	20	12374	10.96	dix-septiense	1129.336
3733	Ielmo Marinho	240460	20	12171	39.01	ielmo-marinhense	312.027
3735	Ipueira	240480	20	2077	16.31	ipueirense	127.347
3737	Itaú	240490	20	5564	41.83	itauense	133.029
3740	Janduís	240520	20	5345	17.53	janduiense	304.899
3742	Japi	240540	20	5522	29.22	japiense	188.989
3744	Jardim De Piranhas	240560	20	13506	40.86	piranhense 	330.53
3746	João Câmara	240580	20	32227	45.08	camarense	714.954
3749	Jucurutu	240610	20	17692	18.95	jucurutuense	933.723
3751	Lagoa D`Anta	240620	20	6227	58.94	lagoa-velhense	105.651
3754	Lagoa Nova	240650	20	13983	79.31	lagoa-novense	176.3
3756	Lajes	240670	20	10381	15.34	lajense	676.619
3758	Lucrécia	240690	20	3633	117.45	lucreciano	30.931
3760	Macaíba	240710	20	69467	136.01	macaibense	510.753
3763	Marcelino Vieira	240730	20	8265	23.91	marcelinense 	345.709
3765	Maxaranguape	240750	20	10441	79.51	maxaranguapense	131.315
3767	Montanhas	240770	20	11413	138.82	montanhense	82.213
3769	Monte Das Gameleiras	240790	20	2261	31.43	monte-gameleirense	71.946
3772	Nísia Floresta	240820	20	23784	77.26	nísia-florestense	307.839
3774	Olho D`Água Do Borges	240840	20	4295	30.42	olho-d'água-borgense	141.17
3777	Paraú	240870	20	3859	10.07	parauense	383.212
3778	Parazinho	240880	20	4845	17.64	parazinhense	274.67
3780	Parnamirim	240325	20	202456	1.638	parnamirinense	123.589
3782	Passagem	240920	20	2895	70.24	passagense	41.215
3784	Pau Dos Ferros	240940	20	27745	106.73	pau-ferrense	259.958
3787	Pedro Avelino	240970	20	7171	7.53	pedro-avelinense	952.752
3790	Pilões	241000	20	3453	41.76	pilonense	82.688
3791	Poço Branco	241010	20	13949	60.54	poço-branquense	230.399
3794	Presidente Juscelino	241030	20	8768	52.4	juscelinense	167.344
3797	Rafael Godeiro	241060	20	3063	30.61	rafael-godeirense	100.072
3799	Riacho De Santana	241080	20	4156	32.44	riacho-santanense	128.105
3802	Rodolfo Fernandes	241100	20	4418	28.53	rodolfo-fernandense 	154.839
3804	Santa Cruz	241120	20	35797	57.33	santa-cruzense	624.352
3806	Santana Do Matos	241140	20	13809	9.73	santanense	1419.401
3808	Santo Antônio	241150	20	22216	73.79	santo-antoniense	301.08
3811	São Fernando	241180	20	3401	8.41	são-fernandense	404.425
3813	São Gonçalo Do Amarante	241200	20	87668	351.91	gonçalense	249.122
3816	São José Do Campestre	241230	20	12356	36.22	campestrense	341.113
3818	São Miguel	241250	20	22157	129.05	são-miguelense 	171.69
3820	São Paulo Do Potengi	241260	20	15843	65.9	potengiense	240.424
3823	São Tomé	241290	20	10827	12.55	são-tomeense	862.578
3825	Senador Elói De Souza	241310	20	5637	33.63	elói-de-souzense	167.604
3828	Serra Do Mel	241335	20	10287	16.69	serrano	616.51
3830	Serrinha	241350	20	6581	34.04	serrinhense	193.35
3832	Severiano Melo	241360	20	5752	36.44	severianense	157.85
3835	Taipu	241390	20	11836	33.55	taipuense	352.816
3837	Tenente Ananias	241410	20	9883	44.19	tenente-ananiense	223.67
3839	Tibau	241105	20	3687	21.79	tibauense	169.21
3841	Timbaúba Dos Batistas	241430	20	2295	16.94	timbaubense	135.453
3844	Umarizal	241450	20	10659	49.91	umarizalense 	213.582
3846	Várzea	241470	20	5236	72.04	varzeano	72.683
3849	Viçosa	241490	20	1618	42.69	viçosense	37.904
3851	Alta Floresta D`Oeste	110001	21	24392	3.45	alta-florense	7067.036
3853	Alto Paraíso	110040	21	17135	6.46	alto-paraisense	2651.811
3856	Buritis	110045	21	32383	9.92	buritisense	3265.814
3858	Cacaulândia	110060	21	5736	2.92	cacaulandense	1961.781
3860	Campo Novo De Rondônia	110070	21	12665	3.68	campo-novense	3442.01
3863	Cerejeiras	110005	21	17029	6.12	cerejeirense	2783.304
3865	Colorado Do Oeste	110006	21	18591	12.81	coloradense	1451.063
3868	Cujubim	110094	21	15854	4.1	cujubiense	3863.946
3870	Governador Jorge Teixeira	110100	21	10512	2.07	jorge-teixeirense	5067.391
3873	Jaru	110011	21	52005	17.66	jaruense	2944.131
3875	Machadinho D`Oeste	110013	21	31135	3.66	machadinhense	8509.32
3877	Mirante Da Serra	110130	21	11878	9.97	mirantense	1191.877
3880	Nova Mamoré	110033	21	22546	2.24	nova-mamorense	10071.66
3882	Novo Horizonte Do Oeste	110050	21	10240	12.14	novo-horizontino	843.447
3885	Pimenta Bueno	110018	21	33822	5.42	pimenta-buenense	6240.938
3887	Porto Velho	110020	21	428527	12.57	porto-velhense	34096.429
3889	Primavera De Rondônia	110147	21	3524	5.82	primaverense	605.693
3892	Santa Luzia D`Oeste	110029	21	8886	7.42	santa-luziense	1197.797
3894	São Francisco Do Guaporé	110149	21	16035	1.46	são-francisquense	10959.786
3897	Teixeirópolis	110155	21	4888	10.63	teixeirense	459.979
3900	Vale Do Anari	110175	21	9384	2.99	anariense	3135.146
3901	Vale Do Paraíso	110180	21	8210	8.5	vale-paraisense	965.677
3904	Amajari	140002	22	9327	0.33	amajariense	28472.265
3906	Bonfim	140015	22	10943	1.35	bonfinense	8095.399
3908	Caracaraí	140020	22	18398	0.39	caracaraiense	47410.947
3910	Iracema	140028	22	8696	0.6	iracemense	14409.55
3913	Pacaraima	140045	22	10433	1.3	pacaraimense	8028.463
3915	São João Da Baliza	140050	22	6769	1.58	baliziense	4285.038
3918	Aceguá	430003	23	4394	2.84	aceguaense	1549.391
3920	Agudo	430010	23	16722	31.19	agudense	536.117
3922	Alecrim	430030	23	7045	22.38	alecrinense	314.744
3924	Alegria	430045	23	4301	24.91	alegriense	172.688
3926	Alpestre	430050	23	8027	24.42	alpestrense	328.751
3928	Alto Feliz	430057	23	2917	36.84	alto-felizense	79.173
4109	Herveiras	430957	23	2954	24.97	herveirense	118.281
3931	Ametista Do Sul	430064	23	7323	78.33	ametistense	93.49
3933	Anta Gorda	430070	23	6073	25	anta-gordense	242.965
3935	Arambaré	430085	23	3693	7.11	arambarense	519.126
3938	Arroio Do Meio	430100	23	18783	118.91	arroio-meense	157.957
3940	Arroio Do Sal	430105	23	7740	64.01	arroio-salense	120.912
3943	Arroio Grande	430130	23	18470	7.35	arroio-grandense	2513.609
3945	Augusto Pestana	430150	23	7096	20.42	augusto-pestanense	347.44
3948	Balneário Pinhal	430163	23	10856	104.63	pinhalense	103.758
3950	Barão De Cotegipe	430170	23	6529	25.1	cotegipense	260.131
3952	Barra Do Guarita	430185	23	3089	47.82	barra-guaritense	64.591
3955	Barra Do Rio Azul	430192	23	2003	13.62	barra-azulense	147.029
3958	Barros Cassal	430200	23	11133	17.16	barros-cassalense	648.898
3960	Bento Gonçalves	430210	23	107278	280.86	bento-gonçalvense	381.96
3962	Boa Vista Do Buricá	430220	23	6574	60.46	boa-vistense	108.733
3965	Boa Vista Do Sul	430225	23	2776	29.42	boavistense	94.349
3967	Bom Princípio	430235	23	11789	133.2	bom-principiense	88.504
3970	Boqueirão Do Leão	430245	23	7673	28.91	léo-boqueirense	265.428
3972	Bozano	430258	23	2200	10.94	bozanense	201.04
3975	Butiá	430270	23	20406	27.13	butiaense	752.25
3977	Cacequi	430290	23	13676	5.77	cacequiense	2369.96
3979	Cachoeirinha	430310	23	118278	2.687	cachoeirinhense	44.018
3982	Caiçara	430340	23	5071	26.8	caiçarense	189.238
3984	Camargo	430355	23	2592	18.77	camarguense	138.069
3986	Campestre Da Serra	430367	23	3247	6.04	campestrense	538.002
3988	Campinas Do Sul	430380	23	5506	19.94	campinense	276.163
3991	Campos Borges	430410	23	3494	15.42	campos-borgense	226.579
3993	Cândido Godói	430430	23	6535	26.54	godoiense	246.277
3996	Canguçu	430450	23	53259	15.11	canguçuense	3525.309
3998	Canudos Do Vale	430461	23	1807	22.06	canudense do vale	81.913
4000	Capão Da Canoa	430463	23	42040	432.96	caponense	97.1
4003	Capela De Santana	430468	23	11612	63.19	capelense	183.757
4005	Capivari Do Sul	430467	23	3890	9.42	capivariense	412.793
4008	Carlos Barbosa	430480	23	25192	110.17	barbosense	228.67
4010	Casca	430490	23	8651	31.83	casquense	271.747
4013	Caxias Do Sul	430510	23	435564	264.89	caxiense	1644.302
4015	Cerrito	430512	23	6402	14.17	cerritense	451.701
4017	Cerro Grande	430515	23	2417	32.91	cerro-grandense	73.438
4019	Cerro Largo	430520	23	13289	74.79	cerro-larguense	177.676
4022	Charrua	430537	23	3471	17.52	charruense	198.125
4024	Chuí	430543	23	5917	29.21	chuiense	202.553
4026	Cidreira	430545	23	12668	51.52	cidreirense	245.885
4027	Ciríaco	430550	23	4922	17.97	ciriaquense	273.874
4029	Colorado	430560	23	3550	12.44	coloradense	285.263
4031	Constantina	430580	23	9752	48.04	constantinense	203
4033	Coqueiros Do Sul	430585	23	2457	8.92	coqueirense	275.55
4035	Coronel Bicaco	430590	23	7748	15.74	bicaquense	492.126
4038	Coxilha	430597	23	2826	6.68	coxilhense	422.79
4040	Cristal	430605	23	7280	10.68	cristalense	681.628
4042	Cruz Alta	430610	23	62821	46.18	cruzaltense	1360.376
4044	Cruzeiro Do Sul	430620	23	12320	79.2	cruzeirense	155.552
4047	Dezesseis De Novembro	430635	23	2866	13.22	dezesseis-novembrense	216.849
4049	Dois Irmãos	430640	23	27572	423.17	dois-irmãosense	65.156
4052	Dom Feliciano	430650	23	14380	10.6	felicianense	1356.176
4054	Dom Pedro De Alcântara	430655	23	2550	32.63	dom-pedro-alcantarense	78.158
4057	Doutor Ricardo	430675	23	2030	18.72	ricardense	108.434
4060	Encruzilhada Do Sul	430690	23	24534	7.33	encruzilhadense	3348.333
4062	Entre Rios Do Sul	430695	23	3080	25.65	entre-rio-sulense	120.068
4064	Erebango	430697	23	2970	19.4	erebanguense	153.123
4067	Erval Grande	430720	23	5163	18.06	erval-grandense	285.915
4069	Esmeralda	430740	23	3168	3.82	esmeraldense	829.938
4071	Espumoso	430750	23	15240	19.46	espumosense	783.069
4073	Estância Velha	430760	23	42574	816.42	estanciense	52.147
4076	Estrela Velha	430781	23	3628	12.88	estrelavelhense	281.668
4078	Fagundes Varela	430786	23	2579	19.2	fagundense	134.296
4081	Faxinalzinho	430805	23	2567	17.9	faxinalzinhense	143.382
4083	Feliz	430810	23	12359	129.59	felizense	95.372
4085	Floriano Peixoto	430825	23	2018	11.98	florianense	168.428
4088	Forquetinha	430843	23	2479	26.49	forquetinhense	93.57
4090	Frederico Westphalen	430850	23	28843	108.85	westphalense	264.976
4093	Gaurama	430870	23	5862	28.7	gauramense	204.262
4095	Gentil	430885	23	1677	9.11	gentilense	184.015
4097	Giruá	430900	23	17075	19.95	giruaense	855.924
4100	Gramado Dos Loureiros	430912	23	2269	17.27	loureirense	131.396
4102	Gravataí	430920	23	255660	551.58	gravataiense	463.501
4104	Guaíba	430930	23	95204	252.57	guaibense	376.948
4106	Guarani Das Missões	430950	23	8115	27.93	guaraniense	290.497
4112	Humaitá	430970	23	4919	36.57	humaitaense	134.514
4114	Ibiaçá	430980	23	4710	13.5	ibiaçaense	348.818
4116	Ibirapuitã	430995	23	4061	13.23	ibirapuitanense	307.03
4118	Igrejinha	431010	23	31660	233.03	igrejinhense	135.862
4121	Imbé	431033	23	17670	448.53	Imbeense	39.395
4123	Independência	431040	23	6618	18.51	independenciense	357.44
4126	Ipiranga Do Sul	431046	23	1944	12.31	ipiranguense	157.883
4128	Itaara	431053	23	5010	28.96	itaarense	172.99
4130	Itapuca	431057	23	2344	12.72	itapuquense	184.25
4133	Itatiba Do Sul	431070	23	4171	19.66	itatibense	212.194
4135	Ivoti	431080	23	19874	314.71	ivotiense	63.151
4137	Jacuizinho	431087	23	2507	7.41	jacuizinhense	338.535
4140	Jaguari	431110	23	11473	17.04	jaguariense	673.404
4142	Jari	431113	23	3575	4.17	jariense	856.46
4144	Júlio De Castilhos	431120	23	19579	10.15	castilhense	1929.389
4146	Lagoa Dos Três Cantos	431127	23	1598	11.53	três-cantense	138.636
4149	Lajeado	431140	23	71445	793.06	lajeadense	90.088
4151	Lavras Do Sul	431150	23	7679	2.95	lavrense	2600.611
4154	Linha Nova	431164	23	1624	25.48	linha-novense	63.733
4155	Maçambara	431171	23	4738	2.82	maçambarense	1682.828
4157	Mampituba	431173	23	3003	19	mampitubense	158.03
4159	Maquiné	431177	23	6905	11.11	maquinense	621.696
4162	Marcelino Ramos	431190	23	5134	22.36	marcelinense	229.619
4164	Mariano Moro	431200	23	2210	22.3	marianense	99.111
4167	Mato Castelhano	431213	23	2470	10.36	mato-castelhanense	238.365
4169	Mato Queimado	431217	23	1799	15.69	matoqueimadense	114.641
4172	Miraguaí	431230	23	4855	37.24	miraguaiense	130.386
4174	Monte Alegre Dos Campos	431237	23	3102	5.64	montealegrense	549.742
4177	Mormaço	431242	23	2749	18.81	mormacense	146.109
4179	Morro Redondo	431245	23	6227	25.45	morro-redondense	244.646
4181	Mostardas	431250	23	12124	6.11	mostardense	1983
4183	Muitos Capões	431261	23	2988	2.49	caponense	1197.935
4186	Nicolau Vergueiro	431267	23	1721	11.04	nicolau-vergueirense	155.82
4188	Nova Alvorada	431275	23	3182	21.3	nova-alvoradense	149.362
4191	Nova Boa Vista	431295	23	1960	20.8	boa-vistense	94.238
4193	Nova Candelária	431301	23	2751	28.12	nova-candelariense	97.833
4196	Nova Pádua	431308	23	2450	23.73	paduense	103.238
4198	Nova Petrópolis	431320	23	19045	65.38	nova-petropolitano	291.301
4200	Nova Ramada	431333	23	2437	9.57	morador de nova rama	254.756
4202	Nova Santa Rita	431337	23	22716	104.26	nova-santaritense	217.871
4205	Novo Hamburgo	431340	23	238940	1.067	novo-hamburguense	223.822
4207	Novo Tiradentes	431344	23	2277	30.2	tiradentense	75.396
4210	Paim Filho	431360	23	4243	23.29	paim-filhense	182.18
4212	Palmeira Das Missões	431370	23	34328	24.18	palmeirense	1419.435
4215	Pantano Grande	431395	23	9895	11.76	pantanense	841.229
4217	Paraíso Do Sul	431402	23	7336	21.71	paraisense	337.843
4220	Passa Sete	431406	23	5154	16.92	passasetense	304.54
4221	Passo Do Sobrado	431407	23	6011	22.67	passo-sobradense	265.109
4224	Paverama	431415	23	8044	46.8	paveramense	171.864
4226	Pedro Osório	431420	23	7811	12.83	pedro-osoriense	608.792
4229	Picada Café	431442	23	5182	60.86	picadense	85.146
4231	Pinhal Da Serra	431446	23	2130	4.87	pinhalense	437.352
4234	Pinheiro Machado	431450	23	12780	5.68	pinheirense	2249.566
4236	Piratini	431460	23	19841	5.61	piratinense	3539.704
4238	Poço Das Antas	431475	23	2017	31	poçandense	65.065
4241	Portão	431480	23	30920	193.38	portanense	159.895
4243	Porto Lucena	431500	23	5413	21.65	porto-lucenense	250.079
4245	Porto Vera Cruz	431507	23	1852	16.3	porto-vera-cruzense	113.647
4248	Presidente Lucena	431514	23	2484	50.26	lucinense	49.426
4250	Protásio Alves	431517	23	2000	11.57	protásio-alvense	172.816
4253	Quatro Irmãos	431531	23	1775	6.62	quatroirmanense	267.987
4255	Quinze De Novembro	431535	23	3653	16.33	quinze-novembrense	223.639
4258	Restinga Seca	431550	23	15849	16.58	restinguense	956.053
4260	Rio Grande	431560	23	197228	72.79	rio-grandino	2709.534
4262	Riozinho	431575	23	4330	18.07	riozinhense	239.56
4265	Rolador	431595	23	2546	8.63	roladorense	295.006
4267	Ronda Alta	431610	23	10221	24.37	ronda-altense	419.346
4269	Roque Gonzales	431630	23	7203	20.78	roque-gonzalense	346.623
4272	Saldanha Marinho	431643	23	2869	12.95	saldanhense	221.605
4274	Salvador Das Missões	431647	23	2669	28.38	salvadorense	94.042
4277	Santa Bárbara Do Sul	431670	23	8829	9.05	santa-barbarense	975.51
4279	Santa Clara Do Sul	431675	23	5697	65.75	santa-clarense	86.644
4280	Santa Cruz Do Sul	431680	23	118374	161.4	santa-cruzense	733.412
4281	Santa Margarida Do Sul	431697	23	2352	2.46	margaridense 	955.303
4283	Santa Maria Do Herval	431695	23	6053	43.36	hervalense	139.599
4286	Santa Vitória Do Palmar	431730	23	30990	5.91	vitoriense	5244.379
4288	Santana Do Livramento	431710	23	82464	11.86	santanense	6950.388
4291	Santo Antônio Da Patrulha	431760	23	39685	37.8	patrulhense	1049.81
4293	Santo Antônio Do Palma	431755	23	2139	16.96	palmense	126.095
4295	Santo Augusto	431780	23	13968	29.84	santo-augustense	468.105
4298	São Borja	431800	23	61671	17.05	são borjense	3616.035
4300	São Francisco De Assis	431810	23	19254	7.68	assisense	2508.464
4303	São Jerônimo	431840	23	22134	23.64	jeronimense	936.379
4305	São João Do Polêsine	431843	23	2635	30.94	polesinense	85.17
4308	São José Do Herval	431846	23	2204	21.38	hervalense	103.094
4310	São José Do Inhacorá	431849	23	2200	28.28	inhacoraense	77.806
4313	São José Do Sul	431861	23	2082	35.27	são josense do sul	59.034
4316	São Lourenço Do Sul	431880	23	43111	21.17	lourenciano	2036.134
4319	São Martinho	431910	23	5773	33.63	são-martinhense	171.662
4321	São Miguel Das Missões	431915	23	7421	6.03	miguelino	1229.848
4528	Indaial	420750	24	54854	127.41	indaialense	430.539
4323	São Paulo Das Missões	431930	23	6364	28.43	paulista-das-missões	223.887
4326	São Pedro Do Butiá	431937	23	2873	26.69	são-butiaiense	107.631
4329	São Sepé	431960	23	23798	10.81	sepense	2200.702
4331	São Valentim Do Sul	431971	23	2168	23.5	são-valentinense	92.24
4334	São Vicente Do Sul	431980	23	8440	7.18	vicentino	1175.233
4336	Sapucaia Do Sul	432000	23	130957	2.245	sapucaiense	58.309
4339	Sede Nova	432023	23	3011	25.24	sede-novense	119.297
4341	Selbach	432030	23	4929	27.75	selbaquense	177.643
4343	Sentinela Do Sul	432035	23	5198	18.43	sentinelense	281.965
4346	Sertão	432050	23	6294	14.32	sertanense	439.473
4348	Sete De Setembro	432057	23	2124	16.34	setembrense	129.993
4350	Silveira Martins	432065	23	2449	20.68	sillveirense	118.423
4353	Soledade	432080	23	30044	24.76	soledadense	1213.414
4355	Tapejara	432090	23	19250	80.61	tapejarense	238.799
4358	Taquara	432120	23	54643	119.35	taquarense	457.856
4360	Taquaruçu Do Sul	432132	23	2966	38.6	taquaraçusense	76.849
4362	Tenente Portela	432140	23	13719	40.58	portelense	338.085
4365	Tio Hugo	432146	23	2724	23.85	tio-huguense	114.236
4367	Toropi	432149	23	2952	14.54	toropiense	202.977
4369	Tramandaí	432160	23	41585	287.97	tramandaiense	144.408
4371	Três Arroios	432163	23	2855	19.21	três-arroiense	148.583
4374	Três De Maio	432180	23	23726	56.2	três-maiense	422.2
4376	Três Palmeiras	432185	23	4381	24.26	três-palmeirense	180.6
4379	Triunfo	432200	23	25793	31.5	triunfense	818.802
4381	Tunas	432215	23	4395	20.15	tunense	218.073
4383	Tupanciretã	432220	23	22281	9.89	tupanciretanense	2251.869
4386	Turuçu	432232	23	3522	13.89	turuçuense	253.636
4388	União Da Serra	432235	23	1487	11.35	união-serrense	130.99
4390	Uruguaiana	432240	23	125435	21.95	uruguaianense	5715.791
4393	Vale Real	432254	23	5118	113.52	vale-realense	45.085
4395	Vanini	432255	23	1984	30.58	vaninense	64.873
4397	Vera Cruz	432270	23	23983	77.46	vera-cruzense	309.622
4399	Vespasiano Correa	432285	23	1974	17.33	vespasianense	113.886
4402	Vicente Dutra	432310	23	5285	27.1	dutrense	195.045
4403	Victor Graeff	432320	23	3036	12.74	victorense	238.274
4405	Vila Lângaro	432335	23	2152	14.14	vila-langarense	152.172
4407	Vila Nova Do Sul	432345	23	4221	8.31	vila-novense	507.945
4410	Vista Gaúcha	432370	23	2759	31.1	vista-gauchense	88.719
4413	Xangri Lá	432380	23	12434	204.88	xangri-laense	60.688
4415	Abelardo Luz	420010	24	17100	17.9	abelardo-lusense-	955.375
4417	Agronômica	420030	24	4904	37.73	agronomense	129.993
4419	Águas De Chapecó	420050	24	6110	43.92	chapecoense-das-águas	139.13
4422	Alfredo Wagner	420070	24	9410	12.85	alfredense	732.278
4424	Anchieta	420080	24	6380	27.91	anchietense	228.581
4426	Anita Garibaldi	420100	24	8623	14.65	anita-garibaldense	588.614
4429	Apiúna	420125	24	9600	19.45	apiunense	493.53
4431	Araquari	420130	24	24810	64.25	araquariense	386.135
4434	Arroio Trinta	420160	24	3502	37.12	arroio-trintense	94.334
4436	Ascurra	420170	24	7412	66.37	ascurrense	111.673
4438	Aurora	420190	24	5549	26.81	aurorense	206.948
4440	Balneário Barra Do Sul	420205	24	8430	76.28	barrassulense	110.512
4443	Balneáreo Piçarras	421280	24	17078	171.82	piçarrense	99.395
4446	Barra Velha	420210	24	22386	159.7	barra-velhense	140.177
4448	Belmonte	420215	24	2635	28.15	belmontense	93.607
4450	Biguaçu	420230	24	58206	155.44	biguaçuense	374.45
4452	Bocaina Do Sul	420243	24	3290	6.41	bocainense	513.045
4455	Bom Jesus Do Oeste	420257	24	2132	31.4	bonjesuense	67.899
4458	Botuverá	420270	24	4468	14.74	botuveraense	303.024
4460	Braço Do Trombudo	420285	24	3457	38.55	braço trombudense	89.681
4462	Brusque	420290	24	105503	372.22	brusquense	283.446
4465	Calmon	420315	24	3387	5.3	calmonense	639.532
4467	Campo Alegre	420330	24	11748	23.68	campo-alegrense	496.149
4469	Campo Erê	420350	24	9370	19.57	campo-erense	478.734
4472	Canoinhas	420380	24	52765	46.09	canoinhense	1144.838
4474	Capinzal	420390	24	20769	85.15	capinzalense	243.9
4476	Catanduvas	420400	24	9555	48.25	catanduvense	198.034
4478	Celso Ramos	420415	24	2771	13.36	celso-ramense	207.409
4480	Chapadão Do Lageado	420419	24	2762	22.19	lageadense	124.472
4483	Concórdia	420430	24	68621	86.07	concordense	797.264
4485	Coronel Freitas	420440	24	10213	43.62	freitense ou freitano	234.16
4487	Correia Pinto	420455	24	14785	22.69	correia-pintense	651.616
4490	Cunha Porã	420470	24	10613	48.18	cunha-porense	220.293
4492	Curitibanos	420480	24	37748	39.64	curitibanense	952.285
4494	Dionísio Cerqueira	420500	24	14811	39.21	cerqueirense	377.715
4497	Entre Rios	420517	24	3018	28.7	entrerriense	105.167
4500	Faxinal Dos Guedes	420530	24	10661	31.39	faxinalense	339.639
4502	Florianópolis	420540	24	421240	627.24	florianopolitano	671.578
4504	Forquilhinha	420545	24	22548	123.95	forquilhense	181.915
4507	Galvão	420560	24	3472	28.48	galvãoense	121.899
4509	Garuva	420580	24	14761	29.44	garuvense	501.378
4511	Governador Celso Ramos	420600	24	12999	111.42	gancheiro	116.668
4514	Guabiruba	420630	24	18430	106.17	guabirubense	173.591
4516	Guaramirim	420650	24	35172	131.18	guaramirense	268.12
4518	Guatambú	420665	24	4679	22.85	guatumbuense	204.759
4521	Ibicaré	420680	24	3373	21.61	ibicareense	156.073
4523	Içara	420700	24	58833	200.02	içarense	294.132
4525	Imaruí	420720	24	11672	21.53	imaruense	542.238
4530	Ipira	420760	24	4752	30.57	ipirense	155.449
4531	Iporã Do Oeste	420765	24	8409	41.55	iporã-oestino	202.369
4534	Iraceminha	420775	24	4253	25.87	iraceminhense	164.376
4536	Irati	420785	24	2096	27.04	iratiense	77.518
4539	Itaiópolis	420810	24	20301	15.67	itaiopolense	1295.321
4541	Itapema	420830	24	45797	771.5	itapemense	59.361
4543	Itapoá	420845	24	14763	57.73	itapoaense	255.746
4546	Jacinto Machado	420870	24	10609	24.74	jacinto-machadense	428.767
4548	Jaraguá Do Sul	420890	24	143123	268.73	jaraguaense	532.593
4550	Joaçaba	420900	24	27020	116.29	joaçabense	232.354
4553	Jupiá	420917	24	2148	23.42	jupiaense	91.71
4555	Lages	420930	24	156727	59.6	lageano	2629.789
4557	Lajeado Grande	420945	24	1490	22.6	lajeado grandense	65.928
4560	Lebon Régis	420970	24	11838	12.58	lebon-regense	940.659
4562	Lindóia Do Sul	420985	24	4642	24.49	lindoiense	189.57
4565	Luzerna	421003	24	5600	47.93	luzernense	116.832
4567	Mafra	421010	24	52912	37.68	mafrense	1404.21
4569	Major Vieira	421030	24	7479	14.22	major-vieirense	525.99
4572	Marema	421055	24	2203	21.26	maremense	103.615
4574	Matos Costa	421070	24	2839	6.57	matos-costense	432.179
4576	Mirim Doce	421085	24	2513	7.47	mirindocense	336.315
4578	Mondaí	421100	24	10231	50.91	mondaiense	200.979
4580	Monte Castelo	421110	24	8346	14.86	monte-castelense	561.733
4583	Navegantes	421130	24	60556	543.29	navegantino	111.462
4585	Nova Itaberaba	421145	24	4267	31.01	nova itaberadense	137.583
4588	Novo Horizonte	421165	24	2750	18.13	novo-horizontino	151.674
4590	Otacílio Costa	421175	24	16337	19.3	otaciliense	846.58
4593	Paial	421187	24	1763	20.56	paialense	85.761
4595	Palhoça	421190	24	137334	347.68	palhocense	395
4597	Palmeira	421205	24	2373	8.12	palmeirense	292.217
4600	Paraíso	421223	24	4080	22.84	paraisense	178.608
4602	Passos Maia	421227	24	4425	7.2	passosmaiense	614.434
4604	Pedras Grandes	421240	24	4107	23.9	pedras-grandense	171.824
4607	Petrolândia	421270	24	6131	20.03	petrolandense	306.153
4609	Pinheiro Preto	421300	24	3147	47.9	pinheirense	65.706
4611	Planalto Alegre	421315	24	2654	42.37	planaltoalegrense	62.633
4614	Ponte Alta Do Norte	421335	24	3303	8.24	norte pontealtense	400.974
4616	Porto Belo	421350	24	16083	167.82	porto-belense	95.835
4618	Pouso Redondo	421370	24	14810	41.19	pouso-redondense	359.519
4621	Presidente Getúlio	421400	24	14887	50.35	getulense	295.65
4623	Princesa	421415	24	2758	31.99	princesense	86.215
4625	Rancho Queimado	421430	24	2748	9.59	rancho-queimadense	286.433
4628	Rio Do Oeste	421460	24	7090	28.86	riense-do-oeste	245.635
4630	Rio Dos Cedros	421470	24	10284	18.51	rio-cedrense	555.657
4633	Rio Rufino	421505	24	2436	8.62	rio rufinense	282.569
4635	Rodeio	421510	24	10922	85.24	rodeiense	128.136
4638	Saltinho	421535	24	3961	25.3	saltinhense	156.531
4640	Sangão	421545	24	10400	125.22	sangãoense	83.055
4642	Santa Helena	421555	24	2382	29.41	santaelenense	80.983
4644	Santa Rosa Do Sul	421565	24	8054	53.18	santa-rosense	151.445
4647	Santiago Do Sul	421569	24	1465	19.91	santiaguense	73.564
4827	Aspásia	350395	26	1809	26.09	aspasiense	69.337
4649	São Bento Do Sul	421580	24	74801	150.94	são-bentense	495.577
4652	São Carlos	421600	24	10291	64.73	são-carlense	158.989
4654	São Domingos	421610	24	9491	24.74	dominguense	383.652
4656	São João Batista	421630	24	26260	118.97	batistense	220.727
4657	São João Do Itaperiú	421635	24	3435	22.61	itaperiuense	151.926
4659	São João Do Sul	421640	24	7002	38.33	joão-sulense	182.694
4662	São José Do Cedro	421670	24	13684	48.94	cedrense	279.583
4664	São Lourenço Do Oeste	421690	24	21792	60.24	lourencense ou lourenciano	361.766
4666	São Martinho	421710	24	3209	14.29	são-martinhense	224.53
4669	São Pedro De Alcântara	421725	24	4704	33.69	alcantarense	139.636
4672	Seara	421750	24	16936	54.19	searaense	312.541
4674	Siderópolis	421760	24	12998	49.48	sideropolitano	262.718
4676	Sul Brasil	421775	24	2766	24.54	sul brasilense	112.7
4679	Tigrinhos	421795	24	1757	30.59	tigrinhense	57.439
4681	Timbé Do Sul	421810	24	5308	15.91	timbeense	333.58
4683	Timbó Grande	421825	24	7167	12.01	timbó-grandense	596.939
4686	Treze De Maio	421840	24	6876	42.69	treze-maioense	161.079
4688	Trombudo Central	421860	24	6553	60.27	trombudense	108.728
4691	Turvo	421880	24	11854	50.72	turvense	233.702
4693	Urubici	421890	24	10699	10.5	urubiciense	1019.236
4695	Urussanga	421900	24	20223	84.1	urussanguense	240.477
4698	Vargem Bonita	421917	24	4793	16.05	vargembonitense	298.611
4700	Videira	421930	24	47188	124.88	videirense	377.853
4702	Witmarsum	421940	24	3600	23.87	witmarsumense	150.798
4704	Xavantina	421960	24	4142	19.26	xavantinense	215.072
4707	Amparo De São Francisco	280010	25	2275	64.75	amparense	35.133
4709	Aracaju	280030	25	571149	3.14	aracajuano	181.856
4711	Areia Branca	280050	25	16857	114.93	areia-branquense	146.677
4714	Brejo Grande	280070	25	7742	52.01	brejo-grandense	148.857
4716	Canhoba	280110	25	3956	23.23	canhobense	170.288
4719	Carira	280140	25	20007	31.44	carirense	636.4
4721	Cedro De São João	280160	25	5633	67.29	cedrense	83.71
4723	Cumbe	280190	25	3813	29.65	cumbense	128.596
4725	Estância	280210	25	64409	100	estanciano	644.08
4728	Gararu	280240	25	11405	17.41	gararuense	654.991
4730	Gracho Cardoso	280260	25	5645	23.32	gracho-cardosense	242.061
4732	Indiaroba	280280	25	15831	50.49	indiarobense	313.523
4735	Itabi	280310	25	4972	26.96	itabiense	184.422
4737	Japaratuba	280330	25	16864	46.22	japaratubense	364.897
4739	Lagarto	280350	25	94861	97.84	lagartense	969.573
4742	Malhada Dos Bois	280380	25	3456	54.68	malhadense	63.199
4744	Maruim	280400	25	16343	174.29	maruinense	93.77
4746	Monte Alegre De Sergipe	280420	25	13627	33.45	monte-alegrense	407.406
4749	Nossa Senhora Aparecida	280445	25	8508	25	aparecidense	340.378
4751	Nossa Senhora Das Dores	280460	25	24580	50.85	dorense	483.347
4754	Pacatuba	280490	25	13137	35.14	pacatubense	373.816
4756	Pedrinhas	280510	25	8833	260.25	pedrinhense	33.941
4759	Poço Redondo	280540	25	30880	25.06	poço-redondense	1232.117
4761	Porto Da Folha	280560	25	27146	30.94	porto-folhense	877.297
4763	Riachão Do Dantas	280580	25	19386	36.48	riachãoense	531.472
4766	Rosário Do Catete	280610	25	9221	87.27	rosarense	105.66
4768	Santa Luzia Do Itanhy	280630	25	12969	39.36	santa-luziense	329.502
4771	Santo Amaro Das Brotas	280660	25	11410	48.73	brotense	234.155
4773	São Domingos	280680	25	10271	100.23	são-dominguense	102.47
4776	Simão Dias	280710	25	38702	68.54	simão-diense	564.688
4778	Telha	280730	25	2957	60.31	telhense	49.026
4780	Tomar Do Geru	280750	25	12855	42.16	geruense	304.903
4782	Adamantina	350010	26	33797	82.16	adamantinense	411.355
4783	Adolfo	350020	26	3557	16.85	adolfino	211.077
4785	Águas Da Prata	350040	26	7584	53.05	pratense	142.961
4787	Águas De Santa Bárbara	350055	26	5601	13.73	santa-barbarense	408.024
4790	Alambari	350075	26	4884	30.66	alambariense	159.271
4792	Altair	350090	26	3815	12.16	altairense	313.858
4795	Alumínio	350115	26	16839	200.92	aluminense	83.808
4796	Álvares Florence	350120	26	3897	10.74	alvares florencense	362.941
4799	Alvinlândia	350150	26	3000	35.38	alvinlandense	84.803
4801	Américo Brasiliense	350170	26	34478	281.99	américo-brasiliense	122.268
4804	Analândia	350200	26	4293	13.16	analandense	326.281
4806	Angatuba	350220	26	22210	21.61	angatubense 	1027.985
4809	Aparecida	350250	26	35007	289.12	aparecidense	121.083
4811	Apiaí	350270	26	25191	25.85	apiaiense	974.324
4813	Araçatuba	350280	26	181579	155.54	araçatubense	1167.436
4815	Aramina	350300	26	5152	25.39	araminense	202.887
4818	Araraquara	350320	26	208662	207.8	araraquarense	1004.144
4820	Arco Íris	350335	26	1925	7.27	arcoirense	264.733
4822	Areias	350350	26	3696	12.11	areiense	305.227
4824	Ariranha	350370	26	8547	64.19	ariranhense	133.15
4829	Atibaia	350410	26	126603	264.61	atibaiano	478.448
4831	Avaí	350430	26	4959	9.18	avaiense	540.46
4834	Bady Bassitt	350460	26	14603	134.54	badiense	108.543
4836	Bálsamo	350480	26	8160	54.18	balsamense	150.602
4838	Barão De Antonina	350500	26	3116	20.35	barãoense	153.141
4841	Barra Bonita	350530	26	35246	235.12	barra-bonitense	149.906
4843	Barra Do Turvo	350540	26	7729	7.67	barra-turvense	1007.821
4846	Barueri	350570	26	240749	3.639	barueriense	66.141
4848	Batatais	350590	26	56476	66.48	batataense	849.526
4850	Bebedouro	350610	26	75035	109.81	bebedourense	683.299
4852	Bernardino De Campos	350630	26	10775	44.12	bernardinense	244.198
4855	Birigui	350650	26	108728	204.79	biriguiense	530.922
4857	Boa Esperança Do Sul	350670	26	13645	19.75	boa-esperancense	690.763
4860	Boituva	350700	26	48314	194.07	boituvense	248.954
4862	Bom Sucesso De Itararé	350715	26	3571	26.73	bom sucessiense	133.578
4865	Borborema	350740	26	14529	26.31	borboremense	552.257
4867	Botucatu	350750	26	127328	85.88	botucatuense	1482.643
4869	Braúna	350770	26	5021	25.7	braunense	195.332
4872	Brotas	350790	26	21580	19.59	brotense	1101.385
4874	Buritama	350810	26	15418	47.19	buritamense	326.756
4876	Cabrália Paulista	350830	26	4365	18.19	cabraliense	239.91
4879	Cachoeira Paulista	350860	26	30091	104.49	cachoeirense	287.99
4881	Cafelândia	350880	26	16607	18.05	cafelandense	920.096
4883	Caieiras	350900	26	86529	894.84	caieirense	96.698
4886	Cajati	350925	26	28372	62.43	cajatiense	454.436
4888	Cajuru	350940	26	23371	35.41	cajuruense	660.088
4890	Campinas	350950	26	1080113	1.358	campineiro	795.004
4892	Campos Do Jordão	350970	26	47789	164.49	jordanense	290.52
4895	Canas	350995	26	4385	82.33	canense	53.261
4897	Cândido Rodrigues	351010	26	2668	37.94	cândido-rodriguense	70.313
4899	Capão Bonito	351020	26	46178	28.15	capão-bonitense	1640.232
4902	Caraguatatuba	351050	26	100840	207.76	caraguatatubense	485.377
4904	Cardoso	351070	26	11805	18.45	cardosense	639.725
4906	Cássia Dos Coqueiros	351090	26	2634	13.74	cassiano	191.683
4909	Catiguá	351120	26	7127	48.03	catigüense	148.393
4911	Cerqueira César	351140	26	17532	34.48	cerqueirense	508.53
4912	Cerquilho	351150	26	39617	309.98	cerquilhense	127.803
4914	Charqueada	351170	26	15085	85.79	charqueadense	175.843
4917	Colina	351200	26	17371	41.11	colinense	422.575
4919	Conchal	351220	26	25229	138.02	conchalense	182.793
4921	Cordeirópolis	351240	26	21080	153.22	cordeiropolense	137.579
4923	Coronel Macedo	351260	26	5001	16.45	macedense	303.932
4926	Cosmorama	351290	26	7214	16.25	cosmoramense	443.819
4929	Cristais Paulista	351320	26	7588	19.7	cristalense	385.23
4931	Cruzeiro	351340	26	77039	252.01	cruzeirense	305.699
4933	Cunha	351360	26	21866	15.54	cunhense	1407.319
4936	Dirce Reis	351385	26	1689	19.12	dircense	88.353
4938	Dobrada	351400	26	7939	53.02	dobradense	149.729
4940	Dolcinópolis	351420	26	2096	26.76	dolcinopolense	78.339
4942	Dracena	351440	26	43258	88.64	dracenense	488.042
4945	Echaporã	351470	26	6318	12.26	echaporense	515.427
4947	Elias Fausto	351490	26	15775	77.83	elias-faustense	202.693
4949	Embaúba	351495	26	2423	29.15	embaubense	83.129
4952	Emilianópolis	351512	26	3020	13.45	emilianópolense	224.488
4954	Espírito Santo Do Pinhal	351518	26	41907	107.61	pinhalense	389.421
4956	Estiva Gerbi	355730	26	10044	135.35	estivense	74.208
4959	Euclides Da Cunha Paulista	351535	26	9585	16.66	euclidense	575.214
4961	Fernando Prestes	351560	26	5534	32.43	fernando-prestense	170.67
4964	Ferraz De Vasconcelos	351570	26	168306	5.624	ferrazense	29.922
4967	Florínia	351610	26	2829	12.54	florineense	225.634
4969	Franca	351620	26	318640	526.09	francano	605.681
4971	Franco Da Rocha	351640	26	131604	981.28	franco-rochense	134.114
4974	Garça	351670	26	43115	77.6	garcense	555.63
4976	Gavião Peixoto	351685	26	4419	18.13	gavionense	243.766
4978	Getulina	351700	26	10765	15.86	getulinense	678.701
4981	Guaimbê	351730	26	5425	24.88	guaimbeense	218.013
4983	Guapiaçu	351750	26	17869	54.81	guapiaçuense	326
4985	Guará	351770	26	19858	54.78	guaraense	362.482
4987	Guaraci	351790	26	9976	15.55	guaraciense	641.501
4990	Guararapes	351820	26	30597	31.99	guararapense	956.345
4992	Guaratinguetá	351840	26	112072	148.95	guaratinguetaense	752.433
4994	Guariba	351860	26	35486	131.29	guaribense	270.289
4997	Guatapará	351885	26	6966	16.86	guataparaense	413.06
4999	Herculândia	351900	26	8696	23.85	herculandense	364.641
5001	Hortolândia	351907	26	192692	3.082	hortolandense	62.503
5004	Iaras	351925	26	6376	15.89	iarense	401.308
5006	Ibirá	351940	26	10896	40.07	ibiraense	271.912
5008	Ibitinga	351960	26	53158	77.12	ibitinguense	689.25
5011	Iepê	351990	26	7628	12.81	iepense	595.485
5013	Igarapava	352010	26	27952	59.7	igarapavense	468.246
5015	Iguape	352030	26	28841	14.59	iguapense	1977.414
5017	Ilha Solteira	352044	26	25064	38.19	ilhense	656.225
5020	Indiana	352060	26	4825	38.1	indianense	126.624
5022	Inúbia Paulista	352080	26	3630	41.53	inubense	87.414
5025	Ipeúna	352110	26	6016	31.66	ipeunense	190.01
5027	Iporanga	352120	26	4299	3.73	iporanguense	1152.05
5029	Iracemápolis	352140	26	20029	173.99	iracemapolense	115.118
5032	Itaberá	352170	26	17858	16.5	itaberense	1082.106
5034	Itajobi	352190	26	14556	28.99	itajobiense	502.066
5036	Itanhaém	352210	26	87057	145.2	itanhaense	599.581
5038	Itapecerica Da Serra	352220	26	152614	1.015	itapecericano	150.298
5041	Itapevi	352250	26	200769	2.415	itapeviense	83.107
5042	Itapira	352260	26	68537	132.21	itapirense	518.385
5045	Itaporanga	352280	26	14549	28.66	itaporanguense	507.706
5047	Itapura	352300	26	4357	14.46	itapurense	301.368
5049	Itararé	352320	26	47934	47.76	itarareense	1003.581
5051	Itatiba	352340	26	101471	314.92	itatibense	322.211
5053	Itirapina	352360	26	15524	27.52	itirapinense	564.183
5056	Itu	352390	26	154147	240.57	ituano	640.757
5058	Ituverava	352410	26	38695	54.87	ituveravense	705.236
5060	Jaboticabal	352430	26	71662	101.42	jaboticabalense	706.603
5062	Jaci	352450	26	5657	38.87	jaciense	145.524
5065	Jales	352480	26	47012	127.57	jalesense	368.52
5067	Jandira	352500	26	108344	6.124	jandirense	17.69
5069	Jarinu	352520	26	23847	114.85	jarinuense	207.64
5072	Joanópolis	352550	26	11768	31.37	joanopolitano	375.095
5074	José Bonifácio	352570	26	32763	38.1	bonifacense	859.948
5076	Jumirim	352585	26	2798	49.36	jumirense	56.685
5078	Junqueirópolis	352600	26	18726	32.12	junqueiropolense	582.959
5081	Lagoinha	352630	26	4841	18.95	lagoinhense	255.472
5083	Lavínia	352650	26	8779	16.33	lavinense	537.733
5086	Lençóis Paulista	352680	26	61428	75.88	lençoiense	809.493
5088	Lindóia	352700	26	6712	137.67	lindoiano	48.756
5091	Lourdes	352725	26	2128	18.71	lourdense	113.743
5093	Lucélia	352740	26	19882	63.16	luceliense	314.785
5095	Luís Antônio	352760	26	11286	18.86	luís-antoniense	598.439
5098	Lutécia	352790	26	2714	5.71	luteciano	474.925
5100	Macaubal	352810	26	7663	30.88	macaubalense	248.125
5102	Magda	352830	26	3200	10.27	magdense	311.712
5104	Mairiporã	352850	26	80956	252.25	mairiporense	320.93
5106	Marabá Paulista	352870	26	4812	5.24	marabaense	917.675
5109	Mariápolis	352890	26	3916	21.07	mariapolense	185.897
5111	Marinópolis	352910	26	2113	27.15	marinopolense	77.832
5114	Mauá	352940	26	417064	6.803	mauaense	61.301
5116	Meridiano	352960	26	3855	16.82	meridianense	229.246
5118	Miguelópolis	352970	26	20451	24.88	miguelopense	821.96
5121	Miracatu	352990	26	20592	20.56	miracatuense	1001.536
5123	Mirante Do Paranapanema	353020	26	17059	13.77	mirantense	1239.081
5126	Mococa	353050	26	66290	77.55	mocoquense	854.857
5128	Mogi Guaçu	353070	26	137245	168.98	guaçuano	812.186
5130	Mombuca	353090	26	3266	24.43	mombucano	133.698
5133	Monte Alegre Do Sul	353120	26	7152	64.84	monte-alegrense	110.302
5135	Monte Aprazível	353140	26	21746	43.76	monte-aprazivelense	496.906
5137	Monte Castelo	353160	26	4063	17.47	monte-castelense	232.565
5140	Morro Agudo	353190	26	29116	20.97	morro-agudense	1388.195
5142	Motuca	353205	26	4290	18.76	motuquense	228.7
5145	Narandiba	353220	26	4288	11.98	narandibense	358.029
5147	Nazaré Paulista	353240	26	16414	50.36	nazareano	325.926
5150	Nipoã	353270	26	4274	31.01	nipoense	137.816
5152	Nova Campina	353282	26	8515	22.1	nova campinense	385.376
5154	Nova Castilho	353286	26	1125	6.14	castilhense	183.232
5157	Nova Guataporanga	353310	26	2177	63.81	guataporanguense	34.116
5159	Nova Luzitânia	353330	26	3441	46.46	luzitaniense	74.057
5162	Novo Horizonte	353350	26	36593	39.28	novo-horizontino	931.669
5164	Ocauçu	353370	26	4163	13.86	ocauçuense	300.353
5167	Onda Verde	353400	26	3884	15.98	onda-verdense	243.119
5169	Orindiúva	353420	26	5675	22.87	orindiuvense	248.109
5171	Osasco	353440	26	666740	10.411	osasquense	64.037
5172	Oscar Bressane	353450	26	2537	11.46	bressanense	221.34
5175	Ouro Verde	353480	26	7800	29.15	ouro-verdense	267.614
5177	Pacaembu	353490	26	13226	39.07	pacaembuense	338.503
5179	Palmares Paulista	353510	26	10934	133.14	palmarense	82.125
5182	Panorama	353540	26	14583	40.93	panoramense	356.312
5184	Paraibuna	353560	26	17388	21.48	paraibunense	809.577
5186	Paranapanema	353580	26	17808	17.48	paranapanemense	1018.726
5189	Pardinho	353610	26	5582	26.59	pardinhense	209.894
5191	Parisi	353625	26	2032	24.04	parasiano	84.522
5193	Paulicéia	353640	26	6339	16.97	pauliceiense	373.568
5196	Paulo De Faria	353660	26	8589	11.63	paulo-fariense	738.29
5198	Pedra Bela	353680	26	5780	36.45	pedra-belense	158.587
5200	Pedregulho	353700	26	15700	22.03	pedregulhense	712.604
5203	Pedro De Toledo	353720	26	10204	15.22	toledense	670.402
5205	Pereira Barreto	353740	26	24962	25.5	pereira-barretense	978.885
5208	Piacatu	353770	26	5287	22.75	piacatuense	232.364
5210	Pilar Do Sul	353790	26	26406	38.77	pilarense	681.124
5212	Pindorama	353810	26	15039	81.37	pindoramense	184.825
5214	Piquerobi	353830	26	3537	7.33	piquerobiense	482.575
5217	Piracicaba	353870	26	364571	264.77	piracicabano	1376.913
5219	Pirajuí	353890	26	22704	27.55	pirajuiense	824.197
5221	Pirapora Do Bom Jesus	353910	26	15733	144.63	piraporense	108.78
5224	Piratininga	353940	26	12072	30	piratiningano	402.414
5226	Planalto	353960	26	4463	15.38	planaltense	290.1
5228	Poá	353980	26	106013	6.211	poaense	17.066
5231	Pongaí	354010	26	3481	18.99	pongaiense	183.33
5233	Pontalinda	354025	26	4074	19.38	pantalindense	210.19
5235	Populina	354040	26	4223	13.37	populinense	315.947
5237	Porto Feliz	354060	26	48893	87.76	porto-felicense	557.103
5239	Potim	354075	26	19397	436.22	potinense	44.466
5242	Pradópolis	354090	26	17377	103.73	pradopolitano	167.526
5244	Pratânia	354105	26	4599	26.26	pratino	175.1
5246	Presidente Bernardes	354120	26	13570	18.04	bernardense	752.134
5249	Presidente Venceslau	354150	26	37910	50.1	venceslauense	756.743
5252	Quatá	354170	26	12799	19.64	quataense	651.742
5254	Queluz	354190	26	11309	45.27	queluzense	249.829
5256	Rafard	354210	26	8612	70.93	rafardense	121.421
5258	Redenção Da Serra	354230	26	3873	12.52	rendencense	309.366
5261	Registro	354260	26	54261	75.11	registrense	722.412
5263	Ribeira	354280	26	3358	10	ribeirense	335.742
5265	Ribeirão Branco	354300	26	18269	26.19	ribeirão-branquense	697.501
5267	Ribeirão Do Sul	354320	26	4446	21.83	ribeirão-sulense	203.686
5270	Ribeirão Pires	354330	26	113068	1.144	ribeirão-pirense	98.75
5272	Rifaina	354360	26	3436	21.14	rifainense	162.508
5274	Rinópolis	354380	26	9935	27.73	rinopolense	358.329
5277	Rio Grande Da Serra	354410	26	43974	1.192	rio-grandense-da-serra	36.877
5279	Riversul	354350	26	6163	15.96	riversulense	386.196
5281	Roseira	354430	26	9599	73.47	roseirense	130.654
5284	Sabino	354460	26	5217	16.78	sabinense	310.895
5286	Sales	354480	26	5451	17.67	salense	308.46
5288	Salesópolis	354500	26	15635	36.79	salesopolense	424.973
5291	Salto	354520	26	105516	792.17	saltense	133.199
5293	Salto Grande	354540	26	8787	46.64	salto-grandense	188.396
5295	Santa Adélia	354560	26	14333	43.32	santa-adeliense	330.895
5298	Santa Branca	354600	26	13763	50	santa-branquense	275.25
5299	Santa Clara D`Oeste	354610	26	2084	11.36	santa-clarense	183.427
5300	Santa Cruz Da Conceição	354620	26	4002	26.66	santa-cruzense	150.128
5302	Santa Cruz Das Palmeiras	354630	26	29932	101.35	palmeirense	295.338
5305	Santa Fé Do Sul	354660	26	29239	140.43	santa-fé-sulense	208.216
5307	Santa Isabel	354680	26	50453	139.09	isabelense	362.738
5310	Santa Mercedes	354710	26	2831	16.96	mercedense	166.873
5312	Santa Rita Do Passa Quatro	354750	26	26478	35.11	santa-ritense	754.141
5315	Santana Da Ponte Pensa	354720	26	1641	12.6	santanense-da-ponte-pensa	130.263
5318	Santo André	354780	26	676407	3.866	andreense	174.947
5320	Santo Antônio De Posse	354800	26	20650	134.09	possense	153.997
5322	Santo Antônio Do Jardim	354810	26	5943	54.05	jardinense	109.956
5325	Santópolis Do Aguapeí	354840	26	4277	33.44	santopolitano	127.906
5328	São Bernardo Do Campo	354870	26	765463	1.872	são-bernardense	408.773
5331	São Francisco	354900	26	2793	36.94	são-francisquense	75.617
5333	São João Das Duas Pontes	354920	26	2566	19.84	são-joanense	129.34
5335	São João Do Pau D`Alho	354930	26	2103	17.86	são-joanense	117.72
5338	São José Do Barreiro	354960	26	4077	7.14	barreirense	570.961
5341	São José Dos Campos	354990	26	629921	572.77	joseense	1099.777
5343	São Luís Do Paraitinga	355000	26	10397	16.84	luisense	617.316
5346	São Paulo	355030	26	11253503	7.387	paulistano	1523.278
5348	São Pedro Do Turvo	355050	26	7198	9.84	são-pedrense	731.76
5351	São Sebastião Da Grama	355080	26	12099	47.94	gramense	252.38
5354	Sarapuí	355110	26	9027	25.6	sarapuiano	352.685
5356	Sebastianópolis Do Sul	355130	26	3031	18.61	sebastianopolense	162.891
5359	Serrana	355150	26	38878	309.68	serranense	125.544
5361	Sete Barras	355180	26	13005	12.34	barrense	1053.474
5363	Silveiras	355200	26	5792	13.96	silveirense	414.782
5366	Sud Mennucci	355230	26	7435	12.57	sud-menucciano	591.305
5368	Suzanápolis	355255	26	3383	10.3	suzanapolense	328.526
5371	Tabatinga	355270	26	14686	39.74	tabatinguense	369.557
5373	Taciba	355290	26	5714	9.41	tacibense	607.312
5375	Taiaçu	355310	26	5894	55.27	taiaçuense	106.638
5377	Tambaú	355330	26	22406	39.88	tambauense	561.788
5380	Tapiratiba	355360	26	12737	57.23	tapiratibense	222.541
5382	Taquaritinga	355370	26	53988	90.95	taquaritinguense	593.581
5384	Taquarivaí	355385	26	5151	22.22	taquarivaiense	231.793
5387	Tatuí	355400	26	107326	205.03	tatuiano	523.475
5389	Tejupá	355420	26	4809	16.23	tejupaense	296.276
5391	Terra Roxa	355440	26	8505	38.39	terra-roxense	221.541
5393	Timburi	355460	26	2646	13.45	timburiense	196.79
5396	Trabiju	355475	26	1544	24.35	trabijuense	63.421
5398	Três Fronteiras	355490	26	5427	35.45	trifonteirano	153.092
5400	Tupã	355500	26	63476	100.99	tupãense	628.513
5403	Turmalina	355530	26	1978	13.37	turmalinense	147.938
5405	Ubatuba	355540	26	78801	110.87	ubatubano	710.783
5407	Uchoa	355560	26	9471	37.51	uchoense	252.478
5409	Urânia	355580	26	8836	42.29	uraniense	208.942
5412	Valentim Gentil	355610	26	11036	73.72	valentim-gentilense	149.694
5431	Alvorada	170070	27	8374	6.91	alvoradense	1212.165
5433	Angico	170105	27	3175	7.03	angicoense	451.731
5435	Aragominas	170130	27	5882	5.01	aragominense	1173.055
5438	Araguaína	170210	27	150484	37.62	araguainense	4000.403
5440	Araguatins	170220	27	31329	11.93	araguatinense	2625.276
5442	Arraias	170240	27	10645	1.84	arraiano	5786.859
5444	Aurora Do Tocantins	170270	27	3446	4.58	aurorense	752.828
5447	Bandeirantes Do Tocantins	170305	27	3122	2.02	bandeirantense	1541.837
5449	Barrolândia	170310	27	5349	7.5	barrolandense	713.298
5452	Brasilândia Do Tocantins	170360	27	2064	3.22	brasilandense	641.465
5455	Cachoeirinha	170382	27	2148	6.1	cachoeirense	352.344
5457	Cariri Do Tocantins	170386	27	3756	3.33	caririense	1128.599
5459	Carrasco Bonito	170389	27	3688	19.12	carrascoense	192.936
5462	Chapada Da Natividade	170510	27	3277	1.99	chapadense	1646.469
5464	Colinas Do Tocantins	170550	27	30838	36.54	colinense	843.843
5467	Conceição Do Tocantins	170560	27	4182	1.67	conceicionense	2500.734
5470	Crixás Do Tocantins	170625	27	1564	1.59	crixaense	986.691
5473	Divinópolis Do Tocantins	170710	27	6363	2.71	divinopolino	2347.428
5475	Dueré	170730	27	4592	1.34	duerense	3424.845
5478	Figueirópolis	170765	27	5340	2.77	figueiropolense	1930.069
5480	Formoso Do Araguaia	170820	27	18427	1.37	formosense do araguaia	13423.347
5482	Goianorte	170830	27	4956	2.75	goianortense	1800.979
5485	Gurupi	170950	27	76755	41.8	gurupiense	1836.087
5487	Itacajá	171050	27	7104	2.33	itacajaense	3051.351
5489	Itapiratins	171090	27	3532	2.84	itapiratinense	1243.957
5492	Juarina	171180	27	2231	4.64	juarinense	481.046
5494	Lagoa Do Tocantins	171195	27	3525	3.87	lagoense do tocantins	911.34
5496	Lavandeira	171215	27	1605	3.09	lavandeirense	519.571
5499	Marianópolis Do Tocantins	171250	27	4352	2.08	marianopolino	2091.37
5501	Maurilândia Do Tocantins	171280	27	3154	4.27	maurilandense	738.103
5504	Monte Do Carmo	171360	27	6716	1.86	carmelito	3616.665
5506	Muricilândia	171395	27	3152	2.66	muricilandense	1186.642
5509	Nova Olinda	171488	27	10686	6.82	novalindense 	1566.179
5511	Novo Acordo	171510	27	3762	1.41	novoacordino 	2671.89
5513	Novo Jardim	171525	27	2457	1.88	novojardinense	1309.662
5516	Palmeirante	171570	27	4954	1.88	palmeirantense	2640.808
5518	Palmeirópolis	171575	27	7339	4.31	palmeiropolitano	1703.941
5521	Pau D`Arco	171630	27	4588	3.33	pau darquense	1377.401
5523	Peixe	171660	27	10384	1.96	peixense	5291.198
5525	Pindorama Do Tocantins	171700	27	4506	2.89	pindoramense	1559.083
5528	Ponte Alta Do Bom Jesus	171780	27	4544	2.52	pontealtense	1806.137
5530	Porto Alegre Do Tocantins	171800	27	2796	5.57	porto-alegrense	502.024
5533	Presidente Kennedy	171840	27	3681	4.78	kenediense	770.42
5535	Recursolândia	171850	27	3768	1.7	recursolandense	2216.656
5538	Rio Dos Bois	171870	27	2570	3.04	rioboiense	845.063
5540	Sampaio	171880	27	3864	17.38	sampaiense	222.292
5542	Santa Fé Do Araguaia	171886	27	6599	3.93	santaféense	1678.087
5545	Santa Rosa Do Tocantins	171890	27	4568	2.54	santa rosense	1796.253
5546	Santa Tereza Do Tocantins	171900	27	2523	4.67	santa terezense	539.911
5548	São Bento Do Tocantins	172010	27	4608	4.17	são bentense	1105.897
5551	São Salvador Do Tocantins	172025	27	2910	2.05	são salvadorense	1422.03
5553	São Valério	172049	27	4383	1.74	são valeriano	2519.579
5558	Taipas Do Tocantins	172093	27	1945	1.74	taipense	1116.199
5561	Tocantinópolis	172120	27	22619	21	tocantinopolino	1077.069
5563	Tupiratins	172130	27	2097	2.34	tupiratinense	895.305
5565	Xambioá	172210	27	11484	9.68	xambioaense	1186.424
411850	cidade teste	411850	27	\N	\N	\N	\N
\.


--
-- TOC entry 2237 (class 0 OID 74001)
-- Dependencies: 190
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (idempresa, idfilial, idcliente, nome, apelido, inscriestad, tipopessoa, cpfcnpj, contato, fonecomercial, foneresidencial, celular, fax, cep, complemento, observacao, dthrcadastro, dthralteracao, rua, bairro, numero, email, cidade, inativo, dtnascimento, tabpreco, parcelamento, descmax, desconto_padrao, limite_credito, representante_padrao) FROM stdin;
1	1	12	HERMES DA FONSECA	\N	               	0			\N	\N	\N	\N		\N		2014-01-29 22:04:20.107-02	2014-01-29 22:04:20.111-02				\N	3	f	\N	3	4	0	\N	0	1
1	1	14	RODRIGUES ALVES	\N	85806165       	0	05153572943	Dani	32256588	32246501	99263778	\N	85502070	\N		2014-02-08 15:04:00.206-02	2014-02-08 15:04:00.272-02	Itacolomi	Menino deus	2490	dagyu@gmfg.von	3451	f	\N	4	1	0	\N	0	1
1	1	15	CAMPOS SALES	\N	               	0			\N	\N	\N	\N	\N	\N		2014-02-08 15:05:43.302-02	2014-02-08 15:05:43.303-02	\N	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	1
1	1	16	PRUDENTE DE MORAIS		               	1										2014-02-08 18:52:26.342-02	2014-02-08 18:52:26.351-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	17	FLORIANO PEIXOTO	\N	               	0						\N	\N	\N		2014-02-08 18:53:55.903-02	2014-02-08 18:53:55.904-02	\N	\N	\N		\N	f	\N	\N	\N	0	\N	0	1
1	1	18	DEODORO DA FONSECA	\N	               	0						\N	\N	\N		2014-02-08 18:54:49.765-02	2014-02-08 18:54:49.765-02	\N	\N	\N		\N	f	\N	\N	\N	0	\N	0	1
1	1	19	TIM MAIA		               	1										2014-02-08 18:55:44.616-02	2014-02-08 18:55:44.632-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	20	RAUL SEIXAS		               	1										2014-02-08 19:00:45.702-02	2014-02-08 19:00:45.714-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	21	Pp	\N	               	0			\N	\N	\N	\N	\N	\N		2014-02-08 19:02:06.344-02	2014-02-08 19:02:06.349-02	\N	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	1
1	1	22	dd		               	1										2014-02-08 19:02:16.66-02	2014-02-08 19:02:16.668-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	23	ff		               	1										2014-02-08 19:05:10.784-02	2014-02-08 19:05:10.79-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	24	Agorafoi	\N	               	0			\N	\N	\N	\N	\N	\N		2014-02-08 19:06:06.661-02	2014-02-08 19:06:06.662-02	\N	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	1
1	1	25	pomba		               	1										2014-02-08 19:12:23.042-02	2014-02-08 19:12:23.057-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	26	Gali	\N	               	0			\N	\N	\N	\N	\N	\N		2014-02-08 19:12:55.849-02	2014-02-08 19:12:55.849-02	\N	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	1
1	1	27	car		               	1										2014-02-08 19:14:11.546-02	2014-02-08 19:14:11.553-02					\N	f	\N	\N	\N	0	0	0	\N
1	1	28	Cel	\N	               	0			\N	\N	\N	\N	\N	\N		2014-02-08 19:15:04.526-02	2014-02-08 19:15:04.527-02	\N	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	1
1	1	3	HEBE CAMARGO	\N	               	0			\N	\N	\N	\N	\N	\N		2014-01-28 20:25:51.189-02	2014-01-28 20:25:07.21-02	AVENIDA BRAZIL	\N	\N	\N	\N	f	\N	\N	\N	0	\N	0	\N
1	1	4	GETULIO VARGAS	\N	               	0						\N	\N	\N		2014-01-28 20:50:40.092-02	2014-01-28 20:25:07.21-02	jorge lacerda	\N	\N		\N	f	\N	1	1	0	\N	0	1
1	1	5	JULLIO PRESTES	\N	               	0			\N	\N	\N	\N	\N	\N		2014-01-28 20:53:10.477-02	2014-01-28 20:53:10.482-02	rua Principal	\N	\N	\N	\N	f	\N	1	1	0	\N	0	1
1	1	6	WASHINGTON LUIS 4	\N	               	0						\N		\N		2014-01-28 21:41:29.923-02	2014-03-13 23:39:14.635-03					\N	f	\N	4	4	0	\N	0	28
1	1	9	DELFIM MOREIRA vvv	\N	               	0						\N		\N		2014-01-29 19:56:40.669-02	2014-03-19 20:35:10.686-03					\N	f	\N	4	3	0	\N	50	29
1	1	7	ARTUR BERNARDES	\N	               	0			\N	\N	\N	\N	\N	\N		2014-01-28 21:41:54.427-02	2014-03-13 23:39:14.637-03	\N	\N	\N	\N	\N	f	\N	1	1	0	\N	0	28
1	1	8	EPITÁCIO PESSOA 6	\N	               	1			\N	\N	\N	\N	\N	\N		2014-01-29 19:56:40.669-02	2014-03-13 23:39:14.637-03	\N	\N	\N	\N	\N	f	\N	1	1	0	\N	0	28
1	1	10	RODRIGUES ALVES 1	\N	               	0			88888	322465	69999	\N		\N		2014-01-29 19:58:03.577-02	2014-03-20 19:30:25.821-03				rmail	\N	f	\N	1	1	0	\N	0	29
1	1	11	VENCESLAU BRÁS 2	\N	               	0			\N	\N	\N	\N	85502070	\N		2014-01-29 19:58:43.66-02	2014-03-20 20:40:37.849-03	Rua Itacolomi 	 Amadori		\N	411850	f	\N	1	1	0	\N	0	30
1	1	13	AFONSO PENA	\N	88976699       	0	1234567890	AFONSO	65329865	32265326	99253265	\N	88905260	\N	Sr.	2014-01-29 22:24:04.412-02	2014-03-20 20:59:49.888-03	Principal	Centro	90	afonsopena@gmail.com	5346	f	\N	1	1	0	\N	0	32
1	1	1	PEDRO ALVARES CABRAL		99999999       	2	05153565659532	PEDRO	99999999999	3333333333	444444444	13131232	99999-999		Sr.	2014-01-28 20:24:57.964-02	2014-03-20 21:25:08.764-03	C	Jardim	90	email@gmail.com	3450	f	\N	5	2	0	0	100	32
1	1	2	SANTOS DUMONT	SANTOS 	85656895656    	1	32132132132	SANTOS	322465624	32265645	95646323	87484665	86565-599	AP 10	SEM OBSERVAÇÕES	2014-01-28 20:25:07.202-02	2014-03-20 21:29:42.983-03	CAXIAS	SANTA CRUZ	9865	SANTOSDUMONT@GMAIL.COM	65	f	1986-01-01	6	3	101	102	100	32
\.


--
-- TOC entry 2247 (class 0 OID 74436)
-- Dependencies: 200
-- Data for Name: devices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY devices (id, serial, dthrcadastro, dthralteracao, versao_android, email) FROM stdin;
7	355500041422007	2014-02-09 16:05:00.255-02	\N	2.3.6	hghk
8	355500041422007	2014-02-09 16:07:29.352-02	\N	2.3.6	test@gmail.com
9	355500041422007	2014-03-12 19:53:16.605-03	\N	2.3.6	ladairsmiderle@gmail.com
10	355500041422007	2014-03-12 21:52:37.574-03	\N	2.3.6	dfgh@trfh
11	355500041422007	2014-03-12 22:05:09.35-03	\N	2.3.6	icuffu@gmail.com
12	355500041422007	2014-03-13 22:48:40.555-03	\N	2.3.6	la@gmail.com
13	355500041422007	2014-03-13 23:00:44.321-03	\N	2.3.6	aa@gmail.com
14	355500041422007	2014-03-19 20:34:14.403-03	\N	2.3.6	lada@gmail.com
15	355500041422007	2014-03-20 20:19:43.366-03	\N	2.3.6	xunda@gmail.com
16	355500041422007	2014-03-20 20:45:11.662-03	\N	2.3.6	jeca@hdg.com
17	355500041422007	2014-03-20 20:53:54.17-03	\N	2.3.6	vcvc@tr.com
18	355500041422007	2014-03-23 20:16:40.357-03	\N	2.3.6	fffff@gms
19	355500041422007	2014-03-23 20:18:15.477-03	\N	2.3.6	tyui@gj
\.


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 199
-- Name: devices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('devices_id_seq', 19, true);


--
-- TOC entry 2216 (class 0 OID 41144)
-- Dependencies: 169
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY empresa (idempresa, fone, razaosocial, nomefantasia) FROM stdin;
2	999999              	FIAT                                                                                                                                                                                                    	MATERIAIS DE CONSTRUÇÃO FANTASIA                                                                                                                                                                        
1	322465010           	CHEVROLET                                                                                                                                                                                               	TESTE NOME FANTASIA                                                                                                                                                                                     
3	111111              	FORD                                                                                                                                                                                                    	FORD CARROS                                                                                                                                                                                             
\.


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 168
-- Name: empresa_idempresa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('empresa_idempresa_seq', 2, true);


--
-- TOC entry 2231 (class 0 OID 57509)
-- Dependencies: 184
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (idestado, nome, uf) FROM stdin;
1	Acre	AC
2	Alagoas	AL
3	Amazonas	AM
4	Amapá	AP
5	Bahia	BA
6	Ceará	CE
7	Distrito Federal	DF
8	Espirito Santo	ES
9	Goiás	GO
10	Maranhão	MA
11	Minas Gerais	MG
12	Mato Grosso do Sul	MS
13	Mato Grosso	MT
14	Pará	PA
15	Paraíba	PB
16	Pernambuco	PE
17	Piauí	PI
18	Paraná	PR
19	Rio de Janeiro	RJ
20	Rio Grande do Norte	RN
21	Rondônia	RO
22	Roraima	RR
23	Rio Grande do Sul	RS
24	Santa Catarina	SC
25	Sergipe	SE
26	São Paulo	SP
27	Tocantins	TO
\.


--
-- TOC entry 2217 (class 0 OID 41153)
-- Dependencies: 170
-- Data for Name: filial; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY filial (idempresa, idfilial, nomefantasia, razaosocial, fone, rua, bairro, numero, cep, fax, dthralteracao, website, dthrcadastro) FROM stdin;
2	1	FIPAL	FIAT PATO BRANCO	33333	\N	\N	\N	\N	\N	2013-12-09 21:35:58.284-02	\N	\N
2	2	FLORENÇA	FIAT CURITIBA	69666	\N	\N	\N	\N	\N	2013-12-09 21:35:58.284-02	\N	\N
1	2	METROSUL	CHEVROLLET CURITIBA	3226650	\N	\N	\N	\N	\N	2013-12-09 21:35:58.284-02	\N	\N
1	3	VALESUL	CHEVROLLET SÃO JOSÉ DOS PINHAIS	2313213	\N	\N	\N	\N	\N	2013-12-09 21:35:58.284-02	\N	\N
3	1	FORD PATO BRANCO	FORM RAZAO SOCIAL	7889987	\N	\N	\N	\N	\N	2013-12-09 21:35:58.284-02	\N	\N
1	1	VVL	CHEVROLLET PATO BRANCO	5555555	ITACOLOMI	MENINO DEUS	2490	85502070	3121321	2013-12-09 21:35:58.284-02	WWW.	\N
\.


--
-- TOC entry 2243 (class 0 OID 74319)
-- Dependencies: 196
-- Data for Name: filial_mobile_config; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY filial_mobile_config (idempresa, idfilial, exibe_estoque, estoque_negativo, cadastrar_cliente, email_pedido_cliente, email_pedido_admin, email_pedidos, acao_titulo_vencido, acao_limite_credito, dthralteracao, dias_vencimento) FROM stdin;
1	1	t	t	t	t	t	EMIAL@PEDIDOS.COM.BR	2	1	2013-12-09 22:14:46.874	\N
\.


--
-- TOC entry 2234 (class 0 OID 65754)
-- Dependencies: 187
-- Data for Name: grupprod; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupprod (idempresa, idfilial, idgrupo, descricao, dthralteracao, descmax) FROM stdin;
1	1	1	ENLATADOS	2013-12-10 21:05:57.062-02	\N
1	1	3	FRUTAS	2013-12-10 21:05:57.062-02	\N
1	1	4	COSMETICOS	2013-12-10 21:05:57.062-02	\N
1	1	5	BEBIDAS	2013-12-10 21:05:57.062-02	\N
1	1	7	ORTIFRUTI	2013-12-10 21:05:57.062-02	\N
1	1	8	PANIFICADORA	2013-12-10 21:05:57.062-02	\N
1	1	9	LIMPEZA	2013-12-10 21:05:57.062-02	\N
1	1	-1	PRODUTOS SEM GRUPO	2013-12-10 21:05:57.062-02	\N
1	2	1	GRUPO TESTE	2013-12-10 21:05:57.062-02	\N
1	2	2	SEGUNDO	2013-12-10 21:05:57.062-02	\N
1	1	10	TESTANDO	2013-12-10 21:05:57.062-02	10
1	1	11	NOVO	2013-12-10 21:05:57.062-02	33
1	1	45	NOVO GRUPO	2013-12-10 21:05:57.062-02	1
1	1	2	VERDURAS	2013-12-10 21:05:57.062-02	11
1	2	3	MECANICO	2013-12-10 21:05:57.062-02	30
3	1	1	grupo mesas	2013-12-10 21:05:57.062-02	100
1	2	-1	PRODUTOS SEM GRUPO	2013-12-10 21:05:57.062-02	\N
1	3	-1	PRODUTOS SEM GRUPO	2013-12-10 21:05:57.062-02	\N
1	1	6	AÇOUGUEe	2014-01-21 21:51:33.7-02	0
\.


--
-- TOC entry 2218 (class 0 OID 41164)
-- Dependencies: 171
-- Data for Name: gruprep; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gruprep (idgrupo, descricao, idempresa, idfilial, descmax, comicaovenda, visualizatodosclientes, minvenda, dthralteracao, dthrcadastro, grupoproduto) FROM stdin;
3	NOVO GRUPO	1	1	39	39	t	39	2013-12-10 21:21:44.057-02	2013-09-27 13:18:13.682-03	5
4	GRUPO PASCOA	1	1	7	7	t	7	2013-12-10 21:21:44.057-02	2013-10-08 19:01:07.311-03	-1,1,2,3,4,5,6,7,8,9
1	GRUPO PUNTO	2	1	10	10	f	10	2013-12-10 21:21:44.057-02	2013-10-15 19:43:29.872-03	\N
2	GRUPO LINEA	2	1	20	20	f	20	2013-12-10 21:21:44.057-02	2013-10-15 19:52:00.703-03	\N
1	GRUPO PALIO	2	2	10	10	f	10	2013-12-10 21:21:44.057-02	2013-10-15 19:52:45.961-03	\N
1	UNICO FILIAL 2	1	2	10	10	f	10	2013-12-10 21:21:44.057-02	2013-09-11 22:16:27.127-03	2
2	GRUPO COMPRAS	1	1	10	10	f	10	2013-12-10 21:21:44.057-02	2013-09-11 19:50:39.522-03	9,7,5
900	aaa	1	2	1	1	f	1	2013-12-10 21:21:44.057-02	2013-10-20 21:24:14.78-02	\N
5	TESTANDO HORA	1	1	10	10	f	10	2013-12-10 21:21:44.057-02	2013-11-13 18:58:38.453-02	\N
6	A3SDA1D3A1S3	1	1	1	1	f	1	2013-12-10 21:21:44.057-02	2013-11-13 18:59:24.268-02	-1,7
11	aaasss	1	1	\N	\N	\N	\N	2013-12-10 21:21:44.057-02	2013-11-13 19:01:34.982-02	\N
1	GRUPO VENDAS	1	1	367	38	t	37	2013-12-10 21:21:44.057-02	2013-09-11 19:50:19.252-03	-1,1,2,3,4,5,6,7,8,9
\.


--
-- TOC entry 2228 (class 0 OID 49341)
-- Dependencies: 181
-- Data for Name: gruprepparcela; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY gruprepparcela (idempresa, idfilial, idgrupo, idparcelamento, dthralteracao) FROM stdin;
1	1	3	2	2013-12-03 20:54:09.061-02
1	1	4	1	2013-12-03 20:54:09.061-02
1	1	4	2	2013-12-03 20:54:09.061-02
2	1	1	1	2013-12-03 20:54:09.061-02
2	1	2	1	2013-12-03 20:54:09.061-02
1	2	1	1	2013-12-03 20:54:09.061-02
1	1	2	1	2013-12-03 20:54:09.061-02
1	1	2	2	2013-12-03 20:54:09.061-02
1	1	6	1	2013-12-03 20:54:09.061-02
1	1	1	1	2013-12-04 19:53:24.194-02
1	1	1	2	2013-12-04 19:53:24.194-02
\.


--
-- TOC entry 2226 (class 0 OID 49296)
-- Dependencies: 179
-- Data for Name: grupreptabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY grupreptabpreco (idempresa, idfilial, idgrupo, idtabela, dthralteracao) FROM stdin;
1	1	4	1	2013-12-03 21:06:55.989-02
1	1	4	2	2013-12-03 21:06:55.989-02
1	1	4	3	2013-12-03 21:06:55.989-02
2	1	1	1	2013-12-03 21:06:55.989-02
2	1	1	2	2013-12-03 21:06:55.989-02
2	1	2	1	2013-12-03 21:06:55.989-02
2	2	1	1	2013-12-03 21:06:55.989-02
2	2	1	2	2013-12-03 21:06:55.989-02
1	2	1	1	2013-12-03 21:06:55.989-02
1	1	2	3	2013-12-03 21:06:55.989-02
1	1	6	1	2013-12-03 21:06:55.989-02
1	1	3	1	2013-12-03 21:06:55.989-02
1	1	3	3	2013-12-03 21:06:55.989-02
1	1	1	3	2013-12-04 19:53:24.194-02
1	1	1	2	2013-12-04 19:53:24.194-02
1	1	1	1	2013-12-04 19:53:24.194-02
\.


--
-- TOC entry 2242 (class 0 OID 74284)
-- Dependencies: 195
-- Data for Name: mensagem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mensagem (idempresa, representante_origem, representante_destino, mensagem, dthrcadastro, usuario_origem, usuario_destino, idmensagem, titulo) FROM stdin;
1	\N	5		2013-12-10 22:47:21.732-02	5	\N	1	Bom dia primeira mensagem
1	\N	1	Bom dia	2013-12-10 22:47:21.732-02	5	\N	2	Ola senhores
1	\N	3	Bom dia	2013-12-10 22:47:21.732-02	5	\N	3	Ola senhores
1	\N	2	hehehe	2013-12-10 22:47:21.732-02	5	\N	4	aaa
1	\N	3	ola	2013-12-10 22:47:21.732-02	5	\N	5	Ola 
1	\N	5	sadasdasd	2013-12-10 22:47:21.732-02	5	\N	7	ola
1	\N	4	sadasdasd	2013-12-10 22:47:21.732-02	5	\N	8	ola
1	\N	1	sadasdasd	2013-12-10 22:47:21.732-02	5	\N	9	ola
1	\N	1	ola	2013-12-10 22:47:21.732-02	5	\N	6	Ola 
\.


--
-- TOC entry 2220 (class 0 OID 41233)
-- Dependencies: 173
-- Data for Name: menuaplicacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY menuaplicacao (idmenu, icon, label, ordem, url, menupai, separador) FROM stdin;
31	\N	Empresa	1	/restrito/config/config_empresa.jsf	21	f
17	ui-icon-close	Sair	6	/j_spring_security_logout	\N	f
3	\N	Produto	2	\N	1	f
18	\N	Representante	3	\N	1	f
2	ui-icon-contact	Usuário	1	\N	1	f
7	\N	Usuários	1	/restrito/lista/lista_usuarios.jsf	2	f
20	\N	Representantes	2	/restrito/lista/lista_representante.jsf	18	f
19	\N	Grupo de Representante	1	/restrito/lista/lista_representante_grupo.jsf	18	f
8	\N	Grupo de Produtos	1	/restrito/lista/lista_produto_grupo.jsf	3	f
13	\N	Metas	2	\N	11	f
12	\N	Vendas	1	\N	11	f
21	\N	Configurações	1	\N	\N	f
1	ui-icon-document	Cadastros	2	\N	\N	f
15	\N	Smartphones	1	\N	14	f
4	ui-icon-pencil	Relatorios	3	\N	\N	f
11	\N	Consultas	4	\N	\N	f
16	ui-icon-contact	Sobre	6	\N	\N	f
14	\N	Outra coisa	5	\N	\N	f
22	\N	Trocar de Empresa	1	/restrito/selecao_empresa.jsf	21	f
23	\N	Pedido	1	\N	1	f
25	\N	Forma de Parcelamentos	2	/restrito/lista/lista_parcelamento.jsf	23	f
24	\N	Tabela de Preço	1	/restrito/lista/lista_tabela_preco.jsf	23	f
9	\N	Produtos	2	/restrito/lista/lista_produto.jsf	3	f
26	\N	Localização	3	/restrito/lista/representante_rota.jsf	18	f
27	\N	Pedido	7	\N	\N	f
29	\N	Clientes	3	/restrito/lista/lista_cliente.jsf	1	f
30	\N	Pedidos	3	/restrito/lista/lista_pedido.jsf	23	f
28	\N	Mensagem	8	/restrito/lista/lista_mensagem.jsf	\N	f
\.


--
-- TOC entry 2225 (class 0 OID 49273)
-- Dependencies: 178
-- Data for Name: parcelamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY parcelamento (idempresa, idfilial, idparcelamento, descricao, carencia, diasentreparcela, nroparcela, percentual, inativo, dthralteracao) FROM stdin;
1	2	1	30 DIAS	30	30	1	10	f	2013-12-10 23:09:28.81-02
2	1	1	AVISTA	0	0	1	0	f	2013-12-10 23:09:28.81-02
1	1	4	30x60	30	30	2	10	f	2013-12-10 23:09:28.81-02
1	1	6	AVISTA	0	1	1	1	f	2013-12-10 23:09:28.81-02
1	1	2	15 DIAS	15	15	1	1	t	2013-12-10 23:09:28.81-02
1	1	3	TESTE	2	2	2	2	f	2013-12-10 23:09:28.81-02
1	1	1	30x60x90x120	200	20	20	10	f	2013-12-10 23:09:28.81-02
\.


--
-- TOC entry 2238 (class 0 OID 74026)
-- Dependencies: 191
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pedido (idempresa, idfilial, idpedido, idcliente, dthremissao, idrepresentante, tabelapreco, parcelamento, valor_bruto, valor_liquido, desconto_total, status, dthralteracao, observacao) FROM stdin;
1	1	1	12	2014-03-19 22:26:13-03	29	3	4	55	55	0	\N	2014-03-19 22:26:13-03	
1	1	2	18	2014-03-20 18:39:42-03	29	3	4	52.299999999999997	51.799999999999997	0.5	\N	2014-03-20 18:39:42-03	Ola.ngfjgh
1	1	3	15	2014-03-20 14:40:27-03	29	1	1	1.8	1.8	0	\N	2014-03-20 14:40:27-03	
1	1	4	19	2014-03-20 07:49:42-03	29	3	4	33.450000000000003	33.450000000000003	0	\N	2014-03-20 07:49:42-03	
\.


--
-- TOC entry 2240 (class 0 OID 74070)
-- Dependencies: 193
-- Data for Name: pedido_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pedido_item (idempresa, idfilial, idpedido, idproduto, sequencia, quantidade, precovenda, desconto) FROM stdin;
1	1	1	25	1	1	55	0
1	1	2	3	1	2	25.649999999999999	0
1	1	2	10	2	1	0.5	0.5
1	1	3	21	1	2	0.90000000000000002	0
1	1	4	24	1	1	33.450000000000003	0
\.


--
-- TOC entry 2241 (class 0 OID 74092)
-- Dependencies: 194
-- Data for Name: pedido_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pedido_pagamento (idempresa, idfilial, idpedido, sequencia, data_vencimento, data_pagamento, valor_pago, valor_parcela) FROM stdin;
\.


--
-- TOC entry 2235 (class 0 OID 73931)
-- Dependencies: 188
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produto (idempresa, idfilial, idproduto, idgrupo, descricao, referencia, unidade, codbar, quantidade, descmax, precovenda, precopromocao, promocao, inativo, dtcadastro, dthralteracao) FROM stdin;
1	1	21	6	SALAME	SALAMINHO	1	1	7	1	1	1	f	f	2013-11-03 21:08:31.246	2014-03-20 21:47:29.28-03
1	1	2	4	batatinha	 taabasbsabd	1	1	9	1	12.890000000000001	1	f	f	2013-10-28 20:49:39.754	2014-03-20 21:47:29.28-03
1	1	8	5	rufles	salgadinho	PC	1	9	1	10	1	f	f	2013-10-28 21:15:41.686	2014-03-20 21:47:29.28-03
1	1	9	5	salgado	salgadinho	un	1	9	1	1	1	f	f	2013-10-28 21:17:35.224	2014-03-20 21:47:29.28-03
1	1	10	5	aaaa	ref	1	1	8	1	1	1	f	f	2013-10-28 21:19:57.814	2014-03-20 21:47:29.28-03
1	1	11	6	novo	aaa	un	1	9	1	1	1	f	f	2013-10-28 21:20:58.705	2014-03-20 21:47:29.28-03
1	1	12	6	carne	carne	CM	1	10	1	1	1	f	f	2013-10-28 21:26:29.207	2014-03-20 21:47:29.28-03
1	1	13	6	lalal	1	1	1	10	1	1	1	f	f	2013-10-28 21:27:55.608	2014-03-20 21:47:29.28-03
1	1	17	7	abacaxi	frutas	un	1	5	1	1	1	f	f	2013-10-28 21:35:31.735	2014-03-20 21:47:29.28-03
1	1	23	-1	raaaaaaa				10	0	0	0	f	f	2013-12-04 19:52:22.323	2014-03-20 21:47:29.28-03
1	1	0	1	qa	\N	\N	\N	10	\N	\N	\N	f	f	2014-01-21 20:08:31.125	2014-03-20 21:47:29.28-03
1	1	20	-1	MAÇA	10	UN	1	9	1	1	1	f	f	2013-11-03 20:47:45.961	2014-03-20 21:47:29.28-03
1	1	22	-1	CAMARÃO				2	0	0	0	f	f	2013-11-19 19:45:20.153	2014-03-20 21:47:29.28-03
1	1	25	-1	COXINHA DA ASA	FRIOS	PC	2546546546	94	100	55	33	f	f	2014-02-09 16:23:04.383	2014-03-20 21:47:29.28-03
1	1	24	-1	Fogão de Piso Atlas 4 Bocas Coliseum Glass Bivolt Branco	Fogão de Piso Atlas 4 Bocas Coliseum Glass Bivolt Branco	pc	1321321312	8	10	33.450000000000003	33.450000000000003	f	f	2013-12-11 21:42:15.728	2014-03-20 21:47:29.28-03
1	1	1	4	PRIMEIRO PRODUTO	REFERENCIAa	UN	123456789	6	30	13.23	90	f	f	2013-10-28 20:49:39.754	2014-03-20 21:47:29.28-03
1	1	3	4	REFRIGERANTE	lata	LT	10	7	10	25.649999999999999	10	f	f	2013-10-28 20:49:39.754	2014-03-20 21:47:29.28-03
1	1	4	4	CERVEJA	lata	LT	1	6	1	100	1	f	f	2013-10-28 20:50:15.574	2014-03-20 21:47:29.28-03
1	1	5	4	suco	lata	lt	1	10	1	2.1000000000000001	1	f	f	2013-10-28 20:55:07.601	2014-03-20 21:47:29.28-03
1	1	6	5	batata	111	sc	10	10	10	10.59	10	f	f	2013-10-28 20:55:48.066	2014-03-20 21:47:29.28-03
1	1	7	5	conde	10	un	1	10	1	50	0	f	f	2013-10-28 21:07:06.165	2014-03-20 21:47:29.28-03
1	1	14	7	aasda	asdas	1	1	7	1	1	1	f	f	2013-10-28 21:30:32.036	2014-03-20 21:47:29.28-03
1	1	15	7	caixa	ca	un	1	10	1	1	1	f	f	2013-10-28 21:32:41.309	2014-03-20 21:47:29.28-03
1	1	16	7	saca	aaa	un	1	10	1	1	1	f	f	2013-10-28 21:34:53.744	2014-03-20 21:47:29.28-03
1	1	18	7	caca	aa			10	0	0	0	f	f	2013-10-28 21:41:35.061	2014-03-20 21:47:29.28-03
1	1	19	8	aaaa	2	N	1	10	1	1	1	f	f	2013-10-28 21:51:06.006	2014-03-20 21:47:29.28-03
\.


--
-- TOC entry 2232 (class 0 OID 57544)
-- Dependencies: 185
-- Data for Name: repfilial; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY repfilial (idempresa, idfilial, idrepresentante, idgrupo, descmax, comicaovenda, visualizatodosclientes, minvenda, grupoproduto, dthralteracao) FROM stdin;
1	1	1	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	2	1	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	1	7	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	2	3	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
2	1	1	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	1	3	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	1	2	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	2	2	\N	10	10	f	10	\N	2013-12-12 22:04:07.939
1	1	5	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	2	6	\N	0	0	f	0	\N	2013-12-12 22:04:07.939
1	2	4	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	1	4	1	\N	\N	f	\N	\N	2013-12-12 22:04:07.939
1	1	8	\N	\N	\N	t	\N	\N	2014-02-09 13:31:22.755
1	1	9	\N	\N	\N	t	\N	\N	2014-02-09 13:32:11.152
1	1	10	\N	\N	\N	t	\N	\N	2014-02-09 13:34:39.025
1	1	11	\N	\N	\N	t	\N	\N	2014-02-09 13:37:30.704
1	1	12	\N	\N	\N	t	\N	\N	2014-02-09 14:31:02.51
1	1	13	\N	\N	\N	t	\N	\N	2014-02-09 14:31:52.289
1	1	14	\N	\N	\N	t	\N	\N	2014-02-09 14:34:05.916
1	1	15	\N	\N	\N	t	\N	\N	2014-02-09 14:34:58.614
1	1	16	\N	\N	\N	t	\N	\N	2014-02-09 14:35:21.345
1	1	17	\N	\N	\N	t	\N	\N	2014-02-09 15:03:10.961
1	1	18	\N	\N	\N	t	\N	\N	2014-02-09 15:05:53.45
1	1	19	\N	\N	\N	t	\N	\N	2014-02-09 15:07:26.929
1	1	20	\N	\N	\N	t	\N	\N	2014-02-09 15:10:04.651
1	1	21	\N	\N	\N	t	\N	\N	2014-02-09 15:58:23.793
1	1	22	\N	\N	\N	t	\N	\N	2014-02-09 16:05:01.23
1	1	23	\N	\N	\N	t	\N	\N	2014-02-09 16:07:29.355
1	1	24	\N	\N	\N	t	\N	\N	2014-03-12 19:53:17.752
1	1	25	\N	\N	\N	t	\N	\N	2014-03-12 21:52:37.577
1	1	26	\N	\N	\N	t	\N	\N	2014-03-12 22:05:09.352
1	1	27	\N	\N	\N	t	\N	\N	2014-03-13 22:48:41.672
1	1	28	\N	\N	\N	t	\N	\N	2014-03-13 23:00:45.282
1	1	29	\N	\N	\N	t	\N	\N	2014-03-19 20:34:15.967
1	1	30	\N	\N	\N	t	\N	\N	2014-03-20 20:19:43.386
1	1	31	\N	\N	\N	t	\N	\N	2014-03-20 20:45:11.663
1	1	32	\N	\N	\N	t	\N	\N	2014-03-20 20:53:54.173
1	1	33	\N	\N	\N	t	\N	\N	2014-03-23 20:16:41.701
1	1	34	\N	\N	\N	t	\N	\N	2014-03-23 20:18:15.48
\.


--
-- TOC entry 2229 (class 0 OID 49375)
-- Dependencies: 182
-- Data for Name: repparcela; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY repparcela (idempresa, idfilial, idrepresentante, idparcelamento, dthralteracao) FROM stdin;
1	1	22	1	\N
1	1	22	2	\N
1	1	22	3	\N
1	1	23	1	\N
1	1	23	2	\N
1	1	23	3	\N
1	1	24	1	\N
1	1	24	2	\N
1	1	24	3	\N
1	1	25	1	\N
1	1	25	2	\N
1	1	25	3	\N
1	1	26	1	\N
1	1	26	2	\N
1	1	26	3	\N
1	1	27	1	\N
1	1	27	2	\N
1	1	27	3	\N
1	1	28	1	\N
1	1	28	2	\N
1	1	28	3	\N
1	1	29	1	\N
1	1	29	2	\N
1	1	29	3	\N
1	1	30	1	\N
1	1	30	2	\N
1	1	30	3	\N
1	1	31	1	\N
1	1	31	2	\N
1	1	31	3	\N
1	1	32	1	\N
1	1	32	2	\N
1	1	32	3	\N
1	1	33	1	\N
1	1	33	2	\N
1	1	33	3	\N
1	1	34	1	\N
1	1	34	2	\N
1	1	34	3	\N
1	1	1	1	2013-12-12 22:04:41.843-02
1	1	1	2	2013-12-12 22:04:41.843-02
1	1	1	3	2013-12-12 22:04:41.843-02
1	1	8	1	\N
1	1	8	2	\N
1	1	8	3	\N
1	1	9	1	\N
1	1	9	2	\N
1	1	9	3	\N
1	1	10	1	\N
1	1	10	2	\N
1	1	10	3	\N
1	1	11	1	\N
1	1	11	2	\N
1	1	11	3	\N
1	1	12	1	\N
1	1	12	2	\N
1	1	12	3	\N
1	1	13	1	\N
1	1	13	2	\N
1	1	13	3	\N
1	1	14	1	\N
1	1	14	2	\N
1	1	14	3	\N
1	1	15	1	\N
1	1	15	2	\N
1	1	15	3	\N
1	1	16	1	\N
1	1	16	2	\N
1	1	16	3	\N
1	1	17	1	\N
1	1	17	2	\N
1	1	17	3	\N
1	1	18	1	\N
1	1	18	2	\N
1	1	18	3	\N
1	1	19	1	\N
1	1	19	2	\N
1	1	19	3	\N
1	1	20	1	\N
1	1	20	2	\N
1	1	20	3	\N
1	1	21	1	\N
1	1	21	2	\N
1	1	21	3	\N
\.


--
-- TOC entry 2223 (class 0 OID 41328)
-- Dependencies: 176
-- Data for Name: representante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY representante (idempresa, idrepresentante, idusuario, nome, login, senha, rua, bairro, cep, numero, observacao, cpfcnpj, placa, veiculo, contato, fonecomercial, foneresidencial, celular, fax, complemento, dthrcadastro, dthralteracao, cidade, tipopessoa, email, inscriestad, inativo, idcidade, dtnascimento) FROM stdin;
1	30	\N	demo30	demo30	demo30	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-20 20:19:43.366-03	2014-03-20 20:19:43.385-03	\N	1	xunda@gmail.com	\N	f	\N	\N
1	31	\N	demo31	demo31	demo31	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-20 20:45:11.662-03	2014-03-20 20:45:11.663-03	\N	1	jeca@hdg.com	\N	f	\N	\N
1	32	\N	demo32	demo32	demo32	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-20 20:53:54.17-03	2014-03-20 20:53:54.173-03	\N	1	vcvc@tr.com	\N	f	\N	\N
1	33	\N	demo33	demo33	demo33	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-23 20:16:40.357-03	2014-03-23 20:16:41.7-03	\N	1	fffff@gms	\N	f	\N	\N
1	34	\N	demo34	demo34	demo34	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-23 20:18:15.477-03	2014-03-23 20:18:15.48-03	\N	1	tyui@gj	\N	f	\N	\N
1	3	\N	MISTO	MISTO	1	itacolomi														\N	2013-12-12 22:04:18.101-02	4689	0	1		f	\N	\N
2	1	\N	GILBERTO	GIL	1															\N	2013-12-12 22:04:18.101-02	\N	1	1		f	\N	\N
1	2	\N	RIADAL	RIADAL	1						051.535.656-56									\N	2013-12-12 22:04:18.101-02	1468	1	2		t	\N	\N
1	5	\N	BADANHA	BAD	1															\N	2013-12-12 22:04:18.101-02	\N	1	1		f	\N	\N
1	6	\N	teste	teste	1															\N	2013-12-12 22:04:18.101-02	\N	1	1		f	\N	\N
1	4	\N	CAS	CAS	1															\N	2013-12-12 22:04:18.101-02	\N	1	1		f	\N	\N
1	1	\N	ladair	ladair	1						051.535.729-43	dar 5584	gol	ladair						\N	2013-12-12 22:04:18.101-02	3451	1	ladairsmiderle@gmail.com	85806165	f	\N	\N
1	7	\N	ivo	ivo	1															\N	2013-12-12 22:04:18.101-02	10	1	1		f	\N	\N
1	8	\N	demo8	demo8	demo8	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 13:31:22.752-02	2014-02-09 13:31:22.754-02	\N	1	\N	\N	f	\N	\N
1	9	\N	demo9	demo9	demo9	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 13:32:11.151-02	2014-02-09 13:32:11.152-02	\N	1	\N	\N	f	\N	\N
1	10	\N	demo10	demo10	demo10	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 13:34:03.076-02	2014-02-09 13:34:39.023-02	\N	1	\N	\N	f	\N	\N
1	11	\N	demo11	demo11	demo11	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 13:37:23.714-02	2014-02-09 13:37:30.703-02	\N	1	\N	\N	f	\N	\N
1	12	\N	demo12	demo12	demo12	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 14:31:01.525-02	2014-02-09 14:31:02.509-02	\N	1	la	\N	f	\N	\N
1	13	\N	demo13	demo13	demo13	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 14:31:52.287-02	2014-02-09 14:31:52.289-02	\N	1	la	\N	f	\N	\N
1	14	\N	demo14	demo14	demo14	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 14:34:05.915-02	2014-02-09 14:34:05.915-02	\N	1	la@	\N	f	\N	\N
1	15	\N	demo15	demo15	demo15	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 14:34:58.613-02	2014-02-09 14:34:58.614-02	\N	1	la@	\N	f	\N	\N
1	16	\N	demo16	demo16	demo16	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 14:35:20.375-02	2014-02-09 14:35:21.344-02	\N	1	la@	\N	f	\N	\N
1	17	\N	demo17	demo17	demo17	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 15:03:09.986-02	2014-02-09 15:03:10.96-02	\N	1	la	\N	f	\N	\N
1	18	\N	demo18	demo18	demo18	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 15:05:53.449-02	2014-02-09 15:05:53.45-02	\N	1	ku	\N	f	\N	\N
1	19	\N	demo19	demo19	demo19	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 15:07:26.928-02	2014-02-09 15:07:26.929-02	\N	1	tt	\N	f	\N	\N
1	20	\N	demo20	demo20	demo20	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 15:10:04.65-02	2014-02-09 15:10:04.651-02	\N	1	jj	\N	f	\N	\N
1	21	\N	demo21	demo21	demo21	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 15:58:22.823-02	2014-02-09 15:58:23.793-02	\N	1	la@yyft.com	\N	f	\N	\N
1	22	\N	demo22	demo22	demo22	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 16:05:00.255-02	2014-02-09 16:05:01.229-02	\N	1	hghk	\N	f	\N	\N
1	23	\N	demo23	demo23	demo23	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-02-09 16:07:29.352-02	2014-02-09 16:07:29.354-02	\N	1	test@gmail.com	\N	f	\N	\N
1	24	\N	demo24	demo24	demo24	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-12 19:53:16.605-03	2014-03-12 19:53:17.751-03	\N	1	ladairsmiderle@gmail.com	\N	f	\N	\N
1	25	\N	demo25	demo25	demo25	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-12 21:52:37.574-03	2014-03-12 21:52:37.576-03	\N	1	dfgh@trfh	\N	f	\N	\N
1	26	\N	demo26	demo26	demo26	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-12 22:05:09.35-03	2014-03-12 22:05:09.352-03	\N	1	icuffu@gmail.com	\N	f	\N	\N
1	27	\N	demo27	demo27	demo27	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-13 22:48:40.555-03	2014-03-13 22:48:41.671-03	\N	1	la@gmail.com	\N	f	\N	\N
1	28	\N	demo28	demo28	demo28	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-13 23:00:44.321-03	2014-03-13 23:00:45.281-03	\N	1	aa@gmail.com	\N	f	\N	\N
1	29	\N	demo29	demo29	demo29	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	\N	2014-03-19 20:34:14.403-03	2014-03-19 20:34:15.966-03	\N	1	lada@gmail.com	\N	f	\N	\N
\.


--
-- TOC entry 2236 (class 0 OID 73982)
-- Dependencies: 189
-- Data for Name: representanterota; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY representanterota (idempresa, idrepresentante, dthrrota, latitude, longitude, hrinsert) FROM stdin;
1	1	2014-01-15 22:28:39.443-02	-25.7440317	-53.05221265	2014-01-15 23:11:07.063-02
1	1	2014-01-15 23:11:18.414-02	-25.7441798666667	-53.05243215	2014-01-16 21:14:15.088-02
1	1	2014-01-16 07:30:58.193-02	-25.7437227333333	-53.05248055	2014-01-16 21:14:15.088-02
1	1	2014-01-16 07:30:58.898-02	-25.7437227333333	-53.05248055	2014-01-16 21:14:15.088-02
1	1	2014-01-16 11:56:31.852-02	-25.7468832166667	-53.0535132333333	2014-01-16 21:14:15.088-02
1	1	2014-01-17 08:22:15.08-02	-25.74694675	-53.0535890833333	2014-01-20 20:22:16.842-02
1	1	2014-01-17 08:22:20.17-02	-25.74694675	-53.0535890833333	2014-01-20 20:22:16.842-02
\.


--
-- TOC entry 2227 (class 0 OID 49319)
-- Dependencies: 180
-- Data for Name: reptabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY reptabpreco (idempresa, idfilial, idrepresentante, idtabela, dthralteracao) FROM stdin;
1	1	22	1	2014-02-09 16:05:01.232-02
1	1	22	2	2014-02-09 16:05:01.232-02
1	1	22	3	2014-02-09 16:05:01.233-02
1	1	23	1	2014-02-09 16:07:29.355-02
1	1	23	2	2014-02-09 16:07:29.355-02
1	1	23	3	2014-02-09 16:07:29.355-02
1	1	24	1	2014-03-12 19:53:17.756-03
1	1	24	2	2014-03-12 19:53:17.756-03
1	1	24	3	2014-03-12 19:53:17.756-03
1	1	25	1	2014-03-12 21:52:37.577-03
1	1	25	2	2014-03-12 21:52:37.577-03
1	1	25	3	2014-03-12 21:52:37.577-03
1	1	26	1	2014-03-12 22:05:09.352-03
1	1	26	2	2014-03-12 22:05:09.352-03
1	1	26	3	2014-03-12 22:05:09.352-03
1	1	27	1	2014-03-13 22:48:41.677-03
1	1	27	2	2014-03-13 22:48:41.677-03
1	1	27	3	2014-03-13 22:48:41.677-03
1	1	28	1	2014-03-13 23:00:45.284-03
1	1	28	2	2014-03-13 23:00:45.284-03
1	1	28	3	2014-03-13 23:00:45.284-03
1	1	29	1	2014-03-19 20:34:15.972-03
1	1	29	2	2014-03-19 20:34:15.972-03
1	1	29	3	2014-03-19 20:34:15.972-03
1	1	30	1	2014-03-20 20:19:43.386-03
1	1	30	2	2014-03-20 20:19:43.386-03
1	1	30	3	2014-03-20 20:19:43.386-03
1	1	31	1	2014-03-20 20:45:11.664-03
1	1	31	2	2014-03-20 20:45:11.664-03
1	1	31	3	2014-03-20 20:45:11.664-03
1	1	32	1	2014-03-20 20:53:54.174-03
1	1	32	2	2014-03-20 20:53:54.174-03
1	1	32	3	2014-03-20 20:53:54.174-03
1	1	33	1	2014-03-23 20:16:41.706-03
1	1	33	2	2014-03-23 20:16:41.706-03
1	1	33	3	2014-03-23 20:16:41.706-03
1	1	34	1	2014-03-23 20:18:15.481-03
1	1	34	2	2014-03-23 20:18:15.481-03
1	1	34	3	2014-03-23 20:18:15.481-03
1	1	1	1	2013-12-12 22:04:56.344-02
1	1	1	2	2013-12-12 22:04:56.344-02
1	1	1	3	2013-12-12 22:04:56.344-02
1	1	8	1	2014-02-09 13:31:22.756-02
1	1	8	2	2014-02-09 13:31:22.756-02
1	1	8	3	2014-02-09 13:31:22.756-02
1	1	9	1	2014-02-09 13:32:11.153-02
1	1	9	2	2014-02-09 13:32:11.153-02
1	1	9	3	2014-02-09 13:32:11.153-02
1	1	10	1	2014-02-09 13:34:39.039-02
1	1	10	2	2014-02-09 13:34:39.039-02
1	1	10	3	2014-02-09 13:34:39.039-02
1	1	11	1	2014-02-09 13:37:30.719-02
1	1	11	2	2014-02-09 13:37:30.719-02
1	1	11	3	2014-02-09 13:37:30.719-02
1	1	12	1	2014-02-09 14:31:02.513-02
1	1	12	2	2014-02-09 14:31:02.513-02
1	1	12	3	2014-02-09 14:31:02.513-02
1	1	13	1	2014-02-09 14:31:52.289-02
1	1	13	2	2014-02-09 14:31:52.29-02
1	1	13	3	2014-02-09 14:31:52.29-02
1	1	14	1	2014-02-09 14:34:05.916-02
1	1	14	2	2014-02-09 14:34:05.916-02
1	1	14	3	2014-02-09 14:34:05.916-02
1	1	15	1	2014-02-09 14:34:58.615-02
1	1	15	2	2014-02-09 14:34:58.615-02
1	1	15	3	2014-02-09 14:34:58.615-02
1	1	16	1	2014-02-09 14:35:21.347-02
1	1	16	2	2014-02-09 14:35:21.347-02
1	1	16	3	2014-02-09 14:35:21.347-02
1	1	17	1	2014-02-09 15:03:10.963-02
1	1	17	2	2014-02-09 15:03:10.963-02
1	1	17	3	2014-02-09 15:03:10.963-02
1	1	18	1	2014-02-09 15:05:53.45-02
1	1	18	2	2014-02-09 15:05:53.45-02
1	1	18	3	2014-02-09 15:05:53.45-02
1	1	19	1	2014-02-09 15:07:26.93-02
1	1	19	2	2014-02-09 15:07:26.93-02
1	1	19	3	2014-02-09 15:07:26.93-02
1	1	20	1	2014-02-09 15:10:04.651-02
1	1	20	2	2014-02-09 15:10:04.651-02
1	1	20	3	2014-02-09 15:10:04.651-02
1	1	21	1	2014-02-09 15:58:23.796-02
1	1	21	2	2014-02-09 15:58:23.796-02
1	1	21	3	2014-02-09 15:58:23.796-02
\.


--
-- TOC entry 2245 (class 0 OID 74394)
-- Dependencies: 198
-- Data for Name: sincronizacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sincronizacao (idsincronizacao, idfilial, idempresa, idrepresentante, dthrsincronizacao, tabela, sinccompleta) FROM stdin;
1	1	1	1	2013-11-13 21:08:10.385-02	CLIENTE	t
2	1	1	1	2013-11-26 23:10:02.537-02	CLIENTE	f
3	1	1	1	2013-11-26 23:11:10.352-02	CLIENTE	f
4	1	1	1	2013-11-26 23:21:07.737-02	CLIENTE	f
5	1	1	1	2013-11-26 23:21:26.464-02	CLIENTE	f
6	1	1	1	2013-11-26 23:21:32.937-02	CLIENTE	f
7	1	1	1	2013-11-27 20:40:33.06-02	CLIENTE	f
8	1	1	1	2013-11-27 20:40:50.148-02	CLIENTE	f
9	1	1	1	2013-11-27 20:41:42.019-02	CLIENTE	f
10	1	1	1	2013-11-27 20:42:01.141-02	CLIENTE	f
11	1	1	1	2013-11-27 20:42:01.23-02	CLIENTE	f
12	1	1	1	2013-11-27 20:42:05.189-02	CLIENTE	f
13	1	1	1	2013-11-28 09:04:41.611-02	CLIENTE	f
14	1	1	1	2013-11-28 09:04:42.315-02	CLIENTE	f
15	1	1	1	2013-11-28 09:22:30.208-02	CLIENTE	f
16	1	1	1	2013-11-28 09:22:39.885-02	CLIENTE	f
17	1	1	1	2013-11-28 09:22:44.049-02	CLIENTE	f
18	1	1	1	2013-11-28 09:24:57.049-02	CLIENTE	f
21	1	1	1	2013-11-28 09:57:03.03-02	\N	f
22	1	1	1	2013-11-28 09:58:21.777-02	\N	f
23	1	1	1	2013-11-28 09:58:53.74-02	\N	f
24	1	1	1	2013-11-28 10:00:04.067-02	\N	f
25	1	1	1	2013-11-28 14:06:26.108-02	\N	f
26	1	1	1	2013-11-28 14:06:39.333-02	\N	f
27	1	1	1	2013-11-28 14:12:19.959-02	\N	f
28	1	1	1	2013-11-28 14:39:37.456-02	\N	f
29	1	1	1	2013-11-28 14:45:31.758-02	\N	f
30	1	1	1	2013-11-28 14:47:16.354-02	\N	f
31	1	1	1	2013-11-28 14:50:43.899-02	\N	f
32	1	1	1	2013-11-28 14:51:38.701-02	\N	f
33	1	1	1	2013-11-28 15:05:42.851-02	\N	f
34	1	1	1	2013-11-28 15:07:27.706-02	\N	f
35	1	1	1	2013-11-28 15:10:07.938-02	\N	f
36	1	1	1	2013-11-28 15:12:24.565-02	\N	f
37	1	1	1	2013-11-28 15:18:54.206-02	\N	f
38	1	1	1	2013-11-28 15:19:07.247-02	\N	f
39	1	1	1	2013-11-28 15:19:10.764-02	\N	f
40	1	1	1	2013-11-28 15:19:12.909-02	\N	f
41	1	1	1	2013-11-28 15:19:14.639-02	\N	f
42	1	1	1	2013-11-28 15:19:16.715-02	\N	f
43	1	1	1	2013-11-28 15:19:18.206-02	\N	f
44	1	1	1	2013-11-28 15:20:33.635-02	\N	f
45	1	1	1	2013-11-28 15:20:40.053-02	\N	f
46	1	1	1	2013-11-28 15:21:44.943-02	\N	f
47	1	1	1	2013-11-28 15:22:04.016-02	\N	f
48	1	1	1	2013-11-28 15:22:23.81-02	\N	f
49	1	1	1	2013-11-28 15:23:26.518-02	\N	f
50	1	1	1	2013-11-28 15:23:32.701-02	\N	f
51	1	1	1	2013-11-28 15:24:00.9-02	\N	f
52	1	1	1	2013-11-28 15:24:05.436-02	\N	f
53	1	1	1	2013-11-28 15:24:06.467-02	\N	f
54	1	1	1	2013-11-28 15:24:06.593-02	\N	f
55	1	1	1	2013-11-28 15:24:13.996-02	\N	f
56	1	1	1	2013-11-28 15:24:15.037-02	\N	f
57	1	1	1	2013-11-28 15:24:15.214-02	\N	f
58	1	1	1	2013-11-28 15:24:15.318-02	\N	f
59	1	1	1	2013-11-28 15:24:15.483-02	\N	f
60	1	1	1	2013-11-28 15:24:15.649-02	\N	f
61	1	1	1	2013-11-28 15:24:15.801-02	\N	f
62	1	1	1	2013-11-28 15:24:15.91-02	\N	f
63	1	1	1	2013-11-28 15:24:16.097-02	\N	f
64	1	1	1	2013-11-28 15:24:16.208-02	\N	f
65	1	1	1	2013-11-28 15:24:16.381-02	\N	f
66	1	1	1	2013-11-28 15:24:16.494-02	\N	f
67	1	1	1	2013-11-28 15:24:16.676-02	\N	f
68	1	1	1	2013-11-28 15:24:16.819-02	\N	f
69	1	1	1	2013-11-28 15:24:17.006-02	\N	f
70	1	1	1	2013-11-28 15:24:17.242-02	\N	f
71	1	1	1	2013-11-28 15:24:17.424-02	\N	f
72	1	1	1	2013-11-28 15:24:17.626-02	\N	f
73	1	1	1	2013-11-28 15:24:17.736-02	\N	f
74	1	1	1	2013-11-28 15:24:17.886-02	\N	f
75	1	1	1	2013-11-28 15:24:18.048-02	\N	f
76	1	1	1	2013-11-28 15:24:18.146-02	\N	f
77	1	1	1	2013-11-28 15:24:19.911-02	\N	f
78	1	1	1	2013-11-28 15:24:20.069-02	\N	f
79	1	1	1	2013-11-28 15:24:20.216-02	\N	f
80	1	1	1	2013-11-28 15:24:20.309-02	\N	f
81	1	1	1	2013-11-28 15:24:20.415-02	\N	f
82	1	1	1	2013-11-28 15:24:20.518-02	\N	f
83	1	1	1	2013-11-28 15:24:20.653-02	\N	f
84	1	1	1	2013-11-28 15:24:20.817-02	\N	f
85	1	1	1	2013-11-28 15:24:20.931-02	\N	f
86	1	1	1	2013-11-28 15:24:21.12-02	\N	f
87	1	1	1	2013-11-28 15:24:21.227-02	\N	f
88	1	1	1	2013-11-28 15:24:27.851-02	\N	f
89	1	1	1	2013-11-28 15:24:28.296-02	\N	f
90	1	1	1	2013-11-28 15:24:34.015-02	\N	f
91	1	1	1	2013-11-28 15:24:47.712-02	\N	f
92	1	1	1	2013-11-28 15:24:48.347-02	\N	f
93	1	1	1	2013-11-28 15:24:53.728-02	\N	f
94	1	1	1	2013-11-28 15:24:54.348-02	\N	f
95	1	1	1	2013-11-28 15:24:55.157-02	\N	f
96	1	1	1	2013-11-28 15:24:56.266-02	\N	f
97	1	1	1	2013-11-28 03:25:38.246-02	\N	f
98	1	1	1	2013-11-28 03:25:39.38-02	\N	f
99	1	1	1	2013-11-28 03:25:40.836-02	\N	f
100	1	1	1	2013-11-28 03:25:41.409-02	\N	f
101	1	1	1	2013-11-28 03:25:41.98-02	\N	f
102	1	1	1	2013-11-28 15:26:00.694-02	\N	f
103	1	1	1	2013-11-28 15:26:01.378-02	\N	f
104	1	1	1	2013-11-28 15:26:40.521-02	\N	f
105	1	1	1	2013-11-28 16:03:56.448-02	\N	f
106	1	1	1	2013-11-28 16:04:40.063-02	\N	f
107	1	1	1	2013-11-28 16:08:42.648-02	\N	f
108	1	1	1	2013-11-28 16:09:35.771-02	\N	f
109	1	1	1	2013-11-28 16:10:10.466-02	\N	f
110	1	1	1	2013-11-28 16:19:18.718-02	\N	f
111	1	1	1	2013-11-28 16:20:04.706-02	\N	f
112	1	1	1	2013-11-28 16:20:39.16-02	\N	f
113	1	1	1	2013-11-28 16:23:12.574-02	\N	f
114	1	1	1	2013-11-28 16:23:53.843-02	\N	f
115	1	1	1	2013-11-28 16:26:27.491-02	\N	f
116	1	1	1	2013-11-28 16:27:07.597-02	\N	f
117	1	1	1	2013-11-28 16:29:07.059-02	\N	f
118	1	1	1	2013-11-28 16:31:07.88-02	\N	f
119	1	1	1	2013-11-28 16:33:45.432-02	\N	f
120	1	1	1	2013-11-28 16:37:15.964-02	\N	f
121	1	1	1	2013-11-28 16:38:40.511-02	\N	f
122	1	1	1	2013-11-28 16:41:40.447-02	\N	f
123	1	1	1	2013-11-28 16:41:47.41-02	\N	f
124	1	1	1	2013-11-28 16:47:08.592-02	\N	f
125	1	1	1	2013-11-28 16:49:11.932-02	\N	f
126	1	1	1	2013-11-28 16:50:44.923-02	\N	f
127	1	1	1	2013-11-28 16:51:05.637-02	\N	f
128	1	1	1	2013-11-28 16:51:07.27-02	\N	f
129	1	1	1	2013-11-28 16:51:07.877-02	\N	f
130	1	1	1	2013-11-28 16:51:08.116-02	\N	f
131	1	1	1	2013-11-28 16:51:08.421-02	\N	f
132	1	1	1	2013-11-28 16:51:08.644-02	\N	f
133	1	1	1	2013-11-28 16:51:08.892-02	\N	f
134	1	1	1	2013-11-28 16:51:09.354-02	\N	f
135	1	1	1	2013-11-28 16:51:09.576-02	\N	f
136	1	1	1	2013-11-28 16:51:25.364-02	\N	f
137	1	1	1	2013-11-28 16:51:26.37-02	\N	f
138	1	1	1	2013-11-28 16:51:29.415-02	\N	f
139	1	1	1	2013-11-28 16:51:29.678-02	\N	f
140	1	1	1	2013-11-28 16:51:29.942-02	\N	f
141	1	1	1	2013-11-28 16:51:30.144-02	\N	f
142	1	1	1	2013-11-28 16:51:30.349-02	\N	f
143	1	1	1	2013-11-28 16:51:30.56-02	\N	f
144	1	1	1	2013-11-28 16:51:30.761-02	\N	f
145	1	1	1	2013-11-28 16:51:30.948-02	\N	f
146	1	1	1	2013-11-28 16:51:31.095-02	\N	f
147	1	1	1	2013-11-28 16:51:31.279-02	\N	f
148	1	1	1	2013-11-28 16:51:34.104-02	\N	f
149	1	1	1	2013-11-28 16:51:34.617-02	\N	f
150	1	1	1	2013-11-28 16:51:34.743-02	\N	f
151	1	1	1	2013-11-28 16:51:34.824-02	\N	f
152	1	1	1	2013-11-28 16:51:34.894-02	\N	f
153	1	1	1	2013-11-28 16:51:34.967-02	\N	f
154	1	1	1	2013-11-28 16:51:35.039-02	\N	f
155	1	1	1	2013-11-28 16:51:35.107-02	\N	f
156	1	1	1	2013-11-28 16:51:35.176-02	\N	f
157	1	1	1	2013-11-28 16:51:35.318-02	\N	f
158	1	1	1	2013-11-28 16:51:35.399-02	\N	f
159	1	1	1	2013-11-28 16:51:35.478-02	\N	f
160	1	1	1	2013-11-28 16:51:35.546-02	\N	f
161	1	1	1	2013-11-28 16:51:35.612-02	\N	f
162	1	1	1	2013-11-28 16:51:35.68-02	\N	f
163	1	1	1	2013-11-28 16:51:35.746-02	\N	f
164	1	1	1	2013-11-28 16:51:35.814-02	\N	f
165	1	1	1	2013-11-28 16:51:35.881-02	\N	f
166	1	1	1	2013-11-28 16:51:36.027-02	\N	f
167	1	1	1	2013-11-28 16:51:36.097-02	\N	f
168	1	1	1	2013-11-28 16:51:36.166-02	\N	f
169	1	1	1	2013-11-28 16:51:36.233-02	\N	f
170	1	1	1	2013-11-28 16:51:36.338-02	\N	f
171	1	1	1	2013-11-28 16:57:32.653-02	\N	f
172	1	1	1	2013-11-28 17:04:11.001-02	\N	f
173	1	1	1	2013-11-28 17:20:19.687-02	\N	f
174	1	1	1	2013-11-28 17:20:27.884-02	\N	f
175	1	1	1	2013-11-28 17:20:31.988-02	\N	f
176	1	1	1	2013-11-28 17:22:02.242-02	\N	f
177	1	1	1	2013-11-28 17:23:21.964-02	\N	f
178	1	1	1	2013-11-28 17:28:16.64-02	\N	f
179	1	1	1	2013-11-28 19:32:26.128-02	\N	f
180	1	1	1	2013-11-28 19:32:28.243-02	\N	f
181	1	1	1	2013-11-28 19:32:29.111-02	\N	f
182	1	1	1	2013-11-28 19:33:07.575-02	\N	f
183	1	1	1	2013-11-28 19:34:00.751-02	\N	f
184	1	1	1	2013-11-28 19:34:05.297-02	\N	f
185	1	1	1	2013-11-28 19:34:07.099-02	\N	f
186	1	1	1	2013-11-28 19:34:07.893-02	\N	f
187	1	1	1	2013-11-28 19:34:08.521-02	\N	f
188	1	1	1	2013-11-28 19:34:10.789-02	\N	f
189	1	1	1	2013-11-28 19:34:11.086-02	\N	f
190	1	1	1	2013-11-28 19:34:11.39-02	\N	f
191	1	1	1	2013-11-28 19:34:11.649-02	\N	f
192	1	1	1	2013-11-28 19:34:11.93-02	\N	f
193	1	1	1	2013-11-28 19:34:12.21-02	\N	f
194	1	1	1	2013-11-28 19:34:14.332-02	\N	f
195	1	1	1	2013-11-28 19:34:15.241-02	\N	f
196	1	1	1	2013-11-28 19:34:15.314-02	\N	f
197	1	1	1	2013-11-28 19:34:15.896-02	\N	f
198	1	1	1	2013-11-28 19:34:17.659-02	\N	f
199	1	1	1	2013-11-28 19:34:18.371-02	\N	f
200	1	1	1	2013-11-28 19:34:19.077-02	\N	f
201	1	1	1	2013-11-28 19:34:19.745-02	\N	f
202	1	1	1	2013-11-28 19:34:20.026-02	\N	f
203	1	1	1	2013-11-28 19:34:20.272-02	\N	f
204	1	1	1	2013-11-28 19:34:20.471-02	\N	f
205	1	1	1	2013-11-28 19:34:20.817-02	\N	f
206	1	1	1	2013-11-28 19:34:21.048-02	\N	f
207	1	1	1	2013-11-28 19:34:22.085-02	\N	f
208	1	1	1	2013-11-28 19:34:23.491-02	\N	f
209	1	1	1	2013-11-28 19:34:23.615-02	\N	f
210	1	1	1	2013-11-28 19:34:23.831-02	\N	f
211	1	1	1	2013-11-28 19:34:24.096-02	\N	f
212	1	1	1	2013-11-28 19:34:24.372-02	\N	f
213	1	1	1	2013-11-28 19:34:24.631-02	\N	f
214	1	1	1	2013-11-28 19:34:24.901-02	\N	f
215	1	1	1	2013-11-28 19:34:25.134-02	\N	f
216	1	1	1	2013-11-28 19:34:25.391-02	\N	f
217	1	1	1	2013-11-28 19:34:25.641-02	\N	f
218	1	1	1	2013-11-28 19:34:25.901-02	\N	f
219	1	1	1	2013-11-28 19:34:26.14-02	\N	f
220	1	1	1	2013-11-28 19:34:26.363-02	\N	f
221	1	1	1	2013-11-28 19:34:26.606-02	\N	f
222	1	1	1	2013-11-28 19:34:26.826-02	\N	f
223	1	1	1	2013-11-28 19:34:27.082-02	\N	f
224	1	1	1	2013-11-28 19:34:27.3-02	\N	f
225	1	1	1	2013-11-28 19:34:27.542-02	\N	f
226	1	1	1	2013-11-28 19:34:27.711-02	\N	f
227	1	1	1	2013-11-28 19:34:27.976-02	\N	f
228	1	1	1	2013-11-28 19:34:28.089-02	\N	f
229	1	1	1	2013-11-28 19:34:28.256-02	\N	f
230	1	1	1	2013-11-28 19:34:28.389-02	\N	f
231	1	1	1	2013-11-28 19:34:28.57-02	\N	f
232	1	1	1	2013-11-28 19:34:28.723-02	\N	f
233	1	1	1	2013-11-28 19:34:28.913-02	\N	f
234	1	1	1	2013-11-28 19:34:29.561-02	\N	f
235	1	1	1	2013-11-28 19:34:29.669-02	\N	f
236	1	1	1	2013-11-28 19:34:29.803-02	\N	f
237	1	1	1	2013-11-28 19:34:29.919-02	\N	f
238	1	1	1	2013-11-28 19:34:30.093-02	\N	f
239	1	1	1	2013-11-28 19:34:30.167-02	\N	f
240	1	1	1	2013-11-28 19:34:30.431-02	\N	f
241	1	1	1	2013-11-28 19:34:56.417-02	\N	f
242	1	1	1	2013-11-28 19:34:58.638-02	\N	f
243	1	1	1	2013-11-28 19:35:40.388-02	\N	f
244	1	1	1	2013-11-28 19:40:39.421-02	\N	f
245	1	1	1	2013-11-28 19:42:49.024-02	\N	f
246	1	1	1	2013-12-09 21:21:40.352-02	\N	f
247	1	1	1	2013-12-09 21:22:34.609-02	\N	f
248	1	1	1	2013-12-09 21:23:11.546-02	\N	f
249	1	1	1	2013-12-09 21:23:26.346-02	\N	f
250	1	1	1	2013-12-09 21:23:48.623-02	\N	f
251	1	1	1	2013-12-09 21:24:42.101-02	\N	f
252	1	1	1	2013-12-09 21:25:03.28-02	\N	f
253	1	1	1	2013-12-09 21:26:16.445-02	\N	f
254	1	1	1	2013-12-09 21:26:48.03-02	\N	f
255	1	1	1	2013-12-09 21:31:21.495-02	\N	f
256	1	1	1	2013-12-09 21:32:06.89-02	\N	f
257	1	1	1	2013-12-09 21:32:59.76-02	\N	f
258	1	1	1	2013-12-09 21:34:53.364-02	\N	f
259	1	1	1	2013-12-09 21:35:48.099-02	\N	f
260	1	1	1	2013-12-09 21:36:07.967-02	\N	f
261	1	1	1	2013-12-09 22:15:01.142-02	\N	f
262	1	1	1	2013-12-09 22:18:42.319-02	\N	f
263	1	1	1	2013-12-09 22:19:18.36-02	\N	f
264	1	1	1	2013-12-10 21:06:53.93-02	\N	f
265	1	1	1	2013-12-10 21:11:58.077-02	\N	f
266	1	1	1	2013-12-10 21:13:07.303-02	\N	f
267	1	1	1	2013-12-10 21:15:12.044-02	\N	f
268	1	1	1	2013-12-10 21:15:46.411-02	\N	f
269	1	1	1	2013-12-10 21:17:29.632-02	\N	f
270	1	1	1	2013-12-10 21:20:55.796-02	\N	f
271	1	1	1	2013-12-10 21:21:08.635-02	\N	f
272	1	1	1	2013-12-10 21:21:50.278-02	\N	f
273	1	1	1	2013-12-10 22:07:46.287-02	\N	f
274	1	1	1	2013-12-10 22:10:42.457-02	\N	f
275	1	1	1	2013-12-10 22:11:30.02-02	\N	f
276	1	1	1	2013-12-10 22:40:09.302-02	\N	f
277	1	1	1	2013-12-10 22:41:36.658-02	\N	f
278	1	1	1	2013-12-10 22:46:33.369-02	\N	f
279	1	1	1	2013-12-10 22:47:29.19-02	\N	f
280	1	1	1	2013-12-10 22:49:06.709-02	\N	f
281	1	1	1	2013-12-10 22:49:21.758-02	\N	f
282	1	1	1	2013-12-10 23:10:17.365-02	\N	f
283	1	1	1	2013-12-11 20:03:58.485-02	\N	f
284	1	1	1	2013-12-11 20:06:02.259-02	\N	f
285	1	1	1	2013-12-11 20:06:40.921-02	\N	f
286	1	1	1	2013-12-11 20:08:35.13-02	\N	f
287	1	1	1	2013-12-11 21:00:16.726-02	\N	f
288	1	1	1	2013-12-11 21:02:04.811-02	\N	f
289	1	1	1	2013-12-11 21:03:28.906-02	\N	f
290	1	1	1	2013-12-11 21:04:50.526-02	\N	f
291	1	1	1	2013-12-11 21:06:25.783-02	\N	f
292	1	1	1	2013-12-11 21:07:47.414-02	\N	f
293	1	1	1	2013-12-11 21:42:22.056-02	\N	f
294	1	1	1	2013-12-12 22:07:53.911-02	\N	f
295	1	1	1	2013-12-12 22:16:36.555-02	\N	f
296	1	1	1	2013-12-12 22:27:28.985-02	\N	f
297	1	1	1	2013-12-12 22:28:23.938-02	\N	f
298	1	1	1	2013-12-12 22:36:07.436-02	\N	f
299	1	1	1	2013-12-12 22:38:05.945-02	\N	f
300	1	1	1	2013-12-12 22:39:00.202-02	\N	f
301	1	1	1	2013-12-12 22:41:22.676-02	\N	f
302	1	1	1	2013-12-16 20:25:01.285-02	\N	f
303	1	1	1	2013-12-16 20:27:01.285-02	\N	f
304	1	1	1	2014-01-02 22:06:48.439-02	\N	f
305	1	1	1	2014-01-02 22:15:13.624-02	\N	f
306	1	1	1	2014-01-02 22:15:36.409-02	\N	f
307	1	1	1	2014-01-02 22:18:25.632-02	\N	f
308	1	1	1	2014-01-02 22:18:26.922-02	\N	f
309	1	1	1	2014-01-02 22:19:32.506-02	\N	f
310	1	1	1	2014-01-02 22:20:31.004-02	\N	f
311	1	1	1	2014-01-02 22:20:32.166-02	\N	f
312	1	1	1	2014-01-02 22:22:07.761-02	\N	f
313	1	1	1	2014-01-02 22:24:59.237-02	\N	f
314	1	1	1	2014-01-02 22:26:46.697-02	\N	f
315	1	1	1	2014-01-02 22:28:51.504-02	\N	f
316	1	1	1	2014-01-02 22:30:01.131-02	\N	f
317	1	1	1	2014-01-04 10:02:24.14-02	\N	f
318	1	1	1	2014-01-04 10:46:03.896-02	\N	f
319	1	1	1	2014-01-04 10:46:39.673-02	\N	f
320	1	1	1	2014-01-04 10:46:58.367-02	\N	f
321	1	1	1	2014-01-04 10:50:42.798-02	\N	f
322	1	1	1	2014-01-04 15:09:10.037-02	\N	f
323	1	1	1	2014-01-04 15:26:19.487-02	\N	f
324	1	1	1	2014-01-04 15:26:35.362-02	\N	f
325	1	1	1	2014-01-04 15:26:42.779-02	\N	f
326	1	1	1	2014-01-04 15:28:24.696-02	\N	f
327	1	1	1	2014-01-04 15:28:32.007-02	\N	f
328	1	1	1	2014-01-04 15:28:35.91-02	\N	f
329	1	1	1	2014-01-04 15:28:40.48-02	\N	f
330	1	1	1	2014-01-04 15:30:28.588-02	\N	f
331	1	1	1	2014-01-04 15:30:31.919-02	\N	f
332	1	1	1	2014-01-04 15:30:34.779-02	\N	f
333	1	1	1	2014-01-04 15:30:50.312-02	\N	f
334	1	1	1	2014-01-04 15:31:32.026-02	\N	f
335	1	1	1	2014-01-04 15:33:48.009-02	\N	f
336	1	1	1	2014-01-04 15:35:49.442-02	\N	f
337	1	1	1	2014-01-04 15:46:54.643-02	\N	f
338	1	1	1	2014-01-04 15:49:14.449-02	\N	f
339	1	1	1	2014-01-04 15:50:37.428-02	\N	f
340	1	1	1	2014-01-04 15:52:43.218-02	\N	f
341	1	1	1	2014-01-04 15:54:01.269-02	\N	f
342	1	1	1	2014-01-04 15:55:53.222-02	\N	f
343	1	1	1	2014-01-04 16:00:29.847-02	\N	f
344	1	1	1	2014-01-04 16:00:59.808-02	\N	f
345	1	1	1	2014-01-04 16:01:39.283-02	\N	f
346	1	1	1	2014-01-04 16:02:27.136-02	\N	f
347	1	1	1	2014-01-04 16:02:31.099-02	\N	f
348	1	1	1	2014-01-04 16:02:33.81-02	\N	f
349	1	1	1	2014-01-04 16:02:36.962-02	\N	f
350	1	1	1	2014-01-04 16:02:40.083-02	\N	f
351	1	1	1	2014-01-04 16:02:42.596-02	\N	f
352	1	1	1	2014-01-04 16:02:45.17-02	\N	f
353	1	1	1	2014-01-04 16:02:47.877-02	\N	f
354	1	1	1	2014-01-04 16:12:08.547-02	\N	f
355	1	1	1	2014-01-04 16:14:26.816-02	\N	f
356	1	1	1	2014-01-04 16:16:35.115-02	\N	f
357	1	1	1	2014-01-04 16:18:37.503-02	\N	f
358	1	1	1	2014-01-04 16:20:21.96-02	\N	f
359	1	1	1	2014-01-05 16:02:30.099-02	\N	f
360	1	1	1	2014-01-05 18:18:13.468-02	\N	f
361	1	1	1	2014-01-05 18:18:38.092-02	\N	f
362	1	1	1	2014-01-05 18:19:10.412-02	\N	f
363	1	1	1	2014-01-05 18:19:19.252-02	\N	f
364	1	1	1	2014-01-05 18:19:57.432-02	\N	f
365	1	1	1	2014-01-05 18:20:24.457-02	\N	f
366	1	1	1	2014-01-05 19:39:12.118-02	\N	f
367	1	1	1	2014-01-13 21:58:35.891-02	\N	f
368	1	1	1	2014-01-13 22:50:47.187-02	\N	f
369	1	1	1	2014-01-13 22:51:11.062-02	\N	f
370	1	1	1	2014-01-13 22:51:39.663-02	\N	f
371	1	1	1	2014-01-13 22:59:08.819-02	\N	f
372	1	1	1	2014-01-13 23:11:52.643-02	\N	f
373	1	1	1	2014-01-13 23:15:40.909-02	\N	f
374	1	1	1	2014-01-14 20:54:38.272-02	\N	f
375	1	1	1	2014-01-14 20:58:57.205-02	\N	f
376	1	1	1	2014-01-14 21:02:51.282-02	\N	f
377	1	1	1	2014-01-14 21:19:33.117-02	\N	f
378	1	1	1	2014-01-14 21:23:06.857-02	\N	f
379	1	1	1	2014-01-14 21:26:10.006-02	\N	f
380	1	1	1	2014-01-14 21:28:05.962-02	\N	f
381	1	1	1	2014-01-14 21:31:18.666-02	\N	f
382	1	1	1	2014-01-14 21:32:26.955-02	\N	f
383	1	1	1	2014-01-14 21:34:46.342-02	\N	f
384	1	1	1	2014-01-14 21:39:24.757-02	\N	f
385	1	1	1	2014-01-15 20:29:05.667-02	\N	f
386	1	1	1	2014-01-15 20:54:33.8-02	\N	f
387	1	1	1	2014-01-15 21:32:45.553-02	\N	f
388	1	1	1	2014-01-15 21:48:50.002-02	\N	f
389	1	1	1	2014-01-15 22:27:41.515-02	\N	f
390	1	1	1	2014-01-16 21:24:31.686-02	\N	f
391	1	1	1	2014-01-16 21:25:45.424-02	\N	f
392	1	1	1	2014-01-16 22:15:04.133-02	\N	f
393	1	1	1	2014-01-20 20:56:03.947-02	\N	f
394	1	1	1	2014-01-20 20:56:47.202-02	\N	f
395	1	1	1	2014-01-20 21:00:33.459-02	\N	f
396	1	1	1	2014-01-20 21:03:18.437-02	\N	f
397	1	1	1	2014-01-20 21:04:37.954-02	\N	f
398	1	1	1	2014-01-20 21:07:11.866-02	\N	f
399	1	1	1	2014-01-20 21:13:07.639-02	\N	f
400	1	1	1	2014-01-20 21:13:49.227-02	\N	f
401	1	1	1	2014-01-20 21:56:53.495-02	\N	f
402	1	1	1	2014-01-20 21:58:33.367-02	\N	f
403	1	1	1	2014-01-20 22:00:04.349-02	\N	f
404	1	1	1	2014-01-20 22:01:10.635-02	\N	f
405	1	1	1	2014-01-20 22:01:34.678-02	\N	f
406	1	1	1	2014-01-20 22:03:30.732-02	\N	f
407	1	1	1	2014-01-20 22:04:12.532-02	\N	f
408	1	1	1	2014-01-20 22:04:39.702-02	\N	f
409	1	1	1	2014-01-20 22:07:52.643-02	\N	f
410	1	1	1	2014-01-20 22:10:28.874-02	\N	f
411	1	1	1	2014-01-20 22:12:35.865-02	\N	f
412	1	1	1	2014-01-20 22:16:20.12-02	\N	f
413	1	1	1	2014-01-20 22:17:20.168-02	\N	f
414	1	1	1	2014-01-20 22:35:19.607-02	\N	f
415	1	1	1	2014-01-20 22:36:30.406-02	\N	f
416	1	1	1	2014-01-20 22:36:55.037-02	\N	f
417	1	1	1	2014-01-20 22:36:58.813-02	\N	f
418	1	1	1	2014-01-20 22:37:22.197-02	\N	f
419	1	1	1	2014-01-20 22:37:29.311-02	\N	f
420	1	1	1	2014-01-20 22:39:05.997-02	\N	f
421	1	1	1	2014-01-20 22:40:27.833-02	\N	f
422	1	1	1	2014-01-21 19:34:32.879-02	\N	f
423	1	1	1	2014-01-21 19:35:54.628-02	\N	f
424	1	1	1	2014-01-21 19:37:37.215-02	\N	f
425	1	1	1	2014-01-21 19:39:11.914-02	\N	f
426	1	1	1	2014-01-21 19:47:04.28-02	\N	f
427	1	1	1	2014-01-21 19:48:19.861-02	\N	f
428	1	1	1	2014-01-21 19:49:21.395-02	\N	f
429	1	1	1	2014-01-21 19:55:53.675-02	\N	f
430	1	1	1	2014-01-21 19:56:39.178-02	\N	f
431	1	1	1	2014-01-21 20:04:39.532-02	\N	f
432	1	1	1	2014-01-21 20:05:43.345-02	\N	f
433	1	1	1	2014-01-21 20:09:09.395-02	\N	f
434	1	1	1	2014-01-21 20:11:58.12-02	\N	f
435	1	1	1	2014-01-21 20:26:20.128-02	\N	f
436	1	1	1	2014-01-21 20:27:09.642-02	\N	f
437	1	1	1	2014-01-21 20:31:48.083-02	\N	f
438	1	1	1	2014-01-21 20:33:34.275-02	\N	f
439	1	1	1	2014-01-21 20:33:55.143-02	\N	f
440	1	1	1	2014-01-21 21:02:14.293-02	\N	f
441	1	1	1	2014-01-21 21:02:53.579-02	\N	f
442	1	1	1	2014-01-21 21:03:31.207-02	\N	f
443	1	1	1	2014-01-21 21:15:32.304-02	\N	f
444	1	1	1	2014-01-21 21:16:36.158-02	\N	f
445	1	1	1	2014-01-21 21:16:44.737-02	\N	f
446	1	1	1	2014-01-21 21:17:38.814-02	\N	f
447	1	1	1	2014-01-21 21:19:14.471-02	\N	f
448	1	1	1	2014-01-21 21:20:49.893-02	\N	f
449	1	1	1	2014-01-21 21:22:48.73-02	\N	f
450	1	1	1	2014-01-21 21:24:33.637-02	\N	f
451	1	1	1	2014-01-21 21:27:15.512-02	\N	f
452	1	1	1	2014-01-21 21:29:24.631-02	\N	f
453	1	1	1	2014-01-21 21:30:51.23-02	\N	f
454	1	1	1	2014-01-21 21:31:56.507-02	\N	f
455	1	1	1	2014-01-21 21:47:36.909-02	\N	f
456	1	1	1	2014-01-21 21:48:45.591-02	\N	f
457	1	1	1	2014-01-21 21:50:15.978-02	\N	f
458	1	1	1	2014-01-21 21:51:38.188-02	\N	f
459	1	1	1	2014-01-21 21:52:43.998-02	\N	f
460	1	1	1	2014-01-21 22:03:11.815-02	\N	f
461	1	1	1	2014-01-21 22:04:51.158-02	\N	f
462	1	1	1	2014-01-21 22:06:33.446-02	\N	f
463	1	1	1	2014-01-21 22:06:37.064-02	\N	f
464	1	1	1	2014-01-21 22:06:46.115-02	\N	f
465	1	1	1	2014-01-21 22:06:57.246-02	\N	f
466	1	1	1	2014-01-21 22:07:11.965-02	\N	f
467	1	1	1	2014-01-21 22:07:30.691-02	\N	f
468	1	1	1	2014-01-21 22:07:47.551-02	\N	f
469	1	1	1	2014-01-21 22:07:55.852-02	\N	f
470	1	1	1	2014-01-21 22:08:42.788-02	\N	f
471	1	1	1	2014-01-21 22:11:44.783-02	\N	f
472	1	1	1	2014-01-21 22:18:25.523-02	\N	f
473	1	1	1	2014-01-22 18:48:38.657-02	\N	f
474	1	1	1	2014-01-22 18:48:55.741-02	\N	f
475	1	1	1	2014-01-22 18:49:41.709-02	\N	f
476	1	1	1	2014-01-22 18:56:33.101-02	\N	f
477	1	1	1	2014-01-22 18:56:38.322-02	\N	f
478	1	1	1	2014-01-22 18:56:46.387-02	\N	f
479	1	1	1	2014-01-22 18:58:28.944-02	\N	f
480	1	1	1	2014-01-22 19:06:11.818-02	\N	f
481	1	1	1	2014-01-22 19:06:16.058-02	\N	f
482	1	1	1	2014-01-22 19:07:00.216-02	\N	f
483	1	1	1	2014-01-22 19:08:48.36-02	\N	f
484	1	1	1	2014-01-22 19:09:42.401-02	\N	f
485	1	1	1	2014-01-22 19:11:48.933-02	\N	f
486	1	1	1	2014-01-22 19:17:39-02	\N	f
487	1	1	1	2014-01-22 19:18:04.102-02	\N	f
488	1	1	1	2014-01-22 19:18:08.863-02	\N	f
489	1	1	1	2014-01-22 19:21:25.48-02	\N	f
490	1	1	1	2014-01-22 19:22:16.661-02	\N	f
491	1	1	1	2014-01-22 19:23:46.518-02	\N	f
492	1	1	1	2014-01-22 19:24:54.701-02	\N	f
493	1	1	1	2014-01-22 19:26:05.45-02	\N	f
494	1	1	1	2014-01-22 19:28:19.044-02	\N	f
495	1	1	1	2014-01-22 19:36:27.364-02	\N	f
496	1	1	1	2014-01-22 19:42:59.581-02	\N	f
497	1	1	1	2014-01-22 19:54:58.974-02	\N	f
498	1	1	1	2014-01-22 20:01:20.077-02	\N	f
499	1	1	1	2014-01-22 20:02:05.57-02	\N	f
500	1	1	1	2014-01-22 20:02:10.927-02	\N	f
501	1	1	1	2014-01-22 20:23:32.509-02	\N	f
502	1	1	1	2014-01-22 20:28:36.331-02	\N	f
503	1	1	1	2014-01-22 20:29:15.458-02	\N	f
504	1	1	1	2014-01-22 20:31:08.477-02	\N	f
505	1	1	1	2014-01-22 20:31:26.616-02	\N	f
506	1	1	1	2014-01-22 20:34:13.907-02	\N	f
507	1	1	1	2014-01-22 20:36:36.302-02	\N	f
508	1	1	1	2014-01-22 20:44:52.673-02	\N	f
509	1	1	1	2014-01-22 20:46:11.674-02	\N	f
510	1	1	1	2014-01-22 20:49:43.003-02	\N	f
511	1	1	1	2014-01-22 20:49:49.767-02	\N	f
512	1	1	1	2014-01-22 20:57:53.216-02	\N	f
513	1	1	1	2014-01-22 21:33:34.822-02	\N	f
514	1	1	1	2014-01-22 22:08:54.4-02	\N	f
515	1	1	1	2014-01-22 22:12:52.882-02	\N	f
516	1	1	1	2014-01-22 22:13:09.318-02	\N	f
517	1	1	1	2014-01-22 22:18:53.018-02	\N	f
518	1	1	1	2014-01-22 22:39:29.48-02	\N	f
519	1	1	1	2014-01-22 22:39:54.284-02	\N	f
520	1	1	1	2014-01-22 22:49:42.166-02	\N	f
521	1	1	1	2014-01-22 22:49:44.879-02	\N	f
522	1	1	1	2014-01-22 22:49:49.58-02	\N	f
523	1	1	1	2014-01-22 23:36:32.784-02	\N	f
524	1	1	1	2014-01-22 23:38:14.631-02	\N	f
525	1	1	1	2014-01-27 21:40:25.055-02	\N	f
526	1	1	1	2014-01-27 21:40:31.903-02	\N	f
527	1	1	1	2014-01-27 21:41:21.707-02	\N	f
528	1	1	1	2014-01-27 21:43:28.93-02	\N	f
529	1	1	1	2014-01-27 21:51:03.096-02	\N	f
530	1	1	1	2014-01-27 21:51:11.855-02	\N	f
531	1	1	1	2014-01-27 21:51:17.301-02	\N	f
532	1	1	1	2014-01-27 21:51:23.2-02	\N	f
533	1	1	1	2014-01-27 21:51:38.88-02	\N	f
534	1	1	1	2014-01-27 21:53:11.313-02	\N	f
535	1	1	1	2014-01-27 21:54:39.092-02	\N	f
536	1	1	1	2014-01-27 21:54:43.838-02	\N	f
537	1	1	1	2014-01-27 21:54:47.369-02	\N	f
538	1	1	1	2014-01-27 21:57:29.797-02	\N	f
539	1	1	1	2014-01-27 21:58:44.218-02	\N	f
540	1	1	1	2014-01-27 22:04:28.722-02	\N	f
541	1	1	1	2014-01-27 22:05:28.724-02	\N	f
542	1	1	1	2014-01-27 22:06:59.697-02	\N	f
543	1	1	1	2014-01-27 22:07:09.334-02	\N	f
544	1	1	1	2014-01-27 22:07:16.108-02	\N	f
545	1	1	1	2014-01-27 22:09:20.526-02	\N	f
546	1	1	1	2014-01-27 22:09:25.691-02	\N	f
547	1	1	1	2014-01-27 22:09:28.814-02	\N	f
548	1	1	1	2014-01-27 22:09:31.665-02	\N	f
549	1	1	1	2014-01-27 22:09:35.3-02	\N	f
550	1	1	1	2014-01-27 22:09:58.925-02	\N	f
551	1	1	1	2014-01-27 22:10:09.719-02	\N	f
552	1	1	1	2014-01-27 22:10:38.577-02	\N	f
553	1	1	1	2014-01-27 22:10:57.193-02	\N	f
554	1	1	1	2014-01-27 22:11:08.507-02	\N	f
555	1	1	1	2014-01-27 22:11:25.859-02	\N	f
556	1	1	1	2014-01-27 22:12:10.285-02	\N	f
557	1	1	1	2014-01-27 22:12:25.671-02	\N	f
558	1	1	1	2014-01-27 22:34:26.815-02	\N	f
559	1	1	1	2014-01-27 22:36:12.204-02	\N	f
560	1	1	1	2014-01-27 22:36:31.418-02	\N	f
561	1	1	1	2014-01-27 22:36:47.313-02	\N	f
562	1	1	1	2014-01-27 22:37:09.107-02	\N	f
563	1	1	1	2014-01-27 22:37:52.171-02	\N	f
564	1	1	1	2014-01-28 19:36:07.46-02	\N	f
565	1	1	1	2014-01-28 19:41:25.076-02	\N	f
566	1	1	1	2014-01-28 19:42:31.423-02	\N	f
567	1	1	1	2014-01-28 19:51:40.151-02	\N	f
568	1	1	1	2014-01-28 19:54:07.912-02	\N	f
569	1	1	1	2014-01-28 19:54:24.231-02	\N	f
570	1	1	1	2014-01-28 19:55:30.666-02	\N	f
571	1	1	1	2014-01-28 19:55:52.015-02	\N	f
572	1	1	1	2014-01-28 19:55:55.359-02	\N	f
573	1	1	1	2014-01-28 19:56:04.392-02	\N	f
574	1	1	1	2014-01-28 19:56:25.754-02	\N	f
575	1	1	1	2014-01-28 19:56:43.982-02	\N	f
576	1	1	1	2014-01-28 19:57:31.006-02	\N	f
577	1	1	1	2014-01-28 19:58:44.967-02	\N	f
578	1	1	1	2014-01-28 20:00:20.055-02	\N	f
579	1	1	1	2014-01-28 20:02:33.063-02	\N	f
580	1	1	1	2014-01-28 20:03:10.219-02	\N	f
581	1	1	1	2014-01-28 20:03:51.621-02	\N	f
582	1	1	1	2014-01-28 20:06:20.687-02	\N	f
583	1	1	1	2014-01-28 20:07:13.319-02	\N	f
584	1	1	1	2014-01-28 20:12:22.326-02	\N	f
585	1	1	1	2014-01-28 20:12:46.142-02	\N	f
586	1	1	1	2014-01-28 20:13:31.567-02	\N	f
587	1	1	1	2014-01-28 20:13:40.439-02	\N	f
588	1	1	1	2014-01-28 20:17:40.594-02	\N	f
589	1	1	1	2014-01-28 20:25:28.78-02	\N	f
590	1	1	1	2014-01-28 20:25:50.524-02	\N	f
591	1	1	1	2014-01-28 20:39:00.661-02	\N	f
592	1	1	1	2014-01-28 20:50:39.898-02	\N	f
593	1	1	1	2014-01-28 20:50:44.168-02	\N	f
594	1	1	1	2014-01-28 20:53:10.115-02	\N	f
595	1	1	1	2014-01-28 21:37:50.646-02	\N	f
596	1	1	1	2014-01-28 21:38:42.632-02	\N	f
597	1	1	1	2014-01-28 21:39:42.345-02	\N	f
598	1	1	1	2014-01-28 21:40:52.2-02	\N	f
599	1	1	1	2014-01-28 21:41:29.658-02	\N	f
600	1	1	1	2014-01-28 21:41:54.206-02	\N	f
601	1	1	1	2014-01-28 21:45:52.609-02	\N	f
602	1	1	1	2014-01-29 19:56:40.089-02	\N	f
603	1	1	1	2014-01-29 19:58:03.257-02	\N	f
604	1	1	1	2014-01-29 19:58:43.417-02	\N	f
605	1	1	1	2014-01-29 20:06:56.973-02	\N	f
606	1	1	1	2014-01-29 20:08:15.033-02	\N	f
607	1	1	1	2014-01-29 20:09:41.92-02	\N	f
608	1	1	1	2014-01-29 20:10:05.167-02	\N	f
609	1	1	1	2014-01-29 20:10:54.175-02	\N	f
610	1	1	1	2014-01-29 21:07:40.61-02	\N	f
611	1	1	1	2014-01-29 22:04:19.705-02	\N	f
612	1	1	1	2014-01-29 22:18:48.019-02	\N	f
613	1	1	1	2014-01-29 22:24:04.085-02	\N	f
614	1	1	1	2014-01-30 19:47:16.692-02	\N	f
615	1	1	1	2014-01-30 19:49:29.956-02	\N	f
616	1	1	1	2014-02-08 15:03:59.303-02	\N	f
617	1	1	1	2014-02-08 15:04:22.544-02	\N	f
618	1	1	1	2014-02-08 15:04:54.608-02	\N	f
619	1	1	1	2014-02-08 15:04:57.991-02	\N	f
620	1	1	1	2014-02-08 15:05:18.304-02	\N	f
621	1	1	1	2014-02-08 15:05:43.081-02	\N	f
622	1	1	1	2014-02-08 15:06:00.376-02	\N	f
623	1	1	1	2014-02-08 15:13:53.73-02	\N	f
624	1	1	1	2014-02-08 16:21:35.819-02	\N	f
625	1	1	1	2014-02-08 16:21:42.858-02	\N	f
626	1	1	1	2014-02-08 16:28:13.427-02	\N	f
627	1	1	1	2014-02-08 16:39:09.358-02	\N	f
628	1	1	1	2014-02-08 16:39:21.544-02	\N	f
629	1	1	1	2014-02-08 16:39:53.737-02	\N	f
630	1	1	1	2014-02-08 16:40:14.251-02	\N	f
631	1	1	1	2014-02-08 16:40:55.249-02	\N	f
632	1	1	1	2014-02-08 16:41:17.325-02	\N	f
633	1	1	1	2014-02-08 16:43:13.159-02	\N	f
634	1	1	1	2014-02-08 17:13:25.24-02	\N	f
635	1	1	1	2014-02-08 17:13:41.484-02	\N	f
636	1	1	1	2014-02-08 17:15:31.577-02	\N	f
637	1	1	1	2014-02-08 17:17:36.534-02	\N	f
638	1	1	1	2014-02-08 17:21:22.635-02	\N	f
639	1	1	1	2014-02-08 17:23:09.761-02	\N	f
640	1	1	1	2014-02-08 18:40:47.092-02	\N	f
641	1	1	1	2014-02-08 18:41:07.651-02	\N	f
642	1	1	1	2014-02-08 18:41:12.792-02	\N	f
643	1	1	1	2014-02-08 18:41:42.543-02	\N	f
644	1	1	1	2014-02-08 18:42:35.258-02	\N	f
645	1	1	1	2014-02-08 18:42:57.183-02	\N	f
646	1	1	1	2014-02-08 18:43:28.707-02	\N	f
647	1	1	1	2014-02-08 18:44:21.643-02	\N	f
648	1	1	1	2014-02-08 18:45:01.298-02	\N	f
649	1	1	1	2014-02-08 18:45:05.515-02	\N	f
650	1	1	1	2014-02-08 18:45:11.193-02	\N	f
651	1	1	1	2014-02-08 18:45:14.796-02	\N	f
652	1	1	1	2014-02-08 18:45:31.907-02	\N	f
653	1	1	1	2014-02-08 18:45:36.865-02	\N	f
654	1	1	1	2014-02-08 18:45:40.66-02	\N	f
655	1	1	1	2014-02-08 18:48:12.808-02	\N	f
656	1	1	1	2014-02-08 18:52:44.49-02	\N	f
657	1	1	1	2014-02-08 18:53:55.672-02	\N	f
658	1	1	1	2014-02-08 18:54:49.38-02	\N	f
659	1	1	1	2014-02-08 18:56:14.361-02	\N	f
660	1	1	1	2014-02-08 19:01:07.645-02	\N	f
661	1	1	1	2014-02-08 19:01:59.068-02	\N	f
662	1	1	1	2014-02-08 19:02:41.509-02	\N	f
663	1	1	1	2014-02-08 19:05:00.865-02	\N	f
664	1	1	1	2014-02-08 19:05:56.988-02	\N	f
665	1	1	1	2014-02-08 19:12:55.705-02	\N	f
666	1	1	1	2014-02-08 19:13:02.774-02	\N	f
667	1	1	1	2014-02-08 19:13:09.824-02	\N	f
668	1	1	1	2014-02-08 19:15:04.384-02	\N	f
669	1	1	1	2014-02-09 11:57:09.169-02	\N	f
670	1	1	1	2014-02-09 11:58:48.922-02	\N	f
671	1	1	1	2014-02-09 12:56:53.813-02	\N	f
672	1	1	1	2014-02-09 12:57:09.763-02	\N	f
673	1	1	1	2014-02-09 12:57:46.362-02	\N	f
674	1	1	1	2014-02-09 12:58:40.903-02	\N	f
675	1	1	1	2014-02-09 12:59:15.498-02	\N	f
676	1	1	1	2014-02-09 13:31:22.91-02	\N	f
677	1	1	1	2014-02-09 13:32:11.251-02	\N	f
678	1	1	1	2014-02-09 14:34:06.062-02	\N	f
679	1	1	1	2014-02-09 14:34:58.64-02	\N	f
680	1	1	1	2014-02-09 14:35:21.491-02	\N	f
681	1	1	1	2014-02-09 15:03:11.081-02	\N	f
682	1	1	1	2014-02-09 15:05:53.509-02	\N	f
683	1	1	1	2014-02-09 15:07:27.016-02	\N	f
684	1	1	1	2014-02-09 15:10:04.724-02	\N	f
685	1	1	1	2014-02-09 15:11:07.198-02	\N	f
686	1	1	1	2014-02-09 15:58:23.981-02	\N	f
687	1	1	1	2014-02-09 16:05:01.502-02	\N	f
688	1	1	1	2014-02-09 16:07:29.488-02	\N	f
689	1	1	1	2014-02-09 16:08:09.639-02	\N	f
690	1	1	1	2014-02-09 16:08:19.152-02	\N	f
691	1	1	1	2014-02-09 16:08:31.521-02	\N	f
692	1	1	23	2014-02-09 16:15:09.515-02	\N	f
693	1	1	23	2014-02-09 16:15:50.622-02	\N	f
694	1	1	23	2014-02-09 16:21:37.851-02	\N	f
695	1	1	23	2014-02-09 16:23:08.466-02	\N	f
696	1	1	23	2014-02-09 16:24:45.401-02	\N	f
697	1	1	20	2014-02-09 16:29:50-02	\N	f
698	1	1	20	2014-02-09 16:29:53.871-02	\N	f
699	1	1	19	2014-02-09 16:30:11.138-02	\N	f
700	1	1	1	2014-03-12 19:53:18.41-03	\N	f
701	1	1	24	2014-03-12 19:56:54.185-03	\N	f
702	1	1	24	2014-03-12 19:56:59.066-03	\N	f
703	1	1	24	2014-03-12 19:57:03.051-03	\N	f
704	1	1	24	2014-03-12 19:57:06.98-03	\N	f
705	1	1	24	2014-03-12 20:51:37.83-03	\N	f
706	1	1	1	2014-03-12 21:52:37.678-03	\N	f
707	1	1	25	2014-03-12 21:53:52.338-03	\N	f
708	1	1	1	2014-03-12 22:05:09.436-03	\N	f
709	1	1	26	2014-03-12 22:07:46.043-03	\N	f
710	1	1	26	2014-03-12 22:09:19.8-03	\N	f
711	1	1	1	2014-03-13 22:48:42.377-03	\N	f
712	1	1	1	2014-03-13 23:00:45.523-03	\N	f
713	1	1	28	2014-03-13 23:06:10.932-03	\N	f
714	1	1	28	2014-03-13 23:06:27.667-03	\N	f
715	1	1	28	2014-03-13 23:06:45.939-03	\N	f
716	1	1	28	2014-03-13 23:07:49.519-03	\N	f
717	1	1	28	2014-03-13 23:13:57.766-03	\N	f
718	1	1	28	2014-03-13 23:19:45.126-03	\N	f
719	1	1	28	2014-03-13 23:20:15.54-03	\N	f
720	1	1	28	2014-03-13 23:39:14.233-03	\N	f
721	1	1	1	2014-03-19 20:34:16.532-03	\N	f
722	1	1	29	2014-03-19 20:35:10.598-03	\N	f
723	1	1	29	2014-03-19 20:49:55.333-03	\N	f
724	1	1	29	2014-03-19 21:22:22.843-03	\N	f
725	1	1	29	2014-03-19 21:22:57.768-03	\N	f
726	1	1	29	2014-03-19 21:23:02.95-03	\N	f
727	1	1	29	2014-03-19 21:23:06.419-03	\N	f
728	1	1	29	2014-03-19 21:49:33.982-03	\N	f
729	1	1	29	2014-03-19 22:24:28.724-03	\N	f
730	1	1	29	2014-03-19 22:27:58.607-03	\N	f
731	1	1	29	2014-03-20 19:26:15.886-03	\N	f
732	1	1	29	2014-03-20 19:26:37.126-03	\N	f
733	1	1	29	2014-03-20 19:26:40.784-03	\N	f
734	1	1	29	2014-03-20 19:26:44.914-03	\N	f
735	1	1	29	2014-03-20 19:30:25.699-03	\N	f
736	1	1	29	2014-03-20 19:30:57.896-03	\N	f
737	1	1	1	2014-03-20 20:19:43.517-03	\N	f
738	1	1	30	2014-03-20 20:40:37.23-03	\N	f
739	1	1	1	2014-03-20 20:45:11.759-03	\N	f
740	1	1	1	2014-03-20 20:53:54.276-03	\N	f
741	1	1	32	2014-03-20 20:59:49.661-03	\N	f
742	1	1	32	2014-03-20 21:02:57.742-03	\N	f
743	1	1	32	2014-03-20 21:27:34.911-03	\N	f
744	1	1	32	2014-03-20 21:28:25.448-03	\N	f
745	1	1	32	2014-03-20 21:30:34.281-03	\N	f
746	1	1	32	2014-03-20 21:47:32.588-03	\N	f
747	1	1	1	2014-03-23 20:16:42.085-03	\N	f
748	1	1	1	2014-03-23 20:18:15.547-03	\N	f
\.


--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 197
-- Name: sincronizacao_idsincronizacao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sincronizacao_idsincronizacao_seq', 748, true);


--
-- TOC entry 2239 (class 0 OID 74029)
-- Dependencies: 192
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY status (idstatus, descricao) FROM stdin;
1	AGUARDANDO APROVAÇÃO
2	APROVADO
3	REPROVADO
\.


--
-- TOC entry 2224 (class 0 OID 49258)
-- Dependencies: 177
-- Data for Name: tabpreco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tabpreco (idempresa, idfilial, idtabela, descricao, percentual, acrescimo, inativo, dthralteracao) FROM stdin;
1	2	1	TABELA FILIAL 2	0	f	f	2013-12-11 20:08:19.805-02
1	1	3	TABELA 3 FILIAL 1	0	t	f	2013-12-11 20:08:19.805-02
2	1	1	0% SEM ENTRADA	0	f	f	2013-12-11 20:08:19.805-02
2	1	2	0,99% SEM ENTRADA	0	t	f	2013-12-11 20:08:19.805-02
2	2	1	LIQUIDA TUDO	0	t	f	2013-12-11 20:08:19.805-02
2	2	2	TORRA ESTOQUE	0	t	f	2013-12-11 20:08:19.805-02
1	1	2	A PRAZO	20	t	f	2013-12-11 20:08:19.805-02
1	1	4	FEIRÃO	10	t	t	2013-12-11 20:08:19.805-02
1	1	5	nova tabela	10	f	f	2013-12-11 20:08:19.805-02
1	1	6	MAIS UMA	30	f	f	2013-12-11 20:08:19.805-02
1	1	1	TABELA AVISTA a	10	f	f	2013-12-11 20:08:19.805-02
\.


--
-- TOC entry 2222 (class 0 OID 41315)
-- Dependencies: 175
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuario (idusuario, idempresa, idfilial, login, senha, email, idgrupo, dtalteracao, dtcadastro, sexo, ativo, fone, nome, dthrultimoacesso, idrepresentante) FROM stdin;
5	1	1	LADA	1	\N	\N	\N	\N	\N	t	\N	Ladair Smiderle	\N	\N
6	2	1	FOCO	1	\N	\N	\N	\N	\N	t	\N	Ladair Empreendedorismo	\N	\N
7	3	1	VISAO	1	\N	\N	\N	\N	\N	t	\N	Ladair Smiderle	\N	\N
\.


--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 174
-- Name: usuario_idusuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuario_idusuario_seq', 74, true);


--
-- TOC entry 2233 (class 0 OID 65743)
-- Dependencies: 186
-- Data for Name: usuarioacesso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuarioacesso (idusuario, dthracesso) FROM stdin;
5	2013-10-02 20:35:54.28-03
5	2013-10-02 20:36:39.272-03
5	2013-10-02 21:05:07.579-03
5	2013-10-02 22:00:49.819-03
5	2013-10-03 19:56:43.458-03
5	2013-10-03 20:05:17.186-03
5	2013-10-03 20:09:13.527-03
5	2013-10-03 20:18:20.481-03
5	2013-10-03 20:23:03.498-03
5	2013-10-03 20:24:37.569-03
5	2013-10-03 20:25:56.974-03
5	2013-10-03 21:03:17.176-03
5	2013-10-03 21:05:45.162-03
5	2013-10-03 21:08:45.623-03
5	2013-10-03 21:34:21.786-03
5	2013-10-03 21:36:47.387-03
5	2013-10-03 21:45:33.896-03
5	2013-10-03 21:51:35.301-03
5	2013-10-03 21:53:32.901-03
5	2013-10-03 21:54:23.101-03
5	2013-10-03 22:05:48.901-03
5	2013-10-07 18:35:45.615-03
5	2013-10-07 19:03:20.196-03
5	2013-10-07 19:05:50.691-03
5	2013-10-07 19:07:49.207-03
5	2013-10-07 19:21:57.863-03
5	2013-10-07 19:45:41.589-03
5	2013-10-07 19:47:08.513-03
5	2013-10-07 19:51:23.856-03
5	2013-10-07 19:54:08.588-03
5	2013-10-07 19:57:54.687-03
5	2013-10-07 20:00:56.245-03
5	2013-10-07 20:22:23.45-03
5	2013-10-07 20:24:32.551-03
5	2013-10-07 20:25:23.837-03
5	2013-10-07 20:31:48.739-03
5	2013-10-07 20:47:06.027-03
5	2013-10-07 20:48:06.663-03
5	2013-10-07 20:53:47.188-03
5	2013-10-07 20:57:02.293-03
5	2013-10-07 21:19:35.267-03
5	2013-10-07 21:25:58.799-03
5	2013-10-07 21:32:55.114-03
5	2013-10-07 21:42:33.639-03
5	2013-10-07 21:47:23.346-03
5	2013-10-07 21:48:02.998-03
5	2013-10-07 21:59:18.319-03
5	2013-10-07 22:02:59.595-03
5	2013-10-07 22:03:54.556-03
5	2013-10-07 22:14:21.747-03
5	2013-10-07 22:30:18.949-03
5	2013-10-07 22:31:47.89-03
5	2013-10-07 22:33:11.902-03
5	2013-10-07 22:38:20.805-03
5	2013-10-08 12:33:26.089-03
5	2013-10-08 13:14:31.725-03
5	2013-10-08 18:49:12.129-03
5	2013-10-08 19:00:11.386-03
5	2013-10-08 19:04:16.378-03
5	2013-10-08 19:08:06.794-03
5	2013-10-08 19:13:43.034-03
5	2013-10-08 19:15:45.947-03
5	2013-10-08 19:17:09.068-03
5	2013-10-08 19:19:09.569-03
5	2013-10-08 19:53:04.66-03
5	2013-10-08 19:56:28.264-03
5	2013-10-08 20:02:05.082-03
5	2013-10-08 20:03:36.541-03
5	2013-10-08 20:06:28.267-03
5	2013-10-08 20:28:28.238-03
5	2013-10-08 20:38:58.533-03
5	2013-10-08 20:40:35.94-03
5	2013-10-08 20:43:07.222-03
5	2013-10-08 20:45:35.612-03
5	2013-10-08 20:47:59.113-03
5	2013-10-08 20:54:45.579-03
5	2013-10-08 21:12:19.478-03
5	2013-10-08 21:25:39.321-03
5	2013-10-08 21:29:51.909-03
5	2013-10-08 21:30:47.742-03
5	2013-10-08 21:36:23.965-03
5	2013-10-08 21:39:34.439-03
5	2013-10-08 21:42:33.377-03
5	2013-10-08 21:43:34.794-03
5	2013-10-08 21:47:33.952-03
5	2013-10-08 21:52:27.329-03
5	2013-10-08 21:58:07.106-03
5	2013-10-08 21:59:57.059-03
5	2013-10-08 22:06:05.234-03
5	2013-10-08 22:10:02.766-03
5	2013-10-08 22:11:57.777-03
5	2013-10-08 22:13:36.066-03
5	2013-10-08 22:16:57.128-03
5	2013-10-08 22:20:10.853-03
5	2013-10-14 19:21:08.47-03
5	2013-10-14 19:22:40.008-03
5	2013-10-14 19:26:23.305-03
5	2013-10-14 19:32:03.88-03
5	2013-10-14 19:47:43.891-03
5	2013-10-14 19:48:49.38-03
5	2013-10-14 19:51:13.361-03
5	2013-10-14 19:57:11.863-03
5	2013-10-14 20:02:19.052-03
5	2013-10-14 20:04:33.913-03
5	2013-10-14 20:12:45.066-03
5	2013-10-14 20:34:03.233-03
5	2013-10-14 20:35:39.021-03
5	2013-10-14 20:38:31.09-03
5	2013-10-14 20:41:04.838-03
5	2013-10-14 20:41:45.247-03
5	2013-10-14 20:44:44.4-03
5	2013-10-14 20:45:34.841-03
5	2013-10-14 20:46:41.103-03
5	2013-10-14 20:51:31.097-03
5	2013-10-14 20:53:32.093-03
5	2013-10-14 21:01:12.997-03
5	2013-10-14 21:04:25.226-03
5	2013-10-14 21:09:26.341-03
5	2013-10-14 21:10:57.019-03
5	2013-10-14 21:24:42.564-03
5	2013-10-14 21:26:29.033-03
5	2013-10-14 21:27:35.794-03
5	2013-10-14 21:29:58.96-03
5	2013-10-14 21:31:51.751-03
5	2013-10-14 21:42:30.283-03
5	2013-10-14 21:46:08.438-03
5	2013-10-14 21:47:11.268-03
5	2013-10-14 21:48:14.958-03
5	2013-10-14 21:50:03.69-03
5	2013-10-15 18:49:16.776-03
5	2013-10-15 19:01:29.615-03
5	2013-10-15 19:04:21.473-03
5	2013-10-15 19:11:24.901-03
5	2013-10-15 19:18:42.682-03
5	2013-10-15 19:25:06.551-03
5	2013-10-15 19:29:09.717-03
6	2013-10-15 19:33:50.988-03
6	2013-10-15 19:39:39.016-03
5	2013-10-15 19:57:07.008-03
5	2013-10-15 20:12:34.914-03
5	2013-10-15 20:26:36.059-03
5	2013-10-15 20:45:17.661-03
5	2013-10-15 20:46:38.761-03
5	2013-10-15 20:48:36.801-03
5	2013-10-15 20:50:12.051-03
5	2013-10-15 20:51:11.796-03
5	2013-10-15 20:58:52.863-03
5	2013-10-15 21:01:48.761-03
5	2013-10-15 21:06:06.381-03
5	2013-10-15 21:07:36.774-03
5	2013-10-15 21:13:01.052-03
5	2013-10-15 21:14:22.359-03
5	2013-10-15 21:16:56.673-03
5	2013-10-15 21:17:39.052-03
5	2013-10-15 21:27:05.343-03
5	2013-10-15 21:34:25.693-03
5	2013-10-15 22:00:48.454-03
5	2013-10-15 22:05:35.382-03
5	2013-10-15 22:06:19.551-03
5	2013-10-15 22:18:19.15-03
5	2013-10-15 22:22:38.968-03
5	2013-10-15 22:26:26.449-03
5	2013-10-15 22:29:57.682-03
5	2013-10-15 22:40:41.462-03
5	2013-10-15 22:45:27.867-03
5	2013-10-15 22:54:31.217-03
5	2013-10-15 22:57:14.594-03
5	2013-10-15 23:06:07.933-03
5	2013-10-16 19:39:55.277-03
5	2013-10-16 20:23:28.069-03
5	2013-10-16 20:32:02.896-03
5	2013-10-16 20:38:02.691-03
5	2013-10-16 20:40:53.689-03
5	2013-10-16 20:48:56.775-03
5	2013-10-16 21:10:44.501-03
5	2013-10-16 21:19:17.4-03
5	2013-10-16 21:22:02.806-03
5	2013-10-16 21:42:46.984-03
5	2013-10-16 21:49:21.806-03
5	2013-10-16 21:57:27.977-03
5	2013-10-16 21:59:30.91-03
5	2013-10-16 22:01:42.698-03
5	2013-10-16 22:02:54.805-03
5	2013-10-16 22:04:06.507-03
5	2013-10-16 22:07:16.451-03
5	2013-10-16 22:08:46.972-03
5	2013-10-16 22:21:11.716-03
5	2013-10-19 08:46:01.805-03
5	2013-10-19 09:31:03.727-03
5	2013-10-19 10:00:30.945-03
5	2013-10-19 10:15:49.943-03
5	2013-10-19 10:16:04.284-03
5	2013-10-19 10:39:29.056-03
5	2013-10-19 10:57:40.691-03
5	2013-10-19 10:58:29.852-03
5	2013-10-19 11:10:23.235-03
5	2013-10-19 11:14:06.09-03
5	2013-10-19 12:22:36.888-03
5	2013-10-19 12:38:25.945-03
5	2013-10-19 12:41:24.505-03
5	2013-10-19 12:42:17.202-03
5	2013-10-19 12:43:43.086-03
5	2013-10-19 12:44:31.933-03
5	2013-10-19 12:44:45.481-03
5	2013-10-19 12:47:31.726-03
5	2013-10-19 12:56:41.922-03
5	2013-10-19 13:23:47.527-03
5	2013-10-19 13:26:15.954-03
5	2013-10-19 13:34:41.607-03
5	2013-10-19 13:36:00.592-03
5	2013-10-19 13:36:27.984-03
5	2013-10-19 13:38:32.187-03
5	2013-10-19 13:49:23.488-03
5	2013-10-19 13:50:11.089-03
5	2013-10-19 13:50:43.742-03
5	2013-10-19 13:54:50.272-03
5	2013-10-19 13:56:16.977-03
5	2013-10-19 13:58:32.61-03
5	2013-10-19 14:05:16.352-03
5	2013-10-19 14:10:53.289-03
5	2013-10-19 14:13:07.265-03
5	2013-10-19 14:18:03.898-03
5	2013-10-19 14:19:33.317-03
5	2013-10-19 14:23:38.769-03
5	2013-10-19 14:25:23.596-03
5	2013-10-19 14:28:25.969-03
5	2013-10-19 14:30:02.849-03
5	2013-10-19 14:36:47.959-03
5	2013-10-19 14:38:33.711-03
5	2013-10-19 14:40:55.162-03
5	2013-10-19 14:41:06.15-03
5	2013-10-19 14:44:53.661-03
5	2013-10-19 14:46:06.656-03
5	2013-10-19 14:46:31.956-03
5	2013-10-19 14:48:48.406-03
5	2013-10-19 14:49:45.869-03
5	2013-10-19 14:49:54.336-03
5	2013-10-19 14:50:07.851-03
5	2013-10-19 14:50:45.465-03
5	2013-10-19 14:52:24.422-03
5	2013-10-19 14:53:07.432-03
5	2013-10-19 14:55:40.254-03
5	2013-10-19 14:56:13.038-03
5	2013-10-19 14:57:11.179-03
5	2013-10-19 14:57:29.604-03
5	2013-10-19 14:58:06.696-03
5	2013-10-19 14:59:18.702-03
5	2013-10-19 15:00:12.98-03
5	2013-10-19 15:01:35.217-03
5	2013-10-19 15:03:29.788-03
5	2013-10-19 15:04:17.626-03
5	2013-10-19 15:05:09.602-03
5	2013-10-19 15:07:07.293-03
5	2013-10-19 15:07:42.346-03
5	2013-10-19 15:08:06.192-03
5	2013-10-19 15:08:51.771-03
5	2013-10-19 15:11:08.342-03
5	2013-10-19 15:11:59.426-03
5	2013-10-19 15:15:46.385-03
5	2013-10-19 15:16:10.865-03
5	2013-10-19 15:17:02.383-03
5	2013-10-19 15:17:24.812-03
5	2013-10-19 15:18:38.129-03
5	2013-10-19 15:50:07.41-03
5	2013-10-19 15:53:03.781-03
5	2013-10-19 15:54:13.049-03
7	2013-10-19 15:54:23.277-03
5	2013-10-19 15:54:43.75-03
7	2013-10-19 15:56:16.701-03
5	2013-10-19 15:56:46.828-03
5	2013-10-19 16:48:36.376-03
5	2013-10-19 17:08:03.844-03
5	2013-10-20 18:34:04.222-02
5	2013-10-20 18:37:02.149-02
5	2013-10-20 18:37:39.159-02
5	2013-10-20 18:38:32.999-02
5	2013-10-20 18:40:12.156-02
5	2013-10-20 18:40:52.611-02
5	2013-10-20 18:41:21.905-02
5	2013-10-20 18:41:57.658-02
5	2013-10-20 18:43:03.64-02
5	2013-10-20 18:44:39.078-02
5	2013-10-20 18:50:19.969-02
5	2013-10-20 18:51:26.253-02
5	2013-10-20 18:54:33.903-02
5	2013-10-20 18:56:05.412-02
5	2013-10-20 18:58:36.098-02
5	2013-10-20 18:58:57.255-02
5	2013-10-20 18:59:22.908-02
5	2013-10-20 18:59:45.478-02
5	2013-10-20 19:00:16.177-02
5	2013-10-20 19:00:45.906-02
5	2013-10-20 19:00:55.748-02
5	2013-10-20 19:01:40.348-02
5	2013-10-20 19:02:03.971-02
5	2013-10-20 19:02:52.198-02
5	2013-10-20 19:05:10.217-02
5	2013-10-20 19:05:42.862-02
5	2013-10-20 19:05:49.002-02
5	2013-10-20 19:06:03.82-02
5	2013-10-20 19:06:28.293-02
5	2013-10-20 19:07:01.695-02
5	2013-10-20 19:07:26.035-02
5	2013-10-20 19:07:34.207-02
5	2013-10-20 19:07:54.849-02
5	2013-10-20 19:08:31.896-02
5	2013-10-20 19:09:32.608-02
5	2013-10-20 19:09:50.494-02
5	2013-10-20 19:20:48.923-02
5	2013-10-20 19:26:42.784-02
5	2013-10-20 19:27:51.245-02
5	2013-10-20 19:30:33.362-02
5	2013-10-20 19:31:17.723-02
5	2013-10-20 19:31:58.324-02
7	2013-10-20 19:32:21.713-02
5	2013-10-20 19:34:14.368-02
5	2013-10-20 19:37:23.912-02
5	2013-10-20 19:37:33.891-02
7	2013-10-20 19:43:37.557-02
5	2013-10-20 19:43:52.547-02
5	2013-10-20 19:48:35.663-02
5	2013-10-20 20:00:26.745-02
5	2013-10-20 20:00:53.1-02
5	2013-10-20 20:32:44.266-02
5	2013-10-20 20:34:23.682-02
5	2013-10-20 20:38:24.824-02
5	2013-10-20 20:40:37.148-02
5	2013-10-20 20:44:40.353-02
5	2013-10-20 20:45:42.862-02
5	2013-10-20 20:55:13.097-02
5	2013-10-20 20:56:59.153-02
5	2013-10-20 21:19:10.202-02
5	2013-10-20 21:21:28.477-02
5	2013-10-20 21:23:51.853-02
5	2013-10-20 21:28:55.404-02
5	2013-10-20 21:30:20.118-02
5	2013-10-20 21:32:13.232-02
5	2013-10-20 21:45:34.895-02
5	2013-10-20 21:49:38.047-02
5	2013-10-20 21:53:14.267-02
7	2013-10-20 22:04:29.621-02
5	2013-10-20 22:05:03.847-02
5	2013-10-20 22:10:15.706-02
5	2013-10-20 22:11:24.234-02
5	2013-10-20 22:27:28.882-02
5	2013-10-20 22:28:36.091-02
5	2013-10-20 22:33:20.395-02
5	2013-10-20 22:33:25.747-02
5	2013-10-20 22:35:09.259-02
5	2013-10-20 22:39:47.865-02
5	2013-10-20 22:41:35.844-02
5	2013-10-21 19:27:43.276-02
5	2013-10-21 19:42:24.783-02
5	2013-10-21 19:56:41.203-02
5	2013-10-21 20:12:37.142-02
5	2013-10-21 20:14:56.248-02
5	2013-10-21 20:17:57.372-02
5	2013-10-21 20:18:45.541-02
5	2013-10-21 20:23:18.269-02
5	2013-10-21 20:25:30.039-02
5	2013-10-21 20:30:34.696-02
5	2013-10-21 20:32:29.828-02
5	2013-10-21 20:33:48.463-02
5	2013-10-21 20:38:52.255-02
5	2013-10-21 20:42:20.262-02
5	2013-10-21 20:46:37.522-02
5	2013-10-21 20:48:05.931-02
5	2013-10-21 20:49:31.86-02
5	2013-10-21 20:51:38.452-02
5	2013-10-21 21:02:10.791-02
5	2013-10-21 21:34:33.459-02
5	2013-10-21 21:37:43.173-02
5	2013-10-21 21:44:52.963-02
5	2013-10-21 22:21:36.731-02
5	2013-10-21 22:25:51.909-02
5	2013-10-21 22:43:37.641-02
5	2013-10-21 22:48:06.85-02
5	2013-10-21 22:58:19.209-02
5	2013-10-21 23:08:32.46-02
5	2013-10-21 23:13:08.156-02
5	2013-10-28 19:24:29.467-02
5	2013-10-28 19:30:32.938-02
5	2013-10-28 19:34:51.12-02
5	2013-10-28 19:36:53.605-02
5	2013-10-28 19:38:35.269-02
5	2013-10-28 19:40:41.32-02
5	2013-10-28 19:59:02.282-02
5	2013-10-28 20:02:45.593-02
5	2013-10-28 20:17:57.327-02
5	2013-10-28 20:39:23.79-02
5	2013-10-28 20:41:27.459-02
5	2013-10-28 20:45:57.246-02
5	2013-10-28 20:52:31.641-02
5	2013-10-28 21:02:34.385-02
5	2013-10-28 21:04:17.659-02
5	2013-10-28 21:14:45.139-02
5	2013-10-28 21:17:01.122-02
5	2013-10-28 21:20:28.667-02
5	2013-10-28 21:26:01.223-02
5	2013-10-28 21:27:30.371-02
5	2013-10-28 21:29:29.6-02
5	2013-10-28 21:32:18.917-02
5	2013-10-28 21:34:17.456-02
5	2013-10-28 21:37:54.672-02
5	2013-10-28 21:40:51.48-02
5	2013-10-28 21:50:31.082-02
5	2013-10-28 21:52:07.427-02
5	2013-10-28 22:51:05.314-02
5	2013-11-03 20:41:41.62-02
5	2013-11-03 20:58:19.507-02
5	2013-11-03 21:07:12.995-02
5	2013-11-03 21:27:09.858-02
5	2013-11-03 21:55:27.777-02
5	2013-11-03 22:12:49.324-02
5	2013-11-03 22:14:51.558-02
5	2013-11-03 22:17:24.556-02
5	2013-11-03 22:18:31.153-02
5	2013-11-03 22:22:18.965-02
5	2013-11-03 22:26:29.695-02
5	2013-11-03 22:28:18.32-02
5	2013-11-03 22:32:09.964-02
5	2013-11-03 22:37:41.917-02
5	2013-11-03 22:43:34.594-02
5	2013-11-03 22:44:31.455-02
5	2013-11-04 20:30:04.561-02
5	2013-11-04 20:31:47.077-02
5	2013-11-04 20:41:34.925-02
5	2013-11-04 20:56:35.664-02
5	2013-11-04 20:57:37.683-02
5	2013-11-04 21:36:26.416-02
5	2013-11-05 19:17:00.045-02
5	2013-11-05 19:19:24.426-02
5	2013-11-05 19:20:38.182-02
5	2013-11-05 19:23:03.607-02
5	2013-11-05 19:56:05.501-02
5	2013-11-05 20:20:36.925-02
5	2013-11-05 20:29:03.709-02
5	2013-11-05 20:30:47.542-02
5	2013-11-05 20:31:53.103-02
5	2013-11-05 20:32:52.871-02
5	2013-11-05 20:34:51.794-02
5	2013-11-05 20:35:54.953-02
5	2013-11-05 20:39:46.53-02
5	2013-11-05 20:54:32.327-02
5	2013-11-05 21:02:21.228-02
5	2013-11-05 21:05:20.788-02
5	2013-11-05 21:13:27.702-02
5	2013-11-05 21:19:40.487-02
5	2013-11-05 21:22:36.483-02
5	2013-11-05 21:28:10.515-02
5	2013-11-05 21:30:14.229-02
5	2013-11-05 21:35:22.24-02
5	2013-11-05 21:44:11.836-02
5	2013-11-05 21:46:05.326-02
5	2013-11-05 21:49:35.071-02
5	2013-11-05 21:53:41.572-02
5	2013-11-05 22:05:58.581-02
5	2013-11-05 22:16:18.986-02
5	2013-11-05 22:21:03.746-02
5	2013-11-05 22:24:34.772-02
5	2013-11-05 22:55:03.387-02
5	2013-11-05 22:55:34.134-02
5	2013-11-05 22:58:48.799-02
5	2013-11-05 22:59:51.906-02
5	2013-11-05 23:01:28.615-02
5	2013-11-11 18:02:30.52-02
5	2013-11-11 18:13:36.512-02
5	2013-11-11 18:15:48.621-02
5	2013-11-11 18:25:03.28-02
5	2013-11-11 18:25:57.931-02
5	2013-11-11 18:30:47.112-02
5	2013-11-11 18:33:07.361-02
5	2013-11-11 20:44:52.879-02
5	2013-11-11 21:06:41.2-02
5	2013-11-11 21:16:57.516-02
5	2013-11-11 22:05:23.301-02
5	2013-11-11 22:08:42.624-02
5	2013-11-12 19:05:24.332-02
5	2013-11-12 19:06:54.312-02
5	2013-11-12 19:08:50.027-02
5	2013-11-12 19:11:29.082-02
5	2013-11-12 19:15:04.413-02
5	2013-11-12 19:49:44.91-02
5	2013-11-12 19:51:33.232-02
5	2013-11-12 20:00:11.484-02
5	2013-11-12 20:06:33.82-02
5	2013-11-12 20:08:11.148-02
5	2013-11-12 20:12:04.715-02
5	2013-11-12 20:33:10.797-02
5	2013-11-12 20:39:49.773-02
5	2013-11-12 20:46:05.457-02
5	2013-11-12 20:48:17.92-02
5	2013-11-12 20:51:06.265-02
5	2013-11-12 20:54:46.99-02
5	2013-11-12 21:02:03.223-02
5	2013-11-12 21:06:18.171-02
5	2013-11-12 21:30:28.81-02
5	2013-11-12 21:31:41.588-02
5	2013-11-12 21:41:32.19-02
5	2013-11-12 22:07:49.4-02
5	2013-11-12 22:10:26.63-02
5	2013-11-12 22:11:04.195-02
5	2013-11-12 22:13:23.815-02
5	2013-11-12 22:14:47.985-02
5	2013-11-12 22:16:07.236-02
5	2013-11-12 22:25:17.28-02
5	2013-11-12 22:27:11.693-02
5	2013-11-12 22:34:20.987-02
5	2013-11-12 23:15:14.816-02
5	2013-11-13 12:52:37.241-02
5	2013-11-13 12:56:21.885-02
5	2013-11-13 12:57:56.408-02
5	2013-11-13 12:59:02.525-02
5	2013-11-13 13:00:36.374-02
5	2013-11-13 13:05:41.977-02
5	2013-11-13 13:08:41.465-02
5	2013-11-13 13:10:43.956-02
5	2013-11-13 13:13:11.189-02
5	2013-11-13 13:14:34.13-02
5	2013-11-13 18:32:53.198-02
5	2013-11-13 18:42:32.871-02
5	2013-11-13 18:44:31.345-02
5	2013-11-13 18:50:30.273-02
5	2013-11-13 18:55:39.688-02
5	2013-11-13 19:06:44.314-02
5	2013-11-13 19:09:00.532-02
5	2013-11-13 19:12:42.623-02
5	2013-11-13 19:14:37.315-02
5	2013-11-13 19:15:53.756-02
5	2013-11-13 19:28:52.368-02
5	2013-11-13 19:29:47.689-02
5	2013-11-13 19:32:17.111-02
5	2013-11-13 19:33:34.543-02
5	2013-11-13 19:59:20.492-02
5	2013-11-13 20:03:03.687-02
5	2013-11-13 20:04:46.482-02
5	2013-11-13 20:45:55.769-02
5	2013-11-13 20:47:03.723-02
5	2013-11-13 20:48:14.516-02
5	2013-11-13 20:52:34.434-02
5	2013-11-13 20:57:59.682-02
5	2013-11-13 21:00:28.834-02
5	2013-11-13 21:02:31.966-02
5	2013-11-13 21:10:05.252-02
5	2013-11-13 21:19:24.507-02
5	2013-11-13 21:23:44.507-02
5	2013-11-13 21:24:44.343-02
5	2013-11-13 21:25:37.159-02
5	2013-11-13 21:27:26.77-02
5	2013-11-13 21:28:48.099-02
5	2013-11-13 21:29:38.378-02
5	2013-11-13 21:39:15.063-02
5	2013-11-13 21:40:07.186-02
5	2013-11-13 22:07:19.671-02
5	2013-11-13 22:09:06.092-02
5	2013-11-14 12:37:14.621-02
5	2013-11-14 13:05:40.718-02
5	2013-11-14 18:28:35.558-02
5	2013-11-14 18:30:54.631-02
5	2013-11-14 18:33:41.867-02
5	2013-11-14 18:37:28.288-02
5	2013-11-14 18:53:34.973-02
5	2013-11-14 18:54:26.58-02
5	2013-11-14 18:57:09.939-02
5	2013-11-14 18:58:45.265-02
5	2013-11-14 19:04:02.639-02
5	2013-11-14 19:07:39.246-02
5	2013-11-14 19:10:18.047-02
5	2013-11-14 19:16:19.642-02
5	2013-11-14 19:18:18.859-02
5	2013-11-14 19:19:18.755-02
5	2013-11-14 19:24:41.787-02
5	2013-11-14 19:28:48.316-02
5	2013-11-14 19:33:49.775-02
5	2013-11-14 19:35:10.042-02
5	2013-11-14 19:36:09.844-02
5	2013-11-14 19:37:57.554-02
5	2013-11-14 19:39:23.648-02
5	2013-11-14 19:40:34.852-02
5	2013-11-14 19:41:25.811-02
5	2013-11-14 19:42:10.797-02
5	2013-11-14 19:48:49.641-02
5	2013-11-14 19:54:48.066-02
5	2013-11-14 19:57:26.262-02
5	2013-11-14 20:17:53.817-02
5	2013-11-14 20:21:19.915-02
5	2013-11-15 09:12:07.619-02
5	2013-11-15 09:18:53.794-02
5	2013-11-15 09:23:34.203-02
5	2013-11-15 09:26:15.145-02
5	2013-11-15 09:27:19.825-02
5	2013-11-15 09:42:10.491-02
5	2013-11-15 09:45:58.248-02
5	2013-11-15 10:04:11.339-02
5	2013-11-15 10:05:24.083-02
5	2013-11-15 10:18:56.594-02
5	2013-11-15 10:26:43.166-02
5	2013-11-15 10:35:21.875-02
5	2013-11-15 10:37:13.843-02
5	2013-11-17 20:14:16.228-02
5	2013-11-17 20:21:13.832-02
5	2013-11-17 20:33:01.663-02
5	2013-11-17 21:11:02.386-02
5	2013-11-17 23:25:19.551-02
5	2013-11-18 19:09:31.554-02
5	2013-11-18 19:17:49.985-02
5	2013-11-18 19:28:02.07-02
5	2013-11-18 19:53:26.935-02
5	2013-11-18 20:11:31.995-02
5	2013-11-18 20:19:16.967-02
5	2013-11-18 20:20:40.653-02
5	2013-11-18 20:29:14.663-02
5	2013-11-18 20:39:42.849-02
5	2013-11-18 20:44:05.347-02
5	2013-11-18 20:47:57.646-02
5	2013-11-18 20:54:10.802-02
5	2013-11-18 20:56:18.524-02
5	2013-11-18 21:00:47.114-02
5	2013-11-18 21:08:51.237-02
5	2013-11-18 21:09:52.028-02
5	2013-11-18 21:14:21.428-02
5	2013-11-18 21:19:14.545-02
5	2013-11-18 21:23:08.463-02
5	2013-11-18 21:24:50.238-02
5	2013-11-18 21:25:48.345-02
5	2013-11-18 21:29:22.579-02
5	2013-11-18 21:36:29.238-02
5	2013-11-18 21:41:29.864-02
5	2013-11-18 21:42:25.577-02
5	2013-11-18 22:11:30.971-02
5	2013-11-18 22:15:11.041-02
5	2013-11-18 22:18:54.576-02
5	2013-11-18 22:26:59.807-02
5	2013-11-18 22:42:10.188-02
5	2013-11-18 22:45:51.945-02
5	2013-11-18 22:47:13.512-02
5	2013-11-18 22:48:24.502-02
5	2013-11-18 22:51:42.687-02
5	2013-11-18 22:54:49.867-02
5	2013-11-18 22:58:59.481-02
5	2013-11-18 23:06:20.314-02
5	2013-11-18 23:07:47.239-02
5	2013-11-18 23:11:07.192-02
5	2013-11-18 23:12:08.603-02
5	2013-11-18 23:13:20.139-02
5	2013-11-18 23:14:06.005-02
5	2013-11-18 23:14:42.232-02
5	2013-11-18 23:20:06.763-02
5	2013-11-18 23:21:41.001-02
5	2013-11-18 23:23:56.32-02
5	2013-11-18 23:25:19.134-02
5	2013-11-18 23:28:14.68-02
5	2013-11-19 12:56:29.678-02
5	2013-11-19 12:57:31.651-02
5	2013-11-19 19:09:23.102-02
5	2013-11-19 19:15:24.489-02
5	2013-11-19 19:35:24.013-02
5	2013-11-19 19:40:53.856-02
5	2013-11-19 19:42:15.659-02
5	2013-11-19 19:44:07.674-02
5	2013-11-19 19:45:54.939-02
5	2013-11-19 19:59:28.161-02
5	2013-11-19 20:01:35.92-02
5	2013-11-19 20:10:19.343-02
5	2013-11-19 20:26:10.268-02
5	2013-11-19 20:29:29.576-02
5	2013-11-19 20:35:10.609-02
5	2013-11-19 21:24:18.351-02
5	2013-11-19 21:45:44.278-02
5	2013-11-19 22:12:19.199-02
5	2013-11-19 22:17:53.8-02
5	2013-11-19 22:21:04.812-02
5	2013-11-19 22:36:39.403-02
5	2013-11-19 22:38:54.962-02
5	2013-11-19 23:03:16.914-02
5	2013-11-19 23:10:27.803-02
5	2013-11-19 23:19:28.885-02
5	2013-11-19 23:21:23.312-02
5	2013-11-19 23:26:18.987-02
5	2013-11-20 18:56:21.332-02
5	2013-11-20 19:19:05.678-02
5	2013-11-20 19:20:02.849-02
5	2013-11-20 19:25:19.241-02
5	2013-11-20 19:27:23.272-02
5	2013-11-20 19:28:25.157-02
5	2013-11-20 19:42:15.254-02
5	2013-11-20 19:50:40.703-02
5	2013-11-20 19:53:16.251-02
5	2013-11-20 19:55:38.103-02
5	2013-11-20 20:00:01.055-02
5	2013-11-20 20:03:04.505-02
5	2013-11-20 20:12:00.635-02
5	2013-11-20 20:38:53.315-02
5	2013-11-20 20:41:42.083-02
5	2013-11-20 20:46:16.095-02
5	2013-11-20 20:51:44.755-02
5	2013-11-20 20:58:58.94-02
5	2013-11-20 21:02:28.135-02
5	2013-11-20 21:05:50.309-02
5	2013-11-20 21:17:39.14-02
5	2013-11-20 21:19:48.762-02
5	2013-11-20 21:24:12.659-02
5	2013-11-20 21:26:22.323-02
5	2013-11-20 22:01:41.779-02
5	2013-11-20 22:38:20.542-02
5	2013-11-21 20:51:07.932-02
5	2013-11-21 20:51:47.404-02
5	2013-11-21 20:52:17.705-02
5	2013-11-21 21:24:07.262-02
5	2013-11-21 21:32:10.542-02
5	2013-11-21 21:41:25.595-02
5	2013-11-21 21:53:31.786-02
5	2013-11-21 22:05:17.176-02
5	2013-11-21 22:05:42.469-02
5	2013-11-21 22:07:25.863-02
5	2013-11-21 22:17:36.37-02
5	2013-11-21 22:23:06.302-02
5	2013-11-21 22:24:44.322-02
5	2013-11-21 22:39:21.996-02
5	2013-11-21 23:43:41.364-02
5	2013-11-28 15:21:54.836-02
5	2013-11-28 16:04:03.983-02
5	2013-11-28 16:09:02.909-02
5	2013-11-28 16:43:04.729-02
5	2013-11-28 16:46:16.735-02
5	2013-11-28 17:21:04.152-02
5	2013-11-28 17:27:55.365-02
5	2013-11-28 19:32:16.714-02
5	2013-11-28 19:39:38.433-02
5	2013-12-04 19:39:22.405-02
5	2013-12-04 19:48:27.519-02
5	2013-12-04 20:03:13.531-02
5	2013-12-09 21:34:05.101-02
5	2013-12-09 22:23:47.22-02
5	2013-12-11 21:02:27.202-02
5	2013-12-12 22:33:34.706-02
5	2014-01-05 16:21:22.725-02
5	2014-01-05 17:50:39.16-02
5	2014-01-14 21:44:25.934-02
5	2014-01-15 20:50:36.047-02
5	2014-01-16 21:13:40.226-02
5	2014-01-20 19:42:51.436-02
5	2014-01-21 21:38:05.566-02
5	2014-01-22 23:36:54.125-02
5	2014-01-28 20:18:32.969-02
5	2014-02-08 16:14:20.599-02
5	2014-02-08 16:19:02.334-02
5	2014-02-08 16:21:49.624-02
5	2014-02-08 16:43:23.987-02
5	2014-02-08 17:17:55.113-02
5	2014-02-08 17:31:02.212-02
5	2014-02-08 17:33:05.102-02
5	2014-02-08 17:34:26.856-02
5	2014-02-08 17:39:45.799-02
5	2014-02-08 17:50:51.667-02
5	2014-02-08 17:53:40.376-02
5	2014-02-08 17:55:02.351-02
5	2014-02-08 18:51:43.17-02
5	2014-02-08 19:00:30.506-02
5	2014-02-09 16:09:56.496-02
5	2014-03-12 21:48:12.243-03
5	2014-03-19 22:25:11.529-03
5	2014-03-20 19:23:54.222-03
5	2014-03-20 20:18:38.115-03
5	2014-03-20 21:00:34.657-03
5	2014-03-20 21:19:53.471-03
5	2014-03-20 21:24:14.581-03
5	2014-03-20 21:24:50.591-03
\.


--
-- TOC entry 2219 (class 0 OID 41195)
-- Dependencies: 172
-- Data for Name: usuariopermissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuariopermissao (idusuario, permissao) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
5	"ROLE_ADMIN"
5	ROLE_ADMIN
5	ROLE_USER
6	ROLE_ADMIN
6	ROLE_USER
7	ROLE_USER
7	ROLE_ADMIN
\.


--
-- TOC entry 2110 (class 2606 OID 57519)
-- Name: PK_CIDADE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT "PK_CIDADE" PRIMARY KEY (idcidade);


--
-- TOC entry 2128 (class 2606 OID 74009)
-- Name: PK_CLIENTE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT "PK_CLIENTE" PRIMARY KEY (idempresa, idfilial, idcliente);


--
-- TOC entry 2125 (class 2606 OID 74217)
-- Name: PK_EMPRESA; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY representanterota
    ADD CONSTRAINT "PK_EMPRESA" PRIMARY KEY (idempresa, idrepresentante, dthrrota);


--
-- TOC entry 2112 (class 2606 OID 57532)
-- Name: PK_ESTADO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT "PK_ESTADO" PRIMARY KEY (idestado);


--
-- TOC entry 2149 (class 2606 OID 74327)
-- Name: PK_FILIALMOBILECONFIG; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filial_mobile_config
    ADD CONSTRAINT "PK_FILIALMOBILECONFIG" PRIMARY KEY (idempresa, idfilial);


--
-- TOC entry 2119 (class 2606 OID 65758)
-- Name: PK_GRUPPROD; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grupprod
    ADD CONSTRAINT "PK_GRUPPROD" PRIMARY KEY (idempresa, idfilial, idgrupo);


--
-- TOC entry 2068 (class 2606 OID 49357)
-- Name: PK_GRUPREP; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gruprep
    ADD CONSTRAINT "PK_GRUPREP" PRIMARY KEY (idempresa, idfilial, idgrupo);


--
-- TOC entry 2103 (class 2606 OID 49404)
-- Name: PK_GRUPREPPARCELA; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY gruprepparcela
    ADD CONSTRAINT "PK_GRUPREPPARCELA" PRIMARY KEY (idempresa, idfilial, idgrupo, idparcelamento);


--
-- TOC entry 2095 (class 2606 OID 49318)
-- Name: PK_GRUPREPRABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "PK_GRUPREPRABPRECO" PRIMARY KEY (idempresa, idfilial, idgrupo, idtabela);


--
-- TOC entry 2072 (class 2606 OID 41250)
-- Name: PK_IDMENU; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY menuaplicacao
    ADD CONSTRAINT "PK_IDMENU" PRIMARY KEY (idmenu);


--
-- TOC entry 2077 (class 2606 OID 41342)
-- Name: PK_IDUSUARIO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "PK_IDUSUARIO" PRIMARY KEY (idusuario);


--
-- TOC entry 2147 (class 2606 OID 74289)
-- Name: PK_MENSAGEM; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "PK_MENSAGEM" PRIMARY KEY (idempresa, idmensagem);


--
-- TOC entry 2088 (class 2606 OID 49283)
-- Name: PK_PARCELAMENTO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "PK_PARCELAMENTO" PRIMARY KEY (idempresa, idfilial, idparcelamento);


--
-- TOC entry 2134 (class 2606 OID 74035)
-- Name: PK_PEDIDO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "PK_PEDIDO" PRIMARY KEY (idempresa, idfilial, idpedido);


--
-- TOC entry 2139 (class 2606 OID 74074)
-- Name: PK_PEDIDOITEM; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido_item
    ADD CONSTRAINT "PK_PEDIDOITEM" PRIMARY KEY (idempresa, idfilial, idpedido, idproduto, sequencia);


--
-- TOC entry 2141 (class 2606 OID 74101)
-- Name: PK_PEDIDOPAGAMENTO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pedido_pagamento
    ADD CONSTRAINT "PK_PEDIDOPAGAMENTO" PRIMARY KEY (idempresa, idfilial, idpedido, sequencia);


--
-- TOC entry 2122 (class 2606 OID 73935)
-- Name: PK_PRODUTO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT "PK_PRODUTO" PRIMARY KEY (idempresa, idfilial, idproduto);


--
-- TOC entry 2115 (class 2606 OID 57548)
-- Name: PK_REPFILIAL; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY repfilial
    ADD CONSTRAINT "PK_REPFILIAL" PRIMARY KEY (idempresa, idfilial, idrepresentante);


--
-- TOC entry 2107 (class 2606 OID 49402)
-- Name: PK_REPPARCELA; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY repparcela
    ADD CONSTRAINT "PK_REPPARCELA" PRIMARY KEY (idempresa, idfilial, idrepresentante, idparcelamento);


--
-- TOC entry 2082 (class 2606 OID 57588)
-- Name: PK_REPRESENTANTE; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY representante
    ADD CONSTRAINT "PK_REPRESENTANTE" PRIMARY KEY (idempresa, idrepresentante);


--
-- TOC entry 2098 (class 2606 OID 49340)
-- Name: PK_REPTABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "PK_REPTABPRECO" PRIMARY KEY (idempresa, idfilial, idrepresentante, idtabela);


--
-- TOC entry 2154 (class 2606 OID 74417)
-- Name: PK_SINCRONIZACAO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sincronizacao
    ADD CONSTRAINT "PK_SINCRONIZACAO" PRIMARY KEY (idsincronizacao);


--
-- TOC entry 2136 (class 2606 OID 74033)
-- Name: PK_STATUS; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY status
    ADD CONSTRAINT "PK_STATUS" PRIMARY KEY (idstatus);


--
-- TOC entry 2084 (class 2606 OID 49262)
-- Name: PK_TABPRECO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "PK_TABPRECO" PRIMARY KEY (idempresa, idfilial, idtabela);


--
-- TOC entry 2117 (class 2606 OID 74228)
-- Name: PK_USUARIOACESSO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarioacesso
    ADD CONSTRAINT "PK_USUARIOACESSO" PRIMARY KEY (idusuario, dthracesso);


--
-- TOC entry 2086 (class 2606 OID 49290)
-- Name: UK_FILIAL_DESCRICAO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "UK_FILIAL_DESCRICAO" UNIQUE (idempresa, idfilial, descricao);


--
-- TOC entry 2090 (class 2606 OID 49292)
-- Name: UK_PARCELAMENTO_DESCRICAO; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "UK_PARCELAMENTO_DESCRICAO" UNIQUE (idempresa, idfilial, descricao);


--
-- TOC entry 2079 (class 2606 OID 41322)
-- Name: UK_USUARIO_LOGIN; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "UK_USUARIO_LOGIN" UNIQUE (login);


--
-- TOC entry 2156 (class 2606 OID 74441)
-- Name: devices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY devices
    ADD CONSTRAINT devices_pkey PRIMARY KEY (id);


--
-- TOC entry 2062 (class 2606 OID 41152)
-- Name: pk_idempresa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT pk_idempresa PRIMARY KEY (idempresa);


--
-- TOC entry 2065 (class 2606 OID 41157)
-- Name: pk_idempresa_idfilial; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filial
    ADD CONSTRAINT pk_idempresa_idfilial PRIMARY KEY (idempresa, idfilial);


--
-- TOC entry 2070 (class 2606 OID 41228)
-- Name: pk_usuariopermisao; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuariopermissao
    ADD CONSTRAINT pk_usuariopermisao PRIMARY KEY (idusuario, permissao);


--
-- TOC entry 2126 (class 1259 OID 74025)
-- Name: FKI_CIDADE_CLIENTE; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_CIDADE_CLIENTE" ON cliente USING btree (cidade);


--
-- TOC entry 2080 (class 1259 OID 57530)
-- Name: FKI_CIDADE_REPRESENTANTE; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_CIDADE_REPRESENTANTE" ON representante USING btree (idcidade);


--
-- TOC entry 2108 (class 1259 OID 57538)
-- Name: FKI_ESTADO_CIDADE; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_ESTADO_CIDADE" ON cidade USING btree (idestado);


--
-- TOC entry 2066 (class 1259 OID 49355)
-- Name: FKI_FILIAL_GRUPREP; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_GRUPREP" ON gruprep USING btree (idempresa, idfilial);


--
-- TOC entry 2099 (class 1259 OID 49349)
-- Name: FKI_FILIAL_GRUPREPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_GRUPREPPARCELA" ON gruprepparcela USING btree (idempresa, idfilial);


--
-- TOC entry 2091 (class 1259 OID 49304)
-- Name: FKI_FILIAL_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idfilial);


--
-- TOC entry 2104 (class 1259 OID 49388)
-- Name: FKI_FILIAL_REPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_FILIAL_REPPARCELA" ON repparcela USING btree (idfilial, idempresa);


--
-- TOC entry 2113 (class 1259 OID 57569)
-- Name: FKI_GRUPOREP_REPFILIAL; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_GRUPOREP_REPFILIAL" ON repfilial USING btree (idempresa, idfilial, idgrupo);


--
-- TOC entry 2120 (class 1259 OID 73956)
-- Name: FKI_GRUPPROD_PRODUTO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_GRUPPROD_PRODUTO" ON produto USING btree (idempresa, idfilial, idgrupo);


--
-- TOC entry 2100 (class 1259 OID 49368)
-- Name: FKI_GRUPREP_GRUPREPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_GRUPREP_GRUPREPPARCELA" ON gruprepparcela USING btree (idempresa, idfilial, idgrupo);


--
-- TOC entry 2092 (class 1259 OID 49316)
-- Name: FKI_GRUPREP_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_GRUPREP_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idgrupo);


--
-- TOC entry 2129 (class 1259 OID 74063)
-- Name: FKI_PARCELAMENTO_PEDIDO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_PARCELAMENTO_PEDIDO" ON pedido USING btree (idempresa, idfilial, parcelamento);


--
-- TOC entry 2101 (class 1259 OID 49374)
-- Name: FKI_PARCELA_GRUPREPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_PARCELA_GRUPREPPARCELA" ON gruprepparcela USING btree (idempresa, idfilial, idparcelamento);


--
-- TOC entry 2105 (class 1259 OID 49400)
-- Name: FKI_PARCELA_REPPARCELA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_PARCELA_REPPARCELA" ON repparcela USING btree (idempresa, idfilial, idparcelamento);


--
-- TOC entry 2137 (class 1259 OID 74091)
-- Name: FKI_PRODUTO_PEDIDOITEM; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_PRODUTO_PEDIDOITEM" ON pedido_item USING btree (idempresa, idfilial, idproduto);


--
-- TOC entry 2130 (class 1259 OID 74051)
-- Name: FKI_REPRESENTANTEFILIAL_PEDIDO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTEFILIAL_PEDIDO" ON pedido USING btree (idempresa, idfilial, idrepresentante);


--
-- TOC entry 2142 (class 1259 OID 74315)
-- Name: FKI_REPRESENTANTE_MENSAGEM_DESTINO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTE_MENSAGEM_DESTINO" ON mensagem USING btree (representante_destino, idempresa);


--
-- TOC entry 2143 (class 1259 OID 74316)
-- Name: FKI_REPRESENTANTE_MENSAGEM_ORIGEM; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTE_MENSAGEM_ORIGEM" ON mensagem USING btree (representante_origem, idempresa);


--
-- TOC entry 2123 (class 1259 OID 74000)
-- Name: FKI_REPRESENTANTE_REPRESENTANTEROTA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTE_REPRESENTANTEROTA" ON representanterota USING btree (idrepresentante, idempresa);


--
-- TOC entry 2073 (class 1259 OID 57604)
-- Name: FKI_REPRESENTANTE_USUARIO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_REPRESENTANTE_USUARIO" ON usuario USING btree (idempresa, idrepresentante);


--
-- TOC entry 2150 (class 1259 OID 74403)
-- Name: FKI_SINCRONIZACAO_EMPRESA; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_SINCRONIZACAO_EMPRESA" ON sincronizacao USING btree (idempresa);


--
-- TOC entry 2151 (class 1259 OID 74409)
-- Name: FKI_SINCRONIZACAO_FILIAL; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_SINCRONIZACAO_FILIAL" ON sincronizacao USING btree (idempresa, idfilial);


--
-- TOC entry 2152 (class 1259 OID 74415)
-- Name: FKI_SINCRONIZACAO_REPRESENTANTEFILIAL; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_SINCRONIZACAO_REPRESENTANTEFILIAL" ON sincronizacao USING btree (idfilial, idempresa, idrepresentante);


--
-- TOC entry 2131 (class 1259 OID 74069)
-- Name: FKI_STATUS_PEDIDO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_STATUS_PEDIDO" ON pedido USING btree (status);


--
-- TOC entry 2093 (class 1259 OID 49310)
-- Name: FKI_TABELAPRECO_GRUPREPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_TABELAPRECO_GRUPREPTABPRECO" ON grupreptabpreco USING btree (idempresa, idfilial, idtabela);


--
-- TOC entry 2132 (class 1259 OID 74057)
-- Name: FKI_TABPRECO_PEDIDO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_TABPRECO_PEDIDO" ON pedido USING btree (idempresa, idfilial, tabelapreco);


--
-- TOC entry 2096 (class 1259 OID 49338)
-- Name: FKI_TABPRECO_REPTABPRECO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_TABPRECO_REPTABPRECO" ON reptabpreco USING btree (idempresa, idfilial, idtabela);


--
-- TOC entry 2144 (class 1259 OID 74317)
-- Name: FKI_USUARIO_MENSAGEM_DESTINO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_MENSAGEM_DESTINO" ON mensagem USING btree (usuario_destino);


--
-- TOC entry 2145 (class 1259 OID 74318)
-- Name: FKI_USUARIO_MENSAGEM_ORIGEM; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_MENSAGEM_ORIGEM" ON mensagem USING btree (usuario_origem);


--
-- TOC entry 2074 (class 1259 OID 41598)
-- Name: FKI_USUARIO_REPRESENTANTE; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_REPRESENTANTE" ON usuario USING btree (idempresa, idfilial, idrepresentante);


--
-- TOC entry 2075 (class 1259 OID 41577)
-- Name: FKI_USUARIO_USUARIOGRUPO; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "FKI_USUARIO_USUARIOGRUPO" ON usuario USING btree (idempresa, idgrupo);


--
-- TOC entry 2063 (class 1259 OID 41163)
-- Name: fki_filial_empresa; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_filial_empresa ON filial USING btree (idempresa);


--
-- TOC entry 2214 (class 2620 OID 74433)
-- Name: atualiza_estoque; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER atualiza_estoque AFTER INSERT ON pedido_item FOR EACH ROW EXECUTE PROCEDURE atualiza_estoque();


--
-- TOC entry 2191 (class 2606 OID 74020)
-- Name: FK_CIDADE_CLIENTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT "FK_CIDADE_CLIENTE" FOREIGN KEY (cidade) REFERENCES cidade(idcidade);


--
-- TOC entry 2161 (class 2606 OID 57525)
-- Name: FK_CIDADE_REPRESENTANTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY representante
    ADD CONSTRAINT "FK_CIDADE_REPRESENTANTE" FOREIGN KEY (idcidade) REFERENCES cidade(idcidade);


--
-- TOC entry 2189 (class 2606 OID 74010)
-- Name: FK_EMPRESA_CLIENTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT "FK_EMPRESA_CLIENTE" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2210 (class 2606 OID 74328)
-- Name: FK_EMPRESA_FILIALMOBILECONFIG; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filial_mobile_config
    ADD CONSTRAINT "FK_EMPRESA_FILIALMOBILECONFIG" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2183 (class 2606 OID 65764)
-- Name: FK_EMPRESA_GRUPPROD; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupprod
    ADD CONSTRAINT "FK_EMPRESA_GRUPPROD" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2205 (class 2606 OID 74290)
-- Name: FK_EMPRESA_MENSAGEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "FK_EMPRESA_MENSAGEM" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2192 (class 2606 OID 74036)
-- Name: FK_EMPRESA_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_EMPRESA_PEDIDO" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2198 (class 2606 OID 74076)
-- Name: FK_EMPRESA_PEDIDOITEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_item
    ADD CONSTRAINT "FK_EMPRESA_PEDIDOITEM" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2202 (class 2606 OID 74102)
-- Name: FK_EMPRESA_PEDIDOPAGAMENTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_pagamento
    ADD CONSTRAINT "FK_EMPRESA_PEDIDOPAGAMENTO" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2185 (class 2606 OID 73946)
-- Name: FK_EMPRESA_PRODUTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT "FK_EMPRESA_PRODUTO" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2180 (class 2606 OID 57559)
-- Name: FK_EMPRESA_REPFILIAL; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repfilial
    ADD CONSTRAINT "FK_EMPRESA_REPFILIAL" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2162 (class 2606 OID 57589)
-- Name: FK_EMPRESA_REPRESENTANTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY representante
    ADD CONSTRAINT "FK_EMPRESA_REPRESENTANTE" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2187 (class 2606 OID 73990)
-- Name: FK_EMPRESA_REPRESENTANTEROTA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY representanterota
    ADD CONSTRAINT "FK_EMPRESA_REPRESENTANTEROTA" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2177 (class 2606 OID 57539)
-- Name: FK_ESTADO_CIDADE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cidade
    ADD CONSTRAINT "FK_ESTADO_CIDADE" FOREIGN KEY (idestado) REFERENCES estado(idestado);


--
-- TOC entry 2190 (class 2606 OID 74015)
-- Name: FK_FILIAL_CLIENTE; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT "FK_FILIAL_CLIENTE" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2182 (class 2606 OID 65759)
-- Name: FK_FILIAL_GRUPPROD; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupprod
    ADD CONSTRAINT "FK_FILIAL_GRUPPROD" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2158 (class 2606 OID 49350)
-- Name: FK_FILIAL_GRUPREP; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprep
    ADD CONSTRAINT "FK_FILIAL_GRUPREP" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2171 (class 2606 OID 49344)
-- Name: FK_FILIAL_GRUPREPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprepparcela
    ADD CONSTRAINT "FK_FILIAL_GRUPREPPARCELA" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2165 (class 2606 OID 49299)
-- Name: FK_FILIAL_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_FILIAL_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2164 (class 2606 OID 49284)
-- Name: FK_FILIAL_PARCELAMENTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY parcelamento
    ADD CONSTRAINT "FK_FILIAL_PARCELAMENTO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2193 (class 2606 OID 74041)
-- Name: FK_FILIAL_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_FILIAL_PEDIDO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2199 (class 2606 OID 74081)
-- Name: FK_FILIAL_PEDIDOITEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_item
    ADD CONSTRAINT "FK_FILIAL_PEDIDOITEM" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2203 (class 2606 OID 74107)
-- Name: FK_FILIAL_PEDIDOPAGAMENTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_pagamento
    ADD CONSTRAINT "FK_FILIAL_PEDIDOPAGAMENTO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2184 (class 2606 OID 73941)
-- Name: FK_FILIAL_PRODUTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT "FK_FILIAL_PRODUTO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2178 (class 2606 OID 57554)
-- Name: FK_FILIAL_REPFILIAL; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repfilial
    ADD CONSTRAINT "FK_FILIAL_REPFILIAL" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2174 (class 2606 OID 49383)
-- Name: FK_FILIAL_REPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repparcela
    ADD CONSTRAINT "FK_FILIAL_REPPARCELA" FOREIGN KEY (idfilial, idempresa) REFERENCES filial(idfilial, idempresa);


--
-- TOC entry 2167 (class 2606 OID 49322)
-- Name: FK_FILIAL_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_FILIAL_REPTABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2163 (class 2606 OID 49263)
-- Name: FK_FILIAL_TABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tabpreco
    ADD CONSTRAINT "FK_FILIAL_TABPRECO" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2179 (class 2606 OID 57564)
-- Name: FK_GRUPOREP_REPFILIAL; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repfilial
    ADD CONSTRAINT "FK_GRUPOREP_REPFILIAL" FOREIGN KEY (idempresa, idfilial, idgrupo) REFERENCES gruprep(idempresa, idfilial, idgrupo);


--
-- TOC entry 2186 (class 2606 OID 73951)
-- Name: FK_GRUPPROD_PRODUTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT "FK_GRUPPROD_PRODUTO" FOREIGN KEY (idempresa, idfilial, idgrupo) REFERENCES grupprod(idempresa, idfilial, idgrupo);


--
-- TOC entry 2172 (class 2606 OID 49363)
-- Name: FK_GRUPREP_GRUPREPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprepparcela
    ADD CONSTRAINT "FK_GRUPREP_GRUPREPPARCELA" FOREIGN KEY (idempresa, idfilial, idgrupo) REFERENCES gruprep(idempresa, idfilial, idgrupo);


--
-- TOC entry 2168 (class 2606 OID 49358)
-- Name: FK_GRUPREP_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_GRUPREP_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idfilial, idgrupo) REFERENCES gruprep(idempresa, idfilial, idgrupo);


--
-- TOC entry 2196 (class 2606 OID 74058)
-- Name: FK_PARCELAMENTO_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_PARCELAMENTO_PEDIDO" FOREIGN KEY (idempresa, idfilial, parcelamento) REFERENCES parcelamento(idempresa, idfilial, idparcelamento);


--
-- TOC entry 2173 (class 2606 OID 49369)
-- Name: FK_PARCELA_GRUPREPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY gruprepparcela
    ADD CONSTRAINT "FK_PARCELA_GRUPREPPARCELA" FOREIGN KEY (idempresa, idfilial, idparcelamento) REFERENCES parcelamento(idempresa, idfilial, idparcelamento);


--
-- TOC entry 2175 (class 2606 OID 49395)
-- Name: FK_PARCELA_REPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repparcela
    ADD CONSTRAINT "FK_PARCELA_REPPARCELA" FOREIGN KEY (idempresa, idfilial, idparcelamento) REFERENCES parcelamento(idempresa, idfilial, idparcelamento);


--
-- TOC entry 2201 (class 2606 OID 74095)
-- Name: FK_PEDIDO_PEDIDOITEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_item
    ADD CONSTRAINT "FK_PEDIDO_PEDIDOITEM" FOREIGN KEY (idempresa, idfilial, idpedido) REFERENCES pedido(idempresa, idfilial, idpedido);


--
-- TOC entry 2204 (class 2606 OID 74112)
-- Name: FK_PEDIDO_PEDIDOPAGAMENTO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_pagamento
    ADD CONSTRAINT "FK_PEDIDO_PEDIDOPAGAMENTO" FOREIGN KEY (idempresa, idfilial, idpedido) REFERENCES pedido(idempresa, idfilial, idpedido);


--
-- TOC entry 2200 (class 2606 OID 74086)
-- Name: FK_PRODUTO_PEDIDOITEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido_item
    ADD CONSTRAINT "FK_PRODUTO_PEDIDOITEM" FOREIGN KEY (idempresa, idfilial, idproduto) REFERENCES produto(idempresa, idfilial, idproduto);


--
-- TOC entry 2176 (class 2606 OID 57575)
-- Name: FK_REPFILIAL_REPPARCELA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY repparcela
    ADD CONSTRAINT "FK_REPFILIAL_REPPARCELA" FOREIGN KEY (idempresa, idfilial, idrepresentante) REFERENCES repfilial(idempresa, idfilial, idrepresentante);


--
-- TOC entry 2170 (class 2606 OID 57580)
-- Name: FK_REPFILIAL_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "FK_REPFILIAL_REPTABPRECO" FOREIGN KEY (idempresa, idfilial, idrepresentante) REFERENCES repfilial(idempresa, idfilial, idrepresentante);


--
-- TOC entry 2194 (class 2606 OID 74046)
-- Name: FK_REPRESENTANTEFILIAL_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_REPRESENTANTEFILIAL_PEDIDO" FOREIGN KEY (idempresa, idfilial, idrepresentante) REFERENCES repfilial(idempresa, idfilial, idrepresentante);


--
-- TOC entry 2206 (class 2606 OID 74295)
-- Name: FK_REPRESENTANTE_MENSAGEM_DESTINO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "FK_REPRESENTANTE_MENSAGEM_DESTINO" FOREIGN KEY (representante_destino, idempresa) REFERENCES representante(idrepresentante, idempresa);


--
-- TOC entry 2207 (class 2606 OID 74300)
-- Name: FK_REPRESENTANTE_MENSAGEM_ORIGEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "FK_REPRESENTANTE_MENSAGEM_ORIGEM" FOREIGN KEY (representante_origem, idempresa) REFERENCES representante(idrepresentante, idempresa);


--
-- TOC entry 2188 (class 2606 OID 73995)
-- Name: FK_REPRESENTANTE_REPRESENTANTEROTA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY representanterota
    ADD CONSTRAINT "FK_REPRESENTANTE_REPRESENTANTEROTA" FOREIGN KEY (idrepresentante, idempresa) REFERENCES representante(idrepresentante, idempresa);


--
-- TOC entry 2160 (class 2606 OID 57599)
-- Name: FK_REPRESENTANTE_USUARIO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT "FK_REPRESENTANTE_USUARIO" FOREIGN KEY (idempresa, idrepresentante) REFERENCES representante(idempresa, idrepresentante);


--
-- TOC entry 2211 (class 2606 OID 74398)
-- Name: FK_SINCRONIZACAO_EMPRESA; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sincronizacao
    ADD CONSTRAINT "FK_SINCRONIZACAO_EMPRESA" FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2212 (class 2606 OID 74404)
-- Name: FK_SINCRONIZACAO_FILIAL; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sincronizacao
    ADD CONSTRAINT "FK_SINCRONIZACAO_FILIAL" FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2213 (class 2606 OID 74410)
-- Name: FK_SINCRONIZACAO_REPRESENTANTEFILIAL; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sincronizacao
    ADD CONSTRAINT "FK_SINCRONIZACAO_REPRESENTANTEFILIAL" FOREIGN KEY (idfilial, idempresa, idrepresentante) REFERENCES repfilial(idfilial, idempresa, idrepresentante);


--
-- TOC entry 2197 (class 2606 OID 74064)
-- Name: FK_STATUS_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_STATUS_PEDIDO" FOREIGN KEY (status) REFERENCES status(idstatus);


--
-- TOC entry 2166 (class 2606 OID 49305)
-- Name: FK_TABELAPRECO_GRUPREPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grupreptabpreco
    ADD CONSTRAINT "FK_TABELAPRECO_GRUPREPTABPRECO" FOREIGN KEY (idempresa, idfilial, idtabela) REFERENCES tabpreco(idempresa, idfilial, idtabela);


--
-- TOC entry 2195 (class 2606 OID 74052)
-- Name: FK_TABPRECO_PEDIDO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT "FK_TABPRECO_PEDIDO" FOREIGN KEY (idempresa, idfilial, tabelapreco) REFERENCES tabpreco(idempresa, idfilial, idtabela);


--
-- TOC entry 2169 (class 2606 OID 49333)
-- Name: FK_TABPRECO_REPTABPRECO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reptabpreco
    ADD CONSTRAINT "FK_TABPRECO_REPTABPRECO" FOREIGN KEY (idempresa, idfilial, idtabela) REFERENCES tabpreco(idempresa, idfilial, idtabela);


--
-- TOC entry 2208 (class 2606 OID 74305)
-- Name: FK_USUARIO_MENSAGEM_DESTINO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "FK_USUARIO_MENSAGEM_DESTINO" FOREIGN KEY (usuario_destino) REFERENCES usuario(idusuario);


--
-- TOC entry 2209 (class 2606 OID 74310)
-- Name: FK_USUARIO_MENSAGEM_ORIGEM; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mensagem
    ADD CONSTRAINT "FK_USUARIO_MENSAGEM_ORIGEM" FOREIGN KEY (usuario_origem) REFERENCES usuario(idusuario);


--
-- TOC entry 2181 (class 2606 OID 65749)
-- Name: FK_USUARIO_USUARIOACESSO; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarioacesso
    ADD CONSTRAINT "FK_USUARIO_USUARIOACESSO" FOREIGN KEY (idusuario) REFERENCES usuario(idusuario);


--
-- TOC entry 2157 (class 2606 OID 74333)
-- Name: fk_filial_empresa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filial
    ADD CONSTRAINT fk_filial_empresa FOREIGN KEY (idempresa) REFERENCES empresa(idempresa);


--
-- TOC entry 2159 (class 2606 OID 41323)
-- Name: fk_idfilial; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_idfilial FOREIGN KEY (idempresa, idfilial) REFERENCES filial(idempresa, idfilial);


--
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-03-24 19:49:44

--
-- PostgreSQL database dump complete
--

