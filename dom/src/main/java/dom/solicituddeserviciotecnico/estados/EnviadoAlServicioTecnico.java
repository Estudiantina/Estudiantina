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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnviado" )
@Uniques({ @Unique(name = "enviadoUnique" , members = { "idEnviado" } ) })
@ObjectType("ENVIADOALSERVICIOTECNICO")
public class EnviadoAlServicioTecnico implements IEstadoSolicitudDeServicioTecnico {

	
	/**
	 * titulo del estado
	 * return titulo ENVIADO AL SERVICIO TECNICO
	 */
	public String getNombre()
	{
		return "ENVIADO AL SERVICIO TECNICO";
	}
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
	public EnviadoAlServicioTecnico(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}
	
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}



	@Hidden
	@Override
	public boolean ocultarImprimir() {
		return true;
	}

	

	@Hidden
	@Override
	public boolean ocultarSolucion() {
		return true;
	}
	
	@Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
		return true;
	}
	
	@Hidden
	@Override
	public boolean ocultarFechaDeSolucion() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarTecnicoAsignado() {
		
		return false;
	}
	@Hidden
	@Override
	public void recibirDeServicioTecnico() {
		this.getSolicitud().setEstado(this.getSolicitud().getEstadoRecibido());
	}
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

	@Override
	public void finalizarSolicitud() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public boolean ocultarAsignarTecnico() {
		return true;
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		return true;
	}

	@Override
	public boolean ocultarEnviarAServicioTecnico() {
		return true;
	}

	@Override
	public boolean ocultarRecibirDelServicioTecnico() {
		return false;
	}

	@javax.inject.Inject 
    DomainObjectContainer container;
}
