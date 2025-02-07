

# ElevatorBackend

This is a project for managing elevators and services related to them.

## Instrucciones de desarrollo

Lo primero al crear un proyecto es crear el README, podría seguir del src, main y java y resource, el pom.xml
levantar el docker y el postgresql... levantar el postgre y crear la base de datos, creando la tabla con scripts


Añadir la explicación del proyecto, sus funciones y utilidades.

En el README añado las instrucciones necesarias para levantar el proyecto correctamente para otros programadores.

## Apuntes

En el pom.xml se añaden las dependencias, las versiones que utilizan, y es usado en proyectos que usan maven
En el Java va código, en el resource va los recursos
Para spring hay que crear fichero de propiedades por ejemplo .yml 

Dentro de Java no es buena practica llamar a los atributos/propiedades con _, es recomendable usar
camelCase y mediante @Column(name = "nombre_BD") hacerle referencia 

Una vez creado hay que comprobar que Spring está funcionando y crear las entidades, la principal de todas (en este caso
elevator) para comprobar el correcto funcionamiento y conexión a BD

Ya comprobado la correcta conexión entre BD y Spring, he creado @GetMapping para listar mediantes
métodos los ascensores en base de datos, haciendo llamadas por varias propiedades diferentes y usando
DTOs y en proceso de manejar JSON 

En la relaciones 1:n se tiene que añadir la anotación @OneToMany en la entidad principal y @ManyToOne en la anotación
secundaria. También se tiene que agregar en caso de querer sacarlos datos en JSON la anotación
@JsonManagedReference en la principal y @JsonBackReference en la secundaria, esto se añade para evitar que entren en
referencias infinitas entre ellas, ya que 'A' llama a 'B' y 'B' llama 'A'

Para la relación 1:1 y n:1 la carga predeterminada es Eager lo que puede traer problemas al cargar un la entidad, 
porque traería consigo todos los datos de golpe y cosas de gran escala peta



### Spring 

Para crear un proyecto básico con Spring:

Se añaden las anotaciones de Spring, sus dependencias al pom.xml y se importan sus clases en el main
para su correcto funcionamiento

Por ejemplo:
```
@SpringBootApplication
```
Que necesita sus dependencias en el pom.xml que te las añade automáticamente el IDE (Intellij IDEA)
y también sus clases (también las añade el IDE) dentro del main en este caso las imprescindibles

son:

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

Para inyectar los repositorios es necesario usar la anotación por cada inyección 
```
@Autowired
```
Para manejar distintos accesos a datos es necesario crear un DTO para cada uno, por ejemplo desde la entidad elevator
podemos acceder a entidades relacionadas, pero en casa de hacerlo en el otro sentido, sera necesario crear otra DTO,
de la misma manera, si quieres restringir datos a ciertos accesos también requiere de un DTO propio.

Siempre serà recomendable y buena práctica tener manejo de errores, para dar una mejor experiencia al usuario y para 
poder facilitar la legibilidad del error 

Para hacer que un atributo sea auto-incremental en la BD podemos añadir en la entidad la anotación:
```
@GeneratedValue(strategy = GenerationType.IDENTITY)
```
Para crear una relación 1:n :
```
 @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
 @JsonManagedReference
 private List<Elevator> elevators;
    
```
@OneToMany, indica que hay una relación de uno a muchos entre dos entidades. En este caso, una única instancia de 
Address puede estar relacionada con múltiples instancias de Elevator.
Es decir, una dirección puede tener muchos ascensores asociados.

mappedBy = "address", este atributo se utiliza para especificar el campo en la entidad inversa que está mapeando esta
relación. En este caso, "address" hace referencia a un campo en la clase Elevator. Esto significa que la clase Elevator 
tiene un atributo que es una referencia a un objeto Address.

Por lo tanto, mappedBy establece que Address no es el dueño de la relación, sino que Elevator lo es. Esto es importante
para la gestión de la relación en términos de persistencia y eliminación.

cascade = CascadeType.ALL: esta propiedad define el comportamiento de las operaciones en cascada que se aplican a los 
objetos relacionados. En este caso, CascadeType.ALL significa que cualquier operación realizada en la entidad Address
(como persistir, eliminar, etc.) también se aplicará a la lista de Elevator relacionada.
Por ejemplo, si eliminas una Address, todos los Elevator asociados también se eliminarán automáticamente de la base de
datos.

Para crear una relación n:1 :
```
    @ManyToOne
    @JoinColumn(name = "idAddress")  // Columna en la tabla Elevator que almacenará el ID de Address
    @JsonBackReference
    private Address address;
```
@ManyToOne: esta anotación indica que hay una relación de muchos a uno entre la entidad Elevator y la entidad Address.
Esto significa que muchos ascensores (Elevator) pueden estar asociados con una sola dirección (Address).

@JoinColumn(name = "idAddress"): esta anotación se utiliza para especificar la columna en la base de datos que se 
utilizará para almacenar la relación entre las dos entidades.
name = "idAddress" indica que en la tabla Elevator (BD) habrá una columna llamada idAddress que almacenará el ID de la 
dirección a la que está asociado cada ascensor.

La declaración private Address address; en la clase Elevator representa una propiedad o atributo que establece una
relación con la entidad Address.

## TODO

Añadir manejo de errores
Corregir tablas en las que no se pueden repetir valores
Probar las tablas creadas haciendo peticiones GET para comprobar el correcto funcionamiento a BD y comprobar si 
funcionan las relaciones establecidas


## Development

Update your local database connection in `application.yml` or create your own `application-local.yml` file to override
settings for development.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options".

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing -
[learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

Start your application with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/elevator-backend-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=com.alex.elevator/elevator-backend
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html) 
* [Bootstrap docs](https://getbootstrap.com/docs/5.3/getting-started/introduction/)    
"# elevator-backend" 
"# elevator-backend" 
