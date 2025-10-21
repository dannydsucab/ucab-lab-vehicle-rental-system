# 📖 Guía de Conceptos - Sistema de Alquiler de Vehículos

## 🎯 Objetivo de Este Documento

Este documento te ayudará a **entender los conceptos** necesarios para implementar el sistema. No te dará el código completo, sino las ideas y la lógica que necesitas aplicar.

---

## 🧠 Conceptos Clave de POO que Necesitas

### 1. Encapsulamiento 🔒

**¿Qué es?**
Ocultar los detalles internos de una clase y exponer solo lo necesario mediante métodos públicos.

**¿Por qué es importante aquí?**
Todos los atributos de tus clases deben ser `private`. El acceso a ellos debe ser mediante getters y setters.

**Ejemplo conceptual:**
```
❌ MAL: vehiculo.disponible = false;
✅ BIEN: vehiculo.setDisponible(false);
```

**En las pruebas verás:**
Las pruebas usan métodos como `vehiculo.isDisponible()` en lugar de acceder directamente al atributo. Esto te indica que necesitas ese método getter.

---

### 2. Herencia 👨‍👦

**¿Qué es?**
Una clase puede heredar atributos y métodos de otra clase.

**¿Por qué es importante aquí?**
`Cliente` debe heredar de `Persona`. Esto significa que `Cliente` tendrá todos los atributos de `Persona` (nombre, apellido, dni) más sus propios atributos (email, teléfono).

**Pista de las pruebas:**
```java
assertTrue(cliente instanceof Persona, "Cliente debería ser una instancia de Persona");
```
Esta línea te dice que `Cliente` DEBE heredar de `Persona`.

---

### 3. Abstracción 🎨

**¿Qué es?**
Definir la estructura sin la implementación completa. Las clases abstractas no se pueden instanciar directamente.

**¿Por qué es importante aquí?**
`Persona` es abstracta y tiene un método abstracto `mostrarInformacion()`. Cada clase hija debe implementar ese método a su manera.

**Lo que esto significa:**
- No puedes hacer `new Persona(...)`
- Sí puedes hacer `new Cliente(...)` porque Cliente implementa el método abstracto
- Cliente DEBE sobrescribir el método `mostrarInformacion()`

---

### 4. Polimorfismo 🦎

**¿Qué es?**
La capacidad de que diferentes clases respondan al mismo método de diferentes maneras.

**¿Por qué es importante aquí?**
Aunque `mostrarInformacion()` está definido en `Persona`, cada clase hija (como `Cliente`) lo implementa de forma específica.

---

## 🔍 Analizando las Pruebas para Deducir la Implementación

### 📝 Ejemplo: Deducir Constructores

**Observa esta línea en las pruebas:**
```java
vehiculo = new Vehiculo("ABC123", "Toyota Corolla", "Sedán", 50.0, true);
```

**¿Qué te dice esto?**
- La clase `Vehiculo` necesita un constructor
- El constructor recibe 5 parámetros en este orden:
  1. String (matrícula)
  2. String (modelo)
  3. String (tipo)
  4. double (precio por día)
  5. boolean (disponible)

**Tu trabajo:** Crear ese constructor y asignar los valores a los atributos correspondientes.

---

### 📝 Ejemplo: Deducir Métodos

**Observa esta línea en las pruebas:**
```java
Alquiler alquiler = agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
```

**¿Qué te dice esto?**
- La clase `Agencia` tiene un método llamado `realizarAlquiler`
- Recibe 4 parámetros: Cliente, Vehiculo, String, String
- Retorna un objeto de tipo `Alquiler` (o puede ser `null`)

**Tu trabajo:** Implementar ese método siguiendo las reglas de negocio.

---

### 📝 Ejemplo: Deducir Validaciones

**Observa esta prueba:**
```java
@Test
public void testAlquilarVehiculoYaAlquilado() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    Alquiler segundoAlquiler = agencia.realizarAlquiler(cliente, vehiculo, "2024-11-06", "2024-11-10");
    assertNull(segundoAlquiler, "No debería poder alquilar un vehículo ya alquilado");
}
```

**¿Qué te dice esto?**
1. El primer alquiler debería funcionar normalmente
2. El segundo intento de alquilar el mismo vehículo debe fallar
3. Cuando falla, el método debe retornar `null`
4. Esto significa: debes validar si el vehículo está disponible ANTES de crear el alquiler

