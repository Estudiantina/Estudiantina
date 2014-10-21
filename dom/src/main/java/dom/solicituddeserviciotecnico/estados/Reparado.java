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
		return true;
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
	public boolean ocultarAsignarTecnico() {
		// TODO Apéndice de método generado automáticamente
		return true;
	}
	@Hidden
	@Override
	public void recibirDeServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Hidden
	@Override
	public void enviarAServicioTecnico() {
		
		
	}
	@Hidden
	@Override
	public void avisarNetbookReparada() {
		// TODO Apéndice de método generado automáticamente
	}
	@Hidden
	@Override
	public void finalizarSolicitud() {
		this.solicitud.setEstadoSolicitud(this.solicitud.getEstadoCerrado());
		
		
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarEnviarAServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarRecibirDelServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		return true;
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
