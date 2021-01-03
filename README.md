# OperacionFuegoQuasar
Proyecto Operación Fuego Quasar para mercado libre.

El proyecto Operación Fuego Quasar es una aplicación que resuelve el problema planteado por Mercado Libre en donde se busca proveer una serie de servicios, para solventar el problema de los tres satélites que reciben un mensaje de auxilio de un emisor cuya ubicación en el espacio es desconocida. La finalidad de los tres servicios es encontrar la ubicación exacta del emisor del mensaje, y a su vez descifrar el mensaje ultra secreto enviado por este a cada uno de los satélites ubicados en posiciones fijas.

Los tres satélites se encuentran ubicados inicialmente en las siguientes posiciones del plano cartesiano:

● Kenobi: [-500, -200] 
● Skywalker: [100, -100]
● Sato: [500, 100]

Sin embargo, el algoritmo implementado para dar solución a este problema, permite editar dichas posiciones en caso de que se replanteara la posición de alguno(s) de esto(s) satélites. Dicha posición se define a través de un archivo json ubicado dentro del proyecto en la ruta /OperacionFuegoQuasar/src/main/resources/dataSatelites.json.

Para obtener más información acerca del problema planteado por favor ver el documento:

https://drive.google.com/file/d/1k6bttlSIO9JKN328CAXuSavX8ISXEpMF/view?usp=sharing

Para solventar este problema se implementaron dos algoritmos, uno para hallar el posicionamiento de la nave emisora del mensaje y otro para descifrar el mensaje secreto.

Este proyecto fue construido en lenguaje Java - versión 1.8, haciendo uso del framework Spring Boot 2.4. Se utilizó Maven como herramienta de gestión y construcción del proyecto, así como para la definición de las dependencias del mismo.

Cada una de las clases y métodos que hacen parte del proyecto están documentadas a través de Javadoc para lograr un entendimiento más detallado del funcionamiento interno de la aplicación. 

## Descripción del Algoritmo de Posicionamiento

La finalidad de este algoritmo es determinar la posición del emisor del mensaje de auxilio, conociendo únicamente las siguientes variables:

1. Posición fija de cada uno de los 3 satélites:
2. Distancia de cada satélite al emisor del mensaje.

Este problema se solventó a través del planteamiento y solución de ecuaciones de la circunferencia, ya que a través de la información mencionada anteriormente, podemos determinar dicha ecuación para cada uno de los tres satélites: 

