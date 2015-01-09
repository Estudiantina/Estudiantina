package dom.netbook.situacion;

import dom.netbook.Netbook;
import dom.persona.Persona;

public class EnStock implements ISituacionDeNetbook {

	private Netbook netbook;
	public EnStock(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public String getNombreSituacion() {
		return "En Stock";
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
		return true;
	}

	@Override
	public void asignarPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimirActaMigracion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imprimirActaPrestamo() {
		// TODO Auto-generated method stub
		
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
		return false;
	}



}
