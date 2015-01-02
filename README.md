Estudiantina
============
Estudiantina es un software para la administración y gestion de Netbooks Que entrega el Gobierno de La Republica Argentina.

El framework utilizado es Apache Isis.

### Mails de Contacto ###
```
matias@informaticos.com
icia.tjl.nqn@gmail.com
andresrabovich@gmail.com
```


### Screen Shots ###

![](https://github.com/Estudiantina/Estudiantina/blob/develop/screenshots/1.jpg)

![](https://github.com/Estudiantina/Estudiantina/blob/develop/screenshots/2.jpg)

![](https://github.com/Estudiantina/Estudiantina/blob/develop/screenshots/3.jpg)

License
========

Estudiantina is licensed under [GNU GPLv2](http://www.gnu.org/licenses/gpl-2.0.html).

#### PREREQUISITOS ####

* Java 1.5 o superior.

* Maven
Maven instalacion en RedHat y derivados
```
yum install maven -y

```
or
```
yum install maven2 -y
```

descargar el addon de los mapas de google
```
git clone https://github.com/isisaddons/isis-wicket-gmap3.git
```


Instalacion y Configuracion 
===========================

Para instalar Estudiantina lo primero que hay que realizar es configurar una base de datos
de postgresql con usuario por defecto isis y password isis.
en caso de que tu usuario y password no sea isis podrias cambiarlo por otro
en el archivo Estudiantina /webapp/src/main/webapp/WEB-INF/shiro.ini

una vez echo esto podemos proceder con la compilacion y ejecuccion

para compilar y luego instalar utilizaremos el comando:
```
mvn clean install
```
para correr podemos utilizar el siguiente comando

```
mvn antrun:run
```
en el caso de que no poder correr con el comando anterior 
tambien podemos correrlo con el comando

```
cd webapp
mvn jetty:run
```
