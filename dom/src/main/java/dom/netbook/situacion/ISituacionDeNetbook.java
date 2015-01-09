package dom.netbook.situacion;

import dom.persona.Persona;

public interface ISituacionDeNetbook {
	public String getNombreSituacion();
	public boolean hideImprimirActaMigracion();
	public boolean hideImprimirActaPrestamo();
	public boolean hideNumeroActaDeRobo();
	public boolean hidePersona();
	public boolean hideAsignarPersona();
	public void imprimirActaMigracion();
	public void imprimirActaPrestamo();
	public void imprimirActaRecepcionDeNetbook();
	public void desasignarNetbookDePersona();
	public void establecerNetbookComoRobada();
	public void asignarPersona(Persona persona);
}
