package dom.netbook.situacion;

import dom.persona.Persona;

public class Robada implements ISituacionDeNetbook{

	@Override
	public String getNombreSituacion() {
		return "Robada";
	}
	
	@Override
	public boolean hideImprimirActaMigracion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hideImprimirActaPrestamo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hideNumeroActaDeRobo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hidePersona() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}



}
