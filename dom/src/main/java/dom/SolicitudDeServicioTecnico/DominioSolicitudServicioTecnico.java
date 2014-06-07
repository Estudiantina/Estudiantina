package dom.SolicitudDeServicioTecnico;

import java.util.Calendar;

import dom.Netbook.Netbook;

public class DominioSolicitudServicioTecnico {
    //public solicitante integrante de la institucion  
	public String motivoDeSolicitud;
	public Calendar fechaDeSolicitud;
	public String solucion;
	public Calendar fechaDeSolucion;
	public Integer prioridad;
	public Netbook netbook;
	public String codigoSolicitud;
	public String numeroTiquetRegistro;
	public String comentario;
	public String getMotivoDeSolicitud() {
		return motivoDeSolicitud;
	}
	public void setMotivoDeSolicitud(String motivoDeSolicitud) {
		this.motivoDeSolicitud = motivoDeSolicitud;
	}
	public Calendar getFechaDeSolicitud() {
		return fechaDeSolicitud;
	}
	public void setFechaDeSolicitud(Calendar fechaDeSolicitud) {
		this.fechaDeSolicitud = fechaDeSolicitud;
	}
	public String getSolucion() {
		return solucion;
	}
	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}
	public Calendar getFechaDeSolucion() {
		return fechaDeSolucion;
	}
	public void setFechaDeSolucion(Calendar fechaDeSolucion) {
		this.fechaDeSolucion = fechaDeSolucion;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	public Netbook getNetbook() {
		return netbook;
	}
	public void setNetbook(Netbook netbook) {
		this.netbook = netbook;
	}
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}
	public String getNumeroTiquetRegistro() {
		return numeroTiquetRegistro;
	}
	public void setNumeroTiquetRegistro(String numeroTiquetRegistro) {
		this.numeroTiquetRegistro = numeroTiquetRegistro;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
