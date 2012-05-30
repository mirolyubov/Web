--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: class_seq; Type: SEQUENCE; Schema: public; Owner: kite
--

CREATE SEQUENCE class_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.class_seq OWNER TO kite;

--
-- Name: class_seq; Type: SEQUENCE SET; Schema: public; Owner: kite
--

SELECT pg_catalog.setval('class_seq', 5, true);


--
-- Name: family_seq; Type: SEQUENCE; Schema: public; Owner: kite
--

CREATE SEQUENCE family_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.family_seq OWNER TO kite;

--
-- Name: family_seq; Type: SEQUENCE SET; Schema: public; Owner: kite
--

SELECT pg_catalog.setval('family_seq', 1, false);


--
-- Name: object_seq; Type: SEQUENCE; Schema: public; Owner: kite
--

CREATE SEQUENCE object_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.object_seq OWNER TO kite;

--
-- Name: object_seq; Type: SEQUENCE SET; Schema: public; Owner: kite
--

SELECT pg_catalog.setval('object_seq', 1, false);


--
-- Name: object_value_seq; Type: SEQUENCE; Schema: public; Owner: kite
--

CREATE SEQUENCE object_value_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.object_value_seq OWNER TO kite;

--
-- Name: object_value_seq; Type: SEQUENCE SET; Schema: public; Owner: kite
--

SELECT pg_catalog.setval('object_value_seq', 1, false);


--
-- Name: style_seq; Type: SEQUENCE; Schema: public; Owner: kite
--

CREATE SEQUENCE style_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.style_seq OWNER TO kite;

--
-- Name: style_seq; Type: SEQUENCE SET; Schema: public; Owner: kite
--

