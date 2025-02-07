package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.*;
import com.alex.elevator.elevator_backend.repository.InterventionRepository;
import com.alex.elevator.elevator_backend.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/intervention")
public class InterventionController {
    @Autowired
    InterventionRepository interventionRepository;

//  Metodo para manejar solicitudes GET en la ruta "id" para obtener intervencion por ID de una intervencion
    @GetMapping("id")
    public ResponseEntity<List<HashMap<String, Object>>> getIntervention(@RequestParam Long idIntervention) {
//      Obtiene el registro de Intervention asociados al ID proporcionado
        Optional<Intervention> interventionOpt = interventionRepository.findByIdIntervention(idIntervention);
//      Si no se encuentra ninguna intervención, responde con un código 404 (Not Found)
        if (interventionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
//      Si se encuentra la intervención, la convertimos a una lista (aunque sea un solo elemento)
//      para usarla con el metodo obtainInterventions
        List<HashMap<String, Object>> resultToShow = obtainInterventions(List.of(interventionOpt.get()));
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "private" para obtener intervencion si es privada o no
    @GetMapping("private")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestIntervention(@RequestParam boolean privateIntervention) {
//      Obtiene los registros de ContractType asociados al privateIntervention proporcionado
        List<Intervention> interventions = interventionRepository.findByPrivateIntervention(privateIntervention);
//      Convierte la lista de Intervention en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainInterventions(interventions);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "service" para obtener intervencion por ID de un servicio
    @GetMapping("service")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestServiceIntervention(@RequestParam Long idServiceElevator) {
//      Obtiene los registros de Intervention asociados al serviceElevator proporcionado
        List<Intervention> interventions = interventionRepository.findByServiceElevatorIdServiceElevator(idServiceElevator);
//      Convierte la lista de Intervention en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainInterventions(interventions);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainInterventions(List<Intervention> interventions) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar en intervention de Intervention
        for (Intervention intervention : interventions) {
            HashMap<String, Object> interventionMap = new HashMap<>();
            interventionMap.put("idIntervention", intervention.getIdIntervention());
            interventionMap.put("description", intervention.getDescription());
            interventionMap.put("isPrivate", intervention.isPrivateIntervention());
            interventionMap.put("startDate", intervention.getStartDate());
            interventionMap.put("endDate", intervention.getEndDate());
            interventionMap.put("duration", intervention.getDuration());

            List<HashMap<String, Object>> interventionsList = new ArrayList<>();
//          Obtenemos el objeto ServiceElevator asociado a la intervención
            ServiceElevator serviceElevator = intervention.getServiceElevator();
//          Si existe un ServiceElevator asociado, se crea su representación en un mapa
            if (serviceElevator != null) {

                HashMap<String, Object> serviceMap = new HashMap<>();
                serviceMap.put("idServiceElevator", serviceElevator.getIdServiceElevator());
                serviceMap.put("startDate", serviceElevator.getStartDate());
                serviceMap.put("endDate", serviceElevator.getEndDate());
                serviceMap.put("price", serviceElevator.getPrice());
//              Se añade el mapa del elevador a la lista de intervenciones
                interventionsList.add(serviceMap);
            }
            List<HashMap<String, Object>> sparePartsList = new ArrayList<>();
//          Iteramos sobre la lista de repuestos de la intervención
            for (SparePart sparePart : intervention.getSpareParts()) {
                HashMap<String, Object> sparePartMap = new HashMap<>();
                sparePartMap.put("id", sparePart.getIdSparePart());
                sparePartMap.put("reference", sparePart.getReference());
                sparePartMap.put("type", sparePart.getSparePartType());
                sparePartMap.put("maker", sparePart.getMaker());
                sparePartMap.put("name", sparePart.getSparePartName());
                sparePartMap.put("price", sparePart.getPrice());
//              Añadimos el mapa del repuesto a la lista de repuestos
                sparePartsList.add(sparePartMap);
            }
            List<HashMap<String, Object>> toolsList = new ArrayList<>();
//          Iteramos sobre la lista de herramientas de la intervención
            for (Tool tool : intervention.getTools()) {
                HashMap<String, Object> toolMap = new HashMap<>();
                toolMap.put("id", tool.getIdTool());
                toolMap.put("maker", tool.getMaker());
                toolMap.put("name", tool.getToolName());
//              Añadimos el mapa de la herramienta a la lista de herramientas
                toolsList.add(toolMap);
            }
//          Añadimos las listas al mapa principal de la intervención
            interventionMap.put("interventions", interventionsList);
            interventionMap.put("tools", toolsList);
            interventionMap.put("spareParts", sparePartsList);
//          Agregar el mapa de tipo de contrato a la lista principal
            resultToShow.add(interventionMap);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }


//    Se declara una variable final de tipo InterventionService, que será utilizada para gestionar intervenciones.
    private final InterventionService interventionService;
//    Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia InterventionService.
    @Autowired
//  En el constructor, se asigna la instancia de InterventionService proporcionada por Spring al atributo de la clase.
    public InterventionController(InterventionService interventionService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.interventionService = interventionService;
    }
    @PostMapping
//  Se recibe un objeto Intervention en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<Intervention> addIntervention(@RequestBody Intervention intervention) {
//      Se llama al metodo saveIntervention del servicio para guardar la intervención recibida.
        Intervention savedIntervention = interventionService.saveIntervention(intervention);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de intervención guardado.
        return ResponseEntity.ok(savedIntervention);
    }
}
