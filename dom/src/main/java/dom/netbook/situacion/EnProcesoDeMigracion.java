package dom.netbook.situacion;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
import dom.netbook.Netbook;
import dom.persona.personagestionable.PersonaGestionable;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnMigracion" )
@Uniques({ @Unique(name = "enMigracionUnique" , members = { "idEnMigracion" } ) })

@ObjectType("ENPROCESODEMIGRACION")
public class EnProcesoDeMigracion implements ISituacionDeNetbook{

	private Netbook netbook;
	private Establecimiento establecimientoAMigrar;
	
	public EnProcesoDeMigracion(Netbook netbook)
	{
		this.netbook= netbook;
	}
	@Override
	public SituacionDeNetbook getNombreSituacion() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blob imprimirActaMigracion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob imprimirActaPrestamo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		// TODO Auto-generated method stub
		return null;
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
	public void entregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void migrarNetbook(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean ocultarMigrarNetbook() {
		// TODO Auto-generated method stub
		return false;
	}

}
