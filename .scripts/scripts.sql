SCRIPTS POSTGRE
------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.elevator
(
	id_elevator bigint NOT NULL PRIMARY KEY,
	location character varying(255),
	maker character varying(255),
	rae character varying(50),
	status character varying(50)",
	type character varying(50),
    
)


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.user
(
	id_user bigint NOT NULL PRIMARY KEY,
	username character varying(100),
	password character varying(255),
	email character varying(100),
	name character varying(100),
	address character varying(255),
	telephone character varying(20),
	registration DATE	

  );


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TYPE role_type AS ENUM ('SuperAdmin', 'Admin', 'Technical', 'Client');

------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.rol
(
	id_rol bigint NOT NULL PRIMARY KEY,
	type role_type
);



------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TYPE permission_type AS ENUM ('paymentsCollections', 'hireClient', 'hireEmployees', 'orderSparePart''reserveSparePart', 'planJobs', 'manageJobs', 'technicalWork');

------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.permission
(
	id_permission bigint NOT NULL PRIMARY KEY,
	type permission_type

);


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.sparePart
(
	id_sparePart bigint NOT NULL PRIMARY KEY,
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

CREATE TABLE if NOT EXISTS public.service
(
	id_service bigint NOT NULL PRIMARY KEY,
	startDate timestamp,
	endDate timestamp,
	cost decimal (10 , 2),
	price decimal (10 ,2),
	workHour time
)


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.intervention
(
	id_intervention int NOT NULL PRIMARY KEY,
        description text,
        isPrivate BOOLEAN
);

------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.contract
(
    id_contract int NOT NULL PRIMARY KEY,
    startDate date,
    permanency boolean
);


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.tool
(
    id_tool int NOT NULL PRIMARY KEY,
    quantity int,
    status character varying(50),
    date_of_purchase date,
    catch_date timestamp,  
    maker character varying(100),
    price decimal(10, 2),
    reference character varying(50)
);


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.contractType
(
    id_contracType int NOT NULL PRIMARY KEY,
    Contract_type character varying(50),
    price decimal(10, 2),
    quantityElevators int,
    stopNumbers int
);


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.contract_elevator
(
    id_contract int,
    id_elevator int,
    PRIMARY KEY (id_contract, id_elevator),
    FOREIGN KEY (id_contract) REFERENCES public.contract (id_contract),
    FOREIGN KEY (id_elevator) REFERENCES public.elevator (id_elevator) 
);


------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.user_rol
(
    id_user int,
    id_rol int,
    PRIMARY KEY (id_user, id_rol),
    FOREIGN KEY (id_user) REFERENCES public.user (id_user), 
    FOREIGN KEY (id_rol) REFERENCES public.rol (id_rol)   
);


------------------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE IF NOT EXISTS public.rol_permission
(
    id_rol int,
    id_permission int,
    PRIMARY KEY (id_rol, id_permission),
    FOREIGN KEY (id_rol) REFERENCES public.rol (id_rol),        
    FOREIGN KEY (id_permission) REFERENCES public.permission (id_permission)  
);

