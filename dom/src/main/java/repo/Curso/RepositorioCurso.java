package repo.Curso;

import java.util.Date;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;

import dom.Curso.Curso;


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
			@Named("AÑO")final String ano,
			@Named("ciclo lectivo")final Date cicloLectivo,
			@Named("Division")final String division,
			@Named("Turno")final String turno
			)
	{
		
		final Curso curso = container.newTransientInstance(Curso.class);
		curso.setAno(ano); 
		curso.setCicloLectivo(cicloLectivo);
		curso.setDivision(division);
		curso.setTurno(turno);
		
		container.persistIfNotAlready(curso);
		
		return curso;
		
	}
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
