# Mutantes

El presente desafío se ha desarrollado utilizando el lenguaje de programación java y se han tomado las siguientes consideraciones:

## Herramientas utilizadas
- Spring boot
- Spring data jpa
- Spring web
- Jacoco: Para generar los reportes de cobertura de las pruebas unitarias
- Lombook: Permite reducir el código escrito auto generando getters y setters
- Swagger: Genera la documentación de APIs RESTful y un sandbox para probar llamadas
- Postman: Permite realizar las pruebas de programador de los end-point
- Maven: Para la gestión de dependencias y construcción del aplicativo



## Despliegue
El presente proyecto se encuentra desplegado haciendo uso de Free Dynos y Heroku Postgres en la siguiente url:
https://mutantes-rest.herokuapp.com/api/


## Documentación
La documentación de los endpoint se puede encontrar en el siguiente enlace
https://mutantes-rest.herokuapp.com/api/swagger-ui.html#


## Reporte de cobertura
Para el controlador, los servicios y la persistencia se ha realizado una cobertura del 99% mediante pruebas unitarias como se evidencia en el siguiente reporte de cobertura generado por jacoco:


## Ejecución
En el repositorio encuentra el script de ejecución de peticiones HTTP con Postman el cual tiene la URL configurada del servicio.
