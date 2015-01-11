package dom.netbook.situacion;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;

import dom.netbook.Netbook;
import dom.persona.Persona;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idRobada" )
@Uniques({ @Unique(name = "robadaUnique" , members = { "idRobada" } ) })

@ObjectType("ROBADA")
public class Robada implements ISituacionDeNetbook{

	@SuppressWarnings("unused")
	private Netbook netbook;
	
	public Robada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return SituacionDeNetbook.ROBADA;
	}
	
	@Override
	public boolean ocultarImprimirActaMigracion() {
		return true;
	}

	@Override
	public boolean ocultarImprimirActaPrestamo() {
		return true;
	}

	@Override
	public boolean ocultarNumeroActaDeRobo() {
		return false;
	}

	@Override
	public boolean ocultarPersona() {
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
	public boolean ocultarAsignarPersona() {
		return true;
	}

	@Override
	public boolean ocultarDesasignarNetbookDePersona() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean ocultarImprimirActaRecepcionDeNetbook() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean ocultarReportarComoRobada() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void reportarComoRobada(String numeroDeActa) {
		
	}



}
