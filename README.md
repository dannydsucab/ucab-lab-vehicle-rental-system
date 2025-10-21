# 🚗 Sistema de Gestión de Alquiler de Vehículos

## 📚 Descripción del Proyecto

Bienvenido al laboratorio de **Programación Orientada a Objetos**. En este ejercicio desarrollarás un sistema completo de gestión de alquiler de vehículos aplicando los conceptos fundamentales de POO: **herencia**, **polimorfismo**, **encapsulamiento** y **abstracción**.

---

## 🎯 Objetivos de Aprendizaje

Al completar este ejercicio serás capaz de:

- ✅ Implementar **herencia** entre clases
- ✅ Crear y usar **clases abstractas** con métodos abstractos
- ✅ Aplicar **encapsulamiento** con atributos privados y métodos getter/setter
- ✅ Implementar **polimorfismo** sobrescribiendo métodos
- ✅ Gestionar **colecciones** de objetos (listas)
- ✅ Validar **reglas de negocio** antes de realizar operaciones
- ✅ Escribir código que pase **pruebas unitarias** automatizadas

---

## 📋 Requerimientos del Sistema

### 1. Clases y Relaciones

#### Clase `Persona` (Abstracta)
- Debe ser una clase **abstracta** que sirva como base para otras clases
- Atributos comunes: `nombre` (String), `apellido` (String), `dni` (String)
- Debe incluir un método abstracto `mostrarInformacion()` que retorne un String
- Todos los atributos deben tener sus métodos getter y setter correspondientes
- Los atributos deben ser **privados**

#### Clase `Cliente`
- Debe **heredar** de la clase `Persona`
- Atributos adicionales: `email` (String) y `telefono` (String)
- Debe implementar el método abstracto `mostrarInformacion()` heredado de `Persona`
- El método debe retornar un String con toda la información del cliente
- Debe incluir getters y setters para los atributos adicionales

#### Clase `Vehiculo`
- Atributos: `matricula` (String), `modelo` (String), `tipo` (String: Sedán, SUV, Camioneta), `precioPorDia` (double) y `disponible` (boolean)
- El atributo `disponible` indica si el vehículo está disponible para alquilar
- Todos los atributos deben ser **privados** con sus respectivos getters y setters

#### Clase `Alquiler`
- Atributos: `cliente` (referencia a un objeto Cliente), `vehiculo` (referencia a un objeto Vehiculo), `fechaInicio` (String), `fechaFin` (String)
- Debe incluir un método `calcularCostoTotal()` que calcule el precio total del alquiler
- El cálculo debe basarse en los días de alquiler multiplicados por el precio por día del vehículo
- Las fechas están en formato "YYYY-MM-DD" (ejemplo: "2024-11-01")

#### Clase `Agencia`
- Debe mantener listas de: `clientes`, `vehiculos` y `alquileres`
- Debe incluir métodos para:
  - **Registrar clientes**: Agregar clientes a la lista
  - **Registrar vehículos**: Agregar vehículos a la lista
  - **Realizar un alquiler**: Permitir alquilar un vehículo disponible
  - **Cancelar un alquiler**: Liberar un vehículo que estaba alquilado
  - **Listar vehículos disponibles**: Mostrar solo los vehículos que pueden ser alquilados
  - **Listar clientes**: Obtener la lista de todos los clientes registrados
  - **Listar alquileres**: Obtener la lista de alquileres activos

---

### 2. Reglas de Negocio

⚠️ **Importante**: Estas reglas determinan el comportamiento correcto del sistema.

1. **No se puede alquilar un vehículo ya alquilado**
  - Si un vehículo no está disponible, el método de alquiler debe retornar `null`
  - No se debe crear ningún alquiler ni modificar el estado del sistema

2. **Al realizar un alquiler exitoso:**
  - Solo se permite si el vehículo está disponible
  - Se debe crear el objeto Alquiler
  - Se debe actualizar el estado del vehículo a "no disponible"
  - Se debe agregar el alquiler a la lista de alquileres
  - Se debe retornar el objeto Alquiler creado

