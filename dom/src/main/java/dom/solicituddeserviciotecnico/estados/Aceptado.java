package dom.solicituddeserviciotecnico.estados;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
import dom.tecnico.Tecnico;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idAceptado" )
@Uniques({ @Unique(name = "aceptadoUnique" , members = { "idAceptado" } ) })
@ObjectType("ACEPTADO")
public class Aceptado implements IEstadoSolicitudDeServicioTecnico{

	
	public String title()
	{
		return "ACEPTADO";
	}
	
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * get de la dependencia entre el estado y la solicitud
	 * @return solicitud
	 */
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}
	/**
	 * constructor del estado
	 * @param solicitud
	 */
	public Aceptado(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}







	/**
	 * cuando se esta reparando se puede imprimir
	 * por lo tanto no se oculta.
	 */
	@Hidden
	@Override
	public boolean ocultarImprimir() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
    @Hidden
	@Override
	public boolean ocultarSolucion() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
    @Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
    @Hidden
	@Override
	public boolean ocultarFechaDeSolucion() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
    @Hidden
	@Override
	public boolean ocultarTecnicoAsignado() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
    @Hidden
	@Override
	public void recibirDeServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		
	}
    /**
     * al enviar al servicio tecnico
     * de Buenos Aires
     * pasa
     * a estado enviado
     */
	@Hidden
	@Override
	public void enviarAServicioTecnico() {
		container.informUser("se ha enviado la Netbook al servicio Tecnico de BS AS");
		this.solicitud.setEstadoSolicitud(this.solicitud.getEstadoEnviado());
		
	}
	/**
	 * 
	 */
	@Hidden
	@Override
	public void avisarNetbookReparada() {
		container.informUser("la netbook ha pasado a estar reparada");
		this.solicitud.setEstadoSolicitud(this.solicitud.getEstadoReparado());
		
	}
    @Hidden
	@Override
	public void finalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
    	container.informUser("la solicitud se ha cerrado correctamente");
    	this.solicitud.setEstadoSolicitud(this.solicitud.getEstadoCerrado());
    	
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		// TODO Apéndice de método generado automáticamente
		
	}
    @Hidden
	@Override
	public boolean ocultarAsignarTecnico() {
		// TODO Apéndice de método generado automáticamente
		return true;
	}
    @Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	@Override
	@Hidden
	public boolean ocultarEnviarAServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	@Override
	@Hidden
	public boolean ocultarRecibirDelServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		return true;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}
