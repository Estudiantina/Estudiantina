package repo.Curso;



import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.Curso.Curso;
import dom.Curso.Turno;


@Named("Administrar Curso")
public class RepositorioCurso extends AbstractFactoryAndRepository{
	
	public String getId() {
        return "Curso";
    }
    
	
	public String iconName() {
        return "curso";
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
	
	/**
	 * muestra una lista de todos las Cursos que existen
	 * @return lista de Netbooks
	 */
    public List<Curso> listaCursos() {
        return allMatches(QueryDefault.create(Curso.class, "traerTodo"));
    }
	
	
	@javax.inject.Inject 
    DomainObjectContainer container;
	

}
