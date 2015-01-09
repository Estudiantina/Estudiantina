package dom.netbook.situacion;

import dom.netbook.Netbook;
import dom.persona.Persona;

public class Asignada implements ISituacionDeNetbook {

	private Netbook netbook;
	
	public Asignada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public String getNombreSituacion() {
		return "Asignada";
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
		return true;
	}

	@Override
	public boolean hidePersona() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desasignarNetbookDePersona() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hideAsignarPersona() {
		// TODO Auto-generated method stub
		return false;
	}


}
