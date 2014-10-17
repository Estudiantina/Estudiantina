package dom.solicituddeserviciotecnico.estados;

import org.apache.isis.applib.annotation.Hidden;

import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;

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

}
