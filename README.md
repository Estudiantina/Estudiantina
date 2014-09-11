Estudiantina
========
Estudiantina es un software para la administración y gestion de Netbooks Que entrega el Gobierno de La Republica Argentina.

El framework utilizado es Apache Isis.

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

Generar el primer usuario desde la base de datos
================================================
En posgres para generar el primer usuario en la base de datos
se realiza de la siguiente manera


Agregar una Provincia

```
INSERT INTO "Provincia"(
            "Provincia_ID", "nombreProvincia")
    VALUES (1, 'Neuquen');
```

Agregar un Departamento

```
INSERT INTO "Departamento"(
            "Departamento_ID", "nombreDepartamento", "provincia_Provincia_ID_OID")
    VALUES (1, 'Confluencia', 1);
```

Agregar una nueva Localidad

```
INSERT INTO "Localidad"(
            "codigoPostal", "departamento_Departamento_ID_OID", 
            localidad)
    VALUES ('8300', 1 , 
            'Neuquen');
```

Agregar Nuevo Establecimiento

```
INSERT INTO "Establecimiento"(
            "Establecimiento_ID", cue, direccion, "distritoEscolar", email, 
            "localidad_Localidad_ID_OID", nombre, telefono)
    VALUES (1, 222, 'santa fe 332', 'DISTRITO REGIONAL I CONFLUENCIA', 'distrito1@neuquen.gov.ar', 
            1, 'ifes', '444444');
```

Agregar nueva Persona

```
INSERT INTO "Persona"(
            "Persona_ID", apellido, cuil, domicilio, email, "establecimiento_Establecimiento_ID_OID", 
            "fechaNacimiento", "localidad_Localidad_ID_OID", location, nombre, 
            "telefonoCelular", "telefonoFijo", "localizacion_Persona_ID_OID", 
            "localizacion_INTEGER_IDX",discriminator)
VALUES (1, 'pepe', '33333', 'peru 81', 'mario@herivan.com', 1, 
            '11-06-1970', 1, null, 'pepe', 
            '444444',1 , 1, 
            1,'dom.Tecnico.Tecnico');
```
Asignar la persona como un Tecnico
```
INSERT INTO "Tecnico"(
            "Tecnico_ID")
    VALUES (1);
```

Agregar Nuevo Rol
```
INSERT INTO "Rol"(
            "Rol_ID", rol)
    VALUES (1, 'usuario_adiministrador');
```

Agrego los permisos de usuario administrados 
```
INSERT INTO "Permisos"(
            "Permisos_ID", permiso, "listaPermiso_Rol_ID_OID", "listaPermiso_INTEGER_IDX")
    VALUES (1, '*', 1, 1);
```
Creamos un usuario con nombre de usuario "admin" y contraseña "123456" en sha256

```
INSERT INTO "Login"(
            "Login_ID", password, "persona_Persona_ID_OID", "rol_Rol_ID_OID", 
            usuario)
    VALUES (1, '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 1, 
            'admin');
```





