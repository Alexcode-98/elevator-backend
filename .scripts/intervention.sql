CREATE TABLE IF NOT EXISTS public.intervention
(
	id-intervention int NOT NULL,
        description text,
        isPrivate BOOLEAN
);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.intervention
ADD CONSTRAINT intervention_pkey PRIMARY KEY (id);
