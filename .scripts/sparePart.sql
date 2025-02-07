CREATE TABLE IF NOT EXISTS public.sparePart
(
	id_sparePart bigint NOT NULL,
	reference character varying(100),
	sparePart_name character varying (100),
	sparePart_type character varying (50),
	maker character varying (100),
	price decimal (10 , 2),
	unitsAvailable int,
	unitsReserved int,
	status character varying(50),
	orderStatus character varying(50)
);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.sparePart
ADD CONSTRAINT sparePart_pkey PRIMARY KEY (id);