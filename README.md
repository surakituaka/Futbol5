Futbol5
=======

Para hacer la migracion de datos de prueba a la base de datos se debe hacer lo siguiente:

En la configuracion de la fuente del hibernate se debe cambiar: 
<!-- Creation/Update of DB -->                            <!-- Creation/Update of DB -->
<property name="hbm2ddl.auto">update</property>, por   		<property name="hbm2ddl.auto">create</property>
 
 Se debe descomentar las siguientes lineas de codigo: (Las lineas a descomentar tienen puesto un bookmark)

 Principal.java: Linea 51
 Jugador.java: Lineas 55 y 59
 Partido.java: Linea 55
 Inscripcion.java: Lineas 21 y 26
 Calificacion.java: Lineas 29, 34 y 39
