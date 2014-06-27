package repo.Netbook;


import java.util.Date;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MinLength;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.query.QueryDefault;


import dom.Netbook.ModeloNetbook;
import dom.Netbook.Netbook;
import dom.Netbook.estadoDeNetbook;


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
     * 
     * @param searchPhrase parametro de busqueda en el combo
     * del viewer para autocompletado
     * @return
     */
    @Hidden
    public List<Netbook> autoComplete(String searchPhrase) {        
    	return allMatches(QueryDefault.create(Netbook.class, "traerPorId","idNetbook",searchPhrase));
    }
    /**
     * Crea y guarda una netbook en el sistema
     * las propiedades de la netbook
     * son ingresadas por los parametros
     * del metodo mediante el viewer y
     * asignados al un objeto de tipo netbook
     * el cual se va a persistir
     * @param idNetbook 
     * @param modelo
     * @param numeroDeSerie
     * @param numeroLicenciaWindows
     * @param fechaDeExpiracion
     * @param direccionMac
     * @return 
     */
	public Netbook ingresarNetbook(
	@Named("id de Netbook")final String idNetbook,
	@Named("Modelo")final ModeloNetbook modelo,
	@Named("Numero De Serie")final String numeroDeSerie,
	@Named("Numero De Licencia de Windows")final String numeroLicenciaWindows,
	@Named("Fecha de Expiracion") @Optional final Date fechaDeExpiracion,
	@MaxLength(12)@MinLength(12)@RegEx(validation = "[A-Fa-f0-9 ]+") @Named("Direccion Mac")final String direccionMac,
	@Named("Estado de la Netbook")final estadoDeNetbook estadoNetbook)
	{
		final Netbook netbook = container.newTransientInstance(Netbook.class);
	    netbook.setFechaDeExpiracion(fechaDeExpiracion);
	    netbook.setIdNetbook(idNetbook);
	    netbook.setDireccionMac(direccionMac);
	    netbook.setModelo(modelo);
	    netbook.setNumeroDeSerie(numeroDeSerie);
	    netbook.setNumeroLicenciaWindows(numeroLicenciaWindows);
	    netbook.setSituacionDeNetbook("Entregada");
	    netbook.setEstadoNetbook(estadoNetbook);
	   
	    
	    
	    container.persistIfNotAlready(netbook);
	    
		return netbook;
	}
	
	
	/**
	 * Busqueda de Netbook por Id 
	 * 
	 * se retorna la net completa
	 * 
	 * @param netbook
	 * 
	 * @return List<Netbook>
	 * 
	 */
	
	
	

	
	/**
     * 
     * @param idNetbooks parametro de busqueda trae la netbook
     * 
     * @return
     */
	@Named("Buscar Netbook")
    public List<Netbook> listaNetbookPorId(@Named("Id de Netbook")String idNet) {        
    	return allMatches(QueryDefault.create(Netbook.class, "traerPorId","idNetbook",idNet));
    }
	
    
	
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
		
	
}