SELECT pg_catalog.setval('style_seq', 148, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: t_class; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_class (
    class_id integer DEFAULT nextval('class_seq'::regclass) NOT NULL,
    name character varying(32),
    description character varying(512)
);


ALTER TABLE public.t_class OWNER TO kite;

--
-- Name: t_class_style; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_class_style (
    class_id integer,
    style_id integer
);


ALTER TABLE public.t_class_style OWNER TO kite;

--
-- Name: t_family; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_family (
    family_id integer DEFAULT nextval('family_seq'::regclass) NOT NULL,
    name character varying(32),
    description character varying(512)
);


ALTER TABLE public.t_family OWNER TO kite;

--
-- Name: t_object; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_object (
    object_id integer DEFAULT nextval('object_seq'::regclass) NOT NULL,
    object_name character varying(32),
    class_id integer
);


ALTER TABLE public.t_object OWNER TO kite;

--
-- Name: t_object_value; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_object_value (
    object_value_id integer DEFAULT nextval('object_value_seq'::regclass) NOT NULL,
    object_id integer,
    style_id integer,
    value character varying(512)
);


ALTER TABLE public.t_object_value OWNER TO kite;

--
-- Name: t_style; Type: TABLE; Schema: public; Owner: kite; Tablespace: 
--

CREATE TABLE t_style (
    style_id integer DEFAULT nextval('style_seq'::regclass) NOT NULL,
    family_id integer,
    is_mandatory boolean,
    is_multiple boolean
);


ALTER TABLE public.t_style OWNER TO kite;

--
-- Data for Name: t_class; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_class (class_id, name, description) FROM stdin;
1	class1	desc1
2	class2	desc2
3	class3	desc3
4	class4	desc4
5	class5	desc5
\.


--
-- Data for Name: t_class_style; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_class_style (class_id, style_id) FROM stdin;
1	1
1	2
1	3
1	4
1	5
1	6
1	7
1	8
1	9
1	10
2	1
2	2
2	3
2	4
2	5
2	6
2	7
2	8
2	9
2	10
3	1
3	2
3	3
3	4
3	5
3	6
3	7
3	8
3	9
3	10
4	1
4	2
4	3
4	4
4	5
4	6
4	7
4	8
4	9
4	10
5	1
5	2
5	3
5	4
5	5
5	6
5	7
5	8
5	9
5	10
\.


--
-- Data for Name: t_family; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_family (family_id, name, description) FROM stdin;
1	family1	desc1
2	family2	desc2
3	family3	desc3
4	family4	desc4
5	family5	desc5
\.


--
-- Data for Name: t_object; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_object (object_id, object_name, class_id) FROM stdin;
1	object1	1
2	object2	1
3	object3	1
4	object4	1
5	object5	1
6	object6	1
7	object7	1
8	object8	1
9	object9	1
10	object10	1
11	object11	2
12	object12	2
13	object13	2
14	object14	2
15	object15	2
16	object16	2
17	object17	2
18	object18	2
19	object19	2
20	object20	2
21	object21	3
22	object22	3
23	object23	3
24	object24	3
25	object25	3
26	object26	3
27	object27	3
28	object28	3
29	object29	3
30	object30	3
31	object31	4
32	object32	4
33	object33	4
34	object34	4
35	object35	4
36	object36	4
37	object37	4
38	object38	4
39	object39	4
40	object40	4
41	object41	5
42	object42	5
43	object43	5
44	object44	5
45	object45	5
46	object46	5
47	object47	5
48	object48	5
49	object49	5
50	object50	5
\.


--
-- Data for Name: t_object_value; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_object_value (object_value_id, object_id, style_id, value) FROM stdin;
0	2	14	value0
1	2	36	value1
2	3	16	value2
3	3	40	value3
4	4	23	value4
5	4	38	value5
6	4	37	value6
7	4	43	value7
8	5	48	value8
9	6	20	value9
10	6	47	value10
11	7	36	value11
12	7	2	value12
13	7	27	value13
14	8	14	value14
15	9	29	value15
16	9	12	value16
17	9	35	value17
18	11	3	value18
19	11	20	value19
20	12	13	value20
21	13	19	value21
22	13	5	value22
23	14	6	value23
24	14	39	value24
25	15	49	value25
26	17	45	value26
27	17	34	value27
28	17	12	value28
29	17	34	value29
30	18	4	value30
31	18	49	value31
32	18	9	value32
33	20	43	value33
34	20	43	value34
35	21	39	value35
36	21	45	value36
37	22	27	value37
40	23	23	value40
41	23	26	value41
42	23	16	value42
43	25	25	value43
44	25	16	value44
45	26	43	value45
46	26	31	value46
47	26	45	value47
48	26	43	value48
49	27	5	value49
50	27	27	value50
51	27	24	value51
52	28	49	value52
53	30	3	value53
54	31	25	value54
55	32	9	value55
56	32	6	value56
57	32	41	value57
58	33	25	value58
59	33	3	value59
60	36	17	value60
61	36	30	value61
62	36	47	value62
63	36	19	value63
64	37	50	value64
65	37	2	value65
66	37	38	value66
67	37	32	value67
68	38	31	value68
69	38	21	value69
70	38	22	value70
71	38	26	value71
72	39	19	value72
73	39	46	value73
74	39	49	value74
75	40	21	value75
76	40	19	value76
77	40	21	value77
78	40	13	value78
79	41	24	value79
80	42	35	value80
81	42	44	value81
82	43	48	value82
83	43	44	value83
84	43	6	value84
85	43	34	value85
86	46	22	value86
87	46	20	value87
88	46	24	value88
89	47	31	value89
90	47	14	value90
91	47	26	value91
92	48	15	value92
93	49	6	value93
94	49	27	value94
95	49	27	value95
96	50	26	value96
97	50	5	value97
98	50	12	value98
99	50	3	value99
\.


--
-- Data for Name: t_style; Type: TABLE DATA; Schema: public; Owner: kite
--

COPY t_style (style_id, family_id, is_mandatory, is_multiple) FROM stdin;
1	1	t	t
2	1	t	t
3	1	t	t
4	1	t	t
5	1	t	t
6	1	t	t
7	1	t	t
8	1	t	t
9	1	t	t
10	1	t	t
11	2	t	t
12	2	t	t
13	2	t	t
14	2	t	t
15	2	t	t
16	2	t	t
17	2	t	t
18	2	t	t
19	2	t	t
20	2	t	t
21	3	t	t
22	3	t	t
23	3	t	t
24	3	t	t
25	3	t	t
26	3	t	t
27	3	t	t
28	3	t	t
29	3	t	t
30	3	t	t
31	4	t	t
32	4	t	t
33	4	t	t
34	4	t	t
35	4	t	t
36	4	t	t
37	4	t	t
38	4	t	t
39	4	t	t
40	4	t	t
41	5	t	t
42	5	t	t
45	5	t	t
46	5	t	t
47	5	t	t
48	5	t	t
49	5	t	t
50	5	t	t
43	5	t	t
44	5	t	t
\.


--
-- Name: t_class_pkey; Type: CONSTRAINT; Schema: public; Owner: kite; Tablespace: 
--

ALTER TABLE ONLY t_class
    ADD CONSTRAINT t_class_pkey PRIMARY KEY (class_id);


--
-- Name: t_family_pkey; Type: CONSTRAINT; Schema: public; Owner: kite; Tablespace: 
--

ALTER TABLE ONLY t_family
    ADD CONSTRAINT t_family_pkey PRIMARY KEY (family_id);


--
-- Name: t_object_pkey; Type: CONSTRAINT; Schema: public; Owner: kite; Tablespace: 
--

ALTER TABLE ONLY t_object
    ADD CONSTRAINT t_object_pkey PRIMARY KEY (object_id);


--
-- Name: t_object_value_pkey; Type: CONSTRAINT; Schema: public; Owner: kite; Tablespace: 
--

ALTER TABLE ONLY t_object_value
    ADD CONSTRAINT t_object_value_pkey PRIMARY KEY (object_value_id);


--
-- Name: t_style_pkey; Type: CONSTRAINT; Schema: public; Owner: kite; Tablespace: 
--

ALTER TABLE ONLY t_style
    ADD CONSTRAINT t_style_pkey PRIMARY KEY (style_id);


--
-- Name: t_class_style_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_class_style
    ADD CONSTRAINT t_class_style_class_id_fkey FOREIGN KEY (class_id) REFERENCES t_class(class_id);


--
-- Name: t_class_style_style_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_class_style
    ADD CONSTRAINT t_class_style_style_id_fkey FOREIGN KEY (style_id) REFERENCES t_style(style_id);


--
-- Name: t_object_class_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_object
    ADD CONSTRAINT t_object_class_id_fkey FOREIGN KEY (class_id) REFERENCES t_class(class_id);


--
-- Name: t_object_value_object_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_object_value
    ADD CONSTRAINT t_object_value_object_id_fkey FOREIGN KEY (object_id) REFERENCES t_object(object_id);


--
-- Name: t_object_value_style_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_object_value
    ADD CONSTRAINT t_object_value_style_id_fkey FOREIGN KEY (style_id) REFERENCES t_style(style_id);


--
-- Name: t_style_family_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kite
--

ALTER TABLE ONLY t_style
    ADD CONSTRAINT t_style_family_id_fkey FOREIGN KEY (family_id) REFERENCES t_family(family_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

