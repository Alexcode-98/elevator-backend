CREATE TABLE IF NOT EXISTS public.user
(
	id_user bigint NOT NULL,
	username character varying(100),
	password character varying(255),
	email character varying(100),
	name character varying(100),
	address character varying(255),
	telephone character varying(20),
	registation DATE;	
)

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.user
ADD CONSTRAINT user_pkey PRIMARY KEY (id);