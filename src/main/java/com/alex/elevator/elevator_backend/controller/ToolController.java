package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.entities.Tool;
import com.alex.elevator.elevator_backend.repository.ToolRepository;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import com.alex.elevator.elevator_backend.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/tool")
public class ToolController {
    @Autowired
    ToolRepository toolRepository;
//  Metodo para manejar solicitudes GET en la ruta "status" para obtener herramienta por estado de la herramienta
    @GetMapping("status")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestStatus(@RequestParam boolean status) {
//      Obtiene los registros de Tool asociados al status proporcionado
        List<Tool> tools = toolRepository.findByStatus(status);
//      Convierte la lista de Tool en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainTools(tools);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "checkOut" para obtener herramienta por la fecha de salida
    @GetMapping("checkOut")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestCheckout(@RequestParam LocalDateTime checkoutDate) {
//      Obtiene los registros de Tool asociados al checkOut proporcionado
        List<Tool> tools = toolRepository.findByCheckoutDate(checkoutDate);
//      Convierte la lista de Tool en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainTools(tools);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "maker" para obtener herramienta por el fabricante de la herramienta
     @GetMapping("maker")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestMaker(@RequestParam String maker) {
//      Obtiene los registros de Tool asociados al maker proporcionado
        List<Tool> tools = toolRepository.findByMakerLikeIgnoreCase(maker);
//      Convierte la lista de Tool en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainTools(tools);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "name" para obtener herramienta por el nombre
     @GetMapping("name")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestName(@RequestParam String toolName) {
//      Obtiene los registros de Tool asociados al toolName proporcionado
        List<Tool> tools = toolRepository.findByToolNameLikeIgnoreCase(toolName);
//      Convierte la lista de Tool en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainTools(tools);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "reference" para obtener herramienta por la referencia
     @GetMapping("reference")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestReference(@RequestParam String reference) {
//      Obtiene los registros de Tool asociados a la reference proporcionado
        List<Tool> tools = toolRepository.findByReference(reference);
//      Convierte la lista de Tool en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainTools(tools);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainTools(List<Tool> tools) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar tool de Tool
        for (Tool tool : tools) {
            HashMap<String, Object> toolMap = new HashMap<>();
            toolMap.put("reference", tool.getReference());
            toolMap.put("name", tool.getToolName());
            toolMap.put("quantity", tool.getQuantity());
            toolMap.put("maker", tool.getMaker());
            toolMap.put("price", tool.getPrice());
            toolMap.put("dateOfPurchase", tool.getDateOfPurchase());
            toolMap.put("status", tool.isStatus());
            toolMap.put("checkOutDate", tool.getCheckoutDate());
            toolMap.put("returnDate", tool.getReturnDate());
//          Agregar el mapa de tipo de contrato a la lista principal
            resultToShow.add(toolMap);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }


//  Se declara una variable final de tipo ToolService, que será utilizada para gestionar herramientas.
    private final ToolService toolService;
//  Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia ToolService.
    @Autowired
//  En el constructor, se asigna la instancia de ToolService proporcionada por Spring al atributo de la clase.
    public ToolController(ToolService toolService) {
//      Se define un metodo que maneja solicitudes HTTP POST en la ruta del controlador.
        this.toolService = toolService;
    }
    @PostMapping
//  Se recibe un objeto Tool en el cuerpo de la solicitud (@RequestBody).
    public ResponseEntity<Tool> addTool(@RequestBody Tool tool) {
//      Se llama al metodo saveTool del servicio para guardar la herramienta recibida.
        Tool savedTool = toolService.saveTool(tool);
//      Se devuelve una respuesta HTTP con el código 200 (OK) y el objeto de herramienta guardada.
        return ResponseEntity.ok(savedTool);
    }
}
