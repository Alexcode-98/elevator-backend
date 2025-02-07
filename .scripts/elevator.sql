CREATE TABLE IF NOT EXISTS public.elevator
(
    id bigint NOT NULL,
    location character varying(255),
    maker character varying(255),
    rae character varying(50),
    status character varying(50),
    type character varying(50),
);

ALTER TABLE public.elevator
ADD CONSTRAINT elevator_pkey PRIMARY KEY (id);