package dom.netbook.situacion;

import dom.persona.Persona;

public interface ISituacionDeNetbook {

	public boolean hideImprimirActaMigracion();
	public boolean hideImprimirActaPrestamo();
	public boolean hideNumeroActaDeRobo();
	public boolean hidePersona();
	public void imprimirActaMigracion();
	public void imprimitActaPrestamo();
	public void imprimirActaRecepcionDeNetbook();
	public void desasignarNetbookDePersona();
	public void establecerNetbookComoRobada();
	public void asignarPersona(Persona persona);
}
