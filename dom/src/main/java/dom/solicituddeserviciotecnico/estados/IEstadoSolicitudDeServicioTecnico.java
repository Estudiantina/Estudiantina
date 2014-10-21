package dom.solicituddeserviciotecnico.estados;

import dom.tecnico.Tecnico;

public interface IEstadoSolicitudDeServicioTecnico {
	public String getNombre();
	public boolean ocultarImprimir();
	public boolean ocultarSolucion();
	public boolean ocultarAvisarPorMailQueEstaLista();
	public boolean ocultarFechaDeSolucion();
	public boolean ocultarTecnicoAsignado();
	public boolean ocultarAsignarTecnico();
	public boolean ocultarFinalizarSolicitud();
	public boolean ocultarEnviarAServicioTecnico();
	public boolean ocultarRecibirDelServicioTecnico();
	public void recibirDeServicioTecnico();
	public void enviarAServicioTecnico();
	public void avisarNetbookReparada();
	public void finalizarSolicitud();
	public void asignarTecnico(Tecnico tecnico);
}
