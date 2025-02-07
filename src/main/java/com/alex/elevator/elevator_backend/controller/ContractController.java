package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Contract;
import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.repository.ContractRepository;
import com.alex.elevator.elevator_backend.service.ContractService;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    ContractRepository contractRepository;
//  Metodo para manejar solicitudes GET en la ruta "contract" para obtener contract por ID de un contrato
    @GetMapping("contract")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestContract(@RequestParam Long idContract) {
//      Obtiene el registro de Contract asociados al ID proporcionado
        Optional<Contract> contractOpt = contractRepository.findByIdContract(idContract);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un Contract asociado al ID):
        contractOpt.ifPresent(contract -> {
//          Llama al metodo obtainContract, que devuelve un mapa con los datos del Contract,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainContract(contract));
        });
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "user" para obtener contract por user de un contrato
    @GetMapping("user")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestUser(@RequestParam Long idUser) {
//      Obtiene los registros de Contract asociados al user proporcionado
        List<Contract> contracts = contractRepository.findByUserElevatorIdUser(idUser);
//      Convierte la lista de Contract en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainContracts(contracts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "elevator" para obtener contract por el rae de un ascensor
    @GetMapping("elevator")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestElevator(@RequestParam int rae) {
//      Obtiene el registro de Contract asociado al rae proporcionado
        Optional<Contract> contractOpt = contractRepository.findByElevatorsRae(rae);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un Contract asociado al rae):
        contractOpt.ifPresent(contract-> {
//          Llama al metodo obtainContract, que devuelve un mapa con los datos del Contract,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainContract(contract));
        });
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }
//  Metodo para manejar solicitudes GET en la ruta "permanency" para obtener contract por la permanency de un contrato
    @GetMapping("permanency")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestPermanency(@RequestParam boolean permanency) {
//      Obtiene los registros de Contract asociados a la permanency proporcionado
        List<Contract> contracts = contractRepository.findByPermanency(permanency);
//      Convierte la lista de Contract en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainContracts(contracts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

    //  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static HashMap<String, Object> obtainContract(Contract contract) {
//      Creamos un map para almacenar los resultados
        HashMap<String, Object> resultToShow =  new HashMap<>();
        resultToShow.put("contractId", contract.getIdContract());
        resultToShow.put("startDate", contract.getStartDate());
        resultToShow.put("permanency", contract.isPermanency());
        resultToShow.put("idUser", contract.getUserElevator().getIdUser());
        resultToShow.put("userFullName", contract.getUserElevator().getFullName());
        resultToShow.put("userAddress", contract.getUserElevator().getAddress());
        resultToShow.put("elevators", contract.getElevators().size());
//      Devuelve el mapa de datos estructurados
        return resultToShow;
    }

    private static List<HashMap<String, Object>> obtainContracts(List<Contract> contracts) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();

        for (Contract contract : contracts) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("contractId", contract.getIdContract());
            map.put("startDate", contract.getStartDate());
            map.put("permanency", contract.isPermanency());
            map.put("idUser", contract.getUserElevator().getIdUser());
            map.put("quantityElevators", contract.getElevators().size());
            map.put("price", contract.getContractType().getPrice());
            map.put("type", contract.getContractType().getContractType());

            List<HashMap<String, Object>> contractsList = new ArrayList<>();
//          Iterar sobre el contract de los elevators
            for (Elevator elevator : contract.getElevators()) {
                HashMap<String, Object> elevatorMap = new HashMap<>();
                elevatorMap.put("id", elevator.getIdElevator());
                elevatorMap.put("status", elevator.getStatus());
                elevatorMap.put("type", elevator.getElevatorType());
                elevatorMap.put("maker", elevator.getMaker());
//              Agregar el ascensor a la lista de ascensores
                contractsList.add(elevatorMap);
            }

//          Añadir la lista de ascensores al mapa principal
            map.put("elevators", contractsList);

//          Agregar el mapa de dirección y ascensores a la lista principal
            resultToShow.add(map);
        }
//      Devuelve el mapa de datos estructurados
        return resultToShow;
    }

//  Se declara una variable final de tipo ContractService, que será utilizada para gestionar contratos.
    private final ContractService contractService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia ContractService.
    @Autowired
//  En el constructor, se asigna la instancia de ContractService proporcionada por Spring al atributo de la clase.
    public ContractController(ContractService contractService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.contractService = contractService;
    }

    @PostMapping
//  Se recibe un objeto Contract en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {

//      Se llama al metodo saveContract del servicio para guardar el contrato recibido.
        Contract savedContract = contractService.saveContract(contract);

//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de Contract guardado.
        return ResponseEntity.ok(savedContract);
    }




}
