--
-- PostgreSQL database cluster dump
--

-- Started on 2013-10-01 19:00:21

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION PASSWORD 'md52a29a4f7eb0a98abca0992ca3fb555b6';
CREATE ROLE root;
ALTER ROLE root WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION PASSWORD 'md5b4b8daf4b8ea9d39568719e1e320076f' VALID UNTIL 'infinity';






-- Completed on 2013-10-01 19:00:21

--
-- PostgreSQL database cluster dump complete
--

