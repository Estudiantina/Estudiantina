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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.isisaddons.wicket.wickedcharts.cpt.applib.WickedChart;
import repo.netbook.RepositorioNetbook;
import repo.persona.RepositorioPersona;
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
import dom.netbook.Marca;
import dom.netbook.Netbook;
import dom.netbook.situacion.SituacionDeNetbook;
import dom.solicituddeserviciotecnico.SolicitudServicioTecnico;


@DomainService
@Named("Graficos Estadisticos")
public class GraficosEstadisticos {

	/**
	 * muestra un grafico de tortas con la 
	 * cantidad de notebooks por modelo
	 * @return Grafico de torta
	 */
	@ActionSemantics(Of.SAFE)
	@Named("Estadisticas de modelo de netbook")
	public WickedChart graficosDeModeloDeNetbook() {

		Map<Marca, AtomicInteger> porModelo = Maps.newTreeMap();
		List<Netbook> allToDos = repositorioNetbook.listaNetbooks();
		for (Netbook miNetbook : allToDos) {
			Marca modelo = miNetbook.getMarca();
			AtomicInteger integer = porModelo.get(modelo);
			if (integer == null) {
				integer = new AtomicInteger();
				porModelo.put(modelo, integer);
			}
			integer.incrementAndGet();
		}

		return new WickedChart(
				new OpcionesDeGradienteDeGraficoDeModeloDeNetbook(porModelo));
	}

