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
import dom.establecimiento.Establecimiento;
import dom.netbook.Netbook;
import dom.persona.personagestionable.PersonaGestionable;
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
		if (repoPersona.verMisDatos().getEstablecimiento().getDirectivo()==null)
		{
		return true;
		}
		else
		{
			return false;
		}
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
		return false;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public Blob imprimirActaMigracion() {
		
		HashMap<String,Object> parametros = new HashMap<String, Object>();
 		PersonaGestionable persona = container.firstMatch(QueryDefault.create(PersonaGestionable.class, "traerPorcuil","cuil",netbook.getPersona().getCuil()));
 		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
 		parametros.put("distrito", establecimiento.getDistritoEscolar());
 		parametros.put("cue", establecimiento.getCue());
 		/*parametros.put("emailEstablecimiento", establecimiento.getEmail());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
 		parametros.put("establecimiento", establecimiento.getNombre());
 		parametros.put("localidad", establecimiento.getLocalidad());
 		parametros.put("domicilio", establecimiento.getDireccion());
 		parametros.put("telefonoEstablecimiento", establecimiento.getTelefono());
      	parametros.put("alumno", persona.getNombre()+", "+persona.getApellido());
        parametros.put("cuilAlumno", persona.getCuil());
        parametros.put("nombreDirectorCedente", establecimientoAmigrar.getDirectivo().getApellido()+" "+establecimientoAmigrar.getDirectivo().getNombre());
 		parametros.put("netbookModelo", netbook.getMarca().toString()+" "+netbook.getModelo());
 		parametros.put("numeroSerieNetbook", netbook.getNumeroDeSerie());
 		parametros.put("directorCedente", establecimientoAmigrar.getDirectivo().getApellido()+ ",  "+establecimiento.getDirectivo().getNombre());
 		parametros.put("nroDniDirector", establecimiento.getDirectivo().getCuil());
 		parametros.put("directorCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("dniDirectorCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("nombreEstablecimientoCedente", establecimientoAmigrar.getNombre());
 		parametros.put("cueEstablecimientoCedente", establecimientoAmigrar.getDirectivo().getCuil());
 		parametros.put("distritoEscolarCedente", establecimientoAmigrar.getDistritoEscolar());
 		parametros.put("ciudadEstablecimientoCedente", establecimientoAmigrar.getLocalidad().getLocalidad());
 		parametros.put("provinciaEstablecimientoCedente", establecimientoAmigrar.getLocalidad().getLocalidad());
 		parametros.put("domicilioEstablecimientoCedente", establecimientoAmigrar.getDireccion());
 		parametros.put("nombreAlumno", netbook.getPersona().getNombre());
 		parametros.put("modeloNetbook", netbook.getMarca()+" "+netbook.getModelo());
 		parametros.put("nroSerieNetbook", netbook.getNumeroDeSerie());
 		parametros.put("ciudadAlumno", netbook.getPersona().getLocalidad().getLocalidad());
 		parametros.put("ciudadAlumno", "");*/
 		try {
			return servicio.reporte.GeneradorReporte.generarReporte("reportes/ActaMigracion.jrxml", parametros, "Solicitud");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public Blob imprimirActaPrestamo() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		PersonaGestionable persona = container.firstMatch(QueryDefault.create(PersonaGestionable.class, "traerPorcuil","cuil", netbook.getPersona().getCuil() ));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
		parametros.put("nombreAlumno", persona.getNombre());
		parametros.put("apellidoAlumno",persona.getApellido());
		parametros.put("modeloNetbook", netbook.getModelo());
		parametros.put("marcaNetbook", netbook.getMarca());
		parametros.put("serieNetbook", netbook.getNumeroDeSerie());
		parametros.put("establecimientoEducativo",netbook.getPersona().getEstablecimiento().getNombre());
		parametros.put("ciudad", establecimiento.getLocalidad());
		parametros.put("departamento", establecimiento.getLocalidad().getDepartamento().getNombreDepartamento());
		try{
		return servicio.reporte.GeneradorReporte.generarReporte("reportes/reciboNetbook.jrxml", parametros, "Solicitud");
		}
		catch(Exception ex)
		{return null;}
		
	}

	@Override
	public void desasignarNetbookDePersona() {
		this.netbook.setSituacionDeNetbook(this.netbook.getEnStock());
		this.netbook.setPersona(null);
		
	}

	@Override
	public void establecerNetbookComoRobada() {
		
	}

	@Override
	public boolean ocultarAsignarPersona() {
		return true;
	}

	@Override
	public boolean ocultarDesasignarNetbookDePersona() {
		return false;
	}

	@Override
	public boolean ocultarImprimirActaRecepcionDeNetbook() {
		return false;
	}

	@Override
	public boolean ocultarReportarComoRobada() {
		return false;
	}

	@Override
	public void reportarComoRobada(String numeroDeActa) {
		this.netbook.setNumeroDeActaDeRobo(numeroDeActa);
		this.netbook.setSituacionDeNetbook(this.netbook.getRobada());
	}
	@javax.inject.Inject
    private RepositorioPersona repoPersona;
	@javax.inject.Inject
    private DomainObjectContainer container;

	@Override
	public void entregarNetbookAlAlumno() {
		this.netbook.setSituacionDeNetbook(this.netbook.getEntregada());
		
	}

	@Override
	public boolean ocultarEntregarNetbookAlAlumno() {
		return false;
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
