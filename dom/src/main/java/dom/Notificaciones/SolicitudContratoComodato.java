package dom.Notificaciones;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;

import dom.Directivo.Directivo;
import dom.Establecimiento.Establecimiento;
import dom.Localidad.Departamento;
import dom.Localidad.Localidad;
import dom.Persona.Persona;
import dom.tutor.Tutor;

@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@ObjectType("SolicitudContratoComodato")
public class SolicitudContratoComodato extends Notificaciones{

	public String title()
	{
		return "Solicitud Contrato Comodato -"+this.getPersona().toString();
	}
	
	   private Tutor tutor;

	   @Column(allowsNull="true")
	   public Tutor getTutor() {
		   return tutor;
	   }

	   public void setTutor(Tutor tutor) {
		   this.tutor = tutor;
	   }

	   private Directivo directivo;
	   @Column(allowsNull="true")
	   public Directivo getDirectivo() {
		return directivo;
	   }

	   public void setDirectivo(Directivo directivo) {
		   this.directivo = directivo;
	   }

	public Blob imprimir() throws JRException, FileNotFoundException  
	    {
	    	HashMap<String,Object> parametros = new HashMap<String, Object>();
	    	Persona persona = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
	    	Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",persona.getEstablecimiento().getNombre()));
	    	parametros.put("distritoEscolar", establecimiento.getDistritoEscolar());
	    	parametros.put("ciudadEstablecimiento", establecimiento.getLocalidad().getLocalidad());	    	
	    	Localidad localidad = container.firstMatch(QueryDefault.create(Localidad.class, "traerPorCodigoPostal","codigo",establecimiento.getLocalidad().getCodigoPostal()));
	    	Departamento departamento = container.firstMatch(QueryDefault.create(Departamento.class, "traerPorNombre","nombre",localidad.getDepartamento().getNombreDepartamento()));
	    	
	    	parametros.put("domicilioEstablecimiento", establecimiento.getDireccion());
	    	parametros.put("provincia", departamento.getProvincia().getNombreProvincia());
	    	
	    	//parametros.put("dniDirector", directivo.getCuil()+"");
	    	//consulta establecimiento

	    	parametros.put("nombreEstablecimiento",establecimiento.getNombre());
	    	
		   
	    	return servicio.Reporte.GeneradorReporte.generarReporte("reportes/contratoComodato.jrxml", parametros, "ContratoComodato");
			
	    }
	
	
	
		@javax.inject.Inject 
	    DomainObjectContainer container;
	
	
	
	
	
	
}
