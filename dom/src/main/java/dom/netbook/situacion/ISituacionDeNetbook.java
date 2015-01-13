package dom.netbook.situacion;

import dom.persona.personagestionable.PersonaGestionable;

public interface ISituacionDeNetbook {
	public SituacionDeNetbook getNombreSituacion();
	public boolean ocultarImprimirActaMigracion();
	public boolean ocultarImprimirActaPrestamo();
	public boolean ocultarNumeroActaDeRobo();
	public boolean ocultarPersona();
	public boolean ocultarAsignarPersona();
	public boolean ocultarDesasignarNetbookDePersona();
	public boolean ocultarImprimirActaRecepcionDeNetbook();
	public boolean ocultarReportarComoRobada();
	public void reportarComoRobada(String numeroDeActa);
	public void imprimirActaMigracion();
	public void imprimirActaPrestamo();
	public void imprimirActaRecepcionDeNetbook();
	public void desasignarNetbookDePersona();
	public void establecerNetbookComoRobada();
	public void asignarPersona(PersonaGestionable persona);
}
