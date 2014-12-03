package fixture.login;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import repo.login.repologin;
import dom.login.Permisos;

public class PermisosFixture extends FixtureScript{

	@Override
	protected void execute(ExecutionContext executionContext) {
		
		//
		//PERMISOS PARA USUARIOS ADMINISTRADORES
		//
		create("usuario_administrador","*",executionContext);
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
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:PedirCertificado:*",executionContext);
		//PERMISOS ALUMNO Pedido de NetbookPrestada
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:PedirNetbookPrestada:*",executionContext);

		//PERMISOS ALUMNO solicitud de tramite de migracion 
		create("usuario_alumno","repo.notificaciones:RepoNotificaciones:solicitarTramiteDeMigracion:*",executionContext);
		create("usuario_alumno","dom.notificaciones:SolicitudTramiteDeMigracion:imprimir:r",executionContext);
		
		
		
		
		
		
		
		//
		//PERMISOS PARA DIRECTIVOS
		//TODO establecer permisos de directivos
		
		
		//
		//
		//TODO establecer permisos de 
		
	}

	
	private Permisos create(final String rol,String permiso,
			ExecutionContext executionContext) {
		
		return executionContext.add(this,
				repoLogin.aniadirPermiso(repoLogin.buscarRol(rol),permiso));
	}

	@javax.inject.Inject
	private repologin repoLogin;
}
