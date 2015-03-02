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
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.alumno.Alumno;
import dom.establecimiento.Establecimiento;
import dom.localidad.Departamento;
import dom.localidad.Localidad;
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
		return false;
	}

	@Override
	public void asignarPersona(PersonaGestionable persona) {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public Blob imprimirActaMigracion() {
		
		throw new UnsupportedOperationException("No impletandado todavía...");
	}

	@Override
	public Blob imprimirActaPrestamo() {
		throw new UnsupportedOperationException("No impletandado todavía...");
		
	}

	@Override
	public Blob imprimirActaRecepcionDeNetbook() {
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		PersonaGestionable persona = container.firstMatch(QueryDefault.create(PersonaGestionable.class, "traerPorcuil","cuil", netbook.getPersona().getCuil(),"establecimiento",netbook.getEstablecimiento() ));
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
		this.netbook.getPersona().getNetbooks().remove(this.netbook);
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
		this.netbook.setEstablecimientoAmigrar(establecimiento);
		this.netbook.setSituacionDeNetbook(this.netbook.getEnProcesoDeMigracion());
		
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
		return true;
	}

///////////////////////////////////////
//imprimir reporte para contrato de comodato
//////////////////////////////////////
	@Named("Imprimir Contrato Comodato")
	public Blob imprimirContratoDeComodato()
	{
		try {

			HashMap<String,Object> parametros = new HashMap<String, Object>();

			Alumno alumno = container.firstMatch(QueryDefault.create(Alumno.class, "traerAlumnoPorcuil","cuil",this.netbook.getPersona().getCuil(),"institucion",this.netbook.getEstablecimiento()));
			Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",alumno.getEstablecimiento().getNombre()));
			parametros.put("distritoEscolar", establecimiento.getDistritoEscolar());
			parametros.put("ciudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());	    	
			parametros.put("nombreEstablecimiento", establecimiento.getNombre());
			parametros.put("domicilioEstablecimiento", establecimiento.getDireccion());
			parametros.put("Establecimiento", establecimiento.getNombre());
			parametros.put("cudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());
			parametros.put("nombreTutor", alumno.getTutor().getApellido()+" "+alumno.getTutor().getNombre());
			//PARAMETROS DEL TUTOR	    	
			parametros.put("dniTutor",alumno.getTutor().getCuil().toString());
			parametros.put("domicilio",alumno.getTutor().getDomicilio());
			parametros.put("piso",alumno.getTutor().getPiso());
			parametros.put("ciudadTutor",alumno.getTutor().getLocalidad().toString());


			Localidad localidadEstablecimiento = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal", "codigo",alumno.getEstablecimiento().getLocalidad().getCodigoPostal()));
			Departamento departamentoEstablecimiento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre", localidadEstablecimiento.getDepartamento().getNombreDepartamento()));

			parametros.put("provinciaEstablecimiento", departamentoEstablecimiento.getProvincia().getNombreProvincia());

			Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
			Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
			parametros.put("direccionEstablecimiento", establecimiento.getDireccion());
			parametros.put("provincia", departamento.getProvincia().getNombreProvincia());
			parametros.put("DniAlumno", alumno.getCuil().toString());
			parametros.put("caracterTutor", alumno.getTutor().getApellido()+" "+alumno.getTutor().getNombre());
			parametros.put("nombreEstablecimiento",establecimiento.getNombre());
			parametros.put("numeroEstablecimiento","");
			parametros.put("Curso", alumno.getCursos().get(0).getAnio().toString());
			parametros.put("Turno", alumno.getCursos().get(0).getTurno().toString());
			parametros.put("division", alumno.getCursos().get(0).getDivision().toString());
			parametros.put("modeloNetbook",netbook.getMarca().toString());
			parametros.put("numeroSerieNetbook",netbook.getNumeroDeSerie());

			return servicio.reporte.GeneradorReporte.generarReporte("reportes/contratoComodato.jrxml", parametros, "ContratoComodato");
		}
		catch(Exception ex)
		{	String respuesta = "no se pudo generar el reporte verifique que esten todos los datos, inclusive el curso"+ex.toString();
			Blob archivonulo = new Blob("archivo.txt", "text/plain",respuesta.getBytes());
			return archivonulo;
		}
}

	@Override
	public boolean ocultarContratoDeComodato() {
		return false;
	}

	@Override
	public Blob imprimirContratoDeCesion() {
		return null;
	}

	@Override
	public boolean ocultarImprimirContratoDeCesion() {
		return true;
	}	
}