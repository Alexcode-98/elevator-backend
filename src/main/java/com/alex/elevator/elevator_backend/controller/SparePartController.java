package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.entities.SparePart;
import com.alex.elevator.elevator_backend.repository.SparePartRepository;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import com.alex.elevator.elevator_backend.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sparePart")
public class SparePartController {
    @Autowired
    SparePartRepository sparePartRepository;
//  Metodo para manejar solicitudes GET en la ruta "name" para obtener repuestos por su nombre
    @GetMapping("name")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestName(@RequestParam String sparePartName) {
//      Obtiene los registros de SparePart asociados al sparePartName proporcionado
        List<SparePart> spareParts = sparePartRepository.findBySparePartNameLikeIgnoreCase(sparePartName);
//      Convierte la lista de SparePart en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainSpareParts(spareParts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "reference" para obtener repuestos por referencia
     @GetMapping("reference")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestReference(@RequestParam String reference) {
//      Obtiene los registros de SparePart asociados a la reference proporcionado
        List<SparePart> spareParts = sparePartRepository.findByReference(reference);
//      Convierte la lista de SparePart en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainSpareParts(spareParts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "type" para obtener repuestos por el tipo de repuesto
     @GetMapping("type")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestType(@RequestParam String sparePartType) {
//      Obtiene los registros de SparePart asociados al sparePartType proporcionado
        List<SparePart> spareParts = sparePartRepository.findBySparePartTypeLikeIgnoreCase(sparePartType);
//      Convierte la lista de SparePart en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainSpareParts(spareParts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "maker" para obtener repuestos por el fabricante
     @GetMapping("maker")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestMaker(@RequestParam String maker) {
//      Obtiene los registros de SparePart asociados al fabricante proporcionado
        List<SparePart> spareParts = sparePartRepository.findByMakerLikeIgnoreCase(maker);
//      Convierte la lista de SparePart en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainSpareParts(spareParts);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }
//  Metodo que convierte un objeto sparePart en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainSpareParts(List<SparePart> spareParts) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar sobre los sparePart del tipo sparePart
        for (SparePart sparePart : spareParts) {
            HashMap<String, Object> sparePartMap = new HashMap<>();
            sparePartMap.put("reference", sparePart.getReference());
            sparePartMap.put("name", sparePart.getSparePartName());
            sparePartMap.put("type", sparePart.getSparePartType());
            sparePartMap.put("maker", sparePart.getMaker());
            sparePartMap.put("unitsAvailable", sparePart.getUnitsAvailable());
            sparePartMap.put("unitsReserved", sparePart.getUnitsReserved());
            sparePartMap.put("status", sparePart.getStatus());
            sparePartMap.put("orderStatus", sparePart.getOrderStatus());

//          Agregar el mapa de sparePart a la lista principal
            resultToShow.add(sparePartMap);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }

//   Se declara una variable final de tipo SparePartService, que será utilizada para gestionar repuestos.
    private final SparePartService sparePartService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia SparePartService.
    @Autowired
//  En el constructor, se asigna la instancia de SparePartService proporcionada por Spring al atributo de la clase.
    public SparePartController(SparePartService sparePartService) {
//  Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.sparePartService = sparePartService;
    }
    @PostMapping
//  Se recibe un objeto SparePart en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<SparePart> addSparePart(@RequestBody SparePart sparePart) {
//      Se llama al metodo saveSparePart del servicio para guardar el repuesto recibido.
        SparePart savedSparePart = sparePartService.saveSparePart(sparePart);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de repuesto guardado.
        return ResponseEntity.ok(savedSparePart);
    }
}