**Tu trabajo:** Agregar una validación al inicio del método `realizarAlquiler()`.

---

## 🎓 Estrategia de Resolución Paso a Paso

### Fase 1: Clases Simples (30 minutos)

1. **Crea la clase `Vehiculo`**
  - Lee las pruebas y observa qué constructor necesita
  - Identifica los atributos viendo qué se usa en las pruebas
  - Crea los getters/setters necesarios (especialmente `isDisponible()` y `setDisponible()`)

2. **Crea la clase abstracta `Persona`**
  - Debe tener los atributos básicos: nombre, apellido, dni
  - Debe declarar el método abstracto `mostrarInformacion()`
  - Crea getters/setters para los atributos

### Fase 2: Herencia (20 minutos)

3. **Crea la clase `Cliente` que hereda de `Persona`**
  - Usa `extends Persona`
  - Agrega los atributos adicionales: email, telefono
  - Implementa el método abstracto `mostrarInformacion()`
  - El método debe retornar un String con toda la información

**Pista:** Lee esta prueba para ver qué debe contener el String:
```java
assertTrue(info.contains("Juan"), "La información debería contener el nombre");
```

### Fase 3: Alquiler (15 minutos)

4. **Crea la clase `Alquiler`**
  - Observa el constructor en las pruebas
  - Crea getters para los atributos
  - Implementa `calcularCostoTotal()`

**Pista para calcular días:**
Si las fechas son "2024-11-01" y "2024-11-05", necesitas extraer los días (01 y 05) y restarlos.

### Fase 4: Lógica de Negocio (45 minutos)

5. **Crea la clase `Agencia`**
  - Declara las tres listas como atributos
  - Inicialízalas en el constructor
  - Implementa los métodos uno por uno

**Orden recomendado de implementación:**
1. `registrarCliente()` - El más simple
2. `registrarVehiculo()` - Similar al anterior
3. `listarClientes()` - Retornar la lista
4. `listarVehiculosDisponibles()` - Requiere filtrado
5. `realizarAlquiler()` - El más complejo, requiere validaciones
6. `cancelarAlquiler()` - Modificar listas y estado
7. `listarAlquileres()` - Retornar la lista

---

## 💡 Pensamiento Lógico para `realizarAlquiler()`

Esta es la función más compleja. Piensa en los pasos lógicos:

### Pregunta 1: ¿Qué debo verificar primero?
¿El vehículo está disponible? Si no lo está, ¿qué debo retornar?

### Pregunta 2: Si el vehículo SÍ está disponible, ¿qué hago?
1. ¿Creo un nuevo objeto Alquiler?
2. ¿Cambio el estado del vehículo?
3. ¿Agrego el alquiler a alguna lista?
4. ¿Qué debo retornar?

### Pregunta 3: ¿En qué orden hago las operaciones?
El orden importa. Piensa: ¿debo cambiar el estado antes o después de crear el alquiler?

**Las pruebas te darán las respuestas** - léelas cuidadosamente.

---

## 🔢 Lógica para Calcular Días

**Problema:** Tienes dos fechas en formato String: "2024-11-01" y "2024-11-05"

**Necesitas:** Calcular cuántos días hay entre ellas (en este caso: 4 días)

### Enfoque Recomendado: Usar Tipos de Datos de Fecha

Java proporciona clases específicas para trabajar con fechas. **No reinventes la rueda.**

**Pistas para una solución profesional:**

1. **Investiga la clase `LocalDate`** (paquete `java.time`)
  - Esta clase representa una fecha sin hora
  - Tiene un método para parsear Strings: `LocalDate.parse()`
  - Formato por defecto: "yyyy-MM-dd" (¡justo el que necesitas!)

2. **Investiga `ChronoUnit`** (paquete `java.time.temporal`)
  - Permite calcular diferencias entre fechas
  - Método útil: `ChronoUnit.DAYS.between(fecha1, fecha2)`

3. **Flujo lógico sugerido:**
  - Convertir String → LocalDate
  - Calcular diferencia en días entre las dos fechas
  - Multiplicar por el precio por día

**¿Por qué este enfoque es mejor?**
- ✅ Maneja correctamente meses con diferentes días
- ✅ Maneja correctamente cambios de año
- ✅ Código más legible y profesional
- ✅ Menos propenso a errores

