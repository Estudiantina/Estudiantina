package servicio.estadisticas;

import java.math.BigDecimal;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

public class NetbookReparadasAnualmente  extends AbstractViewModel{

	private String memento;
	
	@Override
	public void viewModelInit(String memento) {
		this.memento=memento;
		Memento m =  mementoService.parse(memento);
		
		setMes(m.get("mes", Mes.class));
		setCantidadDeNetbookReparadas(m.get("cantidadNetbookReparadas", BigDecimal.class));
		
	}

	@Override
	public String viewModelMemento() {
		
		return this.memento;
		
	}
	
	private Mes mes;
	private BigDecimal cantidadDeNetbookReparadas;

	
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public BigDecimal getCantidadDeNetbookReparadas() {
		return cantidadDeNetbookReparadas;
	}

	
	public void setCantidadDeNetbookReparadas(BigDecimal cantidadDeNetbookReparadas) {
		this.cantidadDeNetbookReparadas = cantidadDeNetbookReparadas;
	}
	
	public String title(){
		return mes.toString();
	}
	
	
	
	@javax.inject.Inject
    private MementoService mementoService;
	
}
