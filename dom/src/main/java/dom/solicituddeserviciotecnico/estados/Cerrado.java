package dom.solicituddeserviciotecnico.estados;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.IdGeneratorStrategy;

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.ObjectType;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;
import dom.tecnico.Tecnico;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idCerrado" )
@Uniques({ @Unique(name = "cerradoUnique" , members = { "idCerrado" } ) })
@ObjectType("CERRADO")
public class Cerrado implements IEstadoSolicitudDeServicioTecnico{

	
	/**
	 * titulo del estado
	 * return CERRADO
	 */
	public String title()
	{
		return "CERRADO";
	}
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
    public Cerrado(SolicitudServicioTecnico solicitud) {
		this.solicitud = solicitud;
	}
	
	
    public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
	}


	@Hidden
	@Override
	public boolean ocultarImprimir() {
		// TODO Apéndice de método generado automáticamente
		return true;
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

    @Hidden
	@Override
	public void enviarAServicioTecnico() {
		// TODO Apéndice de método generado automáticamente
		
	}

    @Hidden
	@Override
	public void avisarNetbookReparada() {
		// TODO Apéndice de método generado automáticamente
		
	}

    @Hidden
	@Override
	public void finalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
		
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
