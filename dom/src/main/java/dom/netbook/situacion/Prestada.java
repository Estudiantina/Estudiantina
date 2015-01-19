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

import repo.persona.RepositorioPersona;
import dom.alumno.Alumno;
import dom.establecimiento.Establecimiento;
import dom.netbook.Netbook;
import dom.persona.personagestionable.PersonaGestionable;
@PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@DatastoreIdentity(strategy = IdGeneratorStrategy.IDENTITY , column = "idPrestada" )
@Uniques({ @Unique(name = "prestadaUnique" , members = { "idPrestada" } ) })

@ObjectType("PRESTADA")
public class Prestada implements ISituacionDeNetbook {

	private Netbook netbook;
	
	public Prestada(Netbook netbook) {
		this.netbook = netbook;
	}

	@Override
	public SituacionDeNetbook getNombreSituacion() {
		return SituacionDeNetbook.PRESTADA;
	}
	
	@Override
	public boolean ocultarImprimirActaMigracion() {
		return true;
	}

	@Override
	public boolean ocultarImprimirActaPrestamo() {
		if (netbook.getPersona().getClass().getCanonicalName()=="dom.alumno.Alumno")
		{
			Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil", netbook.getPersona().getCuil(),"institucion",repoPersona.verMisDatos().getEstablecimiento()));
			if(alumno.getTutor()==null)
			{
			return true;//si no tiene tutor lo oculta
			}
			else
			{
				if(alumno.getEstablecimiento().getDirectivo()==null)
				{
				return true;
				}
				else
				{
				return false;
				}
			
			}
			
		}
		else
		{
		return true;
		}
	}

	@Override
	public boolean ocultarNumeroActaDeRobo() {
		
		return true;
	}

	@Override
	public boolean ocultarPersona() {
		return false;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blob imprimirActaMigracion() {
		return null;
		
	}

	@Override
	public Blob imprimirActaPrestamo() {
		
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();		
		Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil", netbook.getPersona().getCuil(),"institucion",netbook.getEstablecimiento()));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",alumno.getEstablecimiento().getNombre()));
		parametros.put("nombreAlumno", alumno.getNombre() +", "+alumno.getApellido() );
		parametros.put("cursoAlumno", alumno.getCursos().first().getAnio().toString());
		parametros.put("divisionAlumno", alumno.getCursos().first().getDivision());
		parametros.put("marcaNetbook", netbook.getMarca().toString());
		parametros.put("modelo", netbook.getModelo());
		parametros.put("serieNetbook", netbook.getNumeroDeSerie());
		parametros.put("nombreTutor",alumno.getTutor().getApellido()+" "+alumno.getTutor().getNombre());					
	    parametros.put("nombreDirector", establecimiento.getDirectivo().getApellido()+ ",  "+establecimiento.getDirectivo().getNombre() );
	    
		
		try {
			return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaAutorizacionPrestamoNet.jrxml", parametros, "Solicitud");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		throw new UnsupportedOperationException("No impletandado todavía...");
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

	@Override
	public void entregarNetbookAlAlumno() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		return true;
	}

	@javax.inject.Inject
    private RepositorioPersona repoPersona;
	@javax.inject.Inject
    private DomainObjectContainer container;

	@Override
	public void migrarNetbook(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarMigrarNetbook() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void aceptarMigracion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarAceptarMigracion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Blob imprimirContratoDeComodato() {
		// TODO Auto-generated method stub
		return null;
	}
}
