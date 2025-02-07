package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.*;
import com.alex.elevator.elevator_backend.repository.ContractTypeRepository;
import com.alex.elevator.elevator_backend.service.ContractTypeService;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contractType")
public class ContractTypeController {
    @Autowired
    ContractTypeRepository contractTypeRepository;
//  Metodo para manejar solicitudes GET en la ruta "type" para obtener contratos por el tipo de un contrato
    @GetMapping("type")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestType(@RequestParam String contractType) {
//      Obtiene los registros de ContractType asociados al contractType proporcionado
        List<ContractType> contractTypes = contractTypeRepository.findByContractTypeLikeIgnoreCase(contractType);
//      Convierte la lista de ServiceElevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainContractTypes(contractTypes);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }
//  Metodo para manejar solicitudes GET en la ruta "contract" para obtener tipo de contrato por ID de un contrato
    @GetMapping("contract")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestContract(@RequestParam Long idContract) {
//      Obtiene el registro de ContractType asociados al ID proporcionado
        Optional<ContractType> contractTypeOpt = contractTypeRepository.findByContractsIdContract(idContract);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un ContractType asociado al contrato):
        contractTypeOpt.ifPresent(contractType -> {
//          Llama al metodo obtainContractType, que devuelve un mapa con los datos del ContractType,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainContractType(contractType));
        });
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }
//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static HashMap<String, Object> obtainContractType(ContractType contractType) {
//      Creamos un map para almacenar los resultados
        HashMap<String, Object> resultToShow =  new HashMap<>();
        resultToShow.put("type", contractType.getContractType());
        resultToShow.put("price", contractType.getPrice());

        List<HashMap<String, Object>> contractTypeList = new ArrayList<>();
//      Iterar sobre los contratos del tipo contrato
        for (Contract contract : contractType.getContracts()) {
            HashMap<String, Object> contractMap = new HashMap<>();
            contractMap.put("id", contract.getIdContract());
            contractMap.put("services", contract.getServiceElevators().size());
            contractMap.put("quantityElevator", contract.getElevators().size());
            contractMap.put("starDate", contract.getStartDate());
            contractMap.put("user", contract.getUserElevator().getUsername());

            List<HashMap<String, Object>> elevatorList = new ArrayList<>();
//          Iterar sobre los ascensores del tipo contrato
            for (Elevator elevator : contract.getElevators()) {
                HashMap<String, Object> elevatorMap = new HashMap<>();
                elevatorMap.put("rae", elevator.getRae());
                elevatorMap.put("maker", elevator.getMaker());
                elevatorMap.put("elevatorType", elevator.getElevatorType());
                elevatorMap.put("stopNumbers", elevator.getStopNumbers());
                elevatorList.add(elevatorMap);
            }

//          Añadir la lista de ascensores al contrato
            contractMap.put("elevators", elevatorList);
            contractTypeList.add(contractMap);
        }

//      Añadir la lista de contratos al mapa principal
        resultToShow.put("contracts", contractTypeList);
//      Devuelve el mapa de datos estructurados
        return resultToShow;
    }
//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainContractTypes(List<ContractType> contractTypes) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar contractType de ContractType
        for (ContractType contractType : contractTypes) {
            HashMap<String, Object> contractTypeMap = new HashMap<>();
            contractTypeMap.put("price", contractType.getPrice());

            List<HashMap<String, Object>> contractList = new ArrayList<>();
//          Iterar sobre los contratos del tipo contrato
            for (Contract contract : contractType.getContracts()) {
                HashMap<String, Object> contractMap = new HashMap<>();
                contractMap.put("id", contract.getIdContract());
                contractMap.put("services", contract.getServiceElevators().size());
                contractMap.put("quantityElevator", contract.getElevators().size());
                contractMap.put("starDate", contract.getStartDate());
                contractMap.put("user", contract.getUserElevator().getUsername());

                List<HashMap<String, Object>> elevatorList = new ArrayList<>();
//              Iterar sobre los ascensores del contrato
                for (Elevator elevator : contract.getElevators()) {
                    HashMap<String, Object> elevatorMap = new HashMap<>();
                    elevatorMap.put("rae", elevator.getRae());
                    elevatorMap.put("maker", elevator.getMaker());
                    elevatorMap.put("elevatorType", elevator.getElevatorType());
                    elevatorMap.put("stopNumbers", elevator.getStopNumbers());
                    elevatorList.add(elevatorMap);
                }

//              Añadir la lista de ascensores al contrato
                contractMap.put("elevators", elevatorList);
//              Agregar el contrato a la lista de contratos
                contractList.add(contractMap);
            }

//          Añadir la lista de contratos al mapa principal
            contractTypeMap.put("contracts", contractList);

//          Agregar el mapa de tipo de contrato a la lista principal
            resultToShow.add(contractTypeMap);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }

//  Se declara una variable final de tipo ContractTypeService, que será utilizada para gestionar tipos de contrato.
    private final ContractTypeService contractTypeService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia ContractTypeService.
    @Autowired
//  En el constructor, se asigna la instancia de ContractTypeService proporcionada por Spring al atributo de la clase.
    public ContractTypeController(ContractTypeService contractTypeService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.contractTypeService = contractTypeService;
    }
    @PostMapping
//  Se recibe un objeto ContractType en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<ContractType> addContractType(@RequestBody ContractType contractType) {
//      Se llama al metodo saveContractType del servicio para guardar el tipo contrato recibido.
        ContractType savedContractType = contractTypeService.saveContractType(contractType);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de ContractType guardado.
        return ResponseEntity.ok(savedContractType);
    }
}
