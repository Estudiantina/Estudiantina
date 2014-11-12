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
package servicio.estadisticas;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;

import repo.netbook.RepositorioNetbook;
import repo.persona.RepositorioPersona;
//import servicio.estadisticas.GenerarEstadistica.PieWithGradientOptions;

import com.google.common.collect.Maps;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.Cursor;
import com.googlecode.wickedcharts.highcharts.options.DataLabels;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.PlotOptions;
import com.googlecode.wickedcharts.highcharts.options.PlotOptionsChoice;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.color.HexColor;
import com.googlecode.wickedcharts.highcharts.options.color.HighchartsColor;
import com.googlecode.wickedcharts.highcharts.options.color.NullColor;
import com.googlecode.wickedcharts.highcharts.options.color.RadialGradient;
import com.googlecode.wickedcharts.highcharts.options.functions.PercentageFormatter;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.highcharts.options.series.PointSeries;
import com.googlecode.wickedcharts.highcharts.options.series.Series;

import dom.alumno.Alumno;
import dom.alumno.EstadoDeAlumno;
import dom.netbook.ModeloNetbook;
import dom.netbook.Netbook;


@DomainService
@Named("Graficos Estadisticos")
public class GraficosEstadisticos  {
	
//Para probar voy a realizar un grafico la cantidad de computadoras por modelo que existen.
	///////////////////////////////////////
	//Estadistica Modelos de Netbook
	/////////////////////////////////////
    @ActionSemantics(Of.SAFE)
    public WickedChart filtrarPorModeloNetbook() {
        
        Map<ModeloNetbook, AtomicInteger> byCategory = Maps.newTreeMap();
        List<Netbook> allToDos = repositorioNetbook.listaNetbooks();
        for (Netbook unaComputadora : allToDos) {
            ModeloNetbook category = unaComputadora.getModelo();
            AtomicInteger integer = byCategory.get(category);
            if(integer == null) {
                integer = new AtomicInteger();
                byCategory.put(category, integer);
            }
            integer.incrementAndGet();
        }
        
        return new WickedChart(new PieWithGradientOptions(byCategory));
    }
    ///////////////////////////////////////
    //Estadistica de estados de Alumnos
    /////////////////////////////////////
    @ActionSemantics(Of.SAFE)
	public WickedChart filtrarPorEstadoAlumno(){
		
		Map<EstadoDeAlumno, AtomicInteger> byCategory = Maps.newTreeMap();
		//EstadoDeAlumno estado = null;
		List<Alumno> AllTodos = repositorioPersona.listarAlumnos();
		for (Alumno unapersona: AllTodos){
			EstadoDeAlumno category = unapersona.getEstadoDeAlumno();
			
            AtomicInteger integer = byCategory.get(category);
            if(integer == null) {
                integer = new AtomicInteger();
                byCategory.put(category, integer);
            }
            integer.incrementAndGet();
		}
		return new WickedChart(new PieWithGradientOption(byCategory));
	}
    
    
    public static class PieWithGradientOptions extends Options {
        private static final long serialVersionUID = 1L;

        public PieWithGradientOptions(Map<ModeloNetbook, AtomicInteger> byCategory) {
        
            setChartOptions(new ChartOptions()
                .setPlotBackgroundColor(new NullColor())
                .setPlotBorderWidth(null)
                .setPlotShadow(Boolean.FALSE));
            
            setTitle(new Title("Computadoras: Modelo."));
        
            PercentageFormatter formatter = new PercentageFormatter();
            setTooltip(
                    new Tooltip()
                        .setFormatter(
                                formatter)
                        .       setPercentageDecimals(1));
        
            setPlotOptions(new PlotOptionsChoice()
                .setPie(new PlotOptions()
                .setAllowPointSelect(Boolean.TRUE)
                .setCursor(Cursor.POINTER)
                .setDataLabels(new DataLabels()
                .setEnabled(Boolean.TRUE)
                .setColor(new HexColor("#000000"))
                .setConnectorColor(new HexColor("#000000"))
                .setFormatter(formatter))));

            Series<Point> series = new PointSeries()
                .setType(SeriesType.PIE);
            int i=0;
            for (Map.Entry<ModeloNetbook, AtomicInteger> entry : byCategory.entrySet()) {
                series
                .addPoint(
                        new Point(entry.getKey().name(), entry.getValue().get()).setColor(
                                new RadialGradient()
                                    .setCx(0.5)
                                    .setCy(0.3)
                                    .setR(0.7)
                                        .addStop(0, new HighchartsColor(i))
                                        .addStop(1, new HighchartsColor(i).brighten(-0.3f))));
                i++;
            }
            addSeries(series);
        }
    }
    
    
    public static class PieWithGradientOption extends Options {
        private static final long serialVersionUID = 1L;

        public PieWithGradientOption(Map<EstadoDeAlumno, AtomicInteger> byCategory) {
        
            setChartOptions(new ChartOptions()
                .setPlotBackgroundColor(new NullColor())
                .setPlotBorderWidth(null)
                .setPlotShadow(Boolean.FALSE));
            
            setTitle(new Title("Estadistica de Estados de Alumnos."));
        
            PercentageFormatter formatter = new PercentageFormatter();
            setTooltip(
                    new Tooltip()
                        .setFormatter(
                                formatter)
                        .       setPercentageDecimals(1));
        
            setPlotOptions(new PlotOptionsChoice()
                .setPie(new PlotOptions()
                .setAllowPointSelect(Boolean.TRUE)
                .setCursor(Cursor.POINTER)
                .setDataLabels(new DataLabels()
                .setEnabled(Boolean.TRUE)
                .setColor(new HexColor("#000000"))
                .setConnectorColor(new HexColor("#000000"))
                .setFormatter(formatter))));

            Series<Point> series = new PointSeries()
                .setType(SeriesType.PIE);
            int i=0;
            for (Map.Entry<EstadoDeAlumno, AtomicInteger> entry : byCategory.entrySet()) {
                series
                .addPoint(
                        new Point(entry.getKey().name(), entry.getValue().get()).setColor(
                                new RadialGradient()
                                    .setCx(0.5)
                                    .setCy(0.3)
                                    .setR(0.7)
                                        .addStop(0, new HighchartsColor(i))
                                        .addStop(1, new HighchartsColor(i).brighten(-0.3f))));
                i++;
            }
            addSeries(series);
        }
    }

    
    // //////////////////////////////////////
    // Injected services
    // //////////////////////////////////////

    @javax.inject.Inject
    private RepositorioNetbook repositorioNetbook;

    @javax.inject.Inject
    private RepositorioPersona repositorioPersona;
}