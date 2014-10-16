package dom.solicituddeserviciotecnico.estados;

public interface IEstadoSolicitudDeServicioTecnico {
	public boolean ocultarImprimir();
	public boolean ocultarSolucion();
	public boolean ocultarAvisarPorMailQueEstaLista();
	public boolean ocultarFechaDeSolucion();
	
}
