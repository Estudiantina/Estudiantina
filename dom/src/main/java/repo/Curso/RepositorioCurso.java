package repo.Curso;

import java.util.Date;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;

import dom.Curso.Curso;
import dom.Curso.Turno;


@Named("Administrar Curso")
public class RepositorioCurso extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Curso";
    }
    
	/**
	 * muestra los curso 
	 * @return lista de los Curso
	 */
	public Curso ingresarCurso (
			@Named("AÑO y division")final String anoYdivision,
			@Named("ciclo lectivo")final int cicloLectivo,
			@Named("Turno")final Turno turno
			)
	{
		
		final Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnoYdivision(anoYdivision); 
		curso.setCicloLectivo(cicloLectivo);
		curso.setTurno(turno);
		
		container.persistIfNotAlready(curso);
		
		return curso;
		
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