### Enfoque Alternativo Simple (Solo para este ejercicio)

Si prefieres una solución más simple para este caso específico:
- Las fechas de prueba están en el mismo mes
- Podrías extraer solo los días y restarlos
- **Advertencia:** Esto NO funcionaría para fechas en diferentes meses

**Observa esta prueba para confirmar el resultado esperado:**
```java
// El vehículo cuesta 50.0 por día, de 2024-11-01 a 2024-11-05 son 4 días
double costoEsperado = 50.0 * 4;
assertEquals(costoEsperado, alquiler.calcularCostoTotal(), 0.01, 
    "El costo total debería ser 200.0 (4 días * 50.0)");
```

**Reflexión:** ¿Qué pasaría si la fecha de inicio fuera "2024-10-30" y la final "2024-11-03"?
El enfoque simple fallaría. El enfoque con `LocalDate` funcionaría perfectamente.

### Recursos para Aprender

- 📖 [Java LocalDate Documentation](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)
- 📖 [Java ChronoUnit Documentation](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoUnit.html)
- 🔍 Busca: "Java calculate days between two dates"

---

## 🎯 Entendiendo las Validaciones

### Validación 1: Vehículo Disponible

**Escenario:** Alguien intenta alquilar un vehículo
**Pregunta:** ¿El vehículo está disponible?
- **SI** → Proceder con el alquiler
- **NO** → Retornar null, no hacer nada más

### Validación 2: Estado Actualizado

**Escenario:** Se crea un alquiler exitoso
**Consecuencia:** El vehículo ya no debe estar disponible para otros
**Acción requerida:** Cambiar el atributo `disponible` del vehículo

### Validación 3: Cancelación Correcta

**Escenario:** Se cancela un alquiler
**Consecuencias:**
1. El alquiler debe eliminarse de la lista
2. El vehículo debe volver a estar disponible

---

## 📊 Trabajando con Listas

### Concepto: ArrayList

Necesitarás usar `ArrayList` para almacenar colecciones de objetos.

**Operaciones básicas que necesitarás:**

1. **Crear una lista vacía**
  - Inicializar en el constructor de Agencia

2. **Agregar elementos**
  - Cuando registras un cliente o vehículo
  - Cuando creas un alquiler exitoso

3. **Recorrer una lista**
  - Cuando necesitas filtrar vehículos disponibles
  - Cuando buscas un alquiler específico

4. **Eliminar elementos**
  - Cuando cancelas un alquiler

5. **Retornar una lista**
  - Para los métodos que listan clientes, vehículos, alquileres

**Pista:** Observa en las pruebas cómo se llaman métodos sobre las listas:
```java
assertEquals(1, agencia.listarVehiculosDisponibles().size(), "...");
```
Esto te dice que tu método debe retornar una lista que tenga el método `.size()`.

---

## 🔄 Flujo Lógico del Sistema

### Ciclo de Vida de un Alquiler

**Estado Inicial:**
```
Vehículo: disponible = true
Lista de alquileres: vacía
```

**Después de alquilar:**
```
Vehículo: disponible = false
Lista de alquileres: contiene 1 alquiler
```

**Después de cancelar:**
```
Vehículo: disponible = true
Lista de alquileres: vacía nuevamente
```

**Pregunta para reflexionar:** ¿Qué métodos de tu código son responsables de cada transición de estado?

---

## 🧪 Metodología: Test-Driven Development (TDD)

### ¿Cómo usar las pruebas para guiar tu desarrollo?

**Paso 1:** Lee UNA prueba específica completa
**Paso 2:** Entiende qué está probando
**Paso 3:** Implementa SOLO lo necesario para pasar esa prueba
**Paso 4:** Ejecuta las pruebas (localmente o con push)
**Paso 5:** Si pasa, continúa con la siguiente prueba
**Paso 6:** Si falla, analiza el error y corrige

### Orden Sugerido de Pruebas a Resolver

