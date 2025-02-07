CREATE TYPE permission_type AS ENUM ('paymentsCollections', 'hireClient', 'hireEmployees', 'orderSparePart''reserveSparePart', 'planJobs', 'manageJobs', 'technicalWork');

------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.permission
(
	id_permission bigint NOT NULL,
	type permission_type

);

------------------------------------------------------------------------------------------------------------------------------------------------

ALTER TABLE public.permission
ADD CONSTRAINT permission_pkey PRIMARY KEY (id);