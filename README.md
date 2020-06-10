# Guia-08-EJ1

Ejercicio Nro. 1: 

La empresa “PeliSeri” de distribución de contenidos audiovisuales por plataformas on-line vía  streaming  requiere  el  desarrollo  de  una  aplicación  JAVA  con  OO  y  MVC  para  la 
administración de los mismos conformados tanto por películas como por series.

Ante una nueva película o serie, o bien para la modificación de una existente, la empresa 
recibe  la  información  correspondiente  en  archivos  JSON,  con  el  nombre,  año  en  que  fue 
filmada,  género,  sinopsis  y  nombre  y  apellido  de  sus  actores  y  duración  expresada en 
minutos para la película; y nombre, género, nombre y apellido de sus actores, temporada, 
episodio y sinopsis para la serie.

Una vez publicado el material, la información se guarda junto con la fecha de publicación 
(fecha del día) y código en el archivo Audiovisuales.txt de ancho fijo y sus correspondientes 
actores en AudiovisualesActores.txt separado por TAB.

Luego de reproducir una película o una serie, el suscriptor tiene la posibilidad de recomendar 
la misma, basado en la calificación de una a cinco estrellas (siendo esta última, la máxima 
puntuación) indicando con un breve comentario del motivo de la calificación propuesta.
Estas calificaciones son ingresadas manualmente por el usuario de la aplicación a desarrollar, quedando almacenadas en el archivo Calificaciones.txt con sus datos separadas 
por “;” (punto y coma) incluyendo la fecha en formato dd/mm/aaaa de la calificación.

A todos los suscriptores jóvenes (menores de 35 años), se les recomienda la temporada completa de la serie con mejor calificación promedio durante los últimos 3 meses, evaluada por quienes cumplan con el mismo rango de edad, mediante la generación de un archivo JSON. Este archivo incluye el nombre de la empresa, nombre de la serie, género, nómina de 
actores, sinopsis, temporada, cantidad de episodios y su calificación. 

Para cada uno de los géneros existentes, la película con mejor calificación obtenida en el 
último mes es recomendada a todos los suscriptores mayores de 55 años, mediante otro 
archivo JSON con la estructura similar a la de las series.

La empresa debe abonar un derecho anual por las series y películas publicadas en el último 
mes, cuyo valor ingresado por teclado es diferente entre una película y un episodio de una 
serie. Por tratarse de un derecho anual, también se abonan los derechos de aquellas publicadas un año atrás. En estos casos,  las películas solo abonan el 60% del valor ingresado y el 30% por cada episodio si la temporada si tiene más de 12 episodios o el 45%, si tiene menos. Estos derechos se pagan semanalmente, generando un cronograma 
de pagos con fecha de pago, nombre de la publicación, fecha de la publicación, monto a 
abonar, siendo el primer pago, el primer día del mes siguiente. 

Este cronograma queda guardado en el archivo llamado “CronogramaPagos” junto con el mes (2 dígitos), año de 
generación (4 dígitos) y luego ".txt". Incluir, en el archivo, la cantidad de publicaciones 
afectadas y el total a abonar. Las fechas incluidas deben estar formateadas como dd/mm/aaaa.

Del relevamiento desarrollado surgió la necesidad de contar con la siguiente información:

● Para cada uno de los distintos géneros, nombre de la serie, cantidad total de temporadas 
y cantidad de actores, ordenadas según la cantidad total de temporadas en forma descendente.

● Nombre y apellido de los suscriptores mayores de 60 años que nunca hayan calificado 
una película.

● Apellido y nombre de los actores (ordenados por ambos), duración de una película, fecha 
de  publicación  y  evaluaciones  (fecha,  nombre  del  suscriptor  y  calificación)  de  una 
película seleccionada al azar. 

● Código  y  nombre  de  serie,  temporada  y  episodio  de  aquellas  que  no  hayan  sido 
calificadas por ningún suscriptor masculino adulto menor de 45 años. 

● Cantidad de actores que solamente participan de series o películas de un mismo género. 

● Nombre y género de las publicaciones cuyo monto abonado en concepto de renovación 
de derechos de publicación supere en un 10% el valor ingresado como argumento de la 
publicación y todas sus calificaciones del último año sean menores a 3 estrellas. 

● Apellido y nombre de las actrices que hayan filmado una película en los últimos 6 meses. 

La empresa cuenta con dos archivos. Uno con todos los suscriptores, Suscriptores.txt de 
ancho fijo, con su código, nombre, apellido, número de documento, fecha de nacimiento y 
sexo. Y otro con la nómina de géneros, formado por el código y descripción. De la base de 
datos genérica de actores, la empresa acceda a la información de los mismos (no es necesario codificar su acceso). 

Desarrollar  implementando  polimorfismo,  métodos  polimórficos,  interfaces,  uso  de 
colecciones con interfaces de soporte, ciclos avanzados y operador condicional. 
Junto  con  los  programas  fuentes  desarrollados,  incluir  los  archivos  TXT  de  entrada  y  de 
salida generados y el diagrama de clases en formato jpg.
