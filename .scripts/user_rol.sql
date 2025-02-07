CREATE TABLE IF NOT EXISTS public.user_rol
(
    id_user int,
    id_rol int,
    PRIMARY KEY (id_user, id_rol),
    FOREIGN KEY (id_user) REFERENCES public.user (id_user), 
    FOREIGN KEY (id_rol) REFERENCES public.rol (id_rol)   
);
