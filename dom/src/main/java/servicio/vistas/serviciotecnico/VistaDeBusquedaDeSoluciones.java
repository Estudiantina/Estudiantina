package servicio.vistas.serviciotecnico;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.apache.isis.applib.value.Blob;

public class VistaDeBusquedaDeSoluciones extends AbstractViewModel {

	private String memento;
	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		Memento m =  mementoService.parse(memento);
		
		setSolucion(m.get("solucion", String.class));
		setComentario(m.get("comentario", String.class));
		setMarca(m.get("marca", String.class));
		setModelo(m.get("modelo", String.class));
		setMotivo(m.get("motivo", String.class));
		
	}

	@Override
	public String viewModelMemento() {
		return this.memento;
	}

	private String solucion;
	private String motivo;
	private String comentario;
	private String modelo;
	private String marca;


	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	@MultiLine
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}



	

	public String title()
	{
		return "Busqueda de Soluciones";
	}
	
	public String iconName() {
        return "asistenciatecnica";
    }
	
	@javax.inject.Inject
    private MementoService mementoService;
}