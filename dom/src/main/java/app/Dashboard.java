package app;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;

import repo.notificaciones.RepoNotificaciones;
import dom.notificaciones.Notificaciones;
@MemberGroupLayout(columnSpans = { 0, 0, 0, 12 })
public class Dashboard extends AbstractViewModel {

	public String title() {
		return "Notificaciones No Leidas";
	}

	public String iconName() {
		return "notificacionVista";
	}

	// //////////////////////////////////////
	// ViewModel contract
	// //////////////////////////////////////

	private String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
	}


	@Inject
	private RepoNotificaciones repositorioNotificaciones;
	// //////////////////////////////////////
	// lista de notificaciones 
	// //////////////////////////////////////
	@Named("Notificaciones No leidas")
	//@Render(Type.EAGERLY)
	@Disabled
	@MemberOrder(sequence = "9")
	@MultiLine(numberOfLines = 6)
	public List<Notificaciones> getAllNotificaciones() {
		return repositorioNotificaciones.verNotificacionesNoLeidas();
	}

}