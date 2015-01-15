package dom.netbook.situacion;

import org.apache.isis.applib.value.Blob;

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
	public Blob imprimirActaMigracion();
	public Blob imprimirActaPrestamo();
	public Blob imprimirActaRecepcionDeNetbook();
	public void desasignarNetbookDePersona();
	public void establecerNetbookComoRobada();
	public void entregarNetbookAlAlumno();
	public boolean ocultarEntregarNetbookAlAlumno();
	public void asignarPersona(PersonaGestionable persona);
}
