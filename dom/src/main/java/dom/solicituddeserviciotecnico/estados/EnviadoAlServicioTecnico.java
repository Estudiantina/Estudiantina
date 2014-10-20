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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnviado" )
@Uniques({ @Unique(name = "enviadoUnique" , members = { "idEnviado" } ) })
@ObjectType("ENVIADOALSERVICIOTECNICO")
public class EnviadoAlServicioTecnico implements IEstadoSolicitudDeServicioTecnico {

	
	/**
	 * titulo del estado
	 * return titulo ENVIADO AL SERVICIO TECNICO
	 */
	public String title()
	{
		return "ENVIADO AL SERVICIO TECNICO";
	}
	
	private SolicitudServicioTecnico solicitud;
	/**
	 * constructor
	 */
	public EnviadoAlServicioTecnico(SolicitudServicioTecnico solicitud) {
		super();
		this.solicitud = solicitud;
	}
	
	public SolicitudServicioTecnico getSolicitud() {
		return solicitud;
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

	@Override
	public void finalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
		
	}
	@Hidden
	@Override
	public void asignarTecnico(Tecnico tecnico) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public boolean ocultarAsignarTecnico() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}
	@Hidden
	@Override
	public boolean ocultarFinalizarSolicitud() {
		// TODO Apéndice de método generado automáticamente
		return false;
	}

}
