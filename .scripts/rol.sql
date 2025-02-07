
CREATE TYPE role_type AS ENUM ('SuperAdmin', 'Admin', 'Technical', 'Client');

------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS public.rol
(
    id_rol bigint NOT NULL PRIMARY KEY,  |-- Definir la clave primaria en la creación
    type role_type                       |-- Usar el tipo ENUM creado
);