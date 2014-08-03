package dom.Localidad;

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.DomainObjectContainer;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)



public class Provincia {
	
	private String Provincia;
	
	

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	
	
	
	
	 private DomainObjectContainer container;

	 /**
	  * 
	  */
		protected DomainObjectContainer getContainer()	{
			return container;
		}
		
		/**
		 * 
		 */
		public void setDomainObjectContainer(final DomainObjectContainer container){
			this.container = container;
		}
	

}
