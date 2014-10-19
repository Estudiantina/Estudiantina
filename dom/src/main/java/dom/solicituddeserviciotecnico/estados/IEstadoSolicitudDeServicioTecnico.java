package dom.solicituddeserviciotecnico.estados;

import dom.tecnico.Tecnico;

public interface IEstadoSolicitudDeServicioTecnico {
	public boolean ocultarImprimir();
	public boolean ocultarSolucion();
	public boolean ocultarAvisarPorMailQueEstaLista();
	public boolean ocultarFechaDeSolucion();
	public boolean ocultarTecnicoAsignado();
	public boolean ocultarAsignarTecnico();
	public void recibirDeServicioTecnico();
	public void enviarAServicioTecnico();
	public void avisarNetbookReparada();
	public void finalizarSolicitud();
	public void asignarTecnico(Tecnico tecnico);
}