	/**
	 * muestra un gráfico de torta en el viewer de tipo WickedChart mostrando
	 * 
	 * @return graficoDeTorta
	 */
	@ActionSemantics(Of.SAFE)
	@Named("Estadisticas de alumnos Regulares y Libres")
	public WickedChart graficosDeEstadoAlumno() {

		Map<EstadoDeAlumno, AtomicInteger> porEstadoDeAlumno = Maps.newTreeMap();
		// EstadoDeAlumno estado = null;
		List<Alumno> AllTodos = repositorioPersona.listarAlumnos();
		for (Alumno unapersona : AllTodos) {
			EstadoDeAlumno category = unapersona.getEstadoDeAlumno();

			AtomicInteger integer = porEstadoDeAlumno.get(category);
			if (integer == null) {
				integer = new AtomicInteger();
				porEstadoDeAlumno.put(category, integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new OpcionesDeGradienteDeGraficoEstadoAlumno(
				porEstadoDeAlumno));
	}
	
	/**
	 * muestra un gráfico de torta en el viewer de tipo WickedChart mostrando
	 * 
	 * @return graficoDeTorta
	 */
	@ActionSemantics(Of.SAFE)
	@Named("por Situacion De Netbook")
	public WickedChart graficosDeNetbooksAsignadas() {

		Map<SituacionDeNetbook, AtomicInteger> porSituacionDeNetbook = Maps.newTreeMap();
		// EstadoDeAlumno estado = null;
		List<Netbook> listaNetbook = repositorioNetbook.listaNetbooks();
		for (Netbook netbook : listaNetbook) {
			SituacionDeNetbook situacionDeNetbook = netbook.getSituacion();

			AtomicInteger integer = porSituacionDeNetbook.get(situacionDeNetbook);
			if (integer == null) {
				integer = new AtomicInteger();
				porSituacionDeNetbook.put(situacionDeNetbook, integer);
			}
			integer.incrementAndGet();
		}
		return new WickedChart(new OpcionesDeGradienteDeGraficoSituacionNetbook(
				porSituacionDeNetbook));
	}

	
	public static class OpcionesDeGradienteDeGraficoDeModeloDeNetbook extends
			Options {
		private static final long serialVersionUID = 1L;

		public OpcionesDeGradienteDeGraficoDeModeloDeNetbook(
				Map<Marca, AtomicInteger> byCategory) {

			setChartOptions(new ChartOptions()
					.setPlotBackgroundColor(new NullColor())
					.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE));

			setTitle(new Title("Computadoras: Modelo."));

			PercentageFormatter formatter = new PercentageFormatter();
			setTooltip(new Tooltip().setFormatter(formatter)
					.setPercentageDecimals(1));

			setPlotOptions(new PlotOptionsChoice().setPie(new PlotOptions()
					.setAllowPointSelect(Boolean.TRUE)
					.setCursor(Cursor.POINTER)
					.setDataLabels(
							new DataLabels().setEnabled(Boolean.TRUE)
									.setColor(new HexColor("#000000"))
									.setConnectorColor(new HexColor("#000000"))
									.setFormatter(formatter))));

			Series<Point> series = new PointSeries().setType(SeriesType.PIE);
			int i = 0;
			for (Map.Entry<Marca, AtomicInteger> entry : byCategory
					.entrySet()) {
				series.addPoint(new Point(entry.getKey().name(), entry
						.getValue().get()).setColor(new RadialGradient()
						.setCx(0.5).setCy(0.3).setR(0.7)
						.addStop(0, new HighchartsColor(i))
						.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
				i++;
			}
			addSeries(series);
		}
	}

	public static class OpcionesDeGradienteDeGraficoEstadoAlumno extends
			Options {
		private static final long serialVersionUID = 1L;

		public OpcionesDeGradienteDeGraficoEstadoAlumno(
				Map<EstadoDeAlumno, AtomicInteger> byCategory) {

			setChartOptions(new ChartOptions()
					.setPlotBackgroundColor(new NullColor())
					.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE));

			setTitle(new Title("Estadistica de Estados de Alumnos."));

			PercentageFormatter formatter = new PercentageFormatter();
			setTooltip(new Tooltip().setFormatter(formatter)
					.setPercentageDecimals(1));

			setPlotOptions(new PlotOptionsChoice().setPie(new PlotOptions()
					.setAllowPointSelect(Boolean.TRUE)
					.setCursor(Cursor.POINTER)
					.setDataLabels(
							new DataLabels().setEnabled(Boolean.TRUE)
									.setColor(new HexColor("#000000"))
									.setConnectorColor(new HexColor("#000000"))
									.setFormatter(formatter))));

			Series<Point> series = new PointSeries().setType(SeriesType.PIE);
			int i = 0;
			for (Map.Entry<EstadoDeAlumno, AtomicInteger> entry : byCategory
					.entrySet()) {
				series.addPoint(new Point(entry.getKey().name(), entry
						.getValue().get()).setColor(new RadialGradient()
						.setCx(0.5).setCy(0.3).setR(0.7)
						.addStop(0, new HighchartsColor(i))
						.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
				i++;
			}
			addSeries(series);
		}
	}
	
	
	
	
	public static class OpcionesDeGradienteDeGraficoSituacionNetbook extends
	Options {
private static final long serialVersionUID = 1L;

public OpcionesDeGradienteDeGraficoSituacionNetbook(
		Map<SituacionDeNetbook, AtomicInteger> byCategory) {

	setChartOptions(new ChartOptions()
			.setPlotBackgroundColor(new NullColor())
			.setPlotBorderWidth(null).setPlotShadow(Boolean.FALSE));

	setTitle(new Title("Estadistica de Situacion De Netbooks."));

	PercentageFormatter formatter = new PercentageFormatter();
	setTooltip(new Tooltip().setFormatter(formatter)
			.setPercentageDecimals(1));

	setPlotOptions(new PlotOptionsChoice().setPie(new PlotOptions()
			.setAllowPointSelect(Boolean.TRUE)
			.setCursor(Cursor.POINTER)
			.setDataLabels(
					new DataLabels().setEnabled(Boolean.TRUE)
							.setColor(new HexColor("#000000"))
							.setConnectorColor(new HexColor("#000000"))
							.setFormatter(formatter))));

	Series<Point> series = new PointSeries().setType(SeriesType.PIE);
	int i = 0;
	for (Map.Entry<SituacionDeNetbook, AtomicInteger> entry : byCategory
			.entrySet()) {
		series.addPoint(new Point(entry.getKey().name(), entry
				.getValue().get()).setColor(new RadialGradient()
				.setCx(0.5).setCy(0.3).setR(0.7)
				.addStop(0, new HighchartsColor(i))
				.addStop(1, new HighchartsColor(i).brighten(-0.3f))));
		i++;
	}
	addSeries(series);
}
}
	
	public List<NetbookReparadasAnualmente> verNetbooksReparadasAnualmente(@Named("Año a Mostrar")int anio){
		List<NetbookReparadasAnualmente> reparadas = new ArrayList<NetbookReparadasAnualmente>();
		List<SolicitudServicioTecnico> listaNetbooksReparadas = container.allMatches(QueryDefault.create(SolicitudServicioTecnico.class, "traerSolicitudesReparadas","institucion",repositorioPersona.verMisDatos().getEstablecimiento()));
		int[] cantidadDeNetbooksReparadas = new int[13];
		//todas las pociciones del array en 0
		for(int i=0;i<cantidadDeNetbooksReparadas.length;i++)
		{
			cantidadDeNetbooksReparadas[i]=0;
		}
		//contamos cuantas netbooks se repararon en cada mes
		for (SolicitudServicioTecnico solicitud:listaNetbooksReparadas)
		{
			if(solicitud.isReparada()&& solicitud.getFechaDeSolucion().getYear() == anio)
			{
			cantidadDeNetbooksReparadas[solicitud.getFechaDeSolicitud().getMonthOfYear()]++;
			}
		}
		//Recorremos todos los meses y asignamos cuantas netbooks se repararon al web view
		for (Mes mes:Mes.values())
		{
			Memento m = mementoService.create();
			BigDecimal variableTemporal = new BigDecimal(cantidadDeNetbooksReparadas[mes.ordinal()]);
			m.set("mes", mes);
			m.set("cantidadNetbookReparadas", variableTemporal);
			reparadas.add(container.newViewModelInstance(NetbookReparadasAnualmente.class,m.asString()));
		}	
		
		return reparadas;
	}
	
	@javax.inject.Inject 
    DomainObjectContainer container; 
	
	@javax.inject.Inject
    private MementoService mementoService;
	
	@javax.inject.Inject
	private RepositorioNetbook repositorioNetbook;

	@javax.inject.Inject
	private RepositorioPersona repositorioPersona;
}