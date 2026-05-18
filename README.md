# Minimarket La Esquina - Sistema de Gestión de Inventario

Proyecto de la materia **ISWZ1103 - Programación II**.

Sistema de gestión de inventario para una pequeña empresa (minimarket), desarrollado en **Java** con interfaz de consola, aplicando los principios de **Programación Orientada a Objetos**: encapsulamiento, herencia y polimorfismo.

## Integrantes
- Nombre 1
- Nombre 2
- Nombre 3

## Estructura del proyecto

```
MinimarketLaEsquina/
└── src/
    ├── Producto.java               (Clase abstracta - padre)
    ├── ProductoPerecedero.java     (Hereda de Producto)
    ├── ProductoNoPerecedero.java   (Hereda de Producto)
    ├── Inventario.java             (Lógica del inventario)
    └── Main.java                   (Menú de consola)
```

## Principios de POO aplicados

- **Encapsulamiento:** atributos privados con getters/setters.
- **Herencia:** `ProductoPerecedero` y `ProductoNoPerecedero` extienden de `Producto`.
- **Polimorfismo:** los métodos abstractos `calcularValorTotal()` y `requiereAlerta()` se sobreescriben en las subclases. La lista `ArrayList<Producto>` almacena objetos de distintos tipos hijos.

## Funcionalidades

1. Registrar producto (perecedero o no perecedero).
2. Listar inventario completo.
3. Consultar producto por código.
4. Actualizar stock (entrada/salida).
5. Eliminar producto.
6. Ver alertas (stock bajo / próximo a vencer).
7. Reporte general de inventario.

## Cómo ejecutar

### Desde IntelliJ IDEA
1. Abrir el proyecto: `File > Open` y seleccionar la carpeta `MinimarketLaEsquina`.
2. Hacer click derecho en `Main.java` y seleccionar `Run 'Main.main()'`.

### Desde la terminal
```bash
cd MinimarketLaEsquina
javac -d out src/*.java
java -cp out Main
```

## Requisitos
- Java JDK 11 o superior.
- IntelliJ IDEA (recomendado).

## Datos de ejemplo precargados

El sistema inicia con 4 productos de ejemplo para facilitar las pruebas:
- P001 - Leche entera 1L (perecedero)
- P002 - Pan de molde (perecedero, próximo a vencer)
- N001 - Arroz 1kg (no perecedero)
- N002 - Detergente 1L (no perecedero)

## Restricciones del sistema

- Presupuesto máximo: **$5,000 USD**
- Capacidad del almacén: **1,000 unidades**
- Alerta de stock bajo: **menos de 5 unidades**
- Alerta de vencimiento: **7 días o menos**
