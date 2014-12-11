package fixture.login;

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
		//
		//PERMISOS PARA USUARIOS ADMINISTRADORES
		//
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
		create("usuario_tecnico","dom.solicituddeserviciotecnico:SolicitudServicioTecnico:tecnicoAsignado:*",executionContext);
		
		//PERMISOS DE TECNICOS PARA NETBOOKS
		create("usuario_tecnico","repo.netbook:RepositorioNetbook:ingresarNuevaNetbookAlEstablecimiento:*",executionContext);
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
		//
		//PERMISOS PARA ALUMNOS
		//PERMISOS ALUMNO
		create("usuario_alumno","dom.alumno:Alumno:cursos:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:estadoDeAlumno:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:fechaIngreso:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:nacionalidad:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:tutor:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:EstadoDeAlumno:r",executionContext);
		create("usuario_alumno","dom.alumno:Alumno:Nacionalidad:r",executionContext);
		
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
		//dom.solicituddeserviciotecnico.estados:*:*


		//PERMISOS ALUMNO repositorio SolicitudServicioTecnico
		create("usuario_alumno","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:solicitarServicioTecnico:*",executionContext);
		create("usuario_alumno","repo.solicitudserviciotecnico:RepoSolicitudServicioTecnico:verHistorialReparaciones:*",executionContext);






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
		
		
		
		
		
		
				
		
		//
		//
		//TODO establecer permisos de 
		
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
