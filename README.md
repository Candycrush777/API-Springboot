# API Spring Boot

Este proyecto contiene una API desarrollada con Spring Boot y un frontend aen Angular 19.

## Requisitos

- Java 17+
- Maven 3.8+
- Node.js 16+
- npm o yarn

## Levantar el Backend

1. Navega a la carpeta del backend:
    ```bash
    cd agenda-hexagonal
    ```
2. Compila y ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```
    La API estará disponible en `http://localhost:8080`.

## Levantar el Frontend

1. Navega a la carpeta del frontend:
    ```bash
    cd agenda_front
    ```
2. Instala las dependencias:
    ```bash
    npm install
    ```
3. Inicia la aplicación:
    ```bash
    npm start
    ```
    El frontend estará disponible en `http://localhost:4200`.

## Estructura del Proyecto

```
/backend   # Código fuente de la API Spring Boot
/frontend  # Código fuente del frontend (Angular)
```

## Notas

- Configura las variables de entorno según sea necesario.
- Consulta la documentación interna de cada carpeta para detalles adicionales.