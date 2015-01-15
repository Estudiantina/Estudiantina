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

import dom.netbook.Netbook;
import dom.persona.personagestionable.PersonaGestionable;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idEnStock" )
@Uniques({ @Unique(name = "enStockUnique" , members = { "idEnStock" } ) })

@ObjectType("ENSTOCK")
public class EnStock implements ISituacionDeNetbook {

	private Netbook netbook;
	public EnStock(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return SituacionDeNetbook.ENSTOCK;
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
		return true;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		int netbooksAsignadas=0;
		for (Netbook netbook : persona.getNetbooks())
		{
			if(netbook.getSituacion()==SituacionDeNetbook.ASIGNADA)
			{
				netbooksAsignadas++;
			}
		}
		if(netbooksAsignadas==0)
		{
			persona.getNetbooks().add(netbook);
			netbook.setPersona(persona);
		this.netbook.setSituacionDeNetbook(this.netbook.getAsignada());
		}
		else if(netbooksAsignadas==1)
		{
			persona.getNetbooks().add(netbook);
			netbook.setPersona(persona);
		this.netbook.setSituacionDeNetbook(this.netbook.getPrestada());
		}
	}

	@Override
	public Blob imprimirActaMigracion() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blob imprimirActaPrestamo() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		throw new UnsupportedOperationException("No impletandado todavía...");
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
	public boolean ocultarAsignarPersona() {
		return false;
	}

	@Override
	public boolean ocultarDesasignarNetbookDePersona() {
		return true;
	}

	@Override
	public boolean ocultarImprimirActaRecepcionDeNetbook() {
		return true;
	}
	
	@Override
	public boolean ocultarReportarComoRobada() {
		return true;
	}

	@Override
	public void reportarComoRobada(String numeroDeActa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		return true;
	}

}
