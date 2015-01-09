package dom.netbook.situacion;

import dom.netbook.Netbook;
import dom.persona.Persona;

public class Robada implements ISituacionDeNetbook{

	private Netbook netbook;
	
	public Robada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public String getNombreSituacion() {
		return "Robada";
	}
	
	@Override
	public boolean hideImprimirActaMigracion() {
		return true;
	}

	@Override
	public boolean hideImprimirActaPrestamo() {
		return true;
	}

	@Override
	public boolean hideNumeroActaDeRobo() {
		return false;
	}

	@Override
	public boolean hidePersona() {
		return false;
	}

	@Override
	public void asignarPersona(Persona persona) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void imprimirActaMigracion() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void imprimirActaPrestamo() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void imprimirActaRecepcionDeNetbook() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void desasignarNetbookDePersona() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public boolean hideAsignarPersona() {
		return true;
	}



}
