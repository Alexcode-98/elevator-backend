CREATE TABLE IF NOT EXISTS public.contract_elevator
(
    id_contract int,
    id_elevator int,
    PRIMARY KEY (id_contract, id_elevator),
    FOREIGN KEY (id_contract) REFERENCES public.contract (id_contract),
    FOREIGN KEY (id_elevator) REFERENCES public.elevator (id_elevator) 
);
