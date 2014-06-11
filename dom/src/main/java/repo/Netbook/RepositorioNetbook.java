package repo.Netbook;


import java.util.Date;
import java.util.List;





import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.query.QueryDefault;



import dom.Netbook.Netbook;

@Named("Administrar Netbook")
public class RepositorioNetbook extends AbstractFactoryAndRepository {


	public String getId() {
        return "Netbook";
    }

    public String iconName() {
        return "netbook";
    }
	/**
	 * muestra una lista de todas las Netbooks que existen
	 * @return lista de Netbooks
	 */
    public List<Netbook> listaNetbooks() {
        return allMatches(QueryDefault.create(Netbook.class, "traerTodo"));
    }
    /**
     * TODO optimizar parametro para que solo busque por el ID y no muestre todo
     * @param searchPhrase parametro de busqueda en el combo
     * del viewer para autocompletado
     * @return
     */
    @Hidden
    public List<Netbook> autoComplete(String searchPhrase) {        
    	return allMatches(QueryDefault.create(Netbook.class, "traerTodo"));
    }
    /**
     * Crea y guarda una netbook en el sistema
     * @param idNetbook
     * @param modelo
     * @param numeroDeSerie
     * @param numeroLicenciaWindows
     * @param fechaDeExpiracion
     * @param direccionMac
     * @return
     */
	public Netbook ingresarNetbook(@Named("id de Netbook")final String idNetbook ,
	@Named("Modelo")final String modelo,
	@Named("Numero De Serie")final String numeroDeSerie,
	@Named("Numero De Licencia de Windows")final String numeroLicenciaWindows,
	@Named("Fecha de Expiracion") @Optional final Date fechaDeExpiracion,
	@Named("Direccion Mac")final String direccionMac)
	{
		final Netbook netbook = container.newTransientInstance(Netbook.class);
	    netbook.setFechaDeExpiracion(fechaDeExpiracion);
	    netbook.setIdNetbook(idNetbook);
	    netbook.setDireccionMac(direccionMac);
	    netbook.setModelo(modelo);
	    netbook.setNumeroDeSerie(numeroDeSerie);
	    netbook.setNumeroLicenciaWindows(numeroLicenciaWindows);
	    netbook.setSituacionDeNetbook("Entregada");
	    
	    
	    container.persistIfNotAlready(netbook);
	    
		return netbook;
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container;
		
	
}
