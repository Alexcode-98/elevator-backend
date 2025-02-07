package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.*;
import com.alex.elevator.elevator_backend.repository.*;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    @Autowired
    ElevatorRepository elevatorRepository;

//  Metodo para manejar solicitudes GET en la ruta "status" para obtener ascensores por el estado del ascensor
    @GetMapping("status")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestStatus(@RequestParam String status) {
//      Obtiene los registros de Elevator asociados al status proporcionado
        List<Elevator> result = elevatorRepository.findByStatusIgnoreCase(status);
//      Convierte la lista de Elevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(result);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "maker" para obtener ascensores por el fabricante
    @GetMapping("maker")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestMaker(@RequestParam String maker) {
//      Obtiene los registros de Elevator asociados al maker proporcionado
        List<Elevator> result = elevatorRepository.findByMakerIgnoreCase(maker);
//      Convierte la lista de Elevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(result);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "type" para obtener ascensores por tipo de ascensor
    @GetMapping("type")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestElevatorType(@RequestParam String elevatorType) {
//      Obtiene los registros de Elevator asociados al elevatorType proporcionado
        List<Elevator> result = elevatorRepository.findByElevatorTypeIgnoreCase(elevatorType);
//      Convierte la lista de Elevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(result);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "rae" para obtener un ascensor por el rae
    @GetMapping("rae")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestRae(@RequestParam int rae) {
//      Obtiene los registros de Elevator asociados al maker proporcionado
        Optional<Elevator> elevatorOpt = elevatorRepository.findByRae(rae);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un elevator asociado al rae):
        elevatorOpt.ifPresent(elevator -> {
//          Llama al metodo obtainElevator, que devuelve un mapa con los datos del elevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainElevator(elevator));
        });
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "contract" para obtener ascensores por su contrato
    @GetMapping("/contract")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestContract(@RequestParam Long idContract) {
//      Obtiene los registros de Elevator asociados al idContract proporcionado
        List<Elevator> result = elevatorRepository.findByContractIdContract(idContract);
//      Convierte la lista de Elevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(result);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);

    }
//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainElevators(List<Elevator> result) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar sobre los elevators
        for (Elevator elevator : result) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("rae", elevator.getRae());
            map.put("type", elevator.getElevatorType());
            map.put("status", elevator.getStatus());
            map.put("stopNumber", elevator.getStopNumbers());
            map.put("maker", elevator.getMaker());
//          Iterar sobre la address de los elevators
            if (elevator.getAddress() != null) {
                Address address = elevator.getAddress();
                map.put("streetAddress", address.getStreetAddress());
                map.put("buildingName", address.getBuildingName());
                map.put("city", address.getCity());
                map.put("postalCode", address.getPostCode());
                map.put("state", address.getArea());

            }
//          Iterar sobre el contrato de los elevators
            if (elevator.getContract() != null) {
                Contract contract = elevator.getContract();
                map.put("idContract", contract.getIdContract());
                map.put("startDate", contract.getStartDate());
                map.put("permanency", contract.isPermanency());

            }
//          Agregar el elevator a la lista principal
            resultToShow.add(map);
        }
        return resultToShow;

    }
//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static HashMap<String, Object> obtainElevator(Elevator elevator) {
//      Creamos un map para almacenar los resultados
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Iterar sobre los elevator
        resultToShow.put("rae", elevator.getRae());
        resultToShow.put("type", elevator.getElevatorType());
        resultToShow.put("status", elevator.getStatus());
        resultToShow.put("stopNumber", elevator.getStopNumbers());
        resultToShow.put("maker", elevator.getMaker());
//          Iterar sobre la address de elevator
            if (elevator.getAddress() != null) {
                Address address = elevator.getAddress();
                resultToShow.put("streetAddress", address.getStreetAddress());
                resultToShow.put("buildingName", address.getBuildingName());
                resultToShow.put("city", address.getCity());
                resultToShow.put("postalCode", address.getPostCode());
                resultToShow.put("state", address.getArea());

            }
//          Iterar sobre el contrato de elevator
            if (elevator.getContract() != null) {
                Contract contract = elevator.getContract();
                resultToShow.put("idContract", contract.getIdContract());
                resultToShow.put("startDate", contract.getStartDate());
                resultToShow.put("permanency", contract.isPermanency());

            }
//      Devuelve el mapa de datos estructurados
        return resultToShow;

    }







//  Se declara una variable final de tipo ElevatorService, que será utilizada para gestionar ascensores.
    private final ElevatorService elevatorService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia ElevatorService.
    @Autowired
//  En el constructor, se asigna la instancia de ElevatorService proporcionada por Spring al atributo de la clase.
    public ElevatorController(ElevatorService elevatorService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.elevatorService = elevatorService;
    }
    @PostMapping
//  Se recibe un objeto Elevator en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<Elevator> addElevator(@RequestBody Elevator elevator) {
//      Se llama al metodo saveElevator del servicio para guardar el ascensor recibido.
        Elevator savedElevator = elevatorService.saveElevator(elevator);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de ascensor guardado.
        return ResponseEntity.ok(savedElevator);
    }


}
