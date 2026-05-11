# FastFood Delivery - Microservicio de Gestión de Delivery

## Descripción
Microservicio REST desarrollado con Spring Boot para la gestión de pedidos de comida rápida. Permite administrar el flujo completo desde la creación de una orden hasta su entrega, incluyendo gestión de usuarios, restaurantes, catálogo de productos, carritos, pagos y deliveries.

---

## Equipo 7
| Nombre | Rol |
|---|---|
| Dangelo Rojas | Líder del proyecto |
| Francisco Mardones | Desarrollador Backend |
| Victor Espinoza | Desarrollador Backend |

---

## Tecnologías
- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- MySQL 8.4.3
- Hibernate 7
- Lombok
- Maven

---

## Funcionalidades Implementadas
- CRUD de Regiones y Comunas
- CRUD de Usuarios y Direcciones
- CRUD de Restaurantes y Catálogo de productos
- CRUD de Promociones
- Gestión de Carritos y Carrito Items
- Gestión de Conductores con disponibilidad
- Gestión de Órdenes con cambio de estado
- Gestión de Pagos con aprobación automática
- Gestión de Deliveries con cambio de estado
- Manejo global de excepciones
- DTOs para todas las entidades

---

## Requisitos Previos
- Java 21+
- MySQL 8+
- Maven 3.8+
- Laragon o cualquier servidor MySQL local

---

## Pasos para Ejecutar

**1. Clonar el repositorio:**
```bash
git clone https://github.com/Dangelo-Rojas/FastFood-Delivery-Fullstack.git
cd FastFood-Delivery-Fullstack
```

**2. Configurar la base de datos en `src/main/resources/application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_fastfood?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

**3. Ejecutar el proyecto:**
```bash
./mvnw spring-boot:run
```

**4. La API estará disponible en:**
```
http://localhost:8080/api/v1
```

---

## Endpoints Principales

| Método | Endpoint | Descripción |
|---|---|---|
| GET/POST | `/api/v1/regiones` | Gestión de regiones |
| GET/POST | `/api/v1/comunas` | Gestión de comunas |
| GET/POST | `/api/v1/usuarios` | Gestión de usuarios |
| GET/POST | `/api/v1/direcciones` | Gestión de direcciones |
| GET/POST | `/api/v1/restaurantes` | Gestión de restaurantes |
| GET/POST | `/api/v1/catalogos` | Gestión de productos |
| GET/POST | `/api/v1/carritos` | Gestión de carritos |
| GET/POST | `/api/v1/carrito-items` | Gestión de items del carrito |
| GET/POST | `/api/v1/conductores` | Gestión de conductores |
| GET/POST | `/api/v1/ordenes` | Gestión de órdenes |
| GET/POST | `/api/v1/pagos` | Gestión de pagos |
| GET/POST | `/api/v1/deliveries` | Gestión de deliveries |
