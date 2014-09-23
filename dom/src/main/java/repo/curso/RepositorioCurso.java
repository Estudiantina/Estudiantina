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

import dom.curso.Curso;
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
			@Named("AÑO y division")final String anoYdivision,
			@Named("ciclo lectivo")final int cicloLectivo,
			@Named("Turno")final Turno turno
			
			)
	{
		
		final Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnoYdivision(anoYdivision); 
		curso.setCicloLectivo(cicloLectivo);
		curso.setTurno(turno);
		curso.setEstablecimiento(establecimiento);
		container.persistIfNotAlready(curso);
		
		return curso;
		
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
		return allMatches(QueryDefault.create(Curso.class, "traerCursoPorlikeAnio","anoYdivision",busqueda));
		
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