3. **Al cancelar un alquiler:**
  - Se debe eliminar el alquiler de la lista de alquileres
  - Se debe actualizar el estado del vehículo a "disponible"

4. **Encapsulamiento:**
  - TODOS los atributos deben ser privados
  - Se debe acceder a ellos únicamente mediante getters y setters

5. **Herencia:**
  - La clase `Cliente` debe heredar de `Persona`
  - La clase `Persona` debe ser abstracta

---

## 📊 Sistema de Calificación Automática

Este proyecto utiliza **GitHub Actions** para evaluar automáticamente tu código mediante **pruebas unitarias**. Las pruebas ya están escritas en el archivo `AgenciaTest.java` - tu trabajo es implementar el código para que todas pasen.

### 🎓 Distribución de Puntos (Total: 20 puntos)

Cada prueba unitaria vale **2 puntos**. Hay un total de **10 pruebas**:

| # | Nombre de la Prueba | Puntos | ¿Qué Evalúa? |
|---|---------------------|--------|--------------|
| 1️⃣ | `testAlquilarVehiculoYaAlquilado` | 2 pts | Validación: no alquilar vehículos ya alquilados |
| 2️⃣ | `testAlquilarVehiculoDisponible` | 2 pts | Actualización correcta del estado del vehículo |
| 3️⃣ | `testCancelarAlquiler` | 2 pts | Liberación de vehículos al cancelar |
| 4️⃣ | `testListarVehiculosDisponibles` | 2 pts | Filtrado correcto de vehículos disponibles |
| 5️⃣ | `testClienteEsUnaPersona` | 2 pts | Herencia correctamente implementada |
| 6️⃣ | `testCalcularCostoTotalAlquiler` | 2 pts | Cálculo correcto del costo del alquiler |
| 7️⃣ | `testRegistrarMultiplesClientes` | 2 pts | Gestión de múltiples clientes |
| 8️⃣ | `testAlquilarVehiculoNoDisponible` | 2 pts | Validación de vehículos no disponibles |
| 9️⃣ | `testMostrarInformacionCliente` | 2 pts | Implementación del polimorfismo |
| 🔟 | `testListarAlquileresActivos` | 2 pts | Gestión de la lista de alquileres |

### 📈 Escala de Calificación

- 🏆 **18-20 puntos** (90-100%): Excelente - Implementación completa y correcta
- 🎉 **14-17 puntos** (70-89%): Muy Bien - Buen dominio de los conceptos
- 👍 **10-13 puntos** (50-69%): Aprobado - Cumple lo mínimo requerido
- ⚠️ **0-9 puntos** (0-49%): Insuficiente - Revisar conceptos fundamentales

---

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── ve/
│           └── edu/
│               └── ucab/
│                   └── lab/
│                       ├── Persona.java          (Por implementar)
│                       ├── Cliente.java          (Por implementar)
│                       ├── Vehiculo.java         (Por implementar)
│                       ├── Alquiler.java         (Por implementar)
│                       ├── Agencia.java          (Por implementar)
│                       └── Concesionario.java    (Opcional - para tus pruebas)
└── test/
    └── java/
        └── ve/
            └── edu/
                └── ucab/
                    └── lab/
                        └── AgenciaTest.java      (YA PROPORCIONADO - NO MODIFICAR)
