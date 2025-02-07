package com.alex.elevator.elevator_backend.controller;


import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.entities.ServiceElevator;
import com.alex.elevator.elevator_backend.repository.ServiceElevatorRepository;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import com.alex.elevator.elevator_backend.service.ServiceElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serviceElevator")
public class ServiceElevatorController {

    @Autowired
    ServiceElevatorRepository  serviceElevatorRepository;

//  Metodo para manejar solicitudes GET en la ruta "service" para obtener servicios por ID de un servicio
    @GetMapping("service")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestService(@RequestParam Long idServiceElevator) {
//      Ejemplo de otro modo de hacerlo
//        Optional<Contract> contractOpt = contractRepository.findByIdContract(idContract);
//        HashMap<String, Object> resultToShow = new HashMap<>();
//        contractOpt.ifPresent(contract -> {
//            resultToShow.putAll(obtainContract(contract));
//        });
//        return ResponseEntity.ok(resultToShow);

//      Obtiene el registro de ServiceElevator asociados al ID proporcionado
        Optional<ServiceElevator> serviceElevatorOpt = serviceElevatorRepository.findByIdServiceElevator(idServiceElevator);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un serviceElevator asociado al ID):
        serviceElevatorOpt.ifPresent(serviceElevator -> {
//          Llama al metodo obtainContractType, que devuelve un mapa con los datos del serviceElevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainServiceElevator(serviceElevator));
        });
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }
//  Metodo para manejar solicitudes GET en la ruta "elevator" para obtener servicios por rae de un ascensor
    @GetMapping("elevator")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestServiceServiceElevator(@RequestParam int rae) {
//      Obtiene los registros de ServiceElevator asociados al rae proporcionado
        List<ServiceElevator> elevators = serviceElevatorRepository.findByElevatorRae(rae);
//      Convierte la lista de ServiceElevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainServiceElevators(elevators);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);

    }

//  Metodo para manejar solicitudes GET en la ruta "contract" para obtener servicios por ID de un contrato
    @GetMapping("contract")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestServiceServiceContract(@RequestParam Long idContract) {
//      Obtiene los registros de ServiceElevator asociados al idContract proporcionado
        List<ServiceElevator> contracts = serviceElevatorRepository.findByContractIdContract(idContract);
//      Convierte la lista de ServiceElevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainServiceElevators(contracts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo que convierte un objeto ServiceElevator en un mapa de datos clave-valor
    private static HashMap<String, Object> obtainServiceElevator(ServiceElevator serviceElevator) {
//      creamos mapa para almacenar los resultados
        HashMap<String, Object> resultToShow =  new HashMap<>();
        resultToShow.put("contractId", serviceElevator.getContract().getIdContract());
        resultToShow.put("startDate", serviceElevator.getStartDate());
        resultToShow.put("interventions", serviceElevator.getInterventions().size());
        resultToShow.put("endDate", serviceElevator.getEndDate());
        resultToShow.put("price", serviceElevator.getPrice());
//      Devuelve el mapa de datos estructurados
        return resultToShow;
    }

    //  Metodo que convierte un objeto ServiceElevator en un mapa de datos clave-valor
    private List<HashMap<String, Object>> obtainServiceElevators(List<ServiceElevator> serviceElevators) {
//      Creamos un map para almacenar los resultad
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterer sobre los serviceElevator de ServiceElevator
        for (ServiceElevator serviceElevator : serviceElevators) {
            HashMap<String, Object> serviceElevatorMap = new HashMap<>();
            serviceElevatorMap.put("idServiceElevator", serviceElevator.getIdServiceElevator());
            serviceElevatorMap.put("startDate", serviceElevator.getStartDate());
            serviceElevatorMap.put("endDate", serviceElevator.getEndDate());
            serviceElevatorMap.put("price", serviceElevator.getPrice());
            serviceElevatorMap.put("elevatorRae", serviceElevator.getElevator().getRae());
            serviceElevatorMap.put("idContract", serviceElevator.getContract().getIdContract());
            serviceElevatorMap.put("idIntervention", serviceElevator.getInterventions().size());

//          Añadir la lista de servicios al mapa principal
            resultToShow.add(serviceElevatorMap);
        }
//      Devuelve el mapa de datos estructurados
        return resultToShow;
    }


//  Se declara una variable final de tipo ServiceElevatorService, que será utilizada para gestionar servicios.
    private final ServiceElevatorService serviceElevatorService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia AddressService.
    @Autowired
//  En el constructor, se asigna la instancia de ServiceElevatorService proporcionada por Spring al atributo de la clase.
    public ServiceElevatorController(ServiceElevatorService serviceElevatorService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.serviceElevatorService = serviceElevatorService;
    }
    @PostMapping
//  Se recibe un objeto serviceElevator en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<ServiceElevator> addServiceElevator(@RequestBody ServiceElevator serviceElevator) {
//      Se llama al metodo saveServiceElevator del servicio para guardar el servicio recibido.
        ServiceElevator savedServiceElevator = serviceElevatorService.saveServiceElevator(serviceElevator);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de servicio guardado.
        return ResponseEntity.ok(savedServiceElevator);



    }

}
