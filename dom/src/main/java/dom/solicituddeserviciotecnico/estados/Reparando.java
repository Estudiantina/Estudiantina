package dom.solicituddeserviciotecnico.estados;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
import dom.tecnico.Tecnico;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idReparando" )
@Uniques({ @Unique(name = "reparandoUnique" , members = { "idReparando" } ) })
@ObjectType("REPARANDO")
public class Reparando implements IEstadoSolicitudDeServicioTecnico{

	
	public String title()
	{
		return "REPARANDO";
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
	public Reparando(SolicitudServicioTecnico solicitud) {
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
		this.solicitud.setEstadoSolicitud(this.solicitud.getEnviado());
		
	}
	/**
	 * 
	 */
	@Hidden
	@Override
	public void avisarNetbookReparada() {
		
		
	}
    @Hidden
	@Override
	public void finalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
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
		return false;
	}

	
}