1. ✅ `testClienteEsUnaPersona` - Valida la herencia (lo más básico)
2. ✅ `testMostrarInformacionCliente` - Valida el polimorfismo
3. ✅ `testRegistrarMultiplesClientes` - Valida manejo de listas simples
4. ✅ `testAlquilarVehiculoDisponible` - Valida cambio de estado
5. ✅ `testListarVehiculosDisponibles` - Valida filtrado de listas
6. ✅ `testAlquilarVehiculoNoDisponible` - Valida la validación
7. ✅ `testAlquilarVehiculoYaAlquilado` - Valida validación en secuencia
8. ✅ `testCancelarAlquiler` - Valida liberación de recursos
9. ✅ `testCalcularCostoTotalAlquiler` - Valida cálculos
10. ✅ `testListarAlquileresActivos` - Valida gestión completa

---

## 🎨 Patrones de Diseño Implícitos

### Patrón 1: Constructor con Parámetros

**Observación en las pruebas:**
```java
Cliente cliente = new Cliente("Juan", "Perez", "12345678A", "juan@email.com", "123456789");
```

**Lo que esto implica:**
- Constructor debe aceptar exactamente esos 5 parámetros
- El orden importa
- Deben asignarse a los atributos correspondientes

### Patrón 2: Retorno de null para Operaciones Inválidas

**Observación en las pruebas:**
```java
assertNull(segundoAlquiler, "No debería poder alquilar...");
```

**Lo que esto implica:**
- En lugar de lanzar excepciones, retornas `null`
- `null` indica que la operación no pudo completarse
- El código que llama debe verificar si el retorno es `null`

### Patrón 3: Métodos que Retornan Colecciones

**Observación en las pruebas:**
```java
List<Cliente> clientes = agencia.listarClientes();
```

**Lo que esto implica:**
- El método retorna una lista completa
- El tipo de retorno es `List<TipoDeObjeto>`
- Puedes retornar directamente tu atributo lista

---

## 🔍 Preguntas de Autoevaluación

Antes de hacer push, pregúntate:

### Sobre Encapsulamiento:
- [ ] ¿Todos mis atributos son `private`?
- [ ] ¿He creado getters para todos los atributos que se usan en las pruebas?
- [ ] ¿He creado setters para los atributos que necesitan modificarse?

### Sobre Herencia:
- [ ] ¿La clase `Persona` es abstracta?
- [ ] ¿`Cliente` extiende de `Persona`?
- [ ] ¿El constructor de `Cliente` llama al constructor de `Persona`?

### Sobre Validaciones:
- [ ] ¿Verifico si un vehículo está disponible ANTES de alquilarlo?
- [ ] ¿Retorno `null` cuando una operación no puede completarse?
- [ ] ¿Actualizo el estado del vehículo después de cada operación?

### Sobre Listas:
- [ ] ¿Inicialicé todas las listas en el constructor de `Agencia`?
- [ ] ¿Agrego elementos a las listas cuando corresponde?
- [ ] ¿Elimino elementos cuando se cancelan alquileres?

---

## 💭 Pensamiento Crítico: Casos Extremos

### Caso 1: ¿Qué pasa si intento cancelar un alquiler que no existe?
**Reflexión:** Las pruebas actuales no cubren este caso, pero tu código debe ser robusto.

### Caso 2: ¿Puedo alquilar el mismo vehículo al mismo cliente dos veces?
**Reflexión:** Según las reglas de negocio, si el vehículo no está disponible, no se puede.

### Caso 3: ¿Qué pasa si las fechas están invertidas?
**Reflexión:** Para este ejercicio, asume que las fechas son correctas. En un sistema real, validarías esto.

---

## 📚 Conceptos de Java que Necesitas

### 1. Clases Abstractas
```
Sintaxis básica:
- Declarar clase: abstract class NombreClase
- Declarar método abstracto: abstract TipoRetorno nombreMetodo();
- Las clases hijas deben usar @Override
```

### 2. Herencia
```
Sintaxis básica:
- class ClaseHija extends ClasePadre
- Usar super() en el constructor de la hija para llamar al padre
```

### 3. ArrayList
```
Operaciones que necesitarás:
- Crear: new ArrayList<>()
- Agregar: lista.add(elemento)
- Obtener tamaño: lista.size()
- Recorrer: for (Tipo elem : lista) { ... }
- Eliminar: lista.remove(indice) o lista.remove(objeto)
- Verificar contenido: lista.contains(objeto)
```

### 4. String
```
Operaciones que necesitarás:
- Obtener subcadena: substring(inicio, fin)
- Convertir a entero: Integer.parseInt(string)
- Concatenar: usar + entre strings
- Verificar si contiene: contains(substring)
```

