CREATE TABLE if NOT EXISTS public.service
(
	id_service bigint NOT NULL,
	startDate timestamp,
	endDate timestamp,
	cost decimal (10 , 2),
	price decimal (10 ,2),
	WorkHour time
)

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.service
ADD CONSTRAINT service_pkey PRIMARY KEY (id);