```

---

## 🚀 Cómo Empezar

### Paso 1: Acepta la Asignación de GitHub Classroom
- Haz clic en el enlace proporcionado por tu profesor
- Acepta la asignación
- GitHub creará automáticamente tu repositorio personal

### Paso 2: Clona tu Repositorio
```bash
git clone <url-de-tu-repositorio>
cd <nombre-del-repositorio>
```

### Paso 3: Abre el Proyecto en tu IDE
- **IntelliJ IDEA**: `File → Open → Seleccionar carpeta del proyecto`
- **Eclipse**: `File → Import → Existing Maven Projects`
- **VS Code**: `File → Open Folder`

### Paso 4: Analiza las Pruebas Unitarias
Abre el archivo `AgenciaTest.java` y **lee cuidadosamente cada prueba**. Las pruebas te indican:
- Qué métodos necesitas implementar
- Qué parámetros reciben esos métodos
- Qué deben retornar
- Qué comportamiento se espera

💡 **Tip**: Las pruebas son tu mejor guía. Lee el código de cada `@Test` para entender qué se espera.

### Paso 5: Implementa las Clases
Crea las clases siguiendo los requerimientos descritos arriba. Deduce la estructura observando cómo se usan en las pruebas.

### Paso 6: Prueba Localmente (Opcional pero Recomendado)
```bash
mvn clean test
```
Esto ejecutará las pruebas en tu máquina antes de hacer push.

### Paso 7: Sube tu Código
```bash
git add .
git commit -m "Implementación del sistema de alquiler"
git push origin main
```

### Paso 8: Revisa tu Calificación en GitHub
1. Ve a tu repositorio en GitHub
2. Haz clic en la pestaña **Actions**
3. Selecciona el workflow más reciente
4. Revisa el **resumen de calificación** al final

---

## 📊 Cómo Interpretar los Resultados

Después de hacer `push`, GitHub Actions ejecutará automáticamente las pruebas. Verás algo como esto:

```
📊 RESUMEN DE CALIFICACIÓN

| # | Prueba                          | Puntos |
|---|---------------------------------|--------|
| 1 | Vehículo Ya Alquilado          | 2/2 ✅ |
| 2 | Vehículo Disponible            | 2/2 ✅ |
| 3 | Cancelar Alquiler              | 0/2 ❌ |
| 4 | Listar Vehículos Disponibles   | 2/2 ✅ |
| 5 | Cliente es Persona             | 2/2 ✅ |
| 6 | Calcular Costo Total           | 0/2 ❌ |
| 7 | Múltiples Clientes             | 2/2 ✅ |
| 8 | Vehículo No Disponible         | 2/2 ✅ |
| 9 | Mostrar Información            | 0/2 ❌ |
| 10| Alquileres Activos             | 2/2 ✅ |

