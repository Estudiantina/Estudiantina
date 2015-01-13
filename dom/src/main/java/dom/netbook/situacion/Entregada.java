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
import dom.persona.personagestionable.PersonaGestionable;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEntregada" )
@Uniques({ @Unique(name = "entregadaUnique" , members = { "idEntregada" } ) })

@ObjectType("ENTREGADA")
public class Entregada implements ISituacionDeNetbook{

	private Netbook netbook;
	
	public Entregada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return SituacionDeNetbook.ASIGNADA;
	}
	
	@Override
	public boolean ocultarImprimirActaMigracion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarImprimirActaPrestamo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarNumeroActaDeRobo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarPersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarAsignarPersona() {
		// TODO Auto-generated method stub
		return false;
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
		this.netbook.setPersona(null);
		this.netbook.setSituacionDeNetbook(this.netbook.getEnStock());
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarDesasignarNetbookDePersona() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarImprimirActaRecepcionDeNetbook() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ocultarReportarComoRobada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reportarComoRobada(String numeroDeActa) {
		this.netbook.setNumeroDeActaDeRobo(numeroDeActa);
		this.netbook.setSituacionDeNetbook(this.netbook.getRobada());
		
	}

}
