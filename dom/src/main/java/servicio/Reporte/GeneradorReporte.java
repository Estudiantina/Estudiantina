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
package servicio.Reporte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.isis.applib.value.Blob;

public class GeneradorReporte {

	/**
	 * el metodo generarReporte se encarga de generar 
	 * un archivo .pdf para que el usuario
	 * pueda imprimir o guardar en su maquina
	 * @param ruta ruta o path del reporte incluida la extension .jrxml 
	 * @param parametros parametros de tipo HashMap<String,Object> o clave valor del reporte
	 * @param nombrereporte nombre del archivo que se va a descargar en el browser
	 * @return retorna un archivo pdf en un objeto Blob con el reporte generado 
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	public static Blob generarReporte (String ruta,HashMap<String,Object> parametros,String nombrereporte) throws FileNotFoundException, JRException
	{
		Object[] obj= new Object[1];
		obj[0]="";
		JRBeanArrayDataSource jrDataSource= new JRBeanArrayDataSource(obj);
		File file = new File(ruta);
		InputStream input = new FileInputStream(file);
		JasperDesign jd = JRXmlLoader.load(input);
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		JasperPrint print = JasperFillManager.fillReport(reporte, parametros,jrDataSource);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,print); 
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("/tmp/"+nombrereporte+".pdf"));
		exporter.exportReport();
		
		
        
		File resume = new File("/tmp/"+nombrereporte+".pdf");
		if (!(resume.exists()))
		{
		try {
			resume.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
		byte[] fileContent = new byte[(int) resume.length()];
		try {
		    FileInputStream fileInputStream = new FileInputStream(resume);
		         
		    fileInputStream.read(fileContent);
		    fileInputStream.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		Blob blob= new Blob(nombrereporte+".pdf","application/pdf",fileContent);
		return blob;
		
	}
	
}