🟡 ⚠️ CALIFICACIÓN FINAL: 14/20 puntos (70%)
> Aprobado, pero hay margen de mejora
```

### 💡 Recomendaciones al Ver los Resultados

Si una prueba **falla (❌)**:
1. Lee el nombre de la prueba para identificar qué funcionalidad tiene problemas
2. Abre `AgenciaTest.java` y busca esa prueba específica
3. Lee el código de la prueba para entender qué espera
4. Revisa tu implementación y compárala con lo que la prueba necesita
5. Corrige tu código y vuelve a hacer `push`

---

## 💡 Consejos para Aprobar

### ✅ Estrategia Recomendada

1. **Empieza por lo básico**: Implementa primero las clases simples (`Vehiculo`, `Persona`)
2. **Continúa con la herencia**: Implementa `Cliente` que hereda de `Persona`
3. **Implementa la lógica de negocio**: La clase `Agencia` es la más compleja, déjala para el final
4. **Lee las pruebas**: Cada prueba te da pistas sobre cómo debe funcionar el código
5. **Itera**: Haz push frecuentemente y revisa qué pruebas pasan/fallan

### 🔍 Preguntas Clave para Guiarte

Cuando implementes cada clase, pregúntate:

- ¿Qué atributos necesita esta clase? (Las pruebas te lo muestran)
- ¿Qué métodos se llaman sobre objetos de esta clase en las pruebas?
- ¿Qué parámetros reciben esos métodos?
- ¿Qué retornan esos métodos?
- ¿Qué validaciones debo hacer antes de ejecutar una operación?

### ⚠️ Errores Comunes a Evitar

❌ Atributos públicos (viola encapsulamiento)
❌ No validar si un vehículo está disponible antes de alquilarlo
❌ No actualizar el estado del vehículo después de un alquiler
❌ Olvidar que `Cliente` debe heredar de `Persona`
❌ No implementar el método abstracto en la clase hija
❌ Modificar el archivo `AgenciaTest.java` (esto invalidará tu evaluación)

---

## 🔬 Entendiendo las Pruebas Unitarias

Las pruebas unitarias son código que **verifica que tu código funciona correctamente**. Ejemplo:

```java
@Test
public void testAlquilarVehiculoDisponible() {
    agencia.realizarAlquiler(cliente, vehiculo, "2024-11-01", "2024-11-05");
    assertFalse(vehiculo.isDisponible(), 
        "El vehículo debería estar marcado como no disponible después del alquiler");
}
```

**¿Qué hace esta prueba?**
1. Realiza un alquiler
2. Verifica que el vehículo ahora esté marcado como NO disponible
3. Si tu código no actualiza el estado → la prueba **falla** ❌

**Tu trabajo**: Implementar `realizarAlquiler()` de manera que actualice correctamente el estado.

---

## 📖 Recursos de Apoyo

### Para Entender las Pruebas:
- Lee cada método `@Test` en `AgenciaTest.java`
- Observa qué métodos se llaman y con qué parámetros
- Nota qué se está verificando con `assertEquals`, `assertTrue`, `assertFalse`, etc.

### Para Implementar:
- Consulta la documentación de Java sobre listas: `ArrayList`
- Repasa conceptos de POO: herencia, clases abstractas, métodos abstractos
- Revisa el material de clase sobre encapsulamiento

### Comandos Útiles:
```bash
# Ver el estado de git
git status

# Ver las pruebas localmente
mvn test

# Ver solo una prueba específica
mvn test -Dtest=AgenciaTest#testAlquilarVehiculoDisponible

# Compilar el proyecto
mvn compile
```

---

## 🚫 Reglas Importantes

- ❌ **NO modifiques** el archivo `AgenciaTest.java`
- ❌ **NO modifiques** el archivo `.github/workflows/classroom.yml`
- ❌ **NO uses** librerías externas no permitidas
- ✅ **Puedes** hacer `push` tantas veces como necesites
- ✅ **Puedes** crear clases adicionales si lo consideras necesario
- ✅ **Puedes** agregar métodos privados auxiliares en tus clases

---

## 📅 Información de Entrega

**Fecha límite:** [Insertar fecha aquí]

**Método de entrega:**
- Tu código debe estar en tu repositorio de GitHub Classroom
- La calificación se genera automáticamente al hacer `push`
- Puedes hacer múltiples intentos hasta la fecha límite
- Solo se considerará la última calificación obtenida

---

## 🆘 ¿Necesitas Ayuda?

### Si tienes problemas:
1. **Revisa las pruebas**: Son tu mejor documentación
2. **Ejecuta localmente**: `mvn test` te dará más detalles de los errores
3. **Lee los mensajes de error**: Te indican qué está fallando
4. **Consulta el material de clase**: Sobre POO, herencia, listas, etc.
5. **Pregunta a tu profesor o asistente**: En horario de consulta

### Horarios de Consulta:
**Profesor:** [Nombre]  
**Email:** [email@universidad.edu]  
**Horario:** [Días y horas]

---

## 🎯 ¡Comienza tu Implementación!

Recuerda: **Las pruebas son tu guía**. Léelas, entiéndelas y úsalas para deducir cómo debe ser tu implementación.

**¡Éxito en tu laboratorio! 💻**

---

### 📝 Notas Finales

- El objetivo es que **aprendas a leer y entender pruebas unitarias**
- Las pruebas definen el **contrato** que tu código debe cumplir
- No copies código sin entenderlo - tu profesor evaluará la calidad
- La práctica hace al maestro - intenta, falla, aprende, mejora

**¡Manos a la obra! 🚀**