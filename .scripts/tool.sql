CREATE TABLE IF NOT EXISTS public.tool
(
    id_tool int NOT NULL,
    quantity int,
    status character varying(50),
    date_of_purchase date,
    catch_date timestamp,  
    maker character varying(100),
    price decimal(10, 2),
    reference character varying(50)
);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.tool
ADD CONSTRAINT tool_pkey PRIMARY KEY (id);
