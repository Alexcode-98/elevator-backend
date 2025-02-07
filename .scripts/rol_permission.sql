CREATE TABLE IF NOT EXISTS public.rol_permission
(
    id_rol int,
    id_permission int,
    PRIMARY KEY (id_rol, id_permission),
    FOREIGN KEY (id_rol) REFERENCES public.rol (id_rol),        
    FOREIGN KEY (id_permission) REFERENCES public.permission (id_permission)  
);