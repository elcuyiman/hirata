# Hirata Desktop

Proyecto JavaFX + MySQL para gestión básica de flota.

## Requisitos

- Java 17
- Maven 3.9+
- MySQL 8+

## Configuración de base de datos

1. Ejecuta `schema.sql` en MySQL.
2. Si usas otro usuario/contraseña, ajusta `src/main/java/com/hirata/util/DBConnection.java`.

## Ejecutar

```bash
mvn clean javafx:run
```

## Alcance actual

- Login simple (usuario/contraseña en texto plano).
- Dashboard con contadores.
- Listado y alta de camiones.
- Listado de mantenimientos.
- Registro de kilometraje solo en UI (sin persistencia).

## Próximos pasos sugeridos

- Login con BCrypt.
- CRUD completo de camiones y mantenimientos.
- Persistencia para kilometraje.
- Mejoras visuales con CSS JavaFX.
