# Mutantes

El presente desafío se ha desarrollado utilizando el lenguaje de programación java y se han tomado las siguientes consideraciones:
Funcionamineto 
##Funcionamiento 
En .JSON enviar un vector de String con el dna a validar 
Se hace la validacion de que este registro no exista en Bases de datos , si existe retornara un 400 
Si no existe este registro continura a realizar la validacion de si es mutante 
Despues de realizada la validacion guardara la informacion del Adn 

Si es mutante retornara un 200 , si no es mutante retornara un 403 

## Herramientas utilizadas
- Spring boot
- Spring data jpa
- Lombook: Permite reducir el código escrito auto generando getters y setters
- Swagger: Genera la documentación de APIs 
- Postman: Permite realizar las pruebas de programador de los end-point
- Maven: Para la gestión de dependencias y construcción del aplicativo



## Despliegue
El presente proyecto se encuentra desplegado haciendo uso de Free Dynos y Heroku Postgres en la siguiente url:
https://mutantes-rest.herokuapp.com/api/


## Documentación
La documentación de los endpoint se puede encontrar en el siguiente enlace
https://mutantes-rest.herokuapp.com/api/swagger-ui.html#


## Reporte de cobertura
Se ha realizado el 90 % de cobertura

##Para alguna duda en el siguente documento esta algunos anexos pantallazos de la prueba
https://github.com/ferney23/Mutantes/blob/main/Examen%20Mercadolibre%20%20-%20Mutantes.docx



## Ejecución
En el repositorio se encuentra la collecion de postanm para la ejecucion de los end point 
https://github.com/ferney23/Mutantes/blob/main/MeliMutantes.postman_collection.json 
