package dom.solicituddeserviciotecnico.estados;

import java.util.Date;

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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idSolicitado" )
@Uniques({ @Unique(name = "solicitadoUnique" , members = { "idSolicitado" } ) })
@ObjectType("SOLICITADO")
public class Solicitado implements IEstadoSolicitudDeServicioTecnico {

	
	/**
	 * titulo del estado
	 * return SOLICITADO
	 */
	public String getNombre()
	{
		return "SOLICITADO";
	}
	
	@Override
	@Hidden
	public boolean ocultarImprimir() {
		return true;
	}

	private SolicitudServicioTecnico solicitud;
	
	public Solicitado(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}

	
	
	@javax.jdo.annotations.Column(allowsNull="true")
	private SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}
	
	/**
	 * cuando se solicita se oculta la solucion
	 */
	@Override
	@Hidden
	public boolean ocultarSolucion() {
		return true;
	}

	/**
	 *  cuando se solicita se oculta para enviar mail que esta lista 
	 *  
	 */
	@Override
	@Hidden
	public boolean ocultarAvisarPorMailQueEstaLista() {
		return true;
	}
	/**
	 * se oculta la fecha de solucion porque todabia no esta reparado
	 */
	@Override
	@Hidden
	public boolean ocultarFechaDeSolucion() {
		return true;
	}
	
	@Hidden
	@Override
	public boolean ocultarTecnicoAsignado() {
		return true;
	}
	
	/**
	 * recibirDeServicioTecnico
	 * no esta implementado todavia
	 */
	@Hidden
	@Override
	public void recibirDeServicioTecnico() {
		
		throw new UnsupportedOperationException("No impletandado todavía...");
	}
	/**
	 * eviarAlServicioTecnico
	 * no esta implementado todavia
	 */
	@Hidden
	@Override
	public void enviarAServicioTecnico() {
		throw new UnsupportedOperationException("No impletandado todavía...");
	}
	@Hidden
	@Override
	public void avisarNetbookReparada(String solucion,Date fechaDeSolucion) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void finalizarSolicitud() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		this.getSolicitud().setTecnicoAsignado(tecnico);
		this.getSolicitud().setEstado(this.solicitud.getEstadoAceptado());
		
	}
	
	@Override
	public boolean ocultarAsignarTecnico() {
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarEnviarAServicioTecnico() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarRecibirDelServicioTecnico() {
		return true;
	}

	
	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
