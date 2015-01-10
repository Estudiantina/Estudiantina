package dom.netbook.situacion;

import dom.persona.Persona;

public interface ISituacionDeNetbook {
	public SituacionDeNetbook getNombreSituacion();
	public boolean ocultarImprimirActaMigracion();
	public boolean ocultarImprimirActaPrestamo();
	public boolean ocultarNumeroActaDeRobo();
	public boolean ocultarPersona();
	public boolean ocultarAsignarPersona();
	public boolean ocultarDesasignarNetbookDePersona();
	public boolean ocultarImprimirActaRecepcionDeNetbook();
	public void imprimirActaMigracion();
	public void imprimirActaPrestamo();
	public void imprimirActaRecepcionDeNetbook();
	public void desasignarNetbookDePersona();
	public void establecerNetbookComoRobada();
	public void asignarPersona(Persona persona);
}
