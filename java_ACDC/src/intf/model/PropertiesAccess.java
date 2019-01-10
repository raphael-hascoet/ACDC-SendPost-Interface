package intf.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesAccess {
	
	private static PropertiesAccess INSTANCE;
	
	private String propertiesFileName;
	
	private PropertiesAccess(){
		propertiesFileName = "resources/config.properties";
	}
	
	public static PropertiesAccess getInstance(){
		if(INSTANCE == null){
			INSTANCE = new PropertiesAccess();
		}
		
		return INSTANCE;
	}
	
	FileInputStream inputStream;

	public String getLocalRepo(){
		String ret = "";
				
		try{
			Properties prop = new Properties();

			inputStream = new FileInputStream(propertiesFileName);

			prop.load(inputStream);

			ret = prop.getProperty("localRepo");
			
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception " + e);
			}
		}

		return ret;
	}
	
	FileOutputStream outputStream;

	public void setLocalRepo(String newRepo){
		try{
			Properties prop = new Properties();

			outputStream = new FileOutputStream(propertiesFileName);

			prop.setProperty("localRepo", newRepo);

			prop.store(outputStream, null);
			
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				System.out.println("Exception " + e);
			}
		}
	}

}
