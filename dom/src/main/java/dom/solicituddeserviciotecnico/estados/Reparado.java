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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idReparado" )
@Uniques({ @Unique(name = "reparadoUnique" , members = { "idReparado" } ) })
@ObjectType("REPARADO")
public class Reparado implements IEstadoSolicitudDeServicioTecnico {

	private SolicitudServicioTecnico solicitud;
	
	public Reparado(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}

	public String getNombre()
	{
		return "REPARADO";
	}
	@Hidden
	@Override
	public boolean ocultarImprimir() {
	
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarSolucion() {
	
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
	
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarFechaDeSolucion() {
	
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarTecnicoAsignado() {
	
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarAsignarTecnico() {
	
		return true;
	}
	@Hidden
	@Override
	public void recibirDeServicioTecnico() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void enviarAServicioTecnico() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void avisarNetbookReparada() {
		throw new UnsupportedOperationException("No impletandado todavía...");
	}
	@Hidden
	@Override
	public void finalizarSolicitud() {
		this.solicitud.setEstado(this.solicitud.getEstadoCerrado());
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		return false;
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
