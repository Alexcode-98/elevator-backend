package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.entities.Address;
import com.alex.elevator.elevator_backend.entities.Elevator;
import com.alex.elevator.elevator_backend.repository.AddressRepository;
import com.alex.elevator.elevator_backend.service.AddressService;
import com.alex.elevator.elevator_backend.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

//  Metodo para manejar solicitudes GET en la ruta "state" para obtener la dirección por area de un ascensor
    @GetMapping("area")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestState(@RequestParam String area) {
//      Obtiene los registros de Address asociados al area proporcionada
        List<Address> addresses = addressRepository.findByAreaLikeIgnoreCase(area);
//      Convierte la lista de Address en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(addresses);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "building" para obtener address por el nombre de edificio de un ascensor
    @GetMapping("building")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestBuilding(@RequestParam String buildingName) {
//      Obtiene los registros de Address asociados al area proporcionada
        List<Address> addresses = addressRepository.findByBuildingNameLikeIgnoreCase(buildingName);
//      Convierte la lista de Address en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(addresses);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "street" para obtener la direccion por la calle de un ascensor
    @GetMapping("street")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestStreet(@RequestParam String streetAddress) {
//      Obtiene los registros de Address asociados a la calle proporcionada
        List<Address> addresses = addressRepository.findByStreetAddressLikeIgnoreCase(streetAddress);
//      Convierte la lista de Address en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(addresses);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "postcode" para obtener la direccion por codigo postañ de un ascensor
    @GetMapping("postcode")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestPost(@RequestParam String postCode) {
//      Obtiene los registros de Address asociados al codigo postal proporcionado
        List<Address> addresses = addressRepository.findByPostCodeLikeIgnoreCase(postCode);
//      Convierte la lista de Address en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(addresses);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "city" para obtener la direccion por la ciudad de un ascensor
    @GetMapping("city")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestCity(@RequestParam String city) {
//      Obtiene los registros de Address asociados a la ciudad proporcionada
        List<Address> addresses = addressRepository.findByCityLikeIgnoreCase(city);
//      Convierte la lista de Address en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainElevators(addresses);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo que convierte un objeto Address en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainElevators(List<Address> addresses) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();

        for (Address address : addresses) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("streetAddress", address.getStreetAddress());
            map.put("city", address.getCity());
            map.put("postCode", address.getPostCode());
            map.put("area", address.getArea());

            List<HashMap<String, Object>> addressList = new ArrayList<>();

//          Iterar sobre los ascensores de la dirección
            for (Elevator elevator : address.getElevators()) {
                HashMap<String, Object> elevatorMap = new HashMap<>();
                elevatorMap.put("id", elevator.getIdElevator());
                elevatorMap.put("status", elevator.getStatus());
                elevatorMap.put("type", elevator.getElevatorType());
                elevatorMap.put("maker", elevator.getMaker());
//              Agregar el ascensor a la lista de ascensores
                addressList.add(elevatorMap);
            }

//          Añadir la lista de ascensores al mapa principal
            map.put("address", addressList);

//          Agregar el mapa de dirección y ascensores a la lista principal
            resultToShow.add(map);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }

// Se declara una variable final de tipo AddressService, que será utilizada para gestionar direcciones.
    private final AddressService addressService;
// Se utiliza la anotación @Autowired para inyectar automáticamente la dependencia AddressService
    @Autowired
    public AddressController(AddressService addressService) {
//      Se asigna la instancia de AddressService proporcionada por Spring al atributo de la clase.
        this.addressService = addressService;
    }
//  Se define un metodo para manejar solicitudes HTTP POST en la ruta del controlador.
    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
//      Se llama al metodo saveAddress del servicio para guardar la dirección recibida.
        Address savedAddress = addressService.saveAddress(address);
//      Se devuelve la respuesta HTTP con el código 200 (OK) y el objeto de dirección guardado.
        return ResponseEntity.ok(savedAddress);
    }
}
