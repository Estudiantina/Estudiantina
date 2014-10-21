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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idRecibido" )
@Uniques({ @Unique(name = "recibidoUnique" , members = { "idRecibido" } ) })
@ObjectType("RECIBIDODELSERVICIOTECNICO")
public class RecibidoDelServicioTecnico implements IEstadoSolicitudDeServicioTecnico {
	
	
	/**
	 * titulo del estado
	 * return titulo RECIBIDO DEL SERVICIO TECNICO
	 */
	public String getNombre()
	{
		return "RECIBIDO DEL SERVICIO TECNICO";
	}
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
	public RecibidoDelServicioTecnico(SolicitudServicioTecnico solicitud) {
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
	
		return false;
	}
	
	@Hidden
	@Override
	public boolean ocultarAvisarPorMailQueEstaLista() {
	
		return false;
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
	
		this.solicitud.setEstadoSolicitud(this.getSolicitud().getEstadoReparado());
	}

	@Hidden
	@Override
	public void finalizarSolicitud() {
	
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

    @Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
	
		
    	throw new UnsupportedOperationException("No impletandado todavía...");
	}


    @Hidden
	@Override
	public boolean ocultarAsignarTecnico() {
	
		return true;
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




