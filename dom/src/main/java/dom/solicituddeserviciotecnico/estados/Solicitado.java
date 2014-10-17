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

@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idSolicitado" )
@Uniques({ @Unique(name = "solicitadoUnique" , members = { "idSolicitado" } ) })
@ObjectType("SOLICITADO")
public class Solicitado implements IEstadoSolicitudDeServicioTecnico {

	
	/**
	 * titulo del estado
	 * return SOLICITADO
	 */
	public String title()
	{
		return "SOLICITADO";
	}
	
	@Override
	@Hidden
	public boolean ocultarImprimir() {
		return false;
	}

	private SolicitudServicioTecnico solicitud;
	
	public Solicitado(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}

	
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
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

	
	
	
}
