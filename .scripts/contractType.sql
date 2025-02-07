
CREATE TABLE IF NOT EXISTS public.contractType
(
    id_contracType int NOT NULL,
    Contract_type character varying(50),
    price decimal(10, 2),
    quantityElevators int,
    stopNumbers int
);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.contractType
ADD CONSTRAINT contractType_pkey PRIMARY KEY (id);