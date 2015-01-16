package dom.netbook.situacion;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.establecimiento.Establecimiento;
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
		return true;
	}

	@Override
	public boolean ocultarImprimirActaPrestamo() {
		// TODO Auto-generated method stub
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
	public boolean ocultarAsignarPersona() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Blob imprimirActaMigracion()  {
		return null;
		
	}

	@Override
	public Blob imprimirActaPrestamo() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
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
	public void asignarPersona(PersonaGestionable persona) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("No impletandado todavía...");
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
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public void entregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		return true;
	}
	
	@javax.inject.Inject
    private DomainObjectContainer container;

}
