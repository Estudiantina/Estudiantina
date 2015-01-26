package dom.netbook.situacion;

import java.util.HashMap;
import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.Uniques;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;
import servicio.reporte.GeneradorReporte;
import dom.alumno.Alumno;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
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
		return SituacionDeNetbook.ENTREGADA;
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

	@Override
	public void migrarNetbook(Establecimiento establecimiento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarMigrarNetbook() {
		return true;
	}

	@Override
	public void aceptarMigracion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ocultarAceptarMigracion() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Blob imprimirContratoDeComodato() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ocultarContratoDeComodato() {
		return true;
	}
	
	public Blob imprimirContratoDeCesion() {
	 	try{
	    HashMap<String, Object> parametros = new HashMap<String, Object>();
	    Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",this.netbook.getPersona().getCuil(),"institucion",this.netbook.getEstablecimiento()));
	    parametros.put("domicilioCiudadTutor", alumno.getTutor().getLocalidad().toString());
	    parametros.put("modeloNetbook",netbook.getMarca().toString());
	    parametros.put("marcaNetbook",netbook.getMarca());
	    parametros.put("alturaDomicilioTutor", alumno.getTutor().getAlturaDomicilio());
	    //TODO parametros.put("domicilioProvinciaTutor","")
	    /*if (alumno.getTutor().getPiso().isEmpty()==false)
	    {
	    parametros.put("domicilioPisoTutor", alumno.getTutor().getPiso().toString());
	    }else
	    {*/
	    parametros.put("domicilioPisoTutor", "");
	    //}
	    parametros.put("domicilioDepartamentoTutor",alumno.getTutor().getLocalidad().getDepartamento().toString());
	    parametros.put("numeroSerieNetbook",alumno.getNetbooks().first().getNumeroDeSerie());
	    parametros.put("Instituto",alumno.getEstablecimiento().getNombre());
	    parametros.put("ciudadDeEstablecimiento", alumno.getEstablecimiento().getLocalidad().toString());
	    parametros.put("nombreAlumno", alumno.getApellido()+" "+alumno.getNombre());
	    parametros.put("dniAlumno", alumno.getCuil());
	    parametros.put("nombreDirector", alumno.getEstablecimiento().getDirectivo().toString());
	 	parametros.put("dniDirector", alumno.getEstablecimiento().getDirectivo().getCuil());
	    parametros.put("nombreAlumno", alumno.getApellido()+" "+alumno.getNombre());
	    parametros.put("distritoEscolar", alumno.getEstablecimiento().getDistritoEscolar());
	    parametros.put("ciudadDeEstablecimiento", alumno.getEstablecimiento().getLocalidad().getLocalidad());
	    parametros.put("domicilioEstablecimiento", alumno.getEstablecimiento().getDireccion());
	    parametros.put("nombreTutor", alumno.getTutor().getApellido()+" "+alumno.getTutor().getNombre());
	    parametros.put("dniTutor", alumno.getTutor().getCuil());	    
	    parametros.put("domicilioTutor", alumno.getTutor().getDomicilio());
	    parametros.put("cursoAlumno", alumno.getCursos().first().getAnio());
	    
	    Localidad localidadEstablecimiento = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",alumno.getEstablecimiento().getLocalidad().getCodigoPostal()));
	    Departamento departamentoEstablecimiento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidadEstablecimiento.getDepartamento().getNombreDepartamento()));
	    parametros.put("provinciaDeEstablecimiento", departamentoEstablecimiento.getProvincia().getNombreProvincia());
	    
	    Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",alumno.getTutor().getLocalidad().getCodigoPostal()));
	    Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidad.getDepartamento().getNombreDepartamento()));
	    
	    parametros.put("domicilioDepartamentoTutor", departamento.getNombreDepartamento());
	    parametros.put("turnoCursoAlumno", alumno.getCursos().first().getTurno().toString());
	    parametros.put("divisionCursoAlumno",alumno.getCursos().first().getDivision());
	    
	    return GeneradorReporte.generarReporte("reportes/contratoCesion.jrxml",parametros , "contratoCesion");
		}
		catch(Exception ex)
		{	
			Blob archivonulo = new Blob("archivo.txt", "text/plain", "no se pudo generar el reporte verifique que esten todos los datos: Tutor,Netbook,Establecimiento y Director".getBytes());
			return archivonulo;
		}
	}

	@Override
	public boolean ocultarImprimirContratoDeCesion() {
		// TODO Auto-generated method stub
		return false;
	}

}