### 5. LocalDate (Recomendado para fechas)
```
Paquete: java.time.LocalDate

Operaciones útiles:
- Parsear String a fecha: LocalDate.parse("2024-11-01")
- Calcular diferencia: ChronoUnit.DAYS.between(fecha1, fecha2)
- Formato esperado: "yyyy-MM-dd" (año-mes-día)

Ventajas:
- Maneja automáticamente diferentes meses
- Maneja años bisiestos
- Código más robusto y profesional
```

---

## 🎯 Resumen: Lo que DEBES Deducir

De las pruebas unitarias puedes deducir:

1. **Nombres de clases y atributos** - Observa qué se crea y qué se accede
2. **Firmas de métodos** - Observa qué métodos se llaman, con qué parámetros y qué retornan
3. **Reglas de negocio** - Observa las aserciones (assertEquals, assertTrue, assertNull)
4. **Relaciones entre clases** - Observa los tipos de parámetros y retornos
5. **Comportamiento esperado** - Lee los mensajes de error en las aserciones

---

## 🚀 Comenzar a Programar

### Checklist Inicial

Antes de escribir código:
- [ ] He leído todas las pruebas al menos una vez
- [ ] Entiendo qué es una clase abstracta
- [ ] Entiendo cómo funciona la herencia en Java
- [ ] Sé cómo crear y usar ArrayList
- [ ] He identificado qué clases necesito crear

### Checklist Durante el Desarrollo

Para cada clase:
- [ ] He identificado todos los atributos necesarios
- [ ] He creado el constructor con los parámetros correctos
- [ ] He creado todos los getters y setters
- [ ] He implementado todos los métodos que aparecen en las pruebas

### Checklist Final

Antes de hacer push:
- [ ] He compilado el proyecto sin errores
- [ ] He ejecutado las pruebas localmente (opcional)
- [ ] He revisado que todos los atributos sean privados
- [ ] He verificado la herencia y el método abstracto
- [ ] Estoy seguro de mi implementación de `realizarAlquiler()`

---

## 💡 Últimos Consejos

### 1. No te Frustres
Es normal no aprobar todas las pruebas en el primer intento. Cada error es una oportunidad de aprendizaje.

### 2. Lee los Errores
Los mensajes de error de las pruebas son muy específicos. Te dicen exactamente qué está fallando.

### 3. Trabaja Incremental
No intentes implementar todo de una vez. Hazlo clase por clase, método por método.

### 4. Usa los Recursos
- Consulta el material de clase
- Busca en la documentación de Java
- Pregunta a tu profesor en horario de consulta

### 5. Entiende, No Copies
El objetivo es que APRENDAS, no solo que pases las pruebas. Entiende cada línea que escribes.

---

## 🎓 Reflexión Final

Este ejercicio simula cómo se trabaja en la industria del software:
- Hay **especificaciones** (los requerimientos escritos)
- Hay **pruebas automatizadas** (las pruebas unitarias)
- Tu trabajo es **implementar el código** que satisfaga ambos

**Habilidad clave:** Leer y entender código ajeno (las pruebas) para escribir tu propio código.

---

## 📖 Glosario de Términos

- **Clase Abstracta:** Clase que no puede instanciarse directamente y puede tener métodos abstractos
- **Método Abstracto:** Método declarado sin implementación, las subclases deben implementarlo
- **Herencia:** Mecanismo donde una clase obtiene propiedades y métodos de otra
- **Encapsulamiento:** Ocultar los detalles internos de una clase
- **Polimorfismo:** Capacidad de diferentes clases de responder al mismo mensaje
- **Prueba Unitaria:** Código que verifica que una unidad de código funcione correctamente
- **Aserción:** Declaración que verifica si una condición es verdadera
- **TDD:** Test-Driven Development - Metodología donde las pruebas guían el desarrollo

---

## ✅ Estás Listo Cuando...

- Entiendes qué hace cada prueba unitaria
- Puedes explicar con tus palabras las reglas de negocio
- Has identificado todas las clases y sus relaciones
- Tienes claro qué métodos necesitas implementar
- Comprendes cómo validar si un vehículo está disponible

**¡Ahora sí, a programar! 🚀**

Recuerda: Las pruebas son tus amigas, no tus enemigas. Están ahí para ayudarte a escribir mejor código.