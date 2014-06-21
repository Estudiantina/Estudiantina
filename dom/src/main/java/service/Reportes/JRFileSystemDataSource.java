package service.Reportes;

import java.io.File;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class JRFileSystemDataSource implements JRDataSource {

	File[] files = null;
	int index = -1;
	public JRFileSystemDataSource(String path) {
	File dir = new File(path);
	if (dir.exists() && dir.isDirectory()) {
	files = dir.listFiles();
	}
	}

	@Override
	public boolean next() throws JRException {
	index++;
	if (files != null && index < files.length) {
	return true;
	}
	return false;
	}

	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
	File f = files[index];
	if (f == null) {
	return null;
	}
	if (jrf.getName().equals("name")) {
	return f.getName();
	} else if (jrf.getName().equals("IS_DIRECTORY")) {
	return new Boolean(f.isDirectory());
	} else if (jrf.getName().equals("totalSpace")) {
	return new Long(f.length());
	}
	// Field not found...
	return null;
	}

	/**
	* This method is responsible for setting the field names in the provider.(Required for the provider)
	* @return 
	*/
	public static String[] fieldNames() { 
	String[] fieldNames = {"name", "IS_DIRECTORY", "totalSpace"}; 
	return fieldNames; 
	} 

}
