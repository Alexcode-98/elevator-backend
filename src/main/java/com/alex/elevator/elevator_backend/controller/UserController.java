package com.alex.elevator.elevator_backend.controller;

import com.alex.elevator.elevator_backend.dtos.ClientRegistered;
import com.alex.elevator.elevator_backend.dtos.UserDto;
import com.alex.elevator.elevator_backend.entities.Role;
import com.alex.elevator.elevator_backend.entities.UserElevator;
import com.alex.elevator.elevator_backend.repository.UserElevatorRepository;
import com.alex.elevator.elevator_backend.service.UserElevatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserElevatorRepository userElevatorRepository;

//  Metodo para manejar solicitudes GET en la ruta "id" para obtener usuarios por su ID
    @GetMapping("id")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestUser(@RequestParam Long idUser) {
//      Obtiene el registro de UserElevator asociados al ID proporcionado
        Optional<UserElevator> userOpt = userElevatorRepository.findByIdUser(idUser);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un UserElevator asociado al ID):
        userOpt.ifPresent(userElevator -> {
//          Llama al metodo obtainUser, que devuelve un mapa con los datos del UserElevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainUser(userElevator));
        });

//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "username" para obtener usuarios por su nombre de usuario
    @GetMapping("username")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestUserName(@RequestParam String username) {
//      Obtiene el registro de UserElevator asociados al username proporcionado
        Optional<UserElevator> userOpt = userElevatorRepository.findByUsername(username);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un UserElevator asociado al username):
        userOpt.ifPresent(userElevator -> {
//          Llama al metodo obtainUser, que devuelve un mapa con los datos del UserElevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainUser(userElevator));
        });

//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "fullname" para obtener usuarios por su nombre completo
    @GetMapping("fullname")
    public ResponseEntity<List<HashMap<String, Object>>> processHttpGetRequestFullName(@RequestParam String fullName) {
//      Obtiene los registros de UserElevator asociados al fullName proporcionado
        List<UserElevator> users = userElevatorRepository.findByFullNameLikeIgnoreCase(fullName);
//      Convierte la lista de UserElevator en una lista de HashMaps con los datos estructurados
        List<HashMap<String, Object>> resultToShow = obtainUsers(users);
//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "email" para obtener usuarios por el email
    @GetMapping("email")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestEmail(@RequestParam String email) {
//      Obtiene el registro de UserElevator asociados al email proporcionado
        Optional<UserElevator> userOpt = userElevatorRepository.findByEmail(email);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un UserElevator asociado al email):
        userOpt.ifPresent(userElevator -> {
//          Llama al metodo obtainUser, que devuelve un mapa con los datos del UserElevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainUser(userElevator));
        });

//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo para manejar solicitudes GET en la ruta "phone" para obtener usuarios por el teléfono
    @GetMapping("phone")
    public ResponseEntity<HashMap<String, Object>> processHttpGetRequestPhone(@RequestParam String phone) {
//      Obtiene el registro de ContractType asociados al phone proporcionado
        Optional<UserElevator> userOpt = userElevatorRepository.findByPhone(phone);
//      Crea un mapa vacío para almacenar los datos procesados que se incluirán en la respuesta
        HashMap<String, Object> resultToShow = new HashMap<>();
//      Si el Optional contiene un objeto (es decir, si se encuentra un UserElevator asociado al phone):
        userOpt.ifPresent(userElevator -> {
//          Llama al metodo obtainUser, que devuelve un mapa con los datos del UserElevator,
//          y los agrega al mapa resultToShow
            resultToShow.putAll(obtainUser(userElevator));
        });

//      Devuelve el mapa resultToShow como respuesta HTTP con un estado "200 OK" en formato JSON
        return ResponseEntity.ok(resultToShow);
    }

//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static List<HashMap<String, Object>> obtainUsers(List<UserElevator> users) {
//      Creamos un map para almacenar los resultados
        List<HashMap<String, Object>> resultToShow = new ArrayList<>();
//      Iterar sobre los usuarios del UserElevator
        for (UserElevator user : users) {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("idUser", user.getIdUser());
            userMap.put("userName", user.getUsername());
            userMap.put("email", user.getEmail());
            userMap.put("fullName", user.getFullName());
            userMap.put("address", user.getAddress());
            userMap.put("phone", user.getPhone());

            List<HashMap<String, Object>> rolesList = new ArrayList<>();

//          Iterar sobre la lista de roles
            for (Role role : user.getRoles()) {
                HashMap<String, Object> roleMap = new HashMap<>();
                roleMap.put("roleType", role.getRoleType());

                rolesList.add(roleMap);
            }

//          Añadir la lista de roles al mapa principal
            userMap.put("roles", rolesList);
//          Agregar el mapa de tipo de contrato a la lista principal
            resultToShow.add(userMap);
        }
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }

//  Metodo que convierte un objeto ContractType en un mapa de datos clave-valor
    private static HashMap<String, Object> obtainUser(UserElevator userElevator) {
//      Creamos un map para almacenar los resultados
        HashMap<String, Object> resultToShow = new HashMap<>();


            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("idUser", userElevator.getIdUser());
            userMap.put("userName", userElevator.getUsername());
            userMap.put("email", userElevator.getEmail());
            userMap.put("fullName", userElevator.getFullName());
            userMap.put("address", userElevator.getAddress());
            userMap.put("phone", userElevator.getPhone());

            List<HashMap<String, Object>> rolesList = new ArrayList<>();

//          Iterar sobre la lista de roles
            for (Role role : userElevator.getRoles()) {
                HashMap<String, Object> roleMap = new HashMap<>();
                roleMap.put("roleType", role.getRoleType());

                rolesList.add(roleMap);
            }


//          Añadir la lista de roles al mapa principal
            userMap.put("roles", rolesList);
//          Añadir la lista de contratos al mapa principal
            resultToShow.put("user",userMap);
//      Devuelve la lista de mapas estructurados
        return resultToShow;
    }

//  Declaración de un atributo privado y final para inyectar el servicio "UserElevatorService"
    private final UserElevatorService userElevatorService;
//  Constructor de la clase "UserController" donde se inyecta la dependencia "UserElevatorService"
//  La anotación @Autowired permite que Spring automáticamente inyecte el servicio en el controlador.
    @Autowired
    public UserController(UserElevatorService userElevatorService) {
        this.userElevatorService = userElevatorService;
    }
//  Metodo que maneja solicitudes POST en la ruta "/registerClient"
    @PostMapping("/registerClient")
    public ResponseEntity<UserElevator> addUserElevator(@RequestBody ClientRegistered userElevator) {
//      Se crea un objeto "UserDto" para la transferencia de datos
        UserDto dto = new UserDto();
//      Se copian las propiedades de "userElevator" (objeto recibido en la solicitud)
//      al objeto "dto" usando la utilidad "BeanUtils" de Spring
        BeanUtils.copyProperties(userElevator, dto);
//      Se guarda el usuario en el servicio correspondiente
        userElevatorService.saveUserElevator(dto);
//      Se devuelve una respuesta HTTP con código 200 (OK) y un nuevo objeto "UserElevator"
        return ResponseEntity.ok(new UserElevator());
    }
}
