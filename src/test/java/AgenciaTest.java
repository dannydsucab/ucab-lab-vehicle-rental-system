import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ve.edu.ucab.lab.Agencia;
import ve.edu.ucab.lab.Cliente;
import ve.edu.ucab.lab.Persona;
import ve.edu.ucab.lab.Vehiculo;

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