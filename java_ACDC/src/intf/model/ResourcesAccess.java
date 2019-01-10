package intf.model;

import java.net.URL;

public class ResourcesAccess {
	
	private static ResourcesAccess INSTANCE;
	
	private ResourcesAccess(){}
	
	public static ResourcesAccess getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ResourcesAccess();
		}
		
		return INSTANCE;
	}
	
	public URL getWebView(String HTMLFile){
		return getClass().getClassLoader().getResource("webview/" + HTMLFile);
	}

}
