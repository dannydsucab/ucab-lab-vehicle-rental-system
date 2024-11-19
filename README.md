**Enunciado del Ejercicio de Laboratorio: Sistema de Gestión de Alquiler de Vehículos**

El objetivo de este ejercicio es desarrollar un sistema de gestión de alquiler de vehículos, donde se pondrán en práctica conceptos de Programación Orientada a Objetos (POO) como herencia, polimorfismo, encapsulamiento y abstracción. El sistema debe permitir registrar clientes, realizar alquileres y gestionar los vehículos.

**Requerimientos**:

1. **Clases y Relaciones**:
  - Crear una clase abstracta `Persona` que tenga los atributos comunes: `nombre` (String), `apellido` (String), `dni` (String), y un método abstracto `mostrarInformacion()`. La clase debe incluir los métodos getter y setter para los atributos.
  - Crear una clase `Cliente` que herede de `Persona` y tenga atributos adicionales como `email` (String) y `telefono` (String).
  - Crear una clase `Vehiculo` que contenga los siguientes atributos: `matricula` (String), `modelo` (String), `tipo` (String: Sedán, SUV, Camioneta), `precioPorDia` (double) y un atributo booleano `disponible` para saber si el vehículo está disponible para alquilar.
  - Crear una clase `Alquiler` con los atributos: `cliente` (referencia a un objeto de la clase `Cliente`), `vehiculo` (referencia a un objeto de la clase `Vehiculo`), `fechaInicio` (String), `fechaFin` (String) y un método `calcularCostoTotal()` que calcule el precio total en función de los días de alquiler y el precio por día del vehículo.

2. **Funcionalidades**:
  - Implementar un método en la clase `Agencia` para **registrar clientes**. La agencia debe tener una lista de clientes.
  - Implementar un método en la clase `Agencia` para **realizar un alquiler**. Este método debe permitir seleccionar un vehículo disponible y registrar el alquiler para un cliente.
  - Implementar un método en la clase `Agencia` que **muestre los vehículos disponibles**.
  - Implementar un método en la clase `Agencia` para **cancelar un alquiler**, liberando el vehículo correspondiente.

3. **Reglas de Negocio**:
  - No se puede realizar un alquiler si el vehículo ya está alquilado.
  - El sistema debe permitir alquilar solo vehículos disponibles, y actualizarlos como no disponibles una vez se complete el alquiler.
  - La clase `Agencia` debe mantener listas de `Clientes`, `Vehiculos`, y `Alquileres`.
  - Todos los atributos deben ser **privados**, y debe usarse encapsulamiento para acceder y modificar los mismos.

4. **Consideraciones Adicionales**:
  - Crear una clase `Agencia` con el método `main()` para probar las funcionalidades desarrolladas.
  - Se debe utilizar **polimorfismo** al implementar el método `mostrarInformacion()` en la clase `Cliente`.
  - Realizar al menos tres alquileres de vehículos, y mostrar la información del cliente, vehículo y costo total del alquiler.

**Puntos a Evaluar**:
- Aplicación de conceptos de herencia, polimorfismo y abstracción.
- Correcta implementación de getters y setters (encapsulamiento).
- Uso adecuado de listas para almacenar clientes, vehículos y alquileres.
- Validaciones implementadas al realizar un alquiler y la cancelación del mismo.
- Uso de métodos para realizar operaciones sobre los objetos.

**Ejercicio Adicional: Implementación de Código para Pasar las Pruebas Unitarias**

Para complementar el ejercicio principal, se proporciona un conjunto de pruebas unitarias ya codificadas. El estudiante deberá completar la implementación del sistema de alquiler de vehículos para que todas las pruebas unitarias pasen correctamente.

1. **Pruebas Unitarias Proporcionadas**:
  - **Prueba de Alquiler de Vehículo ya Alquilado**: Validar que no se puede realizar un alquiler si el vehículo ya está alquilado.
  - **Prueba de Disponibilidad de Vehículos**: Validar que el sistema solo permite alquilar vehículos disponibles, y que actualiza correctamente el estado del vehículo al alquilarlo.
  - **Prueba de Cancelación de Alquiler**: Validar que se puede cancelar un alquiler y que, al hacerlo, el vehículo se vuelve a marcar como disponible.
  - **Prueba de Encapsulamiento**: Verificar que los atributos de las clases se mantienen encapsulados y se acceden solo mediante sus métodos getter y setter.

2. **Requerimientos de las Pruebas**:
  - Las pruebas se encuentran implementadas usando JUnit y están incluidas en el proyecto entregado.
  - El estudiante deberá completar la implementación del sistema para asegurar que todas las pruebas se ejecuten de forma exitosa.
  - Cada prueba está diseñada para comprobar un único aspecto de la lógica del sistema. Se debe asegurar que la lógica implementada cumpla con todos los requisitos planteados en las pruebas.

**Código de Pruebas Unitarias**:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgenciaTest {

  private Agencia agencia;
  private Cliente cliente;
  private Vehiculo vehiculo;

  @BeforeEach
  public void setUp() {
    agencia = new Agencia();
    cliente = new Cliente("Juan", "Perez", "12345678A", "juan.perez@example.com", "123456789");
    vehiculo = new Vehiculo("ABC123", "Toyota Corolla", "Sedán", 50.0, true);
    agencia.registrarCliente(cliente);
    agencia.registrarVehiculo(vehiculo);
  }

  @Test
  public void testAlquilarVehiculoYaAlquilado() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    assertThrows(IllegalStateException.class, () -> {
      agencia.realizarAlquiler(cliente, vehiculo, "2024-11-06", "2024-11-10");
    });
  }

  @Test
  public void testAlquilarVehiculoDisponible() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    assertFalse(vehiculo.isDisponible(), "El vehículo debería estar marcado como no disponible después del alquiler");
  }

  @Test
  public void testCancelarAlquiler() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    agencia.cancelarAlquiler(vehiculo);
    assertTrue(vehiculo.isDisponible(), "El vehículo debería estar disponible después de cancelar el alquiler");
  }

  @Test
  public void testEncapsulamiento() {
    assertThrows(IllegalAccessException.class, () -> {
      vehiculo.matricula = "XYZ789"; // Esto debería fallar debido a que el atributo es privado
    });
  }

  @Test
  public void testListarVehiculosDisponibles() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    assertEquals(0, agencia.listarVehiculosDisponibles().size(), "No debería haber vehículos disponibles después del alquiler");

    Vehiculo nuevoVehiculo = new Vehiculo("DEF456", "Honda Civic", "Sedán", 60.0, true);
    agencia.registrarVehiculo(nuevoVehiculo);
    assertEquals(1, agencia.listarVehiculosDisponibles().size(), "Debería haber un vehículo disponible después de registrar un nuevo vehículo");
  }

  @Test
  public void testClienteEsUnaPersona() {
    assertTrue(cliente instanceof Persona, "Cliente debería ser una instancia de Persona");
  }
}
```

**Puntos a Evaluar con las Pruebas Unitarias Proporcionadas**:
- Completar la implementación del código para satisfacer todas las pruebas proporcionadas.
- Aplicar conceptos de POO en la implementación que permitan cumplir las reglas de negocio.
- Garantizar la cobertura total de las reglas de negocio del sistema.
- Manejar adecuadamente los escenarios límite definidos en las pruebas (por ejemplo, intentar alquilar un vehículo ya alquilado).

**Entrega**:
- Sube el código fuente a tu repositorio asignado para la evaluación.

