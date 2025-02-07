CREATE TABLE IF NOT EXISTS public.contract
(
    id_contract int NOT NULL,
    startDate date,
    permanency boolean
);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.contract
ADD CONSTRAINT intervention_pkey PRIMARY KEY (id);
