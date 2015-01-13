/*
 *  
 *
 *  Copyright (C) 2014 Estudiantina, All Rights Reserved.
 *  Autors:
 *  Matias Nahuel Heredia
 *  Jose Luis Troche
 *  Andres Robobich
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
package repo.curso;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import repo.persona.RepositorioPersona;
import dom.curso.Anio;
import dom.curso.Curso;
import dom.curso.Division;
import dom.curso.Turno;
import dom.establecimiento.Establecimiento;

@Named("Cursos")
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
			@Named("Establecimiento")final Establecimiento establecimiento,
			@Named("AÑO")final Anio anio,
			@Named("division")final Division division,
			@Named("ciclo lectivo")final int cicloLectivo,
			@Named("Turno")final Turno turno	
			)
	{
		
		final Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnio(anio);
		curso.setDivision(division);
		curso.setCicloLectivo(cicloLectivo);
		curso.setTurno(turno);
		curso.setEstablecimiento(establecimiento);
		container.persistIfNotAlready(curso);
		
		return curso;
		
	}
	
	
	/**
	 * ingresa un curso en el establecimiento actual
	 * @return curso Ingresado
	 */
	public Curso ingresarCursoEnEstablecimientoActual (
			@Named("AÑO")final Anio anio,
			@Named("division")final Division division,
			@Named("ciclo lectivo")final int cicloLectivo,
			@Named("Turno")final Turno turno	
			)
	{
		
		final Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnio(anio);
		curso.setDivision(division);
		curso.setCicloLectivo(cicloLectivo);
		curso.setTurno(turno);
		curso.setEstablecimiento(repoPersona.VerMisDatos().getEstablecimiento());
		container.persistIfNotAlready(curso);
		return curso;	
	}
	
	
	/**
	 * busca el curso por todas propiedades
	 * en el establecimiento actual
	 * @param anio
	 * @param division
	 * @param turno
	 * @param cicloLectivo
	 * @return
	 */
	public List<Curso> buscarCursoEnEsteEstablecimiento(@Named("año")Anio anio,@Named("Division")Division division,Turno turno,@Named("cicloLectivo")Integer cicloLectivo)
	{
		return allMatches(QueryDefault.create(Curso.class, "buscarPorTodo","anio",anio,"division",division,"turno",turno,"establecimiento",repoPersona.VerMisDatos().getEstablecimiento(),"cicloLectivo",cicloLectivo));
	}
	/**
	 * autocompletar campos de Cursos
	 * @param busqueda parametro de busqueda de cursos por 
	 * año Y division del curso 
	 * @return lista de cursos
	 */
	@Hidden
	public List<Curso> autoComplete(String busqueda)
	{
        Anio anio = Anio.valueOf(busqueda.split(" ")[0]);
        Division division = Division.valueOf(busqueda.split(" ")[1]);
        
		return allMatches(QueryDefault.create(Curso.class, "traerCursoPorlikeAnio","anio",anio,"division",division));
		
	}
	
	/**
	 * muestra una lista de todos las Cursos que existen
	 * @return lista de Netbooks
	 */
    public List<Curso> listaCursos() {
        return allMatches(QueryDefault.create(Curso.class, "traerTodo"));
    }
    
    public Establecimiento verCursosDentroDelEstablecimiento()
    {
    	return firstMatch(QueryDefault.create(Establecimiento.class, "traerPorEstablecimiento", "establecimiento",repoPersona.VerMisDatos().getEstablecimiento()));
    }
    
    
    
    @javax.inject.Inject
    RepositorioPersona repoPersona;
    
	@javax.inject.Inject 
    DomainObjectContainer container;
}