![img](https://i.stack.imgur.com/ClR0b.jpg)

En la imagen anterior podemos ver un ejemplo del planteamiento de la solución, en donde cada uno de los tres satélites ubicados en puntos fijos, cuentan con un radio diferente que determina la distancia de cada uno al emisor del mensaje (Punto de intersección entre las tres circunferencias). Gracias a esta información podemos plantear tres ecuaciones y proceder a resolverlas.

Básicamente el algoritmo tiene los siguientes pasos:

1. Realizar validaciones de entrada: Valida que la información de entrada se encuentre completa y consistente para poder resolver el problema.
2. Registra la información enviada por cada satélite en memoria para iniciar con la solución del problema.
3. Plantear las 3 ecuaciones de la circunferencia, una para cada satélite. Dichas ecuaciones tienen la forma (x - a)^2 + (y - b)^2 = r^2, en donde (a, b) es la posición del satélite y r respresenta la distancia al emisor del mensaje.
4. Obtener los puntos de intersección entre los dos primeros satélites:
* 4.1 Restar las dos ecuaciones para obtener una ecuación de la recta de la forma X = aY + b (Eje Radical).
* 4.2 Despejar una de las variables de esta ecuación (X o Y) y reemplazarla en una de las dos ecuaciones de la circunferencia para poder obtener una ecuación cuadrática en terminos de una sola variable, y así poderla resolver. Esta ecuación cuadrática se expresa con la forma aX^2 + bX + c = 0
* 4.3 Resolver la ecuación cuadrática para obtener los dos posibles valores que satisfacen la ecuación.
* 4.4 Para cada una de estas dos ecuaciones de la circunferencia, encontrar la ecuación cuadrática reemplazando los valores encontrados en el punto 4.1. Y resolverla para hallar las posibles soluciones. La solución correcta será la que se satisfaga en ambas ecuaciones.  
* 4.5 Devolver un mapa que contenga los dos puntos de intersección para estas dos circunferencias.

5. Reemplazar ambas posiciones calculadas en el punto 4, en la tercera ecuación de la circunferencia, para determinar si se satisface la igualdad. El punto que satisfaga la igualdad representará la posición del emisor del mensaje.

## Descripción del Algoritmo que Descifra el Mensaje Secreto

1. Realizar validaciones de entrada: Valida que la información de entrada se encuentre completa y consistente para poder resolver el problema.
2. Registra la información enviada por cada satélite en memoria para iniciar con la solución del problema.
3. Llenar en memoria (Mapa <Posición, Mensaje>) la información recibida por cada uno de los tres satélites, se irá llenando el mapa siempre y cuando no se sobreescriba una posición con valor nulo a medida que se iteran los diferentes satélites.
4. Si al final del llenado del mapa se encuantra una posición vacía o nula, se enviará un mensaje de excepción indicando que no fue posible descifrar el mensaje secreto.
5. Iterar el mapa creado anteriormente e ir llenando una variable con el mensaje secreto.
6. Retornar la cadena que contiene el mensaje secreto.

## Endpoints de la Aplicación

La aplicación cuenta con 3 endpoints que proveen las distintas funcionalidades que resuelven el problema, dichos servicios se puede consumir una vez iniciada la aplicación en el puerto por defecto por donde se escuchan las aplicaciones de Springboot a ravés de la URI http://localhost:8080:

### /topsecret

Servicio de tipo POST que recibe la información de los trés satélites (nombre del satélite, distancia al emisor del mensaje y arreglo con el mensaje obtenido por dicho satélite), y retorna la posición (x,y) del emisor y una cadena String con el mensaje descifrado.

Para ver un ejemplo de este consumo y realizar dicho consumo, se puede realizar a través de la aplicación Postman haciendo uso del request diseñado para este fin. Dicho request se puede obtener dentro del proyecto en la ruta /OperacionFuegoQuasar/src/main/resources/OperacionFuegoQuasar.postman_collection.json o se puede descargar de la siguiente dirección: https://drive.google.com/file/d/1GqUuCtqRw_N3Ma9_DzFXWnUa1eqYRxr_/view?usp=sharing

### /topsecret_split/{satelliteName}

Servicio de tipo POST que registra en la aplicación la información de uno de los tres satélites de manera independiente, de tal manera que también se pueda ir registrando uno a uno los siguientes datos para resolver el problema:

Cuerpo del mensaje (Body):

* Nombre del satélite
* Distancia al emisor del mensaje
* Arreglo con el mensaje obtenido por dicho satélite

Parámetro de tipo Path:

* satelliteName: Contiene el nombre del satélite a registrar.

Este request también se puede obtener dentro del proyecto en la ruta /OperacionFuegoQuasar/src/main/resources/OperacionFuegoQuasar.postman_collection.json o se puede descargar de la siguiente dirección: https://drive.google.com/file/d/1GqUuCtqRw_N3Ma9_DzFXWnUa1eqYRxr_/view?usp=sharing

### /topsecret_split

Servicio de tipo GET que calcula la posición del emisor del mensaje y descifra el mensaje secreto, siempre y cuando ya se hayan registrado correctamente los datos de cada satélite. Si aún falta información por registrar, el aplicativo retorna un mensaje de error indicando lo sucedido.

Este servicio no contiene parámetros de entrada y retorna la posición (x,y) y el mensaje descifrado siempre y cuando se haya podido hacer con la información que se tiene registrada.

Se puede obtener este request de ejemplo en la ruta /OperacionFuegoQuasar/src/main/resources/OperacionFuegoQuasar.postman_collection.json o se puede descargar de la siguiente dirección: https://drive.google.com/file/d/1GqUuCtqRw_N3Ma9_DzFXWnUa1eqYRxr_/view?usp=sharing


Los tres servicios mencionados anteriormente cuentan con sus respectivas validaciones de negocio para evitar inconsistencias en la información al momento de procesar los datos, y fueron construidos con buenas prácticas de diseño y codificación para garantizar que la solución sea extensible a futuro y fácil de modificar si se llegara a requerir.

## Ejecución de la Aplicación

### Ejecución en Entorno Local

Para ejecutar la aplicación en entorno local se puede realizar cargando el proyecto a través del IDE de desarrollo o instalando Maven en el equipo, para posteriormente instalar la aplicación y ejecutarla.

#### Ejecución a través del IDE:

Para ejecutar la aplicación a través del IDE de desarrollo se debe considerar tener el siguiente software instalado en el equipo:

* Spring Tool Suite 4 - Se puede instalar desde https://spring.io/tools
* JDK 8 - https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html

Una vez instalado todo lo mencionado, se debe descargar el código fuente del proyecto ubicado en https://github.com/jonathanm1007/OperacionFuegoQuasar.
Importarlo a través del IDE de desarrollo, seleccionar click derecho sobre el mismo y seleccionar Run As -> Spring Boot App. El aplicativo se encargará de descargar las dependencias necesarias para la ejecución y comenzará a escuchar peticiones a través de la URI http://localhost:8080

#### Ejecución a través de Maven

Se debe descargar el código fuente de la ruta https://github.com/jonathanm1007/OperacionFuegoQuasar

Verificar que se cuente con el siguiente software instalado en la máquina:

* Java Runtime 8 - https://www.java.com/es/download/ie_manual.jsp
* Maven - Se puede instalar basándose en la siguiente guía: https://maven.apache.org/guides/getting-started/windows-prerequisites.html

Se debe construir el proyecto a través de una terminal del sistema con el comando mvn install ubicándonos en la carpeta raíz del proyecto.
Ubicar el archivo ejecutable de la aplicación, el cual estará en la ruta \OperacionFuegoQuasar\target\OperacionFuegoQuasar-0.0.1-SNAPSHOT.jar, para posteriormente ejecutar el comando java -jar OperacionFuegoQuasar-0.0.1-SNAPSHOT.jar

#### Consumo de los servicios publicados en Cloud

EL software realizado también fue publicado en la nube para proceder 
