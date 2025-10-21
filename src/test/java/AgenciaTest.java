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

  // ========================================
  // PRUEBAS MODIFICADAS (sin excepciones)
  // ========================================

  @Test
  public void testAlquilarVehiculoYaAlquilado() {
    // Primer alquiler debe ser exitoso
    Alquiler primerAlquiler = agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    assertNotNull(primerAlquiler, "El primer alquiler debería ser exitoso");

    // Segundo intento de alquiler del mismo vehículo debe retornar null
    Alquiler segundoAlquiler = agencia.realizarAlquiler(cliente, vehiculo, "2024-11-06", "2024-11-10");
    assertNull(segundoAlquiler, "No debería poder alquilar un vehículo ya alquilado, debe retornar null");
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

  // ========================================
  // PRUEBAS NUEVAS (sin excepciones)
  // ========================================

  @Test
  public void testCalcularCostoTotalAlquiler() {
    // Test: Verificar que el costo total se calcula correctamente
    Alquiler alquiler = agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");

    assertNotNull(alquiler, "El alquiler no debería ser null");

    // El vehículo cuesta 50.0 por día, de 2024-11-01 a 2024-11-05 son 4 días
    double costoEsperado = 50.0 * 4;
    assertEquals(costoEsperado, alquiler.calcularCostoTotal(), 0.01,
      "El costo total debería ser 200.0 (4 días * 50.0)");
  }

  @Test
  public void testRegistrarMultiplesClientes() {
    // Test: Verificar que se pueden registrar múltiples clientes
    Cliente cliente2 = new Cliente("Maria", "Gomez", "87654321B", "maria.gomez@example.com", "987654321");
    Cliente cliente3 = new Cliente("Pedro", "Lopez", "11223344C", "pedro.lopez@example.com", "555666777");

    agencia.registrarCliente(cliente2);
    agencia.registrarCliente(cliente3);

    List<Cliente> clientes = agencia.listarClientes();

    assertEquals(3, clientes.size(), "Debería haber 3 clientes registrados");
    assertTrue(clientes.contains(cliente), "La lista debería contener al primer cliente");
    assertTrue(clientes.contains(cliente2), "La lista debería contener al segundo cliente");
    assertTrue(clientes.contains(cliente3), "La lista debería contener al tercer cliente");
  }

  @Test
  public void testAlquilarVehiculoNoDisponible() {
    // Test: Intentar alquilar un vehículo que no está disponible (debe retornar null)
    Vehiculo vehiculoNoDisponible = new Vehiculo("XYZ999", "Ford Mustang", "SUV", 80.0, false);
    agencia.registrarVehiculo(vehiculoNoDisponible);

    Alquiler alquiler = agencia.realizarAlquiler(cliente, vehiculoNoDisponible, "2024-11-01", "2024-11-05");

    assertNull(alquiler, "No debería permitir alquilar un vehículo no disponible, debe retornar null");
    assertFalse(vehiculoNoDisponible.isDisponible(), "El vehículo debe seguir marcado como no disponible");
  }

  @Test
  public void testMostrarInformacionCliente() {
    // Test: Verificar que el método mostrarInformacion() funciona correctamente
    String info = cliente.mostrarInformacion();

    assertNotNull(info, "La información del cliente no debería ser null");
    assertTrue(info.contains("Juan"), "La información debería contener el nombre");
    assertTrue(info.contains("Perez"), "La información debería contener el apellido");
    assertTrue(info.contains("12345678A"), "La información debería contener el DNI");
    assertTrue(info.contains("juan.perez@example.com"), "La información debería contener el email");
  }

  @Test
  public void testListarAlquileresActivos() {
    // Test: Verificar que se pueden listar los alquileres activos
    Cliente cliente2 = new Cliente("Ana", "Martinez", "99887766D", "ana.martinez@example.com", "111222333");
    Vehiculo vehiculo2 = new Vehiculo("GHI789", "Mazda CX-5", "SUV", 70.0, true);

    agencia.registrarCliente(cliente2);
    agencia.registrarVehiculo(vehiculo2);

    // Realizar dos alquileres
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    agencia.realizarAlquiler(cliente2, vehiculo2, "2024-11-10", "2024-11-15");

    List<Alquiler> alquileres = agencia.listarAlquileres();

    assertEquals(2, alquileres.size(), "Debería haber 2 alquileres activos");

    // Cancelar un alquiler
    agencia.cancelarAlquiler(vehiculo);

    // Verificar que solo queda 1 alquiler activo
    alquileres = agencia.listarAlquileres();
    assertEquals(1, alquileres.size(), "Debería haber 1 alquiler activo después de cancelar uno");
  }
}