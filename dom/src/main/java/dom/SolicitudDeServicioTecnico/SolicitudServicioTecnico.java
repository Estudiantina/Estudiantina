package dom.SolicitudDeServicioTecnico;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;





import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;
import javax.jdo.annotations.VersionStrategy;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.Blob;





import dom.Establecimiento.Establecimiento;
import dom.Netbook.Netbook;
import dom.Persona.Persona;
@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.Queries({@javax.jdo.annotations.Query(name = "traerPorPrioridad", language = "JDOQL",
          value = "SELECT FROM repo.Netbook.SolicitudServicioTecnico"),
	@Query(name="taerTipoDeSoluciones", language="JDOQL", 
	      value = "SELECT FROM dom.SolicitudDeServicioTecnico.SolicitudServicioTecnico WHERE motivoDeSolicitud.indexOf(:motivoDeSolicitud) >=0 range 0, 5")})

@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")

@ObjectType("SERVICIOTECNICO")
@Bookmarkable
public class SolicitudServicioTecnico {
    
	//public solicitante integrante de la institucion
	private Persona persona;
	private String motivoDeSolicitud;
	private Date fechaDeSolicitud;
	private String solucion;
	private Date fechaDeSolucion;
	private Prioridad prioridad;
	private Netbook netbook ;
	private String codigoSolicitud;
	private String numeroTiquetRegistro;
	private String comentario;
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String iconName() {
        return "asistenciatecnica";
    }
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	
	
	
	@Title
	@javax.jdo.annotations.Column(allowsNull="false")
	public String getMotivoDeSolicitud() {
		return motivoDeSolicitud;
	}





	public void setMotivoDeSolicitud(String motivoDeSolicitud) {
		this.motivoDeSolicitud = motivoDeSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Date getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}





	public void setFechaDeSolicitud(Date fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}



    @Optional
	@javax.jdo.annotations.Column(allowsNull="true")
    @MultiLine
	public String getSolucion() {
		return solucion;
	}




    
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}




    @javax.jdo.annotations.Column(allowsNull="true")
    @Optional
	public Date getFechaDeSolucion() {
		return fechaDeSolucion;
	}





	public void setFechaDeSolucion(Date fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public Prioridad getPrioridad() {
		return prioridad;
	}




    
	public void setPrioridad(Prioridad prioridad) {
		this.prioridad = prioridad;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}




    @Unique
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}




	@javax.jdo.annotations.Column(allowsNull="false")
	public String getNumeroTiquetRegistro() {
		return numeroTiquetRegistro;
	}





	public void setNumeroTiquetRegistro(String numeroTiquetRegistro) {
		this.numeroTiquetRegistro = numeroTiquetRegistro;
	}



    @MultiLine
    @javax.jdo.annotations.Column(allowsNull="true")
	public String getComentario() {
		return comentario;
	}

    



	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

    
    /**
     * TODO ImprimirReporte
     * archivo incompleto para imprimir
     * el metodo funciona pero esta hardcodeado
     * @return Reporte a imprimir
     * @throws JRException 
     * @throws FileNotFoundException 
     */
	public Blob imprimir() throws JRException, FileNotFoundException
	{
		Object[] obj= new Object[1];
		obj[0]="";
		HashMap< String,Object> parametros = new HashMap<String, Object>();
		parametros.put("motivoSolicitud", this.getMotivoDeSolicitud());
		parametros.put("numeroSerieNetbook", this.getNetbook().getNumeroDeSerie());
		parametros.put("apellidoYnombre", this.getPersona().getApellido()+" "+this.getPersona().getNombre());
		parametros.put("cuilDni", this.getPersona().getCuil());
		Persona per = container.firstMatch(QueryDefault.create(Persona.class, "traerPorcuil","cuil",this.getPersona().getCuil()));
		Establecimiento establecimiento =container.firstMatch(QueryDefault.create(Establecimiento.class, "traerPorNombre","nombre",per.getEstablecimiento().getNombre()));
		//TODO establecer parametro de curso y division
		parametros.put("nombreInstitucion", establecimiento.getNombre());
		parametros.put("direccionDeLaInstitucion",establecimiento.getDireccion());
		parametros.put("telefono",establecimiento.getTelefono());
		parametros.put("Email",establecimiento.getEmail());
		SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MMM/yyyy/");
		parametros.put("fechaDeNacimiento", formatofecha.format(this.getPersona().getFechaNacimiento()));
		JRBeanArrayDataSource jrDataSource= new JRBeanArrayDataSource(obj);
		File file = new File("reportes/solicitudAsistenciaTecnica.jrxml");
		InputStream input = new FileInputStream(file);
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint print = JasperFillManager.fillReport(reporte, parametros,jrDataSource);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,print); 
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("/tmp/solicitudAsistenciaTecnica.pdf"));
		exporter.exportReport();
		
		
        
		File resume = new File("/tmp/solicitudAsistenciaTecnica.pdf");
		if (!(resume.exists()))
		{
		try {
			resume.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
		byte[] fileContent = new byte[(int) resume.length()];
		try {
		    FileInputStream fileInputStream = new FileInputStream(resume);
		         
		    fileInputStream.read(fileContent);
		    fileInputStream.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		Blob blob= new Blob("solicitudAsistenciaTecnica.pdf","application/pdf",fileContent);
			
		return blob;
		
	}
	



	@javax.inject.Inject 
    DomainObjectContainer container;
	
}
