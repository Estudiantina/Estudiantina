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
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idAsignada" )
@Uniques({ @Unique(name = "asignadaUnique" , members = { "idAsignada" } ) })

@ObjectType("ASIGNADA")
public class Asignada implements ISituacionDeNetbook {

	private Netbook netbook;
	
	public Asignada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return SituacionDeNetbook.ASIGNADA;
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
		return true;
	}

	@Override
	public boolean ocultarPersona() {
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
		this.netbook.setSituacionDeNetbook(this.netbook.getEnStock());
		this.netbook.setPersona(null);
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarAsignarPersona() {
		return true;
	}


}
